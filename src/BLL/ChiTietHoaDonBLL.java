package BLL;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


import DAL.ChiTietHoaDonDAL;
import DAL.SanPhamDAL;
import DTO.ChiTietHoaDon;

public class ChiTietHoaDonBLL {
	private static ChiTietHoaDonBLL instance;
	
	private ChiTietHoaDonBLL() {
	}
	
	public static ChiTietHoaDonBLL getInstance(){
		if (instance==null)
			instance = new ChiTietHoaDonBLL();
		return instance;
	}
	
	public ArrayList<ChiTietHoaDon> getAllChiTietHoaDonOfHoaDon(int maHD){
		return ChiTietHoaDonDAL.getInstance().getAllChiTietHoaDonOfHoaDon(maHD);
	}
	
	public void exportChiTietHoaDonIntoServedModelTable(DefaultTableModel model, ArrayList<ChiTietHoaDon> cthdOfSpecificHoaDonArrayList) {
		ChiTietHoaDonDAL.getInstance().exportChiTietHoaDonIntoServedModelTable(model, cthdOfSpecificHoaDonArrayList);
	}
	
	public ChiTietHoaDon getChiTietHoaDon(int maHoaDon, int maSanPham, int lanGoi) {
		return ChiTietHoaDonDAL.getInstance().getChiTietHoaDon(maHoaDon, maSanPham, lanGoi);
	}
	
	public void upDateCTHD(int maHD, DefaultTableModel model) {
		for (int i = 0 ; i < model.getRowCount() ; i++) {
			String tenSP = model.getValueAt(i, 0).toString();
			int maSP  = SanPhamDAL.getInstance().getSanPhamByTen(tenSP).getMaSanPham(); 
			int lanGoi = (int)model.getValueAt(i, 4);
			
			ChiTietHoaDon cthd = ChiTietHoaDonDAL.getInstance().getChiTietHoaDon(maHD, maSP, lanGoi);
			if (cthd==null) {
				ChiTietHoaDon newCTHD = new ChiTietHoaDon(maHD, maSP,(float)model.getValueAt(i, 2),model.getValueAt(i, 0).toString() ,(int)model.getValueAt(i, 1), (int)model.getValueAt(i, 4), model.getValueAt(i, 5).toString());
				ChiTietHoaDonDAL.getInstance().Insert(newCTHD);
			}
			else {
				cthd.setSoLuong((int)model.getValueAt(i, 1));
				cthd.setTinhTrangPhucVu(model.getValueAt(i, 5).toString());
				ChiTietHoaDonDAL.getInstance().Update(cthd);
			}		
		}
	}
	
	public ChiTietHoaDon getCTHDByLatestLanGoi(int maHD) {
		return ChiTietHoaDonDAL.getInstance().getCTHDByLatestLanGoi(maHD);
	}

	public void exportChiTietHoaDonIntoModelTableWithMergeSP(DefaultTableModel model, int maHoaDon) {
		ChiTietHoaDonDAL.getInstance().exportChiTietHoaDonIntoModelTableWithMergeSP(model, maHoaDon);
	}
	public boolean checkIfExistSpChuaPhucVu(int maHD) {
		return ChiTietHoaDonDAL.getInstance().checkIfExistSpChuaPhucVu(maHD);
	}
	public void deleteCTHD(ChiTietHoaDon cthd) {
		ChiTietHoaDonDAL.getInstance().Delete(cthd);
	}
	public void updateCTHD(ChiTietHoaDon cthd) {
		ChiTietHoaDonDAL.getInstance().Update(cthd);
	}

	public void exportChiTietHoaDonIntoWaitingModelTable(DefaultTableModel waitingModel, ArrayList<ChiTietHoaDon> cthdOfSpecificHoaDonArrayList) {
		ChiTietHoaDonDAL.getInstance().exportChiTietHoaDonIntoWaitingModelTable(waitingModel, cthdOfSpecificHoaDonArrayList);
	}
}
