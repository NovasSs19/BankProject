public class Student extends Customer{

    private final double STUDENT_TAX_REDUCTION_RATE = 0.04; // Our bank takes %1 (0,1) DEPOSIT tax reduction
    // commission for depositing operation, however, if the customer is student, 0,1 - 0,04 = 0,06
    // commission is taken from him/her

    private final double WITHDRAWAL_LIMIT = 1000;   // Student's daily withdrawal limit could not be changed.
    // However, Unemployed and Worker can change his/her withdrawal limit

    public Student(int age, String name, boolean hasDisability) {
        super(age, name, hasDisability);
    }

    public String toString(){
        return super.toString() + " Your withdrawal limit is: " + WITHDRAWAL_LIMIT + "$";
    }

    public double calculateTaxReductionRate(){  // calculate the applied commission for depositing money
        double appliedReduction = STUDENT_TAX_REDUCTION_RATE;

        if(hasDisability()){ // disabled student (has 0,04 reduction due to being student, and if he/she has disability
            // he/she also has + 0,03 reduction for depositing money too)
            appliedReduction += 0.03;
        }
        return appliedReduction;
    }

    public double getWithdrawalLimit() {
        return WITHDRAWAL_LIMIT;
    }

    public double getSTUDENT_TAX_REDUCTION_RATE() {
        return STUDENT_TAX_REDUCTION_RATE;
    }
    public void setWithdrawalLimit(double withdrawalLimit){   // in our bank all customer decide their withdrawal limit
        // when they create their account except students. They cannot decide and change it. It is always fixed (1000$)
        // Thus, if students choose the option of setting withdrawal limit, the system display an error message for them
        System.out.println("You cannot change your withdrawal limit because you are a student.");
        System.out.println("Therefore, your withdrawal limit doesn't change");
    }
}