package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import models.Member;
import db.Database;

public class ManageMembersScreen extends JFrame {
    private JTable membersTable;
    private JButton addMemberButton;
    private JButton updateMemberButton;
    private JButton deleteMemberButton;

    public ManageMembersScreen() {
        setTitle("Üyeleri Yönet");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        addMemberButton = new JButton("Üye Ekle");
        addMemberButton.setBounds(10, 10, 150, 25);
        add(addMemberButton);

        updateMemberButton = new JButton("Üye Güncelle");
        updateMemberButton.setBounds(170, 10, 150, 25);
        add(updateMemberButton);

        deleteMemberButton = new JButton("Üye Sil");
        deleteMemberButton.setBounds(330, 10, 150, 25);
        add(deleteMemberButton);

        membersTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(membersTable);
        scrollPane.setBounds(10, 50, 760, 500);
        add(scrollPane);

        loadMembers();

        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMemberDialog addMemberDialog = new AddMemberDialog(ManageMembersScreen.this);
                addMemberDialog.setVisible(true);
                loadMembers();
            }
        });

        updateMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = membersTable.getSelectedRow();
                if (selectedRow != -1) {
                    int memberId = (int) membersTable.getValueAt(selectedRow, 0);
                    UpdateMemberDialog updateMemberDialog = new UpdateMemberDialog(ManageMembersScreen.this, memberId);
                    updateMemberDialog.setVisible(true);
                    loadMembers();
                }
            }
        });

        deleteMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = membersTable.getSelectedRow();
                if (selectedRow != -1) {
                    int memberId = (int) membersTable.getValueAt(selectedRow, 0);
                    Database.deleteMember(memberId);
                    loadMembers();
                }
            }
        });
    }

    private void loadMembers() {
        List<Member> members = Database.getAllMembers();
        String[] columnNames = {"ID", "İsim", "Üye Tipi"};
        Object[][] data = new Object[members.size()][3];

        for (int i = 0; i < members.size(); i++) {
            data[i][0] = members.get(i).getId();
            data[i][1] = members.get(i).getName();
            data[i][2] = members.get(i).getMemberType();
        }

        membersTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}
