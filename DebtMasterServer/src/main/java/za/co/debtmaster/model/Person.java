package za.co.debtmaster.model;

public class Person {

    private String name;
    private String email;
    private Budget budget;


    public Person(String name, String email, Budget budget) {
        this.name = name;
        this.email = email;
        this.budget = budget;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Budget getBudget() {
        return budget;
    }

    public void update(String name, String surname, String email, Budget budget) {

        this.name = name;
        this.email = email;
        this.budget = budget;
    }


    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"budget\":\"" + budget.toString() + "\"" +
                "}";
    }
}
