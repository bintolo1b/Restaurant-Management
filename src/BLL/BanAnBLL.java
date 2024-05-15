package BLL;

import java.util.ArrayList;

import DAL.BanAnDAL;
import DTO.BanAn;


public class BanAnBLL {
	private static BanAnBLL instance;

    private BanAnBLL(){
    }

    public static BanAnBLL getInstance() {
        if (instance == null) {
            instance = new BanAnBLL();
        }
        return instance;
    } 
    
    public ArrayList<BanAn> getAllBanAn() {
    	ArrayList<BanAn> banAnArrayList = BanAnDAL.getInstance().getAll();
    	return banAnArrayList;
    }
    
    public int generateNewID(){
 		String Condition = "select* "
 				+ "from qlnh.ban_an ba  "
 				+ "order by ba.maBan desc "
 				+ "limit 1";
     	ArrayList<BanAn> banAnArrayList = BanAnDAL.getInstance().selectByCondition(Condition);
     	if (banAnArrayList.size()==0)
     		return 1;
     	else return banAnArrayList.get(0).getMaBan()+1;
 	}
   
    public BanAn getBan(int tableID) {
    	return BanAnDAL.getInstance().getBan(tableID);
    }
    
    public void updateBan(BanAn newBanAn) {
    	BanAnDAL.getInstance().Update(newBanAn);
    }
    
    public void insertBan(BanAn newBanAn) {
    	BanAnDAL.getInstance().Insert(newBanAn);
    }
}
