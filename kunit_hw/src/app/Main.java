package app;

public class Main {
    public static void main(String[] args) {//
        String numbers = "//[***][))][%][)**)*]\n1)**)*2%3))24";
        StringCalculator calculator = new StringCalculator();
        int output = calculator.add(numbers);
        System.out.println(" output = " + output);
    }
}
