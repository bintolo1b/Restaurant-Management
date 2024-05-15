package VIEW;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import com.toedter.calendar.JDateChooser;

import BLL.BanAnBLL;
import DTO.BanAn;


public class ReservationTableView extends JFrame{
	private static final long serialVersionUID = 1L;
	public JPanel ReservationTable;
	public JSpinner timeSpinner;
	public JTextField SDTText;
	public JDateChooser DateChooser ;
	public JComboBox<BanAn> BanCBB = new JComboBox<BanAn>();
	public JButton okButton;
	public JButton cancelButton;
	public ArrayList<BanAn> bananArrayList;
	
	public void init()
	{
		JLabel BanJlabel = new JLabel("Bàn");
		BanJlabel.setBounds(50, 10, 100, 20);
		
		JLabel NgayNhanBanJlable = new JLabel("Ngày đặt bàn");
		NgayNhanBanJlable.setBounds(50, 50, 100, 20);
		
		JLabel GioNhanBanJlable = new JLabel("Giờ đặt bàn");
		GioNhanBanJlable.setBounds(50, 90, 100, 20);
		
		SpinnerDateModel spinnerModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setBounds(200, 90, 100, 20);
		
		JLabel SDTJlabel = new JLabel("Số điện thoại");
		SDTJlabel.setBounds(50, 130, 100, 20);
		
		SDTText = new JTextField();
		SDTText.setBounds(200, 130, 100, 20);
		
		bananArrayList = BanAnBLL.getInstance().getAllBanAn();
		for (int i=0; i<bananArrayList.size(); i++)
			if (!bananArrayList.get(i).getTrangThai().equals("Đã ẩn"))
				BanCBB.addItem(bananArrayList.get(i));
		BanCBB.setBounds(200, 10, 100, 20);
		
		DateChooser = new JDateChooser();
        DateChooser.setDateFormatString("yyyy-MM-dd");
        DateChooser.setCalendar(Calendar.getInstance());
        DateChooser.setBounds(200, 50, 100, 20);

        okButton = new JButton("OK");
        okButton.setBounds(70, 180, 80, 20);
        okButton.setBackground(new Color(235,182,120));
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180, 180, 80, 20);
        cancelButton.setBackground(new Color(235,182,120));
        
		setBounds(400,400,375,280);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(238, 238, 228));
		setLayout(null);
		add(BanJlabel);
		add(NgayNhanBanJlable);
		add(GioNhanBanJlable);
		add(SDTJlabel);
		add(DateChooser);
		add(BanCBB);
		add(SDTText);
		add(timeSpinner);
		add(okButton);
		add(cancelButton);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public ReservationTableView() {
		this.init();
	}
}
