package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Association;
import model.User;
import model.UserAssoci;
import util.StringUtil;

public class userAssociDao {
	
	public int add(Connection con,UserAssoci ua) throws Exception
	{
	    String sql="insert into user_has_association values(?,?,?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, ua.getuId());
	    pstmt.setInt(2, ua.getaId());
	    pstmt.setString(3, ua.getStatus());
	    return pstmt.executeUpdate();
	}
	
	public int outUpdate(Connection con,int uId,String aName) throws Exception
	{
	    String sql="update user_has_association set Status='退出待审核' where User_ID=? and Association_ID=(select ID from association where Name=?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, uId);
	    pstmt.setString(2, aName);
	    return pstmt.executeUpdate();
	}
	
	public ResultSet check(Connection con,UserAssoci ua) throws Exception
	{
		String sql="select * from user_has_association where User_ID=? and Association_ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, ua.getuId());
		pstmt.setInt(2, ua.getaId());
		return pstmt.executeQuery();
	}
	
	public ResultSet myAssociList(Connection con,int uId,String n) throws Exception
	{
		StringBuffer sb=new StringBuffer ("select Name,user_has_association.Status from user_has_association,association");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Name like '%"+n+"%'");
		}
		sb.append(" and User_ID=? and Association_ID=ID");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		pstmt.setInt(1, uId);
		return pstmt.executeQuery();
	}
	
	public ResultSet myMember(Connection con,int aId,String n) throws Exception
	{
		StringBuffer sb=new StringBuffer ("select Name,Sex from user_has_association,user");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Name like '%"+n+"%'");
		}
		sb.append(" and Association_ID=? and User_ID=ID and Status!='加入待审核'");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		pstmt.setInt(1, aId);
		return pstmt.executeQuery();
	}
	
	public ResultSet checkMember(Connection con,int aId,String n) throws Exception
	{
		StringBuffer sb=new StringBuffer ("select Name,Sex,Status from user_has_association,user");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Name like '%"+n+"%'");
		}
		sb.append(" and Association_ID=? and User_ID=ID and Status!='已加入'");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		pstmt.setInt(1, aId);
		return pstmt.executeQuery();
	}
	
	public int memDelete(Connection con,int aId,String uName) throws Exception
	{
	    String sql="delete from user_has_association where Association_ID=? and User_ID=(select ID from user where Name=?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, aId);
	    pstmt.setString(2, uName);
	    return pstmt.executeUpdate();
	}
	
	public int memCheckIn(Connection con,int aId,String uName) throws Exception
	{
	    String sql="update user_has_association set Status='已加入' where Association_ID=? and User_ID=(select ID from user where Name=?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, aId);
	    pstmt.setString(2, uName);
	    return pstmt.executeUpdate();
	}
	
	public int resetMem(Connection con,int aId) throws Exception	
	{
		String sql="delete from user_has_association where Association_ID=?";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, aId);
	    return pstmt.executeUpdate();
	}
	
	public int userDelete(Connection con,String sid) throws Exception	
	{
		String sql="delete from user_has_association where User_ID=(select ID from user where Email=?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setString(1, sid);
	    return pstmt.executeUpdate();
	}
	
	public ResultSet myAssoci(Connection con,String sid) throws Exception
	{
		String sql="select Association_ID from user_has_association,user where Email=? and User_ID=ID and Status!='加入待审核'";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, sid);
		return pstmt.executeQuery();
	}
}
