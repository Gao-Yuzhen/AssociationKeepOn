package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Comment;
import model.Topic;
import util.StringUtil;

public class commentDao {

	public ResultSet commentList(Connection con,int tid) throws Exception
	{
		String sql="select Content,Time,User_ID from comment where Topic_ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, tid);
		return pstmt.executeQuery();
	}
	
	public int delete(Connection con,int tid,String n) throws Exception
	{
		String sql="delete from comment where Topic_ID=? and Content=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, tid);
		pstmt.setString(2, n);
		return pstmt.executeUpdate();
	}
	
	public int add(Connection con,Comment c)throws Exception
	{
		String sql="insert into comment values(?,?,?,?,?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, c.getId());
	    pstmt.setString(2, c.getTime());
	    pstmt.setString(3, c.getContent());
	    pstmt.setInt(4, c.getUser_id());	   
	    pstmt.setInt(5, c.getTopic_id());
	    return pstmt.executeUpdate();
	}
	
	public ResultSet checkName(Connection con,String n,int tId)throws Exception
	{
		String sql="select ID from comment where comment=? and Topic_ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,n);
		pstmt.setInt(2, tId);
		return pstmt.executeQuery();
	}
	
	public int topicDelete(Connection con,int tId)throws Exception
	{
		String sql="delete from comment where Topic_ID=?";
	    PreparedStatement pstmt=con.prepareStatement(sql);	   
	    pstmt.setInt(1, tId);
	    return pstmt.executeUpdate();
	}
	public int userDelete(Connection con,String n)throws Exception
	{
		String sql="delete from comment where User_ID=(select ID from user where Name=?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);	   
	    pstmt.setString(1, n);
	    return pstmt.executeUpdate();
	}
}
