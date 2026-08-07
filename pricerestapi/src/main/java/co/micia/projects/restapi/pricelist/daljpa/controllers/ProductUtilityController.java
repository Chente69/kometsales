package co.micia.projects.restapi.pricelist.daljpa.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.micia.projects.restapi.pricelist.dalbusiness.services.ProductUtilityService;
import co.micia.projects.restapi.pricelist.integrations.CodigoProductoDTO;
import co.micia.projects.restapi.pricelist.integrations.CompanyInventoryDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductCompanyDTO;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductUtilityController {
    private static final Logger log = LoggerFactory.getLogger(ProductUtilityController.class);

    private final ProductUtilityService priceService;

    public ProductUtilityController(ProductUtilityService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/products/companies/{id}")
    public ResponseEntity<CompanyInventoryDTO> getAllPricesbyCompanyId(@PathVariable("id") long id) {
        CompanyInventoryDTO result = priceService.findAllProductsByCompanyId(id);
        if (result.getProducts().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        log.info("Found {} products for company {}", result.getProducts().size(), id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/products/companies/{id}/codes")
    public ResponseEntity<List<CodigoProductoDTO>> getAllProductCodesbyCompanyId(@PathVariable("id") long id) {
        List<CodigoProductoDTO> result = priceService.findAllProductsCode(id);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        log.info("Found {} product codes for company {}", result.size(), id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/products/customers/{id}")
    public ResponseEntity<List<ProductCompanyDTO>> getAllPricesbyCustomerId(@PathVariable("id") long id) {
        List<ProductCompanyDTO> result = priceService.findAllProductsPrice(id);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        log.info("Found {} products for customer {}", result.size(), id);
        return ResponseEntity.ok(result);
    }
}
