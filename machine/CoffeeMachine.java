package machine;

public class CoffeeMachine {


    private int water = 400;
    private int milk = 540;
    private int coffeeBeans = 120;
    private int disposableCups = 9;
    private int money = 550;
    private States state = States.CHOOSING_ACTION;

    {
        printQuestion();
    }


    public void input(String action) throws Exception {
        switch (state) {
            case CHOOSING_ACTION:
                action(action);
                break;
            case CHOOSING_COFFEE:
                buy(action);
                break;
            default:
                fill(action);
        }
    }

    private void action(String action) throws Exception {

        switch (action) {
            case "buy":
                state = States.CHOOSING_COFFEE;
                printQuestion();
                break;
            case "fill":
                state = States.FILLING_WATER;
                printQuestion();
                break;
            case "take":
                take();
                printQuestion();
                break;
            case "remaining":
                writeInfo();
                printQuestion();
                break;
            case "exit":
                throw new Exception();
            default:
                System.out.println("Incorrect input!");
                printQuestion();
        }
    }


    private void take() {
        System.out.printf("I gave you $%d\n\n", money);
        money = 0;
    }

    private void fill(String quantity) {

        switch (state) {
            case FILLING_WATER:
                try {
                    water += Integer.parseInt(quantity);
                } catch (NumberFormatException e) {
                    System.out.println("It's not a number!\n");
                }
                state = States.FILLING_MILK;
                printQuestion();
                break;
            case FILLING_MILK:
                try {
                    milk += Integer.parseInt(quantity);
                } catch (NumberFormatException e) {
                    System.out.println("It's not a number!\n");
                }
                state = States.FILLING_COFFEE_BEANS;
                printQuestion();
                break;
            case FILLING_COFFEE_BEANS:
                try {
                    coffeeBeans += Integer.parseInt(quantity);
                } catch (NumberFormatException e) {
                    System.out.println("It's not a number!\n");
                }
                state = States.FILLING_DISPOSABLE_CUPS;
                printQuestion();
                break;
            case FILLING_DISPOSABLE_CUPS:
                try {
                    disposableCups += Integer.parseInt(quantity);
                } catch (NumberFormatException e) {
                    System.out.println("It's not a number!\n");
                }
                state = States.CHOOSING_ACTION;
                printQuestion();
                break;
        }
    }

    private void buy(String action) {

        Coffee coffee = null;

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
                printQuestion();
                return;
            default:
                System.out.println("Incorrect input!");
                printQuestion();
        }

        if (coffee != null) {
            cook(coffee);
            state = States.CHOOSING_ACTION;
            printQuestion();
        }
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

    private void printQuestion() {

        switch (state) {
            case CHOOSING_ACTION:
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                break;
            case CHOOSING_COFFEE:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino," +
                        " back - to main menu: ");
                break;
            case FILLING_WATER:
                System.out.println("Write how many ml of water do you want to add: ");
                break;
            case FILLING_MILK:
                System.out.println("Write how many ml of milk do you want to add: ");
                break;
            case FILLING_COFFEE_BEANS:
                System.out.println("Write how many grams of coffee beans do you want to add: ");
                break;
            case FILLING_DISPOSABLE_CUPS:
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                break;
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
