package BLL;

import java.util.ArrayList;
import DAL.SanPhamDAL;
import DTO.SanPham;

public class SanPhamBLL {
	private static SanPhamBLL instance;

    private SanPhamBLL(){
    }

    public static SanPhamBLL getInstance() {
        if (instance == null) {
            instance = new SanPhamBLL();
        }
        return instance;
    } 
    
	public int generateNewID(){
		return SanPhamDAL.getInstance().getLatestID()+1;
	}
    
    public SanPham getSanPhamByID(int ID){
    	return SanPhamDAL.getInstance().getSanPhamByID(ID);
    }
    
    public void insertSanPham(SanPham object) {
    	SanPhamDAL.getInstance().Insert(object);
    }
    
    public void updateSanPham(SanPham object) {
    	SanPhamDAL.getInstance().Update(object);
    }
    
    public void deleteSanPham(SanPham object) {
    	SanPhamDAL.getInstance().Delete(object);
    }
    
    public boolean isSanPhamExist(int productID) {
       return SanPhamDAL.getInstance().isSanPhamExist(productID);
    }
    
    public ArrayList<SanPham> getAllSanPhamInDanhMuc(int categoryID) {
    	return SanPhamDAL.getInstance().getAllSanPhamInDanhMuc(categoryID);
    }
    
    public ArrayList<SanPham> getAllSanPhamInDanhMucByName(String categoryName) {
    	return SanPhamDAL.getInstance().getAllSanPhamInDanhMucByName(categoryName);
    }
    
    public ArrayList<SanPham> getSanPhamBySubName(String productName){
    	return SanPhamDAL.getInstance().getSanPhamBySubName(productName);
    }
    
    public float getGia(String tenSanPham) {
    	return SanPhamDAL.getInstance().getGia(tenSanPham);
    }
    public ArrayList<SanPham> getAllSanPham() {
    	ArrayList<SanPham> sanPhamArrayList = SanPhamDAL.getInstance().getAll();
    	return  sanPhamArrayList;
    }
    public SanPham getSanPhamByTen(String tenSp) {
    	return SanPhamDAL.getInstance().getSanPhamByTen(tenSp);
    }
}
