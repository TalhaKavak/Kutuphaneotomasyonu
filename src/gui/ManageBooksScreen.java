package gui;

import models.Book;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import models.Book;
import db.Database;

public class ManageBooksScreen extends JFrame {
    private JTable booksTable;
    private JButton addBookButton;
    private JButton updateBookButton;
    private JButton deleteBookButton;

    public ManageBooksScreen() {
        setTitle("Kitapları Yönet");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        addBookButton = new JButton("Kitap Ekle");
        addBookButton.setBounds(10, 10, 150, 25);
        add(addBookButton);

        updateBookButton = new JButton("Kitap Güncelle");
        updateBookButton.setBounds(170, 10, 150, 25);
        add(updateBookButton);

        deleteBookButton = new JButton("Kitap Sil");
        deleteBookButton.setBounds(330, 10, 150, 25);
        add(deleteBookButton);

        booksTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(booksTable);
        scrollPane.setBounds(10, 50, 760, 500);
        add(scrollPane);

        loadBooks();

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBookDialog addBookDialog = new AddBookDialog(ManageBooksScreen.this);
                addBookDialog.setVisible(true);
                loadBooks();
            }
        });

        updateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = booksTable.getSelectedRow();
                if (selectedRow != -1) {
                    int bookId = (int) booksTable.getValueAt(selectedRow, 0);
                    UpdateBookDialog updateBookDialog = new UpdateBookDialog(ManageBooksScreen.this, bookId);
                    updateBookDialog.setVisible(true);
                    loadBooks();
                }
            }
        });

        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = booksTable.getSelectedRow();
                if (selectedRow != -1) {
                    int bookId = (int) booksTable.getValueAt(selectedRow, 0);
                    Database.deleteBook(bookId);
                    loadBooks();
                }
            }
        });
    }

    private void loadBooks() {
        List<Book> books = Database.getAllBooks();
        String[] columnNames = {"ID", "Başlık", "Yazar", "Yıl", "ISBN", "Durum"};
        Object[][] data = new Object[books.size()][6];

        for (int i = 0; i < books.size(); i++) {
            data[i][0] = books.get(i).getId();
            data[i][1] = books.get(i).getTitle();
            data[i][2] = books.get(i).getAuthor();
            data[i][3] = books.get(i).getYear();
            data[i][4] = books.get(i).getIsbn();
            data[i][5] = books.get(i).getStatus();
        }

        booksTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}

