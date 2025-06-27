package com.kdatower.view;
import com.kdatower.manager.TenantManager;
import com.kdatower.model.Tenant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class TenantPanel extends JPanel {
    private TenantManager manager;
    private JTable table;
    private DefaultTableModel model;

    public TenantPanel() {
        manager = new TenantManager();
        setLayout(new BorderLayout());
        setBackground(new Color(169, 223, 191));

        model = new DefaultTableModel(new String[]{"ID", "Tên", "Giới tính", "Phòng"}, 0);
        table = new JTable(model);
        loadData();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton addBtn = new JButton("Thêm khách thuê");
        addBtn.addActionListener(e -> addTenant());
        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        model.setRowCount(0);
        for (Tenant t : manager.getAll()) {
            model.addRow(new Object[]{t.getId(), t.getName(), t.getGender(), t.getRoomId()});
        }
    }

    private void addTenant() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField roomIdField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Tên:"));
        panel.add(nameField);
        panel.add(new JLabel("Giới tính:"));
        panel.add(genderField);
        panel.add(new JLabel("Phòng:"));
        panel.add(roomIdField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Thêm khách thuê", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Tenant t = new Tenant(idField.getText(), nameField.getText(), genderField.getText(), roomIdField.getText());
            manager.addTenant(t);
            loadData();
        }
    }
}
