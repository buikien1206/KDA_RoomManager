package com.kdatower.view;

import com.kdatower.manager.AccountManager;

import javax.swing.*;
import java.awt.*;

public class RegisterDialog extends JDialog {
    public RegisterDialog(JFrame parent, AccountManager manager) {
        super(parent, "Đăng ký tài khoản mới", true);
        setSize(400, 330);
        setLocationRelativeTo(parent);
        setLayout(null);

        // Tiêu đề
        JLabel lblTitle = new JLabel("Đăng ký tài khoản mới");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBounds(70, 20, 300, 30);
        add(lblTitle);

        // Tên đăng nhập
        JLabel lblUser = new JLabel("Tên đăng nhập:");
        lblUser.setBounds(40, 70, 120, 28);
        add(lblUser);
        JTextField userField = new JTextField();
        userField.setBounds(170, 70, 180, 28);
        add(userField);

        // Mật khẩu
        JLabel lblPass = new JLabel("Mật khẩu:");
        lblPass.setBounds(40, 115, 120, 28);
        add(lblPass);
        JPasswordField passField = new JPasswordField();
        passField.setBounds(170, 115, 180, 28);
        add(passField);

        // Nhập lại mật khẩu
        JLabel lblRePass = new JLabel("Nhập lại MK:");
        lblRePass.setBounds(40, 160, 120, 28);
        add(lblRePass);
        JPasswordField rePassField = new JPasswordField();
        rePassField.setBounds(170, 160, 180, 28);
        add(rePassField);

        // Nút Đăng ký
        JButton btnRegister = new JButton("Đăng ký");
        btnRegister.setBounds(70, 220, 120, 38);
        btnRegister.setBackground(new Color(2, 74, 173));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnRegister.setFocusPainted(false);
        btnRegister.setBorder(BorderFactory.createLineBorder(new Color(2,74,173), 2, true));
        add(btnRegister);

        // Nút Hủy
        JButton btnCancel = new JButton("Thoát");
        btnCancel.setBounds(210, 220, 120, 38);
        btnCancel.setBackground(new Color(190, 190, 190));
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnCancel.setFocusPainted(false);
        btnCancel.setBorder(BorderFactory.createLineBorder(new Color(190,190,190), 2, true));
        add(btnCancel);

        // Xử lý sự kiện Đăng ký
        btnRegister.addActionListener(e -> {
    String user   = userField.getText().trim();
    String pass   = new String(passField.getPassword());
    String repass = new String(rePassField.getPassword());
    // ... (các kiểm tra rỗng, khớp mật khẩu)

    boolean ok = manager.register(user, pass);
    if (ok) {
        JOptionPane.showMessageDialog(this, "Đăng ký thành công! Bạn có thể đăng nhập.");
        dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại!");
    }
});


        // Xử lý sự kiện Thoát
        btnCancel.addActionListener(e -> dispose());
    }
}
