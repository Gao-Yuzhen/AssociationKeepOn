package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import dao.adminDao;
import dao.associDao;
import dao.userDao;
import model.Admin;
import model.Association;
import model.User;
import util.StringUtil;
import util.dbUtil;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Logon extends JFrame {
	dbUtil dUtil = new dbUtil();
	userDao uDao = new userDao();
	associDao aDao=new associDao();
	adminDao adDao=new adminDao();

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	private int radio=0;
	public static User user;
	public static Association associ;
	public static Admin admin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logon frame = new Logon();
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
	public Logon() {
		
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
    	
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		label.setBounds(463, 398, 95, 29);
		contentPane.add(label);
		
		JButton b_logon = new JButton("\u767B\u5F55");
		b_logon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_logonActionPerformed(arg0);
			}
		});
		b_logon.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		b_logon.setBounds(458, 605, 113, 29);
		contentPane.add(b_logon);
		
		JLabel label_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		label_1.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		label_1.setBounds(463, 457, 95, 21);
		contentPane.add(label_1);
		
		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 19));
		userNameTxt.setBounds(557, 399, 174, 27);
		contentPane.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		JRadioButton radio_admin = new JRadioButton("\u7BA1\u7406\u5458");
		radio_admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r_adminActionPerformed(e);
			}
		});
		radio_admin.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		radio_admin.setBounds(458, 518, 93, 29);
		contentPane.add(radio_admin);
		
		
		JRadioButton radio_associ = new JRadioButton("\u793E\u56E2");
		radio_associ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r_associActionPerformed(e);
			}
		});
		radio_associ.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		radio_associ.setBounds(576, 518, 73, 29);
		contentPane.add(radio_associ);
		
		JRadioButton radio_student = new JRadioButton("\u5B66\u751F");
		radio_student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r_studentActionPerformed(e);
			}
		});
		radio_student.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		radio_student.setBounds(680, 518, 73, 29);
		contentPane.add(radio_student);
		
		JButton b_register = new JButton("\u6CE8\u518C");
		b_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_registerActionPerformed(arg0);
			}
		});
		b_register.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		b_register.setBounds(634, 605, 113, 29);
		contentPane.add(b_register);
		
		JLabel lblkeepon = new JLabel("\u767B\u5F55\u793E\u56E2KeepOn");
		lblkeepon.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 40));
		lblkeepon.setBounds(458, 257, 371, 74);
		contentPane.add(lblkeepon);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("宋体", Font.PLAIN, 19));
		passwordTxt.setBounds(557, 454, 174, 27);
		contentPane.add(passwordTxt);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radio_admin);
		group.add(radio_associ);
		group.add(radio_student);
	}
	
	private void b_logonActionPerformed(ActionEvent arg0) {
		String userName = userNameTxt.getText();
		String password = new String(passwordTxt.getPassword());
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		if(radio==0)
	    {
			JOptionPane.showMessageDialog(null, "请选择登录方式");
			return;
		}
		Connection con=null;
		try {
			con=(Connection) dUtil.getCon();
			if(radio==1)
			{
				Admin ad=new Admin(userName, password);
				Admin currentAd = adDao.login(con, ad);
				if (currentAd != null) {
					admin=new Admin();
					admin.setName(currentAd.getName());
					admin.setId(currentAd.getId());
					this.dispose();new Admin_main().setVisible(true);
					
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误");
				}
			}
			if(radio==2)
			{
				Association a = new Association(userName, password);
				Association currentA = aDao.login(con, a);

				if (currentA != null) {
					associ=new Association();
					associ.setName(currentA.getName());
					associ.setStatus(currentA.getStatus());
					associ.setTime(currentA.getTime());
					associ.setMemberNum(currentA.getMemberNum());
					associ.setFinance(currentA.getFinance());
					associ.setId(currentA.getId());
					associ.setLeader(currentA.getLeader());
					this.dispose();new A_main().setVisible(true);
					
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误");
				}
			}
			if(radio==3)
			{
				User u=new User(userName,password);
				User currentUser = uDao.login(con, u);
				
				if (currentUser != null) {
					user=new User();
					user.setName(currentUser.getName());
					user.setPassword(currentUser.getPassword());
					user.setSex(currentUser.getSex());
					user.setEmail(currentUser.getEmail());
					user.setBirthday(currentUser.getBirthday());
					user.setId(currentUser.getId());
					this.dispose();new Main().setVisible(true);
					
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "登录失败");
		} finally{
			try {
				dUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	private void b_registerActionPerformed(ActionEvent arg0) {
		if(radio==0)
	    {
			JOptionPane.showMessageDialog(null, "请选择注册方式");
			return;
		}
		if(radio==1)
	    {
			JOptionPane.showMessageDialog(null, "不能注册管理员");
			return;
		}
		if(radio==2)
		{
			this.dispose();
			new RegisterAssoci().setVisible(true);
		}
		if(radio==3)
		{
			this.dispose();
			new RegisterUser().setVisible(true);
		}
	}
	
	private void r_studentActionPerformed(ActionEvent e)
	{
		radio=3;
	}
	
	private void r_associActionPerformed(ActionEvent e)
	{
		radio=2;
	}
	
	private void r_adminActionPerformed(ActionEvent e)
	{
		radio=1;
	}
	
	public int getUserId()
	{
		return user.getId();
	}
}
