package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.researchDao;
import dao.topicDao;
import model.Research;
import model.Topic;
import util.dbUtil;

public class addTopic extends JFrame {
	dbUtil dUtil=new dbUtil();
    topicDao tDao=new topicDao();

	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField contentTxt;
	private int aid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addTopic frame = new addTopic();
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
	public addTopic() {
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
		
		JLabel label = new JLabel("\u6DFB\u52A0\u5E16\u5B50");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label.setBounds(269, 335, 196, 35);
		contentPane.add(label);
		
		JButton b_exit = new JButton("\u9000\u56DE\u4E0A\u9875");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		
		JLabel label_1 = new JLabel("\u6807\u9898\uFF1A");
		label_1.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(253, 415, 130, 21);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u5185\u5BB9\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label_2.setBounds(253, 478, 130, 35);
		contentPane.add(label_2);
		
		nameTxt = new JTextField();
		nameTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		nameTxt.setBounds(378, 414, 404, 32);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);
		
		contentTxt = new JTextField();
		contentTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		contentTxt.setBounds(378, 481, 404, 144);
		contentPane.add(contentTxt);
		contentTxt.setColumns(10);
		
		JButton b_add = new JButton("\u53D1\u8868\u5E16\u5B50");
		b_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_addActionPerformed(e);
			}
		});
		b_add.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_add.setBounds(916, 336, 130, 35);
		contentPane.add(b_add);
	}
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		checkBBS cb=new checkBBS();
		cb.fillTable(aid);
		cb.setVisible(true);
	}
	
	private void b_addActionPerformed(ActionEvent e) {
		String name=nameTxt.getText();
		String content=contentTxt.getText();
		
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null, "标题不能为空");
			return;
		}
		Connection con=null;
		ResultSet n_rs;
		try {
			con=dUtil.getCon();	
			n_rs = tDao.checkName(con,name,aid);
			while(n_rs.next())
			{
				JOptionPane.showMessageDialog(null, "帖子标题不能重复");
				return;
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		if(content.equals(""))
		{
			JOptionPane.showMessageDialog(null, "内容不能为空");
			return;
		}
		int id=0;
		ResultSet rs;
		try {
			con=dUtil.getCon();	
			rs = tDao.maxId(con);
			while(rs.next())
			{
				id=rs.getInt("ID");
				
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		if(id==0)
			id=1;
		int y,m,d;  
        Calendar cal=Calendar.getInstance();    
        y=cal.get(Calendar.YEAR);    
        m=cal.get(Calendar.MONTH)+1;    
        d=cal.get(Calendar.DATE); 
        String time=y+"."+m+"."+d;
		Topic t=new Topic(id,name,content,time,Logon.user.getId(),aid);
		try
		{
			con=dUtil.getCon();		
			int n=tDao.add(con, t);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "帖子添加成功");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "帖子添加失败");
			}
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "帖子添加失败");
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
	
	public void aid(int a)
	{
		aid=a;
	}

}
