package za.co.debtmaster.model;


/**
 * Calculates the monthly repayments of a debt
 * Constructor takes certain parameters
 * Calculator(debtAmount(float), debtType, durationMonths, deposit, interestRate, balloonPayment)
 */
public class Calculator {

    private double debtAmount, deposit, interestRate, balloonPayment;
    private int durationYears;
    private String debtType;

    public Calculator(double debtAmount, double deposit, double interestRate, double balloonPayment, int durationMonths, String debtType) {
        this.debtAmount = debtAmount;
        this.deposit = deposit;
        this.interestRate = interestRate;
        this.balloonPayment = balloonPayment;
        this.durationYears = durationMonths;
        this.debtType = debtType;
    }

    public double calculateVehicle() {
        if(deposit != 0){
            this.debtAmount = debtAmount - debtAmount;
        }


        return 0.00;
    }

    public double homeLoan() {
        return 0.00;
    }

    public double totalPayments(double monthlyPayment, int months) {

        return monthlyPayment * months;
    }
}
