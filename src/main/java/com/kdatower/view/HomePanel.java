package com.kdatower.view;

import com.kdatower.manager.RoomManager;
import com.kdatower.model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class HomePanel extends JPanel {
    // Các trường nhập liệu
    private JTextField idField, nameField, blockIdField;
    private JTable table;
    private DefaultTableModel tableModel;
    private RoomManager manager;

    public HomePanel() {
        setLayout(new BorderLayout());

        // Sidebar giống như hướng dẫn phía trên...
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(21, 67, 96));
        sidebar.setPreferredSize(new Dimension(240, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        // ... (code sidebar như mẫu ở trên)

        add(sidebar, BorderLayout.WEST);

        // === MAIN CONTENT ===
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(new Color(240, 242, 245));
        mainContent.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 24));
        add(mainContent, BorderLayout.CENTER);

        // ========== FORM NHẬP ==========
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220), 1),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);

        idField = new JTextField(10);
        nameField = new JTextField(14);
        blockIdField = new JTextField(8);

        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("Mã Phòng:"), gbc);
        gbc.gridx = 1; form.add(idField, gbc);
        gbc.gridx = 2; form.add(new JLabel("Tên Phòng:"), gbc);
        gbc.gridx = 3; form.add(nameField, gbc);
        gbc.gridx = 4; form.add(new JLabel("Block:"), gbc);
        gbc.gridx = 5; form.add(blockIdField, gbc);

        mainContent.add(form, BorderLayout.NORTH);

        // ========== NÚT CHỨC NĂNG ==========
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(240, 242, 245));

        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Cập nhật");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm Mới");

        btnPanel.add(btnAdd); btnPanel.add(btnEdit); btnPanel.add(btnDelete); btnPanel.add(btnClear);

        mainContent.add(btnPanel, BorderLayout.CENTER);

        // ========== BẢNG ==========
        String[] columns = {"Mã Phòng", "Tên Phòng", "Block"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        table.getTableHeader().setBackground(new Color(21, 67, 96));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        mainContent.add(scroll, BorderLayout.SOUTH);

        // ========== XỬ LÝ CRUD ==========
        manager = new RoomManager();
        loadTable();

        btnAdd.addActionListener(e -> addRoom());
        btnEdit.addActionListener(e -> editRoom());
        btnDelete.addActionListener(e -> deleteRoom());
        btnClear.addActionListener(e -> clearForm());

        // Khi click vào 1 dòng trên bảng, load dữ liệu vào form
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    idField.setText(tableModel.getValueAt(row, 0).toString());
                    nameField.setText(tableModel.getValueAt(row, 1).toString());
                    blockIdField.setText(tableModel.getValueAt(row, 2).toString());
                }
            }
        });
    }

    // ========== LOAD TABLE ==========
    private void loadTable() {
        tableModel.setRowCount(0);
        List<Room> list = manager.getAll();
        for (Room r : list) {
            tableModel.addRow(new Object[]{r.getId(), r.getName(), r.getBlockId()});
        }
    }

    // ========== CRUD ==========
    private void addRoom() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String block = blockIdField.getText().trim();
        if (id.isEmpty() || name.isEmpty() || block.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống trường nào!");
            return;
        }
        manager.addRoom(new Room(id, name, block));
        loadTable();
        clearForm();
    }

    private void editRoom() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng để cập nhật!");
            return;
        }
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String block = blockIdField.getText().trim();
        if (id.isEmpty() || name.isEmpty() || block.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống trường nào!");
            return;
        }
        // Cập nhật list trong manager
        List<Room> list = manager.getAll();
        list.set(row, new Room(id, name, block));
        com.kdatower.dao.RoomXML.writeRooms(list);
        loadTable();
        clearForm();
    }

    private void deleteRoom() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Chọn dòng để xóa!");
            return;
        }
        List<Room> list = manager.getAll();
        list.remove(row);
        com.kdatower.dao.RoomXML.writeRooms(list);
        loadTable();
        clearForm();
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        blockIdField.setText("");
        table.clearSelection();
    }
}
