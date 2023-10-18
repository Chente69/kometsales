package co.micia.projects.restapi.pricelist.daljpa.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tblinventorypt")
public class TBLinventoryPT {
	 /*
	  * Se genera el identificador unico de la clase de manera automática
	  */
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "ID")
	  private Long id;
	  
	  @Column(name = "CUBESPERCARRIER")
	  private BigDecimal cubesPerCarrier;
	  
	  @Column(name = "PACK")
	  private Integer pack;
	  
	  @Column(name = "BASEPRICE")
	  private BigDecimal basePrice;
	  
	  @ManyToOne(fetch = FetchType.EAGER, optional = false)
	  @JoinColumn(name = "boxTypeId", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
	  @JsonIgnore
	  private TBLBoxTypePT boxType;
	  
	  @ManyToOne(fetch = FetchType.EAGER, optional = false)
	  @JoinColumn(name = "productId", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
	  @JsonIgnore
	  private TBLProductPT product;
	  
	  @ManyToOne(fetch = FetchType.EAGER, optional = false)
	  @JoinColumn(name = "companyId", nullable = false)
	  @OnDelete(action = OnDeleteAction.CASCADE)
	  @JsonIgnore
	  private TBLCompanyPT company;
	  

	   
	public TBLinventoryPT() {
	}



	public TBLinventoryPT(Long id, BigDecimal cubesPerCarrier, Integer pack, BigDecimal basePrice, TBLBoxTypePT boxType,
			TBLProductPT product, TBLCompanyPT company) {
		this.id = id;
		this.cubesPerCarrier = cubesPerCarrier;
		this.pack = pack;
		this.basePrice = basePrice;
		this.boxType = boxType;
		this.product = product;
		this.company = company;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public BigDecimal getCubesPerCarrier() {
		return cubesPerCarrier;
	}



	public void setCubesPerCarrier(BigDecimal cubesPerCarrier) {
		this.cubesPerCarrier = cubesPerCarrier;
	}



	public Integer getPack() {
		return pack;
	}



	public void setPack(Integer pack) {
		this.pack = pack;
	}



	public BigDecimal getBasePrice() {
		return basePrice;
	}



	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}



	public TBLBoxTypePT getBoxType() {
		return boxType;
	}



	public void setBoxType(TBLBoxTypePT boxType) {
		this.boxType = boxType;
	}



	public TBLProductPT getProduct() {
		return product;
	}



	public void setProduct(TBLProductPT product) {
		this.product = product;
	}



	public TBLCompanyPT getCompany() {
		return company;
	}



	public void setCompany(TBLCompanyPT company) {
		this.company = company;
	}



	@Override
	public String toString() {
		return "TBLinventoryPT [id=" + id + ", cubesPerCarrier=" + cubesPerCarrier + ", pack=" + pack + ", basePrice="
				+ basePrice + ", boxType=" + boxType + ", product=" + product + ", company=" + company + "]";
	}


	
	  
}
