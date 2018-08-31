package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.activityDao;
import dao.associDao;
import dao.commentDao;
import dao.topicDao;
import dao.userAssociDao;
import dao.userDao;
import util.dbUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class adminUser extends JFrame {

	dbUtil dUtil=new dbUtil();
	associDao aDao=new associDao();
	userAssociDao uaDao=new userAssociDao();
	activityDao acDao=new activityDao();
	userDao uDao=new userDao();
	commentDao cDao=new commentDao();
	topicDao tDao=new topicDao();
	private JPanel contentPane;
	private JScrollPane uList;
	private JTable uTable;
	private String uName;
	private JTextField searchTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminUser frame = new adminUser();
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
	public adminUser() {
		uName="";
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
		
		uList = new JScrollPane();
		uList.setBounds(273, 375, 607, 289);
		contentPane.add(uList);
		
		uTable = new JTable();
		uTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				uTableMousePressed(arg0);
			}
		});
		uTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u7528\u6237\u540D", "\u6027\u522B", "\u5B66\u53F7"
			}
		));
		uTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		uTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		uTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		uTable.getColumnModel().getColumn(3).setPreferredWidth(207);
	    uTable.setRowHeight(30);
		uTable.getTableHeader().setVisible(true); 
		uTable.setBounds(273, 339, 607, 289);
		uTable.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		uTable.getTableHeader().setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		uTable.setDefaultRenderer(Object.class, r);
		uList.setViewportView(uTable);
		
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
		
		JLabel label = new JLabel("\u67E5\u8BE2\u7528\u6237");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label.setBounds(273, 315, 196, 35);
		contentPane.add(label);
		
		fillTable(Logon.admin.getId());
		
		JButton b_out = new JButton("\u6CE8\u9500\u7528\u6237");
		b_out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_outActionPerformed(e);
			}
		});
		b_out.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_out.setBounds(916, 336, 130, 35);
		contentPane.add(b_out);
	}
		
	public void fillTable(int adId)
	{
		DefaultTableModel dtm=(DefaultTableModel) uTable.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		try
		{
			int x=1;
			con=dUtil.getCon();
			ResultSet rs=uDao.uList(con, adId,searchTxt.getText());
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(x);
				x++;
				v.add(rs.getString("Name"));
				v.add(rs.getString("Sex"));
				v.add(rs.getString("Email"));
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
		new Admin_main().setVisible(true);
	}
	
	private void uTableMousePressed(MouseEvent e) {
		int row=uTable.getSelectedRow();
		uName= (String) uTable.getValueAt(row, 1);
	}
	
	private void b_outActionPerformed(ActionEvent e) {
		if(uName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要注销的用户");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			int mem=0;
			Vector v=new Vector();
			Vector v2=new Vector();
			ResultSet rs=uaDao.myAssoci(con, uName);
			while(rs.next())
			{
				v.add(rs.getInt("Association_ID"));	
			}
			uaDao.userDelete(con, uName);
			ResultSet trs=tDao.myTopic(con, uName);
			while(trs.next())
			{
				v2.add(trs.getInt("ID"));	
			}
			for(int i=0;i<v2.size();i++) 
			{
				int tId=(int) v2.get(i);
			    cDao.topicDelete(con,tId);
			}
			cDao.userDelete(con, uName);
			tDao.userDelete(con,uName);

			int n=uDao.deleteUser(con,uName);			
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "注销用户成功");
				for(int i=0;i<v.size();i++) 
				{
					int aId=(int) v.get(i);
				    ResultSet rs2=aDao.checkMem(con, aId);
				    while(rs2.next())
				    {
					    mem=rs2.getInt("MemberNum");
					    aDao.updateMem(con, aId,mem-1 ); 
				    }
				}
				fillTable(Logon.admin.getId());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "注销用户失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "注销用户失败");
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
