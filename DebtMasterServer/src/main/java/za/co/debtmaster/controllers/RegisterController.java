package za.co.debtmaster.controllers;

import io.javalin.http.Context;
import za.co.debtmaster.app.db.DataBaseHandler;
import za.co.debtmaster.model.Budget;
import za.co.debtmaster.model.Person;

import java.sql.SQLException;

public class RegisterController {
    public static final String REG_PATH = "/register";
    public static final String REG_ROUTE = "/register_form";


    public static void registerNewUser(Context context) throws SQLException {
        System.out.println("In registerNewUser");
        System.out.println(context.formParam("name"));
        System.out.println(context.formParam("email"));
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        Person person = new Person(context.formParam("name"), context.formParam("email"), new Budget());

        System.out.println(person);
        try {
            dataBaseHandler.addPerson(person);
            context.sessionAttribute("user", person);
        } catch (Error e) {
            // Render on page that user already exists
            dataBaseHandler.disconnect();
            context.render("registered-already.html");

        }




//        context.render("home.html");
        dataBaseHandler.disconnect();

    }

    public static void renderRegister(Context context) {
        context.render("register.html");

    }

}
