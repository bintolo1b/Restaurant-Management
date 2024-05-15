package VIEW;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import BLL.BanAnBLL;
import BLL.ChiTietDatBanBLL;
import BLL.ChiTietHoaDonBLL;
import BLL.DanhMucBLL;
import BLL.HoaDonBLL;
import BLL.SanPhamBLL;
import DAL.BanAnDAL;
import DAL.SanPhamDAL;
import DTO.BanAn;
import DTO.ChiTietDatBan;
import DTO.ChiTietHoaDon;
import DTO.DanhMuc;
import DTO.HoaDon;
import DTO.NhanVien;
import DTO.SanPham;

public class HomeView extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel currentDisplayPanel;
	private JPanel orderPanel;
	private NhanVien currentNhanVien;

	public HomeView(NhanVien currentNhanVien) {
		this.currentNhanVien = currentNhanVien;
		this.Init();
	}
	
	public void Init() {
		this.setSize(1212,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setResizable(false);
		currentDisplayPanel = new JPanel();
		currentDisplayPanel.setBackground(new Color(228, 236, 228));
		this.InitMenuPanel();
		this.InitHomePanel();
		getContentPane().add(currentDisplayPanel);
		this.setVisible(true);
	}
	
	public void InitTableResavationPanel() {
		currentDisplayPanel.removeAll();
		currentDisplayPanel.setLayout(null);
		currentDisplayPanel.setBounds(230, 70, 966, 691);
		
		currentDisplayPanel.setBackground(new Color(228, 236, 228));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 68, 942, 489);
		currentDisplayPanel.add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Ngày giờ đặt bàn");
		model.addColumn("Ngày giờ nhận/hủy bàn");
		model.addColumn("Mã bàn");
		model.addColumn("SĐT khách hàng");
		model.addColumn("Mã nhân viên");
		model.addColumn("Trạng thái");
		
		ArrayList<ChiTietDatBan> ctdbArrayList = ChiTietDatBanBLL.getInstance().getAllCTDB();
		for (int i = 0 ; i < ctdbArrayList.size() ; i++) {
			Object[] row = new Object[] {ctdbArrayList.get(i).getNgayGioDat(), ctdbArrayList.get(i).getNgayGioNhan_Huy()
					, ctdbArrayList.get(i).getMaBan(), ctdbArrayList.get(i).getSoDienThoaiKH(), ctdbArrayList.get(i).getMaNhanVien(), ctdbArrayList.get(i).getTrangThai()};
			model.addRow(row);
		}
		
		JTable resavationTable = new JTable(model);
		resavationTable.getTableHeader().setReorderingAllowed(false);
		resavationTable.setDefaultEditor(Object.class, null);
		resavationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resavationTable.setBackground(new Color(238, 238, 228));
		
		scrollPane.setViewportView(resavationTable);
		
		JLabel SelectData = new JLabel("Chọn thời gian muốn xem: ");
		SelectData.setBounds(52, 38, 180,20);
		currentDisplayPanel.add(SelectData);
		
		JDateChooser BeginDateChooser = new JDateChooser();
        BeginDateChooser.setDateFormatString("yyyy-MM-dd");
        BeginDateChooser.setCalendar(Calendar.getInstance());
        BeginDateChooser.setBounds(225, 38, 100, 20);
        currentDisplayPanel.add(BeginDateChooser);

		JLabel to = new JLabel("Đến");
		to.setBounds(347, 38, 50, 20);
		currentDisplayPanel.add(to);
		
		JDateChooser EndDateChooser = new JDateChooser();
        EndDateChooser.setDateFormatString("yyyy-MM-dd");
        EndDateChooser.setCalendar(Calendar.getInstance());
        EndDateChooser.setBounds(393, 38, 100, 20);
        currentDisplayPanel.add(EndDateChooser);
        
        JLabel PhoneNumberLabel = new JLabel("Số điện thoại");
        PhoneNumberLabel.setBounds(604, 38, 100, 20);
        currentDisplayPanel.add(PhoneNumberLabel);
        
        JTextField PhoneNumberText = new JTextField();
		PhoneNumberText.setBounds(716, 38, 100, 20);
		currentDisplayPanel.add(PhoneNumberText);
		
		JButton FindButton = new JButton("Tìm kiếm");
		FindButton.setBounds(854, 38, 100, 20);
		FindButton.setBackground(new Color(235,182,120));
		FindButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Date dateBegin = BeginDateChooser.getDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
				String formattedDateBegin = dateFormat.format(dateBegin);
				
				Date dateEnd = EndDateChooser.getDate();
				SimpleDateFormat dateFormatend = new SimpleDateFormat("yyyy-MM-dd 24:00:00");
				String formattedDateEnd = dateFormatend.format(dateEnd);
				
				ArrayList<ChiTietDatBan> ctdbAL = ChiTietDatBanBLL.getInstance().FindTable(formattedDateBegin, formattedDateEnd, PhoneNumberText.getText(), ctdbArrayList);
				model.setRowCount(0); 
				for (int i = 0 ; i < ctdbAL.size() ; i++) {
					Object[] row = new Object[] {ctdbAL.get(i).getNgayGioDat(), ctdbAL.get(i).getNgayGioNhan_Huy()
							, ctdbAL.get(i).getMaBan(), ctdbAL.get(i).getSoDienThoaiKH(), ctdbAL.get(i).getMaNhanVien(), ctdbAL.get(i).getTrangThai()};
					model.addRow(row);
				}
			}
		});
		currentDisplayPanel.add(FindButton);
		
		JButton datBanButton = new JButton("Đặt bàn");
		datBanButton.setBounds(86, 595, 110, 25);
		datBanButton.setBackground(new Color(235,182,120));
		datBanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReservationTableView rtv = new ReservationTableView();
				rtv.cancelButton.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						rtv.dispose();
					}
				});
				rtv.okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean k = true, k1 = true, k2 = true;
						
						if (rtv.SDTText.getText().equals("") == false)
						{
							for (int i=0; i<rtv.SDTText.getText().length()-1; i++) 
								if (rtv.SDTText.getText().charAt(i) >='0' && rtv.SDTText.getText().charAt(i)<='9'); 
								else { k2 = false; break;}
							if (k2)
							{
								Date date = rtv.DateChooser.getDate();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								String formattedDate = sdf.format(date);
	
								Date time = (Date)rtv.timeSpinner.getValue();
								SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
								String formattedTime = sdf1.format(time);
								
								LocalDateTime ltime = LocalDateTime.now();
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
								String formattedDateTime = ltime.format(formatter);
								
								if (formattedDateTime.substring(0, 10).equals(formattedDate))
								{
									if (formattedDateTime.substring(11, 13).compareTo(formattedTime.substring(0,2))>=0) k1 = false;
								} else if (formattedDateTime.substring(0, 10).compareTo(formattedDate)>0) k1 = false;
								if (k1)
								{
									for (ChiTietDatBan ct : ctdbArrayList) {
										if (ct.getMaBan() == rtv.bananArrayList.get(rtv.BanCBB.getSelectedIndex()).getMaBan())
										{
											if (ct.getTrangThai().equals("Chưa nhận bàn"))
											{	
													
												if (ct.getNgayGioDat().substring(0, 10).equals(formattedDate) && ct.getNgayGioDat().substring(11, 13).equals(formattedTime.substring(0,2)))
												{
													k = false;
													break;
												}
											} 
										}
									}
									if (k) 
									{
										ChiTietDatBan ct = new ChiTietDatBan(formattedDate+" "+ formattedTime, "", rtv.bananArrayList.get(rtv.BanCBB.getSelectedIndex()).getMaBan(), rtv.SDTText.getText(), 
											currentNhanVien.getMaNhanVien(), "Chưa nhận bàn");
										ctdbArrayList.add(ct);
										rtv.dispose();
										ChiTietDatBanBLL.getInstance().Insert(ct);
										model.setRowCount(0);
										for (int i = 0 ; i < ctdbArrayList.size() ; i++) {
											Object[] row = new Object[] {ctdbArrayList.get(i).getNgayGioDat(), ctdbArrayList.get(i).getNgayGioNhan_Huy()
													, ctdbArrayList.get(i).getMaBan(), ctdbArrayList.get(i).getSoDienThoaiKH(), ctdbArrayList.get(i).getMaNhanVien(), ctdbArrayList.get(i).getTrangThai()};
											model.addRow(row);
										}
									} else JOptionPane.showMessageDialog(null, "Bàn đã được đặt");
								} else JOptionPane.showMessageDialog(null, "Thời gian không hợp lệ");
							} else JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
						} else JOptionPane.showMessageDialog(null, "Số điện thoại trống");
					}
				});
			}
		});
		currentDisplayPanel.add(datBanButton);
		
		JButton nhanBanButton = new JButton("Nhận bàn");
		nhanBanButton.setBounds(313, 595, 110, 25);
		nhanBanButton.setBackground(new Color(235,182,120));
		nhanBanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (resavationTable.getSelectedRow()>=0)
				{
					int BanAnID = ctdbArrayList.get(resavationTable.getSelectedRow()).getMaBan();
					BanAn currentBanAn = BanAnBLL.getInstance().getBan(BanAnID);
					
					if (ctdbArrayList.get(resavationTable.getSelectedRow()).getTrangThai().equals("Đã nhận bàn")) {
						JOptionPane.showMessageDialog(null, "Bàn đã được nhận từ trước");
						return;
					}
					if (ctdbArrayList.get(resavationTable.getSelectedRow()).getTrangThai().equals("Đã hủy bàn")) {
						JOptionPane.showMessageDialog(null, "Bàn đặt đã bị hủy");
						return;
					}
					if (currentBanAn.getTrangThai().equals("Có người")) {
						JOptionPane.showMessageDialog(null, "Bàn đang có người sử dụng, vui lòng chuyển bàn");
						return;
					}
									
					if (ctdbArrayList.get(resavationTable.getSelectedRow()).getTrangThai().equals("Chưa nhận bàn"))
					{
						ChooseKindOfCustomerView chooseCustomer = new ChooseKindOfCustomerView(HomeView.this, HomeView.this, currentBanAn);	
						if (chooseCustomer.isChoooseSuccess()) {
							LocalDateTime time = LocalDateTime.now();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							String formattedDateTime = time.format(formatter);
			
							ctdbArrayList.get(resavationTable.getSelectedRow()).setNgayGioNhan_Huy(formattedDateTime);
							
							ctdbArrayList.get(resavationTable.getSelectedRow()).setTrangThai("Đã nhận bàn");
							
							ChiTietDatBanBLL.getInstance().Update(ctdbArrayList.get(resavationTable.getSelectedRow()));
							
							model.setRowCount(0); 
							for (int i = 0 ; i < ctdbArrayList.size() ; i++) {
								Object[] row = new Object[] {ctdbArrayList.get(i).getNgayGioDat(), ctdbArrayList.get(i).getNgayGioNhan_Huy()
										, ctdbArrayList.get(i).getMaBan(), ctdbArrayList.get(i).getSoDienThoaiKH(), ctdbArrayList.get(i).getMaNhanVien(), ctdbArrayList.get(i).getTrangThai()};
								model.addRow(row);
							}
						}
					}
				}
				else JOptionPane.showMessageDialog(null, "Chọn bàn để nhận");
					
			}
		});
		currentDisplayPanel.add(nhanBanButton);
		
		JButton huyBanButton = new JButton("Hủy đặt bàn");
		huyBanButton.setBounds(542, 595, 110, 25);
		huyBanButton.setBackground(new Color(235,182,120));
		huyBanButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (resavationTable.getSelectedRowCount()==0) {
					JOptionPane.showMessageDialog(null, "Chọn bàn để hủy");
					return;
				}
				if (ctdbArrayList.get(resavationTable.getSelectedRow()).getTrangThai().equals("Đã nhận bàn") == false)
				{
					ctdbArrayList.get(resavationTable.getSelectedRow()).setTrangThai("Đã hủy bàn");
					LocalDateTime time = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					String formattedDateTime = time.format(formatter);
	
					ctdbArrayList.get(resavationTable.getSelectedRow()).setNgayGioNhan_Huy(formattedDateTime);

					ChiTietDatBanBLL.getInstance().Update(ctdbArrayList.get(resavationTable.getSelectedRow()));
					
					model.setRowCount(0); 
					for (int i = 0 ; i < ctdbArrayList.size() ; i++) {
						Object[] row = new Object[] {ctdbArrayList.get(i).getNgayGioDat(), ctdbArrayList.get(i).getNgayGioNhan_Huy()
								, ctdbArrayList.get(i).getMaBan(), ctdbArrayList.get(i).getSoDienThoaiKH(), ctdbArrayList.get(i).getMaNhanVien(), ctdbArrayList.get(i).getTrangThai()};
						model.addRow(row);
					}
				} else JOptionPane.showMessageDialog(null, "Không thể hủy bàn");
			}
		});
		currentDisplayPanel.add(huyBanButton);
		
		JComboBox<BanAn> BanCBB = new JComboBox<BanAn>();
		ArrayList<BanAn> bananArrayList = BanAnDAL.getInstance().getAll();
		for (int i=0; i<bananArrayList.size(); i++)
			if (!bananArrayList.get(i).getTrangThai().equals("Đã ẩn"))
				BanCBB.addItem(bananArrayList.get(i));
		BanCBB.setBounds(869, 595, 50, 25);
		currentDisplayPanel.add(BanCBB);
		
		JButton chuyenBanButton = new JButton("Chuyển bàn");
		chuyenBanButton.setBounds(759, 595, 110, 25);
		chuyenBanButton.setBackground(new Color(235,182,120));
		chuyenBanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (resavationTable.getSelectedRowCount()==0) {
					JOptionPane.showMessageDialog(null, "Chọn bàn để chuyển");
					return;
				}
				
				if (resavationTable.getSelectedRowCount()==1 && ctdbArrayList.get(resavationTable.getSelectedRow()).getTrangThai().equals("Chưa nhận bàn"))
				{
					if (bananArrayList.get(BanCBB.getSelectedIndex()).getMaBan() != ctdbArrayList.get(resavationTable.getSelectedRow()).getMaBan())
					{	
						int oldMaBan = ctdbArrayList.get(resavationTable.getSelectedRow()).getMaBan();
						ctdbArrayList.get(resavationTable.getSelectedRow()).setMaBan(((BanAn)BanCBB.getSelectedItem()).getMaBan());
						
						ChiTietDatBanBLL.getInstance().UpdateCB(ctdbArrayList.get(resavationTable.getSelectedRow()), oldMaBan);
						
						model.setRowCount(0); 
						for (int i = 0 ; i < ctdbArrayList.size() ; i++) {
							Object[] row = new Object[] {ctdbArrayList.get(i).getNgayGioDat(), ctdbArrayList.get(i).getNgayGioNhan_Huy()
									, ctdbArrayList.get(i).getMaBan(), ctdbArrayList.get(i).getSoDienThoaiKH(), ctdbArrayList.get(i).getMaNhanVien(), ctdbArrayList.get(i).getTrangThai()};
						model.addRow(row);
						}
					} else JOptionPane.showMessageDialog(null, "Không thể chuyển bàn");
				} else JOptionPane.showMessageDialog(null, "Không thể chuyển bàn");
			}
		});
		currentDisplayPanel.add(chuyenBanButton);
		
		currentDisplayPanel.validate();
		currentDisplayPanel.repaint();
	}
	
	public void InitMenuPanel() {
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setBounds(0,0,230,800);
		Color color1 = new Color(128,57,30);
		Color color2 = new Color(235,182,120);
		menuPanel.setBackground(color2);
		
		Font font1 = new Font("Constantia", Font.BOLD | Font.ITALIC, 15);
		Font font2 = new Font("Vivaldi", Font.BOLD, 50);
		
		JLabel logoLabel = new JLabel();
		logoLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(HomeView.class.getResource("logo_pbl3.jpg"))));
		logoLabel.setBounds(10, 80, 208, 210);
		
		JLabel menuLabel = new JLabel("Menu");
		menuLabel.setFont(font2);
		menuLabel.setForeground(color1);
		menuLabel.setBounds(35,300,150,50);
		
		JButton homeButton = new JButton("Trang chủ");
		homeButton.setFont(font1);
		homeButton.setForeground(color1);
		homeButton.setBorderPainted(false);
		homeButton.setBackground(color2);
		homeButton.setBounds(35,400,150,50);
		homeButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				InitHomePanel();
			}
		});
		
		JButton tableButton = new JButton("Bàn ăn");
		tableButton.setFont(font1);
		tableButton.setForeground(color1);
		tableButton.setBorderPainted(false);
		tableButton.setBackground(color2);
		tableButton.setBounds(35,450,150,50);
		tableButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Init_table_order_panel();
			}
		});
		
		JButton categoryButton = new JButton("Danh mục");
		categoryButton.setFont(font1);
		categoryButton.setForeground(color1);
		categoryButton.setBorderPainted(false);
		categoryButton.setBackground(color2);
		categoryButton.setBounds(35,500,150,50);
		categoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InitCategoryPanel();
			}
		});
		
		JButton tableReservationListButton = new JButton("Bàn đặt");
		tableReservationListButton.setFont(font1);
		tableReservationListButton.setForeground(color1);
		tableReservationListButton.setBorderPainted(false);
		tableReservationListButton.setBackground(color2);
		tableReservationListButton.setBounds(35,550,150,50);
		tableReservationListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InitTableResavationPanel();
			}
		});
			
		menuPanel.add(logoLabel);
		menuPanel.add(menuLabel);
		menuPanel.add(homeButton);
		menuPanel.add(tableButton);
		menuPanel.add(categoryButton);
		menuPanel.add(tableReservationListButton);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(230,0,966,70);
		headerPanel.setBackground(new Color(228,236,228));
		headerPanel.setLayout(new GridBagLayout());
		JLabel headerLabel = new JLabel("Quản lí nhà hàng");
		headerLabel.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 30));
		headerLabel.setForeground(color1);
		headerPanel.add(headerLabel);
		
		JPanel helloPanel = new JPanel();
		helloPanel.setBackground(new Color(235, 182, 120));
		helloPanel.setBounds(10, 25, 210, 50);
		menuPanel.add(helloPanel);
		helloPanel.setLayout(new GridBagLayout());
		
		JLabel helloLabel = new JLabel("Xin chào "+currentNhanVien.getHo()+" "+currentNhanVien.getTenDem()+" "+currentNhanVien.getTen());
		helloLabel.setForeground(new Color(128, 57, 30));
		helloLabel.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 15));
		helloPanel.add(helloLabel);
		
		getContentPane().add(menuPanel);
		getContentPane().add(headerPanel);
	}
	
	public void InitHomePanel() {
		currentDisplayPanel.removeAll();
		currentDisplayPanel.setLayout(null);
		currentDisplayPanel.setBounds(230, 70, 966, 691);
		
		JPanel homePanel = new JPanel();
		homePanel.setLayout(null);
		homePanel.setBounds(0, 0, 966, 691);
		homePanel.setBackground(new Color(228,236,228));
		
		ImageIcon icon = new ImageIcon("restaurant_logo.png");
		JLabel logo_lbl = new JLabel();
		logo_lbl.setBounds(325, 44, 400, 400);
		logo_lbl.setIcon(icon);
		
		
		JButton profile_btn = new JButton("Hồ sơ cá nhân");
		JButton changePassword_btn = new JButton("Đổi mật khẩu");
		JButton signOut_btn = new JButton("Đăng xuất");
		
		profile_btn.setBounds(401, 430, 150, 30);
		changePassword_btn.setBounds(401,473,150,30);
		signOut_btn.setBounds(401, 516, 150, 30);
		
		profile_btn.setBackground(new Color(235, 182, 120));
		changePassword_btn.setBackground(new Color(235, 182, 120));
		signOut_btn.setBackground(new Color(235, 182, 120));
		
		profile_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ProfileView(currentNhanVien);
			}
		});
		
		changePassword_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePasswordView(currentNhanVien.getTaiKhoan());
			}
		});
		
		signOut_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginView();
			}
		});
		
		homePanel.add(logo_lbl);
		homePanel.add(profile_btn);
		homePanel.add(changePassword_btn);
		homePanel.add(signOut_btn);
		
		
		currentDisplayPanel.add(homePanel);
		currentDisplayPanel.validate();
		currentDisplayPanel.repaint();	
	}
	
	public void InitCategoryPanel(){
		currentDisplayPanel.removeAll();
		currentDisplayPanel.setLayout(null);
		currentDisplayPanel.setBounds(230, 70, 966, 730);
		
		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(null);
		categoryPanel.setBounds(0, 0, 370, 730);
		categoryPanel.setBackground(new Color(228,236,228));
		
		DefaultTableModel categoryModel = new DefaultTableModel();
		categoryModel.addColumn("Mã danh mục");
		categoryModel.addColumn("Tên danh mục");
		categoryModel.addColumn("Trạng thái");
		for(DanhMuc i: DanhMucBLL.getInstance().getAllDanhMuc()) {
			categoryModel.addRow(new Object[] {i.getMaDanhMuc(),i.getTenDanhMuc(),i.getTrangThai()});
		}
		
		JTable categoryTable = new JTable(categoryModel);
		JScrollPane categoryScrollPane = new JScrollPane(categoryTable);
		categoryScrollPane.setBounds(10, 0, 350, 550);
		categoryTable.setBackground(new Color(238,238,228)); 
		
		categoryTable.getTableHeader().setReorderingAllowed(false); // Ngăn chặn việc di chuyển cột

		// Ngăn chặn việc chỉnh sửa thông tin trong các ô của bảng
		DefaultTableModel categoryModel1 = (DefaultTableModel) categoryTable.getModel();
		for (int i = 0; i < categoryModel1.getColumnCount(); i++) {
		    categoryTable.setDefaultEditor(categoryModel1.getColumnClass(i), null);
		}
		
		JPanel productPanel = new JPanel();
		productPanel.setLayout(null);
		productPanel.setBounds(369, 0, 597, 605);
		productPanel.setBackground(new Color(171,219,227));
		
		DefaultTableModel productModel = new DefaultTableModel();
		productModel.addColumn("Mã sản phẩm");
		productModel.addColumn("Tên");
		productModel.addColumn("Giá");
		productModel.addColumn("Trạng thái");
		productModel.addColumn("Mã danh mục");
		
		JTable productTable = new JTable(productModel);
		productTable.setBackground(new Color(238,238,228)); 
		productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane productScrollPane = new JScrollPane(productTable);
		productScrollPane.setBounds(0, 0, 597, 550);
		
		productTable.getTableHeader().setReorderingAllowed(false); // Ngăn chặn việc di chuyển cột

		// Ngăn chặn việc chỉnh sửa thông tin trong các ô của bảng
		DefaultTableModel productModel1 = (DefaultTableModel) productTable.getModel();
		for (int i = 0; i < productModel1.getColumnCount(); i++) {
		    productTable.setDefaultEditor(productModel1.getColumnClass(i), null);
		}
		
		int[] categoryID = {0};
		
		categoryTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            int selectedRow = categoryTable.getSelectedRow();
		            if (selectedRow != -1) {
		                Object categoryObject = categoryTable.getValueAt(selectedRow, 0); // Lấy mã danh mục từ hàng được chọn
		                try {
		                    categoryID[0] = Integer.parseInt(categoryObject.toString());
		                    ArrayList<SanPham> sanPhamArrayList = SanPhamBLL.getInstance().getAllSanPhamInDanhMuc(categoryID[0]);
		                    productModel1.setRowCount(0); // Xóa các dòng cũ trong productModel
		                    if (!sanPhamArrayList.isEmpty()) {
		                        for (SanPham sanPham : sanPhamArrayList) {
		                            productModel1.addRow(new Object[] {
		                                sanPham.getMaSanPham(),
		                                sanPham.getTen(),
		                                sanPham.getGia(),
		                                sanPham.getTrangThai(),
		                                sanPham.getMaDanhMuc()
		                            });
		                        }
		                    }
		                } catch (NumberFormatException ex) {
		                    ex.printStackTrace();
		                }
		            }
		        }
		    }
		});
		
		categoryPanel.add(categoryScrollPane);
		productPanel.add(productScrollPane);
		currentDisplayPanel.add(categoryPanel);
		currentDisplayPanel.add(productPanel);
		
		JTextField search_txt = new JTextField();
		search_txt.setBounds(172, 563, 230, 30);
		productPanel.add(search_txt);
		
		JLabel search_lbl = new JLabel("Tìm sản phẩm");
		search_lbl.setBounds(10, 563, 150, 30);
		productPanel.add(search_lbl);
		
		JButton search_btn = new JButton("Tìm");
		search_btn.setBounds(443, 563, 80, 30);
		productPanel.add(search_btn);
		search_btn.setBackground(new Color(235,182,120));
		
		search_btn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String productName = search_txt.getText();
		        ArrayList<SanPham> sanPhamArrayList = SanPhamBLL.getInstance().getSanPhamBySubName(productName);

		        boolean found = false; // Flag to track if any products were found in the category
		        if (!sanPhamArrayList.isEmpty()) {
		            for (SanPham sanPham : sanPhamArrayList) {
		                if (sanPham.getMaDanhMuc() == categoryID[0]) {
		                	productModel.setRowCount(0);
		                    productModel.addRow(new Object[]{
		                            sanPham.getMaSanPham(),
		                            sanPham.getTen(),
		                            sanPham.getGia(),
		                            sanPham.getTrangThai(),
		                            sanPham.getMaDanhMuc()
		                    });
		                    found = true;
		                }
		            }
		        }

		        // Show message dialog if no products were found in the selected category
		        if (!found) {
		            JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm trong danh mục này!");
		            search_txt.setText("");
		        }
		    }
		});
		currentDisplayPanel.validate();
		currentDisplayPanel.repaint();
	}
	
	public void Init_table_order_panel() {
		currentDisplayPanel.removeAll();
		currentDisplayPanel.setLayout(null);
		currentDisplayPanel.setBounds(230,70,966,691);
				
		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(new Color(228, 236, 228));
		tablePanel.setLayout(new GridLayout(0, 4, 25, 25)); 

		ArrayList<BanAn> banAnArrayList = BanAnBLL.getInstance().getAllBanAn();
		for (int i = 0; i < banAnArrayList.size(); i++)
			if (!banAnArrayList.get(i).getTrangThai().equals("Đã ẩn"))
				{
				    JButton jButton = new JButton();
				    if (banAnArrayList.get(i).getTrangThai().equals("Có người"))
				        jButton.setBackground(Color.red);
				    else
				        jButton.setBackground(Color.green);
				    jButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String s = e.getActionCommand();
							String subs ="";
							for (int i = 1 ;i<s.length() ;i++)
								if (s.charAt(i-1)==' ' && s.charAt(i)!=' ')
									subs=s.substring(i,s.length());
							DisplayDetailOfTable(Integer.parseInt(subs));
						}
					});
				    
				    int tableID = banAnArrayList.get(i).getMaBan();
				    if (tableID + 1 < 10)
				        jButton.setText("Bàn   " + (tableID));
				    else
				        jButton.setText("Bàn " + (tableID));
				    tablePanel.add(jButton);
				}

		JScrollPane tableScrollPane = new JScrollPane(tablePanel);
		tableScrollPane.setBorder(new EmptyBorder(0, 5, 5, 0));
		tableScrollPane.setBounds(0, 0, 450, 629);
		
		JPanel notePanel = new JPanel();
		notePanel.setBounds(0,630,450,61);
		notePanel.setBackground(new Color(228,236,228));
		
		orderPanel = new JPanel();
		orderPanel.setLayout(null);
		orderPanel.setBackground(new Color(171,219,227));
		orderPanel.setBounds(450 ,0, 516, 691);
			
		currentDisplayPanel.add(tableScrollPane);
		currentDisplayPanel.add(notePanel);
		notePanel.setLayout(null);
		
		JLabel greenLabel = new JLabel("");
		greenLabel.setOpaque(true);
		greenLabel.setBackground(new Color(0, 255, 0));
		greenLabel.setBounds(32, 11, 46, 25);
		notePanel.add(greenLabel);
		
		JLabel redLabel = new JLabel("");
		redLabel.setOpaque(true);
		redLabel.setBackground(new Color(255, 0, 0));
		redLabel.setBounds(270, 11, 46, 24);
		notePanel.add(redLabel);
		
		JLabel tableEmptyLabel = new JLabel("Bàn trống");
		tableEmptyLabel.setBounds(88, 22, 72, 14);
		notePanel.add(tableEmptyLabel);
		
		JLabel tableInUseLabel = new JLabel("Bàn đang sử dụng");
		tableInUseLabel.setBounds(325, 22, 115, 14);
		notePanel.add(tableInUseLabel);
		currentDisplayPanel.add(orderPanel);
		
		currentDisplayPanel.validate();
		currentDisplayPanel.repaint();		
	}
	
	public void DisplayDetailOfTable(int tableID) {
		this.orderPanel.removeAll(); 
		this.orderPanel.setLayout(null);
		this.orderPanel.setBackground(new Color(171,219,227));
		BanAn currentBanAn = BanAnBLL.getInstance().getBan(tableID);
		
		if (currentBanAn.getTrangThai().endsWith("Trống")) {	
			JLabel tableIDLabel = new JLabel("Bàn " + tableID);
			tableIDLabel.setFont(new Font("Arial", Font.BOLD, 15));
			tableIDLabel.setForeground(Color.red);
			tableIDLabel.setBounds(20, 0, 100, 50);
			
			JButton openButton = new JButton("Mở bàn");
			openButton.setBackground(new Color(235,182,120));
			openButton.setBounds(215, 319, 100, 50);
			openButton.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent e) {
					new ChooseKindOfCustomerView(HomeView.this, HomeView.this, currentBanAn);			
				}
			});			
			this.orderPanel.add(tableIDLabel);
			this.orderPanel.add(openButton);
		}
		else 
		{	
			JScrollPane orderDetailScrollPane = new JScrollPane();
			orderDetailScrollPane.setLayout(null);
			
			HoaDon currentHoaDon = HoaDonBLL.getInstance().getLatestHoaDonOfTable(tableID);
			ArrayList<ChiTietHoaDon> cthdOfCurrentHoaDonArrayList = ChiTietHoaDonBLL.getInstance().getAllChiTietHoaDonOfHoaDon(currentHoaDon.getMaHoaDon());
			
			JLabel tableIDLabel = new JLabel("Bàn " + tableID);
			tableIDLabel.setFont(new Font("Arial", Font.BOLD, 15));
			tableIDLabel.setForeground(Color.red);
			tableIDLabel.setBounds(20, 0, 100, 50);
			
			JLabel order_header_label = new JLabel("Đặt món");
			order_header_label.setFont(new Font("Constantia", Font.BOLD, 20));
			order_header_label.setBounds(224, 2, 100, 50);
			
			JLabel dishLabel = new JLabel("Món ăn");
			dishLabel.setFont(new Font("Constantia", Font.BOLD, 15));
			dishLabel.setBounds(20, 95, 100, 50);
			
			JComboBox<String> dishJComboBox = new JComboBox<String>();
			dishJComboBox.setBackground(new Color(238,238,228));
			dishJComboBox.setBounds(177,103,291,30);
			dishJComboBox.setSelectedIndex(-1);
			
			JLabel quantityLabel = new JLabel("Số lượng");
			quantityLabel.setFont(new Font("Constantia", Font.BOLD, 15));
			quantityLabel.setBounds(20, 145, 100, 50);
			
			JSpinner dishquantityjSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
			dishquantityjSpinner.setBounds(179, 153, 50, 30);
			
			JLabel catogoryLabel = new JLabel("Danh mục");
			catogoryLabel.setFont(new Font("Constantia", Font.BOLD, 15));
			catogoryLabel.setBounds(20, 45, 100, 50);
			
			JComboBox<String> catogoryJComboBox = new JComboBox<String>();
			catogoryJComboBox.setBackground(new Color(238,238,228));
			catogoryJComboBox.setBounds(179,53,289,30);	
			ArrayList<DanhMuc> danhMucArrayList = DanhMucBLL.getInstance().getAllDanhMuc();
			for (DanhMuc i : danhMucArrayList)
				if (!i.getTrangThai().equals("Đã ẩn")){
						catogoryJComboBox.addItem(i.getTenDanhMuc());
				}
			catogoryJComboBox.setSelectedIndex(-1);
			catogoryJComboBox.addItemListener(new ItemListener() {	
				@Override
				public void itemStateChanged(ItemEvent e) {
					dishJComboBox.removeAllItems();
					String danhMucStr = catogoryJComboBox.getSelectedItem().toString();
					ArrayList<SanPham> sanPhamArrayList = SanPhamBLL.getInstance().getAllSanPhamInDanhMucByName(danhMucStr);	
					for (SanPham i : sanPhamArrayList)
						if (!i.getTrangThai().equals("Đã ẩn")){
							dishJComboBox.addItem(i.getTen());
						}
					dishJComboBox.setSelectedIndex(-1);	
				}
			});
			
			DefaultTableModel servedModel = new DefaultTableModel();
			servedModel.addColumn("Tên món");
			servedModel.addColumn("Số lượng");
			servedModel.addColumn("Đơn giá");
			servedModel.addColumn("Thành tiền");
			servedModel.addColumn("Lần gọi");
			servedModel.addColumn("Tình trạng");
			
			ChiTietHoaDonBLL.getInstance().exportChiTietHoaDonIntoServedModelTable(servedModel, cthdOfCurrentHoaDonArrayList);
			
			JTable orderTable = new JTable(servedModel);
			orderTable.setDefaultEditor(Object.class, null);
			orderTable.getTableHeader().setReorderingAllowed(false);
			orderTable.setBackground(new Color(238,238,228)); 
			orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane servedProductJTable = new JScrollPane(orderTable);
			servedProductJTable.setBounds(12, 227, 492, 126);
			
			float[] amountFinal = {currentHoaDon.getTongTien()};		
			
	        Locale locale = new Locale.Builder().setLanguage("vi").setRegion("VN").build();
	        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
	        String formattedAmount = currencyFormat.format(amountFinal[0]);
			JTextField totalTextField = new JTextField(formattedAmount);
			totalTextField.setForeground(Color.red);
			totalTextField.setEditable(false);
			totalTextField.setHorizontalAlignment(SwingConstants.CENTER);
			totalTextField.setBounds(404, 366, 100, 30);
			
			JLabel totalLabel = new JLabel("Tổng tiền: ");
			totalLabel.setFont(new Font("Constantia", Font.BOLD, 15));
			totalLabel.setBounds(12, 366, 100, 27);
			
			JButton payButton = new JButton("Thanh toán");
			payButton.setFont(new Font("Constantia", Font.BOLD, 15));
			payButton.setBackground(new Color(238,238,228));
			payButton.setBounds(180, 628, 150, 50);
			payButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new PaymentView(HomeView.this, currentHoaDon.getMaHoaDon(), HomeView.this, false);		
				}
			});
			
			JLabel waitingListLabel = new JLabel("Danh sách món đã phục vụ");
			waitingListLabel.setFont(new Font("Constantia", Font.BOLD, 15));
			waitingListLabel.setBounds(171, 208, 216, 16);
				
			
			JScrollPane waitingProductScrolPane = new JScrollPane();
			waitingProductScrolPane.setBounds(12, 424, 492, 126);
			orderPanel.add(waitingProductScrolPane);
			
			DefaultTableModel waitingModel = new DefaultTableModel();
			waitingModel.addColumn("Tên món");
			waitingModel.addColumn("Số lượng");
			waitingModel.addColumn("Đơn giá");
			waitingModel.addColumn("Thành tiền");
			waitingModel.addColumn("Lần gọi");
			waitingModel.addColumn("Tình trạng");
			
			ChiTietHoaDonBLL.getInstance().exportChiTietHoaDonIntoWaitingModelTable(waitingModel, cthdOfCurrentHoaDonArrayList);
			
			JTable waitingProductJTable = new JTable(waitingModel);
			waitingProductJTable.setDefaultEditor(Object.class, null);
			waitingProductJTable.getTableHeader().setReorderingAllowed(false);
			waitingProductJTable.setBackground(new Color(238,238,228)); 
			waitingProductScrolPane.setViewportView(waitingProductJTable);
			
			JButton addDishButton = new JButton("Thêm món");
			addDishButton.setBackground(new Color(234,182,118));
			addDishButton.setBounds(368, 153, 100, 30);
			addDishButton.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					if (catogoryJComboBox.getSelectedIndex()!=-1 && dishJComboBox.getSelectedIndex()!=-1) {
						int[] newLanGoi = {1};
						ChiTietHoaDon CTHD = ChiTietHoaDonBLL.getInstance().getCTHDByLatestLanGoi(currentHoaDon.getMaHoaDon());
						if (CTHD!=null) {		
							if (CTHD.getTinhTrangPhucVu().equals("Chưa in đơn"))
								newLanGoi[0] = CTHD.getLanGoi();
							else
								newLanGoi[0] = CTHD.getLanGoi()+1;
						}
						
						String tenMon = dishJComboBox.getSelectedItem().toString();
						int soLuong =  (int) dishquantityjSpinner.getValue();
						float donGia = SanPhamBLL.getInstance().getGia(tenMon);
						float thanhTien = soLuong*donGia;
						for (int i = 0; i<waitingModel.getRowCount(); i++) 
						{	
							Object value = waitingModel.getValueAt(i, 0);
							int lanGoi = (int)waitingModel.getValueAt(i, 4);
				
							if (value.toString().equals(dishJComboBox.getSelectedItem().toString()) && 	lanGoi==newLanGoi[0] ) {
								waitingModel.setValueAt((int)waitingModel.getValueAt(i, 1)+soLuong, i, 1);
								waitingModel.setValueAt((float)waitingModel.getValueAt(i, 3)+thanhTien, i, 3);
								ChiTietHoaDonBLL.getInstance().upDateCTHD(currentHoaDon.getMaHoaDon(), waitingModel);
								return;
							}
						}  
						Object[] rowData = {tenMon, soLuong, donGia, thanhTien, newLanGoi[0], "Chưa in đơn"};
						waitingModel.addRow(rowData);
						
						ChiTietHoaDonBLL.getInstance().upDateCTHD(currentHoaDon.getMaHoaDon(), waitingModel);
					}
				}
			});
			
			JLabel haventServedYetLabel = new JLabel("Danh sách món chưa phục vụ");
			haventServedYetLabel.setFont(new Font("Constantia", Font.BOLD, 15));
			haventServedYetLabel.setBounds(171, 404, 232, 16);
			orderPanel.add(haventServedYetLabel);
			
			JButton cancelButton = new JButton("Hủy món");
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (waitingProductJTable.getSelectedRowCount()!=0) {	
						ArrayList<Object[]> confirmServedProduct = new ArrayList<Object[]>();
			        	int[] selectedRows = waitingProductJTable.getSelectedRows();
			        	
			        	
						for (int i = waitingProductJTable.getSelectedRowCount()-1; i>= 0 ; i--) {
							confirmServedProduct.add(new Object[] {waitingModel.getValueAt(i, 0),waitingModel.getValueAt(i, 1),waitingModel.getValueAt(i, 2),waitingModel.getValueAt(i, 3),waitingModel.getValueAt(i, 4),waitingModel.getValueAt(i, 5)});
						}
						
						if (confirmServedProduct.size()!=0) {
							ConfirmCancelDishView confirmView = new ConfirmCancelDishView(HomeView.this, currentHoaDon.getMaHoaDon(), confirmServedProduct);
							if (confirmView.isClickOk()==true) {		
								for (int i = waitingProductJTable.getSelectedRowCount()-1; i>= 0 ; i--) {
									String tenSP = waitingModel.getValueAt(selectedRows[i], 0).toString();
									int maSP  = SanPhamDAL.getInstance().getSanPhamByTen(tenSP).getMaSanPham(); 
									int lanGoi = (int)waitingModel.getValueAt(selectedRows[i], 4);
									ChiTietHoaDon cthd = ChiTietHoaDonBLL.getInstance().getChiTietHoaDon(currentHoaDon.getMaHoaDon(), maSP, lanGoi);
									
									waitingModel.removeRow(selectedRows[i]);		
									
									ChiTietHoaDonBLL.getInstance().deleteCTHD(cthd);;						
								}
							}
						}
					}	
				}
			});
			cancelButton.setBackground(new Color(235, 182, 120));
			cancelButton.setBounds(144, 563, 97, 25);
			orderPanel.add(cancelButton);
			
			JButton exportButton = new JButton("In đơn");
			exportButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ArrayList<Object[]> newProductNotConfirmedYet = new ArrayList<Object[]>();
					for (int i = 0 ; i < waitingModel.getRowCount();i++) {
						if (waitingModel.getValueAt(i, 5).toString().equals("Chưa in đơn")) {
							newProductNotConfirmedYet.add(new Object[] {waitingModel.getValueAt(i, 0),waitingModel.getValueAt(i, 1),waitingModel.getValueAt(i, 2),waitingModel.getValueAt(i, 3),waitingModel.getValueAt(i, 4),waitingModel.getValueAt(i, 5)});
						}
					}
					if (newProductNotConfirmedYet.size()!=0) {
						ConfirmAddProductView confirmView = new ConfirmAddProductView(HomeView.this, currentHoaDon.getMaHoaDon(), newProductNotConfirmedYet);
						if (confirmView.isClickOk()==true) {		
							for (int i = 0 ; i < waitingModel.getRowCount();i++) {
								if (waitingModel.getValueAt(i, 5).toString().equals("Chưa in đơn")) {
									waitingModel.setValueAt("Chờ món", i, 5);
								}
							}
							ChiTietHoaDonBLL.getInstance().upDateCTHD(currentHoaDon.getMaHoaDon(), waitingModel);			
						}
					}
				}
			});
			exportButton.setBackground(new Color(235, 182, 120));
			exportButton.setBounds(12, 563, 97, 25);
			orderPanel.add(exportButton);
			
			JButton giamMonButton = new JButton("Giảm món");
			giamMonButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			        if (waitingProductJTable.getSelectedRowCount()>1) {
			        	JOptionPane.showMessageDialog(null, "Vui lòng chỉ chọn một món để giảm");
			        	return;
			        }
			        else if (waitingProductJTable.getSelectedRowCount()==1 && waitingModel.getValueAt(waitingProductJTable.getSelectedRow(), 5).equals("Chờ món")) {
			        	JOptionPane.showMessageDialog(null, "Món đang được chế biến, không thể giảm");
			        	return;
			        }
			        else if (waitingProductJTable.getSelectedRowCount()==1) {
			        	if ((int)waitingModel.getValueAt(waitingProductJTable.getSelectedRow(), 1)!=1) {
			        		waitingModel.setValueAt((int)waitingModel.getValueAt(waitingProductJTable.getSelectedRow(), 1)-1, waitingProductJTable.getSelectedRow(), 1);
			        		waitingModel.setValueAt((float)waitingModel.getValueAt(waitingProductJTable.getSelectedRow(), 3)-(float)waitingModel.getValueAt(waitingProductJTable.getSelectedRow(), 2), waitingProductJTable.getSelectedRow(), 3);
							ChiTietHoaDonBLL.getInstance().upDateCTHD(currentHoaDon.getMaHoaDon(), waitingModel);
			        	}			 
			        }
				}
			});
			giamMonButton.setBackground(new Color(235, 182, 120));
			giamMonButton.setBounds(277, 563, 97, 25);
			orderPanel.add(giamMonButton);
			
			JButton serveButton = new JButton("Đã phục vụ");
			serveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int[] selectedRows = waitingProductJTable.getSelectedRows();
					
					for (int i = waitingProductJTable.getSelectedRowCount()-1; i>= 0 ; i--) {
						if (waitingModel.getValueAt(selectedRows[i], 5).toString().equals("Chưa in đơn")) {
							JOptionPane.showMessageDialog(null, "Có món đang chọn chưa được in đơn, vui lòng in đơn trước");
							return;
						}
					}
					
					ArrayList<Object[]> confirmServedProduct = new ArrayList<Object[]>();
					for (int i = waitingProductJTable.getSelectedRowCount()-1; i>= 0 ; i--) {
						confirmServedProduct.add(new Object[] {waitingModel.getValueAt(i, 0),waitingModel.getValueAt(i, 1),waitingModel.getValueAt(i, 2),waitingModel.getValueAt(i, 3),waitingModel.getValueAt(i, 4),waitingModel.getValueAt(i, 5)});
					}
					
					if (confirmServedProduct.size()!=0) {
						ConfirmDishServedView confirmView = new ConfirmDishServedView(HomeView.this, currentHoaDon.getMaHoaDon(), confirmServedProduct);
						if (confirmView.isClickOk()==true) {		
							for (int i = waitingProductJTable.getSelectedRowCount()-1; i>= 0 ; i--) {
								String tenSP = waitingModel.getValueAt(selectedRows[i], 0).toString();
								int maSP  = SanPhamDAL.getInstance().getSanPhamByTen(tenSP).getMaSanPham(); 
								int lanGoi = (int)waitingModel.getValueAt(selectedRows[i], 4);
								ChiTietHoaDon cthd = ChiTietHoaDonBLL.getInstance().getChiTietHoaDon(currentHoaDon.getMaHoaDon(), maSP, lanGoi);
								
								cthd.setTinhTrangPhucVu("Đã phục vụ");
								ChiTietHoaDonBLL.getInstance().updateCTHD(cthd);
								
								currentHoaDon.setTongTien(currentHoaDon.getTongTien()+(float)waitingModel.getValueAt(selectedRows[i], 3));
								HoaDonBLL.getInstance().updateHD(currentHoaDon);
								
								DisplayDetailOfTable(currentBanAn.getMaBan());		
							}
						}
					}
				}
			});
			serveButton.setBackground(new Color(235, 182, 120));
			serveButton.setBounds(407, 563, 97, 25);
			orderPanel.add(serveButton);
			
			this.orderPanel.add(tableIDLabel);
			this.orderPanel.add(order_header_label);
			this.orderPanel.add(catogoryLabel);
			this.orderPanel.add(catogoryJComboBox);
			this.orderPanel.add(dishLabel);
			this.orderPanel.add(dishJComboBox);
			this.orderPanel.add(quantityLabel);
			this.orderPanel.add(dishquantityjSpinner);
			this.orderPanel.add(addDishButton);
			this.orderPanel.add(servedProductJTable);
			this.orderPanel.add(totalLabel);
			this.orderPanel.add(totalTextField);
			this.orderPanel.add(payButton);
			this.orderPanel.add(waitingListLabel);
		}
		
		
		
		this.orderPanel.validate(); 
		this.orderPanel.repaint();
	}
	
	public NhanVien getCurrentNhanVien() {
		return currentNhanVien;
	}

	public void setCurrentNhanVien(NhanVien currentNhanVien) {
		this.currentNhanVien = currentNhanVien;
	}
}
