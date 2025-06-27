package com.kdatower.view;
import com.kdatower.manager.RoomManager;
import com.kdatower.model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class RoomPanel extends JPanel {
    private RoomManager manager;
    private JTable table;
    private DefaultTableModel model;

    public RoomPanel() {
        manager = new RoomManager();
        setLayout(new BorderLayout());
        setBackground(new Color(169, 223, 191));

        model = new DefaultTableModel(new String[]{"ID", "Tên phòng", "Block"}, 0);
        table = new JTable(model);
        loadData();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton addBtn = new JButton("Thêm phòng");
        addBtn.addActionListener(e -> addRoom());
        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        model.setRowCount(0);
        for (Room r : manager.getAll()) {
            model.addRow(new Object[]{r.getId(), r.getName(), r.getBlockId()});
        }
    }

    private void addRoom() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField blockIdField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Tên phòng:"));
        panel.add(nameField);
        panel.add(new JLabel("Block:"));
        panel.add(blockIdField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Thêm phòng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Room r = new Room(idField.getText(), nameField.getText(), blockIdField.getText());
            manager.addRoom(r);
            loadData();
        }
    }
}
