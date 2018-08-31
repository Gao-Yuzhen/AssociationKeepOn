package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import dao.associDao;
import dao.userDao;
import model.Association;
import model.User;
import util.dbUtil;
import javax.swing.JPasswordField;
import javax.swing.Icon;

public class RegisterAssoci extends JFrame {

	dbUtil dUtil=new dbUtil();
	associDao aDao=new associDao();
	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField leaderTxt;
	private JPasswordField passwordTxt;
	private JPasswordField password2Txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterAssoci frame = new RegisterAssoci();
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
	public RegisterAssoci() {
		setResizable(false);
		setTitle("\u793E\u56E2KeepOn");
		
		//改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) 
		{
		    Object key = keys.nextElement();
			Object value = UIManager.get(key);
		    if (value instanceof javax.swing.plaf.FontUIResource) 
			UIManager.put(key, font);
				
		}
		
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
		
		JLabel label = new JLabel("\u793E\u56E2\u540D\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label.setBounds(300, 351, 176, 41);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label_1.setBounds(376, 407, 100, 41);
		contentPane.add(label_1);
		
		JLabel lblEmail = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		lblEmail.setBounds(351, 463, 125, 40);
		contentPane.add(lblEmail);
		
		JLabel label_2 = new JLabel("\u4F1A\u957F\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label_2.setBounds(351, 519, 125, 35);
		contentPane.add(label_2);
			
		JLabel label_3 = new JLabel("\u6CE8\u518C\u65B0\u793E\u56E2");
		label_3.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label_3.setBounds(376, 270, 337, 47);
		contentPane.add(label_3);
		
		JButton b_exit = new JButton("\u9000\u56DE\u9996\u9875");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		
		nameTxt = new JTextField();
		nameTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		nameTxt.setBounds(479, 357, 280, 33);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);
		
		leaderTxt = new JTextField();
		leaderTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		leaderTxt.setBounds(479, 525, 176, 33);
		contentPane.add(leaderTxt);
		leaderTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("宋体", Font.PLAIN, 22));
		passwordTxt.setBounds(479, 415, 176, 33);
		contentPane.add(passwordTxt);
		
		password2Txt = new JPasswordField();
		password2Txt.setFont(new Font("宋体", Font.PLAIN, 22));
		password2Txt.setBounds(479, 471, 176, 33);
		contentPane.add(password2Txt);
		
		JButton b_save = new JButton("\u5B8C\u6210\u6CE8\u518C");
		b_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_saveActionPerformed(e);
			}
		});
		b_save.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_save.setBounds(916, 336, 130, 35);
		contentPane.add(b_save);
		
		ImageIcon s = new ImageIcon("src/icon/star.png");
		JLabel star1 = new JLabel(s);
		star1.setBounds(359, 363, 16, 16);
		contentPane.add(star1);
		
		JLabel star2 = new JLabel(s);
		star2.setBounds(382, 420, 16, 16);
		contentPane.add(star2);
		
		JLabel star3 = new JLabel(s);
		star3.setBounds(333, 476, 16, 16);
		contentPane.add(star3);
		
		JLabel star4 = new JLabel(s);
		star4.setBounds(383, 529, 16, 16);
		contentPane.add(star4);
	}
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new Logon().setVisible(true);
	}
	
	private void b_saveActionPerformed(ActionEvent e) {
		String name=nameTxt.getText();
		String password=new String(passwordTxt.getPassword());
		String password2=new String(password2Txt.getPassword());
		String leader=leaderTxt.getText();
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null, "社团名不能为空");
			return;
		}
		Connection con=null;
		ResultSet n_rs;
		try {
			con=dUtil.getCon();	
			n_rs = aDao.checkName(con,name);
			while(n_rs.next())
			{
				JOptionPane.showMessageDialog(null, "社团名已被占用");
				return;
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		if(password.equals(""))
		{
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		if(password2.equals(""))
		{
			JOptionPane.showMessageDialog(null, "确认密码不能为空");
			return;
		}
		if(!password.equals(password2))
		{
			JOptionPane.showMessageDialog(null, "两次输入的密码不一致");
			return;
		}
		if(leader.equals(""))
		{
			JOptionPane.showMessageDialog(null, "会长不能为空");
			return;
		}
		int id=0;
		ResultSet rs;
		try {
			con=dUtil.getCon();	
			rs = aDao.maxId(con);
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
		Association a=new Association(id,name,password,leader);
		try
		{
			con=dUtil.getCon();		
			int n=aDao.add(con, a);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "注册新社团成功，请等待审核");
				this.dispose();
				new Logon().setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "注册新社团失败");
			}
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "注册新社团失败");
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
