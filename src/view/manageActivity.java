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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.activityDao;
import model.Activity;
import model.Association;
import util.dbUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class manageActivity extends JFrame {

	dbUtil dUtil=new dbUtil();
	activityDao acDao=new activityDao();
	private JPanel contentPane;
	private JTable activityTable;
	private String mouseName;
	private JTextField searchTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageActivity frame = new manageActivity();
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
	public manageActivity() {
		mouseName="";
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
		
		JScrollPane activityList = new JScrollPane();
		activityList.setBounds(273, 375, 607, 289);
		contentPane.add(activityList);
		
		activityTable = new JTable();
		activityTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				activityTableMousePressed(arg0);
			}
		});
		activityTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u6D3B\u52A8\u540D\u79F0", "\u65F6\u95F4", "\u72B6\u6001"
			}
		));
		activityTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		activityTable.getColumnModel().getColumn(1).setPreferredWidth(287);
		activityTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		activityTable.getColumnModel().getColumn(3).setPreferredWidth(140);
		activityTable.setRowHeight(30);
		activityTable.getTableHeader().setVisible(true); 
		activityTable.setBounds(273, 339, 607, 289);
		activityTable.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		activityTable.getTableHeader().setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		activityTable.setDefaultRenderer(Object.class, r);
		activityList.setViewportView(activityTable);
		
		JButton b_exit = new JButton("\u9000\u56DE\u9996\u9875");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		
		JButton b_add = new JButton("\u7533\u8BF7\u6D3B\u52A8");
		b_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_addActionPerformed(arg0);
			}
		});
		b_add.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_add.setBounds(916, 336, 130, 35);
		contentPane.add(b_add);
		
		JButton b_manage = new JButton("\u7BA1\u7406\u6D3B\u52A8");
		b_manage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_manageActionPerformed(e);
			}
		});
		b_manage.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_manage.setBounds(916, 398, 130, 35);
		contentPane.add(b_manage);
		
		JButton b_cancel = new JButton("\u53D6\u6D88\u6D3B\u52A8");
		b_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_cancelActionPerformed(e);
			}
		});
		b_cancel.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_cancel.setBounds(916, 460, 130, 35);
		contentPane.add(b_cancel);
		
		JLabel label = new JLabel("\u67E5\u8BE2\u6D3B\u52A8");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label.setBounds(273, 315, 196, 35);
		contentPane.add(label);
		
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
		
		fillTable(Logon.associ.getId());
	}
	
	public void fillTable(int aId)
	{
		int x=1;
		DefaultTableModel dtm=(DefaultTableModel) activityTable.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		try
		{
			con=dUtil.getCon();
			ResultSet rs=acDao.myAcList(con, aId,searchTxt.getText());
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(x);
				x++;
				v.add(rs.getString("Name"));
				v.add(rs.getString("Time"));
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
	
	private void b_addActionPerformed(ActionEvent e) {
		this.dispose();
		new addActivity().setVisible(true);
	}
	
	public void activityTableMousePressed(MouseEvent arg0) {
		int row=activityTable.getSelectedRow();
		mouseName= (String) activityTable.getValueAt(row, 1);
	}
	
	private void b_cancelActionPerformed(ActionEvent e) {
		if(mouseName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要取消的活动");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			int n=acDao.cancelAc(con, Logon.associ.getId(),mouseName);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "取消活动成功");
				fillTable(Logon.associ.getId());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "取消活动失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "取消活动失败");
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
	
	private void b_manageActionPerformed(ActionEvent e) {
		if(mouseName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要管理的活动");
			return;
		}
		this.dispose();
		String sTxt=mouseName;
		checkActivity checkAc=new checkActivity();
		Activity ac=new Activity();
		Connection con=null;
		try
		{
			con=dUtil.getCon();
			ResultSet rs=acDao.manageAc(con, Logon.associ.getId(),sTxt);
			while(rs.next())
			{
				ac.setId(rs.getInt("ID"));
				ac.setName(rs.getString("Name"));
				ac.setTime(rs.getString("Time"));
				ac.setIntroduce(rs.getString("Introduce"));
				ac.setPlace(rs.getString("Place"));
                ac.setTarget(rs.getString("Target"));
			}
		}
		catch(Exception e3)
		{
			e3.printStackTrace();
		}
		finally
		{
			try {
				dUtil.closeCon(con);
			} catch (Exception e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
		}
		checkAc.fillTxt(ac);
		checkAc.setVisible(true);
	}
	
	private void b_searchActionPerformed(ActionEvent arg0) {
		fillTable(Logon.associ.getId());
	}
		
}
