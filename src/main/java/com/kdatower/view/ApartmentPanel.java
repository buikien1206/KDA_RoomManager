package com.kdatower.view;

import com.kdatower.model.Apartment;
import com.kdatower.manager.ApartmentManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ApartmentPanel extends JPanel {
    private JTextField idField, ownerField, areaField, numPeopleField, accountField;
    private JSpinner dateSpinner;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private JComboBox<String> buildingBox, floorBox;
    private JRadioButton statusOccupied, statusEmpty;
    private ButtonGroup statusGroup;
    private JTable table;
    private DefaultTableModel model;
    private ApartmentManager manager;

    public ApartmentPanel() {
        setLayout(new BorderLayout(0, 5));
        manager = new ApartmentManager();

        // --- Thông Tin Căn Hộ ---
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Căn Hộ"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 12, 5, 12);
        gbc.anchor = GridBagConstraints.WEST;

        idField         = new JTextField(7);
        ownerField      = new JTextField(12);
        areaField       = new JTextField(7);
        numPeopleField  = new JTextField(4);
        accountField    = new JTextField(8);

        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
        dateSpinner.setPreferredSize(new Dimension(100, 25));

        buildingBox    = new JComboBox<>(new String[]{"A", "B", "C", "D", "E"});
        floorBox       = new JComboBox<>(new String[]{"1", "2", "3", "4"});
        statusOccupied = new JRadioButton("Đã ở");
        statusEmpty    = new JRadioButton("Bỏ trống");
        statusGroup    = new ButtonGroup();
        statusGroup.add(statusOccupied);
        statusGroup.add(statusEmpty);
        statusOccupied.setSelected(true);

        // Row 1
        gbc.gridx = 0; gbc.gridy = 0; infoPanel.add(new JLabel("Mã Căn Hộ:"), gbc);
        gbc.gridx = 1;                 infoPanel.add(idField, gbc);
        gbc.gridx = 2;                 infoPanel.add(new JLabel("Chủ Căn Hộ:"), gbc);
        gbc.gridx = 3;                 infoPanel.add(ownerField, gbc);
        gbc.gridx = 4;                 infoPanel.add(new JLabel("Diện Tích:"), gbc);
        gbc.gridx = 5;                 infoPanel.add(areaField, gbc);

        // Row 2
        gbc.gridx = 0; gbc.gridy = 1;  infoPanel.add(new JLabel("Tòa:"), gbc);
        gbc.gridx = 1;                 infoPanel.add(buildingBox, gbc);
        gbc.gridx = 2;                 infoPanel.add(new JLabel("Tầng:"), gbc);
        gbc.gridx = 3;                 infoPanel.add(floorBox, gbc);
        gbc.gridx = 4;                 infoPanel.add(new JLabel("Số Người Ở:"), gbc);
        gbc.gridx = 5;                 infoPanel.add(numPeopleField, gbc);

        // Row 3
        gbc.gridx = 0; gbc.gridy = 2;  infoPanel.add(new JLabel("Ngày Vào Ở:"), gbc);
        gbc.gridx = 1;                 infoPanel.add(dateSpinner, gbc);
        gbc.gridx = 2;                 infoPanel.add(new JLabel("Trạng Thái:"), gbc);
        gbc.gridx = 3;                 infoPanel.add(statusOccupied, gbc);
        gbc.gridx = 4;                 infoPanel.add(statusEmpty, gbc);
        gbc.gridx = 5;                 infoPanel.add(new JLabel("Tài Khoản:"), gbc);
        gbc.gridx = 6;                 infoPanel.add(accountField, gbc);

        add(infoPanel, BorderLayout.NORTH);

        // --- Nút Chức Năng ---
        JPanel btnPanel = new JPanel();
        JButton addBtn     = new JButton("Thêm");
        JButton updateBtn  = new JButton("Cập Nhật");
        JButton deleteBtn  = new JButton("Xóa");
        JButton sortBtn    = new JButton("Sắp Xếp");
        JButton searchBtn  = new JButton("Tìm Kiếm");
        JButton refreshBtn = new JButton("Làm Mới");
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(sortBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(refreshBtn);
        add(btnPanel, BorderLayout.CENTER);

        // --- Bảng Dữ Liệu ---
        model = new DefaultTableModel(new String[]{
            "STT","Mã Căn Hộ","Chủ Căn Hộ","Diện Tích","Tòa","Tầng",
            "Trạng Thái","Số Người","Ngày Vào","Tài Khoản"
        }, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(new JScrollPane(table), BorderLayout.SOUTH);

        loadTable(manager.getAll());

        // --- Sự Kiện ---
        addBtn.addActionListener(e -> create());
        updateBtn.addActionListener(e -> modify());
        deleteBtn.addActionListener(e -> remove());

        sortBtn.addActionListener(e -> {
            String[] opts = {"Mã căn hộ","Chủ hộ","Diện tích","Tòa","Tầng","Số người"};
            String sel = (String) JOptionPane.showInputDialog(
                this, "Chọn tiêu chí sắp xếp:", "Sắp Xếp Căn Hộ",
                JOptionPane.QUESTION_MESSAGE, null, opts, opts[0]);
            if (sel != null) {
                switch (sel) {
                    case "Mã căn hộ":   manager.sortById();        break;
                    case "Chủ hộ":      manager.sortByOwner();     break;
                    case "Diện tích":   manager.sortByArea();      break;
                    case "Tòa":         manager.sortByBuilding();  break;
                    case "Tầng":        manager.sortByFloor();     break;
                    case "Số người":    manager.sortByNumPeople(); break;
                }
                loadTable(manager.getAll());
                clearForm();
            }
        });

        searchBtn.addActionListener(e -> search());
        refreshBtn.addActionListener(e -> {
            loadTable(manager.getAll());
            clearForm();
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r < 0) return;
                idField.setText(model.getValueAt(r,1).toString());
                ownerField.setText(model.getValueAt(r,2).toString());
                areaField.setText(model.getValueAt(r,3).toString());
                buildingBox.setSelectedItem(model.getValueAt(r,4).toString());
                floorBox.setSelectedItem(model.getValueAt(r,5).toString());
                statusOccupied.setSelected("Đã ở".equals(model.getValueAt(r,6)));
                statusEmpty.setSelected("Bỏ trống".equals(model.getValueAt(r,6)));
                numPeopleField.setText(model.getValueAt(r,7).toString());
                try {
                    Date d = dateFormat.parse(model.getValueAt(r,8).toString());
                    dateSpinner.setValue(d);
                } catch (Exception ex) {
                    dateSpinner.setValue(new Date());
                }
                accountField.setText(model.getValueAt(r,9).toString());
            }
        });
    }

    private void loadTable(List<Apartment> list) {
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            Apartment a = list.get(i);
            model.addRow(new Object[]{
                i+1, a.getId(), a.getOwner(), a.getArea(),
                a.getBuilding(), a.getFloor(), a.getStatus(),
                a.getNumPeople(), a.getDateIn(), a.getAccount()
            });
        }
    }

    private void create() {
        Apartment a = readInput();
        if (a != null) {
            manager.add(a);
            loadTable(manager.getAll());
            clearForm();
        }
    }

    private void modify() {
        int r = table.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Chọn dòng để cập nhật!");
            return;
        }
        Apartment a = readInput();
        if (a != null) {
            manager.update(r, a);
            loadTable(manager.getAll());
            clearForm();
        }
    }

    private void remove() {
        int r = table.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Chọn dòng để xóa!");
            return;
        }
        manager.delete(r);
        loadTable(manager.getAll());
        clearForm();
    }

    private void search() {
        String key = JOptionPane.showInputDialog(this, "Nhập mã hoặc chủ căn hộ:");
        if (key != null && !key.trim().isEmpty()) {
            loadTable(manager.search(key.trim()));
            clearForm();
        }
    }

    private Apartment readInput() {
        try {
            String id       = idField.getText().trim();
            String owner    = ownerField.getText().trim();
            double area     = Double.parseDouble(areaField.getText().trim());
            String building = buildingBox.getSelectedItem().toString();
            int floor       = Integer.parseInt(floorBox.getSelectedItem().toString());
            String status   = statusOccupied.isSelected() ? "Đã ở" : "Bỏ trống";
            int numPeople   = Integer.parseInt(numPeopleField.getText().trim());
            String dateIn   = dateFormat.format((Date) dateSpinner.getValue());
            String acc      = accountField.getText().trim();

            if (id.isEmpty() || owner.isEmpty() || dateIn.isEmpty())
                throw new IllegalArgumentException();
            return new Apartment(id, owner, area, building, floor, status, numPeople, dateIn, acc);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Kiểm tra lại dữ liệu nhập!");
            return null;
        }
    }

    private void clearForm() {
        idField.setText("");
        ownerField.setText("");
        areaField.setText("");
        numPeopleField.setText("");
        accountField.setText("");
        dateSpinner.setValue(new Date());
        buildingBox.setSelectedIndex(0);
        floorBox.setSelectedIndex(0);
        statusOccupied.setSelected(true);
        table.clearSelection();
    }
}
