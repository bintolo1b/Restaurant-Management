package VIEW;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import BLL.BanAnBLL;
import DTO.BanAn;

import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DinningTableUpdate_InsertView extends JFrame{
	private static final long serialVersionUID = 1L;
	private JSpinner chairNumber_Spinner;
	
	public DinningTableUpdate_InsertView(int dinningTableID, ManagerView managerView) {
	
		JLabel dinningTable_lbl = new JLabel("Bàn ăn");
		dinningTable_lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		dinningTable_lbl.setBounds(193, 21, 122, 38);
		
		JLabel dinningTableID_lbl = new JLabel("Mã bàn");
		dinningTableID_lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dinningTableID_lbl.setBounds(59, 76, 60, 30);

		JTextField dinningTableID_txt = new JTextField();
		dinningTableID_txt.setBounds(161, 75, 30, 30);
		dinningTableID_txt.setColumns(10);
		
		JLabel chairNumber_lbl = new JLabel("Số ghế");
		chairNumber_lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chairNumber_lbl.setBounds(255, 76, 60, 30);
		
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 0, Integer.MAX_VALUE, 1);
        chairNumber_Spinner = new JSpinner(spinnerModel);
		chairNumber_Spinner.setBounds(359, 76, 40, 27);

		
		JLabel status_lbl = new JLabel("Trạng thái");
		status_lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		status_lbl.setBounds(59, 159, 80, 30);
		
		JRadioButton hidingRad_btn = new JRadioButton("Ẩn");
		hidingRad_btn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		hidingRad_btn.setBounds(161, 163, 46, 23);
		
		if(dinningTableID != 0) {
			BanAn banAn = BanAnBLL.getInstance().getBan(dinningTableID);
			dinningTableID_txt.setText(banAn.getMaBan()+"");
			dinningTableID_txt.setEditable(false);
			spinnerModel.setValue(banAn.getSoGhe());
			if(banAn.getTrangThai().equals("Đã ẩn")) hidingRad_btn.setSelected(true);	
		}
		
		else {
			dinningTableID_txt.setText(BanAnBLL.getInstance().generateNewID()+"");
			dinningTableID_txt.setEditable(false);

		}
				
		JButton ok_btn = new JButton("OK");
		ok_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tableID = Integer.parseInt(String.valueOf(dinningTableID_txt.getText()));
				String chairNumber_str = String.valueOf(chairNumber_Spinner.getValue());
				String status = "Trống";
				if(hidingRad_btn.isSelected()) status = "Đã ẩn";
						int chairNumber = Integer.parseInt(chairNumber_str);
						if(dinningTableID == 0) {
							BanAnBLL.getInstance().insertBan(new BanAn(tableID,chairNumber,status));
							dispose();
							managerView.Init_table_order_panel();
						}
						else {
							BanAn banAn = BanAnBLL.getInstance().getBan(dinningTableID);
							if(banAn.getTrangThai().equals("Có người")&&status.equals("Đã ẩn")) {
								JOptionPane.showMessageDialog(null, "Không thể ẩn vì bàn đang có người!");
							}
							else {
								BanAnBLL.getInstance().updateBan(new BanAn(tableID,chairNumber,status));
								dispose();
								managerView.Init_table_order_panel();
							}
						}
					}
		});
		ok_btn.setBackground(new Color(235, 182, 120));
		ok_btn.setBounds(59, 238, 100, 30);
	
		JButton cancel_btn = new JButton("Cancel");
		cancel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel_btn.setBackground(new Color(235, 182, 120));
		cancel_btn.setBounds(272, 238, 100, 30);
		
		this.add(dinningTable_lbl);
		this.add(dinningTableID_lbl);
		this.add(dinningTableID_txt);
		this.add(chairNumber_lbl);
		this.add(chairNumber_Spinner);
		this.add(status_lbl);
		this.add(hidingRad_btn);
		this.add(ok_btn);
		this.add(cancel_btn);
		
		this.getContentPane().setBackground(new Color(238, 238, 228));
		this.setSize(459, 337);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
	}
}
