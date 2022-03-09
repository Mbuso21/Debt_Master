package za.co.debtmaster.model;


/**
 * Calculates the monthly repayments of a debt
 * Constructor takes certain parameters
 * Calculator(debtAmount(float), debtType, durationMonths, deposit, interestRate, balloonPayment)
 */
public class Calculator {

    private double principalAmount, deposit, interestRate, balloonPayment;
    private int durationMonths;
    private String debtType;

    public Calculator(double principalAmount, double deposit, double interestRate, double balloonPayment, int durationMonths, String debtType) {
        this.principalAmount = principalAmount;
        this.deposit = deposit;
        this.interestRate = interestRate/100;
        this.balloonPayment = balloonPayment;
        this.durationMonths = durationMonths;
        this.debtType = debtType;
    }

    public double calculateVehicleAndLoan() {
        if(deposit != 0){
            principalAmount = principalAmount - deposit;
        }
        // Adding Initiation fee and Monthly Admin fee.
        principalAmount = principalAmount + 1207.50;

        System.out.println(principalAmount);

        double numerator = principalAmount * (interestRate/12);
        double denominator = 1.00 - Math.pow(1.00 + interestRate/12, -durationMonths);

        double monthlyPayment = numerator/denominator;
        return (double) Math.round(monthlyPayment * 100)/100 + 69.00;
    }

    public double homeLoan() {
        return 0.00;
    }

    public double totalPayments() {
        if(debtType == "vehicle" || debtType == "personal"){
            return calculateVehicleAndLoan() * durationMonths;
        }
        return 0.00;
    }
}
