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

import dao.placeDao;
import dao.volunteerDao;
import model.Place;
import model.Volunteer;
import util.dbUtil;

public class addPlace extends JFrame {

	dbUtil dUtil=new dbUtil();
    placeDao pDao=new placeDao();

	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField capacityTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addPlace frame = new addPlace();
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
	public addPlace() {
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
		
		JLabel label = new JLabel("\u6DFB\u52A0\u5730\u70B9");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label.setBounds(254, 355, 196, 35);
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
		
		JLabel label_1 = new JLabel("\u5730\u70B9\u540D\u79F0\uFF1A");
		label_1.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(254, 456, 130, 21);
		contentPane.add(label_1);
		
		JLabel label_3 = new JLabel("\u4EBA\u5458\u5BB9\u91CF\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_3.setBounds(254, 544, 130, 23);
		contentPane.add(label_3);
		
		nameTxt = new JTextField();
		nameTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		nameTxt.setBounds(380, 450, 404, 35);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);
		
		capacityTxt = new JTextField();
		capacityTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		capacityTxt.setBounds(379, 541, 137, 35);
		contentPane.add(capacityTxt);
		capacityTxt.setColumns(10);
		
		JButton b_add = new JButton("\u63D0\u4EA4\u5730\u70B9");
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
		new adminPlace().setVisible(true);
	}
	
	private void b_addActionPerformed(ActionEvent e) {
		String name=nameTxt.getText();
		String capacity=capacityTxt.getText();
		
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null, "地点名称不能为空");
			return;
		}
		Connection con=null;
		ResultSet n_rs;
		try {
			con=dUtil.getCon();	
			n_rs = pDao.checkName(con,name);
			while(n_rs.next())
			{
				JOptionPane.showMessageDialog(null, "地点名称不能重复");
				return;
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		if(capacity.equals(""))
		{
			JOptionPane.showMessageDialog(null, "人员容量不能为空");
			return;
		}

		Place p=new Place(name,"可以使用",capacity,Logon.admin.getId());
		try
		{
			con=dUtil.getCon();		
			int n=pDao.add(con, p);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "地点添加成功");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "地点添加失败");
			}
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "地点添加失败");
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
