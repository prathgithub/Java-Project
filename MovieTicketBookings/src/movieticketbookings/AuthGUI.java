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
import java.io.*;

public class AuthGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    private static final String CREDENTIALS_FILE = "users.csv";

    public AuthGUI() {
        setTitle("Login or Sign Up");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));
        getContentPane().setBackground(new Color(220, 240, 255)); // Soft blue

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        statusLabel = new JLabel("", SwingConstants.CENTER);

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        add(new JLabel("Username:", SwingConstants.CENTER));
        add(usernameField);
        add(new JLabel("Password:", SwingConstants.CENTER));
        add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);
        add(buttonPanel);

        add(statusLabel);

        // Add Action Listeners using anonymous classes (no lambdas)
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });

        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (checkCredentials(username, password)) {
            statusLabel.setText("Login successful!");
            dispose(); // Close login window
            new MovieSelectionGUI(); // Open movie selection
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

    private void signUp() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (checkCredentials(username, password)) {
            statusLabel.setText("User already exists.");
        } else if (addUser(username, password)) {
            statusLabel.setText("Signup successful. Please login.");
        } else {
            statusLabel.setText("Signup failed.");
        }
    }

    private boolean checkCredentials(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(CREDENTIALS_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2 && data[0].equals(username) && data[1].equals(password)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Could not read users file.");
        }
        return false;
    }

    private boolean addUser(String username, String password) {
        try {
            FileWriter fw = new FileWriter(CREDENTIALS_FILE, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.println(username + "," + password);
            out.close();
            return true;
        } catch (IOException e) {
            System.out.println("Could not write to users file.");
        }
        return false;
    }

    public static void main(String[] args) {
        new AuthGUI(); // No lambda, direct call
    }
}

