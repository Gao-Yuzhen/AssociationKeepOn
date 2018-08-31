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
import javax.swing.border.EmptyBorder;

import dao.userDao;
import model.Activity;
import model.User;
import util.dbUtil;
import javax.swing.JPasswordField;
import javax.swing.Icon;

public class RegisterUser extends JFrame {

	private JPanel contentPane;
	dbUtil dUtil=new dbUtil();
	userDao uDao=new userDao();
	private JTextField userNameTxt;
	private JTextField sexTxt;
	private JTextField emailTxt;
	private JTextField birthTxt;
	private JPasswordField passwordTxt;
	private JPasswordField password2Txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUser frame = new RegisterUser();
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
	public RegisterUser() {
		setTitle("\u793E\u56E2KeepOn");
		setResizable(false);
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
		label.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label.setBounds(376, 351, 100, 41);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label_1.setBounds(376, 407, 100, 34);
		contentPane.add(label_1);
		
		JLabel lblEmail = new JLabel("\u5B66\u53F7\uFF1A");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		lblEmail.setBounds(396, 458, 80, 41);
		contentPane.add(lblEmail);
		
		JLabel label_2 = new JLabel("\u751F\u65E5\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label_2.setBounds(376, 510, 100, 41);
		contentPane.add(label_2);
		
		userNameTxt = new JTextField();
		userNameTxt.setBounds(489, 356, 212, 32);
		contentPane.add(userNameTxt);
		userNameTxt.setColumns(10);
		userNameTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		
		sexTxt = new JTextField();
		sexTxt.setBounds(489, 411, 72, 32);
		contentPane.add(sexTxt);
		sexTxt.setColumns(10);
		sexTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		
		emailTxt = new JTextField();
		emailTxt.setBounds(489, 465, 330, 32);
		contentPane.add(emailTxt);
		emailTxt.setColumns(10);
		emailTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		
		birthTxt = new JTextField();
		birthTxt.setBounds(489, 519, 330, 32);
		contentPane.add(birthTxt);
		birthTxt.setColumns(10);
		birthTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		

		JLabel label_3 = new JLabel("\u6CE8\u518C\u65B0\u7528\u6237");
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
		
		JButton b_save = new JButton("\u5B8C\u6210\u6CE8\u518C");
		b_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_saveActionPerformed(e);
			}
		});
		b_save.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_save.setBounds(916, 336, 130, 35);
		contentPane.add(b_save);
		
		JLabel label_4 = new JLabel("\u5BC6\u7801\uFF1A");
		label_4.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(395, 566, 81, 41);
		contentPane.add(label_4);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("宋体", Font.PLAIN, 22));
		passwordTxt.setBounds(489, 574, 212, 32);
		contentPane.add(passwordTxt);
		
		JLabel label_5 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label_5.setBounds(356, 624, 120, 34);
		contentPane.add(label_5);
		
		password2Txt = new JPasswordField();
		password2Txt.setFont(new Font("宋体", Font.PLAIN, 22));
		password2Txt.setBounds(489, 629, 212, 32);
		contentPane.add(password2Txt);
		
		ImageIcon s = new ImageIcon("src/icon/star.png");
		JLabel star = new JLabel(s);		
		star.setBounds(356, 363, 16, 16);
		contentPane.add(star);
		
		JLabel star2 = new JLabel(s);
		star2.setBounds(385, 471, 16, 16);
		contentPane.add(star2);
		
		JLabel star3 = new JLabel(s);
		star3.setBounds(384, 579, 16, 16);
		contentPane.add(star3);
		
		JLabel star4 = new JLabel(s);
		star4.setBounds(335, 634, 16, 16);
		contentPane.add(star4);
	}
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new Logon().setVisible(true);
	}
	
	private void b_saveActionPerformed(ActionEvent e) {
		String name=userNameTxt.getText();
		String password=new String(passwordTxt.getPassword());
		String password2=new String(password2Txt.getPassword());
		String sex=sexTxt.getText();
		String birth=birthTxt.getText();
		String email=emailTxt.getText();
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			return;
		}
		Connection con=null;
		ResultSet n_rs;
		try {
			con=dUtil.getCon();	
			n_rs = uDao.checkName(con,name);
			while(n_rs.next())
			{
				JOptionPane.showMessageDialog(null, "用户名已被占用");
				return;
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		if(email.equals(""))
		{
			JOptionPane.showMessageDialog(null, "学号不能为空");
			return;
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
		int id=0;
		ResultSet rs;
		try {
			con=dUtil.getCon();	
			rs = uDao.maxId(con);
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
		User user=new User(id,name,password,email,sex,birth);
		try
		{
			con=dUtil.getCon();		
			int n=uDao.add(con, user);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "注册新用户成功");
				this.dispose();
				new Logon().setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "注册新用户失败");
			}
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "注册新用户失败");
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
