/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieticketbookings;

/**
 *
 * @author user
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

public class PaymentGUI extends JFrame {

    public PaymentGUI(final String movieName, final String movieInfo, final Set<String> selectedSeats) {
        setTitle("Payment - " + movieName);
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 255, 240)); // Light mint green

        String seatList = "";
        for (String seat : selectedSeats) {
            seatList += seat + ", ";
        }
        if (!seatList.isEmpty()) {
            seatList = seatList.substring(0, seatList.length() - 2); // remove last comma
        }

        int totalAmount = selectedSeats.size() * 150;

        JTextArea summaryArea = new JTextArea(
            " Movie: " + movieName + "\n" +
            " Details: " + movieInfo + "\n" +
            " Seats: " + seatList + "\n" +
            " Total: ₹" + totalAmount
        );
        summaryArea.setEditable(false);
        summaryArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        summaryArea.setBackground(new Color(240, 255, 240));

        JPanel paymentPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        paymentPanel.setBackground(new Color(240, 255, 240));

        final JTextField cardField = new JTextField();
        final JTextField nameField = new JTextField();
        final JPasswordField cvvField = new JPasswordField();

        paymentPanel.add(new JLabel("Card Number:"));
        paymentPanel.add(cardField);
        paymentPanel.add(new JLabel("Name on Card:"));
        paymentPanel.add(nameField);
        paymentPanel.add(new JLabel("CVV:"));
        paymentPanel.add(cvvField);

        JButton payButton = new JButton("Pay & Confirm");

        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cardField.getText().trim().isEmpty() ||
                    nameField.getText().trim().isEmpty() ||
                    String.valueOf(cvvField.getPassword()).trim().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please fill all payment details.");
                } else {
                    JOptionPane.showMessageDialog(null, "Booking Confirmed!\nEnjoy your movie!");
                    dispose();
                }
            }
        });

        add(summaryArea, BorderLayout.NORTH);
        add(paymentPanel, BorderLayout.CENTER);
        add(payButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}

