package gui;

import javax.swing.*;

public class UserScreen extends JFrame {
    public UserScreen() {
        setTitle("Kullanıcı Paneli");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Hoşgeldiniz!");
        welcomeLabel.setBounds(200, 50, 200, 30);
        add(welcomeLabel);

   
        JButton searchBooksButton = new JButton("Kitap Ara");
        searchBooksButton.setBounds(200, 100, 200, 30);
        add(searchBooksButton);

    }
}
