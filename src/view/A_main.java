package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class A_main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A_main frame = new A_main();
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
	public A_main() {
		setTitle("\u793E\u56E2KeepOn");
		setResizable(false);
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
		contentPane.setLayout(null);
		
		JLabel a_name = new JLabel("\u540D\u79F0\uFF1A");
		a_name.setHorizontalAlignment(SwingConstants.RIGHT);
		a_name.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		a_name.setBounds(296, 355, 80, 37);
		contentPane.add(a_name);
		
		JLabel a_status = new JLabel("\u72B6\u6001\uFF1A");
		a_status.setHorizontalAlignment(SwingConstants.RIGHT);
		a_status.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		a_status.setBounds(296, 409, 80, 37);
		contentPane.add(a_status);
		
		JLabel a_time = new JLabel("\u6210\u7ACB\u65F6\u95F4\uFF1A");
		a_time.setHorizontalAlignment(SwingConstants.RIGHT);
		a_time.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		a_time.setBounds(235, 461, 141, 37);
		contentPane.add(a_time);
		
		JLabel label = new JLabel("\u6210\u5458\u4EBA\u6570\uFF1A");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label.setBounds(235, 513, 141, 37);
		contentPane.add(label);
		
		JButton b_modify = new JButton("\u7BA1\u7406\u57FA\u672C\u4FE1\u606F");
		b_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_modifyActionPerformed(e);
			}
		});
		b_modify.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_modify.setBounds(738, 383, 165, 37);
		contentPane.add(b_modify);
		
		JButton b_member = new JButton("\u7BA1\u7406\u6210\u5458");
		b_member.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_memberActionPerformed(e);
			}
		});
		b_member.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_member.setBounds(738, 439, 165, 37);
		contentPane.add(b_member);
		
		JButton b_activity = new JButton("\u7BA1\u7406\u6D3B\u52A8\u9879\u76EE");
		b_activity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_activityActionPerformed(e);
			}
		});
		b_activity.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_activity.setBounds(738, 491, 165, 37);
		contentPane.add(b_activity);
		
		JButton b_finance = new JButton("\u7BA1\u7406\u8D22\u52A1");
		b_finance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_financeActionPerformed(arg0);
			}
		});
		b_finance.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_finance.setBounds(738, 543, 165, 37);
		contentPane.add(b_finance);
		
		JLabel label_1 = new JLabel("\u8D22\u52A1\u91D1\u989D\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_1.setBounds(235, 565, 141, 37);
		contentPane.add(label_1);
		
		JButton b_volunteer = new JButton("\u5FD7\u613F\u670D\u52A1");
		b_volunteer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_volunteerActionPerformed(e);
			}
		});
		b_volunteer.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_volunteer.setBounds(944, 543, 165, 37);
		contentPane.add(b_volunteer);
		
		JButton b_vote = new JButton("\u7F51\u4E0A\u6295\u7968");
		b_vote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_voteActionPerformed(e);
			}
		});
		b_vote.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_vote.setBounds(944, 383, 165, 35);
		contentPane.add(b_vote);
		
		JButton b_search = new JButton("\u7F51\u4E0A\u8C03\u67E5");
		b_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_searchActionPerformed(e);
			}
		});
		b_search.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_search.setBounds(944, 439, 165, 37);
		contentPane.add(b_search);
		
		JButton b_bbs = new JButton("\u793E\u56E2BBS");
		b_bbs.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_bbs.setBounds(944, 491, 165, 37);
		contentPane.add(b_bbs);
		
		JButton b_exit = new JButton("\u9000\u51FA\u767B\u5F55");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_exit.setBounds(939, 271, 170, 35);
		contentPane.add(b_exit);
		
		JLabel label_name = new JLabel("");
		label_name.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_name.setBounds(387, 355, 240, 37);
		label_name.setText(Logon.associ.getName());
		contentPane.add(label_name);
		
		JLabel label_status = new JLabel("");
		label_status.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_status.setBounds(391, 409, 236, 37);
		label_status.setText(Logon.associ.getStatus());
		contentPane.add(label_status);
		
		JLabel label_time = new JLabel("");
		label_time.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_time.setBounds(391, 461, 236, 37);
		label_time.setText(Logon.associ.getTime());
		contentPane.add(label_time);
		
		JLabel label_mem = new JLabel("");
		label_mem.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_mem.setBounds(391, 513, 236, 37);
		label_mem.setText(Logon.associ.getMemberNum()+"");
		contentPane.add(label_mem);
		
		JLabel label_finance = new JLabel("");
		label_finance.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_finance.setBounds(391, 565, 236, 37);
		label_finance.setText(Logon.associ.getFinance()+"");
		contentPane.add(label_finance);
	}
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new Logon().setVisible(true);
	}
	
	private void b_modifyActionPerformed(ActionEvent e) {
		this.dispose();
		new modifyAssoci().setVisible(true);
	}
	
	private void b_memberActionPerformed(ActionEvent e) {
		if(Logon.associ.getStatus().equals("正式社团"))
		{
			this.dispose();
		    new manageMember().setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null, "社团还未成立，不能使用该功能");
	}
	
	private void b_activityActionPerformed(ActionEvent e) {
		if(Logon.associ.getStatus().equals("正式社团"))
		{
			this.dispose();
		    new manageActivity().setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null, "社团还未成立，不能使用该功能");
	}
	
	private void b_financeActionPerformed(ActionEvent e) {
		if(Logon.associ.getStatus().equals("正式社团"))
		{
			this.dispose();
		    new manageFinance().setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null, "社团还未成立，不能使用该功能");
	}
	
	private void b_volunteerActionPerformed(ActionEvent e) {
		if(Logon.associ.getStatus().equals("正式社团"))
		{
			this.dispose();
		    new manageVolunteer().setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null, "社团还未成立，不能使用该功能");
	}
	
	private void b_voteActionPerformed(ActionEvent e) {
		if(Logon.associ.getStatus().equals("正式社团"))
		{
			this.dispose();
		    new manageVote().setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null, "社团还未成立，不能使用该功能");
	}
	
	private void b_searchActionPerformed(ActionEvent e) {
		if(Logon.associ.getStatus().equals("正式社团"))
		{
			this.dispose();
		    new manageSearch().setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null, "社团还未成立，不能使用该功能");
	}
}
