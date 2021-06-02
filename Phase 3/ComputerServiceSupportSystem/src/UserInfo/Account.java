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
private String Email; 
private String Username;
private String Password;
private String userType;
    
Connection conn =null;
PreparedStatement ps = null;
   
   public Account() throws SQLException {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
   }
   public Account( String Email,String Username,String Password){
       this.Email = Email;
       this.Username = Username;
       this.Password = Password;
   }
   
   public Person login(String uUserNameEmail, String uPassword, String userType) throws SQLException {
        String sqlValidator = "SELECT * FROM ACCOUNT WHERE (USERNAME=? OR EMAIL=?) AND PASSWORD=?";
        ps = conn.prepareStatement(sqlValidator);
        ps.setString(1, uUserNameEmail);
        ps.setString(2, uUserNameEmail);
        ps.setString(3, uPassword);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Account acc = new Account();
            acc.setUsername(rs.getString("USERNAME"));
            acc.setEmail(rs.getString("EMAIL"));
            acc.setPassword(uPassword);
            acc.setUserType(userType);
            int pid = rs.getInt("PID");
            String pData = "SELECT * FROM PERSON p, ? x WHERE p.PID = ? AND p.PID = x.PID";
            ps = conn.prepareStatement(pData);
            ps.setString(1, userType.toUpperCase());
            ps.setInt(2, pid);
            rs = ps.executeQuery();
            if (userType.equals("Admin")) {
                Admin ad = new Admin();
                ad.setPid(pid);
                ad.setName(rs.getString("NAME"));
                ad.setAddress(rs.getString("ADDRESS"));
                ad.setAccount(acc);
                ps.close();
            conn.close();
                return ad;
            } else if (userType.equals("Technician")) {
                Technician te = new Technician();
                te.setPid(pid);
                te.setName(rs.getString("NAME"));
                te.setAddress(rs.getString("ADDRESS"));
                te.setAccount(acc);
                te.setOrdersMaxCapacity(rs.getInt("ORDERSMAXCAPACITY"));
                te.setSalary(rs.getInt("SALARY"));
                ps.close();
                conn.close();
                return te;
            } else if (userType.equals("Customer")) {
                Customer cu = new Customer();
                cu.setPid(pid);
                cu.setName(rs.getString("NAME"));
                cu.setAddress(rs.getString("ADDRESS"));
                cu.setAccount(acc);
                ps.close();
                conn.close();
                return cu;
            } 
        } 
        return null;
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
    
    
   
}
