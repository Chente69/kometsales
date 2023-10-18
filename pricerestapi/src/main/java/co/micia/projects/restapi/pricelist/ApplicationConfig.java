package co.micia.projects.restapi.pricelist;


import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



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


@Configuration
public class ApplicationConfig {
	 
	 private static final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);

	  @Bean
	  CommandLineRunner initDatabase(ProductRepository prodRepo, CompanyRepository compRepo, BoxTypeResository boxTypeRepo, InventoryRepository inveRepo, CustomerRepository  custRepo) {
        /*
         * Inicializa la Base de Datos, insertando Productos, Compañias, Tipos de Empaques, 
         * requerido para integridad de las pruebas y para poder crear Precios
         * ya que tiene referencia a un Brand en la entidad de persistencia
         */
	    return args -> {
	    	Optional<TBLProductPT> prodEntityOpt;
	    	Optional<TBLCompanyPT> compEntityOpt;
	    	Optional<TBLBoxTypePT> boxTypeEntityOpt;
	    	TBLProductPT prodEntity = null;
	    	TBLCompanyPT compEntity = null;
	    	TBLBoxTypePT boxTypeEntity = null;
	    	
	    	Boolean goAhead = false;

	    	
	    	log.info("Preloading los datos de los productos a la base de datos");
		      log.info("Preloading Products: Red Roses 23cm " + prodRepo.save(new TBLProductPT(1L, "Red Roses 23cm",new BigDecimal("10"))));
		      log.info("Preloading Products: IL Hydrangea Blue" + prodRepo.save(new TBLProductPT(2L, "IL Hydrangea Blue",new BigDecimal("15"))));
		      log.info("Preloading Products: Black Girasol 17Inch " + prodRepo.save(new TBLProductPT(3L, "Black Girasol 17Inch",new BigDecimal("45"))));
		      log.info("Preloading Products: White pom 3Inch " + prodRepo.save(new TBLProductPT(4L, "White pom 3Inch",new BigDecimal("12"))));
		      log.info("Preloading Products: Achillea Blue 23cm " + prodRepo.save(new TBLProductPT(5L, "Achillea Blue 23cmm",new BigDecimal("57"))));
		      
		      log.info("Preloading Company: BellaFlowers" + compRepo.save(new TBLCompanyPT(1L, "BellaFlowers")));
		      log.info("Preloading Company: SuperFlowers" + compRepo.save(new TBLCompanyPT(2L, "SuperFlowers")));
		      log.info("Preloading Company: BeautiFlowers" + compRepo.save(new TBLCompanyPT(3L, "BeautiFlowers")));
		      log.info("Preloading Company: TheFlowers" + compRepo.save(new TBLCompanyPT(4L, "TheFlowers")));
		      log.info("Preloading Company: CandyFlowers" + compRepo.save(new TBLCompanyPT(5L, "CandyFlowers")));
		      
		      log.info("Preloading BoxType: 1111" + boxTypeRepo.save(new TBLBoxTypePT(1L, "1111",new BigDecimal("12.1"),new BigDecimal("12.8"),new BigDecimal("11.2"))));
		      log.info("Preloading BoxType: 2222" + boxTypeRepo.save(new TBLBoxTypePT(2L, "2222",new BigDecimal("11.6"),new BigDecimal("16.2"),new BigDecimal("12.7"))));
		      log.info("Preloading BoxType: 3333" + boxTypeRepo.save(new TBLBoxTypePT(3L, "3333",new BigDecimal("15.2"),new BigDecimal("18.9"),new BigDecimal("17.4"))));
		      log.info("Preloading BoxType: 4444" + boxTypeRepo.save(new TBLBoxTypePT(4L, "4444",new BigDecimal("13.9"),new BigDecimal("15.5"),new BigDecimal("10.8"))));
		      log.info("Preloading BoxType: 5555" + boxTypeRepo.save(new TBLBoxTypePT(5L, "5555",new BigDecimal("14.4"),new BigDecimal("12.7"),new BigDecimal("15.5"))));
		      
		      goAhead = true;
		      prodEntityOpt = prodRepo.findById(1L);
		      if(!prodEntityOpt.isPresent()) goAhead = false;
		      else 
		    	  prodEntity =  prodEntityOpt.get();
		      
		      compEntityOpt = compRepo.findById(4L);
		      if(!compEntityOpt.isPresent()) goAhead = false;
		      else
		    	  compEntity = compEntityOpt.get();
		      		      
		      boxTypeEntityOpt = boxTypeRepo.findById(3L);
		      if(!boxTypeEntityOpt.isPresent()) goAhead = false;
		      else
		    	  boxTypeEntity = boxTypeEntityOpt.get();
		      // Inserta los valores VALUES (1, 1, 4, 3, 16.3, 1, 1.1)
		      log.info("Preloading Inventory: VALUES (1, 1, 4, 3, 16.3, 1, 1.1)" + inveRepo.save(new TBLinventoryPT(1L,new BigDecimal("16.3"),1,new BigDecimal("1.1"), boxTypeEntity,prodEntity, compEntity)));
              
		      goAhead = true;
		      prodEntityOpt = prodRepo.findById(2L);
		      if(!prodEntityOpt.isPresent()) goAhead = false;
		      else 
		    	  prodEntity =  prodEntityOpt.get();
		      
		      compEntityOpt = compRepo.findById(1L);
		      if(!compEntityOpt.isPresent()) goAhead = false;
		      else
		    	  compEntity = compEntityOpt.get();
		      		      
		      boxTypeEntityOpt = boxTypeRepo.findById(2L);
		      if(!boxTypeEntityOpt.isPresent()) goAhead = false;
		      else
		    	  boxTypeEntity = boxTypeEntityOpt.get();
		      // Inserta los valores (2, 2, 1, 2, 13.6, 1, 1.7),
		      log.info("Preloading Inventory: (2, 2, 1, 2, 13.6, 1, 1.7)" + inveRepo.save(new TBLinventoryPT(2L,new BigDecimal("13.6"),1,new BigDecimal("1.7"), boxTypeEntity,prodEntity, compEntity)));
			     
		      goAhead = true;
		      prodEntityOpt = prodRepo.findById(3L);
		      if(!prodEntityOpt.isPresent()) goAhead = false;
		      else 
		    	  prodEntity =  prodEntityOpt.get();
		      
		      compEntityOpt = compRepo.findById(5L);
		      if(!compEntityOpt.isPresent()) goAhead = false;
		      else
		    	  compEntity = compEntityOpt.get();
		      		      
		      boxTypeEntityOpt = boxTypeRepo.findById(5L);
		      if(!boxTypeEntityOpt.isPresent()) goAhead = false;
		      else
		    	  boxTypeEntity = boxTypeEntityOpt.get();
		      // Inserta los (3, 3, 5, 5, 11.6, 1, 1.4),
		      log.info("Preloading Inventory: (3, 3, 5, 5, 11.6, 1, 1.4)" + inveRepo.save(new TBLinventoryPT(3L,new BigDecimal("11.6"),1,new BigDecimal("1.4"), boxTypeEntity,prodEntity, compEntity)));
		      
		      goAhead = true;
		      prodEntityOpt = prodRepo.findById(4L);
		      if(!prodEntityOpt.isPresent()) goAhead = false;
		      else 
		    	  prodEntity =  prodEntityOpt.get();
		      
		      compEntityOpt = compRepo.findById(2L);
		      if(!compEntityOpt.isPresent()) goAhead = false;
		      else
		    	  compEntity = compEntityOpt.get();
		      		      
		      boxTypeEntityOpt = boxTypeRepo.findById(1L);
		      if(!boxTypeEntityOpt.isPresent()) goAhead = false;
		      else
		    	  boxTypeEntity = boxTypeEntityOpt.get();
		      // Inserta los (4, 4, 2, 1, 15.2, 1, 1.3),
		      log.info("Preloading Inventory: (4, 4, 2, 1, 15.2, 1, 1.3)" + inveRepo.save(new TBLinventoryPT(4L,new BigDecimal("15.2"),1,new BigDecimal("1.3"), boxTypeEntity,prodEntity, compEntity)));

		      goAhead = true;
		      prodEntityOpt = prodRepo.findById(5L);
		      if(!prodEntityOpt.isPresent()) goAhead = false;
		      else 
		    	  prodEntity =  prodEntityOpt.get();
		      
		      compEntityOpt = compRepo.findById(3L);
		      if(!compEntityOpt.isPresent()) goAhead = false;
		      else
		    	  compEntity = compEntityOpt.get();
		      		      
		      boxTypeEntityOpt = boxTypeRepo.findById(4L);
		      if(!boxTypeEntityOpt.isPresent()) goAhead = false;
		      else
		    	  boxTypeEntity = boxTypeEntityOpt.get();
		      // Inserta los (5, 5, 3, 4, 12.3, 1, 1.2),
		      log.info("Preloading Inventory: (5, 5, 3, 4, 12.3, 1, 1.2)" + inveRepo.save(new TBLinventoryPT(5L,new BigDecimal("12.3"),1,new BigDecimal("1.2"), boxTypeEntity,prodEntity, compEntity)));
		      
		      
		      log.info("Preloading Customer: Luis " + custRepo.save(new TBLCustomerPT(1L, "Luis",new BigDecimal("5"))));
		      log.info("Preloading Customer: Luis " + custRepo.save(new TBLCustomerPT(2L, "Daniel",new BigDecimal("2"))));
		      log.info("Preloading Customer: Luis " + custRepo.save(new TBLCustomerPT(3L, "William",new BigDecimal("3"))));
		      log.info("Preloading Customer: Luis " + custRepo.save(new TBLCustomerPT(4L, "Johan",new BigDecimal("1"))));
		      log.info("Preloading Customer: Luis " + custRepo.save(new TBLCustomerPT(5L, "Jessica",new BigDecimal("4"))));

	    };
	  }
    




}
