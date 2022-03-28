package za.co.debtmaster.controllers;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegisterControllerTest {

    @Test
    @Description("Test all correct email addressed")
    void testEmailIsValid() {
        assertTrue(RegisterController.isValid("mbuso@test.com"));
        assertTrue(RegisterController.isValid("mbuso@test.co.za"));
        assertTrue(RegisterController.isValid("mbuso@test.hello.com"));
        assertTrue(RegisterController.isValid("mbuso@student.wethinkcode.co.za"));
        assertTrue(RegisterController.isValid("mbuso.meh@student.wethinkcode.co.za"));
        assertTrue(RegisterController.isValid("mbuso-hello.meh@student.wethinkcode.co.za"));
        assertTrue(RegisterController.isValid("mbuso-hello-meh@student.wethinkcode.co.za"));
        assertTrue(RegisterController.isValid("mbuso@student.help"));
    }

    @Test
    @Description("Test all incorrect email addresses")
    void testEmailIsNotValid() {
        assertFalse(RegisterController.isValid(""));
        assertFalse(RegisterController.isValid("mbuso@test..com"));
        assertFalse(RegisterController.isValid("mbusotest..com"));
        assertFalse(RegisterController.isValid("mbuso!!test..com"));
        assertFalse(RegisterController.isValid("mbuso@test.com?"));

    }
}