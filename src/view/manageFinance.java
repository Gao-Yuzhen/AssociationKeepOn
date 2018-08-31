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
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.activityDao;
import dao.associDao;
import dao.financeDao;
import model.Activity;
import model.Finance;
import util.dbUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class manageFinance extends JFrame {
	
	dbUtil dUtil=new dbUtil();
	financeDao fDao=new financeDao();
	associDao aDao=new associDao();
	private JPanel contentPane;
	private JTable financeTable;
	private String mouseName;
	private JTextField searchTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageFinance frame = new manageFinance();
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
	public manageFinance() {
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
		
		JScrollPane financeList = new JScrollPane();
		financeList.setBounds(273, 375, 607, 289);
		contentPane.add(financeList);
		
		financeTable = new JTable();
		financeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				financeTableMousePressed(arg0);
			}
		});
		financeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u6B3E\u9879\u540D\u79F0", "\u6536\u5165", "\u652F\u51FA", "\u603B\u989D"
			}
		));
		financeTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		financeTable.getColumnModel().getColumn(1).setPreferredWidth(207);
		financeTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		financeTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		financeTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		financeTable.setBounds(273, 375, 607, 289);
		financeTable.setRowHeight(30);
		financeTable.getTableHeader().setVisible(true); 
		financeTable.setBounds(273, 339, 607, 289);
		financeTable.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		financeTable.getTableHeader().setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		financeTable.setDefaultRenderer(Object.class, r);
		financeList.setViewportView(financeTable);
		
		JButton b_exit = new JButton("\u9000\u56DE\u9996\u9875");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		
		JLabel label = new JLabel("\u67E5\u8BE2\u6B3E\u9879");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label.setBounds(273, 315, 196, 35);
		contentPane.add(label);
		
		JButton b_add = new JButton("\u6DFB\u52A0\u6B3E\u9879");
		b_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_addActionPerformed(arg0);
			}
		});
		b_add.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_add.setBounds(916, 336, 130, 35);
		contentPane.add(b_add);
		
		JButton b_modify = new JButton("\u4FEE\u6539\u6B3E\u9879");
		b_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_modifyActionPerformed(e);
			}
		});
		b_modify.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_modify.setBounds(916, 398, 130, 35);
		contentPane.add(b_modify);
		
		JButton b_cancel = new JButton("\u5220\u9664\u6B3E\u9879");
		b_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_cancelActionPerformed(e);
			}
		});
		b_cancel.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_cancel.setBounds(916, 460, 130, 35);
		contentPane.add(b_cancel);
		
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
		DefaultTableModel dtm=(DefaultTableModel) financeTable.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		try
		{
			con=dUtil.getCon();
			ResultSet rs=fDao.fList(con, aId,searchTxt.getText());
			int total=0;
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(x);
				x++;
				v.add(rs.getString("Name"));
				int in=rs.getInt("Income");
				int out=rs.getInt("Expense");
				v.add(in);
				v.add(out);
				total=total+in-out;
				v.add(total);
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
		new addFinance().setVisible(true);
	}
	
	public void financeTableMousePressed(MouseEvent arg0) {
		int row=financeTable.getSelectedRow();
		mouseName= (String) financeTable.getValueAt(row, 1);
	}
	
	private void b_cancelActionPerformed(ActionEvent e) {
		if(mouseName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要删除的款项");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			ResultSet rs=fDao.fTotal(con,mouseName);
			int m=0;
			while(rs.next())
			{
				m=rs.getInt("Total");
			}
			int n=fDao.cancelFi(con, Logon.associ.getId(),mouseName);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "删除款项成功");
				fillTable(Logon.associ.getId());
				Logon.associ.setFinance(Logon.associ.getFinance()-m);
				aDao.updateFin(con, Logon.associ.getId(), Logon.associ.getFinance());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "删除款项失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "删除款项失败");
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
	
	private void b_modifyActionPerformed(ActionEvent e) {
		if(mouseName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要修改的款项");
			return;
		}
		this.dispose();
		String sTxt=mouseName;
		modifyFinance modifyFi=new modifyFinance();
		Finance fi=new Finance();
		Connection con=null;
		try
		{
			con=dUtil.getCon();
			ResultSet rs=fDao.modifyFi(con, Logon.associ.getId(),sTxt);
			while(rs.next())
			{
				fi.setId(rs.getInt("ID"));
				fi.setName(rs.getString("Name"));
				fi.setIncome(rs.getInt("Income"));
				fi.setExpense(rs.getInt("Expense"));
				fi.setTotal(rs.getInt("Total"));
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
		modifyFi.fillTxt(fi);
		modifyFi.setVisible(true);
	}
	
	private void b_searchActionPerformed(ActionEvent arg0) {
		fillTable(Logon.associ.getId());
	}
}
