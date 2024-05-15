package VIEW;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import BLL.DieuKienVoucherBLL;
import DTO.DieuKienVoucher;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class DieuKienVoucherUpdate_InsertView extends JDialog{
	private static final long serialVersionUID = 1L;
	private Integer maDieuKien;
	private  ManagerView managerView;
	private JTextField maDieuKienTxt;
	private JTextField diemYeuCauTxt;
	private JTextField phanTramTxt;

	public DieuKienVoucherUpdate_InsertView(JFrame owner, ManagerView managerView ,Integer maDieuKien) {
		super(owner, "Điều kiện voucher", true);
		this.maDieuKien = maDieuKien;
		this.managerView = managerView;
		this.setBounds(100, 100, 397, 361);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		initialize();
		this.setVisible(true);
	}

	private void initialize() {
		JLabel maDKLabel = new JLabel("Mã điều kiện");
		maDKLabel.setBounds(41, 42, 71, 14);
		this.getContentPane().add(maDKLabel);
		this.getContentPane().setBackground(new Color(238, 238, 228));
		
		maDieuKienTxt = new JTextField();
		maDieuKienTxt.setEditable(false);
		maDieuKienTxt.setBounds(147, 39, 163, 20);
		this.getContentPane().add(maDieuKienTxt);
		maDieuKienTxt.setColumns(10);
		
		JLabel diemYeuCauLabel = new JLabel("Điểm yêu cầu");
		diemYeuCauLabel.setBounds(41, 99, 85, 14);
		this.getContentPane().add(diemYeuCauLabel);
		
		diemYeuCauTxt = new JTextField();
		diemYeuCauTxt.setBounds(147, 96, 163, 20);
		this.getContentPane().add(diemYeuCauTxt);
		diemYeuCauTxt.setColumns(10);
		
		JLabel phanTramLabel = new JLabel("Phần trăm giảm");
		phanTramLabel.setBounds(41, 161, 96, 14);
		this.getContentPane().add(phanTramLabel);
		
		phanTramTxt = new JTextField();
		phanTramTxt.setBounds(147, 158, 163, 20);
		this.getContentPane().add(phanTramTxt);
		phanTramTxt.setColumns(10);
		
		JLabel tihnTrangLabel = new JLabel("Tình trạng");
		tihnTrangLabel.setBounds(41, 227, 71, 19);
		this.getContentPane().add(tihnTrangLabel);
		
		JRadioButton openRad = new JRadioButton("Mở");
		openRad.setBounds(144, 223, 85, 23);
		openRad.setSelected(true);
		this.getContentPane().add(openRad);
		
		JRadioButton closeRad = new JRadioButton("Đóng");
		closeRad.setBounds(255, 223, 71, 23);
		this.getContentPane().add(closeRad);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(openRad);
		bg.add(closeRad);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((!phanTramTxt.getText().equals("")) && (!diemYeuCauTxt.getText().equals(""))) {
					float diemYeuCau;
					float phanTramGiam;
					try {
						phanTramGiam = Float.parseFloat(phanTramTxt.getText());
						diemYeuCau = Float.parseFloat(diemYeuCauTxt.getText());
						if (phanTramGiam<0 || diemYeuCau<0)
							JOptionPane.showMessageDialog(null, "Yêu cầu nhập số dương");
						else {
							int newMaDieuKien = Integer.parseInt(maDieuKienTxt.getText());
							String tinhTrang;
							if (openRad.isSelected())
								tinhTrang="Đang mở";
							else tinhTrang="Đã đóng";
							DieuKienVoucher newDKVC = new DieuKienVoucher(newMaDieuKien, diemYeuCau, phanTramGiam, tinhTrang);
							if (maDieuKien==null) 
								DieuKienVoucherBLL.getInstance().addNewDKVC(newDKVC);
							else {
								DieuKienVoucherBLL.getInstance().updateDKVC(newDKVC);
							}
							managerView.InitVoucherPanel();
							dispose();
						}
					}
					catch(Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Sai định dạng số");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Yêu cầu không để trống");
				}
			}
		});
		okButton.setBounds(41, 277, 89, 23);
		okButton.setBackground(new Color(235,182,120));
		getContentPane().add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setBounds(221, 277, 89, 23);
		cancelButton.setBackground(new Color(235,182,120));
		getContentPane().add(cancelButton);
		
		if (this.maDieuKien==null) {
			maDieuKienTxt.setText(DieuKienVoucherBLL.getInstance().generateNewID()+"");
		}
		else {
			DieuKienVoucher currentDKVC = DieuKienVoucherBLL.getInstance().getDKVCByID(this.maDieuKien);
			maDieuKienTxt.setText(currentDKVC.getMaDieuKien()+"");
			diemYeuCauTxt.setText(currentDKVC.getDiemYeuCau()+"");
			phanTramTxt.setText(currentDKVC.getPhanTram()+"");
			if (currentDKVC.getTinhTrang().equals("Đang mở"))
				openRad.setSelected(true);
			else
				closeRad.setSelected(true);
		}
	}
}
