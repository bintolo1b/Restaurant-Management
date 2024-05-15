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

public class ConfirmAddProductView extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private ArrayList<Object[]> newProductNotConfirmedYet;
	private boolean clickOk=false;
	private int maHD;
	
	public ConfirmAddProductView(JFrame owner, int maHD, ArrayList<Object[]> newProductNotConfirmedYet) {
		super(owner,"",true);
		this.maHD=maHD;
		this.newProductNotConfirmedYet=newProductNotConfirmedYet;
		initialize();
	}

	private void initialize() {
		this.getContentPane().setBackground(new Color(238, 238, 228));
		this.setBounds(100, 100, 474, 518);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		HoaDon currentDon = HoaDonBLL.getInstance().getHoaDonByID(maHD);
		
		JLabel headerLabel = new JLabel("Thông báo thêm món nhà bếp");
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		headerLabel.setBounds(121, 13, 231, 24);
		this.getContentPane().add(headerLabel);
		
		JLabel tableLabel = new JLabel("Bàn:");
		tableLabel.setBounds(38, 50, 56, 16);
		this.getContentPane().add(tableLabel);
		
		JLabel turnLabel = new JLabel("Thêm món đợt:");
		turnLabel.setBounds(38, 108, 103, 16);
		this.getContentPane().add(turnLabel);
		
		JLabel turn_valueLabel = new JLabel(newProductNotConfirmedYet.get(0)[4].toString());
		turn_valueLabel.setBounds(144, 108, 56, 16);
		this.getContentPane().add(turn_valueLabel);
		
		JLabel table_valueLabel = new JLabel(currentDon.getMaBan()+"");
		table_valueLabel.setBounds(83, 50, 50, 16);
		this.getContentPane().add(table_valueLabel);
		
		JLabel productListLabel = new JLabel("Danh sách món");
		productListLabel.setBounds(181, 186, 103, 16);
		this.getContentPane().add(productListLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 215, 397, 179);
		this.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên món");
		model.addColumn("Số lượng");
		model.addColumn("Đơn giá");
		model.addColumn("Thành tiền");
		
		for (int i = 0 ; i<newProductNotConfirmedYet.size();i++) {
			model.addRow(newProductNotConfirmedYet.get(i));
		}
		
		table.setModel(model);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		table.setBackground(new Color(238,238,228)); 
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton confirmButton = new JButton("In đơn");
		confirmButton.setBackground(new Color(235, 182, 120));
		confirmButton.setBounds(103, 441, 97, 25);
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
		cancelButton.setBounds(255, 441, 97, 25);
		this.getContentPane().add(cancelButton);
		
		JLabel dateLabel = new JLabel("Ngày:");
		dateLabel.setBounds(38, 79, 56, 16);
		getContentPane().add(dateLabel);
		
		LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
		
		
		JLabel date_valueLabel = new JLabel(formattedDateTime);
		date_valueLabel.setBounds(83, 79, 138, 16);
		getContentPane().add(date_valueLabel);
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
