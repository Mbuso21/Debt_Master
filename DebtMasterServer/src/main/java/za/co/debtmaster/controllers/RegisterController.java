package za.co.debtmaster.controllers;

import io.javalin.http.Context;
import za.co.debtmaster.app.db.DataBaseHandler;
import za.co.debtmaster.model.Budget;
import za.co.debtmaster.model.Person;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegisterController {
    public static final String REG_PATH = "/register";
    public static final String REG_ROUTE = "/register_form";


    public static void registerNewUser(Context context) throws SQLException {
        System.out.println("In registerNewUser");
        System.out.println(context.formParam("name"));
        System.out.println(context.formParam("email"));

        // Check to see if the email is fine
        if(isValid(context.formParam("email")) || context.formParam("name").isBlank()) {
            context.render("registered-already.html");
            return;
        }

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

        dataBaseHandler.disconnect();

    }

    public static void renderRegister(Context context) {
        context.render("register.html");

    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }



}
