package co.micia.projects.restapi.pricelist.daljpa.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblcustomerpt")
public class TBLCustomerPT {
	 /*
	  * Se genera el identificador unico de la clase de manera automática
	  */
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "ID")
	  private Long id;
	  
	  @Column(name = "NAME")
	  private String name;
	  
	  @Column(name = "MARKDOWN")
	  private BigDecimal markdown;

	public TBLCustomerPT() {
	}

	public TBLCustomerPT(Long id, String name, BigDecimal markdown) {
		this.id = id;
		this.name = name;
		this.markdown = markdown;
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

	public BigDecimal getMarkdown() {
		return markdown;
	}

	public void setMarkdown(BigDecimal markdown) {
		this.markdown = markdown;
	}
	  

	  
	  
	  
}
