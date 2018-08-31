package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Research;
import model.Topic;
import util.StringUtil;

public class topicDao {
	
	public ResultSet topicList(Connection con,int aId,String n) throws Exception
	{
		StringBuffer sb=new StringBuffer ("select Title,Time,User_ID from topic");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Title like '%"+n+"%'");
		}
		sb.append(" and Association_ID=?");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		pstmt.setInt(1, aId);
		return pstmt.executeQuery();
	}
	
	public int delete(Connection con,int aId,String n) throws Exception
	{
		String sql1="delete from comment where Topic_ID=(select ID from topic where Association_ID=? and Title=?)";
		PreparedStatement pstmt1=con.prepareStatement(sql1);
		pstmt1.setInt(1, aId);
		pstmt1.setString(2, n);

		String sql="delete from topic where Association_ID=? and Title=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, aId);
		pstmt.setString(2, n);
		return pstmt.executeUpdate();
	}
	
	public ResultSet checkTid(Connection con, String n,int aid) throws Exception
	{
		String sql="select ID,Content from topic where Title=? and Association_ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, n);
		pstmt.setInt(2, aid);
		return pstmt.executeQuery();
		
	}
	
	public int add(Connection con,Topic t)throws Exception
	{
		String sql="insert into topic values(?,?,?,?,?,?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, t.getId());
	    pstmt.setString(2, t.getTitle());
	    pstmt.setString(3, t.getContent());
	    pstmt.setString(4, t.getTime());
	    pstmt.setInt(5, t.getUser_id());	   
	    pstmt.setInt(6, t.getAssoci_id());
	    return pstmt.executeUpdate();
	}
	
	public ResultSet maxId(Connection con)throws Exception
	{
		String sql="select max(ID+1) ID from topic";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	public ResultSet checkName(Connection con,String n,int aId)throws Exception
	{
		String sql="select ID from topic where Title=? and Association_ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,n);
		pstmt.setInt(2, aId);
		return pstmt.executeQuery();
	}
	
	public ResultSet myTopic(Connection con,String n)throws Exception
	{
		String sql="select ID from topic where User_ID=(select ID from user where Name=?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,n);
		return pstmt.executeQuery();
	}

	public int userDelete(Connection con,String n)throws Exception
	{
		String sql2="delete from topic where User_ID=(select ID from user where Name=?)";
		PreparedStatement pstmt2=con.prepareStatement(sql2);
		pstmt2.setString(1,n);
		return pstmt2.executeUpdate();
	}
}
