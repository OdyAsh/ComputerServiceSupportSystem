/*
 * Group 1: Computer Service Support System (24)
 */
package User;

/**
 *
 * @author infolos
 */
public class AdminControler {
    private Admin currentAdmin;

    public AdminControler(Admin currentAdmin) {
        this.currentAdmin = currentAdmin;
    }

    public Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public void setCurrentAdmin(Admin currentAdmin) {
        this.currentAdmin = currentAdmin;
    }
    public String getName(){
        return currentAdmin.getName();
    }
    public String getAddress(){
        return currentAdmin.getAddress();
    }
    public int getPersonId(){
        return currentAdmin.getPID();
    }
    public String getUserName(){
        return currentAdmin.getUserName();
    }
    public String getEmail(){
        return currentAdmin.getEmail();
    }
    public String getPassword(){
        return currentAdmin.getPassword();
    }
}
