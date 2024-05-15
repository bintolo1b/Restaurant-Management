package VIEW;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import BLL.BanAnBLL;
import BLL.ChiTietHoaDonBLL;
import BLL.DanhMucBLL;
import BLL.DieuKienVoucherBLL;
import BLL.HoaDonBLL;
import BLL.KhachHangBLL;
import BLL.NhanVienBLL;
import BLL.SanPhamBLL;
import BLL.TaiKhoanBLL;
import DTO.BanAn;
import DTO.ChiTietHoaDon;
import DTO.DanhMuc;
import DTO.DieuKienVoucher;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.NhanVien;
import DTO.SanPham;
import DTO.TaiKhoan;


public class ManagerView extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel currentDisplayPanel;
	private JPanel orderPanel;
	
	public ManagerView() {
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
	
	public void InitHomePanel() {
		currentDisplayPanel.removeAll();
		currentDisplayPanel.setLayout(null);
		currentDisplayPanel.setBounds(230, 70, 966, 730);
		
		JPanel homePanel = new JPanel();
		homePanel.setLayout(null);
		homePanel.setBounds(0, 0, 870, 690);
		homePanel.setBackground(new Color(228,236,228));
		
		ImageIcon icon = new ImageIcon("restaurant_logo.png");
		JLabel logo_lbl = new JLabel();
		logo_lbl.setBounds(331, 13, 400, 400);
		logo_lbl.setIcon(icon);
		
		
		JButton profile_btn = new JButton("Hồ sơ cá nhân");
		JButton changePassword_btn = new JButton("Đổi mật khẩu");
		JButton signOut_btn = new JButton("Đăng xuất");
		
		profile_btn.setBounds(401, 426, 150, 30);
		changePassword_btn.setBounds(401, 480,150,30);
		signOut_btn.setBounds(401, 531, 150, 30);
		
		profile_btn.setBackground(new Color(235, 182, 120));
		changePassword_btn.setBackground(new Color(235, 182, 120));
		signOut_btn.setBackground(new Color(235, 182, 120));
		
		profile_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ProfileView(null);
			}
		});
		
		changePassword_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePasswordView("user0");
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
	
	public void InitMenuPanel() {
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setBounds(0,0,230,761);
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

		JButton staffButton = new JButton("Nhân viên");
		staffButton.setFont(font1);
		staffButton.setForeground(color1);
		staffButton.setBorderPainted(false);
		staffButton.setBackground(color2);
		staffButton.setBounds(35,450,150,50);
		staffButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Init_AccountPanel();
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
		
		JButton invoiceButton = new JButton("Hoá đơn");
		invoiceButton.setFont(font1);
		invoiceButton.setForeground(color1);
		invoiceButton.setBorderPainted(false);
		invoiceButton.setBackground(color2);
		invoiceButton.setBounds(35,550,150,50);
		invoiceButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				InitInvoicePanel();
			}
		});
		
		JButton	revenueButton = new JButton("Doanh thu");
		revenueButton.setFont(font1);
		revenueButton.setForeground(color1);
		revenueButton.setBorderPainted(false);
		revenueButton.setBackground(color2);
		revenueButton.setBounds(35,600,150,50);
		revenueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InitRevenuePanel();
			}
		});
		
		JButton voucherButton = new JButton("Voucher");
		voucherButton.setFont(font1);
		voucherButton.setForeground(color1);
		voucherButton.setBorderPainted(false);
		voucherButton.setBackground(color2);
		voucherButton.setBounds(35, 650, 150, 50);
		voucherButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InitVoucherPanel();
			}
		});
		
		menuPanel.add(logoLabel);
		menuPanel.add(menuLabel);
		menuPanel.add(homeButton);
		menuPanel.add(staffButton);
		menuPanel.add(invoiceButton);
		menuPanel.add(revenueButton);
		menuPanel.add(categoryButton);
		menuPanel.add(voucherButton);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(230,0,966,70);
		headerPanel.setBackground(new Color(228,236,228));
		GridBagLayout gbl_headerPanel = new GridBagLayout();
		gbl_headerPanel.columnWeights = new double[]{1.0};
		headerPanel.setLayout(gbl_headerPanel);
		JLabel headerLabel = new JLabel("Quản lí nhà hàng");
		headerLabel.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 30));
		headerLabel.setForeground(color1);
		headerPanel.add(headerLabel);
		
		getContentPane().add(menuPanel);
		
		JPanel helloPanel = new JPanel();
		helloPanel.setBackground(new Color(235, 182, 120));
		helloPanel.setBounds(10, 25, 210, 50);
		menuPanel.add(helloPanel);
		GridBagLayout gbl_helloPanel = new GridBagLayout();
		gbl_helloPanel.columnWeights = new double[]{1.0};
		helloPanel.setLayout(gbl_helloPanel);
		
		JLabel helloLabel = new JLabel("Xin chào quản lý");
		helloLabel.setForeground(new Color(128, 57, 30));
		helloLabel.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 15));
		helloPanel.add(helloLabel);
		
		JButton banAnButton = new JButton("Bàn ăn");
		banAnButton.setForeground(new Color(128, 57, 30));
		banAnButton.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 15));
		banAnButton.setBorderPainted(false);
		banAnButton.setBackground(new Color(235, 182, 120));
		banAnButton.setBounds(35, 700, 150, 50);
		banAnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Init_table_order_panel();
			}
		});
		menuPanel.add(banAnButton);
		
		getContentPane().add(headerPanel);
	}
	
	public void Init_table_order_panel() {
		currentDisplayPanel.removeAll();
		currentDisplayPanel.setLayout(null);
		currentDisplayPanel.setBounds(230,70,966,691);
				
		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(new Color(228, 236, 228));
		tablePanel.setLayout(new GridLayout(0, 4, 25, 25)); 

		Integer[] lastClickTableButton = {null};
		ArrayList<BanAn> banAnArrayList = BanAnBLL.getInstance().getAllBanAn();
		for (int i = 0; i < banAnArrayList.size(); i++)
				{
				    JButton jButton = new JButton();
				    if (banAnArrayList.get(i).getTrangThai().equals("Có người"))
				        jButton.setBackground(Color.red);
				    else if (banAnArrayList.get(i).getTrangThai().equals("Trống"))
				        jButton.setBackground(Color.green);
				    else if (banAnArrayList.get(i).getTrangThai().equals("Đã ẩn"))
				        jButton.setBackground(Color.yellow);
				    jButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String s = e.getActionCommand();
							String subs ="";
							for (int i = 1 ;i<s.length() ;i++)
								if (s.charAt(i-1)==' ' && s.charAt(i)!=' ')
									subs=s.substring(i,s.length());
							lastClickTableButton[0] = Integer.parseInt(subs);
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
		orderPanel.setBounds(450 ,0, 516, 629);
			
		currentDisplayPanel.add(tableScrollPane);
		currentDisplayPanel.add(notePanel);
		notePanel.setLayout(null);
		
		JLabel greenLabel = new JLabel("");
		greenLabel.setOpaque(true);
		greenLabel.setBackground(new Color(0, 255, 0));
		greenLabel.setBounds(10, 10, 46, 25);
		notePanel.add(greenLabel);
		
		JLabel redLabel = new JLabel("");
		redLabel.setOpaque(true);
		redLabel.setBackground(new Color(255, 0, 0));
		redLabel.setBounds(279, 10, 46, 24);
		notePanel.add(redLabel);
		
		JLabel tableEmptyLabel = new JLabel("Bàn trống");
		tableEmptyLabel.setBounds(66, 21, 72, 14);
		notePanel.add(tableEmptyLabel);
		
		JLabel tableInUseLabel = new JLabel("Bàn đang sử dụng");
		tableInUseLabel.setBounds(335, 21, 115, 14);
		notePanel.add(tableInUseLabel);
		
		JLabel yellowLabel = new JLabel("");
		yellowLabel.setOpaque(true);
		yellowLabel.setBackground(Color.YELLOW);
		yellowLabel.setBounds(148, 10, 46, 25);
		notePanel.add(yellowLabel);
		
		JLabel hideTableLabel = new JLabel("Bàn ẩn");
		hideTableLabel.setBounds(204, 21, 46, 14);
		notePanel.add(hideTableLabel);
		currentDisplayPanel.add(orderPanel);
		
		JButton addTableButton = new JButton("Thêm bàn");
		addTableButton.setBackground(new Color(235, 182, 129));
		addTableButton.setBounds(532, 646, 101, 23);
		addTableButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DinningTableUpdate_InsertView(0, ManagerView.this);
			}
		});
		currentDisplayPanel.add(addTableButton);
		
		JButton editTableButton = new JButton("Sửa bàn");
		editTableButton.setBackground(new Color(235, 182, 129));
		editTableButton.setBounds(760, 646, 89, 23);
		editTableButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lastClickTableButton[0]!=null) {
					BanAn banAn = BanAnBLL.getInstance().getBan(lastClickTableButton[0]);
					if(banAn.getTrangThai().equals("Có người")) {
						JOptionPane.showMessageDialog(null, "Bàn đang có người, không thể sửa bàn!");
					}
					else {
						new DinningTableUpdate_InsertView(lastClickTableButton[0], ManagerView.this);
					}
				}
			}
		});
		currentDisplayPanel.add(editTableButton);
		
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
			
			JLabel emptyLabel = new JLabel("Bàn trống");
			emptyLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			emptyLabel.setBackground(new Color(235,182,120));
			emptyLabel.setBounds(225, 318, 100, 50);	
			this.orderPanel.add(tableIDLabel);
			this.orderPanel.add(emptyLabel);
		}
		else if (currentBanAn.getTrangThai().endsWith("Đã ẩn")) {	
				JLabel tableIDLabel = new JLabel("Bàn " + tableID);
				tableIDLabel.setFont(new Font("Arial", Font.BOLD, 15));
				tableIDLabel.setForeground(Color.red);
				tableIDLabel.setBounds(20, 0, 100, 50);
				
				JLabel emptyLabel = new JLabel("Đã ẩn bàn");
				emptyLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
				emptyLabel.setBackground(new Color(235,182,120));
				emptyLabel.setBounds(225, 318, 100, 50);	
				this.orderPanel.add(tableIDLabel);
				this.orderPanel.add(emptyLabel);
		}
		else 
		{	
			HoaDon currentHoaDon = HoaDonBLL.getInstance().getLatestHoaDonOfTable(tableID);
			KhachHang currentKhachHang = KhachHangBLL.getInstance().getKhachHangByMaKH(currentHoaDon.getMaKhach());
			ArrayList<ChiTietHoaDon> cthdOfCurrentHoaDonArrayList = ChiTietHoaDonBLL.getInstance().getAllChiTietHoaDonOfHoaDon(currentHoaDon.getMaHoaDon());
			
			JLabel tableIDLabel = new JLabel("Bàn " + tableID);
			tableIDLabel.setFont(new Font("Arial", Font.BOLD, 15));
			tableIDLabel.setForeground(Color.red);
			tableIDLabel.setBounds(20, 0, 100, 50);
			
			
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("Tên món");
			model.addColumn("Số lượng");
			model.addColumn("Đơn giá");
			model.addColumn("Thành tiền");
			model.addColumn("Lần gọi");
			model.addColumn("Tình trạng");
			
			ChiTietHoaDonBLL.getInstance().exportChiTietHoaDonIntoServedModelTable(model, cthdOfCurrentHoaDonArrayList);
			
			
			JTable orderTable = new JTable(model);
			orderTable.setDefaultEditor(Object.class, null);
			orderTable.getTableHeader().setReorderingAllowed(false);
			orderTable.setBackground(new Color(238,238,228)); 
			orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			
			DefaultTableModel waitingProductModel = new DefaultTableModel();
			waitingProductModel.addColumn("Tên món");
			waitingProductModel.addColumn("Số lượng");
			waitingProductModel.addColumn("Đơn giá");
			waitingProductModel.addColumn("Thành tiền");
			waitingProductModel.addColumn("Lần gọi");
			waitingProductModel.addColumn("Tình trạng");
			
			ChiTietHoaDonBLL.getInstance().exportChiTietHoaDonIntoWaitingModelTable(waitingProductModel, cthdOfCurrentHoaDonArrayList);
			
			JScrollPane waitingProductScrollPane = new JScrollPane();
			waitingProductScrollPane.setBounds(12, 424, 492, 146);
			orderPanel.add(waitingProductScrollPane);
			
			JTable waitingProductJTable = new JTable(waitingProductModel);
			waitingProductJTable.setDefaultEditor(Object.class, null);
			waitingProductJTable.getTableHeader().setReorderingAllowed(false);
			waitingProductJTable.setBackground(new Color(238,238,228)); 		
			waitingProductScrollPane.setViewportView(waitingProductJTable);
			
			JLabel HaventServedProductLabel = new JLabel("Danh sách món chưa phục vụ");
			HaventServedProductLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			HaventServedProductLabel.setBounds(165, 397, 233, 19);
			orderPanel.add(HaventServedProductLabel);


			
			JScrollPane jScrollPane = new JScrollPane(orderTable);
			jScrollPane.setBounds(12, 191, 492, 146);
			
			float[] amountFinal = {currentHoaDon.getTongTien()};		
			
	        Locale locale = new Locale.Builder().setLanguage("vi").setRegion("VN").build();
	        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
	        String formattedAmount = currencyFormat.format(amountFinal[0]);
			JTextField totalTextField = new JTextField(formattedAmount);
			totalTextField.setForeground(Color.red);
			totalTextField.setEditable(false);
			totalTextField.setHorizontalAlignment(SwingConstants.CENTER);
			totalTextField.setBounds(404, 350, 100, 30);
	
			JLabel totalLabel = new JLabel("Tổng tiền: ");
			totalLabel.setFont(new Font("Constantia", Font.BOLD, 15));
			totalLabel.setBounds(20, 350, 77, 30);
				
			JLabel khachHangLabel = new JLabel("Khách hàng:");
			khachHangLabel.setFont(new Font("Constantia", Font.BOLD, 15));
			khachHangLabel.setBounds(20, 61, 100, 30);
			orderPanel.add(khachHangLabel);
			
			JLabel khachHang_ValueLabel = new JLabel(currentKhachHang==null?"Khách vãng lai":currentKhachHang.getHo()+" "+currentKhachHang.getTenDem()+" "+currentKhachHang.getTen());
			khachHang_ValueLabel.setFont(new Font("Constantia", Font.BOLD, 15));
			khachHang_ValueLabel.setBounds(148, 67, 202, 19);
			orderPanel.add(khachHang_ValueLabel);
			
			JLabel sdtLabel = new JLabel("Số điện thoại:");
			sdtLabel.setFont(new Font("Constantia", Font.BOLD, 15));
			sdtLabel.setBounds(20, 117, 112, 16);
			orderPanel.add(sdtLabel);
			
			JLabel sdt_ValueLabel = new JLabel(currentKhachHang==null?"":currentKhachHang.getSoDienThoai());
			sdt_ValueLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			sdt_ValueLabel.setBounds(148, 114, 112, 16);
			orderPanel.add(sdt_ValueLabel);
			
			JLabel productListLabel = new JLabel("Danh sách món đã phục vụ");
			productListLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			productListLabel.setBounds(172, 166, 214, 19);
			orderPanel.add(productListLabel);
			
			this.orderPanel.add(tableIDLabel);
			this.orderPanel.add(jScrollPane);
			this.orderPanel.add(totalLabel);
			this.orderPanel.add(totalTextField);
			
		}
		
		this.orderPanel.validate(); 
		this.orderPanel.repaint();
	}
	
	
	public void InitCategoryPanel(){
		currentDisplayPanel.removeAll();
		currentDisplayPanel.setLayout(null);
		currentDisplayPanel.setBounds(230, 70, 966, 691);
		
		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(null);
		categoryPanel.setBounds(0, 0, 441, 689);
		categoryPanel.setBackground(new Color(228,236,228));
		
		DefaultTableModel categoryModel = new DefaultTableModel();
		categoryModel.addColumn("Mã danh mục");
		categoryModel.addColumn("Tên danh mục");
		categoryModel.addColumn("Trạng thái");
		for(DanhMuc i: DanhMucBLL.getInstance().getAllDanhMuc()) {
			categoryModel.addRow(new Object[] {i.getMaDanhMuc(),i.getTenDanhMuc(),i.getTrangThai()});
		}
		
		JTable categoryTable = new JTable(categoryModel);
		categoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);;
		categoryTable.setBackground(new Color(238,238,228)); 
		JScrollPane categoryScrollPane = new JScrollPane(categoryTable);
		categoryScrollPane.setBounds(50, 44, 350, 491);
		
		categoryTable.getTableHeader().setReorderingAllowed(false); 
		
		DefaultTableModel categoryModel1 = (DefaultTableModel) categoryTable.getModel();
		for (int i = 0; i < categoryModel1.getColumnCount(); i++) {
		    categoryTable.setDefaultEditor(categoryModel1.getColumnClass(i), null);
		}
		
		
		JButton addCategoryButton = new JButton("Thêm danh mục");
		addCategoryButton.setBackground(new Color(235, 182, 120));
		JButton updateCategoryButton = new JButton("Sửa danh mục");
		updateCategoryButton.setBackground(new Color(235, 182, 120));
		
		addCategoryButton.setBounds(50, 570, 140, 30);
		updateCategoryButton.setBounds(260,570,140,30);
		
		JPanel productPanel = new JPanel();
		productPanel.setLayout(null);
		productPanel.setBounds(442, 0, 524, 691);
		productPanel.setBackground(new Color(228, 236, 228));
		
		DefaultTableModel productModel = new DefaultTableModel();
		productModel.addColumn("Mã sản phẩm");
		productModel.addColumn("Tên");
		productModel.addColumn("Giá");
		productModel.addColumn("Trạng thái");
		productModel.addColumn("Tên danh mục");
		
		JTable productTable = new JTable(productModel);
		productTable.setBackground(new Color(238,238,228)); 
		productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane productScrollPane = new JScrollPane(productTable);
		productScrollPane.setBounds(23, 46, 476, 491);
	
		productTable.getTableHeader().setReorderingAllowed(false); 

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
		                Object categoryObject = categoryTable.getValueAt(selectedRow, 0);
		                try {
		                    categoryID[0] = Integer.parseInt(categoryObject.toString());
		                    ArrayList<SanPham> sanPhamArrayList = SanPhamBLL.getInstance().getAllSanPhamInDanhMuc(categoryID[0]);
		                    productModel1.setRowCount(0); 
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
		
		int[] productID = {0};
		
		productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            int selectedRow = productTable.getSelectedRow();
		            if (selectedRow != -1) {
		                Object productObject = productTable.getValueAt(selectedRow, 0); 
		                try {
		                    productID[0] = Integer.parseInt(productObject.toString());
		                } catch (NumberFormatException ex) {
		                    ex.printStackTrace();
		                }
		            }
		        }
		    }
		});
		
		addCategoryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CategoryUpdate_InsertView(ManagerView.this, ManagerView.this, 0);
			}
		});
		
		updateCategoryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(categoryTable.getSelectedRow()>0) {
					new CategoryUpdate_InsertView(ManagerView.this, ManagerView.this ,categoryID[0]);
				}
			}
		});
		
		
		JButton addProductButton = new JButton("Thêm sản phẩm");
		addProductButton.setBackground(new Color(235, 182, 120));
		JButton updateProductButton = new JButton("Sửa sản phẩm");
		updateProductButton.setBackground(new Color(235, 182, 120));
		
		addProductButton.setBounds(52, 574, 150, 30);
		updateProductButton.setBounds(293,574,150,30);
		
		addProductButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(categoryTable.getSelectedRow()>0) {
				new ProductUpdate_InsertView(ManagerView.this ,0,categoryID[0]);
				}
			}
		});
		
		updateProductButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (productTable.getSelectedRow() >= 0) { 
		            new ProductUpdate_InsertView(ManagerView.this ,productID[0], categoryID[0]);
		        }
		    }
		});

		
		categoryPanel.add(categoryScrollPane);
		categoryPanel.add(addCategoryButton);
		categoryPanel.add(updateCategoryButton);
		
		productPanel.add(productScrollPane);
		productPanel.add(addProductButton);
		productPanel.add(updateProductButton);
		
		currentDisplayPanel.add(categoryPanel);
		
		JLabel catagoryLabel = new JLabel("Danh mục");
		catagoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		catagoryLabel.setBounds(184, 13, 96, 20);
		categoryPanel.add(catagoryLabel);
		currentDisplayPanel.add(productPanel);
		
		JLabel productLabel = new JLabel("Sản phẩm");
		productLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productLabel.setBounds(239, 13, 84, 20);
		productPanel.add(productLabel);
		currentDisplayPanel.validate();
		currentDisplayPanel.repaint();
	}
	
	private ChartPanel GenerateChart() {
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createBarChart(
            "Biểu Đồ Doanh Thu",
            "Ngày",
            "Doanh thu (VND)",
            data,
            PlotOrientation.VERTICAL,
            false, true, false);
        HoaDonBLL.getInstance().updateRevenue(data);
        
        return new ChartPanel(chart);
    }
 
 public void UpdateChartFollowingDates(java.util.Date startDate, java.util.Date endDate) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        HoaDonBLL.getInstance().updateRevenueFollowingDates(dataset, startDate, endDate);
        UpdateChart(dataset);
    }

 public void UpdateChart(DefaultCategoryDataset dataset) {
	    JFreeChart chart = ChartFactory.createBarChart(
	        "Biểu Đồ Doanh Thu", "Ngày", "Doanh thu (VND)", dataset,
	        PlotOrientation.VERTICAL, false, true, false);
	    
	    ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setChart(chart);
	    
	    // Thêm biểu đồ vào panel hiển thị
	    currentDisplayPanel.removeAll(); // Xóa các thành phần hiện có trên panel
	    currentDisplayPanel.add(chartPanel, BorderLayout.CENTER); // Thêm biểu đồ mới vào panel
	    currentDisplayPanel.add(SouthElementPanel(),BorderLayout.SOUTH);
	    
	    // Cập nhật lại panel hiển thị
	    currentDisplayPanel.validate();
	    currentDisplayPanel.repaint();
	}

 
 public JPanel SouthElementPanel() {
	 	JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel startDate_lbl = new JLabel("Ngày bắt đầu");
		startDate_lbl.setSize(120,30);
		southPanel.add(startDate_lbl);
		
		JDateChooser startDateChooser = new JDateChooser();
		startDateChooser.setForeground(new Color(0, 0, 0));
		startDateChooser.setDateFormatString("yyyy-MM-dd");
		startDateChooser.setSize(120,30);
		southPanel.add(startDateChooser);
        
        JLabel endDate_lbl = new JLabel("Ngày kết thúc");
        endDate_lbl.setSize(120,30);
        southPanel.add(endDate_lbl);
        
        JDateChooser endDateChooser = new JDateChooser();
        endDateChooser.setForeground(new Color(0, 0, 0));
        endDateChooser.setDateFormatString("yyyy-MM-dd");
        endDateChooser.setSize(120,30);
        southPanel.add(endDateChooser);
        
        JButton filter_btn = new JButton("Lọc");
        filter_btn.setSize(80,30);
        filter_btn.addActionListener(new ActionListener() {
			
        	@Override
        	public void actionPerformed(ActionEvent e) {
        	    java.util.Date startDateUtil = startDateChooser.getDate();
        	    java.util.Date endDateUtil = endDateChooser.getDate();
        	    if(startDateUtil != null && endDateUtil != null) {
        	    	 java.util.Date startDate = new java.sql.Date(startDateUtil.getTime());
	        	        java.util.Date endDate = new java.sql.Date(endDateUtil.getTime());
	        	        UpdateChartFollowingDates(startDate, endDate);
        	        
        	    }
        	    else {
        	    	JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ cả ngày bắt đầu và ngày kết thúc");
        	    }
        	}
		});
        southPanel.add(filter_btn);
        
        return southPanel;
 }

public void InitRevenuePanel() {
	currentDisplayPanel.removeAll();
	currentDisplayPanel.setLayout(new BorderLayout());
	currentDisplayPanel.setBounds(230, 70, 966, 691);
	
	currentDisplayPanel.add(GenerateChart(),BorderLayout.CENTER);
	currentDisplayPanel.add(SouthElementPanel(),BorderLayout.SOUTH);

	currentDisplayPanel.validate();
	currentDisplayPanel.repaint();
}

	
	public void InitVoucherPanel() {
		currentDisplayPanel.removeAll();
		currentDisplayPanel.setLayout(null);
		currentDisplayPanel.setBounds(230, 70, 966, 691);
		
		JTable dkVoucherTable;
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(108, 72, 774, 403);
		currentDisplayPanel.add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Mã điều kiện");
		model.addColumn("Điểm yêu cầu");
		model.addColumn("Phần trăm giảm");
		model.addColumn("Tình trạng");
		
		ArrayList<DieuKienVoucher> dkvcArrayList = DieuKienVoucherBLL.getInstance().getAllDKVC();
		for (int i = 0 ; i < dkvcArrayList.size() ; i++) {
			Object[] row = new Object[] {dkvcArrayList.get(i).getMaDieuKien(), dkvcArrayList.get(i).getDiemYeuCau(), dkvcArrayList.get(i).getPhanTram(), dkvcArrayList.get(i).getTinhTrang()};
			model.addRow(row);
		}
	
		dkVoucherTable = new JTable(model);
		dkVoucherTable.setBackground(new Color(238, 238, 228));
		dkVoucherTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dkVoucherTable.setDefaultEditor(Object.class, null);
		dkVoucherTable.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(dkVoucherTable);
		
		JButton addButton = new JButton("Thêm điều kiện");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DieuKienVoucherUpdate_InsertView(ManagerView.this, ManagerView.this, null);
			}
		});
		addButton.setBackground(new Color(235, 182, 120));
		addButton.setBounds(259, 551, 137, 23);
		currentDisplayPanel.add(addButton);
		
		JButton editButton = new JButton("Sửa điều kiện");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dkVoucherTable.getSelectedRowCount()==1) {
					int dkvcID = (int)model.getValueAt(dkVoucherTable.getSelectedRow(), 0);
					new DieuKienVoucherUpdate_InsertView(ManagerView.this, ManagerView.this, dkvcID);
				}
			}
		});
		editButton.setBackground(new Color(235, 182, 120));
		editButton.setBounds(537, 551, 117, 23);
		currentDisplayPanel.add(editButton);
		
		JLabel dkvcLabel = new JLabel("Điều kiện voucher");
		dkvcLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		dkvcLabel.setBounds(414, 36, 160, 23);
		currentDisplayPanel.add(dkvcLabel);
		
		currentDisplayPanel.validate();
		currentDisplayPanel.repaint();
	}
	
	public void InitInvoicePanel() {
		currentDisplayPanel.removeAll();
		currentDisplayPanel.setLayout(null);
		currentDisplayPanel.setBounds(230, 70, 966, 691);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 107, 754, 438);
		currentDisplayPanel.add(scrollPane);
		
		JTable hoaDonTable = new JTable();
		scrollPane.setViewportView(hoaDonTable);
		hoaDonTable.setBackground(new Color(238, 238, 228));
		hoaDonTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hoaDonTable.setDefaultEditor(Object.class, null);
		hoaDonTable.getTableHeader().setReorderingAllowed(false);
		
		JLabel hoaDonLabel = new JLabel("Hóa đơn");
		hoaDonLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		hoaDonLabel.setBounds(440, 66, 112, 28);
		currentDisplayPanel.add(hoaDonLabel);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Mã hóa đơn");
		model.addColumn("Ngày tạo");
		model.addColumn("Tổng tiền");
		model.addColumn("Mã nhân viên");
		model.addColumn("Mã khách");
		model.addColumn("Mã bàn");
		
		hoaDonTable.setModel(model);
		
		ArrayList<HoaDon> hoaDonArrayList = HoaDonBLL.getInstance().getAllHoaDonDaThanhToan();
		for (int i = 0 ; i < hoaDonArrayList.size() ; i++) {
			HoaDon hoaDon = hoaDonArrayList.get(i);
			NumberFormat vietnameseFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("vi-VN"));
			String formattedAmount = vietnameseFormat.format(hoaDon.getTongTien());
			Object[] row = new Object[] {hoaDon.getMaHoaDon(), hoaDon.getNgayTao(), formattedAmount, hoaDon.getMaNhanVien(), hoaDon.getMaKhach(), hoaDon.getMaBan()};
			model.addRow(row);
		}
		
		hoaDonTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int hoaDonID = (int)model.getValueAt(hoaDonTable.getSelectedRow(), 0);
                    new PaymentView(ManagerView.this, hoaDonID, null, true);
                }
            }
        });
		
		currentDisplayPanel.validate();
		currentDisplayPanel.repaint();
	}
	
	private JPanel EditPanel, MainPanel;
	private DefaultTableModel model;
	private CardLayout card;
	
	public void Init_AccountPanel() {
		currentDisplayPanel.removeAll();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 79, 966, 691);

		String[] s = new String[] {
		"Mã nhân viên", "Tên", "Số điện thoại", "Địa chỉ", "Tài khoản", "Trạng thái"
		};
		model = new DefaultTableModel(s,0) {
			private static final long serialVersionUID = 1L;
			@Override
	        public boolean isCellEditable(int row, int column) {
	            return false; 
	        }
        };

        UpdateTable();
		JTable table = new JTable(model);
		card = new CardLayout();
		currentDisplayPanel.setLayout(card);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 966, 81);
		panel.setBackground(new Color(228,236,228));
		panel.setLayout(null);
		
		
		MainPanel = new JPanel();
		currentDisplayPanel.add(MainPanel, "MainPanel");
		MainPanel.setBackground(new Color(238, 238, 228));
		MainPanel.setLayout(null);
		
		EditPanel = new JPanel();
		currentDisplayPanel.add(EditPanel, "EditPanel");
		EditPanel.setBackground(new Color(238, 238, 228));
		EditPanel.setLayout(null);
		
		JTextField txtTimKiem = new JTextField();

		txtTimKiem.setText("Tìm kiếm nhân viên");
		txtTimKiem.setForeground(Color.gray);

		txtTimKiem.setBounds(22, 41, 203, 19);
		panel.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JButton btnThemNV = new JButton("Thêm nhân viên");
		
		btnThemNV.setBounds(800, 40, 129, 21);
		btnThemNV.setBackground(new Color(235,182,120));
		panel.add(btnThemNV);
		
		MainPanel.add(scrollPane);
		MainPanel.add(panel);
		
		scrollPane.setViewportView(table);
		card.show(currentDisplayPanel, "MainPanel");
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					int id = (int)model.getValueAt(table.getSelectedRow(),0);					
					Edit_panel(id);
					card.show(currentDisplayPanel, "EditPanel");
				}
			}
		});
		txtTimKiem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtTimKiem.getText().equals("Tìm kiếm nhân viên")) {
					txtTimKiem.setText("");
					txtTimKiem.setForeground(Color.black);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtTimKiem.getText().isEmpty()) {
					txtTimKiem.setText("Tìm kiếm nhân viên");
					txtTimKiem.setForeground(Color.gray);
				}
			}
		});
		txtTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateTable(txtTimKiem.getText().toLowerCase());
			}
		});
		btnThemNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Edit_panel(-1);
				card.show(currentDisplayPanel, "EditPanel");				
			}
		});
	}
	
	public void UpdateTable() {
		model.setRowCount(0);
		ArrayList<NhanVien> arrayList = NhanVienBLL.getInstance().getAllNhanVien();
		for(NhanVien nv : arrayList){
			String ten = nv.getHo()+ " " + nv.getTenDem() + " " + nv.getTen();
			Object[] r = new Object[]{nv.getMaNhanVien(), ten, nv.getSoDienThoai(), nv.getDiaChi(), nv.getTaiKhoan(), nv.getTrangThai()};
			model.addRow(r);
		}
	}
	
	public void UpdateTable(String searchString) {
		model.setRowCount(0);
		ArrayList<NhanVien> arrayList = NhanVienBLL.getInstance().getAllNhanVien();
		for(NhanVien nv : arrayList){
			String ten = nv.getHo()+ " " + nv.getTenDem() + " " + nv.getTen();
			if(ten.toLowerCase().contains(searchString)) {
				Object[] r = new Object[]{nv.getMaNhanVien(), ten, nv.getSoDienThoai(), nv.getDiaChi(), nv.getTaiKhoan(), nv.getTrangThai()};
				model.addRow(r);				
			}
		}
	}
	
	public void Edit_panel(int Id) {
		MainPanel.setVisible(false);
		EditPanel.removeAll();
		JLabel lblNewLabel_10 = new JLabel("Tên đệm");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_10.setBounds(399, 204, 61, 13);
		EditPanel.add(lblNewLabel_10);
		
		JTextField tendem = new JTextField();
		tendem.setBounds(399, 227, 147, 37);
		tendem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		EditPanel.add(tendem);
		tendem.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Tên");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_11.setBounds(59, 290, 45, 13);
		EditPanel.add(lblNewLabel_11);
		
		JTextField ten = new JTextField();
		ten.setBounds(59, 325, 147, 37);
		ten.setFont(new Font("Tahoma", Font.PLAIN, 14));
		EditPanel.add(ten);
		ten.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Thông tin cá nhân");
		lblNewLabel.setBounds(21, 35, 174, 48);
		EditPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên");
		lblNewLabel_1.setBounds(59, 100, 91, 27);
		EditPanel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JTextField manv = new JTextField();
		manv.setBounds(59, 137, 147, 37);
		EditPanel.add(manv);
		manv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		manv.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Họ");
		lblNewLabel_2.setBounds(59, 190, 61, 25);
		EditPanel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JTextField ho = new JTextField();
		ho.setBounds(59, 225, 147, 37);
		EditPanel.add(ho);
		ho.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ho.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Địa chỉ");
		lblNewLabel_3.setBounds(59, 389, 45, 13);
		EditPanel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JTextField diachi = new JTextField();
		diachi.setBounds(59, 412, 239, 37);
		EditPanel.add(diachi);
		diachi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		diachi.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Thông tin tài khoản");
		lblNewLabel_7.setBounds(21, 495, 143, 40);
		EditPanel.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_8 = new JLabel("Tài khoản");
		lblNewLabel_8.setBounds(60, 564, 120, 25);
		EditPanel.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JTextField taikhoan = new JTextField();
		taikhoan.setBounds(60, 599, 147, 37);
		EditPanel.add(taikhoan);
		taikhoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		taikhoan.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Mật khẩu");
		lblNewLabel_9.setBounds(399, 568, 85, 20);
		EditPanel.add(lblNewLabel_9);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JTextField mk = new JTextField();
		mk.setBounds(399, 599, 147, 37);
		EditPanel.add(mk);
		mk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mk.setColumns(10);
		
		JDateChooser ns = new JDateChooser();
		ns.setBounds(399, 412, 147, 37);
		EditPanel.add(ns);
		ns.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_5 = new JLabel("Ngày sinh");
		lblNewLabel_5.setBounds(398, 387, 91, 17);
		EditPanel.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));

		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rdNam = new JRadioButton("Nam");
		rdNam.setBounds(399, 328, 73, 29);
		EditPanel.add(rdNam);
		rdNam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonGroup.add(rdNam);
		
		JRadioButton rdNu = new JRadioButton("Nữ");
		rdNu.setBounds(474, 328, 72, 29);
		EditPanel.add(rdNu);
		rdNu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonGroup.add(rdNu);
		
		JLabel lblNewLabel_6 = new JLabel("Giới tính");
		lblNewLabel_6.setBounds(399, 290, 72, 13);
		EditPanel.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JTextField sdt = new JTextField();
		sdt.setBounds(398, 137, 147, 37);
		EditPanel.add(sdt);
		sdt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sdt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Số điện thoại");
		lblNewLabel_4.setBounds(398, 108, 91, 19);
		EditPanel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNewLable_TT = new JLabel("Trạng thái");
		lblNewLable_TT.setBounds(600, 108, 91, 19);
		EditPanel.add(lblNewLable_TT);
		lblNewLable_TT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		//new
		ButtonGroup btg = new ButtonGroup();
		JRadioButton rdThoiviec = new JRadioButton("Thôi việc");
		rdThoiviec.setBounds(720, 140, 120, 29);
		EditPanel.add(rdThoiviec);
		rdThoiviec.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btg.add(rdThoiviec);
		
		JRadioButton rdDanglam = new JRadioButton("Đang làm");
		rdDanglam.setBounds(600, 140, 120, 29);
		EditPanel.add(rdDanglam);
		rdDanglam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btg.add(rdDanglam);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.setBounds(850, 20, 85, 25);
		btnLuu.setBackground(new Color(235,182,120));
		EditPanel.add(btnLuu);
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Color color1 = new Color(228,236,228);
		JButton btnQuayLai = new JButton("< Quay lại");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateTable();
				card.show(currentDisplayPanel, "MainPanel");
			}
		});
		btnQuayLai.setHorizontalAlignment(SwingConstants.LEADING);
		btnQuayLai.setBounds(0, 0, 104, 37);
		btnQuayLai.setBackground(color1);
		btnQuayLai.setBorderPainted(false); 
		btnQuayLai.setContentAreaFilled(false); 
		EditPanel.add(btnQuayLai);
		
		btnQuayLai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(
						ho.getText().length() == 0 ||
						tendem.getText().length() < 0 ||
						ten.getText().length() == 0 ||
						sdt.getText().length() == 0 ||
						taikhoan.getText().length() == 0 ||
						mk.getText().length() == 0
						)
						JOptionPane.showMessageDialog(null, "Không được để trống!");
					else if(sdt.getText().length() != 10) 
						throw new Exception("Số điện thoại không hợp lệ!");
					else if(TaiKhoanBLL.getInstance().getTaiKhoanByUserName(taikhoan.getText()) != null) {
						JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại!");
					}
					else {
						java.sql.Date Ns = new java.sql.Date(ns.getDate().getTime());
						int gioitinh = rdNam.isSelected() ? 0 : 1;
						String trangthai = rdDanglam.isSelected() ? "Còn làm việc" : "Thôi việc";
						TaiKhoan tk = new TaiKhoan(taikhoan.getText(), mk.getText(), "Nhân viên");
						NhanVien nv = new NhanVien(Integer.parseInt(manv.getText()), ho.getText() ,tendem.getText() , ten.getText() , Ns.toString(), sdt.getText(), diachi.getText(), gioitinh, taikhoan.getText(), trangthai);
						if(NhanVienBLL.getInstance().getNhanVienByID(Integer.parseInt(manv.getText()))== null) {
							TaiKhoanBLL.getInstance().AddNewTaiKhoan(tk);
							NhanVienBLL.getInstance().AddNewNhanVien(nv);						
						}	
						else {
							TaiKhoanBLL.getInstance().updateTaiKhoan(tk);
							NhanVienBLL.getInstance().UpdateNhanVien(nv);			
						}
						JOptionPane.showMessageDialog(null, "Lưu thành công!");						
					}

				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null,ex.getMessage());
				}
			}
		});
		if(Id>0) {
			NhanVien nv = NhanVienBLL.getInstance().getNhanVienByID(Id);
			manv.setText(Integer.toString(nv.getMaNhanVien()));
			ho.setText(nv.getHo());
			tendem.setText(nv.getTenDem());
			ten.setText(nv.getTen());
			ns.setDate(Date.valueOf(nv.getNgaySinh())); 
			sdt.setText(nv.getSoDienThoai());
			diachi.setText(nv.getDiaChi());				
			taikhoan.setText(nv.getTaiKhoan());
			TaiKhoan tk = TaiKhoanBLL.getInstance().getTaiKhoanByUserName(nv.getTaiKhoan());
			mk.setText(tk.getMatKhau());
			if(nv.getGioiTinh() == 0)
				rdNam.setSelected(true);
			else if(nv.getGioiTinh() == 1) rdNu.setSelected(true);
			if(nv.getTrangThai().equals("Còn làm việc"))
				rdDanglam.setSelected(true);
			else if(nv.getTrangThai().equals("Thôi việc"))
				rdThoiviec.setSelected(true);
			taikhoan.setEnabled(false);			
		}
		else manv.setText(Integer.toString(NhanVienBLL.getInstance().GenarateID()));
		manv.setEnabled(false);
	}
	
	public static void main(String[] args) {
		new ManagerView();
	}
}
