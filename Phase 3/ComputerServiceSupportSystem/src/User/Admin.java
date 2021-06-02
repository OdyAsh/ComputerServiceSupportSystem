/*
 * Group 1: Computer Service Support System (24)
 */
package User;

import UserInfo.Order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public Admin(){
       try{
           conncat = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
           stcat = conncat.createStatement();
           System.out.println("Database connected successfully");
       }catch(SQLException ex){
           System.out.println("Database connection failed");
       }
    }
    
    
    public boolean addAccount(String Name, String Username,String Email, String Password,String Address,String AccountType,int ordersMaxCapacity,int Salary){
        try{
            Statement stmt = conncat.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PID FROM PERSON ORDER BY PID DESC");
            rs.next();
            int id = rs.getInt("PID");
            id += 1;
            String addUser = "INSERT INTO PERSON (PID,NAME,ADDRESS) VALUES('"+id+"','"+Name+"','"+Address+"')";
            String addAccount = "INSERT INTO ACCOUNT (PID,USERNAME,EMAIL,PASSWORD,ACCOUNT_TYPE) VALUES('"+id+"','"+Username+"','"+Email+"','"+Password+"','"+AccountType+"')";
            stcat.executeUpdate(addUser);
            stcat.executeUpdate(addAccount);
            if(AccountType == "Customer"){
                String addCust = "INSERT INTO CUSTOMER (PID) VALUES('"+id+"')";
                stcat.executeUpdate(addCust);
            }else{
                String addTech = "INSERT INTO TECHNICIAN (PID,OrdersMaxCapacity,Salary) VALUES('"+id+"','"+ordersMaxCapacity+"','"+Salary+"')";
                stcat.executeUpdate(addTech);
        }
            stcat.close();
            conncat.close();
            return true;
        }catch(SQLException ex){
            return false;
        }
    }
    public boolean removeAccount(String userType,int id){
        try{
            String sqlPerson = "DELETE FROM PERSON WHERE PID in (SELECT ACCOUNT_TYPE,PID FROM ACCOUNT WHERE ACCOUNT_TYPE ="+userType+" & PID="+id+"));";
            int numOfRows = stcat.executeUpdate(sqlPerson);
            stcat.close();
            conncat.close();
            return numOfRows != 0;
        }catch(SQLException ex){
            return false;
        }
    }
    public boolean searchAccounts(int id){
        try{
            String sqlIdentify = "SELECT PID,ACCOUNT_TYPE FROM ACCOUNT WHERE PID ="+id+"& ACCOUNT_TYPE = 'Technician'";
            ResultSet pid = stcat.executeQuery(sqlIdentify);
            pid.next();
            int personId = pid.getInt("PID");
            stcat.close();
            conncat.close();
            return personId == id;
        }catch(SQLException ex){
            return false;
        }
    }
    public void UpdateSalary(int id, int Salary){
        try {
            String sqlSalaryUpdate = "UPDATE TECHNICIAN SET SALARY = "+Salary+" WHERE PID="+id;
            stcat.executeUpdate(sqlSalaryUpdate);
            stcat.close();
            conncat.close();
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
                temp.setCreationDate(rs.getObject("CREATIONDATE", LocalDateTime.class));
                tempOrders.add(temp);
            }
            return tempOrders;
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
