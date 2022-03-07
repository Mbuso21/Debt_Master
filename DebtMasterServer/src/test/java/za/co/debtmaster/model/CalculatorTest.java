package za.co.debtmaster.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testVehicleFinanceNoDepositNoBalloon() {
        Calculator calculator = new Calculator(120000.00, 0.0, 7.00, 0.0, 5, "Vehicle");
        assertEquals(2329.78, calculator.calculateVehicle());
    }

    @Test
    void testVehicleFinanceBalloonNoDeposit() {
        Calculator calculator = new Calculator(120000.00, 0.0, 7.00, 10800.00, 5, "Vehicle");
        assertEquals(2329.78, calculator.calculateVehicle());
    }

    @Test
    void testVehicleFinanceDepositNoBalloon() {
        Calculator calculator = new Calculator(120000.00, 20000.00, 7.00, 0.0, 5, "Vehicle");
        assertEquals(1794.49, calculator.calculateVehicle());
    }

    @Test
    void testHomeLoanNoDeposit() {
        Calculator calculator = new Calculator(700000.00, 0.0, 7.00, 0.0, 20, "Home");
        assertEquals(5639.00, calculator.homeLoan());
    }

    @Test
    void testHomeLoanWithDeposit() {
        Calculator calculator = new Calculator(700000.00, 20000, 7.00, 0.0, 20, "Home");
        assertEquals(5478.00, calculator.homeLoan());
    }

}