package za.co.debtmaster.model;


/**
 * Calculates the monthly repayments of a debt
 * Constructor takes certain parameters
 * Calculator(debtAmount(float), debtType, duration, deposit, interestRate, balloonPayment)
 */
public class Calculator {

    private double debtAmount, deposit, interestRate, balloonPayment;
    private int duration;
    private String debtType;

    public Calculator(double debtAmount, double deposit, double interestRate, double balloonPayment, int duration, String debtType) {
        this.debtAmount = debtAmount;
        this.deposit = deposit;
        this.interestRate = interestRate;
        this.balloonPayment = balloonPayment;
        this.duration = duration;
        this.debtType = debtType;
    }
}
