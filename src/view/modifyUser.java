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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.userDao;
import model.User;
import util.dbUtil;

public class modifyUser extends JFrame {

	dbUtil dUtil=new dbUtil();
	userDao uDao=new userDao();
	private JPanel contentPane;
	private JTextField userNameTxt;
	private JTextField sexTxt;
	private JTextField emailTxt;
	private JTextField birthTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifyUser frame = new modifyUser();
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
	public modifyUser() {
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
		label.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label.setBounds(376, 351, 100, 41);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_1.setBounds(376, 414, 100, 41);
		contentPane.add(label_1);
		
		JLabel lblEmail = new JLabel("\u5B66\u53F7\uFF1A");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		lblEmail.setBounds(376, 470, 100, 47);
		contentPane.add(lblEmail);
		
		JLabel label_2 = new JLabel("\u751F\u65E5\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_2.setBounds(376, 540, 100, 41);
		contentPane.add(label_2);
		
		userNameTxt = new JTextField();
		userNameTxt.setBounds(489, 356, 212, 34);
		contentPane.add(userNameTxt);
		userNameTxt.setColumns(10);
		userNameTxt.setText(Logon.user.getName());
		userNameTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		
		sexTxt = new JTextField();
		sexTxt.setBounds(489, 421, 72, 34);
		contentPane.add(sexTxt);
		sexTxt.setColumns(10);
		sexTxt.setText(Logon.user.getSex());
		sexTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		
		emailTxt = new JTextField();
		emailTxt.setBounds(489, 483, 330, 34);
		contentPane.add(emailTxt);
		emailTxt.setColumns(10);
		emailTxt.setText(Logon.user.getEmail());
		emailTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		
		birthTxt = new JTextField();
		birthTxt.setBounds(489, 547, 330, 34);
		contentPane.add(birthTxt);
		birthTxt.setColumns(10);
		birthTxt.setText(Logon.user.getBirthday());
		birthTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		

		JLabel label_3 = new JLabel("\u7F16\u8F91\u4E2A\u4EBA\u8D44\u6599");
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
		
		JButton b_save = new JButton("\u4FDD\u5B58\u4FEE\u6539");
		b_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_saveActionPerformed(e);
			}
		});
		b_save.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_save.setBounds(916, 336, 130, 35);
		contentPane.add(b_save);
		
	}
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new Main().setVisible(true);
	}
	
	private void b_saveActionPerformed(ActionEvent e) {
		String name=userNameTxt.getText();
		String sex=sexTxt.getText();
		String birth=birthTxt.getText();
		String email=emailTxt.getText();
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			return;
		}
		if(email.equals(""))
		{
			JOptionPane.showMessageDialog(null, "学号不能为空");
			return;
		}
		Connection con=null;
		try
		{
			con=dUtil.getCon();
			User u=new User(name,email,sex,birth);
			u.setId(Logon.user.getId());
			int n=uDao.update(con, u);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "修改成功");
				Logon.user.setName(name);
				Logon.user.setBirthday(birth);
				Logon.user.setEmail(email);
				Logon.user.setSex(sex);
			}
			else
				JOptionPane.showMessageDialog(null, "修改失败");
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败");
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
