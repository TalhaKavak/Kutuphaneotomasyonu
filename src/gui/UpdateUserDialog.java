package gui;
import models.User;
import db.Database;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField roleField;
    private JButton updateButton;
    private int userId;

    public UpdateUserDialog(JFrame parent, int userId) {
        super(parent, "Kullanıcı Güncelle", true);
        this.userId = userId;
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(null);

        User user = Database.getUserById(userId);

        JLabel usernameLabel = new JLabel("Kullanıcı Adı:");
        usernameLabel.setBounds(10, 10, 80, 25);
        add(usernameLabel);

        usernameField = new JTextField(user.getUsername());
        usernameField.setBounds(100, 10, 160, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Şifre:");
        passwordLabel.setBounds(10, 40, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField(user.getPassword());
        passwordField.setBounds(100, 40, 160, 25);
        add(passwordField);

        JLabel roleLabel = new JLabel("Rol:");
        roleLabel.setBounds(10, 70, 80, 25);
        add(roleLabel);

        roleField = new JTextField(user.getRole());
        roleField.setBounds(100, 70, 160, 25);
        add(roleField);

        updateButton = new JButton("Güncelle");
        updateButton.setBounds(10, 110, 250, 25);
        add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = roleField.getText();

                Database.updateUser(userId, username, password, role);
                dispose();
            }
        });
    }
}
