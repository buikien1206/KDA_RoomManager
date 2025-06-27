package com.kdatower.view;

import com.kdatower.manager.AccountManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private AccountManager accountManager;

    public LoginFrame() {
        setTitle("Hệ thống quản lý dịch vụ căn hộ");
        setSize(820, 510);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        accountManager = new AccountManager();

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 0, 0));

        // ===== BÊN TRÁI: ĐĂNG NHẬP =====
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(64, 52, 64, 52));

        JLabel title = new JLabel("Đăng Nhập");
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setForeground(new Color(16, 49, 91));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username field bo tròn
        RoundedPanel userWrap = new RoundedPanel(new Color(226, 241, 252));
        userWrap.setLayout(new BoxLayout(userWrap, BoxLayout.X_AXIS));
        userWrap.setMaximumSize(new Dimension(370, 46));
        userWrap.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JLabel userIcon = new JLabel();
        try {
            userIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/user_gray.png")));
        } catch (Exception ex) {
            userIcon.setText("\uD83D\uDC64");
        }
        userIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));

        usernameField = new JTextField("Tên đăng nhập");
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        usernameField.setForeground(new Color(150, 165, 180));
        usernameField.setOpaque(false);
        usernameField.setBorder(null);
        usernameField.setCaretColor(new Color(50, 65, 90));
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Tên đăng nhập")) {
                    usernameField.setText("");
                    usernameField.setForeground(new Color(50, 65, 90));
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Tên đăng nhập");
                    usernameField.setForeground(new Color(150, 165, 180));
                }
            }
        });

        userWrap.add(userIcon);
        userWrap.add(usernameField);

        // Password field bo tròn
        RoundedPanel passWrap = new RoundedPanel(new Color(226, 241, 252));
        passWrap.setLayout(new BoxLayout(passWrap, BoxLayout.X_AXIS));
        passWrap.setMaximumSize(new Dimension(370, 46));
        passWrap.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JLabel lockIcon = new JLabel();
        try {
            lockIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/lock_gray.png")));
        } catch (Exception ex) {
            lockIcon.setText("\uD83D\uDD12");
        }
        lockIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));

        passwordField = new JPasswordField("Mật khẩu");
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passwordField.setForeground(new Color(150, 165, 180));
        passwordField.setOpaque(false);
        passwordField.setBorder(null);
        passwordField.setEchoChar((char)0);
        passwordField.setCaretColor(new Color(50, 65, 90));
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Mật khẩu")) {
                    passwordField.setText("");
                    passwordField.setForeground(new Color(50, 65, 90));
                    passwordField.setEchoChar('\u25CF');
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("Mật khẩu");
                    passwordField.setForeground(new Color(150, 165, 180));
                    passwordField.setEchoChar((char)0);
                }
            }
        });

        passWrap.add(lockIcon);
        passWrap.add(passwordField);

        // Nút ĐĂNG NHẬP bo tròn
        RoundedButton loginBtn = new RoundedButton(
            "ĐĂNG NHẬP",
            new Color(13, 60, 120),
            Color.WHITE,
            32
        );
        loginBtn.setPreferredSize(new Dimension(220, 48));
        loginBtn.setMaximumSize(new Dimension(220, 48));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nhấn Enter để login
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleLogin();
                }
            }
        });

        // Sắp xếp các thành phần lên panel
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(title);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 32)));
        leftPanel.add(userWrap);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 16)));
        leftPanel.add(passWrap);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 26)));
        leftPanel.add(loginBtn);
        leftPanel.add(Box.createVerticalGlue());

        mainPanel.add(leftPanel);

        // ===== BÊN PHẢI: CHÀO MỪNG + ĐĂNG KÝ =====
        JPanel rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(2, 74, 173),
                    0, getHeight(), new Color(9, 159, 255)
                );
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                g2.dispose();
            }
        };
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 34, 50, 34));

        JLabel chao = new JLabel("XIN CHÀO!");
        chao.setFont(new Font("Segoe UI", Font.BOLD, 34));
        chao.setForeground(Color.WHITE);
        chao.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel moTa = new JLabel(
            "<html><center>Để tiếp tục kết nối với chúng tôi<br>" +
            "vui lòng đăng nhập bằng thông tin cá nhân của bạn</center></html>"
        );
        moTa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        moTa.setForeground(Color.WHITE);
        moTa.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nút ĐĂNG KÝ bo tròn, viền trắng
        RoundedButton regBtn = new RoundedButton(
            "ĐĂNG KÝ",
            new Color(0, 0, 0, 0),
            Color.WHITE,
            32
        );
        regBtn.setBorderColor(Color.WHITE);
        regBtn.setPreferredSize(new Dimension(190, 46));
        regBtn.setMaximumSize(new Dimension(190, 46));
        regBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(chao);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        rightPanel.add(moTa);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 52)));
        rightPanel.add(regBtn);
        rightPanel.add(Box.createVerticalGlue());

        mainPanel.add(rightPanel);

        setContentPane(mainPanel);

        // Sự kiện
        loginBtn.addActionListener(e -> handleLogin());
        regBtn.addActionListener(e -> {
            RegisterDialog dialog = new RegisterDialog(this, accountManager);
            dialog.setVisible(true);
        });
    }

    private void handleLogin() {
    String username = usernameField.getText().trim();
    String password = new String(passwordField.getPassword());

    // 1) Kiểm tra placeholder
    if (username.equals("Tên đăng nhập") || password.equals("Mật khẩu")) {
        JOptionPane.showMessageDialog(this,
            "Vui lòng nhập đầy đủ tài khoản và mật khẩu!");
        return;
    }

    // 2) Kiểm tra với AccountManager
    if (accountManager.login(username, password)) {
        // thông báo thành công
        JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");

        // 3) Đóng cửa sổ login
        this.dispose();  
        // hoặc this.setVisible(false);

        // 4) Mở MainFrame
        SwingUtilities.invokeLater(() -> {
            MainFrame mf = new MainFrame();
            mf.setVisible(true);
        });
    } else {
        JOptionPane.showMessageDialog(this,
            "Sai tài khoản hoặc mật khẩu!");
    }
}


    // ===== Panel bo tròn cho text fields =====
    static class RoundedPanel extends JPanel {
        private final Color bg;
        public RoundedPanel(Color bg) {
            this.bg = bg;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );
            g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ===== JButton bo tròn custom =====
    static class RoundedButton extends JButton {
        private final int radius;
        private final Color bgColor;
        private final Color fgColor;
        private Color borderColor;

        public RoundedButton(String text, Color bgColor, Color fgColor, int radius) {
            super(text);
            this.bgColor = bgColor;
            this.fgColor = fgColor;
            this.radius  = radius;
            this.borderColor = null;

            // Tắt hoàn toàn border/content area mặc định
            setBorder(null);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setFocusPainted(false);

            setForeground(fgColor);
            setFont(new Font("Segoe UI", Font.BOLD, 18));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        /** Gọi nếu muốn vẽ border viền ngoài */
        public void setBorderColor(Color c) {
            this.borderColor = c;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );
            // Background
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            // Border nếu có
            if (borderColor != null) {
                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(2f));
                g2.drawRoundRect(
                    1, 1,
                    getWidth() - 3,
                    getHeight() - 3,
                    radius, radius
                );
            }

            // Text
            super.paintComponent(g2);
            g2.dispose();
        }
    }
}
