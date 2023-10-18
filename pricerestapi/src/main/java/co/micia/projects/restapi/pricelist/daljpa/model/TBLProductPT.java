package co.micia.projects.restapi.pricelist.daljpa.model;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblproductpt")
public class TBLProductPT {
	 /*
	  * Se genera el identificador unico de la clase de manera automática
	  */
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "ID")
	  private Long id;
	  
	  @Column(name = "NAME")
	  private String name;
	  
	  @Column(name = "FRESHCUTVALUE")
	  private BigDecimal freshCutValue;

	public TBLProductPT() {
	}



	public TBLProductPT(Long id, String name, BigDecimal freshCutValue) {
		this.id = id;
		this.name = name;
		this.freshCutValue = freshCutValue;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getFreshCutValue() {
		return freshCutValue;
	}

	public void setFreshCutValue(BigDecimal freshCutValue) {
		this.freshCutValue = freshCutValue;
	}

	@Override
	public String toString() {
		return "TBLProductPT [id=" + id + ", name=" + name + ", freshCutValue=" + freshCutValue + "]";
	}


	  
	
	  
}
