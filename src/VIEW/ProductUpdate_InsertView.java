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

import BLL.SanPhamBLL;
import DTO.SanPham;


public class ProductUpdate_InsertView extends JDialog{
	private static final long serialVersionUID = 1L;

	public ProductUpdate_InsertView(JFrame owner, int productID, int categoryID) {
		super(owner, "Sản phẩm", true);
		JLabel productID_lbl = new JLabel("Mã sản phẩm");
		JLabel productName_lbl = new JLabel("Tên");
		JLabel price_lbl = new JLabel("Giá");
		JLabel productStatus_lbl = new JLabel("Trạng thái");
		JLabel categoryID_lbl = new JLabel("Mã danh mục");
		
		productID_lbl.setBounds(30,30, 100, 30);
		productName_lbl.setBounds(30,90,100,30);
		price_lbl.setBounds(30,150,100,30);
		productStatus_lbl.setBounds(30,210,100,30);
		categoryID_lbl.setBounds(30,270,100,30);
		
		JTextField productID_txt = new JTextField();
		JTextField productName_txt = new JTextField();
		JTextField price_txt = new JTextField();
		JTextField categoryID_txt = new JTextField(categoryID+"");
		
		productID_txt.setBounds(150,30, 230, 30);
		productName_txt.setBounds(150,90,230,30);
		price_txt.setBounds(150,150,230,30);
		categoryID_txt.setBounds(150,270,230,30);
		
		JRadioButton visibleRad_btn  = new JRadioButton("Đang hiển thị");
		JRadioButton hidenRad_btn = new JRadioButton("Đã ẩn");
		visibleRad_btn.setSelected(true);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(visibleRad_btn);
		buttonGroup.add(hidenRad_btn);
		visibleRad_btn.setBounds(150,210,120,30);
		hidenRad_btn.setBounds(290,210,120,30);
		
		SanPham sanPham = SanPhamBLL.getInstance().getSanPhamByID(productID);
		if(productID!=0) {
			productID_txt.setText(sanPham.getMaSanPham()+"");
			productID_txt.setEditable(false);
			productName_txt.setText(sanPham.getTen());
			price_txt.setText(sanPham.getGia()+"");
			if(sanPham.getTrangThai().equals("Đang hiển thị")) {
			visibleRad_btn.setSelected(true);
			} 
			if(sanPham.getTrangThai().equals("Đã ẩn")) {
			hidenRad_btn.setSelected(true);
			}
			categoryID_txt.setText(sanPham.getMaDanhMuc()+"");
			categoryID_txt.setEditable(false);
		}
		else {
			productID_txt.setText(SanPhamBLL.getInstance().generateNewID()+"");
			productID_txt.setEditable(false);
			categoryID_txt.setText(categoryID+"");
			categoryID_txt.setEditable(false);
			
		}
		
		JButton ok_btn = new JButton("OK");
		JButton cancel_btn = new JButton("Cancel");
		
		ok_btn.setBounds(100,330,80,30);
		ok_btn.setBackground(new Color(235,182,120));
		
		cancel_btn.setBounds(250,330,80,30);
		cancel_btn.setBackground(new Color(235,182,120));

		ok_btn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int productID_temp = Integer.parseInt(productID_txt.getText());
		            String productName_temp = productName_txt.getText();
		            String priceInput = price_txt.getText();
		            
		            if (priceInput.isEmpty()) {
		                throw new IllegalArgumentException("Giá sản phẩm không được để trống");
		            }
		            
		            float price_temp = Float.parseFloat(priceInput);
		            String productStatus_temp = visibleRad_btn.isSelected() ? "Đang hiển thị" : "Đã ẩn";
		            int categoryID_temp = Integer.parseInt(categoryID_txt.getText());

		            if (productName_temp.isEmpty() || price_temp < 0) {
		                throw new IllegalArgumentException("Tên sản phẩm không được để trống và giá sản phẩm phải lớn hơn hoặc bằng 0");
		            }

		            if (productID != 0) {
		                SanPhamBLL.getInstance().updateSanPham(new SanPham(productID_temp, productName_temp, price_temp, productStatus_temp, categoryID_temp));
		            } else {
		                SanPhamBLL.getInstance().insertSanPham(new SanPham(productID_temp, productName_temp, price_temp, productStatus_temp, categoryID_temp));
		            }
		            dispose();
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị số cho giá sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        } catch (IllegalArgumentException ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		cancel_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.add(productID_lbl);
		this.add(productID_txt);
		this.add(productName_lbl);
		this.add(productName_txt);
		this.add(price_lbl);
		this.add(price_txt);
		this.add(productStatus_lbl);
		this.add(visibleRad_btn);
		this.add(hidenRad_btn);
		this.add(categoryID_lbl);
		this.add(categoryID_txt);
		this.add(ok_btn);
		this.add(cancel_btn);
		this.setSize(450,450);
		this.getContentPane().setBackground(new Color(238, 238, 228));
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
