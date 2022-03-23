package za.co.debtmaster.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BudgetTest {

    public Budget budget = new Budget("{\"income\":\"15000.00\"," +
            "\"expenses\":" +
            "{\"rent\":\"3000.00\"," +
            "\"groceries\":\"2000.00\"," +
            "\"transport\":\"2000.00\"" +
            "}}");

    public Budget budget2 = new Budget();

    @Test
    void testGetGrossIncome() {
        assertEquals(15000.00, budget.getGrossIncome());
    }

    @Test
    void testGetExpenses() {
        assertEquals(7000.00, budget.getTotalExpenses());
    }

    @Test
    void testIncomeAndExpenseTotals() {
        assertEquals(8000.00, budget.getNetIncome());
    }

    @Test
    void testToString() {
        assertEquals("{\"income\":\"15000.00\"," +
                "\"expenses\":" +
                "{\"rent\":\"3000.00\"," +
                "\"groceries\":\"2000.00\"," +
                "\"transport\":\"2000.00\"" +
                "}}", budget.toString());
    }
    @Test
    void testNoBudget() {
        assertEquals("{\"income\":\"0\"," +
                "\"expenses\":" +
                "{}}", budget2.toString());
    }

    @Test
    void testUpdateBudget() {
        // Test no budget
        assertEquals("{\"income\":\"0\"," +
                "\"expenses\":" +
                "{}}", budget2.toString());

        // Update the budget
        budget2.updateBudget("{\"income\":\"15000.00\"," +
                "\"expenses\":" +
                "{\"rent\":\"3000.00\"," +
                "\"groceries\":\"2000.00\"," +
                "\"transport\":\"2000.00\"" +
                "}}");

        // Test updated Budget
        assertEquals("{\"income\":\"15000.00\"," +
                "\"expenses\":" +
                "{\"rent\":\"3000.00\"," +
                "\"groceries\":\"2000.00\"," +
                "\"transport\":\"2000.00\"" +
                "}}", budget2.toString());
    }

}