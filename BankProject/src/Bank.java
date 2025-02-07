import java.util.ArrayList;
import java.util.Scanner;

public class Bank{

    public static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>(); // accounts are stored
    // on accounts ArrayList.

    //This method add a given account to our bank
    public static void addAccountsToBank(BankAccount newAcc){
        accounts.add(newAcc);
    }

    public static void displayMenu(){  // use this method to display available operations to customer.
        System.out.println("-------------------Menu--------------------");
        System.out.println("1- Display Balance\n" +
                "2- Withdraw money\n" +
                "3- Transfer money to another account\n" +
                "4- Deposit money\n" +
                "5- Change daily withdrawal limit(only if you are not student)\n" +
                "6- Change your password \n" +
                "0- Enter 0 to exit.");
        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        // Creating account's owner and card
        Customer customerOne = new Student(21, "Dorukhan Ozgur", false);
        BankCard cardOne = new BankCard("1-1X", "1111", 3000);

        Customer customerTwo = new Worker(42, "John Doe", true, 1850);
        BankCard cardTwo = new BankCard("2-2Y", "2222", 8792);

        Customer customerThree = new Unemployed(67, "Chris Smith", true, 1400);
        BankCard cardThree = new BankCard("3-3Z", "3333", 5670);

        Customer customerFour = new Student(20, "Jessie Jade", false);
        BankCard cardFour = new BankCard("4-4W", "4444", 12987);

        // Creating bank accounts with given data (now our demo bank only has 3 accounts)
        BankAccount accountOne = new BankAccount(cardOne, customerOne);
        BankAccount accountTwo = new BankAccount(cardTwo, customerTwo);
        BankAccount accountThree = new BankAccount(cardThree, customerThree);
        BankAccount accountFour = new BankAccount(cardFour, customerFour);

        // Add the accounts to our arraylist that stores available accounts
        addAccountsToBank(accountOne);
        addAccountsToBank(accountTwo);
        addAccountsToBank(accountThree);
        addAccountsToBank(accountFour);

        BankAccount chosenAcc = null;

        System.out.println("Welcome to our bank. In order to continue," +
                " you must enter your account information correctly. ");

        System.out.print("Enter your bank id: ");
        String id = keyboard.nextLine();

        int counter = 0;  // for checking if customer has an account on our bank. (checks id first)

        for(BankAccount acc : accounts){   // check all account id on this bank (they are stored on accounts ArrayList.
                                                        //get the data from this arraylist. and check their id's)
            if(id.equals(acc.getCard().getAccountId())){
                chosenAcc = acc;
                counter++; // account exists on our bank.
            }
        }

        if(counter == 0){  // id's given account does not exist
            System.out.println("You do not have an account on our bank. " +
                    "Please open an account or check the identification information you entered and try again" +
                    "The system will be closed......");
            System.exit(0);
        }

        // Inform customer that he/she has only 3 chance to enter the password correctly
        System.out.println("Welcome " + chosenAcc.getOwner().getName());
        System.out.println("You have three chances to enter the correct password to log in.");
        System.out.println("If you fail three times, the system will not allow you to log into your account");

        // customer has three chances to enter.
        while(chosenAcc.getCard().getIncorrectPasswordTypingChance() != 0) {  // incorrectPasswordTypingChance = 3
            System.out.print("Enter your pw: ");
            String pw = keyboard.nextLine();
            boolean flag = chosenAcc.checker(pw);
            if(flag){   // if flag returns true, it means that customer enters correct password
                break;
            }
        }

        if (chosenAcc.getCard().getIncorrectPasswordTypingChance() == 0){  // if customer enter wrong password 3 times,
            // then system will be over and customer will not be able to continue his/her operation
            System.out.println("You entered 3 wrong password so you are not allowed to continue now.....");
            System.exit(0);
        }

        System.out.println("Now you can perform operation on your account....");
        String menuChoice;

        do{
            displayMenu();
            System.out.print("Enter the operation you want to perform: ");
            menuChoice = keyboard.nextLine();
            if(menuChoice.equals("0")){  // halt the system
                System.out.println("See you next time! The system is over.");
                System.exit(0);  // the system is over only if the customer enter 0
            }
            else if(menuChoice.equals("1")){ // display balance
                chosenAcc.displayBalance();
            }
            else if(menuChoice.equals("2")){ // withdraw money
                System.out.println("Your withdrawal limit is " + chosenAcc.getOwner().getWithdrawalLimit() + "$");
                System.out.println("And your current balance is: " + chosenAcc.getCard().getBalance());
                System.out.print("Enter amount of money that you want to withdraw: ");
                double amount = keyboard.nextDouble();
                chosenAcc.withdrawMoney(amount);
                keyboard.nextLine(); // dummy io
            }
            else if(menuChoice.equals("3")){ // money transferring
                System.out.print("Enter the account id you want to transfer money to: ");
                String destinationId = keyboard.nextLine();

                BankAccount destinationAcc = null;
                int withdrawCounter = 0; // check whether the given id overlaps account that is available on our bank

                for(BankAccount acc : accounts){
                    if(destinationId.equals(acc.getCard().getAccountId())){
                        destinationAcc = acc;
                        withdrawCounter++;
                    }
                }
                if(withdrawCounter == 0){
                    System.out.println("Your destination account doesn't exist in our bank. The system is over. ");
                    System.out.println("Try again.....");
                    System.exit(0);
                }
                System.out.print("Enter the amount you want to transfer: ");
                double transferMoney = keyboard.nextDouble();
                chosenAcc.transferMoney(transferMoney, destinationAcc);
                System.out.println("After the transfer;");
                System.out.println("Your balance is " + chosenAcc.getCard().getBalance());
                System.out.println(destinationAcc.getOwner().getName()+"'s balance is "
                        + destinationAcc.getCard().getBalance() + "$");
                keyboard.nextLine(); // dummy io
            }
            else if(menuChoice.equals("4")){  // deposit money
                System.out.print("Enter deposit amount: ");
                double amount = keyboard.nextDouble();
                chosenAcc.depositMoney(amount);
                keyboard.nextLine();  // dummy io
            }
            else if(menuChoice.equals("5")){ // change daily withdrawal limit (student can't do this operation)
                System.out.println("Your current withdrawal limit is: " + chosenAcc.getOwner().getWithdrawalLimit() + "$");
                System.out.print("Enter the amount you want to change your withdrawal limit to: ");
                double newLimit = keyboard.nextDouble();
                chosenAcc.getOwner().setWithdrawalLimit(newLimit);
                System.out.println("Your new withdrawal limit is: " + chosenAcc.getOwner().getWithdrawalLimit() + "$");
                keyboard.nextLine();  // dummy io
            }
            else if(menuChoice.equals("6")){  // change account's password
                System.out.print("Enter your new password that you want to: ");
                String newPassword = keyboard.nextLine();
                chosenAcc.changePassword(newPassword);
                System.out.println("Your password is changed successfully!");
                System.out.println("New account password is: " + chosenAcc.getCard().getPassword());
            }
            else{ // if customer enter the number other than "0,1,2,3,4,5,6"
                System.out.println("Invalid choice... Please enter valid choice");
            }

        }while(true);   // if customer press 0, the system will be over
    }
}