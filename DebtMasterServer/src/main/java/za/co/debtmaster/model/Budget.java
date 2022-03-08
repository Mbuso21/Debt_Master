package za.co.debtmaster.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Budget {

    private double totalIncome, totalExpenses;
    private String budgetJSONString;


    public Budget(String budgetJSONString) {
        this.totalIncome = getNetIncome();
        this.totalExpenses = getTotalExpenses();
        this.budgetJSONString = budgetJSONString;
    }

    public Budget() {

    }

    public String getBudgetJSONString() {
        return this.budgetJSONString;
    }

    public double getGrossIncome() {
        System.out.println(budgetJSONString);
        Map<String, String> requestMap = convertJSONStringToMap(budgetJSONString);
        return Double.parseDouble(requestMap.get("income").toString());
    }

    public double getTotalExpenses() {
        Map<String, String> requestMap = convertJSONStringToMap(this.budgetJSONString);
        System.out.println(requestMap);
        Map<String, String> expenseMap = convertJSONStringToMap(requestMap.get("expenses"));
        double totalExpenses = 0.00;
        for(Map.Entry<String, String> expense : expenseMap.entrySet()) {
            totalExpenses += Double.parseDouble(expense.getValue());
        }

        return totalExpenses;
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
        Map<String, String> requestMap = null;
        try {
            requestMap = mapper.readValue(budgetJSONString, Map.class);
            System.out.println(requestMap);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't convert to map");

        }

        return requestMap;
    }



}
