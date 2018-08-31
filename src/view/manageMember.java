package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.associDao;
import dao.userAssociDao;
import util.dbUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class manageMember extends JFrame {

	dbUtil dUtil=new dbUtil();
	userAssociDao uaDao=new userAssociDao();
	associDao aDao=new associDao();
	private JPanel contentPane;
	private JTable memberTable;
	private JTable checkTable;
	private String outName;
	private String checkName;
	private String status;
	private JTextField searchTxt;
	private JTextField searchTxt2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageMember frame = new manageMember();
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
	public manageMember() {
		setResizable(false);
		outName="";
		checkName="";
		status="";
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
		
		JScrollPane memberList = new JScrollPane();
		memberList.setBounds(115, 339, 400, 289);
		contentPane.add(memberList);
		
		JScrollPane checkList = new JScrollPane();
		checkList.setBounds(600, 339, 446, 289);
		contentPane.add(checkList);
		
		memberTable = new JTable();
		memberTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				memberTableMousePressed(arg0);
			}
		});
		memberTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u59D3\u540D", "\u6027\u522B"
			}
		));
		memberTable.getColumnModel().getColumn(0).setPreferredWidth(120);
		memberTable.getColumnModel().getColumn(1).setPreferredWidth(160);
		memberTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		memberTable.setBounds(115, 339, 400, 289);
		memberTable.setRowHeight(30);
		memberTable.getTableHeader().setVisible(true); 
		memberTable.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		memberTable.getTableHeader().setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		memberTable.setDefaultRenderer(Object.class, r);
		memberList.setViewportView(memberTable);
		
		checkTable = new JTable();
		checkTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				checkTableMousePressed(e);
			}
		});
		checkTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u59D3\u540D", "\u6027\u522B", "\u7533\u8BF7"
			}
		));
		checkTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		checkTable.getColumnModel().getColumn(1).setPreferredWidth(146);
		checkTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		checkTable.getColumnModel().getColumn(3).setPreferredWidth(140);
		checkTable.setBounds(600, 339, 446, 289);
		checkTable.setRowHeight(30);
		checkTable.getTableHeader().setVisible(true); 
		checkTable.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		checkTable.getTableHeader().setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		DefaultTableCellRenderer r2 = new DefaultTableCellRenderer();   
		r2.setHorizontalAlignment(JLabel.CENTER);   
		checkTable.setDefaultRenderer(Object.class, r2);
		checkList.setViewportView(checkTable);
		
		JButton b_exit = new JButton("\u9000\u56DE\u9996\u9875");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		
		JButton b_out = new JButton("\u9000\u9664\u6210\u5458");
		b_out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_outActionPerformed(e);
			}
		});
		b_out.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_out.setBounds(392, 643, 123, 35);
		contentPane.add(b_out);
		
		JLabel label = new JLabel("\u793E\u56E2\u6210\u5458");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 28));
		label.setBounds(115, 292, 123, 32);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5F85\u5BA1\u6838");
		label_1.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 28));
		label_1.setBounds(600, 292, 123, 32);
		contentPane.add(label_1);
		
		JButton b_check = new JButton("\u901A\u8FC7\u5BA1\u6838");
		b_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_checkActionPerformed(e);
			}
		});
		b_check.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_check.setBounds(923, 643, 123, 35);
		contentPane.add(b_check);
		
		searchTxt = new JTextField();
		searchTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		searchTxt.setBounds(310, 292, 115, 33);
		contentPane.add(searchTxt);
		searchTxt.setColumns(10);
		
		JButton b_search = new JButton("");
		b_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_searchActionPerformed(arg0);
			}
		});
		b_search.setBounds(445, 292, 33, 33);
		contentPane.add(b_search);
		ImageIcon i_search = new ImageIcon("src/icon/search.png");
		b_search.setIcon(i_search);
		
		searchTxt2 = new JTextField();
		searchTxt2.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		searchTxt2.setColumns(10);
		searchTxt2.setBounds(710, 291, 115, 33);
		contentPane.add(searchTxt2);
		
		JButton b_search2 = new JButton("");
		b_search2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_search2ActionPerformed(arg0);
			}
		});
		b_search2.setBounds(845, 291, 33, 33);
		b_search2.setIcon(i_search);
		contentPane.add(b_search2);
		
		fillMemTable(Logon.associ.getId());
		fillCheckTable(Logon.associ.getId());
	}
	
	public void fillMemTable(int aId)
	{
		int x=1;
		DefaultTableModel dtm=(DefaultTableModel) memberTable.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		try
		{
			con=dUtil.getCon();
			ResultSet rs=uaDao.myMember(con, aId,searchTxt.getText());
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(x);
				x++;
				v.add(rs.getString("Name"));
				v.add(rs.getString("Sex"));
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
	
	public void fillCheckTable(int aId)
	{
		int x=1;
		DefaultTableModel dtm=(DefaultTableModel) checkTable.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		try
		{
			con=dUtil.getCon();
			ResultSet rs=uaDao.checkMember(con, aId,searchTxt2.getText());
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(x);
				x++;
				v.add(rs.getString("Name"));
				v.add(rs.getString("Sex"));
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
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new A_main().setVisible(true);
	}
	
	private void memberTableMousePressed(MouseEvent e) {
		int row=memberTable.getSelectedRow();
		outName= (String) memberTable.getValueAt(row, 1);
	}
	
	private void checkTableMousePressed(MouseEvent e) {
		int row=checkTable.getSelectedRow();
		checkName= (String) checkTable.getValueAt(row, 1);
		status=(String) checkTable.getValueAt(row, 3);
	}
	
	private void b_outActionPerformed(ActionEvent e) {
		if(outName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要退除的成员");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			int n=uaDao.memDelete(con, Logon.associ.getId(),outName);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "退除成员成功");
				int m=Logon.associ.getMemberNum();
				Logon.associ.setMemberNum(m-1);
				fillMemTable(Logon.associ.getId());
				fillCheckTable(Logon.associ.getId());
				aDao.updateMem(con, Logon.associ.getId(), m-1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "退除成员失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "退除成员失败");
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
	
	private void b_checkActionPerformed(ActionEvent e) {
		if(checkName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要通过审核的成员");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			if(status.equals("加入待审核"))
			{
				int n=uaDao.memCheckIn(con, Logon.associ.getId(), checkName);
				if(n==1)
				{
					JOptionPane.showMessageDialog(null, "审核通过");
					int m=Logon.associ.getMemberNum();
					Logon.associ.setMemberNum(m+1);
					fillMemTable(Logon.associ.getId());
					fillCheckTable(Logon.associ.getId());
					aDao.updateMem(con, Logon.associ.getId(), m+1);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "审核未通过");
				}
			}
			if(status.equals("退出待审核"))
			{
				int n=uaDao.memDelete(con, Logon.associ.getId(),checkName);
				if(n==1)
				{
					JOptionPane.showMessageDialog(null, "审核通过");
					int m=Logon.associ.getMemberNum();
					Logon.associ.setMemberNum(m-1);
					fillMemTable(Logon.associ.getId());
					fillCheckTable(Logon.associ.getId());
					aDao.updateMem(con, Logon.associ.getId(), m-1);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "审核未通过");
				}
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "退除成员失败");
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
		fillMemTable(Logon.associ.getId());
	}

	private void b_search2ActionPerformed(ActionEvent arg0) {
		fillCheckTable(Logon.associ.getId());
	}
}
