package com.kdatower.view;

import com.kdatower.model.Resident;
import com.kdatower.manager.ResidentManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ResidentPanel extends JPanel {
    private JTextField nameField, hometownField, phoneField, cccdField, householdField, dobField;
    private JCheckBox maleBox, femaleBox;
    private JComboBox<String> aptBox;
    private JTable table;
    private DefaultTableModel model;
    private ResidentManager mgr;

    public ResidentPanel() {
        setLayout(new BorderLayout());
        mgr = new ResidentManager();

        // ==== Form =====
        JPanel f = new JPanel(new GridBagLayout());
        f.setBorder(BorderFactory.createTitledBorder("Thông Tin Cư Dân"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,12,4,12);
        gbc.anchor = GridBagConstraints.WEST;

        nameField = new JTextField(12);
        hometownField = new JTextField(10);
        phoneField = new JTextField(10);
        cccdField = new JTextField(10);
        householdField = new JTextField(8);
        dobField = new JTextField(8);
        JButton dobBtn = new JButton("📅");

        maleBox = new JCheckBox("Nam");
        femaleBox = new JCheckBox("Nữ");
        ButtonGroup bg = new ButtonGroup();
        bg.add(maleBox); bg.add(femaleBox);

        aptBox = new JComboBox<>();
        // load danh sách căn hộ từ file room_data.xml
        for (com.kdatower.model.Room r: new com.kdatower.manager.RoomManager().getAll()) {
            aptBox.addItem(r.getId());
        }

        // Row 1
        gbc.gridx=0; gbc.gridy=0; f.add(new JLabel("Họ và tên:"),gbc);
        gbc.gridx=1; f.add(nameField,gbc);
        gbc.gridx=2; f.add(new JLabel("Mã căn hộ:"),gbc);
        gbc.gridx=3; f.add(aptBox,gbc);
        gbc.gridx=4; f.add(new JLabel("Quê quán:"),gbc);
        gbc.gridx=5; f.add(hometownField,gbc);
        gbc.gridx=6; f.add(new JLabel("Số điện thoại:"),gbc);
        gbc.gridx=7; f.add(phoneField,gbc);

        // Row 2
        gbc.gridx=0; gbc.gridy=1; f.add(new JLabel("Giới tính:"),gbc);
        gbc.gridx=1; f.add(maleBox,gbc);
        gbc.gridx=2; f.add(femaleBox,gbc);
        gbc.gridx=3; f.add(new JLabel("Ngày sinh:"),gbc);
        gbc.gridx=4; f.add(dobField,gbc);
        gbc.gridx=5; f.add(dobBtn,gbc);
        gbc.gridx=6; f.add(new JLabel("CCCD:"),gbc);
        gbc.gridx=7; f.add(cccdField,gbc);

        // Row 3
        gbc.gridx=0; gbc.gridy=2; f.add(new JLabel("Sổ hộ khẩu:"),gbc);
        gbc.gridx=1; f.add(householdField,gbc);
        JButton clear = new JButton("Clear");
        gbc.gridx=4; f.add(clear,gbc);

        add(f, BorderLayout.NORTH);

        // ==== Buttons =====
        JPanel btns = new JPanel();
        JButton add = new JButton("Thêm"), upd = new JButton("Cập Nhật"),
                del = new JButton("Xóa"), sort = new JButton("Sắp Xếp"),
                find = new JButton("Tìm Kiếm"), ref = new JButton("Làm Mới");
        btns.add(add); btns.add(upd); btns.add(del);
        btns.add(sort); btns.add(find); btns.add(ref);
        add(btns, BorderLayout.CENTER);

        // ==== Table =====
        String[] cols = {"STT","Họ Và Tên","Ngày Sinh","Giới Tính","CCCD",
                         "Số Điện Thoại","Mã Căn Hộ","Quê Quán","Sổ Hộ Khẩu"};
        model = new DefaultTableModel(cols,0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.SOUTH);

        // Load initial
        loadTable(mgr.getAll());

        // Event CRUD
        add.addActionListener(e -> { create(); loadTable(mgr.getAll()); clearForm(); });
        upd.addActionListener(e -> { update(); loadTable(mgr.getAll()); clearForm(); });
        del.addActionListener(e -> { delete(); loadTable(mgr.getAll()); clearForm(); });
        sort.addActionListener(e -> { mgr.sortByName(); loadTable(mgr.getAll()); });
        find.addActionListener(e -> {
            String k = JOptionPane.showInputDialog(this,"Nhập tên hoặc mã căn hộ:");
            if (k!=null) loadTable(mgr.search(k));
        });
        ref.addActionListener(e -> { loadTable(mgr.getAll()); clearForm(); });
        clear.addActionListener(e -> clearForm());

        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow(); if (r<0) return;
                nameField.setText(model.getValueAt(r,1).toString());
                dobField.setText(model.getValueAt(r,2).toString());
                String g = model.getValueAt(r,3).toString();
                maleBox.setSelected("Nam".equals(g));
                femaleBox.setSelected("Nữ".equals(g));
                cccdField.setText(model.getValueAt(r,4).toString());
                phoneField.setText(model.getValueAt(r,5).toString());
                aptBox.setSelectedItem(model.getValueAt(r,6).toString());
                hometownField.setText(model.getValueAt(r,7).toString());
                householdField.setText(model.getValueAt(r,8).toString());
            }
        });
    }

    private void loadTable(List<Resident> lst) {
        model.setRowCount(0);
        for (int i=0;i<lst.size();i++){
            Resident r = lst.get(i);
            model.addRow(new Object[]{
                i+1, r.getName(), r.getDateOfBirth(), r.getGender(),
                r.getCccd(), r.getPhone(), r.getApartmentId(),
                r.getHometown(), r.getHouseholdBook()
            });
        }
    }
    private Resident readForm() {
        String g = maleBox.isSelected() ? "Nam" : femaleBox.isSelected() ? "Nữ" : "";
        return new Resident(
            nameField.getText().trim(), g, dobField.getText().trim(),
            cccdField.getText().trim(), phoneField.getText().trim(),
            aptBox.getSelectedItem().toString(),
            hometownField.getText().trim(), householdField.getText().trim()
        );
    }
    private void clearForm() {
        nameField.setText(""); dobField.setText("");
        maleBox.setSelected(false); femaleBox.setSelected(false);
        cccdField.setText(""); phoneField.setText("");
        aptBox.setSelectedIndex(0); hometownField.setText("");
        householdField.setText(""); table.clearSelection();
    }
    private void create()    { mgr.add(readForm()); }
    private void update()    {
        int r = table.getSelectedRow(); if (r<0) { JOptionPane.showMessageDialog(this,"Chọn dòng!"); return; }
        mgr.update(r, readForm());
    }
    private void delete()    {
        int r = table.getSelectedRow(); if (r<0) { JOptionPane.showMessageDialog(this,"Chọn dòng!"); return; }
        mgr.delete(r);
    }
}
