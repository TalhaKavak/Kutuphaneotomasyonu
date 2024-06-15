package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminScreen extends JFrame {
    private JButton manageUsersButton;
    private JButton manageBooksButton;
    private JButton manageMembersButton;
    private JButton logoutButton;

    public AdminScreen() {
        setTitle("Kütüphane Otomasyonu - Yönetici");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        manageUsersButton = new JButton("Kullanıcıları Yönet");
        manageUsersButton.setBounds(200, 50, 200, 30);
        add(manageUsersButton);

        manageBooksButton = new JButton("Kitapları Yönet");
        manageBooksButton.setBounds(200, 100, 200, 30);
        add(manageBooksButton);

        manageMembersButton = new JButton("Üyeleri Yönet");
        manageMembersButton.setBounds(200, 150, 200, 30);
        add(manageMembersButton);

        logoutButton = new JButton("Çıkış Yap");
        logoutButton.setBounds(200, 200, 200, 30);
        add(logoutButton);

        manageUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageUsersScreen manageUsersScreen = new ManageUsersScreen();
                manageUsersScreen.setVisible(true);
            }
        });

        manageBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageBooksScreen manageBooksScreen = new ManageBooksScreen();
                manageBooksScreen.setVisible(true);
            }
        });

        manageMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageMembersScreen manageMembersScreen = new ManageMembersScreen();
                manageMembersScreen.setVisible(true);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
                dispose();
            }
        });
    }
}
