public class BankCard {

    private int incorrectPasswordTypingChance;
    // Unlike real banks that we use today, customer has both id and pw for log in.
    private String accountId;
    private String password;
    private double balance;

    public BankCard(String id, String pw, double balance){
        this.accountId = id;
        this.password = pw;
        this.balance = balance;
        incorrectPasswordTypingChance = 3;  // all cards have three incorrect typing chance
                                            // they could be changed later.
    }
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {      // this method is used in other
                                            // bank methods such as deposit and withdraw.
        this.balance = balance;
    }

    public void decreaseChance(){   // when user types incorrect pw for his/her account, typing chance will decrease
        incorrectPasswordTypingChance--;
    } // Customers have 3 chances to enter their account, When customer enter wrong pw, their chances are decreasing,
    // and if they enter 3 times wrong, their account will be blocked for their safety

    public int getIncorrectPasswordTypingChance(){
        return incorrectPasswordTypingChance;
    }
}