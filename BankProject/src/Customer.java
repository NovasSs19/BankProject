public abstract class Customer {

    // general properties of customers (our bank stores all of these properties)
    private int age;
    private String name;
    private boolean hasDisability;

    public Customer(int age, String name, boolean hasDisability) {
        setAge(age);
        setName(name);
        setHasDisability(hasDisability);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

        if(age > 0){  // Age must be greater than 0
            this.age = age;
        }
        else{   // If user enter his/her age invalid, error message will be displayed and program will be over.
            System.out.println("Error! Enter valid age.");
            System.exit(0);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasDisability() {
        return hasDisability;
    }

    public void setHasDisability(boolean hasDisability) {
        this.hasDisability = hasDisability;
    }

    public String toString(){   // display the customer's information
        String message = "Name: " +  getName() + "Age: " + getAge() + ".";
        if(hasDisability){  // In our bank, when customer want to deposit money, our bank takes some commission (%1).
            // if customer has a disability, taken commission rate decreases (-0,03)
            message += " Due to the having an disabilities, taxes will be applied to you less.";
        }
        return message;
    }

    // all customer types(student, worker, unemployed) must have these methods
    public abstract double calculateTaxReductionRate();
    public abstract double getWithdrawalLimit();
    public abstract void setWithdrawalLimit(double withdrawalLimit);
}
