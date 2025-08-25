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
import java.util.*;
import movieticketbookings.SeatBookingGUI;
public class SeatBookingGUI extends JFrame {

    private String movieName;
    private String movieInfo;
    private Set<String> selectedSeats = new HashSet<String>();
    private Set<String> bookedSeats = new HashSet<String>();

    public SeatBookingGUI(String movieName, String movieInfo) {
        this.movieName = movieName;
        this.movieInfo = movieInfo;

        // Mocked booked seats
        bookedSeats.add("A1");
        bookedSeats.add("B2");

        setTitle("Select Seats for " + movieName);
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(255, 250, 240)); // Light peach

        JPanel seatPanel = new JPanel(new GridLayout(5, 5, 10, 10));
        final String[] rows = {"A", "B", "C", "D", "E"};

        for (int i = 0; i < rows.length; i++) {
            for (int col = 1; col <= 5; col++) {
                final String seatID = rows[i] + col;
                final JButton seatButton = new JButton(seatID);

                if (bookedSeats.contains(seatID)) {
                    seatButton.setEnabled(false);
                    seatButton.setBackground(Color.RED);
                } else {
                    seatButton.setBackground(Color.GREEN);
                    seatButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            toggleSeat(seatButton, seatID);
                        }
                    });
                }

                seatPanel.add(seatButton);
            }
        }

        JButton proceedButton = new JButton("Proceed to Payment");

        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedSeats.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select at least one seat.");
                } else {
                    dispose();
                    new PaymentGUI(movieName, movieInfo, selectedSeats);
                }
            }
        });

        add(new JLabel("Select Your Seats:", SwingConstants.CENTER), BorderLayout.NORTH);
        add(seatPanel, BorderLayout.CENTER);
        add(proceedButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void toggleSeat(JButton seatButton, String seatID) {
        if (selectedSeats.contains(seatID)) {
            selectedSeats.remove(seatID);
            seatButton.setBackground(Color.GREEN);
        } else {
            selectedSeats.add(seatID);
            seatButton.setBackground(Color.ORANGE);
        }
    }
}

