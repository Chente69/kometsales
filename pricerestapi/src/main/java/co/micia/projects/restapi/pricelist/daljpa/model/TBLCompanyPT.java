package co.micia.projects.restapi.pricelist.daljpa.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblcompanypt")
public class TBLCompanyPT {
	 /*
	  * Se genera el identificador unico de la clase de manera automática
	  */
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "ID")
	  private Long id;
	  
	  @Column(name = "NAME")
	  private String name;

	public TBLCompanyPT() {
	}



	public TBLCompanyPT(Long id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "TBLCompanyPT [id=" + id + ", name=" + name + "]";
	}


	
	
	  
	  
}
