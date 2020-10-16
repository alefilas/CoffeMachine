import machine.CoffeeMachine;

import java.util.Scanner;

public class Main {

static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (true) {

            String action = scanner.next();
            if (action.equals("exit")){
                break;
            }
            coffeeMachine.input(action);

        }
    }
}
