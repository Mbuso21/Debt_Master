package za.co.debtmaster.model;

public class Person {

    private String name, surname, email;
    private Budget budget;
    private Debt debt;

    public Person(String name, String surname, String email, Budget budget) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.budget = budget;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Budget getBudget() {
        return budget;
    }

    public Debt getDebt() {
        return debt;
    }

    public void update(String name, String surname, String email, Budget budget) {

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.budget = budget;
    }
}
