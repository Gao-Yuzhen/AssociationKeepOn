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

import dao.researchDao;
import dao.volunteerDao;
import model.Research;
import model.Volunteer;
import util.dbUtil;

public class addSearch extends JFrame {

	dbUtil dUtil=new dbUtil();
    researchDao rDao=new researchDao();

	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField contentTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addSearch frame = new addSearch();
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
	public addSearch() {
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
		
		JLabel label = new JLabel("\u6DFB\u52A0\u8C03\u67E5");
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
		
		JButton b_add = new JButton("\u63D0\u4EA4\u8C03\u67E5");
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
		new manageSearch().setVisible(true);
	}
	
	private void b_addActionPerformed(ActionEvent e) {
		String name=nameTxt.getText();
		String content=contentTxt.getText();
		
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null, "标题不能为空");
			return;
		}
		if(content.equals(""))
		{
			JOptionPane.showMessageDialog(null, "内容不能为空");
			return;
		}
		Connection con=null;
		int id=0;
		ResultSet rs;
		try {
			con=dUtil.getCon();	
			rs = rDao.maxId(con);
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
		Research r=new Research(id,name,content,Logon.associ.getId());
		try
		{
			con=dUtil.getCon();		
			int n=rDao.add(con, r);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "调查添加成功");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "调查添加失败");
			}
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "调查添加失败");
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
