/*
 * Group 1: Computer Service Support System (24)
 */
package BankInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Ash
 */
public class Credit {
    private int balance;
    private String cardNumber; //String, as int can't hold 16 digits!
    private int customerId;
    private int cvv;
    private LocalDate expDate;
    Connection conn = null;
    PreparedStatement ps = null;

    public Credit() throws SQLException {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
        balance = customerId = cvv = 0;
        cardNumber = "";
        expDate = LocalDate.now();
    }

    public Credit(int balance, String cardNumber, int customerId, int cvv, LocalDate expDate) throws SQLException {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.customerId = customerId;
        this.cvv = cvv;
        this.expDate = expDate;
    }
    
    public int withdraw(int customerId, int partPrice) throws SQLException {
        String sql = "SELECT BALANCE FROM CREDIT WHERE CUSTOMERID=?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int currentBalance = rs.getInt("BALANCE");
            if (currentBalance <= partPrice) {
                ps.close();
                return -2;
            }
            int updatedBalance =  currentBalance - partPrice;
            sql = "UPDATE CREDIT SET BALANCE=? WHERE CUSTOMERID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, updatedBalance);
            ps.setInt(2, customerId);
            ps.executeUpdate();
            ps.close();
            return updatedBalance;
        } else {
            return -1;
        }
        
    }
    
    public int refund(int customerId, int partPrice) throws SQLException {
        String sql = "SELECT BALANCE FROM CREDIT WHERE CUSTOMERID=?";
        int notValidOrBalance;
        ps = conn.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int currentBalance = rs.getInt("BALANCE");
            int updatedBalance = currentBalance + partPrice;
            sql = "UPDATE CREDIT SET BALANCE=? WHERE CUSTOMERID=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, updatedBalance);
            ps.setInt(2, customerId);
            ps.executeUpdate();
            ps.close();
            return updatedBalance;
        } else {
            return -1;
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }
    
    
    
}
