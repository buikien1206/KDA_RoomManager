package com.kdatower.view;

import com.kdatower.model.Invoice;
import com.kdatower.manager.InvoiceManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class InvoicePanel extends JPanel {
    private JTextField customerField, cashierField, dateField, totalField;
    private JComboBox<String> aptBox;
    private JCheckBox paidBox, unpaidBox;
    private JTextField idField;
    private JTable table;
    private DefaultTableModel model;
    private InvoiceManager mgr;

    public InvoicePanel() {
        setLayout(new BorderLayout());
        mgr = new InvoiceManager();

        // ==== Form ====
        JPanel f = new JPanel(new GridBagLayout());
        f.setBorder(BorderFactory.createTitledBorder("Thông Tin Danh Sách Thanh Toán"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,12,6,12);
        gbc.anchor = GridBagConstraints.WEST;

        idField = new JTextField(6);
        aptBox = new JComboBox<>();
        for (com.kdatower.model.Room r: new com.kdatower.manager.RoomManager().getAll())
            aptBox.addItem(r.getId());
        dateField = new JTextField(8);
        JButton dateBtn = new JButton("📅");
        customerField = new JTextField(10);
        totalField = new JTextField(8);
        cashierField = new JTextField(10);
        paidBox = new JCheckBox("Đã Thanh Toán");
        unpaidBox = new JCheckBox("Chưa Thanh Toán");

        // Row 1
        gbc.gridx=0; gbc.gridy=0; f.add(new JLabel("Mã Hóa Đơn:"),gbc);
        gbc.gridx=1; f.add(idField,gbc);
        gbc.gridx=2; f.add(new JLabel("Mã Căn Hộ:"),gbc);
        gbc.gridx=3; f.add(aptBox,gbc);
        gbc.gridx=4; f.add(new JLabel("Ngày Thanh Toán:"),gbc);
        gbc.gridx=5; f.add(dateField,gbc);
        gbc.gridx=6; f.add(dateBtn,gbc);

        // Row 2
        gbc.gridx=0; gbc.gridy=1; f.add(new JLabel("Khách Hàng:"),gbc);
        gbc.gridx=1; f.add(customerField,gbc);
        gbc.gridx=2; f.add(new JLabel("Tổng Thanh Toán:"),gbc);
        gbc.gridx=3;
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        p.add(totalField); p.add(new JLabel(" VNĐ"));
        f.add(p,gbc);
        gbc.gridx=4; f.add(new JLabel("Thu ngân:"),gbc);
        gbc.gridx=5; f.add(cashierField,gbc);

        // Row 3
        gbc.gridx=0; gbc.gridy=2; f.add(new JLabel("Trạng Thái:"),gbc);
        gbc.gridx=1; f.add(paidBox,gbc);
        gbc.gridx=2; f.add(unpaidBox,gbc);
        JButton clear = new JButton("Clear");
        gbc.gridx=4; f.add(clear,gbc);

        add(f, BorderLayout.NORTH);

        // ==== Buttons ====
        JPanel btns = new JPanel();
        JButton add = new JButton("Thêm"), upd = new JButton("Cập Nhật"),
                del = new JButton("Xóa"), sort = new JButton("Sắp Xếp"),
                find = new JButton("Tìm Kiếm"), ref = new JButton("Làm Mới"),
                pay = new JButton("Thanh Toán");
        btns.add(add); btns.add(upd); btns.add(del);
        btns.add(sort); btns.add(find); btns.add(ref); btns.add(pay);
        add(btns, BorderLayout.CENTER);

        // ==== Table ====
        String[] cols = {"STT","Mã HĐ","Mã Căn Hộ","Khách Hàng","Thu Ngân","Ngày TT","Tổng Tiền","Trạng Thái"};
        model = new DefaultTableModel(cols,0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.SOUTH);

        loadTable(mgr.getAll());

        // Event CRUD
        add.addActionListener(e -> { create(); loadTable(mgr.getAll()); clearForm(); });
        upd.addActionListener(e -> { update(); loadTable(mgr.getAll()); clearForm(); });
        del.addActionListener(e -> { delete(); loadTable(mgr.getAll()); clearForm(); });
        sort.addActionListener(e -> { mgr.sortByDate(); loadTable(mgr.getAll()); });
        find.addActionListener(e -> {
            String k = JOptionPane.showInputDialog(this,"Nhập mã hoặc khách hàng:");
            if (k!=null) loadTable(mgr.search(k));
        });
        ref.addActionListener(e -> { loadTable(mgr.getAll()); clearForm(); });
        clear.addActionListener(e -> clearForm());
        pay.addActionListener(e -> {
            int r = table.getSelectedRow();
            if (r<0) { JOptionPane.showMessageDialog(this,"Chọn hóa đơn!"); return; }
            Invoice inv = mgr.getAll().get(r);
            inv.setStatus("Đã thanh toán");
            mgr.update(r, inv);
            loadTable(mgr.getAll());
        });

        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow(); if (r<0) return;
                Invoice inv = mgr.getAll().get(r);
                idField.setText(inv.getId());
                aptBox.setSelectedItem(inv.getApartmentId());
                customerField.setText(inv.getCustomer());
                cashierField.setText(inv.getCashier());
                dateField.setText(inv.getDate());
                totalField.setText(String.valueOf((int)inv.getTotal()));
                paidBox.setSelected(inv.getStatus().equals("Đã thanh toán"));
                unpaidBox.setSelected(inv.getStatus().equals("Chưa thanh toán"));
            }
        });
    }

    private void loadTable(List<Invoice> L){
        model.setRowCount(0);
        for(int i=0;i<L.size();i++){
            Invoice inv=L.get(i);
            model.addRow(new Object[]{
                i+1, inv.getId(), inv.getApartmentId(),
                inv.getCustomer(), inv.getCashier(),
                inv.getDate(), String.format("%,.0f",inv.getTotal()), inv.getStatus()
            });
        }
    }
    private Invoice readForm(){
        String status = paidBox.isSelected() ? "Đã thanh toán" : "Chưa thanh toán";
        return new Invoice(
            idField.getText().trim(),
            aptBox.getSelectedItem().toString(),
            customerField.getText().trim(),
            cashierField.getText().trim(),
            dateField.getText().trim(),
            Double.parseDouble(totalField.getText().trim()),
            status
        );
    }
    private void clearForm(){
        idField.setText(""); customerField.setText(""); cashierField.setText("");
        dateField.setText(""); totalField.setText("");
        paidBox.setSelected(false); unpaidBox.setSelected(false);
        aptBox.setSelectedIndex(0); table.clearSelection();
    }
    private void create(){ mgr.add(readForm()); }
    private void update(){
        int r=table.getSelectedRow(); if(r<0){ JOptionPane.showMessageDialog(this,"Chọn dòng!"); return; }
        mgr.update(r, readForm());
    }
    private void delete(){
        int r=table.getSelectedRow(); if(r<0){ JOptionPane.showMessageDialog(this,"Chọn dòng!"); return; }
        mgr.delete(r);
    }
}
