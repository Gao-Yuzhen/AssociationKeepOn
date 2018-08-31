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

import dao.topicDao;
import dao.userDao;
import model.Association;
import util.dbUtil;

public class checkBBS extends JFrame {

	dbUtil dUtil=new dbUtil();
	topicDao tDao=new topicDao();
	userDao uDao=new userDao();
	private JPanel contentPane;
	private JTable topicTable;
	private JTextField searchTxt;
	private String name;
	private int aid;
	private String aname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkBBS frame = new checkBBS();
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
	public checkBBS() {
		name="";
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
		
		JScrollPane topicList = new JScrollPane();
		topicList.setBounds(273, 375, 607, 289);
		contentPane.add(topicList);
		
		topicTable = new JTable();
		topicTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				topicTableMousePressed(e);
			}
		});
		topicTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u6807\u9898", "\u53D1\u5E16\u4EBA", "\u65F6\u95F4"
			}
		));
		topicTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		topicTable.getColumnModel().getColumn(1).setPreferredWidth(277);
		topicTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		topicTable.getColumnModel().getColumn(3).setPreferredWidth(130);
		topicTable.setRowHeight(30);
		topicTable.getTableHeader().setVisible(true); 
		topicTable.setBounds(273, 339, 607, 289);
		topicTable.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		topicTable.getTableHeader().setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		topicTable.setDefaultRenderer(Object.class, r);
		topicList.setViewportView(topicTable);
	
		
		JButton b_exit = new JButton("\u9000\u56DE\u4E0A\u9875");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		JButton b_add = new JButton("\u53D1\u8868\u5E16\u5B50");
		b_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_addActionPerformed(e);
			}
		});
		b_add.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_add.setBounds(916, 398, 130, 35);
		contentPane.add(b_add);
		
		searchTxt = new JTextField();
		searchTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		searchTxt.setBounds(416, 317, 285, 32);
		contentPane.add(searchTxt);
		searchTxt.setColumns(10);
		
		JLabel label = new JLabel("\u67E5\u8BE2\u5E16\u5B50");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label.setBounds(273, 315, 196, 35);
		contentPane.add(label);
		
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
		
		JButton b_comment = new JButton("\u67E5\u770B\u56DE\u5E16");
		b_comment.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_comment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_commentActionPerformed(e);
			}
		});
		b_comment.setBounds(916, 336, 130, 35);
		contentPane.add(b_comment);
		
	}
	
	public void fillTable(int aId)
	{
		aid=aId;
		int x=1;
		DefaultTableModel dtm=(DefaultTableModel) topicTable.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		try
		{
			con=dUtil.getCon();
			ResultSet rs=tDao.topicList(con, aId,searchTxt.getText());
			while(rs.next())
			{
				int uid=rs.getInt("User_ID");
				ResultSet rs2=uDao.checkStatus(con, uid);
				String uname="";
				while(rs2.next())
				{
					uname=rs2.getString("Name");
				}
				Vector v=new Vector();
				v.add(x);
				x++;
				v.add(rs.getString("Title"));
				v.add(uname);
				v.add(rs.getString("Time"));
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
		searchAssoci s_associ=new searchAssoci();
		Association a=new Association();
		a.setName(Main.sTxt());
		s_associ.fillTable(a);
		s_associ.setVisible(true);
	}
	
	private void topicTableMousePressed(MouseEvent e) {
		int row=topicTable.getSelectedRow();
		name= (String) topicTable.getValueAt(row, 1);
	}
	
	private void b_addActionPerformed(ActionEvent e) {
		this.dispose();
		addTopic at=new addTopic();
		at.aid(aid);
		at.setVisible(true);
	}
	
	private void b_searchActionPerformed(ActionEvent arg0) {
		fillTable(aid);
	}
	
	private void b_commentActionPerformed(ActionEvent e) {
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要查看的帖子");
			return;
		}
		this.dispose();
		addComment ac=new addComment();
		ac.topic_id(name,aid);
		ac.fillTable();
		ac.setVisible(true);
	}
	
	public void aname(String an)
	{
		aname=an;
	}
}
