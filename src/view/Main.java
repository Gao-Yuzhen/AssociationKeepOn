package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import model.Association;
import model.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField s_associTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		
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
		
		JLabel lblNewLabel = new JLabel("\u67E5\u8BE2\u793E\u56E2");
		lblNewLabel.setFont(new Font("汉仪南宫体简", Font.PLAIN, 35));
		lblNewLabel.setBounds(310, 451, 176, 55);
		contentPane.add(lblNewLabel);
		
		s_associTxt = new JTextField();
		s_associTxt.setBounds(471, 462, 211, 35);
		contentPane.add(s_associTxt);
		s_associTxt.setColumns(10);
		
		JButton b_search = new JButton("");
		b_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_searchActionPerformed(arg0);
			}
		});
		b_search.setBounds(697, 462, 33, 35);
		contentPane.add(b_search);
		ImageIcon i_search = new ImageIcon("src/icon/search.png");
		b_search.setIcon(i_search);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(916, 400, 130, 40);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu("\u4E2A\u4EBA\u8D44\u6599");
		menu.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		menuBar.add(menu);
		
		JMenuItem m_check = new JMenuItem("\u67E5\u770B\u4E2A\u4EBA\u8D44\u6599");
		m_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m_checkActionPerformed(arg0);
			}
		});
		m_check.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		menu.add(m_check);
		
		JMenuItem m_modify = new JMenuItem("\u7F16\u8F91\u4E2A\u4EBA\u8D44\u6599");
		m_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_modifyActionPerformed(e);
			}
		});
		menu.add(m_modify);
		m_modify.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(916, 500, 130, 40);
		contentPane.add(menuBar_1);
		
		JMenu menu_1 = new JMenu("\u6211\u7684\u793E\u56E2");
		menu_1.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		menuBar_1.add(menu_1);
		
		JMenuItem m_checkA = new JMenuItem("\u67E5\u770B\u6211\u7684\u793E\u56E2");
		m_checkA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m_checkAActionPerformed(arg0);
			}
		});
		menu_1.add(m_checkA);
		m_checkA.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		
		JMenuItem m_manageA = new JMenuItem("\u7BA1\u7406\u6211\u7684\u793E\u56E2");
		m_manageA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m_manageAActionPerformed(arg0);
			}
		});
		menu_1.add(m_manageA);
		m_manageA.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		
		JButton b_exit = new JButton("\u9000\u51FA\u767B\u5F55");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		
		
		
	}
	
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new Logon().setVisible(true);
	}
	
	private void b_searchActionPerformed(ActionEvent arg0) {
		this.dispose();
		String sTxt=s_associTxt.getText();
		searchAssoci s_associ=new searchAssoci();
		Association a=new Association();
		a.setName(sTxt);
		s_associ.fillTable(a);
		s_associ.setVisible(true);

	}
	
	private void m_checkActionPerformed(ActionEvent e) {
		this.dispose();
		new checkUser().setVisible(true);
	}
	
	private void m_modifyActionPerformed(ActionEvent e) {
		this.dispose();
		new modifyUser().setVisible(true);
	}
	
	private void m_checkAActionPerformed(ActionEvent e) {
		this.dispose();
		new checkAssoci().setVisible(true);
	}
	
	private void m_manageAActionPerformed(ActionEvent e) {
		this.dispose();
		new manageAssoci().setVisible(true);
	}
}
