package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoan;


public class ChangePasswordView extends JFrame{
	private static final long serialVersionUID = 1L;

	public ChangePasswordView(String user) {
		JLabel title = new JLabel("Đổi mật khẩu");
		
		JLabel user_lbl = new JLabel("Tên người dùng");
		JLabel oldPass_lbl = new JLabel("Mật khẩu cũ");
		JLabel newPass_lbl = new JLabel("Mật khẩu mới");
		JLabel verNewPass_lbl = new JLabel("Xác minh mật khẩu mới");
		
		title.setBounds(130, 30, 200, 50);
		title.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 30));
		
		user_lbl.setBounds(30, 100, 220, 30);
		oldPass_lbl.setBounds(30, 150, 220, 30);
		newPass_lbl.setBounds(30, 200, 220, 30);
		verNewPass_lbl.setBounds(30, 250, 220, 30);
		
		JTextField user_txt = new JTextField(user);
		user_txt.setEditable(false);
		JPasswordField oldPass_txt = new JPasswordField();
		JPasswordField newPass_txt = new JPasswordField();
		JPasswordField verNewPass_txt = new JPasswordField();
		
		user_txt.setBounds(250, 100, 150, 30);
		oldPass_txt.setBounds(250, 150, 150, 30);
		newPass_txt.setBounds(250, 200, 150, 30);
		verNewPass_txt.setBounds(250, 250, 150, 30);
		
		JButton ok_btn = new JButton("OK");
		JButton cancel_btn = new JButton("Cancel");
		
		ok_btn.setBounds(80, 320, 100, 30);
		ok_btn.setBackground(new Color(235,182,120));
		
		cancel_btn.setBounds(260,320,100,30);
		cancel_btn.setBackground(new Color(235,182,120));
		
		ok_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String oldPass_temp = String.valueOf(oldPass_txt.getPassword());
				String newPass_temp = String.valueOf(newPass_txt.getPassword());
				String verNewPass_temp = String.valueOf(verNewPass_txt.getPassword());
				TaiKhoan taiKhoan = TaiKhoanBLL.getInstance().getTaiKhoan(user, oldPass_temp);
				if(taiKhoan == null) {
					JOptionPane.showMessageDialog(null, "Mật khẩu cũ không chính xác!","Thông báo!",JOptionPane.WARNING_MESSAGE);
					oldPass_txt.setText("");
					newPass_txt.setText("");
					verNewPass_txt.setText("");
				}
				else {
					if(newPass_temp.equals("")) {
						JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống!","Thông báo",JOptionPane.WARNING_MESSAGE);
					}
					else {
						if(newPass_temp.equals(verNewPass_temp)) {
						TaiKhoanBLL.getInstance().updateTaiKhoan(new TaiKhoan(user,newPass_temp,null));
						JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
						}
						else {
						JOptionPane.showMessageDialog(null, "Mật khẩu xác minh không chính xác!","Thông báo!", JOptionPane.WARNING_MESSAGE);
						newPass_txt.setText("");
						verNewPass_txt.setText("");
						}
					}
				}
			}
		});
		
		cancel_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.add(title);
		this.add(user_lbl);
		this.add(user_txt);
		this.add(oldPass_lbl);
		this.add(oldPass_txt);
		this.add(newPass_lbl);
		this.add(newPass_txt);
		this.add(verNewPass_lbl);
		this.add(verNewPass_txt);
		this.add(ok_btn);
		this.add(cancel_btn);
		this.setSize(470,450);
		this.getContentPane().setBackground(new Color(238, 238, 228));
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
}
