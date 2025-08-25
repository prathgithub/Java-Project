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

public class MovieSelectionGUI extends JFrame {

    private JComboBox<String> movieDropdown;
    private JTextArea movieDetails;
    private JButton nextButton;

    private final Map<String, String> movies = new HashMap<String, String>();

    public MovieSelectionGUI() {
        setTitle("Select a Movie");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 255)); // Light lavender

        // Add movies
        movies.put("The Matrix", "Sci-Fi | 5:00 PM | Screen 1");
        movies.put("Inception", "Action | 7:00 PM | Screen 2");
        movies.put("Interstellar", "Adventure | 8:30 PM | Screen 3");

        movieDropdown = new JComboBox<String>(movies.keySet().toArray(new String[0]));
        movieDetails = new JTextArea(4, 20);
        movieDetails.setEditable(false);
        movieDetails.setBackground(new Color(230, 230, 250));
        updateMovieDetails((String) movieDropdown.getSelectedItem());

        nextButton = new JButton("Next: Book Seats");

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(new Color(245, 245, 255));
        top.add(new JLabel("Select a Movie:", SwingConstants.CENTER), BorderLayout.NORTH);
        top.add(movieDropdown, BorderLayout.CENTER);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(movieDetails), BorderLayout.CENTER);
        add(nextButton, BorderLayout.SOUTH);

        // Event Listeners using anonymous inner classes
        movieDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedMovie = (String) movieDropdown.getSelectedItem();
                updateMovieDetails(selectedMovie);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedMovie = (String) movieDropdown.getSelectedItem();
                String movieInfo = movies.get(selectedMovie);
                dispose();
                new SeatBookingGUI(selectedMovie, movieInfo);
            }
        });

        setVisible(true);
    }

    private void updateMovieDetails(String movie) {
        String info = movies.get(movie);
        movieDetails.setText("Movie: " + movie + "\nDetails: " + info);
    }

    public static void main(String[] args) {
        new MovieSelectionGUI();
    }
}
