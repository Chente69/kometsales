package co.micia.projects.restapi.pricelist.daljpa.repositories;
import  co.micia.projects.restapi.pricelist.daljpa.model.TBLCustomerPT;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<TBLCustomerPT, Long> {

}
