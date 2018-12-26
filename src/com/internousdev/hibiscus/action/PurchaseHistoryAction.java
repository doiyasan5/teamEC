package com.internousdev.hibiscus.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.hibiscus.dao.PurchaseHistoryInfoDAO;
import com.internousdev.hibiscus.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseHistoryAction extends ActionSupport implements SessionAware{

	//購入履歴のDTOを管理するListを生成
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList=new ArrayList<PurchaseHistoryInfoDTO>();
	private Map<String ,Object> session;

	public String execute(){

		//タイムアウト処理
		if(!session.containsKey("mCategoryDtoList")) { return "session"; }

		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO =new PurchaseHistoryInfoDAO();

		//データベースから商品購入履歴の情報を取得してリストに情報を格納する
		purchaseHistoryInfoDTOList=purchaseHistoryInfoDAO.getPurchaseHistoryList(String.valueOf(session.get("loginId")));

		return SUCCESS;

	}

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDTOList() {
		return purchaseHistoryInfoDTOList;
	}

	public void setPurchaseHistoryInfoDTOList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList) {
		this.purchaseHistoryInfoDTOList = purchaseHistoryInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session=session;
	}
}
