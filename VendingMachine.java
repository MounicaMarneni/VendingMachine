import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VendingMachine {
    public static void main(String[] args) {
        boolean machineStrted = true;
        String newLine = System.getProperty("line.separator");
        String coinsMenu = "1: 1 Cent" + newLine + "2: 5 Cents" + newLine + "3: 10 Cents" + newLine + "4: 25 Cents";
        String itemsMenu = "1: Coke(25 Cents)" + newLine + "2: Pepsi(35 Cents)" + newLine + "3: Soda(45 Cents)";
        String itemsAvailable = "Coke(25 Cents), Pepsi(35 Cents), Soda(45 Cents)";
        String insertMore = "Insert more coins ?" + newLine + "1: Yes" + newLine + "2: No";
        String confirmMenu = "Confirm Order ?" + newLine + "1: Yes" + newLine + "2: Cancel" + newLine + "3: Reset";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String itemIndex = "";
        while(machineStrted) {
            clearScreen();
            String selectedItem = "";
            Integer totalAmount = 0;
            System.out.println("Welcome to Vending Machine!");
            System.out.println("Items available: " + itemsAvailable);
            boolean coinsInserted = false;
            boolean insufficiantBalance = false;
            while(!coinsInserted) {
                System.out.println("Amount inserted: " + totalAmount);
                System.out.println("Insert Coins...");
                System.out.println(coinsMenu);
                System.out.println("Your Selection: ");
                String coinIndex = null;
                try {
                    coinIndex = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch (coinIndex) {
                    case "1":
                        totalAmount = totalAmount + 1;
                        break;
                    case "2":
                        totalAmount = totalAmount + 5;
                        break;
                    case "3":
                        totalAmount = totalAmount + 10;
                        break;
                    case "4":
                        totalAmount = totalAmount + 25;
                        break;
                    default:
                        totalAmount = totalAmount + 0;
                        break;
                }
                System.out.println(insertMore);
                System.out.println("Your Selection: ");
                String insertMoreChoise = null;
                try {
                    insertMoreChoise = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if("2".equals(insertMoreChoise)) {
                    coinsInserted = true;
                }
            }
            while (true) {
                clearScreen();
                System.out.println("Amount inserted: " + totalAmount);
                System.out.println("Insert Item of Choise...");
                System.out.println(itemsMenu);
                boolean itemInserted = false;
                boolean correctItemInserted = true;
                while (!itemInserted) {
                    if (!correctItemInserted)
                        System.out.println("Insert correct item...");
                    System.out.println("Your Selection: ");
                    try {
                        itemIndex = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (!"1".equals(itemIndex) && !"2".equals(itemIndex) && !"3".equals(itemIndex)) {
                        correctItemInserted = false;
                    } else {
                        itemInserted = true;
                        switch (itemIndex) {
                            case "1":
                                if(totalAmount < 25)
                                    insufficiantBalance = true;
                                selectedItem = "Coke";
                                break;
                            case "2":
                                if(totalAmount < 35)
                                    insufficiantBalance = true;
                                selectedItem = "Pepsi";
                                break;
                            case "3":
                                if(totalAmount < 45)
                                    insufficiantBalance = true;
                                selectedItem = "Soda";
                                break;
                        }
                        if(insufficiantBalance) {
                            break;
                        }
                    }
                }
                if(insufficiantBalance) {
                    String brokenChange = breakChange(totalAmount);
                    System.out.println("Insufficiant funds inserted for the item!");
                    System.out.println("Here is your returned amount:" + brokenChange);
                    System.out.println("Bye....!");
                    System.out.println("Press enter to continue...!");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                clearScreen();
                System.out.println("Amount inserted: " + totalAmount);
                System.out.println("Item selected: " + selectedItem);
                System.out.println(confirmMenu);
                System.out.println("Your Selection: ");
                String confirmChoice = null;
                try {
                    confirmChoice = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if ("1".equals(confirmChoice)) {
                    Integer resultAmount = 0;
                    if("1".equals(itemIndex)) {
                        resultAmount = totalAmount - 25;
                    }
                    if("2".equals(itemIndex)) {
                        resultAmount = totalAmount - 35;
                    }
                    if("3".equals(itemIndex)) {
                        resultAmount = totalAmount - 45;
                    }
                    String brokenChange = breakChange(resultAmount);
                    System.out.println("Here is your selected Item:" + selectedItem);
                    System.out.println("Here is your balance" + brokenChange);
                    System.out.println("Press enter to continue...!");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                } else if ("2".equals(confirmChoice)) {
                    String brokenChange = breakChange(totalAmount);
                    System.out.println("Transaction cancled!");
                    System.out.println("Here is your returned amount:" + brokenChange);
                    System.out.println("Bye....!");
                    System.out.println("Press enter to continue...!");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if ("3".equals(confirmChoice)) {
                    selectedItem = "";
                } else {
                    selectedItem = "";
                }
            }
        }
    }

    static String breakChange(Integer amount) {
        String result = "";
        Integer balance = amount;
        if(balance / 25 != 0) {
            result = result +  "(25 Cents) X " + balance/25;
            balance = balance % 25;
        }
        if(balance / 10 != 0) {
            result = result +  "(10 Cents) X " + balance/10;
            balance = balance % 10;
        }
        if(balance / 5 != 0) {
            result = result +  "(5 Cents) X " +balance/5;
            balance = balance % 5;
        }
        result = result +  "(1 Cents) X " + balance;
        return result;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
