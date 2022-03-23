package za.co.debtmaster.controllers;

import io.javalin.http.Context;

public class RegisterController {
    public static final String REG_PATH = "/register";
    public static final String REG_ROUTE = "/register_form";


    public static void registerNewUser(Context context) {
    }

    public static void renderRegister(Context context) {
        context.render("register.html");

    }

}
