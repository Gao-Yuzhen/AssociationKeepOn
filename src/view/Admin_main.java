package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Admin_main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_main frame = new Admin_main();
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
	public Admin_main() {
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
		
		JButton b_exit = new JButton("\u9000\u51FA\u767B\u5F55");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_exit.setBounds(939, 271, 170, 35);
		contentPane.add(b_exit);
		
		JButton b_associ = new JButton("\u7BA1\u7406\u793E\u56E2");
		b_associ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_associActionPerformed(e);
			}
		});
		b_associ.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_associ.setBounds(382, 434, 170, 35);
		contentPane.add(b_associ);
		
		JButton b_user = new JButton("\u7BA1\u7406\u5B66\u751F");
		b_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_userActionPerformed(arg0);
			}
		});
		b_user.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_user.setBounds(679, 436, 170, 35);
		contentPane.add(b_user);
		
		JButton b_activity = new JButton("\u7BA1\u7406\u6D3B\u52A8\u9879\u76EE");
		b_activity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_activityActionPerformed(e);
			}
		});
		b_activity.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_activity.setBounds(382, 522, 170, 35);
		contentPane.add(b_activity);
		
		JButton b_place = new JButton("\u7BA1\u7406\u6D3B\u52A8\u573A\u5730");
		b_place.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_placeActionPerformed(arg0);
			}
		});
		b_place.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_place.setBounds(679, 524, 170, 35);
		contentPane.add(b_place);
	}

	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new Logon().setVisible(true);
	}
	
	private void b_associActionPerformed(ActionEvent e) {
		this.dispose();
		new adminAssoci().setVisible(true);
	}
	
	private void b_userActionPerformed(ActionEvent e) {
		this.dispose();
		new adminUser().setVisible(true);
	}
	
	private void b_activityActionPerformed(ActionEvent e) {
		this.dispose();
		new adminActivity().setVisible(true);
	}
	
	private void b_placeActionPerformed(ActionEvent e) {
		this.dispose();
		new adminPlace().setVisible(true);
	}
}
