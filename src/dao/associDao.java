package dao;

import java.sql.ResultSet;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Association;
import model.User;
import util.StringUtil;

public class associDao {
	public Association login(Connection con,Association a) throws Exception{
		Association resultA=null;
		String sql="select * from association where Name=? and Password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, a.getName());
		pstmt.setString(2, a.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultA=new Association();
			resultA.setName(rs.getString("Name"));
			resultA.setPassword(rs.getString("Password"));
			resultA.setMemberNum(rs.getInt("MemberNum"));
			resultA.setStatus(rs.getString("Status"));
			resultA.setTime(rs.getString("Time"));
			resultA.setId(rs.getInt("ID"));
			resultA.setLeader(rs.getString("Leader"));
			resultA.setFinance(rs.getInt("Finance"));
		}
		return resultA;
	}

	public ResultSet associList(Connection con,Association a) throws Exception
	{
		StringBuffer sb=new StringBuffer ("select * from association");
		if(!StringUtil.isEmpty(a.getName()))
		{
			sb.append(" and Name like '%"+a.getName()+"%'");
		}
		sb.append(" and Status='正式社团'");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();	
	}
	
	public int update(Connection con,Association a)throws Exception
	{
		String sql="update association set Name=?,Leader=? where ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, a.getName());
		pstmt.setString(2, a.getLeader());
		pstmt.setInt(3, a.getId());
		return pstmt.executeUpdate();
	}
	
	public int updateMem(Connection con,int aId,int mem)throws Exception
	{
		String sql="update association set MemberNum=? where ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, mem);
		pstmt.setInt(2, aId);
		return pstmt.executeUpdate();
	}
	
	public int updateFin(Connection con,int aId,int fi)throws Exception
	{
		String sql="update association set Finance=? where ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, fi);
		pstmt.setInt(2, aId);
		return pstmt.executeUpdate();
	}
	
	public int updateStatus(Connection con,String status,int aId)throws Exception
	{
		String sql="update association set Status=? where ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, status);
		pstmt.setInt(2, aId);
		return pstmt.executeUpdate();
	}
	
	public int updateTime(Connection con,String time,int aId)throws Exception
	{
		String sql="update association set Time=? where ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, time);
		pstmt.setInt(2, aId);
		return pstmt.executeUpdate();
	}
	
	public int add(Connection con,Association a)throws Exception
	{
		String sql="insert into association values(?,?,?,'申请待审核',?,null,0,?,1)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, a.getId());
	    pstmt.setString(2, a.getName());
	    pstmt.setString(3, a.getPassword());
	    pstmt.setString(4, a.getLeader());
	    pstmt.setInt(5, a.getFinance());
	    return pstmt.executeUpdate();
	}
	
	public ResultSet maxId(Connection con)throws Exception
	{
		String sql="select max(ID+1) ID from association";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	public ResultSet asList(Connection con,int adId,String n)throws Exception
	{
		StringBuffer sb=new StringBuffer ("select ID,Name,Leader,Status from association");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Name like '%"+n+"%'");
		}
		sb.append(" and Administrator_ID=?");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));		
	   	pstmt.setInt(1, adId);
		return pstmt.executeQuery();
	}
	
	public ResultSet checkStatus(Connection con,int aId)throws Exception
	{
		String sql="select Status from association where Name=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, aId);
		return pstmt.executeQuery();
	}
	
	public ResultSet checkMem(Connection con,int id)throws Exception
	{
		String sql="select MemberNum from association where ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, id);
		return pstmt.executeQuery();
	}
}
