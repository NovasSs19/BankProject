public class Unemployed extends Customer{

    private double withdrawalLimit;  // Unemployed has daily withdrawal limit, however this limit can change.

    public Unemployed(int age, String name, boolean hasDisability, int withdrawalLimit) {
        super(age, name, hasDisability);
        setWithdrawalLimit(withdrawalLimit);
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(double withdrawalLimit) {

        if(withdrawalLimit < 0){  // Limit must be greater than zero
            System.out.println("Error! withdrawal limit must be greater than 0");
            System.exit(0);
        }
        this.withdrawalLimit = withdrawalLimit;
    }

    public double calculateTaxReductionRate() {  // calculate the total reduction
                                    // for depositing money for Unemployed customers
        double appliedReduction = 0;   // They have no reduction unlike student and worker.
        // (However, if they have disability or, they are older than 60, 0.03 reduction will be applied.)
        if(this.getAge() > 60){
            appliedReduction += 0.03;
        }
        if(this.hasDisability()){
            appliedReduction += 0.03;
        }
        return appliedReduction;    // If unemployed customer is older than 60 and has disability,
                                    // 0.03 + 0.03 = 0.06 reduction will be applied for depositing money
    }

    public String toString(){
        return super.toString() + "Your withdrawal limit is: " + withdrawalLimit + "$";
    }
}