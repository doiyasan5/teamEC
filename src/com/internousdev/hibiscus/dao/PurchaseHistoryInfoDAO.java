package com.internousdev.hibiscus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.hibiscus.dto.PurchaseHistoryInfoDTO;
import com.internousdev.hibiscus.util.DBConnector;

public class PurchaseHistoryInfoDAO {

	//商品購入履歴一覧の取得 取得するもの(商品名、商品名かな、画像ファイルパス、画像ファイル名、値段、個数、発売会社、発売年月日、合計金額）
	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryList(String loginId){
		DBConnector dbConnector=new DBConnector();
		Connection con=dbConnector.getConnection();

		List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList=new ArrayList<PurchaseHistoryInfoDTO>();

		//SQL文
		String sql="select "

				+ "pi.product_name as product_name," 			//商品名
				+ "pi.product_name_kana as product_name_kana,"	//商品名かな
				+ "pi.image_file_name as image_file_name,"		//画像ファイルパス
				+ "pi.image_file_path as image_file_path,"		//画像ファイルぱす
				+ "phi.price as price,"							//値段
				+ "phi.product_count as product_count,"			//個数
				+ "pi.release_company,"							//発売会社
				+ "pi.release_date"								//発売年月日
				+ " FROM purchase_history_info as phi"
				+ " LEFT JOIN product_info as pi"
				+ " ON phi.product_id = pi.product_id"
				+ " WHERE phi.user_id=?";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,loginId);

			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()){
				PurchaseHistoryInfoDTO purchaseHistoryInfoDTO=new PurchaseHistoryInfoDTO();
				purchaseHistoryInfoDTO.setProductName(resultSet.getString("product_name"));
				purchaseHistoryInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
				purchaseHistoryInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
				purchaseHistoryInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
				purchaseHistoryInfoDTO.setPrice(resultSet.getInt("price"));
				purchaseHistoryInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
				purchaseHistoryInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
				purchaseHistoryInfoDTO.setProductCount(resultSet.getInt("product_count"));
				purchaseHistoryInfoDTO.setSubtotal(resultSet.getInt("price")*resultSet.getInt("product_count"));
				purchaseHistoryInfoDTOList.add(purchaseHistoryInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return purchaseHistoryInfoDTOList;
	}

	//商品購入情報をデータベースに格納
	public int regist(String loginId, int productId, int productCount, int destinationId, int price){

		DBConnector dbConnector = new DBConnector();
		Connection con = dbConnector.getConnection();
		String sql = "insert into purchase_history_info(user_id, product_id, product_count, price, destination_id, regist_date, update_date) "
				+ "values (?, ?, ?, ?, ?, now(), now())";
		int count = 0;
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setInt(2, productId);
			ps.setInt(3, productCount);
			ps.setInt(4, price);
			ps.setInt(5, destinationId);
			count = ps.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}

	//商品購入履歴一覧の全削除
	public int deleteAll(String loginId){

		DBConnector dbConnector = new DBConnector();
		Connection con = dbConnector.getConnection();
		String sql = "delete from purchase_history_info where user_id=?";
		int count = 0;
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			count = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return count;
	}

}
