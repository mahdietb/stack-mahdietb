import java.util.ArrayList;
import java.util.Arrays;

public class NewOperators {
    private ArrayList<NewOperator> operators = new ArrayList<>();
    private Calculation calculation = new Calculation();

    public void addNewOperator(String name, String operation) {
        operators.add(new NewOperator(name, operation));

    }

    /**
     *
     * @param phrase input string
     * @return answer of created operator
     *this method calculate the answer of operator we creat and put it in string
     */
    public String simplified(String phrase) {
        char[] array = phrase.toCharArray();
        String operands;
        int j;
        int startIndex;
        StringBuilder temp = new StringBuilder();
        StringBuilder newPhrase = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (isCharacter(array[i])) {
                newPhrase.deleteCharAt(newPhrase.length() - 1);
                startIndex = i;
                j = i;
                while (isCharacter(array[j])) {
                    temp.append(array[j]);
                    j++;
                }
                operands = array[startIndex - 1] + String.valueOf(array[j]);
                String result = Arrays.toString(result(operands, temp.toString()));
                newPhrase.append(calculation.evaluate(result));
                temp = new StringBuilder();
                i = j + 1;
            }
            if (i != array.length)
                newPhrase.append(array[i]);
        }
        return newPhrase.toString();
    }

    public static boolean isCharacter(char bit) {
        return (bit >= 'a' && bit <= 'z');
    }

    /**
     *
     * @param operands two operands that we want to operate on them with new operator
     * @return the simple phrase with numbers
     */
    public char[] result(String operands, String temp) {
        char[] operandsArray = operands.toCharArray();
        int k = 0;
        String operation = findTheOperation(temp);
        char[] array = operation.toLowerCase().toCharArray();
        if (!operation.equals("NotFound")) {
            for (int i = 0; i < array.length; i++) {
                if (isCharacter(array[i])) {
                    array[i] = operandsArray[k];
                    k++;
                }
            }
        }
        return array;
    }

    /**
     *
     * @param temp name of operator in input
     * @return the operation of new intended operator
     */
    public String findTheOperation(String temp) {
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).getName().equals(temp))
                return operators.get(i).getOperation();
        }
        return "NotFound";
    }


    public void print() {
        for (NewOperator a : operators) {
            System.out.println(a);

        }
    }

    /**
     *
     * @param phrase input string
     * @return if the input string contain any of created operator or not
     */
    public boolean containNewOperator(String phrase) {
        for (NewOperator operator : operators) {
            if (phrase.contains(operator.getName()))
                return true;
        }
        return false;
    }


}
