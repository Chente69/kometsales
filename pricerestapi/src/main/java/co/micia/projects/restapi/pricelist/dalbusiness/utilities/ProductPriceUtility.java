package co.micia.projects.restapi.pricelist.dalbusiness.utilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import co.micia.projects.restapi.pricelist.dalbusiness.services.ProductUtilityServiceImpl;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLBoxTypePT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLinventoryPT;
import co.micia.projects.restapi.pricelist.integrations.CodigoProductoDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductCompanyDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductInventoryDTO;
/**
 * Cumponente de Producto para calcular el precio
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */

@Component
public class ProductPriceUtility {
	private static final Logger log = LoggerFactory.getLogger(ProductPriceUtility.class);
	
	/*
	 * Metodod que consultaa los productos de inventario de una compañia
	 * y calcula el finalFreight
	 */
	public List<ProductInventoryDTO> getProductInventoryByCompay(List<TBLinventoryPT> lstinve){
		List<ProductInventoryDTO> products = new ArrayList<ProductInventoryDTO>();
		
		for (TBLinventoryPT item : lstinve) {
			String prodName = item.getProduct().getName();
			BigDecimal freshCutVal = item.getProduct().getFreshCutValue();
			log.info("Calular para el producto : " + prodName + ", el freshCutVal: " + freshCutVal);
			products.add(new ProductInventoryDTO(prodName,item.getBasePrice(),calFinalFreight(item.getBoxType(),item.getCubesPerCarrier(),item.getPack(),freshCutVal)));
		}
		
		return  products;
	}
	
	private BigDecimal calFinalFreight(TBLBoxTypePT boxType,BigDecimal cubesPerCarrier,Integer pack,BigDecimal freshCutValue) {
		BigDecimal finalFreing = new BigDecimal(0);
		BigDecimal cubesPerBox = (boxType.getWidth().multiply( boxType.getHeight() ).multiply(boxType.getLength())).divide(new BigDecimal(1728));
		BigDecimal outboundFreight = (cubesPerBox.multiply(cubesPerCarrier)).divide(new BigDecimal(pack));
		finalFreing = (outboundFreight.multiply(freshCutValue)).divide(new BigDecimal(100));
		return finalFreing.setScale(2);
	}
	
	/*
	 * Metodod que consulta los productos de inventario de una customer
	 * y calcula el precio
	 */
	public List<ProductCompanyDTO> getProductInventoryByCustomer(List<TBLinventoryPT> lstinve ,BigDecimal markdown){
		List<ProductCompanyDTO> products = new ArrayList<ProductCompanyDTO>();
		for (TBLinventoryPT item : lstinve) {
			String prodName = item.getProduct().getName();
			String companyName = item.getCompany().getName();
			BigDecimal priceAux = item.getBasePrice().multiply(markdown.divide(new BigDecimal(100)));
			BigDecimal price = item.getBasePrice().subtract(priceAux);
			log.info("Calular para el producto : " + prodName + "de la compañia: " + companyName + ", el precio: " + price);
			ProductCompanyDTO prodItem = new ProductCompanyDTO(prodName,companyName,price.setScale(2));
			products.add(prodItem);
		}
		return products;
	}
	
	/*
	 * Metodod que consulta los productos de inventario de una compañia
	 * y calcula los codigos de los productos
	 */
	public List<CodigoProductoDTO> getInventaryProductCode(List<TBLinventoryPT> lstinve){
		List<CodigoProductoDTO> products = new ArrayList<CodigoProductoDTO>();
		for (TBLinventoryPT item : lstinve) {
			String prodName = item.getProduct().getName();
			String companyName = item.getCompany().getName();
			String codeProduct = ensureNoDuplicateName(prodName);
			log.info("Calular para el producto : " + prodName + "de la compañia: " + companyName + ", el código: " + codeProduct);
			CodigoProductoDTO prodItem =new CodigoProductoDTO(prodName,codeProduct);
			products.add(prodItem);
		}
		return products;
	}
	
	private String ensureNoDuplicateName(String name) {
		  String str = "";
		  char[]  chrLst = name.toCharArray();
		  Map<Character,Integer> charMap = new HashMap<Character,Integer>();
		  for(Character ch:chrLst) {
			  if(charMap.containsKey(ch)) {
				  charMap.put(ch, charMap.get(ch)+1);
			  } else {
				  charMap.put(ch,1);
			  }
		  }
		  
			Set<Map.Entry<Character,Integer>> entrySet = charMap.entrySet();
			 int nroDistintChars = 0; // Número distinto de caracteres entre la primera y ultima letra
			 Character chGuion = '-';
			 int postLastChar = chrLst.length-1;
			 str = ""+chrLst[0] + nroDistintChars ;
			 for(Map.Entry<Character,Integer> entry:entrySet) {
				 int representacion = (int) entry.getKey();
				 if(entry.getValue()==1 &&  (nroDistintChars>1 && nroDistintChars<= postLastChar) &&(chrLst[0] != entry.getKey() && chrLst[postLastChar] != entry.getKey())) {// Caracter distinto distionto del primero y ultimo de la palabra
					 if(chGuion != entry.getKey() && (97<=representacion && representacion<=122)  && (65<=representacion && representacion<=90)) // Diferete de guion y es u n caracter no especial
						 nroDistintChars++; // Contar caracteres diferentes del primero y ultimo 
				 }
				 if( (nroDistintChars>1 && nroDistintChars<= postLastChar) &&(chrLst[0] != entry.getKey() && chrLst[postLastChar] != entry.getKey())) {// Caracter distinto distionto del primero y ultimo de la palabra
					 if(chGuion == entry.getKey() && (! (97<=representacion && representacion<=122)  && (65<=representacion && representacion<=90))) // Diferete de guion y es un caracterespecial
						 str += ""+ entry.getKey() ; // concatenar caracter especial 
				 }
				
			 }
				 
			str += "" +chrLst[postLastChar];
			 
				 
			 
			 return str;
			 
		}
}
