/*
 * Group 1: Computer Service Support System (24)
 */
package UserInfo;

import User.Person;

/**
 *
 * @author Ash
 */
public class ValidateLogin {
    private Account acc;
    private Person p;
    
    public ValidateLogin() {
        this.acc = new Account();
    }
    
    public Person login(String uUserNameEmail, String uPassword, String userType) {
        p = acc.login(uUserNameEmail, uPassword, userType); //userType is either: "Admin", "Technician", or "Customer"
        if (p == null) {
            return null;
        } else {
            return  p;
        }
        
    }
    
    public Person getPerson() {
        return p;
    }
    public void setPerson(Person p) {
        this.p = p;
    }
    public Account getAccount() {
        return acc;
    }
    public void setAccount(Account acc) {
        this.acc = acc;
    }
}
