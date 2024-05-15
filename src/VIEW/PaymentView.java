package VIEW;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import BLL.BanAnBLL;
import BLL.ChiTietHoaDonBLL;
import BLL.DieuKienVoucherBLL;
import BLL.HoaDonBLL;
import BLL.KhachHangBLL;
import BLL.NhanVienBLL;
import BLL.SuDungVoucherBLL;
import BLL.VoucherBLL;
import DTO.BanAn;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.NhanVien;
import DTO.SuDungVoucher;
import DTO.Voucher;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class PaymentView extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private HoaDon currentHoaDon;
	private KhachHang currentKhachHang;
	private NhanVien currentNhanVien;
	private BanAn currentBanAn;
	private HomeView homeView;
	private boolean readOnly;
	
	public PaymentView(JFrame owner, int maHD, HomeView homeView, boolean readOnly) {
		super(owner, "Hóa đơn", true);
		getContentPane().setBackground(new Color(228, 236, 228));
		this.homeView = homeView;
		this.readOnly = readOnly;
		this.currentHoaDon = HoaDonBLL.getInstance().getHoaDonByID(maHD);
		if (this.currentHoaDon!=null) {
			if (currentHoaDon.getMaKhach()!=0) {
				currentKhachHang = KhachHangBLL.getInstance().getKhachHangByMaKH(currentHoaDon.getMaKhach());
			}
			else 
				currentKhachHang=null;
			currentNhanVien = NhanVienBLL.getInstance().getNhanVienByID(currentHoaDon.getMaNhanVien());
			currentBanAn = BanAnBLL.getInstance().getBan(currentHoaDon.getMaBan());
			initialize();
		}	
	}
	private void initialize() {
		this.setBounds(100, 100, 512, 646);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		
		JLabel tenNhaHangLabel = new JLabel("CNTT 22T_DT5");
		tenNhaHangLabel.setFont(new Font("Serif", Font.BOLD, 16));
		tenNhaHangLabel.setBounds(177, 3, 132, 22);
		this.getContentPane().add(tenNhaHangLabel);
		
		JLabel headerLabel = new JLabel("HÓA ĐƠN BÁN HÀNG");
		headerLabel.setFont(new Font("Serif", Font.BOLD, 13));
		headerLabel.setBounds(177, 69, 132, 14);
		this.getContentPane().add(headerLabel);
		
		JLabel diaChiLabel = new JLabel("ĐC: 54 Nguyễn Lương Bằng, Hòa Khánh, Liên Chiểu, Đà Nẵng");
		diaChiLabel.setBounds(91, 36, 385, 14);
		this.getContentPane().add(diaChiLabel);
		
		JLabel hoTenKhachLabel = new JLabel("Họ tên khách:");
		hoTenKhachLabel.setBounds(32, 138, 80, 14);
		this.getContentPane().add(hoTenKhachLabel);
		
		JLabel hoTenKhachValueLabel = new JLabel();
		if (currentKhachHang!=null) {
			hoTenKhachValueLabel.setText(currentKhachHang.getHo()+" "+currentKhachHang.getTenDem()+" "+currentKhachHang.getTen()); 
		}
		else
			hoTenKhachValueLabel.setText("Khách vãng lai");
		hoTenKhachValueLabel.setBounds(131, 138, 196, 14);
		this.getContentPane().add(hoTenKhachValueLabel);
		
		JLabel dienThoaiLabel = new JLabel("Điện thoại:");
		dienThoaiLabel.setBounds(337, 138, 74, 14);
		this.getContentPane().add(dienThoaiLabel);
		
		JLabel maNhanVienLabel = new JLabel("Mã nhân viên:");
		maNhanVienLabel.setBounds(32, 163, 80, 14);
		this.getContentPane().add(maNhanVienLabel);
		
		JLabel maNhanVienValueLabel = new JLabel(currentNhanVien.getMaNhanVien()+"");
		maNhanVienValueLabel.setBounds(141, 163, 30, 14);
		this.getContentPane().add(maNhanVienValueLabel);
		
		JLabel dienThoaiValueLabel = new JLabel(""); 
		if (currentKhachHang!=null) {
			dienThoaiValueLabel.setText(currentKhachHang.getSoDienThoai());
		}

		dienThoaiValueLabel.setBounds(406, 138, 70, 14);
		this.getContentPane().add(dienThoaiValueLabel);
		
		JLabel maHoaDonLabel = new JLabel("Mã hóa đơn:");
		maHoaDonLabel.setBounds(32, 113, 80, 14);
		this.getContentPane().add(maHoaDonLabel);
		
		JLabel maHoaDonValueLabel = new JLabel(currentHoaDon.getMaHoaDon()+"");
		maHoaDonValueLabel.setBounds(109, 113, 52, 14);
		this.getContentPane().add(maHoaDonValueLabel);
		
		JLabel ngayLabel = new JLabel("Ngày giờ:");
		ngayLabel.setBounds(31, 213, 59, 16);
		this.getContentPane().add(ngayLabel);
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDateTime = currentDateTime.format(formatter);
		JLabel ngayValueLabel = new JLabel(formattedDateTime);
		ngayValueLabel.setBounds(109, 213, 132, 14);
		this.getContentPane().add(ngayValueLabel);
		
		JLabel banLabel = new JLabel("Bàn:");
		banLabel.setBounds(337, 163, 38, 14);
		this.getContentPane().add(banLabel);
		
		JLabel banValueLabel = new JLabel(currentBanAn.getMaBan()+"");
		banValueLabel.setBounds(385, 163, 70, 14);
		this.getContentPane().add(banValueLabel);
		
		JLabel tinhTrangLabel = new JLabel("Tình trạng:");
		tinhTrangLabel.setBounds(32, 188, 69, 14);
		this.getContentPane().add(tinhTrangLabel);
		
		JLabel tinhTrangValueLabel = new JLabel(currentHoaDon.getTinhTrang());
		tinhTrangValueLabel.setBounds(109, 188, 178, 14);
		this.getContentPane().add(tinhTrangValueLabel);
			
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên món");
		model.addColumn("Số lượng");
		model.addColumn("Đơn giá");
		model.addColumn("Thành tiền");
		

		ChiTietHoaDonBLL.getInstance().exportChiTietHoaDonIntoModelTableWithMergeSP(model, currentHoaDon.getMaHoaDon());
		
		JTable orderTable = new JTable(model);
		orderTable.setDefaultEditor(Object.class, null);
		orderTable.getTableHeader().setReorderingAllowed(false);
		orderTable.setBackground(new Color(238,238,228)); 
		orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane jScrollPane = new JScrollPane(orderTable);
		jScrollPane.setBounds(32, 242, 444, 236);
		this.getContentPane().add(jScrollPane);
		
		JLabel tongTienLabel = new JLabel("Tổng tiền:");
		tongTienLabel.setBounds(330, 527, 59, 22);
		this.getContentPane().add(tongTienLabel);
	
		JLabel voucherLabel = new JLabel("Voucher:");
		voucherLabel.setBounds(330, 502, 58, 16);
		getContentPane().add(voucherLabel);
		
		JLabel voucherValueLabel = new JLabel("Giảm 0%");
		voucherValueLabel.setBounds(406, 499, 81, 22);
		getContentPane().add(voucherValueLabel);
		
		float amount = currentHoaDon.getTongTien();
		
		Locale locale = new Locale.Builder().setLanguage("vi").setRegion("VN").build();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        String formattedAmount = currencyFormat.format(amount);

        JLabel tongTienValueLabel = new JLabel(formattedAmount);
		tongTienValueLabel.setForeground(new Color(255, 0, 0));
		tongTienValueLabel.setBounds(406, 531, 90, 14);
		this.getContentPane().add(tongTienValueLabel);
		
		if (readOnly==false) {
			JButton confirmButton = new JButton("Xác nhận thanh toán");
			confirmButton.setBackground(new Color(234, 182, 118));
			confirmButton.setBounds(149, 560, 178, 31);
			getContentPane().add(confirmButton);
			
			if (currentKhachHang!=null) {
				ArrayList<Voucher> voucherChuaSuDungArrayList = VoucherBLL.getInStance().gettAllVoucherChuaSuDung(currentHoaDon.getMaKhach());
				if (voucherChuaSuDungArrayList.size()!=0) {
					float tongPhanTramGiam=0;
					for (int i = 0 ; i < voucherChuaSuDungArrayList.size(); i++) {
						Float sub = DieuKienVoucherBLL.getInstance().getPhanTramGiam(voucherChuaSuDungArrayList.get(i).getMaDieuKien());
						if (sub!=null)
							tongPhanTramGiam +=sub;
					}
					voucherValueLabel.setText("Giảm "+tongPhanTramGiam+"%");
					amount -= tongPhanTramGiam*0.01*amount;
					
					formattedAmount = currencyFormat.format(amount);
					tongTienValueLabel.setText(formattedAmount);
				}
				
				final float[] amountWrapper = new float[]{amount};
				
				confirmButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						currentHoaDon.setTongTien(amountWrapper[0]);
						currentHoaDon.setNgayTao(formattedDateTime);
						currentHoaDon.setTinhTrang("Đã thanh toán");
						HoaDonBLL.getInstance().updateHD(currentHoaDon);
						
						currentBanAn.setTrangThai("Trống");
						BanAnBLL.getInstance().updateBan(currentBanAn);
						
						if (voucherChuaSuDungArrayList.size()!=0) {
							for (int i = 0 ; i < voucherChuaSuDungArrayList.size(); i++) {
								voucherChuaSuDungArrayList.get(i).setTinhTrangSuDung("Đã sử dụng");
								VoucherBLL.getInStance().updateVoucher(voucherChuaSuDungArrayList.get(i));
								
								SuDungVoucher newSDVC = new SuDungVoucher(currentHoaDon.getMaHoaDon(), voucherChuaSuDungArrayList.get(i).getMaVoucher(), DieuKienVoucherBLL.getInstance().getPhanTramGiam(voucherChuaSuDungArrayList.get(i).getMaDieuKien()));
								SuDungVoucherBLL.getInStance().addNewSuDungVoucher(newSDVC);
							}
						}
						
						currentKhachHang.setDiemTichLuy((float)(currentKhachHang.getDiemTichLuy()+amountWrapper[0]*0.01));
						KhachHangBLL.getInstance().UpdateKhachHang(currentKhachHang);
						
						VoucherBLL.getInStance().generateNewVoucherForKhachHang(currentKhachHang.getMaKhach(), currentKhachHang.getDiemTichLuy());
						
						homeView.Init_table_order_panel();
						dispose();
					}
				});
			}
			else if (currentKhachHang==null) {
				final float[] amountWrapper = new float[]{amount};
				
				confirmButton.addActionListener(new ActionListener() {	
					public void actionPerformed(ActionEvent e) {
						currentHoaDon.setTongTien(amountWrapper[0]);
						currentHoaDon.setNgayTao(formattedDateTime);
						currentHoaDon.setTinhTrang("Đã thanh toán");
						HoaDonBLL.getInstance().updateHD(currentHoaDon);
						
						currentBanAn.setTrangThai("Trống");
						BanAnBLL.getInstance().updateBan(currentBanAn);			
						
						homeView.Init_table_order_panel();
						dispose();
					}
				});
			}
			
			
		}
		else if (readOnly==true) {
			if (currentKhachHang!=null) {
				ArrayList<SuDungVoucher> suDungVoucherArrayList = SuDungVoucherBLL.getInStance().getAllSuDungVoucher(currentHoaDon.getMaHoaDon());
				if (suDungVoucherArrayList.size()!=0) {
					float tongPhanTramGiam=0;
					for (int i = 0 ; i < suDungVoucherArrayList.size(); i++) {
						Float sub = DieuKienVoucherBLL.getInstance().getPhanTramGiam(suDungVoucherArrayList.get(i).getMaVoucher());
						if (sub!=null)
							tongPhanTramGiam +=sub;
					}
					voucherValueLabel.setText("Giảm "+tongPhanTramGiam+"%");
				}
				
			}
			
			JButton okButton = new JButton("OK");
			okButton.setBackground(new Color(234, 182, 118));
			okButton.setBounds(149, 560, 178, 31);
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			getContentPane().setBackground(new Color(238, 238, 228));
			getContentPane().add(okButton);
		}
		this.setVisible(true);
	}
}
