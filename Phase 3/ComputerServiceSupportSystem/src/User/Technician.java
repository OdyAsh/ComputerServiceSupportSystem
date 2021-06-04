/*
 * Group 1: Computer Service Support System (24)
 */
package User;
import UserInfo.Account;
import UserInfo.Order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author Ash
 */
public class Technician extends Person {
    private int ordersMaxCapacity;
    private int salary;
    ResultSet rs = null;
    Statement stcat = null;
    PreparedStatement prepStmt = null;
    String query;
    Connection conncat = null;

    public Technician() throws SQLException {
        ordersMaxCapacity = 0;
        salary = 0;
         try {
            conncat = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
            stcat = conncat.createStatement();
            System.out.println("Database connected successfully");
        } 
        catch (SQLException ex) {
            System.out.println("Database connection failed");
        }
    }
     public Technician(Person p) throws SQLException {
        super(p.getPid(), p.getName(), p.getAccount(), p.getAddress());
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
        stcat = conncat.createStatement();
    }
    public Technician(int Pid, String Name, Account account, String Address) throws SQLException {
        super(Pid, Name, account, Address);
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
        stcat = conncat.createStatement();
    }
    public Technician(int Pid, String Name, Account account, String Address, int capacity, int salary) throws SQLException {
        super(Pid, Name, account, Address);
        this.salary = salary;
        ordersMaxCapacity = capacity;
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
        stcat = conncat.createStatement();
    }
    public Technician(int capacity, int salary) throws SQLException {
         try {
            conncat = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
            stcat = conncat.createStatement();
            System.out.println("Database connected successfully");
        } 
        catch (SQLException ex) {
            System.out.println("Database connection failed");
        }
        this.salary = salary;
        ordersMaxCapacity = capacity;
    }

    //Setters
    public void setOrdersMaxCapacity(int capacity) {
        ordersMaxCapacity = capacity;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }

    //Getters
    public int getOrdersMaxCapacity() {
        return ordersMaxCapacity;
    }
    public int getSalary() {
        return salary;
    }

    

    public int numOfCurrentOrders(int techId) throws SQLException {
        int numOrders;
        query = "SELECT TECHNICIANID FROM ORDERS WHERE TECHNICIANID =?";
        prepStmt = conncat.prepareStatement(query);
        prepStmt.setInt(1, techId);
        rs = prepStmt.executeQuery(query);
        rs.last();
        numOrders = rs.getRow();
        prepStmt.close();
        return numOrders;
    } 

    public boolean updateOrderStatus(int orderId, String status) throws SQLException {
       query = "UPDATE ORDERS SET STATUS =? WHERE ORDERID =?";
       prepStmt = conncat.prepareStatement(query);
       prepStmt.setString(1, status);
       prepStmt.setInt(2, orderId);
       int isUpdated = prepStmt.executeUpdate();
       prepStmt.close();
       return isUpdated != 0;
    }


     public ArrayList<Order> viewOrderQueue(int techId) throws SQLException {
         ArrayList<Order> queue = null;
         Order temp = new Order();
         //order id getting messed with
         query = "SELECT ORDERID, STATUS, CREATIONDATE FROM ORDERS TECHNICIANID =?";
         prepStmt = conncat.prepareStatement(query);
         prepStmt.setInt(1, techId);
         rs = prepStmt.executeQuery(query);
         while(rs.next()) {
            temp.setOrderId(rs.getInt("ORDERID")); 
            temp.setStatus(rs.getString("STATUS"));
            temp.setCreationDate(rs.getObject("CREATIONDATE", LocalDate.class));
            queue.add(temp);
         }
         prepStmt.close();
         return queue;
    }

    
}
