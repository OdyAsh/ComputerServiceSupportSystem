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
        this.bankName = "";
        creditCard = new ArrayList<>();
    }
    
    public Bank(String bankName) throws SQLException {      
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ComputerServiceSupportSystem", "csss", "csss");
        this.bankName = bankName;
        creditCard = new ArrayList<>();
        
    }
    
    public int checkCredit(int customerId, int cardNumber, int partPrice) throws SQLException {   
        String sql = "SELECT * FROM CREDIT WHERE CUSTOMERID=?";
        int notValidOrBalance;
        ps = conn.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getInt("CARDNUMBER") == cardNumber) {
                if (rs.getInt("BALANCE") >= partPrice) {
                    LocalDate expDate = rs.getObject("EXPIRATIONDATE", LocalDate.class);
                    LocalDate currDate = LocalDate.now();
                    if (expDate.compareTo(currDate) <= 0) {
                        Credit cr = new Credit();
                        notValidOrBalance = cr.withdraw(customerId, partPrice);
                    } else {
                        notValidOrBalance = -4;
                    }
                } else {
                    notValidOrBalance = -3;
                }
            } else {
                notValidOrBalance = -2;
            }
        } else {
            notValidOrBalance = -1;
        }
        ps.close();
        ;
        return notValidOrBalance;
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
