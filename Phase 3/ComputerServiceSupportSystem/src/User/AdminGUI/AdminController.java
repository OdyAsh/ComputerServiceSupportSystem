/*
 * Group 1: Computer Service Support System (24)
 */
package User.AdminGUI;

import User.Admin;
import UserInfo.Order;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author infolos
 */
public class AdminController {
    private Admin currentAdmin;
    private ArrayList<Order> tempOrd = null;
    public AdminController(Admin currentAdmin) {
        this.currentAdmin = currentAdmin;
    }
    public ArrayList<Order> getTempOrders() {
        return tempOrd;
    }

    public void setTempOrders(LocalDate date,String Status) {
        tempOrd = this.getCurrentAdmin().generateReport(date, Status);
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
        return currentAdmin.getPid();
    }
    public String getUserName(){
        return currentAdmin.getAccount().getUsername();
    }
    public String getEmail(){
        return currentAdmin.getAccount().getEmail();
    }
    public String getPassword(){
        return currentAdmin.getAccount().getPassword();
    }
}
