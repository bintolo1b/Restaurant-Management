package BLL;

import DAL.QuanLyDAL;
import DTO.QuanLy;

public class QuanLyBLL {
private static QuanLyBLL instance;
	
	private QuanLyBLL() {	
	}
	
	public static QuanLyBLL getInstance() {
		if (instance==null)
			instance = new QuanLyBLL();
		return instance;
	}
	
	public void updateQuanLy(QuanLy object) {
		QuanLyDAL.getInstance().Update(object);
	}
}
