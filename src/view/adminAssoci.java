package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.activityDao;
import dao.associDao;
import dao.userAssociDao;
import model.Association;
import util.dbUtil;

import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class adminAssoci extends JFrame {

	dbUtil dUtil=new dbUtil();
	activityDao acDao=new activityDao();
	associDao aDao=new associDao();
	userAssociDao uaDao=new userAssociDao();
	private JPanel contentPane;
	private JScrollPane asList;
	private JTable asTable;
	private int id;
	private JTextField searchTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminAssoci frame = new adminAssoci();
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
	public adminAssoci() {
		id=0;
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
		
		asList = new JScrollPane();
		asList.setBounds(273, 375, 607, 289);
		contentPane.add(asList);
		
		asTable = new JTable();
		asTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				asTableMousePressed(arg0);
			}
		});
		asTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u793E\u56E2\u540D\u79F0", "\u4F1A\u957F", "\u72B6\u6001"
			}
		));
		asTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		asTable.getColumnModel().getColumn(1).setPreferredWidth(257);
		asTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		asTable.getColumnModel().getColumn(3).setPreferredWidth(150);
	    asTable.setRowHeight(30);
		asTable.getTableHeader().setVisible(true); 
		asTable.setBounds(273, 339, 607, 289);
		asTable.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		asTable.getTableHeader().setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		asTable.setDefaultRenderer(Object.class, r);
		asList.setViewportView(asTable);
		
		JButton b_in = new JButton("\u901A\u8FC7\u7533\u8BF7");
		b_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_inActionPerformed(arg0);
			}
		});
		b_in.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_in.setBounds(916, 336, 130, 35);
		contentPane.add(b_in);
		
		JButton b_stop = new JButton("\u5C01\u9501\u793E\u56E2");
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
		
		JLabel label = new JLabel("\u67E5\u8BE2\u793E\u56E2");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label.setBounds(273, 315, 196, 35);
		contentPane.add(label);
		
		fillTable(Logon.admin.getId());
	}

	public void fillTable(int adId)
	{
		DefaultTableModel dtm=(DefaultTableModel) asTable.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		try
		{
			con=dUtil.getCon();
			ResultSet rs=aDao.asList(con, adId,searchTxt.getText());
			int x=1;
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(x);
				x++;
				v.add(rs.getString("Name"));
				v.add(rs.getString("Leader"));
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
	
	private void asTableMousePressed(MouseEvent e) {
		int row=asTable.getSelectedRow();
		id= (int) asTable.getValueAt(row, 0);
	}
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new Admin_main().setVisible(true);
	}
	
	private void b_inActionPerformed(ActionEvent e) {
		if(id==0)
		{
			JOptionPane.showMessageDialog(null, "请选择要通过申请的社团");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			ResultSet rs=aDao.checkStatus(con, id);
			while(rs.next())
			{
				if(rs.getString("Status").equals("正式社团"))
				{
					JOptionPane.showMessageDialog(null, "该社团已通过申请");
					return;
				}
					
			}
			int n=aDao.updateStatus(con, "正式社团",id);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "申请通过成功");
				int y,m,d;  
                Calendar cal=Calendar.getInstance();    
                y=cal.get(Calendar.YEAR);    
                m=cal.get(Calendar.MONTH)+1;    
                d=cal.get(Calendar.DATE); 
                String time=y+"."+m+"."+d;
                aDao.updateTime(con, time, id);
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
		if(id==0)
		{
			JOptionPane.showMessageDialog(null, "请选择要封锁的社团");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			ResultSet rs=aDao.checkStatus(con, id);
			while(rs.next())
			{
				if(rs.getString("Status").equals("申请待审核"))
				{
					JOptionPane.showMessageDialog(null, "该社团还未成立");
					return;
				}
					
			}
			int n=aDao.updateStatus(con, "申请待审核",id);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "封锁社团成功");
                aDao.updateTime(con, null, id);
                uaDao.resetMem(con, id);
                acDao.resetAc(con, id);
                aDao.updateMem(con, id, 0);
                //aDao.updateFin(con, id, 0);
				fillTable(Logon.admin.getId());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "封锁社团失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "封锁社团失败");
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
