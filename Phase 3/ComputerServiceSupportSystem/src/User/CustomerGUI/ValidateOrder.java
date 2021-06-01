/*
 * Group 1: Computer Service Support System (24)
 */
package User.CustomerGUI;

import User.Customer;

/**
 *
 * @author Ash
 */
public class ValidateOrder {
    private Customer c;
    public ValidateOrder() {
        this.c = new Customer();
    }
    public ValidateOrder(Customer c) {
        this.c = c;
    }
    public int checkPartAvailability(String part) throws Exception {
        if (part.equals(""))
            throw new Exception("Can't leave this field empty!");
        else {
            return c.searchParts(part);
        }
    }
    
    public Customer getCustomer() {
        return c;
    }
    public void setCustomer(Customer c) {
        this.c = c;
    }
    
    public String getName() {
        return c.getName();
    }
    public int getPid() {
        return c.getPid();
    }
}
