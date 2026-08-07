package co.micia.projects.restapi.pricelist.dalbusiness.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.micia.projects.restapi.pricelist.daljpa.model.TBLBoxTypePT;
import co.micia.projects.restapi.pricelist.daljpa.model.TBLinventoryPT;
import co.micia.projects.restapi.pricelist.integrations.CodigoProductoDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductCompanyDTO;
import co.micia.projects.restapi.pricelist.integrations.ProductInventoryDTO;

@Component
public class ProductPriceUtility {
    private static final BigDecimal CUBIC_INCHES_PER_CUBIC_FOOT = BigDecimal.valueOf(1728);
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    private static final int MONEY_SCALE = 2;
    private static final int CALCULATION_SCALE = 10;

    public List<ProductInventoryDTO> getProductInventoryByCompay(List<TBLinventoryPT> inventory) {
        List<ProductInventoryDTO> products = new ArrayList<>();
        for (TBLinventoryPT item : inventory) {
            products.add(new ProductInventoryDTO(
                    item.getProduct().getName(),
                    item.getBasePrice(),
                    calculateFinalFreight(item.getBoxType(), item.getCubesPerCarrier(), item.getPack(),
                            item.getProduct().getFreshCutValue())));
        }
        return products;
    }

    private BigDecimal calculateFinalFreight(TBLBoxTypePT boxType, BigDecimal cubesPerCarrier, Integer pack,
            BigDecimal freshCutValue) {
        if (pack == null || pack <= 0) {
            throw new IllegalArgumentException("Inventory pack must be greater than zero");
        }

        BigDecimal cubicFeetPerBox = boxType.getWidth()
                .multiply(boxType.getHeight())
                .multiply(boxType.getLength())
                .divide(CUBIC_INCHES_PER_CUBIC_FOOT, CALCULATION_SCALE, RoundingMode.HALF_UP);
        BigDecimal outboundFreight = cubicFeetPerBox
                .multiply(cubesPerCarrier)
                .divide(BigDecimal.valueOf(pack), CALCULATION_SCALE, RoundingMode.HALF_UP);

        return outboundFreight
                .multiply(freshCutValue)
                .divide(ONE_HUNDRED, CALCULATION_SCALE, RoundingMode.HALF_UP)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

    public List<ProductCompanyDTO> getProductInventoryByCustomer(List<TBLinventoryPT> inventory, BigDecimal markdown) {
        List<ProductCompanyDTO> products = new ArrayList<>();
        BigDecimal markdownRate = markdown.divide(ONE_HUNDRED, CALCULATION_SCALE, RoundingMode.HALF_UP);

        for (TBLinventoryPT item : inventory) {
            BigDecimal price = item.getBasePrice()
                    .multiply(BigDecimal.ONE.subtract(markdownRate))
                    .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
            products.add(new ProductCompanyDTO(item.getProduct().getName(), item.getCompany().getName(), price));
        }
        return products;
    }

    public List<CodigoProductoDTO> getInventaryProductCode(List<TBLinventoryPT> inventory) {
        List<CodigoProductoDTO> products = new ArrayList<>();
        for (TBLinventoryPT item : inventory) {
            String productName = item.getProduct().getName();
            products.add(new CodigoProductoDTO(productName, ensureNoDuplicateName(productName)));
        }
        return products;
    }

    private String ensureNoDuplicateName(String name) {
        return name.charAt(0) + "0" + name.charAt(name.length() - 1);
    }
}
