package co.micia.projects.restapi.pricelist.dalbusiness.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import co.micia.projects.restapi.pricelist.daljpa.model.TBLBoxTypePT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLCompanyPT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLCustomerPT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLinventoryPT;
import co.micia.projects.restapi.pricelist.daljpa.repositories.CompanyRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.CustomerRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.InventoryRepository;
import co.micia.projects.restapi.pricelist.integrations.CodigoProductoDTO;
import co.micia.projects.restapi.pricelist.integrations.CompanyInventoryDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductCompanyDTO;
import co.micia.projects.restapi.pricelist.dalbusiness.utilities.ProductPriceUtility;
/**
 * Service that implements ProductUtilityService Interface 
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
import co.micia.projects.restapi.pricelist.integrations.ProductInventoryDTO;
@Service
public class ProductUtilityServiceImpl implements ProductUtilityService {
    private static final Logger log = LoggerFactory.getLogger(ProductUtilityServiceImpl.class);
    /*Declara las variables donde ser referenciaran  los Beans de repositorio necesarios para realizar 
    /*la logica de negocio
     * */
	private CompanyRepository compRepo;
	private InventoryRepository inveRepo;
	private CustomerRepository cusRepo;
	private ProductPriceUtility productPriceUtility;

	
    /* Inyecta por el constructor los Beans de repositorio necesarios para realizar 
    /*la logica de negocio
     * */
	public ProductUtilityServiceImpl(CompanyRepository compRepo, InventoryRepository inveRepo,CustomerRepository cusRepo,ProductPriceUtility productPriceUtility) {
		this.compRepo = compRepo;
		this.inveRepo = inveRepo;
		this.cusRepo = cusRepo;
		this.productPriceUtility = productPriceUtility;
		
	}

	/*
	 * Metodod que consultaa los productos de inventario de una compañia
	 * y calcula el finalFreight
	 */
	@Override
	public CompanyInventoryDTO findAllProductsByCompanyId(Long companyId) {

	    Optional<TBLCompanyPT> compEntityOpt; // variable to find optional entity of TBLCompanyPT
		List<ProductInventoryDTO> products = new ArrayList<ProductInventoryDTO>();
		CompanyInventoryDTO results = new CompanyInventoryDTO();
	  
		//Find the company by id given in the @param companyId
	    compEntityOpt = compRepo.findById(companyId);
	  
		if(compEntityOpt.isPresent()) {
		  
		  // Get all the inventory products for the company with id companyId
		  List<TBLinventoryPT> lstinve =   inveRepo.findAllByCompanyId(companyId);
		  
			if(lstinve != null && !lstinve.isEmpty()) {
				 products= productPriceUtility.getProductInventoryByCompay(lstinve);
				 results = new CompanyInventoryDTO(companyId,products);
			}
		}
		return results;
	}

	/*
	 * Metodod que consulta los productos de inventario de una customer
	 * y calcula el precio
	 */
	@Override
	public List<ProductCompanyDTO> findAllProductsPrice(Long customerId) {
		Optional<TBLCustomerPT> cusEntityOpt ; // variable to find optional entity of TBLCustomerPT
		List<ProductCompanyDTO> products = new ArrayList<ProductCompanyDTO>();
		cusEntityOpt = cusRepo.findById(customerId);
		BigDecimal markdown = new BigDecimal(1L);
		log.info("Looking for customer with id: " + customerId);
		if(cusEntityOpt.isPresent()) {
				TBLCustomerPT cusEntity = cusEntityOpt.get();;
				markdown = cusEntity.getMarkdown();
				log.info("get markdow: " + markdown);
			  // Get all the inventory products for all  companies
			  List<TBLinventoryPT> lstinve =   inveRepo.findAll();
			  
				if(lstinve != null && !lstinve.isEmpty()) {
					 products= productPriceUtility.getProductInventoryByCustomer(lstinve,markdown);
				}
		}
		return products;
	}
	/*
	 * Metodod que consulta los productos de inventario de una compañia
	 * y calcula los codigos de los productos
	 */
	@Override
	public List<CodigoProductoDTO> findAllProductsCode(Long companyId) {
		Optional<TBLCompanyPT> compEntityOpt; // variable to find optional entity of TBLCompanyPT
		List<CodigoProductoDTO> products = new ArrayList<CodigoProductoDTO>();
		
		//Find the company by id given in the @param companyId
	    compEntityOpt = compRepo.findById(companyId); 
	    
		if(compEntityOpt.isPresent()) {
		  // Get all the inventory products for the company with id companyId
		  List<TBLinventoryPT> lstinve =   inveRepo.findAllByCompanyId(companyId);
		  
			if(lstinve != null && !lstinve.isEmpty()) {
				 products= productPriceUtility.getInventaryProductCode(lstinve);
			}
		}
		return products;
	}
	
	

	 


}
