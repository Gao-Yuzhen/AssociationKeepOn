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
import javax.swing.border.EmptyBorder;

import dao.activityDao;
import model.Activity;
import model.Association;
import util.dbUtil;

public class checkActivity extends JFrame {
	
	dbUtil dUtil=new dbUtil();
	activityDao acDao=new activityDao();
	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField timeTxt;
	private JTextField targetTxt;
	private JTextField placeTxt;
	private JTextField introTxt;
	private int acId;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkActivity frame = new checkActivity();
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
	public checkActivity() {
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
		
		JLabel label = new JLabel("\u6D3B\u52A8\u4FE1\u606F");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label.setBounds(269, 315, 196, 35);
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
		
		JLabel label_1 = new JLabel("\u6D3B\u52A8\u540D\u79F0\uFF1A");
		label_1.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(252, 386, 130, 21);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u6D3B\u52A8\u65F6\u95F4\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		label_2.setBounds(252, 434, 130, 35);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u6D3B\u52A8\u5730\u70B9\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		label_3.setBounds(252, 493, 130, 23);
		contentPane.add(label_3);
		
		nameTxt = new JTextField();
		nameTxt.setEditable(false);
		nameTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		nameTxt.setBounds(377, 385, 451, 28);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);
		
		timeTxt = new JTextField();
		timeTxt.setEditable(false);
		timeTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		timeTxt.setBounds(377, 438, 171, 28);
		contentPane.add(timeTxt);
		timeTxt.setColumns(10);
		
		targetTxt = new JTextField();
		targetTxt.setEditable(false);
		targetTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		targetTxt.setBounds(691, 438, 137, 28);
		contentPane.add(targetTxt);
		targetTxt.setColumns(10);
		
		JLabel label_4 = new JLabel("\u9762\u5411\u7FA4\u4F53\uFF1A");
		label_4.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(563, 434, 130, 35);
		contentPane.add(label_4);
		
		placeTxt = new JTextField();
		placeTxt.setEditable(false);
		placeTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		placeTxt.setBounds(377, 491, 254, 28);
		contentPane.add(placeTxt);
		placeTxt.setColumns(10);
		
		JLabel label_5 = new JLabel("\u6D3B\u52A8\u63CF\u8FF0\uFF1A");
		label_5.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(252, 564, 130, 21);
		contentPane.add(label_5);
		
		introTxt = new JTextField();
		introTxt.setEditable(false);
		introTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		introTxt.setBounds(377, 546, 451, 61);
		contentPane.add(introTxt);
		introTxt.setColumns(10);
		
		JButton b_modify = new JButton("\u7F16\u8F91\u4FE1\u606F");
		b_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_modifyActionPerformed(e);
			}
		});
		b_modify.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_modify.setBounds(916, 336, 130, 35);
		contentPane.add(b_modify);
		
		JButton b_save = new JButton("\u4FDD\u5B58\u4FEE\u6539");
		b_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_saveActionPerformed(e);
			}
		});
		b_save.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_save.setBounds(916, 398, 130, 35);
		contentPane.add(b_save);
	}
	
	public void fillTxt(Activity ac)
	{
		nameTxt.setText(ac.getName());
		timeTxt.setText(ac.getTime());
		targetTxt.setText(ac.getTarget());
		placeTxt.setText(ac.getPlace());
	    introTxt.setText(ac.getIntroduce());
	    acId=ac.getId();
	}
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new manageActivity().setVisible(true);
	}

	private void b_modifyActionPerformed(ActionEvent e) {
		nameTxt.setEditable(true);
		timeTxt.setEditable(true);
		targetTxt.setEditable(true);
		placeTxt.setEditable(true);
		introTxt.setEditable(true);
	}
	
	private void b_saveActionPerformed(ActionEvent e) {
		String name=nameTxt.getText();
		String time=timeTxt.getText();
		String place=placeTxt.getText();
		String target=targetTxt.getText();
		String intro=introTxt.getText();
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null, "活动名称不能为空");
			return;
		}
		if(time.equals(""))
		{
			JOptionPane.showMessageDialog(null, "活动时间不能为空");
			return;
		}
		if(place.equals(""))
		{
			JOptionPane.showMessageDialog(null, "活动地点不能为空");
			return;
		}
		Connection con=null;
		try
		{
			con=dUtil.getCon();
			Activity ac=new Activity();
			ac.setName(name);
			ac.setTime(time);
			ac.setPlace(place);
			ac.setIntroduce(intro);
			ac.setTarget(target);
			ac.setId(acId);
			int n=acDao.update(con, ac);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "修改成功");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "修改失败");
			}
			
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
		nameTxt.setEditable(false);
		timeTxt.setEditable(false);
		targetTxt.setEditable(false);
		placeTxt.setEditable(false);
		introTxt.setEditable(false);
	}
}
