package za.co.debtmaster.controllers;

import io.javalin.http.Context;
import za.co.debtmaster.model.Budget;
import za.co.debtmaster.model.Person;

public class RegisterController {
    public static final String REG_PATH = "/register";
    public static final String REG_ROUTE = "/register_form";


    public static void registerNewUser(Context context) {
        System.out.println("In registerNewUser");
        System.out.println(context.formParam("name"));
        System.out.println(context.formParam("email"));

        Person person = new Person(context.formParam("name"), context.formParam("email"), new Budget());

        System.out.println(person.toString());

//        context.render("index.html");


    }

    public static void renderRegister(Context context) {
        context.render("register.html");

    }

}
