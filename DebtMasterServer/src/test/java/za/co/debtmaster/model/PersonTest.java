package za.co.debtmaster.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testPerson() {
        Budget budget = new Budget();
        Person person = new Person( "Mbuso","mbuso@test.com", budget);
        assertEquals("Mbuso", person.getName());
        assertEquals("mbuso@test.com", person.getEmail());

    }
    @Test
    void testUpdate() {
        Budget budget = new Budget();
        Person person = new Person("Mbuso", "mbuso@test.com", budget);
        assertEquals("Mbuso", person.getName());
        assertEquals("mbuso@test.com", person.getEmail());

        // we call the update function
        person.update("Seokamela", "Ntjobokoane", "david@test.com", budget);
        assertEquals("Seokamela", person.getName());
        assertEquals("david@test.com", person.getEmail());
    }

    @Test
    void testToStringNoBudget() {
        Budget budget = new Budget();
        Person person = new Person("Mbuso", "mbuso@test.com", budget);
        assertEquals("{\"name\":\"Mbuso\",\"email\":\"mbuso@test.com\",\"budget\":\"No Budget\"}", person.toString());
    }

    @Test
    void testToStringWithBudget (){
        Budget budget = new Budget("{\"income\":\"15000.00\"," +
                "\"expenses\":" +
                "{\"rent\":\"3000.00\"," +
                "\"groceries\":\"2000.00\"," +
                "\"transport\":\"2000.00\"" +
                "}}");
        Person person = new Person("Mbuso", "mbuso@test.com", budget);
        assertEquals("{\"name\":\"Mbuso\"," +
                                "\"email\":\"mbuso@test.com\"," +
                                "\"budget\":" +
                                        "\"{\"income\":\"15000.00\"," +
                                        "\"expenses\":" +
                                                        "{\"rent\":\"3000.00\"," +
                                                        "\"groceries\":\"2000.00\"," +
                                                        "\"transport\":\"2000.00\"}" +
                                        "}\"" +
                                "}", person.toString());
    }

}