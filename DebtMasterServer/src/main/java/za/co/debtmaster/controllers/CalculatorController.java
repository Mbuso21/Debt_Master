package za.co.debtmaster.controllers;

import io.javalin.http.Context;

public class CalculatorController {

    public static final String PATH_FORM = "/calculator_form";
    public static final String PATH = "/calculator";
    public static final String PATH_VEHICLE = "/calculator/vehicle";
    public static final String PATH_PERSONAL = "/calculator/personal";



    public static void handleCalculations(Context context) {

    }

    /**
     * Renders page
     * @param context
     */
    public static void renderCalculator(Context context) {
        context.render("calculate-debt.html");
    }
}
