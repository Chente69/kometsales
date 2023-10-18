package co.micia.projects.restapi.pricelist.integrations;

import java.math.BigDecimal;
/**
 * DTO empleado para devolver los productos de un cliente dado
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
public class ProductCompanyDTO {
	private String productName;
	private String companyName;
	private BigDecimal price;
	
	public ProductCompanyDTO() {
	}

	public ProductCompanyDTO(String productName, String companyName, BigDecimal price) {
		this.productName = productName;
		this.companyName = companyName;
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductCompanyDTO [productName=" + productName + ", companyName=" + companyName + ", price=" + price
				+ "]";
	}

	
	


}
