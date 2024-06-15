package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import db.Database;

public class RegisterScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterScreen() {
        setTitle("Kayıt Ol");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        registerButton = new JButton("Kayıt Ol");
        registerButton.setBounds(150, 150, 100, 25);
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                Database.addUser(username, password, "user"); 
                JOptionPane.showMessageDialog(null, "Kayıt başarılı. Şimdi giriş yapabilirsiniz.");
                dispose();
            }
        });
    }
}
