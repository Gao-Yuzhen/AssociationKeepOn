package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Activity;
import model.User;
import util.StringUtil;

public class userDao {
	//µÇÂ½ÑéÖ¤
	public User login(Connection con,User user) throws Exception{
		User resultUser=null;
		String sql="select * from user where Name=? and Password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setName(rs.getString("Name"));
			resultUser.setPassword(rs.getString("Password"));
			resultUser.setId(rs.getInt("ID"));
			resultUser.setEmail(rs.getString("Email"));
			resultUser.setSex(rs.getString("Sex"));
			resultUser.setBirthday(rs.getString("Birthday"));
		}
		return resultUser;
	}
	
	public ResultSet check(Connection con,User user) throws Exception
	{
		String sql="select Name,Sex,Email,Birthday from user where ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, user.getId());
		return pstmt.executeQuery();
	}
	
	public int update(Connection con,User user)throws Exception
	{
		String sql="update user set Name=?,Email=?,Sex=?,Birthday=? where ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getEmail());
		pstmt.setString(3, user.getSex());
		pstmt.setString(4, user.getBirthday());
		pstmt.setInt(5, user.getId());
		return pstmt.executeUpdate();
	}

	public int add(Connection con,User user)throws Exception
	{
		String sql="insert into user values(?,?,?,?,?,null,?,1)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, user.getId());
	    pstmt.setString(2, user.getName());
	    pstmt.setString(3, user.getPassword());
	    pstmt.setString(4, user.getEmail());
	    pstmt.setString(5, user.getSex());
	    pstmt.setString(6, user.getBirthday());
	    return pstmt.executeUpdate();
	}
	
	public ResultSet maxId(Connection con)throws Exception
	{
		String sql="select max(ID+1) ID from user";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	public ResultSet uList(Connection con,int adId,String n)throws Exception
	{
		StringBuffer sb=new StringBuffer ("select ID,Name,Sex,Email from user");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Name like '%"+n+"%'");
		}
		sb.append(" and Administrator_ID=?");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
	   	pstmt.setInt(1, adId);
		return pstmt.executeQuery();
	}
	
	public int deleteUser(Connection con,String sid) throws Exception
	{
	    String sql="delete from user where Email=?";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setString(1, sid);
	    return pstmt.executeUpdate();
	}
	


}
