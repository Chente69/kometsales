package co.micia.projects.restapi.pricelist.daljpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLCompanyPT;

public interface CompanyRepository extends JpaRepository<TBLCompanyPT, Long> {

}
