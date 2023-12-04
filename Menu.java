import java.util.Scanner;

public class Menu {
    Scanner get = new Scanner(System.in);
    private Calculation calculation = new Calculation();
    private NewOperators newOperators = new NewOperators();

    public void caption() {

        System.out.println("===========================");
        System.out.println("==========Welcome==========");
        System.out.println("===========================");
        System.out.println("please choose your intended option");
        System.out.println();
        System.out.println("1)Calculation");
        System.out.println("2)Add NewOperator");
        System.out.println("3)History");
        System.out.println("4)Exit");
        System.out.println();
        System.out.print("option :");

    }

    /**
     * show the menu of calculator
     */
    public void menu() {
        boolean check = true;
        String choice;
        while (check) {
            caption();
            choice = get.next();
            switch (choice) {
                case "1" -> {
                    newOperators.print();
                    extracted();
                }
                case "2" -> {
                    System.out.println("newOperator");
                    newOperator();
                }
                case "3" -> System.out.println("history");
                case "4" -> check = false;
            }

        }
    }


    /**
     * check if we have new operator or not and calculate the final answer base on that
     */
    private void extracted() {
        System.out.print("phrase =");
        String phrase = get.next();
        if (newOperators.containNewOperator(phrase)) {
           phrase = newOperators.simplified(phrase);
        }
        System.out.println(calculation.evaluate(phrase));
    }

    private void newOperator() {
        System.out.println("please fill the needed information");
        System.out.print("nameOfOperator :");
        String name = get.next();
        System.out.println("operation :");
        String operation = get.next();
        newOperators.addNewOperator(name, operation);
    }


}
