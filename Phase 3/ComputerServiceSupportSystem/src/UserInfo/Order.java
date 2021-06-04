package UserInfo;

import ExceptionHandling.MyException;
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
    Statement stcat = null;
    ResultSet rs = null;
    String query;
    private String comment;
    private String computerPart;
    private LocalDate creationDate;
    private int customerId;
    private int orderId;
    private int price;
    private OrderStatus status;
    private int technicianId;
    
    
        
    /**
     * Both constructors set the creation date to the current local date Technician
     * Technician id is set to 0 and expected to be handled by the assign technician
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
        this.customerId = customerId;
        comment = "";
        computerPart = compPart;
        creationDate = LocalDate.now();
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
    public void setTechnicianId(int id) {
        
        technicianId = id;
    }
    public void setCustomerId(int id) {
        
        customerId = id;
    }
    
            
    public void setCreationDate(LocalDate ld) {
        
        this.creationDate = ld;
    }
    
    public int assignToTechnician(int orderId) throws SQLException {
        String query2 = "SELECT PID FROM TECHNICIAN t1 WHERE PID NOT IN "
                + "(SELECT TECHNICIANID FROM ORDERS, TECHNICIAN t2 WHERE TECHNICIANID = t2.PID)";
        rs = stcat.executeQuery(query2);
       
            if(rs.next()) {
            int currentTechId = rs.getInt("PID");
            query = "UPDATE ORDERS SET TECHNICIANID = " + currentTechId + " ,STATUS = 'InRepair' WHERE ORDERID = " + orderId;
            stcat.executeUpdate(query);
            setTechnicianId(currentTechId);
            stcat.close();
            return currentTechId;
        }
        else {
                
            ResultSet rs2 = null;
            query = "SELECT TECHNICIANID FROM ORDERS WHERE TECHNICIANID IS NOT NULL";
            rs2 = stcat.executeQuery(query);
            int currentTechId;
            int capacity;
            ResultSet rs3 = null;
            
            while(rs2.next()) {
                
                currentTechId = rs2.getInt("TECHNICIANID");
                query = "SELECT PID, MAXCAPACITY FROM TECHNICIAN, ORDERS WHERE TECHNICIANID = " + currentTechId;
                rs3 = stcat.executeQuery(query);
                capacity = rs.getInt("MAXCAPACITY");
                rs3.last();
                
                if(rs3.getRow() < capacity) {
                    
                    query = "UPDATE ORDERS SET TECHNICIANID = " + currentTechId + " ,STATUS = 'InRepair' WHERE ORDERID = " + orderId;
                    stcat.executeUpdate(query);
                    setTechnicianId(currentTechId);
                    stcat.close();
                    this.status = OrderStatus.InRepair;
                    return currentTechId;
                    
                }
                
            }
            return -1;
        }
    } 

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
