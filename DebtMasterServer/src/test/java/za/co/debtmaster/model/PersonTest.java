package za.co.debtmaster.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testPerson() {
        Budget budget = new Budget();
        Debt debt = new Debt();
        Person person = new Person("Mbuso", "Mehlomakulu", "mbuso@test.com", budget);
        assertEquals("Mbuso", person.getName());
        assertEquals("Mehlomakulu", person.getSurname());
        assertEquals("mbuso@test.com", person.getEmail());

    }
    @Test
    void testUpdate() {
        Budget budget = new Budget();
        Debt debt = new Debt();
        Person person = new Person("Mbuso", "Mehlomakulu", "mbuso@test.com", budget);
        assertEquals("Mbuso", person.getName());
        assertEquals("Mehlomakulu", person.getSurname());
        assertEquals("mbuso@test.com", person.getEmail());

        // we call the update function
        person.update("Seokamela", "Ntjobokoane", "david@test.com", budget);
        assertEquals("Seokamela", person.getName());
        assertEquals("Ntjobokoane", person.getSurname());
        assertEquals("david@test.com", person.getEmail());
    }

}