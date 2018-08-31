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
import dao.placeDao;
import util.dbUtil;

public class adminPlace extends JFrame {

	dbUtil dUtil=new dbUtil();
	placeDao pDao=new placeDao();
	private JPanel contentPane;
	private JTable pTable;
	private JScrollPane pList;
	private String mName;
	private JTextField searchTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminPlace frame = new adminPlace();
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
	public adminPlace() {
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
		
		pList = new JScrollPane();
		pList.setBounds(273, 375, 607, 289);
		contentPane.add(pList);
		
		pTable = new JTable();
		pTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				pTableMousePressed(arg0);
			}
		});
		pTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u5730\u70B9\u540D\u79F0", "\u4EBA\u5458\u5BB9\u91CF", "\u72B6\u6001"
			}
		));
		pTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		pTable.getColumnModel().getColumn(1).setPreferredWidth(267);
		pTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		pTable.getColumnModel().getColumn(3).setPreferredWidth(140);
		pTable.setRowHeight(30);
		pTable.getTableHeader().setVisible(true); 
		pTable.setBounds(273, 339, 607, 289);
		pTable.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		pTable.getTableHeader().setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		pTable.setDefaultRenderer(Object.class, r);
		pList.setViewportView(pTable);
		
		JButton b_in = new JButton("\u6DFB\u52A0\u5730\u70B9");
		b_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_inActionPerformed(arg0);
			}
		});
		b_in.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_in.setBounds(916, 336, 130, 35);
		contentPane.add(b_in);
		
		JButton b_stop = new JButton("\u6682\u505C\u4F7F\u7528");
		b_stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_stopActionPerformed(e);
			}
		});
		b_stop.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_stop.setBounds(916, 398, 130, 35);
		contentPane.add(b_stop);
		
		JButton b_use = new JButton("\u6062\u590D\u4F7F\u7528");
		b_use.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_useActionPerformed(e);
			}
		});
		b_use.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_use.setBounds(916, 460, 130, 35);
		contentPane.add(b_use);
		
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
		
		JLabel label = new JLabel("\u67E5\u8BE2\u5730\u70B9");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label.setBounds(273, 315, 196, 35);
		contentPane.add(label);
		
		fillTable(Logon.admin.getId());
	}

	public void fillTable(int adId)
	{
		DefaultTableModel dtm=(DefaultTableModel) pTable.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		int x=1;
		try
		{
			con=dUtil.getCon();
			ResultSet rs=pDao.pList(con, adId,searchTxt.getText());
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(x);
				x++;
				v.add(rs.getString("Name"));
				v.add(rs.getString("Capacity"));
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
	
	private void pTableMousePressed(MouseEvent e) {
		int row=pTable.getSelectedRow();
		mName= (String) pTable.getValueAt(row, 1);
	}
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new Admin_main().setVisible(true);
	}
	
	private void b_inActionPerformed(ActionEvent e) {

		this.dispose();
		new addPlace().setVisible(true);
	}
	
	private void b_stopActionPerformed(ActionEvent e) {
		if(mName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要暂停使用的地点");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			ResultSet rs=pDao.checkStatus(con, mName);
			while(rs.next())
			{
				if(rs.getString("Status").equals("暂停使用"))
				{
					JOptionPane.showMessageDialog(null, "该地点已暂停使用");
					return;
				}
					
			}
			int n=pDao.updateStatus(con,"暂停使用",mName);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "暂停使用成功");
				fillTable(Logon.admin.getId());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "暂停使用失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "暂停使用失败");
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
	
	private void b_useActionPerformed(ActionEvent e) {
		if(mName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要恢复使用的地点");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			ResultSet rs=pDao.checkStatus(con, mName);
			while(rs.next())
			{
				if(rs.getString("Status").equals("可以使用"))
				{
					JOptionPane.showMessageDialog(null, "该地点可以使用");
					return;
				}
					
			}
			int n=pDao.updateStatus(con,"可以使用",mName);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "恢复使用成功");
				fillTable(Logon.admin.getId());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "恢复使用失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "恢复使用失败");
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
