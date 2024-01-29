package Controller;

import Model.BMI;
import Model.Operator;
import java.util.Scanner;

public class Calculator_Handler {

    private Scanner sc;

    public Calculator_Handler() {
        this.sc = new Scanner(System.in);
    }

    public void normalCalculator() {
        double result = 0;

        while (true) {
            System.out.println("Enter equation, '=' to calculate:");
            String input = sc.nextLine();

            if (input.equals("=")) {
                System.out.println("Result: " + result);
                break;
            }

            double a = 0;
            double b = 0;
            char operator = ' ';

            StringBuilder numBuilder = new StringBuilder();

            for (char c : input.toCharArray()) {
                if (Character.isDigit(c) || c == '.') {
                    numBuilder.append(c);
                } else if (isOperator(c)) {
                    operator = c;
                    a = Double.parseDouble(numBuilder.toString());
                    numBuilder = new StringBuilder();
                }
            }

            if (numBuilder.length() > 0) {
                b = Double.parseDouble(numBuilder.toString());
            }

            Operator operatorEnum = getOperator(String.valueOf(operator));
            if (operatorEnum == null) {
                System.out.println("Invalid operator. Please try again.");
                continue;
            }
            result = calculate(a, operatorEnum, b);
            System.out.println("Memory: " + result);
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    public void bmiCalculator() {
        System.out.println("Enter weight(kg):");
        double weight = sc.nextDouble();
        System.out.println("Enter height(cm):");
        double heightInCm = sc.nextDouble();
        sc.nextLine();

        // Convert height from centimeters to meters 
        double heightInMeters = heightInCm / 100.0;

        double bmiValue = weight / (heightInMeters * heightInMeters);
        BMI bmi = getBMIStatus(bmiValue);

        // Format the BMI value to two decimal places
        String formattedBMIValue = String.format("%.2f", bmiValue);

        System.out.println("BMI Score: " + formattedBMIValue);
        System.out.println("BMI Category: " + bmi);
    }

    public Operator getOperator(String symbol) {
        for (Operator operator : Operator.values()) {
            if (operator.getSymbol().equals(symbol)) {
                return operator;
            }
        }
        return null;
    }

    public double calculate(double a, Operator operator, double b) {
        switch (operator) {
            case ADDITION -> {
                return a + b;
            }
            case SUBTRACTION -> {
                return a - b;
            }
            case MULTIPLICATION -> {
                return a * b;
            }
            case DIVISION -> {
                if (b == 0) {
                    System.out.println("Cannot divide by zero. Please try again.");
                    return a;
                }
                return a / b;
            }
            case EXPONENT -> {
                return Math.pow(a, b);
            }
            default -> {
                return a;
            }
        }
    }

    public BMI getBMIStatus(double bmiValue) {

        if (bmiValue < 19) {
            return BMI.UNDER_STANDARD;
        } else if (bmiValue >= 19 && bmiValue <= 25) {
            return BMI.STANDARD;
        } else if (bmiValue > 25 && bmiValue <= 30) {
            return BMI.OVERWEIGHT;
        } else if (bmiValue > 30 && bmiValue <= 40) {
            return BMI.FAT_SHOULD_LOSE_WEIGHT;
        } else {
            return BMI.VERY_FAT_SHOULD_LOSE_WEIGHT_IMMEDIATELY;
        }
    }
}
