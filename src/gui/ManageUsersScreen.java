package gui;
import db.Database;
import models.User;
import gui.LoginScreen;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageUsersScreen extends JFrame {
    private JTable usersTable;
    private JButton addUserButton;
    private JButton updateUserButton;
    private JButton deleteUserButton;

    public ManageUsersScreen() {
        setTitle("Kullanıcıları Yönet");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        addUserButton = new JButton("Kullanıcı Ekle");
        addUserButton.setBounds(10, 10, 150, 25);
        add(addUserButton);

        updateUserButton = new JButton("Kullanıcı Güncelle");
        updateUserButton.setBounds(170, 10, 150, 25);
        add(updateUserButton);

        deleteUserButton = new JButton("Kullanıcı Sil");
        deleteUserButton.setBounds(330, 10, 150, 25);
        add(deleteUserButton);

        usersTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(usersTable);
        scrollPane.setBounds(10, 50, 760, 500);
        add(scrollPane);

        loadUsers();

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserDialog addUserDialog = new AddUserDialog(ManageUsersScreen.this);
                addUserDialog.setVisible(true);
                loadUsers();
            }
        });

        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = usersTable.getSelectedRow();
                if (selectedRow != -1) {
                    int userId = (int) usersTable.getValueAt(selectedRow, 0);
                    UpdateUserDialog updateUserDialog = new UpdateUserDialog(ManageUsersScreen.this, userId);
                    updateUserDialog.setVisible(true);
                    loadUsers();
                }
            }
        });

        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = usersTable.getSelectedRow();
                if (selectedRow != -1) {
                    int userId = (int) usersTable.getValueAt(selectedRow, 0);
                    Database.deleteUser(userId);
                    loadUsers();
                }
            }
        });
    }

    private void loadUsers() {
        List<User> users = Database.getAllUsers();
        String[] columnNames = {"ID", "Kullanıcı Adı", "Şifre", "Rol"};
        Object[][] data = new Object[users.size()][4];

        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i).getId();
            data[i][1] = users.get(i).getUsername();
            data[i][2] = users.get(i).getPassword();
            data[i][3] = users.get(i).getRole();
        }

        usersTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
