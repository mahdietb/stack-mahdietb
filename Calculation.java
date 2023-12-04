import java.util.Objects;


public class Calculation {
    private boolean operatorInPostfix(String bit) {
        return (Objects.equals(bit, "!") || Objects.equals(bit, "^") || Objects.equals(bit, "*") || Objects.equals(bit, "/") || Objects.equals(bit, "+") || Objects.equals(bit, "-"));
    }

    private boolean divisionByZero(double second) {//to continue
        return (second != 0);
    }

    /**
     *
     * @param phrase input string
     * @return final answer of phrase
     */
    public String evaluate(String phrase) {
        Postfix postfix = new Postfix();
        String finalAnswer ;
        InputController controller = new InputController();
        if (!controller.check(phrase).equals("error")) {
            finalAnswer = calculate(postfix.turnToPostfix(phrase));

        } else {
            finalAnswer = "error";
        }
        return finalAnswer;
    }

    /**
     *
     * @param inputs postfix array
     * @return the answer of postfix form of phrase
     */
    private String calculate(String[] inputs) {
        String answer = "";
        Stack<Double> numbers = new Stack<>();
        String result;
        int check = 0;
        for (String input : inputs) {
            if (operatorInPostfix(input)) {
                result = calculateForEach(numbers, input);
                if (!result.equals("error")) {
                    numbers.push(Double.parseDouble(result));
                } else {
                    answer = "error";
                    check = 1;
                }
            } else {
                if (input != null) {
                    if (!input.equals(".")) {
                        numbers.push(Double.parseDouble(input));
                    } else {
                        check = 1;
                        answer = "error";
                    }
                }
            }
        }
        if (check == 0) {
            answer = String.valueOf(numbers.peek());
        }
        return answer;
    }

    private String calculateForEach(Stack<Double> stack, String inputOperator) {
        double a, b;
        String answer = "";
        switch (inputOperator) {
            case "!" -> {
                a = stack.pop();
                answer = String.valueOf(factorial(a));
            }
            case "^" -> {
                a = stack.pop();
                b = stack.pop();
                answer = String.valueOf(Math.pow(b, a));
            }
            case "*" -> {
                a = stack.pop();
                b = stack.pop();
                answer = String.valueOf(a * b);
            }
            case "/" -> {
                a = stack.pop();
                b = stack.pop();
                if (divisionByZero(a)) {
                    answer = String.valueOf(b / a);
                } else {
                    answer = "error";
                }
            }
            case "+" -> answer = String.valueOf(sum(stack));
            case "-" -> answer = String.valueOf(minus(stack));

        }
        return answer;
    }

    private double factorial(double a) {
        return multiply(a);
    }

    private double multiply(double a) {
        if (a >= 1) {
            return a * multiply(a - 1);
        } else {
            return 1;
        }
    }

    private double sum(Stack<Double> stack) {
        double a = stack.pop();
        if (stack.isEmpty()) {
            return a;
        } else {
            double b = stack.pop();
            return a + b;
        }
    }

    private double minus(Stack<Double> stack) {
        double a = stack.pop();
        if (stack.isEmpty()) {
            return -a;
        } else {
            double b = stack.pop();
            return b - a;
        }
    }


}
