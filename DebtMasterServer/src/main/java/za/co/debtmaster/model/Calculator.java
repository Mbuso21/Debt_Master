package za.co.debtmaster.model;


/**
 * Calculates the monthly repayments of a debt
 * Constructor takes certain parameters
 * Calculator(debtAmount(float), debtType, durationMonths, deposit, interestRate, balloonPayment)
 */
public class Calculator {

    private double principalAmount;
    private final double deposit;
    private final double interestRate;
    private final double balloonPayment;
    private final int durationMonths;
    private final String debtType;

    public Calculator(double principalAmount, double deposit, double interestRate, double balloonPayment, int durationMonths, String debtType) {
        this.principalAmount = principalAmount;
        this.deposit = deposit;
        this.interestRate = interestRate/100;
        this.balloonPayment = balloonPayment;
        this.durationMonths = durationMonths;
        this.debtType = debtType;
    }

    /**
     * Calculates the monthly payment that a Vehicle or Personal loan will cost
     * this includes
     * @return the monthly repayment
     */
    public double calculateVehicleAndLoan() {

        principalAmount = principalAmount - deposit;
        double InitiationFee = 1207.50;
        // Adding Initiation fee and Monthly Admin fee.
        principalAmount = principalAmount + InitiationFee;

        System.out.println(principalAmount);

        double numerator = principalAmount * (interestRate/12);
        double denominator = 1.00 - Math.pow(1.00 + interestRate/12, -durationMonths);

        double monthlyPayment = numerator/denominator;
        double monthlyAdminFee = 69.00;
        return (double) Math.round(monthlyPayment * 100)/100 + monthlyAdminFee;
    }

    public double homeLoan() {
        principalAmount = principalAmount - deposit;
        // transfer and other costs
        principalAmount = principalAmount + (principalAmount * 0.065);
        // Home loan equation for the numerator
        double numerator = principalAmount * (interestRate/12) * Math.pow(1 + (interestRate/12), durationMonths);
        // Home loan equation for denominator
        double denominator = Math.pow(1 + (interestRate/12), durationMonths) - 1;
        double monthlyPayment = numerator/denominator;
        return (double) Math.round(monthlyPayment * 100)/100;
    }

    public double totalPayments() {
        if(debtType == "vehicle" || debtType == "personal"){
            return calculateVehicleAndLoan() * durationMonths;
        }

        if(debtType == "home") {
            return homeLoan() * durationMonths;
        }
        return 0.00;
    }
}
