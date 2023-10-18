package co.micia.projects.restapi.pricelist.daljpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLinventoryPT;
public interface InventoryRepository extends JpaRepository<TBLinventoryPT, Long> {
	List<TBLinventoryPT> findAllByCompanyId(Long companyId); 	
}
