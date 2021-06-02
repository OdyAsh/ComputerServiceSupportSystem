package UserInfo;

import java.time.LocalDate;

enum OrderStatus {
    Fixed, NotFixed, Processing, Cancelled, InRepair;
}

public class Order {

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
        //database connection
        comment = "";
        computerPart = "";
        creationDate = LocalDate.now();
        customerId = 0;
        count++;
        orderId = count;
        price = 0;
        status = OrderStatus.Processing;
        technicianId = 0;
    }

    public Order(String compPart, int price) {
        //database connection
        comment = "";
        computerPart = compPart;
        creationDate = LocalDate.now();
        count++;
        orderId = count;
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
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public void setCreationDate(LocalDate ld) {
    this.creationDate = ld;
    }
    /*Tech Id setter
    public boolean assignToTechnician(int orderId) {
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
    public OrderStatus getStatus() {
        return status;
    }
    public int getTechnicianId() {
        return technicianId;
    }



}
