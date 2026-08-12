package co.micia.projects.restapi.pricelist.dalbusiness.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import co.micia.projects.restapi.pricelist.daljpa.model.TBLBoxTypePT;

class ProductPriceUtilityTest {

    private final ProductPriceUtility utility = new ProductPriceUtility();

    @ParameterizedTest(name = "Freight: basePrice={0}, cubes={1}, pack={2}, box={3}x{4}x{5}, freshCut={6} => {7}")
    @MethodSource("freightTestCases")
    @DisplayName("calculateFinalFreight - formula verification")
    void calculateFinalFreight_formulaMatches(BigDecimal basePrice, BigDecimal cubesPerCarrier, Integer pack,
            BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal freshCutValue, BigDecimal expected) {
        TBLBoxTypePT boxType = new TBLBoxTypePT(1L, "TEST", width, height, length);

        BigDecimal result = utility.calculateFinalFreightForTest(boxType, cubesPerCarrier, pack, freshCutValue);

        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "Markdown: price={0}, markdown={1}% => {2}")
    @CsvSource({
        "10.00, 10, 9.00",
        "20.00, 15, 17.00",
        "5.00, 0, 5.00",
        "1.10, 10, 0.99",
        "2.00, 10, 1.80",
        "100.00, 50, 50.00"
    })
    @DisplayName("applyMarkdown - percentage calculation")
    void applyMarkdown_calculatesCorrectly(BigDecimal price, int markdown, BigDecimal expected) {
        BigDecimal result = utility.applyMarkdownForTest(price, markdown);

        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "Code: name={0} => {1}")
    @CsvSource({
        "Red Roses 23cm, R0m",
        "IL Hydrangea Blue, I0e",
        "Black Girasol 17Inch, B0h",
        "White pom 3Inch, W0h",
        "Achillea Blue 23cmm, A0m"
    })
    @DisplayName("ensureNoDuplicateName - format verification")
    void ensureNoDuplicateName_formatMatches(String productName, String expected) {
        String result = utility.ensureNoDuplicateNameForTest(productName);

        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "pack={0}")
    @CsvSource({"0", "-1"})
    @DisplayName("calculateFinalFreight - throws on invalid pack")
    void calculateFinalFreight_throwsOnInvalidPack(Integer invalidPack) {
        TBLBoxTypePT boxType = new TBLBoxTypePT(1L, "TEST", BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN);

        assertThrows(IllegalArgumentException.class, () ->
            utility.calculateFinalFreightForTest(boxType, BigDecimal.ONE, invalidPack, BigDecimal.TEN));
    }

    static Stream<Arguments> freightTestCases() {
        return Stream.of(
            Arguments.of(new BigDecimal("1.1"), new BigDecimal("16.3"), 1,
                    new BigDecimal("15.2"), new BigDecimal("18.9"), new BigDecimal("17.4"),
                    new BigDecimal("10"), new BigDecimal("4.72")),

            Arguments.of(new BigDecimal("2.0"), new BigDecimal("18.0"), 2,
                    new BigDecimal("10.0"), new BigDecimal("10.0"), new BigDecimal("10.0"),
                    new BigDecimal("20"), new BigDecimal("1.04")),

            Arguments.of(new BigDecimal("1.7"), new BigDecimal("13.6"), 1,
                    new BigDecimal("11.6"), new BigDecimal("16.2"), new BigDecimal("12.7"),
                    new BigDecimal("15"), new BigDecimal("2.82"))
        );
    }
}