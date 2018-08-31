package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Activity;
import model.Research;
import util.StringUtil;

public class researchDao {
	
	public int add(Connection con,Research r)throws Exception
	{
		String sql="insert into research values(?,?,?,?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, r.getId());
	    pstmt.setString(2, r.getTitle());
	    pstmt.setString(3, r.getContent());
	    pstmt.setInt(4, r.getAssoci_id());	    
	    return pstmt.executeUpdate();
	}
	
	public ResultSet maxId(Connection con)throws Exception
	{
		String sql="select max(ID+1) ID from research";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	public ResultSet rList(Connection con,int aId,String n) throws Exception
	{
		StringBuffer sb=new StringBuffer ("select Title from research");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Name like '%"+n+"%'");
		}
		sb.append(" and Association_ID=?");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		pstmt.setInt(1, aId);
		return pstmt.executeQuery();
	}
	
	public int cancelR(Connection con,int aId,String name) throws Exception
	{
	    String sql="delete from research where Association_ID=? and Title=?";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, aId);
	    pstmt.setString(2, name);
	    return pstmt.executeUpdate();
	}

}
