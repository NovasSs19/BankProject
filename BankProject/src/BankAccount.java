import java.util.*;
public class BankAccount {

    // Bank Account has two variables,
    // 1- Owner of the account
    // 2- Card for entering the account
    private BankCard card;
    private Customer owner;

    public BankAccount(BankCard card, Customer owner) {
        this.card = card;
        this.owner = owner;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public BankCard getCard() {
        return card;
    }

    public void setCard(BankCard card) {
        this.card = card;
    }

    public boolean checker(String pw){ // This method return boolean value because we check it in main. use it as a flag

        if(!pw.equals(card.getPassword())){ // If customer enter wrong password, his/her chance will decrease.(3 chance)
            card.decreaseChance();
            // display message to warn customer that he/she entered wrong pw. And show remaining chance.
            System.out.println("Your password is incorrect, you have "
                    + card.getIncorrectPasswordTypingChance() + " chance left.");
            return false;
        }
        else{
            System.out.println("You have written correct id and password.");
            return true;
        }
    }

    public void depositMoney(double amount){

        double bankDepositTax = 0.1;  // bank general tax for depositing money
                            // (however, it will be decreasing according to some conditions)

        double reductionTaxRate = owner.calculateTaxReductionRate(); // calculate discount

        double totalAppliedDepositTax = bankDepositTax - reductionTaxRate;  // total applied tax
        // when current customer want to deposit money

        // First display the current balance of customer, and deposit amount
        System.out.println("Your current balance before depositing is: "
                + card.getBalance() + "$ and you are now depositing: " + amount + "$");
        System.out.println(amount * totalAppliedDepositTax + "$ is commission," +
                " so you get " + (amount - amount * totalAppliedDepositTax) + "$");

        // update the balance of the card
        this.card.setBalance(card.getBalance() + (amount - amount * totalAppliedDepositTax));
        // displaying new balance after depositing money
        System.out.println("Your new balance is: " + card.getBalance() + "$");
    }

    public void withdrawMoney(double withdrawalAmount){

        while (withdrawalAmount > owner.getWithdrawalLimit() || withdrawalAmount > card.getBalance()){
            // check both withdrawal limit and owner's card's balance, if wanted withdrawal amount is appropriate
            // in terms of both balance and limit, then customer can withdraw money
            Scanner keyboard = new Scanner(System.in);

            //display the error message to inform the customer, display the current balance that he/she has
            // and display the withdrawal limit
            System.out.println("You want to withdraw more money than your limit or you have not enough money in your card");
            System.out.println("Your withdrawal limit is " + owner.getWithdrawalLimit() +
                    "$ and your balance is: " + card.getBalance() + "$");
            System.out.println("Please, enter a appropriate amount of money that you can withdraw");
            System.out.print("Enter amount of money that you want to withdraw: ");
            withdrawalAmount = keyboard.nextDouble();
        }
        this.card.setBalance(card.getBalance() - withdrawalAmount); //update the balance
        System.out.println("Withdrawal was successfully done!");
        System.out.println("Your current balance is: " + card.getBalance());
    }

    public void transferMoney(double moneyAmount, BankAccount destination){  // In our bank, customers can transfer money
        // to another account. There is a commission for transferring money (0.01).
        // However, both sender and recipient are student, there is no commission for transferring money.

        Scanner keyboard = new Scanner(System.in);

        double transferCommissionRate = 0.01; // when user want to transfer money to another account,
        // the tax will be applied (if both account owners' are student, there is no transfer commission.)
        String input;

        // display the account that customer want to transfer money, and let him/her to accept or reject the operation
        System.out.print("Transfer to: " + destination.owner.getName() + " if you don't want to send money to this account" +
                " press n and halt the system, otherwise, press any other key to continue.");
        input = keyboard.nextLine();

        if(input.equalsIgnoreCase("n")){  // if customer press n, the system will over,
            // he/she must run the system again to do operation
            System.out.println("System is over. To do another operation, run this system again.");
            System.exit(0);
        }  // if customer press any other keys, this means that he/she accept the operation

        if(this.owner.getClass() == Student.class && destination.owner.getClass() == Student.class){
            transferCommissionRate = 0;  // From Student to student there is no commission fee for transferring money!
        }

        if(moneyAmount + (moneyAmount * transferCommissionRate) > this.card.getBalance()){
            System.out.println("You don't have enough money! Your balance is: " + this.card.getBalance() + "$");
            System.out.println("And you must pay " + moneyAmount * transferCommissionRate +
                    "$ extra commission if you want to send " + moneyAmount + "$ so you must have "
                    + moneyAmount + (moneyAmount * transferCommissionRate) + "$ in your account" ) ;
            System.out.println("Try again....");
        }
        else{
            System.out.println("You also paid " + moneyAmount * transferCommissionRate + "$ transfer commission" );
            this.card.setBalance(this.card.getBalance() - (moneyAmount +(moneyAmount * transferCommissionRate)));
            destination.card.setBalance(destination.card.getBalance() + moneyAmount);
        }
    }

    public void displayBalance(){
        System.out.println("Your current balance is: " + card.getBalance() + "$");
    }

    public void changePassword(String newPw){
        Scanner keyboard = new Scanner(System.in);
        String pw = newPw;

        while(card.getPassword().equals(pw)){  // ıf user enter same password as him/her current password,
            // the system ask him/her again to enter another pw
            System.out.println("Your old password is the same as your new password!");
            System.out.print("Enter another password: ");
            pw = keyboard.nextLine();
        }
        card.setPassword(pw);
    }
}