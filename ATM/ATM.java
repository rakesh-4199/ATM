package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ATM extends JFrame implements ActionListener {
    private JTextField accountField, amountField;
    private JTextArea displayArea;
    private JButton depositButton, withdrawButton, checkBalanceButton;
    private HashMap<String, Double> accounts;

    public ATM() {
        accounts = new HashMap<>();
        accounts.put("12345", 1000.0);
        accounts.put("67890", 1500.0);

        setLayout(new FlowLayout());

        JLabel accountLabel = new JLabel("Account Number:");
        add(accountLabel);

        accountField = new JTextField(15);
        add(accountField);

        JLabel amountLabel = new JLabel("Amount:");
        add(amountLabel);

        amountField = new JTextField(10);
        add(amountField);

        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(this);
        add(checkBalanceButton);

        displayArea = new JTextArea(10, 30);
        add(new JScrollPane(displayArea));

        setTitle("ATM Machine");
        setSize(400, 300);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        String accountNumber = accountField.getText();
        double amount = Double.parseDouble(amountField.getText());

        if (!accounts.containsKey(accountNumber)) {
            displayArea.setText("Invalid account number.");
            return;
        }

        if (ae.getSource() == depositButton) {
            accounts.put(accountNumber, accounts.get(accountNumber) + amount);
            displayArea.setText("Deposited " + amount + " to account " + accountNumber);
        } else if (ae.getSource() == withdrawButton) {
            if (accounts.get(accountNumber) >= amount) {
                accounts.put(accountNumber, accounts.get(accountNumber) - amount);
                displayArea.setText("Withdrew " + amount + " from account " + accountNumber);
            } else {
                displayArea.setText("Insufficient funds.");
            }
        } else if (ae.getSource() == checkBalanceButton) {
            displayArea.setText("Balance for account " + accountNumber + ": " + accounts.get(accountNumber));
        }

        accountField.setText("");
        amountField.setText("");
    }

    public static void main(String[] args) {
        new ATM();
    }
}
