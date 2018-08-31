package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

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
import model.Association;
import model.User;
import util.dbUtil;

public class modifyAssoci extends JFrame {

	dbUtil dUtil=new dbUtil();
	associDao aDao=new associDao();
	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField statusTxt;
	private JTextField timeTxt;
	private JTextField leaderTxt;
	private JTextField memberTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifyAssoci frame = new modifyAssoci();
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
	public modifyAssoci() {
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
		
		JLabel label_1 = new JLabel("\u72B6\u6001\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label_1.setBounds(376, 407, 100, 41);
		contentPane.add(label_1);
		
		JLabel lblEmail = new JLabel("\u6210\u7ACB\u65F6\u95F4\uFF1A");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		lblEmail.setBounds(351, 463, 125, 40);
		contentPane.add(lblEmail);
		
		JLabel label_2 = new JLabel("\u4F1A\u957F\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label_2.setBounds(351, 519, 125, 35);
		contentPane.add(label_2);
			
		JLabel label_3 = new JLabel("\u57FA\u672C\u4FE1\u606F");
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
		
		JLabel label_4 = new JLabel("\u6210\u5458\u4EBA\u6570\uFF1A");
		label_4.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(351, 569, 125, 41);
		contentPane.add(label_4);
		
		nameTxt = new JTextField();
		nameTxt.setEditable(false);
		nameTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		nameTxt.setBounds(479, 357, 280, 35);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);
		nameTxt.setText(Logon.associ.getName());
		
		statusTxt = new JTextField();
		statusTxt.setEditable(false);
		statusTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		statusTxt.setBounds(479, 415, 125, 35);
		contentPane.add(statusTxt);
		statusTxt.setColumns(10);
		statusTxt.setText(Logon.associ.getStatus());
		
		timeTxt = new JTextField();
		timeTxt.setEditable(false);
		timeTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		timeTxt.setBounds(479, 470, 176, 35);
		contentPane.add(timeTxt);
		timeTxt.setColumns(10);
		timeTxt.setText(Logon.associ.getTime());
		
		leaderTxt = new JTextField();
		leaderTxt.setEditable(false);
		leaderTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		leaderTxt.setBounds(479, 523, 176, 35);
		contentPane.add(leaderTxt);
		leaderTxt.setColumns(10);
		leaderTxt.setText(Logon.associ.getLeader());
		
		memberTxt = new JTextField();
		memberTxt.setEditable(false);
		memberTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 24));
		memberTxt.setBounds(479, 577, 125, 35);
		contentPane.add(memberTxt);
		memberTxt.setColumns(10);
		memberTxt.setText(Logon.associ.getMemberNum()+"");
		
		JButton b_editName = new JButton("");
		b_editName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_editNameActionPerformed(e);
			}
		});
		b_editName.setBounds(770, 357, 33, 35);
		contentPane.add(b_editName);
		ImageIcon e_name = new ImageIcon("src/icon/edit.png");
		b_editName.setIcon(e_name);
		
		JButton b_editLeader = new JButton("");
		b_editLeader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_editLeaderActionPerformed(e);
			}
		});
		b_editLeader.setBounds(667, 523, 33, 35);
		contentPane.add(b_editLeader);
		ImageIcon e_leader = new ImageIcon("src/icon/edit.png");
		b_editLeader.setIcon(e_leader);
		
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
		new A_main().setVisible(true);
	}
	
	private void b_editNameActionPerformed(ActionEvent e) {
		nameTxt.setEditable(true);
	}
	
	private void b_editLeaderActionPerformed(ActionEvent e) {
		leaderTxt.setEditable(true);
	}
	
	private void b_saveActionPerformed(ActionEvent e) {
		String name=nameTxt.getText();
		String leader=leaderTxt.getText();
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null, "社团名不能为空");
			return;
		}
		if(leader.equals(""))
		{
			JOptionPane.showMessageDialog(null, "会长不能为空");
			return;
		}
		Connection con=null;
		try
		{
			con=dUtil.getCon();
			Association a=new Association();
			a.setName(name);
			a.setLeader(leader);
			a.setId(Logon.associ.getId());
			int n=aDao.update(con, a);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "修改成功");
				Logon.associ.setName(name);
				Logon.associ.setLeader(leader);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "修改失败");
				nameTxt.setText(Logon.associ.getName());
				leaderTxt.setText(Logon.associ.getLeader());
			}
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败");
			nameTxt.setText(Logon.associ.getName());
			leaderTxt.setText(Logon.associ.getLeader());
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
		nameTxt.setEditable(false);
		leaderTxt.setEditable(false);
		
	}
}
