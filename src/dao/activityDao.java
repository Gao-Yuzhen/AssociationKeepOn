package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Activity;
import model.Association;
import util.StringUtil;

public class activityDao {
	
	public int add(Connection con,Activity ac)throws Exception
	{
		String sql="insert into activityproject values(?,?,?,?,?,?,?,?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, ac.getId());
	    pstmt.setString(2, ac.getName());
	    pstmt.setString(3, ac.getStatus());
	    pstmt.setString(4, ac.getTime());
	    pstmt.setString(5, ac.getPlace());
	    pstmt.setString(6, ac.getTarget());
	    pstmt.setString(7, ac.getIntroduce());
	    pstmt.setInt(8, ac.getAssoci_id());	    
	    return pstmt.executeUpdate();
	}
	
	public ResultSet maxId(Connection con)throws Exception
	{
		String sql="select max(ID+1) ID from activityproject";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	public ResultSet myAcList(Connection con,int aId,String n) throws Exception
	{
		StringBuffer sb=new StringBuffer ("select Name,Time,Status from activityproject");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Name like '%"+n+"%'");
		}
		sb.append(" and Association_ID=?");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		pstmt.setInt(1, aId);
		return pstmt.executeQuery();
	}
	
	public ResultSet acList(Connection con,String n) throws Exception
	{
		StringBuffer sb=new StringBuffer ("select Name,Time,Association_ID,Status from activityproject");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Name like '%"+n+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public int cancelAc(Connection con,int aId,String name) throws Exception
	{
	    String sql="delete from activityproject where Association_ID=? and Name=?";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, aId);
	    pstmt.setString(2, name);
	    return pstmt.executeUpdate();
	}
	
	public ResultSet manageAc(Connection con,int aId,String name) throws Exception
	{
		String sql="select ID,Name,Time,Introduce,Target,Place from activityproject where Association_ID=? and Name=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, aId);
	    pstmt.setString(2, name);
		return pstmt.executeQuery();
	}
	
	public int update(Connection con,Activity ac)throws Exception
	{
		String sql="update activityproject set Name=?,Time=?,Place=?,Target=?,Introduce=? where ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, ac.getName());
		pstmt.setString(2, ac.getTime());
		pstmt.setString(3, ac.getPlace());
		pstmt.setString(4, ac.getTarget());
		pstmt.setString(5, ac.getIntroduce());
		pstmt.setInt(6, ac.getId());
		return pstmt.executeUpdate();
	}
	
	public int resetAc(Connection con,int aId) throws Exception	
	{
		String sql="delete from activityproject where Association_ID=?";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, aId);
	    return pstmt.executeUpdate();
	}

	public ResultSet checkStatus(Connection con,String n,int aid)throws Exception
	{
		String sql="select Status from activityproject where Name=? and Association_ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, n);
	   	pstmt.setInt(2, aid);
		return pstmt.executeQuery();
	}
	
	public int updateStatus(Connection con,String status,String n,int aid)throws Exception
	{
		String sql="update activityproject set Status=? where Name=? and Association_ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, status);
		pstmt.setString(2, n);
	   	pstmt.setInt(3, aid);
		return pstmt.executeUpdate();
	}
	
	
	public ResultSet checkName(Connection con,String n,int aid)throws Exception
	{
		String sql="select ID from activityproject where Name=? and Association_ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,n);
		pstmt.setInt(2, aid);
		return pstmt.executeQuery();
	}
}
