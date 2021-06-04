/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInfo;

import User.Admin;
import User.Customer;
import User.Person;
import User.Technician;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private int pid;
    private String Email; 
    private String Username;
    private String Password;
    private String userType;

    Connection conn =null;
    PreparedStatement ps = null;
   
   public Account() throws SQLException {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
        this.pid = 0;
        this.Email = "";
        this.Username = "";
        this.Password = "";
   }
   public Account(int pid, String Email, String Username, String Password) throws SQLException{
       conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
       this.pid = pid;
       this.Email = Email;
       this.Username = Username;
       this.Password = Password;
   }
   
   public Account retrieveAccount(String uUserNameEmail, String uPassword, String userType) throws SQLException {
       String sqlValidator = "SELECT * FROM ACCOUNT WHERE (USERNAME=? OR EMAIL=?) AND PASSWORD=?";
        ps = conn.prepareStatement(sqlValidator);
        ps.setString(1, uUserNameEmail);
        ps.setString(2, uUserNameEmail);
        ps.setString(3, uPassword);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Account acc = new Account();
            acc.setPid(rs.getInt("PID"));
            acc.setUsername(rs.getString("USERNAME"));
            acc.setEmail(rs.getString("EMAIL"));
            acc.setPassword(uPassword);
            acc.setUserType(userType);
            ps.close();
            
            return acc;
        } else {
            ps.close();
            
            return null;
        }
   }
   
   public Admin loginAdmin(String uUserNameEmail, String uPassword, String userType) throws SQLException {
            Account acc = retrieveAccount(uUserNameEmail, uPassword, userType);            
            if (acc == null) {
                return null;
            }
            int loggedInPid = acc.getPid();
            String pData = "SELECT * FROM PERSON p, ADMIN x WHERE p.PID = ? AND p.PID = x.PID";
            ps = conn.prepareStatement(pData);
            ps.setInt(1, loggedInPid);
            ResultSet rs = ps.executeQuery();
            Admin ad = new Admin();
            if (rs.next()) {
                ad.setPid(loggedInPid);
                ad.setName(rs.getString("NAME"));
                ad.setAddress(rs.getString("ADDRESS"));
                ad.setAccount(acc);
                ps.close();

                return ad;
            } else {
                return null;
            }
            
   }
   
   public Technician loginTechnician(String uUserNameEmail, String uPassword, String userType) throws SQLException {
            Account acc = retrieveAccount(uUserNameEmail, uPassword, userType);
            if (acc == null) {
                return null;
            }
            int loggedInPid = acc.getPid();
            String pData = "SELECT * FROM PERSON p, TECHNICIAN x WHERE p.PID = ? AND p.PID = x.PID";
            ps = conn.prepareStatement(pData);
            ps.setInt(1, loggedInPid);
            ResultSet rs = ps.executeQuery();
            Technician te = new Technician();
            if (rs.next()) {
                te.setPid(loggedInPid);
                te.setName(rs.getString("NAME"));
                te.setAddress(rs.getString("ADDRESS"));
                te.setAccount(acc);
                te.setOrdersMaxCapacity(rs.getInt("MAXCAPACITY"));
                te.setSalary(rs.getInt("SALARY"));
                ps.close();

                return te;
            } else {
                return null;
            }  
   }
   
   public Customer loginCustomer(String uUserNameEmail, String uPassword, String userType) throws SQLException {
            Account acc = retrieveAccount(uUserNameEmail, uPassword, userType);
            if (acc == null) {
                return null;
            }
            int loggedInPid = acc.getPid();
            String pData = "SELECT * FROM PERSON p, CUSTOMER x WHERE p.PID = ? AND p.PID = x.PID";
            ps = conn.prepareStatement(pData);
            ps.setInt(1, loggedInPid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer cu = new Customer();
                cu.setPid(loggedInPid);
                cu.setName(rs.getString("NAME"));
                cu.setAddress(rs.getString("ADDRESS"));
                cu.setAccount(acc);
                ps.close();
                
                return cu;
            } else {
                return null;
            }
   }

   public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
 
   public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "Account{" + "pid=" + pid + ", Email=" + Email + ", Username=" + Username + ", Password=" + Password + ", userType=" + userType + ", conn=" + conn + ", ps=" + ps + '}';
    }

}