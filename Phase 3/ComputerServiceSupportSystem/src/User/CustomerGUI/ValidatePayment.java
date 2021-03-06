/*
 * Group 1: Computer Service Support System (24)
 */
package User.CustomerGUI;

import BankInfo.Bank;
import User.Customer;
import java.sql.SQLException;

/**
 *
 * @author Ash
 */
public class ValidatePayment {
    private Customer c;
    private Bank b;
    private int notValidOrBalance;
    public ValidatePayment() throws SQLException {
        this.c = new Customer();
        this.b = new Bank();
    }
    
    public ValidatePayment(Customer c) throws SQLException {
        this.c = c;
        this.b = new Bank();
    }
    
    public int checkCredit(int customerId, String cardNumber, int partPrice) throws SQLException {
        notValidOrBalance = b.checkCredit(customerId, cardNumber, partPrice);
        return notValidOrBalance;
    }
    
    //-1: Customer doesn't have credit
    //-2: Customer entered wrong cardnumber
    //-3: Customer doesn't have enough balance
    //-4: Customer's credit is expired
    //1: Credit is elligible, 
    //so order will be created via ValidateOrder controller and part price will be withdrawn via this controller
    public String returnErrorMessage() {
        switch (notValidOrBalance) {
            case -1:
                return "Your credit card isn't stored in our registered banks...";
            case -2:
                return "Wrong cardnumber...";
            case -3:
                return "you don't have enough balance in the account...";
            case -4:
                return "Unfortunately, your credit card has expired...";
            default:
                return null;
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
