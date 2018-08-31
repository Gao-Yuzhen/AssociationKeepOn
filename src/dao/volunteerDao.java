package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Activity;
import model.Volunteer;
import util.StringUtil;

public class volunteerDao {

	public int add(Connection con,Volunteer vol)throws Exception
	{
		String sql="insert into volunteerproject values(?,?,?,?,?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, vol.getId());
	    pstmt.setString(2, vol.getName());
	    pstmt.setString(3, vol.getTime());
	    pstmt.setString(4, vol.getCapacity());
	    pstmt.setInt(5, vol.getAssoci_id());	    
	    return pstmt.executeUpdate();
	}
	
	public ResultSet maxId(Connection con)throws Exception
	{
		String sql="select max(ID+1) ID from volunteerproject";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	public ResultSet volList(Connection con,int aId,String n) throws Exception
	{
		StringBuffer sb=new StringBuffer ("select Name,Time,Capacity from volunteerproject");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Name like '%"+n+"%'");
		}
		sb.append(" and Association_ID=?");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		pstmt.setInt(1, aId);
		return pstmt.executeQuery();
	}
	
	public int cancelVol(Connection con,int aId,String name) throws Exception
	{
	    String sql="delete from volunteerproject where Association_ID=? and Name=?";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, aId);
	    pstmt.setString(2, name);
	    return pstmt.executeUpdate();
	}
	
	public ResultSet checkName(Connection con,String n,int aId)throws Exception
	{
		String sql="select ID from research where Name=? and Association_ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,n);
		pstmt.setInt(2, aId);
		return pstmt.executeQuery();
	}
}
