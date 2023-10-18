package co.micia.projects.restapi.pricelist.daljpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.micia.projects.restapi.pricelist.daljpa.model.TBLProductPT;

public interface ProductRepository extends JpaRepository<TBLProductPT, Long> {

}
