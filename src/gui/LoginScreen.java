package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import db.Database;
import models.User;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton; 

    public LoginScreen() {
        setTitle("Giriş Yap");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Kullanıcı Adı:");
        usernameLabel.setBounds(50, 50, 80, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 160, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Şifre:");
        passwordLabel.setBounds(50, 100, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 160, 25);
        add(passwordField);

        loginButton = new JButton("Giriş Yap");
        loginButton.setBounds(50, 150, 100, 25);
        add(loginButton);

        registerButton = new JButton("Kayıt Ol"); 
        registerButton.setBounds(210, 150, 100, 25);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                User user = Database.authenticateUser(username, password);
                if (user != null) {
                    if ("admin".equals(user.getRole())) {
                        AdminScreen adminScreen = new AdminScreen();
                        adminScreen.setVisible(true);
                    } else {
                        UserScreen userScreen = new UserScreen();
                        userScreen.setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Geçersiz kullanıcı adı veya şifre");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterScreen registerScreen = new RegisterScreen();
                registerScreen.setVisible(true);
            }
        });
    }
}
