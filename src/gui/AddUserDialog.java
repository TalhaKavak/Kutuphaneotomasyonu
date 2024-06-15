package gui;
import db.Database;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField roleField;
    private JButton addButton;

    public AddUserDialog(JFrame parent) {
        super(parent, "Kullanıcı Ekle", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Kullanıcı Adı:");
        usernameLabel.setBounds(10, 10, 80, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 10, 160, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Şifre:");
        passwordLabel.setBounds(10, 40, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 40, 160, 25);
        add(passwordField);

        JLabel roleLabel = new JLabel("Rol:");
        roleLabel.setBounds(10, 70, 80, 25);
        add(roleLabel);

        roleField = new JTextField();
        roleField.setBounds(100, 70, 160, 25);
        add(roleField);

        addButton = new JButton("Ekle");
        addButton.setBounds(10, 110, 250, 25);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = roleField.getText();

                Database.addUser(username, password, role);
                dispose();
            }
        });
    }
}
