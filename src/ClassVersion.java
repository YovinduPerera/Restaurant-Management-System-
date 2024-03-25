
import java.util.Scanner;

public class ClassVersion {
    static boolean RUN_PROGRAMME = true;
    static void EXIT() {
        RUN_PROGRAMME = false;
    }
    //Method for select item from the menue
    static void MENU() {

        Scanner input = new Scanner(System.in);

        System.out.println("--------------------WELCOME TO THE SHOP---------------------");
        System.out.print("PLEASE ENTER THE NUMBER OR CODE WHAT YOU WANT TO DO : ");
        String object = input.next();

        switch (object) {

            case "100":
            case "VFQ":
                FoodQueue.VIEW_ALL_QUEUE();
                break;
            case "101":
            case "VEQ":
                FoodQueue.VIEW_EMPTY_QUEUE();
                break;
            case "102":
            case "ACQ":
                FoodQueue.ADD_CUSTOMER();
                break;
            case "103":
            case "RCQ":
                FoodQueue.REMOVE_CUSTOMER();
                break;
            case "104":
            case "PCQ":
                FoodQueue.REMOVE_SERVED_CUSTOMERS();
                break;
            case "105":
            case "VCS":
                FoodQueue.ViewCustomers();
                break;
            case "106":
            case "SPD":
                FoodQueue.StoreProgramData();
                break;
            case "107":
            case "LPD":
                FoodQueue.LoadProgramData();
                break;
            case "108":
            case "STK":
                FoodQueue.COUNT_OF_BURGERS();
                break;
            case "109":
            case "AFS":
                FoodQueue.BURGER_ADDING();
                break;
            case "110":
            case "IFQ":
                FoodQueue.IncomeInQueues();
                break;
            case "999":
            case "EXITt":
                EXIT();
                break;
            default:
                System.out.println("Wrong Code! ");
        }


    }


    public static void main(String[] args) {


        System.out.println("");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+FOODIES FAVE QUEUE MANAGEMENT SYSTEM+-+-+-+-+-+-+-+-");
        System.out.println("");
        System.out.println("--------------------------MENU------------------------------");
        System.out.println("  100 or VFQ: View all Queues.                              ");
        System.out.println("  101 or VEQ: View all Empty Queues.                        ");
        System.out.println("  102 or ACQ: Add customer to a Queue.                      ");
        System.out.println("  103 or RCQ: Remove a customer from a Queue.               ");
        System.out.println("  104 or PCQ: Remove a served customer.                     ");
        System.out.println("  105 or VCS: View Customers Sorted in alphabetical order.  ");
        System.out.println("  106 or SPD: Store Program Data into file.                 ");
        System.out.println("  107 or LPD: Load Program Data from file.                  ");
        System.out.println("  108 or STK: View Remaining burgers Stock.                 ");
        System.out.println("  109 or AFS: Add burgers to Stock.                         ");
        System.out.println("  999 or EXT: Exit the Program.                             ");
        System.out.println("");

        while (RUN_PROGRAMME) {
            MENU();
        }
    }
}
