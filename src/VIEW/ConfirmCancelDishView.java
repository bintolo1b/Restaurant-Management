package VIEW;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import BLL.HoaDonBLL;
import DTO.HoaDon;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.Color;

public class ConfirmCancelDishView extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private ArrayList<Object[]> cancelProduct;
	private boolean clickOk=false;
	private int maHD;
	
	public ConfirmCancelDishView(JFrame owner, int maHD, ArrayList<Object[]> cancelProduct) {
		super(owner,"",true);
		this.maHD=maHD;
		this.cancelProduct=cancelProduct;
		initialize();
	}

	private void initialize() {
		this.getContentPane().setBackground(new Color(238, 238, 228));
		this.setBounds(100, 100, 474, 474);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		HoaDon currentDon = HoaDonBLL.getInstance().getHoaDonByID(maHD);
		
		JLabel headerLabel = new JLabel("Xác nhận hủy món");
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		headerLabel.setBounds(158, 13, 170, 24);
		this.getContentPane().add(headerLabel);
		
		JLabel tableLabel = new JLabel("Bàn:");
		tableLabel.setBounds(38, 50, 56, 16);
		this.getContentPane().add(tableLabel);
		
		JLabel table_valueLabel = new JLabel(currentDon.getMaBan()+"");
		table_valueLabel.setBounds(83, 50, 50, 16);
		this.getContentPane().add(table_valueLabel);
		
		JLabel productListLabel = new JLabel("Danh sách món");
		productListLabel.setBounds(177, 128, 103, 16);
		this.getContentPane().add(productListLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 157, 397, 179);
		this.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên món");
		model.addColumn("Số lượng");
		model.addColumn("Đơn giá");
		model.addColumn("Thành tiền");
		model.addColumn("Lần gọi");
		
		for (int i = 0 ; i<cancelProduct.size();i++) {
			model.addRow(cancelProduct.get(i));
		}
		
		table.setModel(model);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		table.setBackground(new Color(238,238,228)); 
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton confirmButton = new JButton("Xác nhận");
		confirmButton.setBackground(new Color(235, 182, 120));
		confirmButton.setBounds(103, 393, 97, 25);
		this.getContentPane().add(confirmButton);
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickOk=true;
				dispose();
			}
		});
		
		JButton cancelButton = new JButton("Hủy");
		cancelButton.setBackground(new Color(235, 182, 120));
		cancelButton.setBounds(267, 393, 97, 25);
		this.getContentPane().add(cancelButton);
		
		JLabel dateLabel = new JLabel("Ngày:");
		dateLabel.setBounds(38, 79, 56, 16);
		getContentPane().add(dateLabel);
		
		LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
		
		
		JLabel date_valueLabel = new JLabel(formattedDateTime);
		date_valueLabel.setBounds(83, 79, 134, 16);
		getContentPane().add(date_valueLabel);
		
		JLabel warningLabel = new JLabel("(Sau khi xác nhận thì không thể hoàn lại)");
		warningLabel.setBounds(119, 363, 261, 16);
		getContentPane().add(warningLabel);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.setVisible(true);
	}

	public boolean isClickOk() {
		return clickOk;
	}
	
	
}
