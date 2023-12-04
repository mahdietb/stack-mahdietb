

public class Postfix {

   private int priorityOf(char operator) {
        int pri = 0;

        switch (operator) {
            case '!' -> pri = 4;

            case '^' -> pri = 3;

            case '*', '/' -> pri = 2;

            case '+', '-' -> pri = 1;

        }

        return pri;

    }

    private boolean isNumber(char bit) {
        return ((bit >= '0' && bit <= '9') || bit == '.');
    }


    /**
     *
     * @param array char array of input string
     * @param i the index of '-' operator
     * @return if '-' means the number is negative, or it is operator
     */
    private boolean isNegativeNumber(char[] array, int i) {

        boolean check = false;

        if (i == 0 && isNumber(array[i + 1])) {
            check = true;
        } else if (i != 0 && array[i - 1] == '(' && isNumber(array[i + 1])) {
            check = true;
        }
        return check;

    }

    /**
     *
     * @param a firs character
     * @param b next one
     * @return if we have consecutive pow or not
     */
   private boolean isSeveralPowers(char a, char b) {
        return (a == '^' && b == '^');
    }


  private   boolean isOperator(char bit) {
        return (bit == '!' || bit == '^' || bit == '*' || bit == '/' || bit == '+' || bit == '-');
    }

    /**
     *
     * @param phrase input string
     * @return the postfix form of phrase
     */
    public  String[] turnToPostfix(String phrase) {
        StringBuilder temp = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        char[] array = phrase.toCharArray();
        String[] numbers = new String[phrase.length()];
        int size = 0;
        int position = 0;
        boolean check = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') {
                stack.push(array[i]);
            }
            if (isNumber(array[i])) {
                int k = i;
                while (isNumber(array[i])) {
                    temp.append((array[i]));
                    if (i == array.length - 1) {
                        break;
                    }
                    i++;
                }
                if (check && position == k - 1) {
                    numbers[size++] = "-" + temp;
                    check = false;
                    position = 0;
                } else {
                    numbers[size++] = temp.toString();
                }
                temp = new StringBuilder();
            }
            if (isOperator(array[i])) {
                if (array[i] == '-' && isNegativeNumber(array, i)) {
                    check = true;
                    position = i;
                } else {

                    size = getSize(stack, array, numbers, size, i);
                }
            }
            if (array[i] == ')') {
                while (stack.peek() != '(')
                    numbers[size++] = String.valueOf(stack.pop());
                stack.pop();
            }
        }
        while (!stack.isEmpty())
            numbers[size++] = String.valueOf(stack.pop());

        return numbers;
    }

    /**
     *
     * @param stack operators stack
     * @param array input char array
     * @param numbers the final form of postfix array
     * @param size the index we should put our value on it
     * @param i current index of array
     * @return the index we should carry in main method
     */
    private int getSize(Stack<Character> stack, char[] array, String[] numbers, int size, int i) {

        if (stack.isEmpty()) {
            stack.push(array[i]);
        } else {
            if (priorityOf(array[i]) > priorityOf(stack.peek()) || isSeveralPowers(array[i], stack.peek())) {
                stack.push(array[i]);
            } else {
                while (!stack.isEmpty() && (priorityOf(array[i]) <= priorityOf(stack.peek()))) {
                    numbers[size++] = String.valueOf(stack.pop());
                }
                stack.push(array[i]);
            }
        }
        return size;
    }

}
