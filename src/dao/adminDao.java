package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Admin;

public class adminDao {
	public Admin login(Connection con,Admin admin) throws Exception{
		Admin resultAdmin=null;
		String sql="select * from administrator where Name=? and Password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, admin.getName());
		pstmt.setString(2, admin.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultAdmin=new Admin();
			resultAdmin.setName(rs.getString("Name"));
			resultAdmin.setPassword(rs.getString("Password"));
			resultAdmin.setId(rs.getInt("ID"));
		}
		return resultAdmin;
	}

}
