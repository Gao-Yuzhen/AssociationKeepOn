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

import dao.commentDao;
import dao.topicDao;
import dao.userDao;
import util.dbUtil;

public class checkComment extends JFrame {

	dbUtil dUtil=new dbUtil();
	commentDao cDao=new commentDao();
	topicDao tDao=new topicDao();
	userDao uDao=new userDao();
	private JPanel contentPane;
	private JTable commentTable;
	private int tid;
	private String name;
	private JLabel label_title;
	private JLabel label_content;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkComment frame = new checkComment();
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
	public checkComment() {
		name="";
		setResizable(false);
		setTitle("\u793E\u56E2KeepOn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1219, 866);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// ÉèÖÃFrame¾ÓÖÐÏÔÊ¾
		this.setLocationRelativeTo(null);
								
		//ÉèÖÃ±³¾°
		JPanel p = new JPanel();
		ImageIcon i = new ImageIcon("src/back.jpg");
		JLabel l = new JLabel(i);
		l.setBounds(0, 0, 1230, 830);
		getLayeredPane().add(l, new Integer(Integer.MIN_VALUE));
		p = (JPanel) this.getContentPane();
		p.setOpaque(false);
		p.setBounds(0, 0, 1219, 866);
		
		JScrollPane commentList = new JScrollPane();
		commentList.setBounds(273, 375, 607, 289);
		contentPane.add(commentList);
		
		commentTable = new JTable();
		commentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				commentTableMousePressed(e);
			}
		});
		commentTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56DE\u5E16", "\u56DE\u5E16\u4EBA", "\u65F6\u95F4"
			}
		));
		commentTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		commentTable.getColumnModel().getColumn(1).setPreferredWidth(277);
		commentTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		commentTable.getColumnModel().getColumn(3).setPreferredWidth(130);
		commentTable.setRowHeight(30);
		commentTable.getTableHeader().setVisible(true); 
		commentTable.setBounds(273, 339, 607, 289);
		commentTable.setFont(new Font("ººÒÇÄÏ¹¬Ìå¼ò", Font.PLAIN, 20));
		commentTable.getTableHeader().setFont(new Font("ººÒÇÄÏ¹¬Ìå¼ò", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		commentTable.setDefaultRenderer(Object.class, r);
		commentList.setViewportView(commentTable);
	
		
		JButton b_exit = new JButton("\u9000\u56DE\u4E0A\u9875");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("ººÒÇÄÏ¹¬Ìå¼ò", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		JButton b_out = new JButton("\u5220\u9664\u56DE\u5E16");
		b_out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_outActionPerformed(e);
			}
		});
		b_out.setFont(new Font("ººÒÇÄÏ¹¬Ìå¼ò", Font.PLAIN, 20));
		b_out.setBounds(916, 336, 130, 35);
		contentPane.add(b_out);
		
		JLabel label = new JLabel("\u6807\u9898");
		label.setFont(new Font("×Ö¿áÌÃº£²Ø¿¬Ìå", Font.PLAIN, 30));
		label.setBounds(295, 288, 196, 35);
		contentPane.add(label);
		
		label_title = new JLabel("");
		label_title.setFont(new Font("ººÒÇÄÏ¹¬Ìå¼ò", Font.PLAIN, 22));
		label_title.setBounds(369, 289, 286, 32);
		contentPane.add(label_title);
		
		JLabel label_1 = new JLabel("\u5185\u5BB9");
		label_1.setFont(new Font("×Ö¿áÌÃº£²Ø¿¬Ìå", Font.PLAIN, 30));
		label_1.setBounds(295, 338, 81, 21);
		contentPane.add(label_1);
		
		label_content = new JLabel("");
		label_content.setFont(new Font("ººÒÇÄÏ¹¬Ìå¼ò", Font.PLAIN, 22));
		label_content.setBounds(369, 333, 422, 32);
		contentPane.add(label_content);
		
	}
	
    public void fillTable()
	{
		int x=1;
		DefaultTableModel dtm=(DefaultTableModel) commentTable.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		try
		{
			con=dUtil.getCon();
			ResultSet rs=cDao.commentList(con, tid);
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
				v.add(rs.getString("Content"));
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
		new manageBBS().setVisible(true);
	}
	
	private void commentTableMousePressed(MouseEvent e) {
		int row=commentTable.getSelectedRow();
		name= (String) commentTable.getValueAt(row, 1);
	}
	
	private void b_outActionPerformed(ActionEvent e) {
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null, "ÇëÑ¡ÔñÒªÉ¾³ýµÄ»ØÌû");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			int n=cDao.delete(con, tid,name);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "É¾³ý»ØÌû³É¹¦");
				fillTable();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "É¾³ý»ØÌûÊ§°Ü");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "É¾³ý»ØÌûÊ§°Ü");
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
	
	public void topic_id(String n)
	{
	    label_title.setText(n);
	    Connection con =null;
		try {
			con=dUtil.getCon();
		    ResultSet rs=tDao.checkTid(con, n,Logon.associ.getId());
			while(rs.next())
			{
				tid=rs.getInt("ID");
				label_content.setText(rs.getString("Content"));
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
}
