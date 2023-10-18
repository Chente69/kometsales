package co.micia.projects.restapi.pricelist.dalbusiness.services;


import java.util.List;

import co.micia.projects.restapi.pricelist.integrations.CodigoProductoDTO;
import co.micia.projects.restapi.pricelist.integrations.CompanyInventoryDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductCompanyDTO;

public interface ProductUtilityService {
	CompanyInventoryDTO findAllProductsByCompanyId(Long companyId);
	List<ProductCompanyDTO> findAllProductsPrice(Long customerId);
	List<CodigoProductoDTO> findAllProductsCode(Long companyId);

}
