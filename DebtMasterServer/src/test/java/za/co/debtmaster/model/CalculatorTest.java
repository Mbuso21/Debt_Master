package za.co.debtmaster.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testVehicleFinanceNoDepositNoBalloon() {
        Calculator calculator = new Calculator(
                200000.00,
                0.0,
                7.00,
                0.0,
                60,
                "Vehicle");
        assertEquals(4053.15, calculator.calculateVehicle());
    }

    @Test
    void testVehicleFinanceBalloonNoDeposit() {
        Calculator calculator = new Calculator(
                200000.00,
                0.00,
                7.00,
                70000,
                60,
                "Vehicle");
        assertEquals(3075.40, calculator.calculateVehicle());
    }

    @Test
    void testVehicleFinanceWithDepositNoBalloon() {
        Calculator calculator = new Calculator(
                200000.00,
                20000,
                7.00,
                0.00,
                60,
                "Vehicle");
        assertEquals(3657.13, calculator.calculateVehicle());
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
    void testTotalPayments() {
        Calculator calculator = new Calculator(700000.00,
                                                    20000,
                                                7.00,
                                            0.0,
                                            240,
                                                "Home");
        assertEquals(180000.00, calculator.totalPayments(2500.00, 72));
    }

}