package Controller;

import View.Menu;

public class BMI_Calculator {

    Calculator_Handler handler = new Calculator_Handler();

    public void displayMainMenu() {
        Menu<String> mainMenu = new Menu<>("Calculator Program", new String[]{
            "Normal calculator",
            "BMI Calculator",
            "Exit"
        }) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 ->
                        handler.normalCalculator();
                    case 2 -> 
                        handler.bmiCalculator();
                    case 3 ->
                        System.exit(0);
                    default ->
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        };
        mainMenu.run();
    }

    public static void main(String[] args) {
        BMI_Calculator main = new BMI_Calculator();
        main.displayMainMenu();
    }
}
