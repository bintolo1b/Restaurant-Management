package VIEW;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import BLL.DanhMucBLL;
import DTO.DanhMuc;



public class CategoryUpdate_InsertView extends JDialog{
	private static final long serialVersionUID = 1L;
	public CategoryUpdate_InsertView(JFrame owner, ManagerView managerview,  int categoryID){
		super(owner, "Danh mục", true);
		JLabel categoryID_lbl  = new JLabel("Mã danh mục");
		JLabel categoryName_lbl = new JLabel("Tên danh mục");
		JLabel categoryStatus_lbl = new JLabel("Trạng thái");
		categoryID_lbl.setBounds(30,40,100,30);
		categoryName_lbl.setBounds(30, 100, 100, 30);
		categoryStatus_lbl.setBounds(30,160,100,30);
		
		JTextField categoryID_txt = new JTextField(categoryID+"");
		JTextField categoryName_txt =new JTextField();
		categoryID_txt.setBounds(150,40,220,30);
		categoryName_txt.setBounds(150,100,220,30);
		
		JRadioButton visibleRad_btn = new JRadioButton("Đang hiển thị");
		JRadioButton hidenRad_btn = new JRadioButton("Đã ẩn");
		visibleRad_btn.setBounds(150,160,120,30);
		visibleRad_btn.setSelected(true);
		hidenRad_btn.setBounds(290,160,80,30);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(visibleRad_btn);
		buttonGroup.add(hidenRad_btn);
		
		JButton ok_btn = new JButton("OK");
		ok_btn.setBounds(80,220,80,30);
		ok_btn.setBackground(new Color(235,182,120));
		
		JButton cancel_btn = new JButton("Cancel");
		cancel_btn.setBounds(230,220,80,30);
		cancel_btn.setBackground(new Color(235,182,120));
		
		if(categoryID != 0) {
			categoryID_txt.setEditable(false);
			DanhMuc danhMuc = DanhMucBLL.getInstance().getDanhMucByID(categoryID);
			categoryName_txt.setText(danhMuc.getTenDanhMuc());
			if(danhMuc.getTrangThai().equals("Đang hiển thị")) {
				visibleRad_btn.setSelected(true);
			} 
			if(danhMuc.getTrangThai().equals("Đã ẩn")) {
				hidenRad_btn.setSelected(true);
			}
		}
		else {
			categoryID_txt.setText(DanhMucBLL.getInstance().generateNewID()+"");
			categoryID_txt.setEditable(false);
		}
		
		ok_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int categoryID_temp = Integer.parseInt(categoryID_txt.getText());
				String categoryName_temp = categoryName_txt.getText();
				String categoryStatus_temp;
				if(visibleRad_btn.isSelected()) categoryStatus_temp = "Đang hiển thị";
				else categoryStatus_temp = "Đã ẩn";
				if(categoryID == 0) {
					if(categoryName_temp.equals("")) {
						JOptionPane.showMessageDialog(null, "Tên danh mục không được để trống");
					}
					else {
						DanhMucBLL.getInstance().insertDanhMuc(new DanhMuc(categoryID_temp,categoryName_temp,categoryStatus_temp));
						managerview.InitCategoryPanel();
						dispose();
					}
				}
				else {
					if(categoryName_temp.equals("")) {
						JOptionPane.showMessageDialog(null, "Tên danh mục không được để trống");
					}
					else {
						DanhMucBLL.getInstance().updateDanhMuc(new DanhMuc(categoryID_temp,categoryName_temp,categoryStatus_temp));
						managerview.InitCategoryPanel();
						dispose();
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
		
		this.add(categoryID_lbl);
		this.add(categoryID_txt);
		this.add(categoryName_lbl);
		this.add(categoryName_txt);
		this.add(categoryStatus_lbl);
		this.add(visibleRad_btn);
		this.add(hidenRad_btn);
		this.add(ok_btn);
		this.add(cancel_btn);
		this.setSize(400,350);
		this.getContentPane().setBackground(new Color(238, 238, 228));
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
	}
}

