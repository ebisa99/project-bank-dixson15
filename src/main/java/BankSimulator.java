import DAO.AccountDAO;
import DAO.AccountOperationManagerDAO;
import DAO.BankTransactionDAO;
import controller.AccountController;
import controller.AccountOperationManagerController;
import controller.CustomerController;
import model_entity.*;

import java.util.Scanner;

public class BankSimulator {

    private static  Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

        displayInfo();
    }

    private static void displayInfo(){
        System.out.println("\n\n\t----WELCOME TO DICKSON BANK----");
        //Scanner in = new Scanner(System.in);
        try
        {
            while(true)
            {
                System.out.println("1.CUSTOMER\t2.BANKER \n3.EXIT");
                int ch = in.nextInt();
                switch(ch)
                {
                    case 1:
                        promptCustomer();
                        break;

                    case 2:
                        promptBanker();
                        break;

                    case 3:
                        System.exit(0);
                        break;

                    default: System.out.println("Invalid Option");
                            break;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("SELF THROWN EXCEPTION IS--->"+e);
        }
    }

    private static void promptCustomer() {

        System.out.println("--------------CUSTOMER SERVICE------------");
            try {
                while (true) {
                    System.out.println("1.APPLY FOR BANK ACCOUNT\n2.LOGIN \n3.EXIT");
                    int ch = in.nextInt();
                    switch (ch) {
                        case 1:
                            applyforAccount();
                            break;

                        case 2:
                            loginToAccount();
                            break;

                        case 3:
                            displayInfo();
                            break;

                        default:
                            System.out.println("Invalid Option");
                    }
                }
            } catch (Exception e) {
                System.out.println("SELF THROWN EXCEPTION IS--->" + e);
            }
        }

    private static void loginToAccount() {


    }

    private static void applyforAccount() {

        Account account = null;
        BankTransaction bankTransaction = new BankTransaction();

        System.out.println("CUSTOMER ID#:" );
        int custID = in.nextInt();
        System.out.println("First name: ");
        String first = in.next();
        System.out.println("Last name: ");
        String last = in.next();

        Customer customer = new Customer(custID,first,last);
        CustomerController customerController = new CustomerController();
        customerController.saveCustomer(customer);


        System.out.println();
        System.out.println("ACCOUNT ID#:");
        int accID = in.nextInt();
        System.out.println("ACCOUNT NUMBER :" );
        long accN = in.nextLong();
        System.out.println("ACCOUNT BALANCE :" );
        double accB = in.nextDouble();

        System.out.println("ACCOUNT TYPE :1. SAVING \t 2. CHECKING" );
        int accT = in.nextInt();

       switch (accT){
           case 1:
               account = new SavingAccount(accID,customer,accN,accB);
               break;
           case 2:
               account = new CheckingAccount(accID,customer,accN,accB);
               break;
           default:
               System.out.println("Undefined account!!");
               break;
       }

        Banker banker = new Banker(2,"Bolowa","Bokul","BANK_TELLER");

        AccountOperationManagerController accountOperationManagerController = new AccountOperationManagerController();
        System.out.println("Credit score: ");

        int credit = in.nextInt();
        System.out.println(accountOperationManagerController.createCustomerAccount(banker,account,credit));
        AccountOperationManagerDAO accountOperationManagerDAO = new AccountOperationManagerDAO();


        System.out.println("R for Review:" );
        String review = in.next().toUpperCase();

        switch (review){
            case "R":
                accountOperationManagerDAO.getAccountsforReview();
                break;
            default:
                System.out.println("Undefined account!!");
                break;
        }

        System.out.println("A for Review:" );
        String review2 = in.next().toUpperCase();

        switch (review2){
            case "A":
                accountOperationManagerDAO.approveAccount(account);
                break;
            default:
                System.out.println("Undefined account!!");
                break;
        }
        //accountOperationManagerDAO.approveAccount(account);
        System.out.println();

    }

    private static void promptBanker() {

        System.out.println("--------------BANK EMPLOYEE SERVICE------------");

        try {
            while (true) {
                System.out.println("1.REVIEW ACCOUNT FOR APPROVAL \n2.APPROVE/REJECT \n3.VIEW ALL TRANSACTIONS LOG\n4.VIEW ALL TRANSACTIONS LOG\n5.EXIT. ");

                int ch = in.nextInt();
                switch (ch) {
                    case 1:
                        rewiewAccount();
                        break;
                    case 2:
                        approveAccount();
                        break;
                    case 3:
                        break;

                    case 4:
                        break;
                    case 5:
                        displayInfo();
                        break;
                    default:
                        System.out.println("Invalid Option");
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR OCCUR!!!!!" + e);
        }
    }

    private static void rewiewAccount() {
       System.out.println("___________VIEW ACCOUNT FOR APPROVAL__________");

        try {
            AccountOperationManagerDAO accountOperationManagerDAO = new AccountOperationManagerDAO();
            accountOperationManagerDAO.getAccountsforReview();
        }
        catch(Exception e){
            System.out.println("ERROR OCCUR!!!!!" + e);
        }
    }

    private static void approveAccount() {

        System.out.println("___________VIEW ACCOUNT FOR APPROVAL__________");

        try {

            AccountOperationManagerDAO accountOperationManagerDAO = new AccountOperationManagerDAO();
           // accountOperationManagerDAO.approveAccount();

        }
        catch(Exception e){
            System.out.println("ERROR OCCUR!!!!!" + e);
        }
    }
}