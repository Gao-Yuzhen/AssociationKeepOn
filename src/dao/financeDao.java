package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Activity;
import model.Finance;
import util.StringUtil;

public class financeDao {
	
	public int add(Connection con,Finance fi)throws Exception
	{
		String sql="insert into finance values(null,?,?,?,?,?)";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	    pstmt.setString(1, fi.getName());
	    pstmt.setInt(2,fi.getTotal() );
	    pstmt.setInt(3,fi.getIncome() );
	    pstmt.setInt(4,fi.getExpense() );
	    pstmt.setInt(5, fi.getAssoci_id());	    
	    return pstmt.executeUpdate();
	}
	
	public ResultSet fList(Connection con,int aId,String n) throws Exception
	{
		StringBuffer sb=new StringBuffer ("select Name,Income,Expense from finance");
		if(!StringUtil.isEmpty(n))
		{
			sb.append(" and Name like '%"+n+"%'");
		}
		sb.append(" and Association_ID=?");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		pstmt.setInt(1, aId);
		return pstmt.executeQuery();
	}
	
	public ResultSet fTotal(Connection con,String name) throws Exception
	{
		String sql="select Total from finance where Name=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, name);
		return pstmt.executeQuery();
	}

	public int cancelFi(Connection con,int aId,String name) throws Exception
	{
	    String sql="delete from finance where Association_ID=? and Name=?";
	    PreparedStatement pstmt=con.prepareStatement(sql);
	   	pstmt.setInt(1, aId);
	    pstmt.setString(2, name);
	    return pstmt.executeUpdate();
	}
	
	public ResultSet modifyFi(Connection con,int aId,String name) throws Exception
	{
		String sql="select ID,Name,Income,Expense,Total from finance where Association_ID=? and Name=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, aId);
	    pstmt.setString(2, name);
		return pstmt.executeQuery();
	}
	
	public int update(Connection con,Finance f)throws Exception
	{
		String sql="update finance set Name=?,Income=?,Expense=?,Total=? where ID=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, f.getName());
		pstmt.setInt(2, f.getIncome());
		pstmt.setInt(3, f.getExpense());
		pstmt.setInt(4, f.getTotal());
		pstmt.setInt(5, f.getId());
		return pstmt.executeUpdate();
	}
}
