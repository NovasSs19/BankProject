public class Worker extends Customer{

    private final double WORKER_TAX_REDUCTION_RATE = 0.02;  // Our bank takes %1 (0,1) DEPOSIT tax reduction
    private double withdrawalLimit;  // Worker has daily withdrawal limit, however this limit can change.

    public Worker(int age, String name, boolean hasDisability, int withdrawalLimit) {
        super(age, name, hasDisability);
        setWithdrawalLimit(withdrawalLimit);
    }

    public String toString(){
        return super.toString() + " Your withdrawal limit is: " + withdrawalLimit + "$";
    }

    public double calculateTaxReductionRate() {
        double appliedReduction = WORKER_TAX_REDUCTION_RATE;  // disabled worker customer
        // (has 0,02 reduction due to being worker,
        // and if he/she has disability he/she also has  +0,03 reduction for depositing money too

        if(hasDisability()){
            appliedReduction += 0.03;
        }
        return appliedReduction;
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(double withdrawalLimit) {
        if(withdrawalLimit > 0 ){   // withdrawal limit must be greater than zero.
            this.withdrawalLimit = withdrawalLimit;
        }
        else{
            System.out.println("Error! withdrawal limit must be greater than 0!");
            System.exit(0);
        }
    }

    public double getWORKER_TAX_REDUCTION_RATE() {
        return WORKER_TAX_REDUCTION_RATE;
    }
}