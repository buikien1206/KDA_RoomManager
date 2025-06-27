package com.kdatower.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class MainFrame extends JFrame {
    private CardLayout cards;
    private JPanel cardPanel;

    public MainFrame() {
        setTitle("Hệ thống quản lý căn hộ - KDA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 800);
        setLocationRelativeTo(null);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(21, 67, 96));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(240, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Logo hoặc tiêu đề
        URL logoUrl = getClass().getClassLoader().getResource("resources/logo.png");
        JLabel logo = new JLabel();
        if (logoUrl != null) {
            logo.setIcon(new ImageIcon(logoUrl));
        } else {
            logo.setText("KDA");
            logo.setFont(new Font("Segoe UI", Font.BOLD, 24));
            logo.setForeground(Color.WHITE);
        }
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(logo);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel hello = new JLabel("Hello, Admin!");
        hello.setForeground(new Color(255, 153, 51));
        hello.setFont(new Font("Segoe UI", Font.BOLD, 18));
        hello.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(hello);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        // Card layout panels
        cards    = new CardLayout();
        cardPanel = new JPanel(cards);

        // Menu buttons
        sidebar.add(createMenuButton("Quản Lý Căn Hộ",   "apt"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 8)));
        sidebar.add(createMenuButton("Danh Sách Cư Dân", "res"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 8)));
        sidebar.add(createMenuButton("Quản Lý Hóa Đơn",  "inv"));
        // … thêm nút khác nếu cần …
        sidebar.add(Box.createVerticalGlue());

        // Đăng xuất
        JButton logoutBtn = new JButton("Đăng xuất");
        logoutBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE,  forty8()));
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        logoutBtn.setBackground(new Color(192, 57, 43));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorder(BorderFactory.createLineBorder(new Color(231, 76, 60), 1, true));
        logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.addActionListener(e -> {
            // Đóng MainFrame, mở lại LoginFrame
            dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        });
        sidebar.add(logoutBtn);

        // Các panel chính
        cardPanel.add(new ApartmentPanel(), "apt");
        cardPanel.add(new ResidentPanel(),  "res");
        cardPanel.add(new InvoicePanel(),   "inv");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(cardPanel, BorderLayout.CENTER);

        cards.show(cardPanel, "apt");
    }

    private JButton createMenuButton(String text, String cardKey) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(new Color(31, 97, 141));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243), 1, true));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.addActionListener(e -> cards.show(cardPanel, cardKey));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // helper để tránh magic number
    private int forty8() { return 48; }
}
