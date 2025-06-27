package com.kdatower.view;

import com.kdatower.manager.AccountManager;
import com.kdatower.model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterDialog extends JDialog {
    public RegisterDialog(JFrame parent, AccountManager manager) {
        super(parent, "Đăng ký tài khoản mới", true);
        setSize(400, 330);
        setLocationRelativeTo(parent);
        setLayout(null);

        JLabel lblTitle = new JLabel("Đăng ký tài khoản mới");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBounds(70, 20, 300, 30);
        add(lblTitle);

        JLabel lblUser = new JLabel("Tên đăng nhập:");
        lblUser.setBounds(40, 70, 120, 28);
        add(lblUser);
        JTextField userField = new JTextField();
        userField.setBounds(170, 70, 180, 28);
        add(userField);

        JLabel lblPass = new JLabel("Mật khẩu:");
        lblPass.setBounds(40, 115, 120, 28);
        add(lblPass);
        JPasswordField passField = new JPasswordField();
        passField.setBounds(170, 115, 180, 28);
        add(passField);

        JLabel lblRePass = new JLabel("Nhập lại MK:");
        lblRePass.setBounds(40, 160, 120, 28);
        add(lblRePass);
        JPasswordField rePassField = new JPasswordField();
        rePassField.setBounds(170, 160, 180, 28);
        add(rePassField);

        JButton btnRegister = new JButton("Đăng ký");
        btnRegister.setBounds(70, 220, 120, 38);
        btnRegister.setBackground(new Color(2,74,173));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnRegister.setFocusPainted(false);
        btnRegister.setBorder(BorderFactory.createLineBorder(new Color(2,74,173), 20, true));
        add(btnRegister);

        JButton btnCancel = new JButton("Thoát");
        btnCancel.setBounds(210, 220, 120, 38);
        btnCancel.setBackground(new Color(190, 190, 190));
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setFocusPainted(false);
        btnCancel.setBorder(BorderFactory.createLineBorder(new Color(190,190,190), 20, true));
        add(btnCancel);

        // Xử lý Đăng ký
        btnRegister.addActionListener(e -> {
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword());
            String repass = new String(rePassField.getPassword());
            if (user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            if (!pass.equals(repass)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không khớp!");
                return;
            }
            if (manager.register(new Account(user, pass))) {
                JOptionPane.showMessageDialog(this, "Đăng ký thành công! Bạn có thể đăng nhập.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại!");
            }
        });

        btnCancel.addActionListener(e -> dispose());
    }
}
