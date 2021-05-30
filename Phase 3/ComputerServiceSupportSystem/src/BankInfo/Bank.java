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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ash
 */
public class Bank {
    private String bankName;
    private ArrayList<Credit> creditCard;
    Connection conn = null;
    PreparedStatement ps = null;
    
    public Bank() throws SQLException {
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
    }
    
    public Bank(String bankName) {       
        this.bankName = bankName;
        creditCard = new ArrayList<>();
        
    }
    
    public boolean checkCredit(int customerId, int cardNumber, int partPrice) throws SQLException {
           
        ResultSet rs = null;
        String sql = "SELECT * FROM CREDIT WHERE CustomerID=?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, customerId);
        rs = ps.executeQuery(sql);
        if (rs.next() && rs.getInt("CardNumber") == cardNumber && rs.getInt("Balance") >= partPrice) {
            LocalDate expDate = rs.getObject("ExpirationDate", LocalDate.class);
            LocalDate currDate = LocalDate.now();
            ps.close();
            conn.close();
            return (expDate.compareTo(currDate) <= 0) ? true : false;
        } else {
            ps.close();
            conn.close();
            return false;
        }
    }
    
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public ArrayList<Credit> getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(ArrayList<Credit> creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "Bank{" + "bankName=" + bankName + ", creditCard=" + creditCard + "}\n";
    }
    
}
