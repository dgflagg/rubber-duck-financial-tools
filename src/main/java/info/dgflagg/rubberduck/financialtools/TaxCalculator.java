package info.dgflagg.rubberduck.financialtools;

import java.util.*;

public class TaxCalculator {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(FinancialController.class);

    public static final double STANDARD_DEDUCTION = 12200;

    //TODO: create a singleton to store individual, married and hoh brackets - use a switch statement to choose
    public static final Map<Double,Double> TAX_BRACKETS = new LinkedHashMap<>() {{
        put(510300.0,   0.37);
        put(204100.0,   0.35);
        put(160725.0,   0.32);
        put(84200.0,    0.24);
        put(39475.0,    0.22);
        put(9700.0,     0.12);
        put(0.0,        0.1);
    }};


    public static double federalIncomeTax(double grossIncome, double pretaxRetirementContributions) {

        double deductions = pretaxRetirementContributions;

        double agi = adjustedGrossIncome(grossIncome, deductions);
        double taxableIncome = taxableIncome(agi, STANDARD_DEDUCTION);

        double taxableIncomeRemaining = taxableIncome;

        Map<Double,Double> taxesApplied = calculateTaxesOwedByBracket(TAX_BRACKETS, taxableIncome);

        taxesApplied.forEach( (taxRate, taxApplied) -> log.info(taxRate + ": $" + taxApplied) );

        //sum the taxes owed
        double federalIncomeTax = taxesApplied.values().stream().reduce((x, y) -> x + y).get();

        return federalIncomeTax;
    }

    private static Map<Double,Double> calculateTaxesOwedByBracket(Map<Double,Double> taxBrackets,
                                                                  double taxableIncomeRemaining) {

        //the taxes applied in each tax bracket
        Map<Double,Double> taxesApplied = new LinkedHashMap<>();

        //iterate over the bottom limits of the tax brackets in order from highest taxable income to lowest
        for(Double taxBracket : taxBrackets.keySet()) {
            double bracketTax = 0;

            //determine if the remaining taxable income needs is above the lower limit of the current tax bracket
            if(taxableIncomeRemaining > taxBracket) {

                //determine how much of the remaining taxable income should be taxed at this current bracket rate
                double incomeOverBracket = taxableIncomeRemaining - taxBracket;

                //determine how much tax is owed for the current bracket at the current bracket tax rate
                bracketTax = incomeOverBracket * taxBrackets.get(taxBracket);

                //decrement the income remaining to to taxed by the amount of income over the current tax bracket
                taxableIncomeRemaining = taxableIncomeRemaining - incomeOverBracket;
            }

            //add the taxes applied for this bracket into the the list
            taxesApplied.put(taxBrackets.get(taxBracket), bracketTax);

        }

        return taxesApplied;

    }

    private static double adjustedGrossIncome(double grossIncome, double deductions) {

        double adjustedGrossIncome = grossIncome - deductions;

        return adjustedGrossIncome;
    }

    private static double taxableIncome(double adjustedGrossIncome, double deductions) {

        double taxableIncome = adjustedGrossIncome - deductions;

        return taxableIncome;
    }

}
