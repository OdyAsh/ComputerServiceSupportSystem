/*
 * Group 1: Computer Service Support System (24)
 */
package User;
import UserInfo.Order;
/**
 *
 * @author Ash
 */
public class Technician extends Person {
    private int ordersMaxCapacity;
    private int salary;

    public Technician() {
        ordersMaxCapacity = 0;
        salary = 0;
    }
    public Technician(int capacity, int salary) {
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

    // still going to implement while implementing database parts

    /*public int numOfCurrentOrders {

    } */

    //This function might be edited according to database implementation
    public void updateOrderStatus(Order order, String status) {
        order.setStatus(status);
    }


    // Will be implemented along with DB stuff ya5ooya
    /* public void viewOrderQueue() {

    }*/

    
}
