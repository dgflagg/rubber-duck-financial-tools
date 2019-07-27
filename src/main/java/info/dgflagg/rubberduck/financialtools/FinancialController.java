package info.dgflagg.rubberduck.financialtools;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinancialController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(FinancialController.class);

    @RequestMapping("/pay")
    public double test(@RequestParam(value="salary") Double salary) {
        log.info(String.format("salary: $%f", salary));

        double fit = TaxCalculator.federalIncomeTax(salary, 0);

        double takeHomePay = salary - fit;

        return takeHomePay;
    }

}
