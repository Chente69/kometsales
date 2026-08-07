package co.micia.projects.restapi.pricelist.daljpa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsCompanyInventoryWithRoundedFreight() throws Exception {
        mockMvc.perform(get("/api/products/companies/{id}", 4L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyId").value(4L))
                .andExpect(jsonPath("$.products[0].productName").value("Red Roses 23cm"))
                .andExpect(jsonPath("$.products[0].finalFreight").value(4.72));
    }

    @Test
    void returnsCustomerPricesWithRoundedMarkdown() throws Exception {
        mockMvc.perform(get("/api/products/customers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[0].price").value(1.05));
    }

    @Test
    void returnsProductCodesAndNoContentForUnknownCompany() throws Exception {
        mockMvc.perform(get("/api/products/companies/{id}/codes", 4L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productCode").value("R0m"));

        mockMvc.perform(get("/api/products/companies/{id}", 999L))
                .andExpect(status().isNoContent());
    }
}
