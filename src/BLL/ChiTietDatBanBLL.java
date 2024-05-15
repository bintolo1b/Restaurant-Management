package BLL;

import java.util.ArrayList;

import DAL.ChiTietDatBanDAL;
import DTO.ChiTietDatBan;


public class ChiTietDatBanBLL {
	private static ChiTietDatBanBLL instance;
	
	private ChiTietDatBanBLL() {
	}
	
	public static ChiTietDatBanBLL getInstance(){
		if (instance==null)
			instance = new ChiTietDatBanBLL();
		return instance;
	} 
	
	public ArrayList<ChiTietDatBan> getAllCTDB(){
		return ChiTietDatBanDAL.getInstance().getAll();
	}
	
	public ArrayList<ChiTietDatBan> FindTable(String Begin, String End, String text, ArrayList<ChiTietDatBan> ctdbArrayList)
	{
		ArrayList<ChiTietDatBan> ctdbAL = new ArrayList<ChiTietDatBan>();
		for (int i = 0 ; i < ctdbArrayList.size() ; i++) 
		{
			if (ctdbArrayList.get(i).getNgayGioDat().compareTo(Begin)>=0 && ctdbArrayList.get(i).getNgayGioDat().compareTo(End)<=0)
			{
				if (text.equals("")) ctdbAL.add(ctdbArrayList.get(i)); else
					if (ctdbArrayList.get(i).getSoDienThoaiKH().equals(text)) ctdbAL.add(ctdbArrayList.get(i)); 
			}
		}
		return ctdbAL;
	}
	
	public void Insert(ChiTietDatBan obj) 
	{
		ChiTietDatBanDAL.getInstance().Insert(obj);
	}

	public void Update(ChiTietDatBan obj) {
		ChiTietDatBanDAL.getInstance().Update(obj);
	}

	public void UpdateCB(ChiTietDatBan obj, int maban) {
		ChiTietDatBanDAL.getInstance().UpdateCB(obj, maban);
	}
}
