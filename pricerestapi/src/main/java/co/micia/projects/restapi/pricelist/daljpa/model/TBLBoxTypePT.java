package co.micia.projects.restapi.pricelist.daljpa.model;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblboxtypept")
public class TBLBoxTypePT {
	 /*
	  * Se genera el identificador unico de la clase de manera automática
	  */
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "ID")
	  private Long id;
	  
	  @Column(name = "CODE")
	  private String code;
	  
	  @Column(name = "WIDTH")
	  private BigDecimal width;
	  
	  @Column(name = "HEIGHT")
	  private BigDecimal height;
	  
	  @Column(name = "LENGTH")
	  private BigDecimal length;

	public TBLBoxTypePT() {
	}

	public TBLBoxTypePT(Long id, String code, BigDecimal width, BigDecimal height, BigDecimal length) {
		this.id = id;
		this.code = code;
		this.width = width;
		this.height = height;
		this.length = length;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "TBLboxtypePT [id=" + id + ", code=" + code + ", width=" + width + ", height=" + height + ", length="
				+ length + "]";
	}


	
	
	  
	  
}
