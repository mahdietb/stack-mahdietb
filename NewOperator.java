import java.util.Scanner;

public class NewOperator {
    private String name;
    private String operation;

    public NewOperator(String name, String operation) {
        this.name = name;
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public String getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return "NewOperator{" +
                "name='" + name + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
