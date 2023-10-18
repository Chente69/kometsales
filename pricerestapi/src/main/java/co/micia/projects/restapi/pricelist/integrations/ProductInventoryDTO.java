package co.micia.projects.restapi.pricelist.integrations;
import java.math.BigDecimal;

/**
 * DTO empleado para devolver los productos deuan compañia
 * calculando el finalFreight
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */

public class ProductInventoryDTO {

	private String productName;
	private BigDecimal basePrice;
	private BigDecimal finalFreight;

	public ProductInventoryDTO() {
	}

	public ProductInventoryDTO(String productName, BigDecimal basePrice, BigDecimal finalFreight) {
		this.productName = productName;
		this.basePrice = basePrice;
		this.finalFreight = finalFreight;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getFinalFreight() {
		return finalFreight;
	}

	public void setFinalFreight(BigDecimal finalFreight) {
		this.finalFreight = finalFreight;
	}

	@Override
	public String toString() {
		return "ProductInventoryDTO [productName=" + productName + ", basePrice=" + basePrice + ", finalFreight="
				+ finalFreight + "]";
	}

  
	
	  
}
