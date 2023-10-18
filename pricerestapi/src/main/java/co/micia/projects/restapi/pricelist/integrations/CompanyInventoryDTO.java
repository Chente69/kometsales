package co.micia.projects.restapi.pricelist.integrations;

import java.util.List;
/**
 * DTO empleado para devolver los productos de una compañia
 * realizando calccualo para obtener el finalFreing del producto
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
public class CompanyInventoryDTO {
	 private  Long companyId;
	 private List<ProductInventoryDTO> products;
	public CompanyInventoryDTO() {
	
	}
	public CompanyInventoryDTO(Long companyId, List<ProductInventoryDTO> products) {
		this.companyId = companyId;
		this.products = products;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public List<ProductInventoryDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductInventoryDTO> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		return "CompanyInventoryDTO [companyId=" + companyId + ", products=" + products + "]";
	}
 
	
 
 
}
