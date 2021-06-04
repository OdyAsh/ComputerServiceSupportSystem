/*
 * Group 1: Computer Service Support System (24)
 */
package User.CustomerGUI;

import ExceptionHandling.MyException;
import User.Customer;
import UserInfo.Order;
import java.sql.SQLException;

/**
 *
 * @author Ash
 */
public class ValidateOrder {
    private Customer c;
    private Order o;
    public ValidateOrder() throws SQLException {
        this.c = new Customer();
    }
    
    public ValidateOrder(Customer c) {
        this.c = c;
    }
    public int checkPartAvailability(String part) throws MyException, SQLException {
        if (part.equals(""))
            throw new MyException("Can't leave this field empty!");
        else {
            int price = c.searchParts(part);
            if (price == -1) {
                throw new MyException("Part not found");
            } else {
                return price;
            }
        }
    }
    
    String createOrder(int customerId, String part, int partPrice) throws SQLException { //note: here we could've ommitted the customerId param., as the Customer object already has a pid
        int orderId = c.createOrder(customerId, part, partPrice);
        int technicianId = o.assignToTechnician(orderId);
        if (technicianId == -1) {
            return "Order created successfully!\nHowever, all technicians are currently working on other orders.\nTherefore, order status is \"Processing\" until a technician is found";
        } else {
            return "Order created successfully!\nAssigning your order to technician with id: " + technicianId;
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
