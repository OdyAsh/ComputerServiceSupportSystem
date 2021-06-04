/*
 * Group 1: Computer Service Support System (24)
 */
package User.CustomerGUI;

import ExceptionHandling.MyException;
import User.Customer;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ash
 */
public class OrderCreationUI extends javax.swing.JFrame {

    /**
     * Creates new form OrderCreationUI
     */
    ValidateOrder vo;
    ValidatePayment vp;
    private String part;
    private int price;
    private int cardNumber;
    private String paymentType;
    private int notValidOrBalance;
    
    public OrderCreationUI() throws SQLException {
        initComponents();
        customerIdLabel.setText("id here");
        vo = new ValidateOrder();
    }
    
    public OrderCreationUI(Customer c) throws SQLException {
        vo = new ValidateOrder(c);
        vp = new ValidatePayment(c);
        initComponents();
        customerIdLabel.setText(String.valueOf(vo.getPid()));
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cashOrCreditGroup = new javax.swing.ButtonGroup();
        paymentMethodLabel = new javax.swing.JLabel();
        customerIdLabel = new javax.swing.JLabel();
        yourId = new javax.swing.JLabel();
        partNameLabel = new javax.swing.JLabel();
        partNameField = new javax.swing.JTextField();
        priceLabel = new javax.swing.JLabel();
        formName1 = new javax.swing.JLabel();
        cashRadioButton = new javax.swing.JRadioButton();
        creditRadioButton = new javax.swing.JRadioButton();
        cardNumberLabel = new javax.swing.JLabel();
        cardNumberField = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();
        priceDisplayLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create an order");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        paymentMethodLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        paymentMethodLabel.setText("Payment Method");
        paymentMethodLabel.setEnabled(false);

        customerIdLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        customerIdLabel.setText("...");

        yourId.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        yourId.setText("your id: ");

        partNameLabel.setText("Part name:");

        partNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                partNameFieldFocusLost(evt);
            }
        });
        partNameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                partNameFieldMouseClicked(evt);
            }
        });
        partNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partNameFieldActionPerformed(evt);
            }
        });

        priceLabel.setText("Price (EGP):");

        formName1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        formName1.setText("Order Creation Form");

        cashOrCreditGroup.add(cashRadioButton);
        cashRadioButton.setText("Cash");
        cashRadioButton.setEnabled(false);
        cashRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashRadioButtonActionPerformed(evt);
            }
        });

        cashOrCreditGroup.add(creditRadioButton);
        creditRadioButton.setText("Credit");
        creditRadioButton.setEnabled(false);
        creditRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditRadioButtonActionPerformed(evt);
            }
        });

        cardNumberLabel.setText("Card number:");
        cardNumberLabel.setEnabled(false);

        cardNumberField.setText("...");
        cardNumberField.setEnabled(false);
        cardNumberField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cardNumberFieldFocusLost(evt);
            }
        });
        cardNumberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardNumberFieldActionPerformed(evt);
            }
        });

        submitButton.setText("Submit");
        submitButton.setEnabled(false);
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        priceDisplayLabel.setText("...");

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(paymentMethodLabel)
                .addGap(187, 187, 187))
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(submitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                        .addComponent(cardNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cashRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cardNumberLabel)
                            .addComponent(creditRadioButton)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(priceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(priceDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(84, 84, 84))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(partNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(partNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(formName1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(yourId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(customerIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(formName1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerIdLabel)
                    .addComponent(yourId))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(partNameLabel)
                    .addComponent(partNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLabel)
                    .addComponent(priceDisplayLabel))
                .addGap(32, 32, 32)
                .addComponent(paymentMethodLabel)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cashRadioButton)
                    .addComponent(creditRadioButton))
                .addGap(18, 18, 18)
                .addComponent(cardNumberLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public String getPartInfo() {
        return partNameField.getText();
    }
    public String getPaymentMethod() {
        return cashOrCreditGroup.getSelection().getActionCommand();
    }
    public int getCardNumber() throws MyException {
        if (cardNumberField.getText().equals("")) {
            throw new MyException("Please don't leave the field empty...");
        }
        try {
            int cn = Integer.parseInt(cardNumberField.getText());
            return cn;
        } catch (NumberFormatException nfe) {
            throw new MyException("Please don't enter characters other than integers");
        }
    }
    private void partNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_partNameFieldFocusLost
        part = getPartInfo();
        try {
            price = vo.checkPartAvailability(part);
            priceDisplayLabel.setText(String.valueOf(price));
            paymentMethodLabel.setEnabled(true);
            cashRadioButton.setEnabled(true);
            creditRadioButton.setEnabled(true);  
        } catch (MyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid input", JOptionPane.WARNING_MESSAGE);
            priceDisplayLabel.setText("...");
            paymentMethodLabel.setEnabled(false);
            cashRadioButton.setEnabled(false);
            creditRadioButton.setEnabled(false);
            submitButton.setEnabled(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error accessing parts' database...", "Database Error", JOptionPane.WARNING_MESSAGE);
            priceDisplayLabel.setText("...");
            paymentMethodLabel.setEnabled(false);
            cashRadioButton.setEnabled(false);
            creditRadioButton.setEnabled(false);
            submitButton.setEnabled(false);
        }
    }//GEN-LAST:event_partNameFieldFocusLost
    
    private void creditRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditRadioButtonActionPerformed
        creditRadioButton.setActionCommand(creditRadioButton.getText());
        cardNumberLabel.setEnabled(true);
        cardNumberField.setEnabled(true);
    }//GEN-LAST:event_creditRadioButtonActionPerformed

    private void cashRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashRadioButtonActionPerformed
        cashRadioButton.setActionCommand(cashRadioButton.getText());
        cardNumberLabel.setEnabled(false);
        cardNumberField.setEnabled(false);
        submitButton.setEnabled(true);
    }//GEN-LAST:event_cashRadioButtonActionPerformed

    private void cardNumberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardNumberFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardNumberFieldActionPerformed

    private void cardNumberFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cardNumberFieldFocusLost
        try {
            cardNumber = getCardNumber();
            submitButton.setEnabled(true);
        } catch (MyException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "invalid Input", JOptionPane.ERROR_MESSAGE);
            submitButton.setEnabled(false);
        }
    }//GEN-LAST:event_cardNumberFieldFocusLost

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        paymentType = getPaymentMethod();
        if (paymentType.equals("Credit")) {
            try {
                notValidOrBalance = vp.checkCredit(vp.getPid(), cardNumber, price);
                if (notValidOrBalance < 0) {
                    JOptionPane.showMessageDialog(this, vp.returnErrorMessage(), "Credit card issue", JOptionPane.ERROR_MESSAGE);
                } else {
                    String orderConfirmation = vo.createOrder(vo.getPid(), part, price);
                    orderConfirmation += "\nPlease visit the nearest branch to deliver us the part that you want to repair.";
                    JOptionPane.showMessageDialog(this, orderConfirmation, "Order Created!", JOptionPane.INFORMATION_MESSAGE);
                }   
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "There was a problem accessing the Bank's database, try later...", "Database problem", JOptionPane.ERROR_MESSAGE);
            }
        } else if (paymentType.equals("Cash")) {
            try { 
                String orderConfirmation = vo.createOrder(vo.getPid(), part, price);
                orderConfirmation += "\nNote, since you're paying with cash, please visit the nearest branch \nto pay and give us the part that you want to repair.";
                JOptionPane.showMessageDialog(this, orderConfirmation, "Order Created!", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "There was a problem accessing the Bank's database, try later...", "Database problem", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void partNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partNameFieldActionPerformed

    }//GEN-LAST:event_partNameFieldActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        partNameField.setFocusable(false); //removes focus from textField when mouse is clicked anywhere else
        
    }//GEN-LAST:event_formMouseClicked

    private void partNameFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_partNameFieldMouseClicked
         partNameField.setFocusable(true); //make the field focusable again when customer clicks on it
         partNameField.requestFocusInWindow(); //moves the mouse cursor to the field to avoid clicking the field twice
    }//GEN-LAST:event_partNameFieldMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        HomeCustomer hc;
        try {
            hc = new HomeCustomer(vo.getCustomer());
            hc.setVisible(true);
            this.dispose(); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error opening the database...", "Database Problem", JOptionPane.ERROR_MESSAGE);;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderCreationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderCreationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderCreationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderCreationUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new OrderCreationUI().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(OrderCreationUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cardNumberField;
    private javax.swing.JLabel cardNumberLabel;
    private javax.swing.ButtonGroup cashOrCreditGroup;
    private javax.swing.JRadioButton cashRadioButton;
    private javax.swing.JRadioButton creditRadioButton;
    private javax.swing.JLabel customerIdLabel;
    private javax.swing.JLabel formName1;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField partNameField;
    private javax.swing.JLabel partNameLabel;
    private javax.swing.JLabel paymentMethodLabel;
    private javax.swing.JLabel priceDisplayLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel yourId;
    // End of variables declaration//GEN-END:variables
}
