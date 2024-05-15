package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.toedter.calendar.JDateChooser;

import BLL.QuanLyBLL;
import DAL.QuanLyDAL;
import DTO.NhanVien;
import DTO.QuanLy;


public class ProfileView extends JFrame{
	private static final long serialVersionUID = 1L;
	private NhanVien currentNhanVien;
	public ProfileView(NhanVien currentNhanVien) {
		if (currentNhanVien!=null) {
			this.currentNhanVien = currentNhanVien;
			InitStaffProfileView();
		}
		else {
			InitManagerProfileView();
		}
	}
	
	public void InitStaffProfileView() {
		JLabel title = new JLabel("Hồ sơ cá nhân");
		
		JLabel staffID_lbl = new JLabel("Mã nhân viên");
		JLabel name_lbl = new JLabel("Họ tên");
		JLabel dateOfBirth_lbl = new JLabel("Ngày sinh");
		JLabel phoneNumber_lbl = new JLabel("Số điện thoại");
		JLabel address_lbl = new JLabel("Địa chỉ");
		JLabel gender_lbl = new JLabel("Giới tính");
		
		JTextField staffID_txt = new JTextField();
		JTextField name_txt = new JTextField();
		JTextField dateOfBirth_txt = new JTextField();
		JTextField phoneNumber_txt = new JTextField();
		JTextField address_txt = new JTextField();
		
		this.getContentPane().setBackground(new Color(238, 238, 228) );
		
		title.setBounds(110,30,250,40);
		title.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 30));
		
		staffID_lbl.setBounds(40,120,100,30);
		name_lbl.setBounds(40,180,100,30);
		dateOfBirth_lbl.setBounds(40,240,100,30);
		phoneNumber_lbl.setBounds(40,300,100,30);
		address_lbl.setBounds(40,360,100,30);
		gender_lbl.setBounds(40,420,100,30);
		
		staffID_txt.setBounds(150,120,230,30);
		name_txt.setBounds(150,180,230,30);
		dateOfBirth_txt.setBounds(150,240,230,30);
		phoneNumber_txt.setBounds(150,300,230,30);
		address_txt.setBounds(150,360,230,30);
		
		JRadioButton maleRad_btn = new JRadioButton("Nam");
		JRadioButton femaleRad_btn = new JRadioButton("Nữ");
		maleRad_btn.setBounds(170,420,100,30);
		femaleRad_btn.setBounds(300,420,100,30);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(maleRad_btn);
		buttonGroup.add(femaleRad_btn);
		staffID_txt.setText(currentNhanVien.getMaNhanVien()+"");
		name_txt.setText(currentNhanVien.getHo()+" "+currentNhanVien.getTenDem()+" "+currentNhanVien.getTen());
		dateOfBirth_txt.setText(currentNhanVien.getNgaySinh());
		phoneNumber_txt.setText(currentNhanVien.getSoDienThoai());
		address_txt.setText(currentNhanVien.getDiaChi());
		if(currentNhanVien.getGioiTinh() == 0) maleRad_btn.setSelected(true);
		else if(currentNhanVien.getGioiTinh() == 1) femaleRad_btn.setSelected(true);
		
		staffID_txt.setEditable(false);
		name_txt.setEditable(false);
		dateOfBirth_txt.setEditable(false);
		phoneNumber_txt.setEditable(false);
		address_txt.setEditable(false);
		maleRad_btn.setEnabled(false);
		femaleRad_btn.setEnabled(false);
		
		this.add(title);
		this.add(staffID_lbl);
		this.add(staffID_txt);
		this.add(name_lbl);
		this.add(name_txt);
		this.add(dateOfBirth_lbl);
		this.add(dateOfBirth_txt);
		this.add(phoneNumber_lbl);
		this.add(phoneNumber_txt);
		this.add(address_lbl);
		this.add(address_txt);
		this.add(gender_lbl);
		this.add(maleRad_btn);
		this.add(femaleRad_btn);
		
		this.setSize(450,520);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void InitManagerProfileView() {
		QuanLy quanLy = QuanLyDAL.getInstance().getQuanLy();
		
		JLabel position_lbl = new JLabel("Chức vụ");
		JLabel title = new JLabel("Hồ sơ cá nhân");
		JLabel name_lbl = new JLabel("Họ tên");
		JLabel dateOfBirth_lbl = new JLabel("Ngày sinh");
		JLabel phoneNumber_lbl = new JLabel("Số điện thoại");
		JLabel address_lbl = new JLabel("Địa chỉ");
		JLabel gender_lbl = new JLabel("Giới tính");
		
		JTextField position_txt = new JTextField();
		JTextField surName_txt = new JTextField();
		JTextField middleName_txt = new JTextField();
		JTextField firstName_txt = new JTextField();
		JTextField phoneNumber_txt = new JTextField();
		
		JTextField address_txt = new JTextField();
		
		JRadioButton maleRad_btn = new JRadioButton("Nam");
		JRadioButton femaleRad_btn = new JRadioButton("Nữ");
		
		this.getContentPane().setBackground(new Color(238, 238, 228) );
		
		maleRad_btn.setBounds(170,420,100,30);
		femaleRad_btn.setBounds(300,420,100,30);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(maleRad_btn);
		buttonGroup.add(femaleRad_btn);
		
		title.setBounds(110,30,250,40);
		title.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 30));
		
		position_lbl.setBounds(40,120,100,30);
		position_txt.setText("Quản lý");
		name_lbl.setBounds(40,180,100,30);
		dateOfBirth_lbl.setBounds(40,240,100,30);
		phoneNumber_lbl.setBounds(40,300,100,30);
		address_lbl.setBounds(40,360,100,30);
		gender_lbl.setBounds(40,420,100,30);
		
		position_txt.setBounds(150,120,230,30);
		surName_txt.setBounds(150,180,50,30);
		middleName_txt.setBounds(200,180,90,30);
		firstName_txt.setBounds(290,180,90,30);
		phoneNumber_txt.setBounds(150,300,230,30);
		address_txt.setBounds(150,360,230,30);
		
		 AbstractDocument phoneNumberDoc = (AbstractDocument) phoneNumber_txt.getDocument();
	        phoneNumberDoc.setDocumentFilter(new DocumentFilter() {
	            final int maxChars = 10;

	            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
	                // Kiểm tra xem việc thay thế có vượt quá giới hạn kí tự hay không
	                if ((fb.getDocument().getLength() + text.length() - length) <= maxChars) {
	                    super.replace(fb, offset, length, text, attrs);
	                }
	            }
	        });
		
		JDateChooser dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(0, 0, 0));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(150,240,130,30);
        
		surName_txt.setText(quanLy.getHo());
		middleName_txt.setText(quanLy.getTenDem());
		firstName_txt.setText(quanLy.getTen());
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date date;
	        try {
	            date = dateFormat.parse(quanLy.getNgaySinh());
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(date);
	            dateChooser.setDate(calendar.getTime());
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

		phoneNumber_txt.setText(quanLy.getSoDienThoai());
		address_txt.setText(quanLy.getDiaChi());
		if(quanLy.getGioiTinh() == 0) maleRad_btn.setSelected(true);
		else if(quanLy.getGioiTinh() == 1) femaleRad_btn.setSelected(true);
		
		
		JButton ok_btn = new JButton("OK");
		JButton cancel_btn = new JButton("Cancel");
		
		ok_btn.setBounds(100,480,100,30);
		ok_btn.setBackground(new Color(235,182,120));
		cancel_btn.setBounds(250,480,100,30);
		cancel_btn.setBackground(new Color(235,182,120));
		
		ok_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String surName = String.valueOf(surName_txt.getText());
				String middleName = String.valueOf(middleName_txt.getText());
				String firstName = String.valueOf(firstName_txt.getText());
				Calendar selectedDate = dateChooser.getCalendar();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				int gender;
				if(maleRad_btn.isSelected()) gender = 0;
				else gender = 1;
				String address = String.valueOf(address_txt.getText());
				String phoneNumber = String.valueOf(phoneNumber_txt.getText());
				if(selectedDate == null) {
					JOptionPane.showMessageDialog(null, "vui lòng chọn ngày!","Thông báo", JOptionPane.WARNING_MESSAGE);

				}
				else {
					String dateOfBirth = sdf.format(selectedDate.getTime());
				if(surName.equals("")||firstName.equals("")||address.equals("")||phoneNumber.equals("")) {
					JOptionPane.showMessageDialog(null, "Họ tên, địa chỉ và số điện thoại không được để trống!","Thông báo", JOptionPane.WARNING_MESSAGE);
				}
				else {
					boolean phoneCheck = true;
					for(char x: phoneNumber.toCharArray()) {
						if(Character.isAlphabetic(x)) phoneCheck = false;
					}
					if(phoneCheck == false || phoneNumber.length() < 10) {
						JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!","Thông báo!",JOptionPane.WARNING_MESSAGE);
						phoneNumber_txt.setText("");
					}
					else {
						QuanLyBLL.getInstance().updateQuanLy(
							new QuanLy(surName,middleName,firstName,dateOfBirth,gender,address,phoneNumber,"user0"));
							JOptionPane.showMessageDialog(null, "Cập nhật thành công");
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
		this.add(position_lbl);
		this.add(position_txt);
		this.add(name_lbl);
		this.add(surName_txt);
		this.add(middleName_txt);
		this.add(firstName_txt);
		this.add(dateOfBirth_lbl);
		this.add(dateChooser);
		this.add(phoneNumber_lbl);
		this.add(phoneNumber_txt);
		this.add(address_lbl);
		this.add(address_txt);
		this.add(gender_lbl);
		this.add(maleRad_btn);
		this.add(femaleRad_btn);
		this.add(ok_btn);
		this.add(cancel_btn);
		
		this.setSize(450,580);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
