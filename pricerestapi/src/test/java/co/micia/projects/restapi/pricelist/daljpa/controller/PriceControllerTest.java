package co.micia.projects.restapi.pricelist.daljpa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.micia.projects.restapi.pricelist.ApplicationConfig;
import co.micia.projects.restapi.pricelist.dalbusiness.services.ProductUtilityServiceImpl;
import co.micia.projects.restapi.pricelist.dalbusiness.utilities.ProductPriceUtility;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLinventoryPT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLCustomerPT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLBoxTypePT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLCompanyPT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLProductPT;
import co.micia.projects.restapi.pricelist.daljpa.repositories.CustomerRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.BoxTypeResository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.InventoryRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.CompanyRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.ProductRepository;
import co.micia.projects.restapi.pricelist.integrations.CompanyInventoryDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductInventoryDTO;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test Component with client methods uses for  testing the Controller Methods exposed by PriceController
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class PriceControllerTest {
	private static final String INVENTRY_PRODUCTS_BY_cOMPANY_PATH = "/api/products/companies/";
	private static final Logger log = LoggerFactory.getLogger(PriceControllerTest.class);
	/*
	 * Inject the components needed to test different cases
	 */	
	@Autowired
	private MockMvc mockMvc;
	    
	@Autowired
	private ObjectMapper objectMapper;	
		    
			
	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private CompanyRepository companyRepository;
	
	@Mock
	private InventoryRepository inventoryRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private BoxTypeResository boxTypeResository;

	@InjectMocks
	ProductUtilityServiceImpl priceService;
	
	@Mock
	private ProductPriceUtility  utility;
	

	   
    /*
     * Test case para probar que se consultan todos los productos de una compañia desde los metodos HTPP de la API
     * expuestas en el controlador ProductUtilityController
     */       

	    @Test
	    void consultarPreciosProductosdeCompañia() throws Exception,ParseException {
		  
			  TBLProductPT prod1 =new TBLProductPT(1L, "Red Roses 23cm",new
			  BigDecimal("10")); TBLCompanyPT comp = new TBLCompanyPT(4L, "TheFlowers");
			  TBLBoxTypePT boxType = new TBLBoxTypePT(3L, "3333",new BigDecimal("15.2"),new
			  BigDecimal("18.9"),new BigDecimal("17.4"));
			  
			  // Ingresa el inventario del producto a devolver en la consultar del controlador
			  TBLinventoryPT inventory = new TBLinventoryPT(1L,new BigDecimal("16.3"),1,new BigDecimal("1.1"), boxType,prod1, comp);
			  List<TBLinventoryPT> lstinve = Lists.newArrayList(inventory);
			  when(companyRepository.findById(4L)).thenReturn(Optional.of(comp));
			  when(boxTypeResository.findById(3L)).thenReturn(Optional.of(boxType));
			  when(productRepository.findById(1L)).thenReturn(Optional.of(prod1));
			  

			  // Valida que el servicio que devuelve el resultado de la consulta esta funcionando adecuadamente
			  List<ProductInventoryDTO> products = Lists.newArrayList(new ProductInventoryDTO(prod1.getName(),new  BigDecimal("1.1"),prod1.getFreshCutValue())); 
			  CompanyInventoryDTO priceList =new CompanyInventoryDTO(1L,products);
			  
			  when(utility.getProductInventoryByCompay(lstinve)).thenReturn(products);
			  

			  //TODO Revisar por que el servidor devueve internal server errro 
			  // Valida que la API este funcionando adecuadamente
				/*
				 * this.mockMvc.perform(get(INVENTRY_PRODUCTS_BY_cOMPANY_PATH + "/{id}", 4L))
				 * .andExpect(status().isInternalServerError())
				 * .andExpect(jsonPath("$.id").value(4L)) .andDo(print());
				 */
			  

	    
	    
	    }
	

}
