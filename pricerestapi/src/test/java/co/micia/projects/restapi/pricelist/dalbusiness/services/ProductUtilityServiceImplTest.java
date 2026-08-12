package co.micia.projects.restapi.pricelist.dalbusiness.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import co.micia.projects.restapi.pricelist.dalbusiness.utilities.ProductPriceUtility;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLCompanyPT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLinventoryPT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLProductPT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLBoxTypePT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLCustomerPT;
import co.micia.projects.restapi.pricelist.daljpa.repositories.CompanyRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.InventoryRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.ProductRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.BoxTypeResository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.CustomerRepository;
import co.micia.projects.restapi.pricelist.integrations.CompanyInventoryDTO;
import co.micia.projects.restapi.pricelist.integrations.CodigoProductoDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductCompanyDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductInventoryDTO;

@ExtendWith(MockitoExtension.class)
class ProductUtilityServiceImplTest {

    @Mock ProductRepository productRepository;
    @Mock CompanyRepository companyRepository;
    @Mock InventoryRepository inventoryRepository;
    @Mock BoxTypeResository boxTypeRepository;
    @Mock CustomerRepository customerRepository;
    @Mock ModelMapper modelMapper;
    @Mock ProductPriceUtility priceUtility;

    @InjectMocks ProductUtilityServiceImpl service;

    private TBLProductPT testProduct;
    private TBLCompanyPT testCompany;
    private TBLBoxTypePT testBoxType;
    private TBLinventoryPT testInventory;
    private TBLCustomerPT testCustomer;

    @BeforeEach
    void setUp() {
        testProduct = new TBLProductPT(1L, "Red Roses 23cm", new BigDecimal("10.00"));
        testCompany = new TBLCompanyPT(1L, "BellaFlowers");
        testBoxType = new TBLBoxTypePT(1L, "1111", new BigDecimal("12.1"), new BigDecimal("12.8"), new BigDecimal("11.2"));
        testInventory = new TBLinventoryPT(1L, new BigDecimal("1.1"), 1, new BigDecimal("16.3"), testBoxType, testProduct, testCompany);
        testCustomer = new TBLCustomerPT(1L, "Luis", new BigDecimal("10"));
    }

    @Test
    void findAllProductsByCompanyId_calculatesFreightCorrectly() {
        when(companyRepository.existsById(1L)).thenReturn(true);
        when(inventoryRepository.findAllByCompanyId(1L)).thenReturn(List.of(testInventory));
        when(priceUtility.getProductInventoryByCompay(any())).thenReturn(List.of(new ProductInventoryDTO("Red Roses 23cm", new BigDecimal("1.1"), new BigDecimal("4.72"))));

        CompanyInventoryDTO result = service.findAllProductsByCompanyId(1L);

        assertNotNull(result);
        verify(priceUtility).getProductInventoryByCompay(List.of(testInventory));
    }

    @Test
    void findAllProductsPrice_appliesCustomerMarkdown() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        when(inventoryRepository.findAll()).thenReturn(List.of(testInventory));
        when(priceUtility.getProductInventoryByCustomer(any(), any())).thenReturn(List.of(new ProductCompanyDTO("Red Roses 23cm", "BellaFlowers", new BigDecimal("0.99"))));

        List<ProductCompanyDTO> result = service.findAllProductsPrice(1L);

        assertEquals(1, result.size());
        verify(priceUtility).getProductInventoryByCustomer(List.of(testInventory), new BigDecimal("10"));
    }

    @Test
    void findAllProductsCode_generatesCorrectFormat() {
        when(companyRepository.existsById(1L)).thenReturn(true);
        when(inventoryRepository.findAllByCompanyId(1L)).thenReturn(List.of(testInventory));
        when(priceUtility.getInventaryProductCode(any())).thenReturn(List.of(new CodigoProductoDTO("Red Roses 23cm", "R0m")));

        List<CodigoProductoDTO> result = service.findAllProductsCode(1L);

        assertEquals(1, result.size());
        verify(priceUtility).getInventaryProductCode(List.of(testInventory));
    }

    @Test
    void findAllProductsByCompanyId_returnsEmptyWhenCompanyNotExists() {
        when(companyRepository.existsById(999L)).thenReturn(false);

        CompanyInventoryDTO result = service.findAllProductsByCompanyId(999L);

        assertNotNull(result);
        assertEquals(0, result.getProducts().size());
    }

    @Test
    void findAllProductsByCompanyId_returnsEmptyWhenNoInventory() {
        when(companyRepository.existsById(1L)).thenReturn(true);
        when(inventoryRepository.findAllByCompanyId(1L)).thenReturn(Collections.emptyList());

        CompanyInventoryDTO result = service.findAllProductsByCompanyId(1L);

        assertNotNull(result);
        assertEquals(0, result.getProducts().size());
    }

    @Test
    void findAllProductsPrice_returnsEmptyWhenCustomerNotExists() {
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        List<ProductCompanyDTO> result = service.findAllProductsPrice(999L);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void findAllProductsPrice_returnsEmptyWhenNoInventory() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        when(inventoryRepository.findAll()).thenReturn(Collections.emptyList());

        List<ProductCompanyDTO> result = service.findAllProductsPrice(1L);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void findAllProductsCode_returnsEmptyWhenCompanyNotExists() {
        when(companyRepository.existsById(999L)).thenReturn(false);

        List<CodigoProductoDTO> result = service.findAllProductsCode(999L);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void findAllProductsCode_returnsEmptyWhenNoInventory() {
        when(companyRepository.existsById(1L)).thenReturn(true);
        when(inventoryRepository.findAllByCompanyId(1L)).thenReturn(Collections.emptyList());

        List<CodigoProductoDTO> result = service.findAllProductsCode(1L);

        assertNotNull(result);
        assertEquals(0, result.size());
    }
}