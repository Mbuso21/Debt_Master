package za.co.debtmaster.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testVehicleFinanceNoDeposit() {
        Calculator calculator = new Calculator(
                200000.00,
                0.0,
                7.00,
                0.0,
                60,
                "Vehicle");
        assertEquals(4053.15, calculator.calculateVehicleAndLoan());
    }

    @Test
    void testVehicleFinanceWithDeposit() {
        Calculator calculator = new Calculator(
                200000.00,
                20000,
                7.00,
                0.00,
                60,
                "Vehicle");
        assertEquals(3657.13, calculator.calculateVehicleAndLoan());
    }

    @Test
    void testPersonalLoanNoDeposit() {
        Calculator calculator = new Calculator(
                30000.00,
                0.0,
                17.00,
                0.0,
                24,
                "personal");
        assertEquals(1611.97, calculator.calculateVehicleAndLoan());
    }

    @Test
    void testHomeLoanNoDeposit() {
        Calculator calculator = new Calculator(
                700000.00,
                0.00,
                7.00,
                0.0,
                240,
                "Home");
        assertEquals(5639.00, calculator.homeLoan());
    }

    @Test
    void testHomeLoanWithDeposit() {
        Calculator calculator = new Calculator(
                700000.00,
                20000,
                7.00,
                0.0,
                240,
                "Home");
        assertEquals(5478.00, calculator.homeLoan());
    }

    @Test
    void testTotalPaymentsHome() {
        Calculator calculator = new Calculator(700000.00,
                                                    20000,
                                                7.00,
                                            0.0,
                                            240,
                                                "home");
        assertEquals(180000.00, calculator.totalPayments());
    }

    @Test
    void testTotalPaymentsVehicle() {
        Calculator calculator = new Calculator(200000.00,
                0.00,
                7.00,
                0.0,
                60,
                "vehicle");
        assertEquals(243189.00, calculator.totalPayments());
    }

}