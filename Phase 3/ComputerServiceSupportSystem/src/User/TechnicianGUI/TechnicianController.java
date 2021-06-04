/*
 * Group 1: Computer Service Support System (24)
 */
package User.TechnicianGUI;

import User.Technician;
import UserInfo.Order;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Eyad
 */
public class TechnicianController {
    
    private Technician currentTechnician;
    private ArrayList<Order> queue = null;
    
    public TechnicianController() throws SQLException {
        currentTechnician = new Technician();  
    }
    
    public TechnicianController(Technician currentTech) {
        currentTechnician = currentTech;
        
    }
    
    public void setCurrentTechnician(Technician currentTech) {
        this.currentTechnician = currentTech;
    }
    
    public Technician getCurrentTechnician() {
        return currentTechnician;
    }
    
    public ArrayList<Order> viewOrderQueue() {
        return queue;
    }
    
    public void setQueue(int technicianId) throws SQLException {
        
        queue = currentTechnician.viewOrderQueue(technicianId);
        
    }
    
    public void updateStatus(int orderId, String status) throws SQLException {
 
        Order temp = null;
        
        for(int i = 0; i < queue.size(); i++) {
            temp = queue.get(i);
            if(temp.getOrderId() == orderId) {
                temp.setStatus(status);
                currentTechnician.updateOrderStatus(temp.getOrderId(), status);
                queue.set(i, temp);
                break;
            }
        }
    }
    
    public void leaveComment(String comment, int orderId) throws SQLException {
       
        Order temp = null;
        
        for(int i = 0; i < queue.size(); i++) {
            temp = queue.get(i);
            if(temp.getOrderId() == orderId) {
                temp.setComment(comment);
                currentTechnician.updateOrderStatus(orderId, comment);
                break;
            }
        }
        
    }
     
    public int getPid() {
        return currentTechnician.getPid();
    }
   
}
