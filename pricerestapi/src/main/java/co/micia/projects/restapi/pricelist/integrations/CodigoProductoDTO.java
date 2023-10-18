package co.micia.projects.restapi.pricelist.integrations;
/**
 * DTO empleado para devolver los productos de compañia
 * calculando el código de producto
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */

public class CodigoProductoDTO {
	private String productName;
	private String productCode;
	
	public CodigoProductoDTO() {
	}

	public CodigoProductoDTO(String productName, String productCode) {
		this.productName = productName;
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	

}
