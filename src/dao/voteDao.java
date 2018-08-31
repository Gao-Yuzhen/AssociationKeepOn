package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Activity;
import model.Vote;
import util.StringUtil;

public class voteDao {
	
	public int add(Connection con,Vote v)throws Exception
	{
		String sql="insert into vote values(?,?,?,?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, v.getId());
	    pstmt.setString(2, v.getTitle());
	    pstmt.setString(3, v.getTime());
	    pstmt.setInt(4, v.getAssoci_id());	    
	    return pstmt.executeUpdate();
	}
	
	public ResultSet maxId(Connection con)throws Exception
	{
		String sql="select max(ID+1) ID from vote";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	public ResultSet vList(Connection con,int aId,String n) throws Exception
	{
		StringBuffer sb=new StringBuffer ("select Title from vote");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Title like '%"+n+"%'");
		}
		sb.append(" and Association_ID=?");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		pstmt.setInt(1, aId);
		return pstmt.executeQuery();
	}
	
	public int cancelV(Connection con,int aId,String name) throws Exception
	{
	    String sql="delete from vote where Association_ID=? and Title=?";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, aId);
	    pstmt.setString(2, name);
	    return pstmt.executeUpdate();
	}
	
	public ResultSet checkName(Connection con,String n,int aId)throws Exception
	{
		String sql="select ID from research where Title=? and Association_ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,n);
		pstmt.setInt(2, aId);
		return pstmt.executeQuery();
	}

}
