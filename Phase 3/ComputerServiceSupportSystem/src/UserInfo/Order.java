package UserInfo;

import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

enum OrderStatus {
    Fixed, NotFixed, Processing, Cancelled, InRepair;
}

public class Order {
    Connection conncat = null;
    java.sql.Statement stcat = null;
    ResultSet rs = null;
    String query;
    private String comment;
    private String computerPart;
    private LocalDate creationDate;
    private int customerId;
    private int orderId;
    //private static int count = 0; //desc in sql: SELECT PID FROM PERSON ORDER BY PID DESC (greatest ID)
    private int price;
    private OrderStatus status;
    private int technicianId;
    
    
        
    /**
     * Both constructors set the creation date to the current local date Technician
     * Technician id is set to 0 and expected to be handled by the assign technician func
     * Comment member is the same as Technician ID Order status is set to processing
     * by default when an object is created
     * Order id is set automatically using the static count variable
     */
    public Order() {
        try{
            conncat = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem");
            stcat = conncat.createStatement();
            System.out.println("Database connected successfully");
            query = "SELECT MAX(ORRDERID)FROM ORDERS";
            rs = stcat.executeQuery(query);
            orderId = rs.getInt("ORDERID");
            orderId += 1;
        } 
        catch (SQLException ex) {
            System.out.println("Database connection failed"); 
        }
        comment = "";
        computerPart = "";
        creationDate = LocalDate.now();
        customerId = 0;
        price = 0;
        status = OrderStatus.Processing;
        technicianId = 0;
        
    }

    public Order(int customerId, String compPart, int price) {
        
        try{
            
            conncat = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem");
            stcat = conncat.createStatement();
            System.out.println("Database connected successfully");
            query = "SELECT MAX(ORRDERID)FROM ORDERS";
            rs = stcat.executeQuery(query);
            orderId = rs.getInt("ORDERID");
            orderId += 1;    
        } 
        catch (SQLException ex) {
            
            System.out.println("Database connection failed");    
        }    
        comment = "";
        computerPart = compPart;
        creationDate = LocalDate.now();
        this.customerId = customerId;
        this.price = price;
        status = OrderStatus.Processing;
        technicianId = 0;
    }

    
    
    // setters
    /**
     * Setters are missing creation date setter because it should only be created when the constructor is called
     * Hence protecting the data from being manipulated unreasonably
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setComputerPart(String part) {
        computerPart = part;
    }
    public void customerId (int id){
        customerId = id;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setStatus(String strStatus) {
        
        //needs error control using while and if to make sure string is exactly matching the enum values
        status = status.valueOf(strStatus);
    }
    public void setCreationDate(LocalDate ld) {
    this.creationDate = ld;
    }
    /*Tech Id setter
    public int assignToTechnician(int orderId) {
        //retrieve from DB by selecting orderID
    } */

    //Getters

    public String getComment() {
        return comment;
    }
    public String getComputerPart() {
        return computerPart;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public int getCustomerId() {
        return customerId;
    }
    public int getOrderId() {
        return orderId;
    }
    public int getPrice() {
        return price;
    }
    public String getStatus() {
        String strStatus = this.status.toString();
        return strStatus;
    }
    public int getTechnicianId() {
        return technicianId;
    }

}
