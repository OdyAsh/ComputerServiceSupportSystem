/*
 * Group 1: Computer Service Support System (24)
 */
package User;

import UserInfo.Account;
import UserInfo.Order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ash
 */
public class Admin extends Person{
    Connection conncat = null;
    java.sql.Statement stcat = null;
    public Admin() throws SQLException {
       try{
           conncat = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
           stcat = conncat.createStatement();
           System.out.println("Database connected successfully");
       }catch(SQLException ex){
           System.out.println("Database connection failed");
       }
    }
    
     public Admin(Person p) throws SQLException {
        super(p.getPid(), p.getName(), p.getAccount(), p.getAddress());
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
        stcat = conncat.createStatement();
    }
    
    public Admin(int Pid, String Name, Account account, String Address) throws SQLException {
        super(Pid, Name, account, Address);
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
        stcat = conncat.createStatement();
    }
    
    public boolean addAccount(String Name, String Username,String Email, String Password,String Address,String AccountType,int ordersMaxCapacity,int Salary){
        try{
            Statement stmt = conncat.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PID FROM PERSON ORDER BY PID DESC");
            if (rs.next()) {
                int id = rs.getInt("PID");
                id += 1;
                String addUser = "INSERT INTO PERSON (PID,NAME,ADDRESS) VALUES("+id+",'"+Name+"','"+Address+"')";
                String addAccount = "INSERT INTO ACCOUNT (PID,USERNAME,EMAIL,PASSWORD,ACCOUNT_TYPE) VALUES("+id+",'"+Username+"','"+Email+"','"+Password+"','"+AccountType+"')";
                stcat.executeUpdate(addUser);
                stcat.executeUpdate(addAccount);
                if(AccountType.equals("Customer")){
                    String addCust = "INSERT INTO CUSTOMER (PID) VALUES("+id+")";
                    stcat.executeUpdate(addCust);
                }else{
                    String addTech = "INSERT INTO TECHNICIAN (PID,MAXCAPACITY,SALARY) VALUES("+id+",'"+ordersMaxCapacity+"','"+Salary+"')";
                    stcat.executeUpdate(addTech);
                }
                stcat.close();
                return true;
            } else {
                return false;
            }   
        } catch(SQLException ex){
            return false;
        }
    }
    public boolean removeAccount(int id){
        try{
            String sqlPerson = "DELETE FROM PERSON WHERE PID="+id;
            stcat = conncat.createStatement();
            int numOfRows = stcat.executeUpdate(sqlPerson);
            stcat.close();
            return numOfRows != 0;
        }catch(SQLException ex){
            return false;
        }
    }
    public boolean searchAccounts(int id){
        try{
            String sqlIdentify = "SELECT PID,ACCOUNT_TYPE FROM ACCOUNT WHERE PID ="+id;
            ResultSet rs = stcat.executeQuery(sqlIdentify);
            if (rs.next()) {
                int personId = rs.getInt("PID");
                stcat.close();
                return personId == id;
            } else {
                return false;
            }
        }catch(SQLException ex){
            return false;
        }
    }
    public void UpdateSalary(int id, int Salary){
        try {
            String sqlSalaryUpdate = "UPDATE TECHNICIAN SET SALARY = "+Salary+" WHERE PID="+id;
            stcat.executeUpdate(sqlSalaryUpdate);
            stcat.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public ArrayList<Order> generateReport(LocalDate date,String Status){
        ArrayList<Order> tempOrders = null;
        Order temp = new Order();
        ResultSet rs;
        try {
            String sql = "SELECT ORDERID,PRICE,STATUS,CREATIONDATE FROM ORDERS WHERE STATUS=? & CREATIONDATE >=?";
            PreparedStatement stmt= conncat.prepareStatement(sql);
            stmt.setString(1, Status);
            stmt.setObject(2, date);
            rs = stmt.executeQuery();
            while(rs.next()){
                temp.setOrderId(rs.getInt("ORDERID"));
                temp.setPrice(rs.getInt("PRICE"));
                temp.setStatus(rs.getString("STATUS"));
                temp.setCreationDate(rs.getObject("CREATIONDATE", LocalDate.class));
                tempOrders.add(temp);
            }
            stcat.close();
            
            return tempOrders;
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
