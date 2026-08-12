package co.micia.projects.restapi.pricelist;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import co.micia.projects.restapi.pricelist.daljpa.repositories.ProductRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.CompanyRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.BoxTypeResository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.InventoryRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.CustomerRepository;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLProductPT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLCompanyPT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLBoxTypePT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLinventoryPT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLCustomerPT;


@TestConfiguration
public class PriceTestConfiguration {

	@Bean
	CommandLineRunner initTestData(ProductRepository prodRepo, CompanyRepository compRepo,
			BoxTypeResository boxTypeRepo, InventoryRepository inveRepo, CustomerRepository custRepo) {
		return args -> {
			// Additional products for test data
			prodRepo.save(new TBLProductPT(6L, "Test Rose Bouquet", new BigDecimal("20")));
			prodRepo.save(new TBLProductPT(7L, "Test Lily Bundle", new BigDecimal("25")));
			prodRepo.save(new TBLProductPT(8L, "Test Orchid Stem", new BigDecimal("30")));

			// Additional companies
			compRepo.save(new TBLCompanyPT(6L, "TestFlowers"));
			compRepo.save(new TBLCompanyPT(7L, "GreenValley"));

			// Additional box types
			boxTypeRepo.save(new TBLBoxTypePT(6L, "6666", new BigDecimal("10.0"), new BigDecimal("10.0"), new BigDecimal("10.0")));
			boxTypeRepo.save(new TBLBoxTypePT(7L, "7777", new BigDecimal("8.5"), new BigDecimal("9.5"), new BigDecimal("7.0")));

			// Additional inventories linking new entities
			TBLProductPT prod6 = prodRepo.findById(6L).get();
			TBLCompanyPT comp6 = compRepo.findById(6L).get();
			TBLBoxTypePT box6 = boxTypeRepo.findById(6L).get();
			inveRepo.save(new TBLinventoryPT(6L, new BigDecimal("18.0"), 2, new BigDecimal("2.0"), box6, prod6, comp6));

			TBLProductPT prod7 = prodRepo.findById(7L).get();
			TBLCompanyPT comp7 = compRepo.findById(7L).get();
			TBLBoxTypePT box7 = boxTypeRepo.findById(7L).get();
			inveRepo.save(new TBLinventoryPT(7L, new BigDecimal("14.5"), 1, new BigDecimal("2.5"), box7, prod7, comp7));

			// Additional customers
			custRepo.save(new TBLCustomerPT(6L, "TestCustomer1", new BigDecimal("10")));
			custRepo.save(new TBLCustomerPT(7L, "TestCustomer2", new BigDecimal("15")));
		};
	}
}
