/*
 * Group 1: Computer Service Support System (24)
 */
package UserInfo;

import User.Admin;
import User.Customer;
import User.Person;
import User.Technician;
import java.sql.SQLException;

/**
 *
 * @author Ash
 */
public class ValidateLogin {
    private Account acc;
    private Person p;
    private Admin ad;
    private Technician te;
    private Customer cu;
    
    public ValidateLogin() throws SQLException {
        this.acc = new Account();
        this.p = new Person();
        this.ad = new Admin();
        this.te = new Technician();
        this.cu = new Customer();
    }
    
    public boolean login(String uUserNameEmail, String uPassword, String userType) throws SQLException {
        boolean loginSuccessful = false;
        switch (userType) {
            case "Admin":
                ad = acc.loginAdmin(uUserNameEmail, uPassword, userType);
                loginSuccessful = (ad!=null);
                break;
            case "Technician":
                te = acc.loginTechnician(uUserNameEmail, uPassword, userType);
                loginSuccessful = (te!=null);
                break;
            case "Customer":   
                cu = acc.loginCustomer(uUserNameEmail, uPassword, userType);
                loginSuccessful = (cu!=null);
                break;
            default:
                break;
        }
        return loginSuccessful;
    }

    public Admin getAdmin() {
        return ad;
    }

    public void setAdmin(Admin ad) {
        this.ad = ad;
    }

    public Technician getTechnician() {
        return te;
    }

    public void setTechnician(Technician te) {
        this.te = te;
    }

    public Customer getCustomer() {
        return cu;
    }

    public void setCustomer(Customer cu) {
        this.cu = cu;
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
