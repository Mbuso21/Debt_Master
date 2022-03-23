package za.co.debtmaster.model;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Budget {

    private double totalIncome, totalExpenses;
    public String budgetJSONString;


    public Budget(String budgetJSONString) {
        this.budgetJSONString = budgetJSONString;
        this.totalIncome = getGrossIncome();
        this.totalExpenses = getTotalExpenses();

    }

    public Budget() {
        this.totalIncome = 0.00;
        this.totalExpenses = 0.00;
        this.budgetJSONString = "{\"income\":\"0\",\"expenses\":{}}";

    }

    public double getGrossIncome() {
        Map<String, String> requestMap = convertJSONStringToMap(budgetJSONString);
        return Double.parseDouble(requestMap.get("income").toString());
    }

    public double getTotalExpenses() {
        Map<String, String> requestMap = convertJSONStringToMap(this.budgetJSONString);
        // {"income":"15000.00","expenses":{".............."}}
        // requestMap.get("expenses") is taken as a LinkedHashMap object
        Object expenseJSON = requestMap.get("expenses");

        Map<String, String> expenseMap = (Map<String, String>) expenseJSON;
        double totalExpensesIn = 0.00;
        for(Map.Entry<String, String> expense : expenseMap.entrySet()) {
            totalExpensesIn += Double.parseDouble(expense.getValue());
        }
        return totalExpensesIn;
    }

    /**
     *
     * @return difference between income and expenses
     */
    public double getNetIncome() {

        return totalIncome-totalExpenses;
    }

    /**
     * Converts JSON String to Map
     * @param budgetJSONString JSON String
     * @return converted JSON to Map
     */
    public Map<String, String> convertJSONStringToMap(String budgetJSONString) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> requestMap = new HashMap<>();
        try {
            requestMap = mapper.readValue(budgetJSONString, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't convert to map");

        }
        return requestMap;
    }

    @Override
    public String toString() {
        return this.budgetJSONString;
    }


    public void updateBudget(String updatedBudget) {
        this.budgetJSONString = updatedBudget;
    }
}
