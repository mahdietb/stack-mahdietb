

public class InputController {
    private final Character[] invalidOperator = {'#', '@', '$', '%', '&', '?', '<', '>', '=', '<'};

    private boolean startWithOperator(String phrase) {

        return (phrase.startsWith("!") || phrase.startsWith("*") || phrase.startsWith("/") || phrase.startsWith("^"));
    }

    private boolean endsWithOperator(String phrase) {
        return (phrase.endsWith("^") || phrase.endsWith("*") || phrase.endsWith("/") || phrase.endsWith("-") || phrase.endsWith("+"));
    }

    private boolean isOperator(char bit) {
        return (bit == '!' || bit == '^' || bit == '*' || bit == '/' || bit == '+' || bit == '-');
    }

    /**
     * @param phrase the input phrase
     * @return if there is two operator are next to each other or not
     */
    private boolean twoOperator(String phrase) {
        boolean result = false;
        char[] array = phrase.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                if (isOperator(array[i]) && isOperator(array[i + 1])) {
                    if (!checkExceptionForFactorial(array[i], array[i + 1])) {
                        result = true;
                    }

                }
            }
        }
        return result;
    }


    private boolean isNumberOfOperandValid(String phrase) {
        char[] array = phrase.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (isOperator(array[i]) && array[i] != '!') {
                if (isNumber(array[i - 1]) && isNumber(array[i + 1]))
            return true;
            }
        }
    return false;
    }

    /**
     * check exception for two consecutive operator if the fist one is factorial it is not an error
     */
    private boolean checkExceptionForFactorial(char a, char b) {
        return (a == '!' && b != '!');
    }


    private boolean operatorBeforeParentheses(char ch) {
        return (ch == '^' || ch == '*' || ch == '/' || ch == '+' || ch == '-');
    }

    private boolean operatorAfterParentheses(char ch) {//(*!^/
        return (ch == '!' || ch == '^' || ch == '*' || ch == '/');
    }

    private boolean operatorAfterOrBeforeParentheses(String phrase) {
        boolean result = true;
        Stack<Character> s = new Stack<>();
        char[] array = phrase.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') {
                s.push(array[i]);
                if ((i != array.length - 1) && operatorAfterParentheses(array[i + 1]))
                    return result;
            }
            if (array[i] == ')') {
                if (s.isEmpty()) return result;
                s.pop();
                if (operatorBeforeParentheses(array[i - 1]))
                    return result;

            }

        }

        return !s.isEmpty();

    }

    public boolean isInvalidOperator(String phrase) {
        for (int i = 0; i < invalidOperator.length; i++) {
            if (phrase.contains(String.valueOf(invalidOperator[i])))
                return true;
        }
        return false;
    }

    public String check(String phrase) {
        if (operatorAfterOrBeforeParentheses(phrase)||isNumberOfOperandValid(phrase)
                || isInvalidOperator(phrase) || startWithOperator(phrase)
                || endsWithOperator(phrase) || twoOperator(phrase) || isValidNumber(phrase)) {
            return "error";
        } else {
            return phrase;
        }
    }

    public boolean isNumber(char input) {
        return (input >= '0' && input <= '9');
    }


    /**
     * @param phrase input string
     * @return if the number in string is in valid form or not
     */
    public boolean isValidNumber(String phrase) {
        int index;
        if (phrase.contains(".")) {
            index = phrase.indexOf(".");
            return !(isNumber(phrase.charAt(index - 1)) && isNumber(phrase.charAt(index + 1)));
        }

        return false;
    }

}
