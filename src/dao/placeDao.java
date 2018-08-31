package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Activity;
import model.Place;
import util.StringUtil;

public class placeDao {

	public int add(Connection con,Place p)throws Exception
	{
		String sql="insert into activityplace values(null,?,?,?,?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	    pstmt.setString(1, p.getName());
	    pstmt.setString(2, p.getStatus());
	    pstmt.setString(3, p.getCapacity());
	    pstmt.setInt(4, p.getAdmin_id());	    
	    return pstmt.executeUpdate();
	}
	
	public ResultSet pList(Connection con,int adId,String n) throws Exception
	{
		StringBuffer sb=new StringBuffer ("select Name,Capacity,Status from activityplace");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Name like '%"+n+"%'");
		}
		sb.append(" and Administrator_ID=?");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));	
		pstmt.setInt(1, adId);
		return pstmt.executeQuery();
	}
	
	public int cancelP(Connection con,int adId,String name) throws Exception
	{
	    String sql="delete from activityplace where Administrator_ID=? and Name=?";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, adId);
	    pstmt.setString(2, name);
	    return pstmt.executeUpdate();
	}
	
	public ResultSet checkStatus(Connection con,String n)throws Exception
	{
		String sql="select Status from activityplace where Name=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, n);
		return pstmt.executeQuery();
	}
	
	public int updateStatus(Connection con,String status,String n)throws Exception
	{
		String sql="update activityplace set Status=? where Name=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, status);
		pstmt.setString(2, n);
		return pstmt.executeUpdate();
	}
	
}
