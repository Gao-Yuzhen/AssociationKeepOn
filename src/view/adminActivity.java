package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.activityDao;
import dao.associDao;
import util.dbUtil;

public class adminActivity extends JFrame {

	dbUtil dUtil=new dbUtil();
	activityDao acDao=new activityDao();
	associDao aDao=new associDao();
	private JPanel contentPane;
	private JTable acTable;
	private JScrollPane acList;
	private String mName;
	private JTextField searchTxt;
	private String aName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminActivity frame = new adminActivity();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public adminActivity() {
		mName="";
		setResizable(false);
		setTitle("\u793E\u56E2KeepOn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1219, 866);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// 设置Frame居中显示
		this.setLocationRelativeTo(null);
						
		//设置背景
		JPanel p = new JPanel();
		ImageIcon i = new ImageIcon("src/back.jpg");
		JLabel l = new JLabel(i);
		l.setBounds(0, 0, 1230, 830);
		getLayeredPane().add(l, new Integer(Integer.MIN_VALUE));
		p = (JPanel) this.getContentPane();
		p.setOpaque(false);
		p.setBounds(0, 0, 1219, 866);
		
		JButton b_exit = new JButton("\u9000\u56DE\u9996\u9875");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		
		acList = new JScrollPane();
		acList.setBounds(273, 375, 607, 289);
		contentPane.add(acList);
		
		acTable = new JTable();
		acTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				acTableMousePressed(arg0);
			}
		});
		acTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u6D3B\u52A8\u540D\u79F0", "\u65F6\u95F4", "\u793E\u56E2","\u72B6\u6001"
			}
		));
		acTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		acTable.getColumnModel().getColumn(1).setPreferredWidth(287);
		acTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		acTable.getColumnModel().getColumn(3).setPreferredWidth(140);
		acTable.getColumnModel().getColumn(4).setPreferredWidth(140);
		acTable.setRowHeight(30);
		acTable.getTableHeader().setVisible(true); 
		acTable.setBounds(273, 339, 607, 289);
		acTable.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		acTable.getTableHeader().setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		acTable.setDefaultRenderer(Object.class, r);
		acList.setViewportView(acTable);
		
		JLabel label = new JLabel("\u67E5\u8BE2\u6D3B\u52A8");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label.setBounds(273, 315, 196, 35);
		contentPane.add(label);
		
		JButton b_in = new JButton("\u901A\u8FC7\u7533\u8BF7");
		b_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_inActionPerformed(arg0);
			}
		});
		b_in.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_in.setBounds(916, 336, 130, 35);
		contentPane.add(b_in);
		
		JButton b_stop = new JButton("\u7EC8\u6B62\u6D3B\u52A8");
		b_stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_stopActionPerformed(e);
			}
		});
		b_stop.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_stop.setBounds(916, 398, 130, 35);
		contentPane.add(b_stop);
		
		searchTxt = new JTextField();
		searchTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		searchTxt.setBounds(416, 317, 285, 32);
		contentPane.add(searchTxt);
		searchTxt.setColumns(10);
		
		JButton b_search = new JButton("");
		b_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_searchActionPerformed(arg0);
			}
		});
		b_search.setBounds(720, 316, 33, 33);
		contentPane.add(b_search);
		ImageIcon i_search = new ImageIcon("src/icon/search.png");
		b_search.setIcon(i_search);
		
		fillTable(Logon.admin.getId());
	}

	public void fillTable(int adId)
	{
		DefaultTableModel dtm=(DefaultTableModel) acTable.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		int x=1;
		try
		{
			con=dUtil.getCon();
			ResultSet rs=acDao.acList(con,searchTxt.getText());
			while(rs.next())
			{
				Vector v=new Vector();
				int aid=rs.getInt("Association_ID");
				aName="";
				ResultSet nrs=aDao.checkId(con,aid);
				while(nrs.next())
				{
					aName=nrs.getString("Name");
				}
				v.add(x);
				x++;
				v.add(rs.getString("Name"));
				v.add(rs.getString("Time"));
				v.add(aName);
				v.add(rs.getString("Status"));
				dtm.addRow(v);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void acTableMousePressed(MouseEvent e) {
		int row=acTable.getSelectedRow();
		mName= (String) acTable.getValueAt(row, 1);
		aName=(String)acTable.getValueAt(row, 3);
	}
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new Admin_main().setVisible(true);
	}
	
	private void b_inActionPerformed(ActionEvent e) {
		if(mName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要通过申请的活动");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			ResultSet nrs=aDao.checkName(con, aName);
			int aid=0;
			while(nrs.next())
			{
				aid=nrs.getInt("ID");
			}
			ResultSet rs=acDao.checkStatus(con, mName,aid);
			while(rs.next())
			{
				if(rs.getString("Status").equals("申请已通过"))
				{
					JOptionPane.showMessageDialog(null, "该活动已通过申请");
					return;
				}
					
			}
			int n=acDao.updateStatus(con, "申请已通过",mName,aid);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "申请通过成功");
				fillTable(Logon.admin.getId());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "申请通过失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "申请通过失败");
		}
		finally
		{
			try {
				dUtil.closeCon(con);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}
	private void b_stopActionPerformed(ActionEvent e) {
		if(mName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要终止的活动");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			ResultSet nrs=aDao.checkName(con, aName);
			int aid=0;
			while(nrs.next())
			{
				aid=nrs.getInt("ID");
			}
			ResultSet rs=acDao.checkStatus(con, mName,aid);
			while(rs.next())
			{
				if(rs.getString("Status").equals("申请待审核"))
				{
					JOptionPane.showMessageDialog(null, "该活动还未通过审核");
					return;
				}
					
			}
			int n=acDao.cancelAc(con,aid,mName);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "终止活动成功");
				fillTable(Logon.admin.getId());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "终止活动失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "终止活动失败");
		}
		finally
		{
			try {
				dUtil.closeCon(con);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}
	
	private void b_searchActionPerformed(ActionEvent arg0) {
		fillTable(Logon.admin.getId());
	}

}
