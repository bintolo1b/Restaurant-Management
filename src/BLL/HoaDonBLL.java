package BLL;

import java.util.ArrayList;
import java.util.Date;

import org.jfree.data.category.DefaultCategoryDataset;

import DAL.HoaDonDAL;
import DTO.HoaDon;

public class HoaDonBLL {
	private static HoaDonBLL instance;

    private HoaDonBLL(){
    }

    public static HoaDonBLL getInstance() {
        if (instance == null) {
            instance = new HoaDonBLL();
        }
        return instance;
    } 
    
	public int generateNewID(){
		return HoaDonDAL.getInstance().getLatestID()+1;
	}
	
	public void addNewHoaDon(HoaDon newHD) {
		HoaDonDAL.getInstance().Insert(newHD);
	}
	
	public HoaDon getLatestHoaDonOfTable(int tableID) {
		return HoaDonDAL.getInstance().getLatestHoaDonOfTable(tableID);
	}
	
	public void updateHD(HoaDon oldHD) {
		HoaDonDAL.getInstance().Update(oldHD);
	}
	
	public HoaDon getHoaDonByID(int maHD) {
		return HoaDonDAL.getInstance().getHoaDonByID(maHD);
	}
	
	public ArrayList<HoaDon> getAllHoaDonDaThanhToan(){
		return HoaDonDAL.getInstance().getAllHoaDonDaThanhToan();
	}
	
	public void updateRevenue(DefaultCategoryDataset dataset) {
		HoaDonDAL.getInstance().updateRevenue(dataset);
	}
	
	public void updateRevenueFollowingDates(DefaultCategoryDataset dataset, Date startDate, Date endDate) {
		HoaDonDAL.getInstance().updateChartFollowingDates(dataset, startDate, endDate);
	}

}
