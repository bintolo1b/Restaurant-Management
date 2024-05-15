package VIEW;

import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import BLL.BanAnBLL;
import BLL.HoaDonBLL;
import BLL.KhachHangBLL;
import DTO.BanAn;
import DTO.HoaDon;
import DTO.KhachHang;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ChooseKindOfCustomerView extends JDialog  {
	private static final long serialVersionUID = 1L;
	private HomeView homeView;
	private BanAn currentBanAn;
	private JPanel currentDisplayPanel;
	private boolean ChoooseSuccess = true;
	
	
	public ChooseKindOfCustomerView(JFrame owner,HomeView hv, BanAn currentBanAn) {
		super(owner, "Khách hàng", true);
		this.homeView = hv;
		this.currentBanAn = currentBanAn;
		this.setBounds(100, 100, 470, 359);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(WindowEvent e) {
				ChoooseSuccess = false;
                dispose();
            }
		});
		currentDisplayPanel = new JPanel();
		getContentPane().add(currentDisplayPanel);
		InitChoosingPanel();
		this.setVisible(true);
	}

	public void InitChoosingPanel() {	
		currentDisplayPanel.removeAll();
		currentDisplayPanel.setLayout(null);
		currentDisplayPanel.setBackground(new Color(171, 219, 227));
		
		JButton visitingGuestButton = new JButton("Khách vãng lai");
		visitingGuestButton.setBackground(new Color(235, 182, 120));
		visitingGuestButton.setBounds(132, 64, 169, 34);
		visitingGuestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				int newHoaDonID = HoaDonBLL.getInstance().generateNewID();	
			    float tongTien = 0;
			    int maNhanVien = getHomeView().getCurrentNhanVien().getMaNhanVien();
			    int maBan = getCurrentBanAn().getMaBan();

			    String tinhTrang="Chưa thanh toán";
			    HoaDon newHD = new HoaDon(newHoaDonID, null, tongTien, maNhanVien, null, maBan, tinhTrang);
			    HoaDonBLL.getInstance().addNewHoaDon(newHD);
			    
			    getCurrentBanAn().setTrangThai("Có người");
			    BanAnBLL.getInstance().updateBan(getCurrentBanAn());
			    getHomeView().Init_table_order_panel();
			    getHomeView().DisplayDetailOfTable(getCurrentBanAn().getMaBan());
				dispose();
			}
		});
		currentDisplayPanel.add(visitingGuestButton);
		
		JButton familiarGuestButton = new JButton("Khách quen");
		familiarGuestButton.setBackground(new Color(235, 182, 120));
		familiarGuestButton.setBounds(132, 145, 169, 34);
		familiarGuestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InitEnterFamiliarGuestPhoneNumberPanel();
			}
		});
		currentDisplayPanel.add(familiarGuestButton);
		
		JButton newGuestButton = new JButton("Khách mới");
		newGuestButton.setBackground(new Color(235, 182, 120));
		newGuestButton.setBounds(132, 226, 169, 34);
		newGuestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InitEnterNewCustomerInfor();
			}
		});
		
//		ChooseKindOfCustomerController controller = new ChooseKindOfCustomerController(this);
//		visitingGuestButton.addActionListener(controller);
//		familiarGuestButton.addActionListener(controller);
//		newGuestButton.addActionListener(controller);
		
		currentDisplayPanel.add(newGuestButton);
		currentDisplayPanel.validate();
		currentDisplayPanel.repaint();
	}
	
	public void InitEnterNewCustomerInfor() {
		currentDisplayPanel.removeAll();
		currentDisplayPanel.setLayout(null);
		currentDisplayPanel.setBackground(new Color(171, 219, 227));
		
		JTextField hoTxt;
		JTextField tenDemTxt;
		JTextField tenTxt;
		JTextField sdtTxt;
		JTextField diachiTxt;
		
		hoTxt = new JTextField("");
		hoTxt.setBounds(42, 51, 88, 22);
		currentDisplayPanel.add(hoTxt);
		hoTxt.setColumns(20);
		
		tenDemTxt = new JTextField("");
		tenDemTxt.setBounds(212, 51, 82, 22);
		currentDisplayPanel.add(tenDemTxt);
		tenDemTxt.setColumns(20);
		
		JLabel hoLabel = new JLabel("Họ");
		hoLabel.setBounds(12, 54, 37, 16);
		currentDisplayPanel.add(hoLabel);
		
		JLabel tenDemLabel = new JLabel("Tên đệm");
		tenDemLabel.setBounds(147, 54, 56, 16);
		currentDisplayPanel.add(tenDemLabel);
		
		JLabel tenLabel = new JLabel("Tên");
		tenLabel.setBounds(324, 54, 56, 16);
		currentDisplayPanel.add(tenLabel);
		
		tenTxt = new JTextField("");
		tenTxt.setBounds(363, 51, 88, 22);
		currentDisplayPanel.add(tenTxt);
		tenTxt.setColumns(20);
		
		JLabel sdtLabel = new JLabel("SĐT");
		sdtLabel.setBounds(12, 106, 56, 16);
		currentDisplayPanel.add(sdtLabel);
		
		sdtTxt = new JTextField("");
		sdtTxt.setBounds(42, 103, 88, 22);
		currentDisplayPanel.add(sdtTxt);
		sdtTxt.setColumns(10);
		
		JLabel diachiLabel = new JLabel("Địa chỉ");
		diachiLabel.setBounds(147, 106, 56, 16);
		currentDisplayPanel.add(diachiLabel);
		
		diachiTxt = new JTextField("");
		diachiTxt.setBounds(212, 103, 239, 22);
		currentDisplayPanel.add(diachiTxt);
		diachiTxt.setColumns(60);
		
		JLabel gioiTinhLabel = new JLabel("Giới Tính");
		gioiTinhLabel.setBounds(12, 161, 56, 16);
		currentDisplayPanel.add(gioiTinhLabel);
		
		JRadioButton namRadioButton = new JRadioButton("Nam");
		namRadioButton.setBackground(new Color(171, 219, 227));
		namRadioButton.setBounds(130, 157, 123, 25);
		namRadioButton.setSelected(true);
		currentDisplayPanel.add(namRadioButton);
		
		JRadioButton nuRadioButton = new JRadioButton("Nữ");
		nuRadioButton.setBackground(new Color(171, 219, 227));
		nuRadioButton.setBounds(257, 157, 123, 25);
		currentDisplayPanel.add(nuRadioButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(namRadioButton);
        group.add(nuRadioButton);
        
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(0, 0, 0));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(130, 221, 123, 22);
        currentDisplayPanel.add(dateChooser);
        
        JLabel ngaySinhLabel = new JLabel("Ngày sinh");
        ngaySinhLabel.setBounds(12, 220, 56, 16);
        currentDisplayPanel.add(ngaySinhLabel);
        
        JButton okButton = new JButton("OK");
        okButton.setBackground(new Color(235, 182, 120));
        okButton.setBounds(92, 283, 97, 25);
        currentDisplayPanel.add(okButton);
        okButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean ok = true;
				if (sdtTxt.getText().length()!=10) {
					JOptionPane.showMessageDialog(null, "Sai định dạng số điện thoại");
					ok = false;
					return;
				}
				if (ok==true) {
					for (int i = 0 ; i < sdtTxt.getText().length(); i++)
						if (!Character.isDigit(sdtTxt.getText().charAt(i))) {
							JOptionPane.showMessageDialog(null, "Sai định dạng số điện thoại");
							ok = false;
							return;
						}
				}
				if (ok==true && tenTxt.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Tên không được trống");
					ok=false;
					return;
				}
//				if (ok==true && dateChooser.getCalendar()==null) {
//					JOptionPane.showMessageDialog(null, "Ngày sinh không được trống");
//					ok=false;
//					return;
//				}
				if (ok==true && KhachHangBLL.getInstance().isSdtExist(sdtTxt.getText())==true) {
					JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại");
					ok=false;
					return;
				}
				if (ok==true) {
					String sdt = sdtTxt.getText();
					String ho = hoTxt.getText();
					String tenDem = tenDemTxt.getText();
					String ten = tenTxt.getText();
					String diaChi = diachiTxt.getText();
					int gioiTinh;
					if (namRadioButton.isSelected())
						gioiTinh=0;
					else gioiTinh=1;
					
					String ngaySinh=null;
					if (dateChooser.getCalendar()!=null) {
						Calendar selectedDate = dateChooser.getCalendar();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						ngaySinh = sdf.format(selectedDate.getTime());;
					}
			
					
					float diemTichLuy = 0;					
					int newKhachHangID  = KhachHangBLL.getInstance().generateNewID();
					KhachHang newKH = new KhachHang(newKhachHangID,sdt,ho,tenDem,ten,diaChi,gioiTinh,ngaySinh,diemTichLuy);
					KhachHangBLL.getInstance().addNewKhachHang(newKH);
					
					int newHoaDonID = HoaDonBLL.getInstance().generateNewID();

				    float tongTien = 0;
				    int maNhanVien = homeView.getCurrentNhanVien().getMaNhanVien();
				    int maBan = currentBanAn.getMaBan();
				    String tinhTrang="Chưa thanh toán";
				    HoaDon newHD = new HoaDon(newHoaDonID, null, tongTien, maNhanVien, newKhachHangID, maBan, tinhTrang);
				    HoaDonBLL.getInstance().addNewHoaDon(newHD);
				    
				    currentBanAn.setTrangThai("Có người");
				    BanAnBLL.getInstance().updateBan(currentBanAn);
				    homeView.Init_table_order_panel();
				    homeView.DisplayDetailOfTable(currentBanAn.getMaBan());
					dispose();
				}
			}
		});
        
        JButton cancelButton = new JButton("Hủy");
        cancelButton.setBackground(new Color(235, 182, 120));
        cancelButton.setBounds(255, 283, 97, 25);
        cancelButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				InitChoosingPanel();
			}
		});
        
        currentDisplayPanel.add(cancelButton);
		currentDisplayPanel.validate();
		currentDisplayPanel.repaint();
	}
	
	public void InitEnterFamiliarGuestPhoneNumberPanel() {
		currentDisplayPanel.removeAll();
		currentDisplayPanel.setLayout(null);
		currentDisplayPanel.setBackground(new Color(171, 219, 227));
		
		JLabel khachHangLabel = new JLabel("");
		khachHangLabel.setBounds(89, 134, 253, 21);
		currentDisplayPanel.add(khachHangLabel);
		
		
		JLabel sdtLabel = new JLabel("Nhập số điện thoại");
		sdtLabel.setBounds(89, 102, 150, 21);
		currentDisplayPanel.add(sdtLabel);
		
		JTextField sdtTxt;
		sdtTxt = new JTextField("");
		sdtTxt.setBounds(203, 102, 116, 20);
		currentDisplayPanel.add(sdtTxt);
		sdtTxt.setColumns(10);
		
		final int[] maKhach = new int[]{-1};
		
		JButton okButton = new JButton("OK");
		okButton.setBackground(new Color(235, 182, 120));
		okButton.setBounds(89, 257, 89, 23);
		currentDisplayPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (khachHangLabel.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Chưa tìm thấy số điện thoại của khách hàng");
				}
				else {
					int newHoaDonID = HoaDonBLL.getInstance().generateNewID();
				    float tongTien = 0;
				    int maNhanVien = 1;
				    String tinhTrang = "Chưa thanh toán";
				    
				    HoaDon newHD = new HoaDon(newHoaDonID, null, tongTien, maNhanVien, maKhach[0], currentBanAn.getMaBan(), tinhTrang);
				    HoaDonBLL.getInstance().addNewHoaDon(newHD);
				    
				    currentBanAn.setTrangThai("Có người");
				    BanAnBLL.getInstance().updateBan(currentBanAn);
				    homeView.Init_table_order_panel();
				    homeView.DisplayDetailOfTable(currentBanAn.getMaBan());
					dispose();
					dispose();
				}
			}
		});
		
		JButton cancelButton = new JButton("Hủy");
		cancelButton.setBackground(new Color(235, 182, 120));
		cancelButton.setBounds(281, 257, 89, 23);
		currentDisplayPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				InitChoosingPanel();
			}
		});
		
		JButton searchButton = new JButton("Tìm kiếm");
		searchButton.setBackground(new Color(235, 182, 120));
		searchButton.setBounds(329, 101, 89, 23);
		currentDisplayPanel.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean ok = true;
				if (sdtTxt.getText().length()!=10) {
					JOptionPane.showMessageDialog(null, "Sai định dạng số điện thoại");
					khachHangLabel.setText("");
					ok = false;
					return;
				}
				if (ok==true) {
					for (int i = 0 ; i < sdtTxt.getText().length(); i++)
						if (!Character.isDigit(sdtTxt.getText().charAt(i))) {
							JOptionPane.showMessageDialog(null, "Sai định dạng số điện thoại");
							khachHangLabel.setText("");
							ok = false;
							return;
						}
				}
				if (ok==true) {
					if (KhachHangBLL.getInstance().isSdtExist(sdtTxt.getText())) {
						KhachHang newKH = KhachHangBLL.getInstance().getKhachHangBySDT(sdtTxt.getText());
						khachHangLabel.setText("Khách hàng: "+ newKH.getHo()+" "+newKH.getTenDem()+" "+newKH.getTen());
						maKhach[0] = newKH.getMaKhach();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Số điện thoại không tồn tại");
						khachHangLabel.setText("");
					}
				}
			}
		});
		currentDisplayPanel.validate();
		currentDisplayPanel.repaint();
	}
	
	public HomeView getHomeView() {
		return homeView;
	}

	public BanAn getCurrentBanAn() {
		return currentBanAn;
	}

	public boolean isChoooseSuccess() {
		return ChoooseSuccess;
	}
	
}
