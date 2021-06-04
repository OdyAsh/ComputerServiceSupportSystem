/*
 * Group 1: Computer Service Support System (24)
 */
package User.AdminGUI;

import User.Admin;
import UserInfo.Order;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author infolos
 */
public class AdminController {
    private Admin currentAdmin;
    private ArrayList<Order> tempOrd = null;
    public AdminController() throws SQLException {
        this.currentAdmin = new Admin();
    }
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
    public boolean addAccount(String Name, String Username,String Email, String Password,String Address,String AccountType,int ordersMaxCapacity,int Salary){
        boolean test = currentAdmin.addAccount(Name, Username, Email, Password, Address, AccountType, ordersMaxCapacity, Salary);
        return test;
    }
    public boolean removeAccount(int id){
        boolean test = currentAdmin.removeAccount(id);
        return test;
    }
    public boolean searchAccounts(int id){
        boolean test = currentAdmin.searchAccounts(id);
        return test;
    }
    public void UpdateSalary(int id, int Salary){
        currentAdmin.UpdateSalary(id, Salary);
    }
}
