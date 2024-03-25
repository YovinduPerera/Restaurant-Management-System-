import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class FoodQueue {
    static WaitingList waitingList = new WaitingList();
    static int BURGER_STOCK = 50;

    static Customer[] CASHIER_1 = new Customer[2]; // queue1
    static Customer[] CASHIER_2 = new Customer[3]; // queue2
    static Customer[] CASHIER_3 = new Customer[5]; // queue3
    static Customer[][] CUSTOMER_POSITION = new Customer[3][];

    static {
        CUSTOMER_POSITION[0] = CASHIER_1;
        CUSTOMER_POSITION[1] = CASHIER_2;
        CUSTOMER_POSITION[2] = CASHIER_3;
    }

    // method to view all queues
    static void VIEW_ALL_QUEUE() {
        System.out.println("");
        System.out.println("*****************");
        System.out.println("*    CASHIER    *");
        System.out.println("*****************");

        int maxCashierLength = 5;

        for (int i = 0; i < maxCashierLength; i++) {
            for (int j = 0; j < 3; j++) {
                Customer[] cashier = getCashier(j);
                if (i < cashier.length) {
                    if (cashier[i] == null || cashier[i].isServed()) {
                        System.out.print(" X    ");
                    } else {
                        System.out.print(" O    ");
                    }
                } else {
                    System.out.print("      ");
                }
            }
            System.out.println();
        }
        System.out.println();
        printCustomerNames();      /////////////////////////////////////
    }


    // method to view empty queues
    static void VIEW_EMPTY_QUEUE() {
        System.out.println("*****************");
        System.out.println("*    CASHIER    *");
        System.out.println("*****************");

        boolean queue1Empty = isQueueEmpty(CASHIER_1);
        boolean queue2Empty = isQueueEmpty(CASHIER_2);
        boolean queue3Empty = isQueueEmpty(CASHIER_3);

        if (queue1Empty) {
            for (int i = 0; i < CASHIER_1.length; i++) {
                if (CASHIER_1[i] != null && !CASHIER_1[i].isServed()) {
                    System.out.printf("Queue 1 position %d is full%n", i + 1);
                } else {
                    System.out.printf("Queue 1 position %d is empty%n", i + 1);
                }
            }
        } else {
            for (int i = 0; i < CASHIER_1.length; i++) {
                if (CASHIER_1[i] != null && !CASHIER_1[i].isServed()) {
                    System.out.printf("Queue 1 position %d is full%n", i + 1);
                } else {
                    System.out.printf("Queue 1 position %d is empty%n", i + 1);
                }
            }
        }

        if (queue2Empty) {
            for (int i = 0; i < CASHIER_2.length; i++) {
                if (CASHIER_2[i] != null && !CASHIER_2[i].isServed()) {
                    System.out.printf("Queue 2 position %d is full%n", i + 1);
                } else {
                    System.out.printf("Queue 2 position %d is empty%n", i + 1);
                }
            }
        } else {
            for (int i = 0; i < CASHIER_2.length; i++) {
                if (CASHIER_2[i] != null && !CASHIER_2[i].isServed()) {
                    System.out.printf("Queue 2 position %d is full%n", i + 1);
                } else {
                    System.out.printf("Queue 2 position %d is empty%n", i + 1);
                }
            }
        }

        if (queue3Empty) {
            for (int i = 0; i < CASHIER_3.length; i++) {
                if (CASHIER_3[i] != null && !CASHIER_3[i].isServed()) {
                    System.out.printf("Queue 3 position %d is full%n", i + 1);
                } else {
                    System.out.printf("Queue 3 position %d is empty%n", i + 1);
                }
            }
        } else {
            for (int i = 0; i < CASHIER_3.length; i++) {
                if (CASHIER_3[i] != null && !CASHIER_3[i].isServed()) {
                    System.out.printf("Queue 3 position %d is full%n", i + 1);
                } else {
                    System.out.printf("Queue 3 position %d is empty%n", i + 1);
                }
            }
        }
    }

    // Check if the queue is empty
    static boolean isQueueEmpty(Customer[] cashier) {
        for (Customer customer : cashier) {
            if (customer != null && !customer.isServed()) {
                return false;
            }
        }
        return true;
    }

    // Get the length of a specific cashier queue
    static int getCashierLength(int queueNumber) {
        switch (queueNumber) {
            case 0:
                return CASHIER_1.length;
            case 1:
                return CASHIER_2.length;
            case 2:
                return CASHIER_3.length;
            default:
                return 0;
        }
    }

    // use in AddCustomer method,RemoveCustomer, and RemoveServedCustomer to check how many customers are in the queue
    static int COUNT_OF_CUSTOMER(Customer[] cashier) {
        int count = 0;
        for (Customer customer : cashier) {
            if (customer != null && !customer.isServed()) {
                count++;
            }
        }
        return count;
    }

    static void ADD_CUSTOMER() {
        Scanner input = new Scanner(System.in);

        System.out.print("PLEASE ENTER THE CUSTOMER FIRST NAME....:");
        String firstName = input.next();
        System.out.println("PLEASE ENTER THE CUSTOMER SECOND NAME....:");
        String secondName = input.next();
        System.out.println("PLEASE ENTER THE AMOUNT OF BURGERS....:");
        int burgerAmount = input.nextInt();
        System.out.print("WHAT IS THE QUEUE NUMBER YOU WANT TO ADD FROM (1, 2, 3): ");
        int queueNumber = input.nextInt();

        Customer customer = new Customer(firstName, secondName, burgerAmount);

        int cashierIsFull = 0;
        Customer[] cashier;
        int cashierIndex = queueNumber - 1;

        if (cashierIndex >= 0 && cashierIndex < 3) {
            cashier = getCashier(cashierIndex);
            cashierIsFull = COUNT_OF_CUSTOMER(cashier);

            if (cashierIsFull < getCashierLength(cashierIndex)) {
                for (int i = 0; i < cashier.length; i++) {
                    if (cashier[i] == null || cashier[i].isServed()) {
                        cashier[i] = customer;
                        customer.setServed(false);
                        System.out.println("Customer " + customer.getFull_NAME() + " is added"); ///////////////////////////////////
                        break;
                    }
                }
                BURGER_STOCK -= burgerAmount; // Deduct the burger amount from stock
            } else {
                System.out.println("");
                System.out.println("QUEUE IS FULL....PLEASE WAIT!");
                System.out.println("Waiting is is loading");
                waitingList.add(customer);
                System.out.println("Customer added to the waiting list.");
            }
        } else {
            System.out.println("");
            System.out.println("INVALID QUEUE NUMBER");
            System.out.println("");
        }
    }

    static Customer[] getCashier(int cashierIndex) {
        switch (cashierIndex) {
            case 0:
                return CASHIER_1;
            case 1:
                return CASHIER_2;
            case 2:
                return CASHIER_3;
            default:
                return null;
        }
    }

    static void printCustomerNames() {
        System.out.println("******************");
        System.out.println("* CUSTOMER NAMES *");
        System.out.println("******************");

        for (Customer[] cashier : CUSTOMER_POSITION) {
            for (Customer customer : cashier) {
                if (customer != null && !customer.isServed()) {
                    System.out.println(customer.getFull_NAME());
                }
            }
        }
        System.out.println();
    }

    // method to remove a customer in a specific location
    static void REMOVE_CUSTOMER() {
        Scanner INPUT_2 = new Scanner(System.in);

        System.out.print("ENTER THE QUEUE NUMBER OF CUSTOMER THAT YOU WANT TO REMOVE (1,2,3) : ");
        int WHAT_QUEUE = INPUT_2.nextInt();

        System.out.print("WHICH CUSTOMER (PLEASE ENTER POSITION NUMBER OF CUSTOMER):");
        int WHICH_CUSTOMER = INPUT_2.nextInt();

        Customer[] cashier;
        int cashierIndex = WHAT_QUEUE - 1;

        if (cashierIndex >= 0 && cashierIndex < 3) {
            cashier = getCashier(cashierIndex);

            if (cashier[WHICH_CUSTOMER - 1] != null && !cashier[WHICH_CUSTOMER - 1].isServed()) {
                int burgerAmount = cashier[WHICH_CUSTOMER - 1].getREQUIRED_BURGER_AMOUNT(); // Get the required burger amount
                cashier[WHICH_CUSTOMER - 1] = null;
                System.out.println("Customer is removed");
                BURGER_STOCK += burgerAmount; // Add the burger amount back to the stock
            } else {
                System.out.println("Invalid customer position or customer is already served");
            }
        } else {
            System.out.println("");
            System.out.println("INVALID QUEUE NUMBER");
            System.out.println("");
        }

        VIEW_ALL_QUEUE();
    }

    static void REMOVE_SERVED_CUSTOMERS() {
        BURGER_STOCK -= 5;

        Scanner INPUT_3 = new Scanner(System.in);

        System.out.print("ENTER THE QUEUE NUMBER OF CUSTOMER THAT YOU WANT TO REMOVE (1,2,3) : ");
        int WHAT_QUEUE = INPUT_3.nextInt();

        int COUNT_OF_CUSTOMER = 0;
        Customer[] cashier;
        int cashierIndex = WHAT_QUEUE - 1;

        if (cashierIndex >= 0 && cashierIndex < 3) {
            cashier = getCashier(cashierIndex);
            COUNT_OF_CUSTOMER = COUNT_OF_CUSTOMER(cashier);

            if (COUNT_OF_CUSTOMER != 0) {
                for (int i = 0; i < cashier.length - 1; i++) {
                    cashier[i] = cashier[i + 1];
                }
                cashier[cashier.length - 1] = null;
                System.out.println("Customers are removed");
                Customer nextCustomer = waitingList.remove();
                if (nextCustomer != null) {
                    cashier[cashier.length - 1] = nextCustomer;
                    nextCustomer.setServed(false);
                    System.out.println("Customers are removed and replaced with the next customer from the waiting list.");
                }


            } else {
                System.out.println("Empty customers");
            }
        } else {
            System.out.println("INVALID QUEUE NUMBER");
        }

    }

    static void ViewCustomers() {
        System.out.println("********** CUSTOMER QUEUE **********");

        // Create a temporary array to store all customer names
        String[] allCustomers = new String[countAllCustomers()];

        int index = 0;
        for (Customer[] cashier : CUSTOMER_POSITION) {
            for (Customer customer : cashier) {
                if (customer != null && !customer.isServed()) {
                    allCustomers[index] = customer.getFull_NAME();
                    index++;
                }
            }
        }

        // Perform bubble sort to sort customer names alphabetically
        for (int i = 0; i < allCustomers.length - 1; i++) {
            for (int j = 0; j < allCustomers.length - i - 1; j++) {
                if (allCustomers[j].compareToIgnoreCase(allCustomers[j + 1]) > 0) {
                    // Swap the customer names
                    String temp = allCustomers[j];
                    allCustomers[j] = allCustomers[j + 1];
                    allCustomers[j + 1] = temp;
                }
            }
        }

        // Print the sorted customer names
        for (String customer : allCustomers) {
            System.out.println(customer);
        }
    }

    static int countAllCustomers() {
        int count = 0;
        for (Customer[] cashier : CUSTOMER_POSITION) {
            for (Customer customer : cashier) {
                if (customer != null && !customer.isServed()) {
                    count++;
                }
            }
        }
        return count;
    }

    static void COUNT_OF_BURGERS() {

        System.out.println(BURGER_STOCK + "-BURGERS ARE REMAIN");

    }

    static void BURGER_ADDING() {
        Scanner input = new Scanner(System.in);

        System.out.print("ENTER THE NUMBER OF BURGERS TO ADD: ");
        int burgerCount = input.nextInt();

        BURGER_STOCK += burgerCount; // Add the specified number of burgers to the stock

        System.out.println("Burgers added successfully!");
        System.out.println("Updated burger stock: " + BURGER_STOCK);
    }

    static void StoreProgramData() {
        try {
            // Create a FileWriter object to write data to a file
            FileWriter writer = new FileWriter("program_data1.txt");

            String output1 = formatArray("Customer Position 1", CASHIER_1);
            String output2 = formatArray("Customer Position 2", CASHIER_2);
            String output3 = formatArray("Customer Position 3", CASHIER_3);

            writer.write(output1);
            writer.write(output2);
            writer.write(output3);
            writer.write("BURGER_STOCK: " + BURGER_STOCK + "\n");
            // Close the FileWriter
            writer.close();
            System.out.println("Program data has been stored successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while storing the program data: " + e.getMessage());
        }
    }

    public static String formatArray(String position, Customer[] array) {
        StringBuilder result = new StringBuilder();
        result.append(position).append(":\n");
        for (Customer customer : array) {
            if (customer != null) {
                result.append(customer.getFull_NAME()).append("\n");
            }
        }
        return result.toString();
    }

    static void LoadProgramData() {
        try {
            // Create a FileReader object to read data from the file
            FileReader reader = new FileReader("program_data1.txt");
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            // Close the FileReader
            scanner.close();
            System.out.println("Program data has been loaded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading the program data: " + e.getMessage());
        }
    }

    static void IncomeInQueues() {
        int queue1Income = calculateQueueIncome(CASHIER_1);
        int queue2Income = calculateQueueIncome(CASHIER_2);
        int queue3Income = calculateQueueIncome(CASHIER_3);

        System.out.println("Income in Queue 1: $" + queue1Income);
        System.out.println("Income in Queue 2: $" + queue2Income);
        System.out.println("Income in Queue 3: $" + queue3Income);
    }

    static int calculateQueueIncome(Customer[] queue) {
        int income = 0;
        for (Customer customer : queue) {
            if (customer != null && !customer.isServed()) {
                int burgerAmount = customer.getREQUIRED_BURGER_AMOUNT();
                income += burgerAmount * 650; // Assuming $650 per burger
            }
        }
        return income;
    }

    public static class Customer {
        private String FIRST_NAME;
        private String SECOND_NAME;
        private int REQUIRED_BURGER_AMOUNT;
        private boolean served;

        public Customer(String firstName, String secondName, int requiredBurgerAmount) {
            this.FIRST_NAME = firstName;
            this.SECOND_NAME = secondName;
            this.REQUIRED_BURGER_AMOUNT = requiredBurgerAmount;
            this.served = true;
        }

        public int getREQUIRED_BURGER_AMOUNT() {
            return REQUIRED_BURGER_AMOUNT;
        }

        public boolean isServed() {
            return served;
        }

        public void setServed(boolean served) {
            this.served = served;
        }

        public String getFull_NAME() {
            return FIRST_NAME + " " + SECOND_NAME;
        }
    }

    public static class WaitingList {
        private static final int MAX_SIZE = 10;
        private Customer[] queue;
        private int front;
        private int rear;
        private int count;

        public WaitingList() {
            queue = new Customer[MAX_SIZE];
            front = 0;
            rear = -1;
            count = 0;
        }

        public boolean isFull() {
            return count == MAX_SIZE;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public void add(Customer customer) {
            if (!isFull()) {
                rear = (rear + 1) % MAX_SIZE;
                queue[rear] = customer;
                count++;
            } else {
                System.out.println("Waiting List is full. Customer cannot be added.");
            }
        }

        public Customer remove() {
            if (!isEmpty()) {
                Customer customer = queue[front];
                queue[front] = null;
                front = (front + 1) % MAX_SIZE;
                count--;
                return customer;
            } else {
                System.out.println("Waiting List is empty.");
                return null;
            }
        }

        public Customer peek() {
            if (!isEmpty()) {
                return queue[front];
            } else {
                System.out.println("Waiting List is empty.");
                return null;
            }
        }
    }
}
