package co.micia.projects.restapi.pricelist.daljpa.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.micia.projects.restapi.pricelist.dalbusiness.services.ProductUtilityService;
import co.micia.projects.restapi.pricelist.dalbusiness.services.ProductUtilityServiceImpl;
import co.micia.projects.restapi.pricelist.integrations.CodigoProductoDTO;
import co.micia.projects.restapi.pricelist.integrations.CompanyInventoryDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductCompanyDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductInventoryDTO;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductUtilityController {
	private static final Logger log = LoggerFactory.getLogger(ProductUtilityController.class);
	ProductUtilityService priceService;
	
	/*
	 * Injecta el servicio pricesService por constructor del controlador
	 */
	@Autowired
	public ProductUtilityController(ProductUtilityService priceService) {
		this.priceService = priceService;
	}
	
    /**
     * This method corresponds to HTTP GET method, that Queries all the Price entities stored in H2 pricedb Data Base
     * and calculate Product finalFreight
     * @return ResponseEntity<List<Price>> Price Entity Collection
     */
	  @GetMapping("/products/companies/{id}")
	  public ResponseEntity<CompanyInventoryDTO> getAllPricesbyCompanyId(@PathVariable("id") long id) {
	    try {
	    	CompanyInventoryDTO pricesResult = new CompanyInventoryDTO();

	    	pricesResult =priceService.findAllProductsByCompanyId(id);
	    	List<ProductInventoryDTO> products = pricesResult.getProducts();
	    	products.stream().map(s -> s).collect(Collectors.toList()).forEach(e -> log.info("Product : "+ e.toString()));
	    	
	        if (pricesResult.getProducts().isEmpty()) {
	          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }

	        return new ResponseEntity<>(pricesResult, HttpStatus.OK);
	     } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	     }	  
	  }	
	
	   /**
	     * This method corresponds to HTTP GET method, that Queries all the Price entities stored in H2 pricedb Data Base
	     * and calculate the product code
	     * 
	     * @return ResponseEntity<List<Price>> Price Entity Collection
	     */
		  @GetMapping("/products/companies/{id}/codes")
		  public ResponseEntity<List<CodigoProductoDTO>> getAllProductCodesbyCompanyId(@PathVariable("id") long id) {
		    try {
		    	List<CodigoProductoDTO> pricesResult = new ArrayList<CodigoProductoDTO> ();
		    	pricesResult =priceService.findAllProductsCode(id);

		    	pricesResult.stream().map(s -> s).collect(Collectors.toList()).forEach(e -> log.info("Product : "+ e.toString()));
		    	
		        if (pricesResult.isEmpty()) {
		          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		        }

		        return new ResponseEntity<>(pricesResult, HttpStatus.OK);
		     } catch (Exception e) {
		        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		     }	  
		  }	
	   /**
	     * This method corresponds to HTTP GET method, that Queries all the Price entities stored in H2 pricedb Data Base
	     * and calculate prices
	     * @return ResponseEntity<List<Price>> Price Entity Collection
	     */
		  @GetMapping("/products/customers/{id}")
		  public ResponseEntity<List<ProductCompanyDTO>> getAllPricesbyCustomerId(@PathVariable("id") long id) {
		    try {
		    	List<ProductCompanyDTO> pricesResult = new ArrayList<ProductCompanyDTO> ();


		    	pricesResult =priceService.findAllProductsPrice(id);

		    	pricesResult.stream().map(s -> s).collect(Collectors.toList()).forEach(e -> log.info("Product : "+ e.toString()));
		        if (pricesResult.isEmpty()) {
		          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		        }

		        return new ResponseEntity<>(pricesResult, HttpStatus.OK);
		     } catch (Exception e) {
		        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		     }	  
		  }

}
