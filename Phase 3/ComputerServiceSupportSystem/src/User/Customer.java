/*
 * Group 1: Computer Service Support System (24)
 */
package User;

import ExceptionHandling.MyException;
import UserInfo.Account;
import UserInfo.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;

/**
 *
 * @author Ash
 */
public class Customer extends Person {
    Connection conn = null;
    PreparedStatement ps = null;

    public Customer() throws SQLException {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
    }

    public Customer(int Pid, String Name, Account account, String Address) throws SQLException {
        super(Pid, Name, account, Address);
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
    }
    
    public Customer(Person p) throws SQLException {
        super(p.getPid(), p.getName(), p.getAccount(), p.getAddress());
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
    }
    
    public int searchParts(String part) throws SQLException {
        String sql = "SELECT PRICE FROM PART WHERE PNAME=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, part);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int partPrice = rs.getInt("PRICE");
            ps.close();
            return partPrice;
        } else {
            ps.close();
            return -1;
        }
    }
    
    public Order trackOrder(int orderId, int customerId) throws SQLException, MyException {
        String sql = "SELECT * FROM ORDERS WHERE ORDERID=?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int cust = rs.getInt("CUSTOMERID");
            if(cust == customerId) {
                Order o = new Order();
                o.setOrderId(rs.getInt("ORDERID"));
                o.setCustomerId(cust);
                o.setTechnicianId(rs.getInt("TECHNICIANID"));
                o.setComment(rs.getString("COMMENT"));
                o.setComputerPart(rs.getString("COMPUTERPART"));
                o.setStatus(rs.getString("STATUS"));
                o.setPrice(rs.getInt("PRICE"));
                o.setCreationDate(rs.getObject("CREATIONDATE", LocalDate.class));
                ps.close();
                return o;
            } else {
                ps.close();
                throw new MyException("No order was found with that id...");
            }
        } else {
            ps.close();
            return null;
        }
    }
    
    public void cancelOrder(int orderId, int customerId) throws SQLException, MyException {
        String sql = "SELECT * FROM ORDERS WHERE ORDERID=" + orderId;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String errorType = "";
        if (rs.next()) {
            sql = "SELECT * FROM ORDERS WHERE ORDERID=" + orderId + " AND CUSTOMERID=" + customerId;
            rs = st.executeQuery(sql);
            if (rs.next()) {
                sql = "SELECT * FROM ORDERS WHERE ORDERID=" + orderId + " AND CUSTOMERID=" + customerId + " AND STATUS='Processing'";
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    sql = "UPDATE ORDERS SET STATUS='Cancelled' WHERE ORDERID=? AND CUSTOMERID=? AND STATUS='Processing'";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, orderId);
                    ps.setInt(2, customerId);
                    ps.executeUpdate();
                    st.close();
                    ps.close();
                    return;
                } else {
                    errorType = "Can't cancel, as the order has been assigned to a technician...";
                }
            } else {
                errorType = "The order id that you entered doesn't belong to you!";
            }
        } else {
            errorType = "No order exists with id" + orderId;
        }
        st.close();
        ps.close();
        throw new MyException(errorType);
    }
    
    public int createOrder(int customerId, String part, int partPrice) throws SQLException {
        Order o = new Order(customerId, part, partPrice);
        String sql = "INSERT INTO ORDERS (ORDERID,CREATIONDATE,PRICE,COMPUTERPART,COMMENT,STATUS,TECHNICIANID,CUSTOMERID) VALUES (?,?,?,?,NULL,'Processing',NULL,?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, o.getOrderId());
        ps.setObject(2, Date.valueOf(o.getCreationDate()));
        ps.setInt(3, partPrice);
        ps.setString(4, part);
        ps.setInt(5, customerId);
        ps.executeUpdate();
        ps.close();
        return o.getOrderId();
    }
}
