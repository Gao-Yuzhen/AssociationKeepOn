package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbUtil {
	private String dbUrl="jdbc:mysql://localhost:3306/association"; 
	private String dbUserName="root"; 
	private String dbPassword="kcys5050";
	private String jdbcName="com.mysql.jdbc.Driver"; 
	
	// 获取数据库连接 
	public Connection getCon() throws Exception
	{ 
		Class. forName (jdbcName); 
		Connection con=DriverManager. getConnection (dbUrl,dbUserName,dbPassword);
		return con; 
	} 
	// 关闭数据库连接 
	public void closeCon(Connection con) throws Exception
	{ 
		if(con!=null)
		{ con.close(); } 
	} 
	public static void main(String[] args) 
	{ 
		dbUtil Util=new dbUtil(); 
		try 
		{ 
			Util.getCon(); 
		    System. out .println("数据库连接成功"); 
		} 
		catch (Exception e) 
		{ 
			System.out.println("数据库连接失败");   
		} 
	}
	
}
