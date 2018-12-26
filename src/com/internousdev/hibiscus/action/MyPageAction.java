package com.internousdev.hibiscus.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.hibiscus.dao.UserInfoDAO;
import com.internousdev.hibiscus.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author fujii
 */
public class MyPageAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;

	public String execute() {
		//タイムアウト処理
		if(!session.containsKey("mCategoryDtoList")) { return "session"; }

		String result = ERROR;
		//login情報判定
		if(session.containsKey("logined")){
			if(Integer.parseInt(session.get("logined").toString())==1){

				UserInfoDAO userInfoDAO = new UserInfoDAO();

				//user情報取得
				UserInfoDTO userInfoDTO = userInfoDAO.getUserInfo(String.valueOf(session.get("loginId")));

				if(userInfoDTO!= null){
					session.put("familyName", userInfoDTO.getFamilyName());
					session.put("firstName",userInfoDTO.getFirstName());
					session.put("familyNameKana",userInfoDTO.getFamilyNameKana());
					session.put("firstNameKana",userInfoDTO.getFirstNameKana());
					session.put("sex",userInfoDTO.getSex());
					session.put("email",userInfoDTO.getEmail());
				}
				return SUCCESS;
			}
		}

		return result;

	}
	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}

}

