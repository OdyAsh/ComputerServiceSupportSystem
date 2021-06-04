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
public class OrderDetails {
    private Customer c;
    private Order o;
    
    public OrderDetails() throws SQLException {
        c = new Customer();
        o = new Order();
    }
    public OrderDetails(Customer c) throws SQLException {
        this.c = c;
        o = new Order();
    }
    public OrderDetails(Customer c, Order o) throws SQLException {
        this.c = c;
        this.o = o;
    }
    
    public Order trackOrderDetails(int orderId, int custID) throws SQLException, MyException{
        o = c.trackOrder(orderId, custID);
        return o;
    }
    public void checkOrderToCancel(int orderId, int custID) throws SQLException, MyException{
        c.cancelOrder(orderId, custID);
    }
    
    public int getTechnicianId() {
        return o.getTechnicianId();
    }
    
    public String getStatus() {
        return o.getStatus();
    }
    
    public int getPrice() {
        return o.getPrice();
    }
    
    public String getComputerPart() {
        return o.getComputerPart();
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
