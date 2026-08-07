package co.micia.projects.restapi.pricelist.dalbusiness.services;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import co.micia.projects.restapi.pricelist.dalbusiness.utilities.ProductPriceUtility;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLCustomerPT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLinventoryPT;
import co.micia.projects.restapi.pricelist.daljpa.repositories.CompanyRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.CustomerRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.InventoryRepository;
import co.micia.projects.restapi.pricelist.integrations.CodigoProductoDTO;
import co.micia.projects.restapi.pricelist.integrations.CompanyInventoryDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductCompanyDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductInventoryDTO;

@Service
public class ProductUtilityServiceImpl implements ProductUtilityService {
    private final CompanyRepository companyRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;
    private final ProductPriceUtility productPriceUtility;

    public ProductUtilityServiceImpl(CompanyRepository companyRepository,
            InventoryRepository inventoryRepository,
            CustomerRepository customerRepository,
            ProductPriceUtility productPriceUtility) {
        this.companyRepository = companyRepository;
        this.inventoryRepository = inventoryRepository;
        this.customerRepository = customerRepository;
        this.productPriceUtility = productPriceUtility;
    }

    @Override
    public CompanyInventoryDTO findAllProductsByCompanyId(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            return new CompanyInventoryDTO(companyId, Collections.emptyList());
        }

        List<TBLinventoryPT> inventory = inventoryRepository.findAllByCompanyId(companyId);
        List<ProductInventoryDTO> products = productPriceUtility.getProductInventoryByCompay(inventory);
        return new CompanyInventoryDTO(companyId, products);
    }

    @Override
    public List<ProductCompanyDTO> findAllProductsPrice(Long customerId) {
        return customerRepository.findById(customerId)
                .map(TBLCustomerPT::getMarkdown)
                .map(markdown -> productPriceUtility.getProductInventoryByCustomer(inventoryRepository.findAll(), markdown))
                .orElseGet(Collections::emptyList);
    }

    @Override
    public List<CodigoProductoDTO> findAllProductsCode(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            return Collections.emptyList();
        }

        return productPriceUtility.getInventaryProductCode(inventoryRepository.findAllByCompanyId(companyId));
    }
}
