package za.co.debtmaster.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {

    public Budget budget = new Budget("{\"income\":\"15000.00\"," +
            "\"expenses\":" +
            "{\"rent\":\"3000.00\", " +
            "\"groceries\":\"2000.00\", " +
            "\"transport\":\"2000.00\"" +
            "}" +
            "}");

    @Test
    void testGetGrossIncome() {
        System.out.println(budget.getBudgetJSONString());
        assertEquals(15000.00, budget.getGrossIncome());
    }

    @Test
    void testGetExpenses() {
        assertEquals(7000.00, budget.getTotalExpenses());
    }

    @Test
    void testIncomeAndExpenseTotals() {
        assertEquals(5000.00, budget.getNetIncome());
    }

}