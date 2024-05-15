package BLL;

import java.util.ArrayList;
import DAL.DanhMucDAL;
import DTO.DanhMuc;

public class DanhMucBLL {
	private static DanhMucBLL instance;

    private DanhMucBLL(){
    }

    public static DanhMucBLL getInstance() {
        if (instance == null) {
            instance = new DanhMucBLL();
        }
        return instance;
    } 
    
	public int generateNewID(){
		return DanhMucDAL.getInstance().getLatestID()+1;
	}
    
    public void updateDanhMuc(DanhMuc object) {
    	DanhMucDAL.getInstance().Update(object);
    }
    
    public void insertDanhMuc(DanhMuc object) {
    	DanhMucDAL.getInstance().Insert(object);
    }
    
    public DanhMuc getDanhMucByID(int ID){
    	return DanhMucDAL.getInstance().getDanhMucByID(ID);
    }
    
    public boolean isDanhMucExist(int categoryID) {
       return DanhMucDAL.getInstance().isDanhMucExist(categoryID);
    }

    public ArrayList<DanhMuc> getAllDanhMuc() {
    	ArrayList<DanhMuc> danhMucArrayList = DanhMucDAL.getInstance().getAll();
    	return  danhMucArrayList;
    }
}
