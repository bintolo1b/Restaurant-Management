package VIEW;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.NhanVienBLL;
import BLL.TaiKhoanBLL;
import DTO.NhanVien;
import DTO.TaiKhoan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame implements FocusListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JPasswordField passwordField;
	private JButton loginButton;
	private JButton exitButton;
	public JTextField userinputJTextField;

	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel loginLabel = new JLabel("Đăng nhập");
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		loginLabel.setBounds(163, 11, 109, 57);
		contentPane.add(loginLabel);
		
		userinputJTextField = new JTextField();
		userinputJTextField.setForeground(Color.GRAY);
		userinputJTextField.setText("Tài khoản");
		userinputJTextField.addFocusListener(this);
		userinputJTextField.setBounds(157, 80, 109, 20);
		userinputJTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) 
					loginButton.doClick();
			}
		});
		contentPane.add(userinputJTextField);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.GRAY);
        passwordField.setEchoChar((char) 0); // Hiển thị văn bản thường thay vì ký tự *
        passwordField.setText("Mật khẩu");
        passwordField.addFocusListener(this);
		passwordField.setBounds(157, 115, 109, 20);
		passwordField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) 
					loginButton.doClick();
			}
		});
		contentPane.add(passwordField);
		
		loginButton = new JButton("Đăng Nhập");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userinputJTextField.getText();
				String password = String.valueOf(passwordField.getPassword());	
			    TaiKhoan currentTaiKhoan = TaiKhoanBLL.getInstance().getTaiKhoan(username, password);
			    if (currentTaiKhoan!=null) {
			    	if (currentTaiKhoan.getLoaiNguoiDung().equals("Nhân viên")) {
			    		NhanVien currentNhanVien = NhanVienBLL.getInstance().getNhanVienByTaiKhoan(username);
			    		if (currentNhanVien.getTrangThai().equals("Thôi việc"))
			    			JOptionPane.showMessageDialog(null, "Tài khoản đã bị khóa vì đã thôi việc","Thông báo",JOptionPane.WARNING_MESSAGE);
			    		else {
			    			new HomeView(currentNhanVien);
			    			dispose();
			    		}	
			    	}
			    	else if(currentTaiKhoan.getLoaiNguoiDung().equals("Quản lí")) {
			    		new ManagerView();
			    		dispose();
			    	}
			    }
			    else {
			    	JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không chính xác!","Thông báo",JOptionPane.WARNING_MESSAGE);
			    }
			}	
		});
		loginButton.setBounds(157, 180, 109, 23);
		loginButton.setBackground(new Color(235,182,120));
		contentPane.add(loginButton);
		
		exitButton = new JButton("Thoát");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exitButton.setBounds(157, 214, 109, 23);
		exitButton.setBackground(new Color(235,182,120));
		contentPane.add(exitButton);
		
		this.getContentPane().setBackground(new Color(228,236,228));
		this.setResizable(false);
		this.setVisible(true);
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == userinputJTextField) {
			if (userinputJTextField.getText().equals("Tài khoản")) {
				userinputJTextField.setText("");
				userinputJTextField.setForeground(Color.BLACK); // Đổi màu chữ thành màu đen khi bắt đầu nhập
	        } 
		}		
		if (e.getSource() == passwordField) {
			if (String.valueOf(passwordField.getPassword()).equals("Mật khẩu")) {
				passwordField.setText("");
				passwordField.setForeground(Color.BLACK);
				passwordField.setEchoChar('•'); // Đặt ký tự echo là • để ẩn văn bản khi nhập
            }
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource()==userinputJTextField) {
			if (userinputJTextField.getText().isEmpty()) {
				userinputJTextField.setText("Tài khoản");
				userinputJTextField.setForeground(Color.GRAY); // Đổi màu chữ thành màu xám khi không có nhập liệu
            }
		}
		if (e.getSource() == passwordField) {
			if (String.valueOf(passwordField.getPassword()).isEmpty()) {
				passwordField.setText("Mật khẩu");
				passwordField.setForeground(Color.GRAY);
				passwordField.setEchoChar((char) 0); // Hiển thị văn bản mờ thay vì ký tự *
            }
		}
		
	}
	
	public static void main(String[] args) {
		new LoginView();
	}

}
