package info.dgflagg.rubberduck.financialtools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FinancialControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void payShouldReturnSalaryMinusTaxes() throws Exception {

        this.mockMvc
                .perform(get("/pay").param("salary", "100000"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(60000));
    }

}
