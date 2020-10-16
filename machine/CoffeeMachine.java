package machine;


import java.util.Scanner;

public class CoffeeMachine {


    private int water = 400;
    private int milk = 540;
    private int coffeeBeans = 120;
    private int disposableCups = 9;
    private int money = 550;
    private States state = States.CHOOSING_ACTION;

    {
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
    }


    public void input(String action) {
        switch (state) {
            case CHOOSING_ACTION:
                action(action);
                break;
            case CHOOSING_COFFEE:
                buy(action);
                break;
            case FILLING_WATER:
                water += Integer.parseInt(action);
                System.out.println("Write how many ml of milk do you want to add: ");
                state = States.FILLING_MILK;
                break;
            case FILLING_MILK:
                milk += Integer.parseInt(action);
                System.out.println("Write how many grams of coffee beans do you want to add: ");
                state = States.FILLING_COFFEE_BEANS;
                break;
            case FILLING_COFFEE_BEANS:
                coffeeBeans += Integer.parseInt(action);
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                state = States.FILLING_DISPOSABLE_CUPS;
                break;
            case FILLING_DISPOSABLE_CUPS:
                disposableCups += Integer.parseInt(action);
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                state = States.CHOOSING_ACTION;
                break;
        }
    }

    public void action(String action) {

        switch (action) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino," +
                        " back - to main menu: ");
                state = States.CHOOSING_COFFEE;
                break;
            case "fill":
                System.out.println("Write how many ml of water do you want to add: ");
                state = States.FILLING_WATER;
                break;
            case "take":
                take();
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                break;
            case "remaining":
                writeInfo();
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                break;
        }
    }


    private void take() {
        System.out.printf("I gave you $%d\n\n", money);
        money = 0;
    }

    private void buy(String action) {

        Coffee coffee;

        switch (action) {
            case "1":
                coffee = Coffee.ESPRESSO;
                break;
            case "2":
                coffee = Coffee.LATTE;
                break;
            case "3":
                coffee = Coffee.CAPPUCCINO;
                break;
            case "back":
                state = States.CHOOSING_ACTION;
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                return;
            default:
                throw new IllegalStateException("Unexpected value: ");
        }

        cook(coffee);
        state = States.CHOOSING_ACTION;
        System.out.println("Write action (buy, fill, take, remaining, exit): ");

    }

    private void cook(Coffee coffee) {

        if (disposableCups < 1 || coffee.getWater() > water || coffee.getMilk() > milk ||
                coffee.getCoffeeBeans() > coffeeBeans) {
            System.out.println("I have enough resources, making you a coffee!\n");
        } else {
            disposableCups -= 1;
            water -= coffee.getWater();
            milk -= coffee.getMilk();
            coffeeBeans -= coffee.getCoffeeBeans();
            money += coffee.getMoney();
        }

    }


    private void writeInfo() {

        System.out.printf("The coffee machine has:\n" +
                        "%d of water\n" +
                        "%d of milk\n" +
                        "%d of coffee beans\n" +
                        "%d of disposable cups\n" +
                        "%d of money\n\n", water, milk, coffeeBeans,
                disposableCups, money);
    }

}
