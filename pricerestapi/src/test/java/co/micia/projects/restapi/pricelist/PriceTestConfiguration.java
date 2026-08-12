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
			// Additional products for test data (IDs will be auto-generated)
			TBLProductPT prod6 = prodRepo.save(new TBLProductPT(null, "Test Rose Bouquet", new BigDecimal("20")));
			TBLProductPT prod7 = prodRepo.save(new TBLProductPT(null, "Test Lily Bundle", new BigDecimal("25")));
			prodRepo.save(new TBLProductPT(null, "Test Orchid Stem", new BigDecimal("30")));

			// Additional companies
			TBLCompanyPT comp6 = compRepo.save(new TBLCompanyPT(null, "TestFlowers"));
			TBLCompanyPT comp7 = compRepo.save(new TBLCompanyPT(null, "GreenValley"));

			// Additional box types
			TBLBoxTypePT box6 = boxTypeRepo.save(new TBLBoxTypePT(null, "6666", new BigDecimal("10.0"), new BigDecimal("10.0"), new BigDecimal("10.0")));
			TBLBoxTypePT box7 = boxTypeRepo.save(new TBLBoxTypePT(null, "7777", new BigDecimal("8.5"), new BigDecimal("9.5"), new BigDecimal("7.0")));

			// Additional inventories linking new entities (use returned entities with generated IDs)
			inveRepo.save(new TBLinventoryPT(null, new BigDecimal("18.0"), 2, new BigDecimal("2.0"), box6, prod6, comp6));
			inveRepo.save(new TBLinventoryPT(null, new BigDecimal("14.5"), 1, new BigDecimal("2.5"), box7, prod7, comp7));

			// Additional customers
			custRepo.save(new TBLCustomerPT(null, "TestCustomer1", new BigDecimal("10")));
			custRepo.save(new TBLCustomerPT(null, "TestCustomer2", new BigDecimal("15")));
		};
	}
}
