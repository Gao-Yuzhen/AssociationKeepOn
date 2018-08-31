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

import dao.activityDao;
import dao.associDao;
import dao.financeDao;
import model.Activity;
import model.Finance;
import util.StringUtil;
import util.dbUtil;

public class addFinance extends JFrame {

	dbUtil dUtil=new dbUtil();
	financeDao fDao=new financeDao();
	associDao aDao=new associDao();

	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField inTxt;
	private JTextField outTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addFinance frame = new addFinance();
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
	public addFinance() {
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
		
		JLabel label = new JLabel("\u6DFB\u52A0\u6B3E\u9879");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label.setBounds(268, 336, 196, 35);
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
		
		JLabel label_1 = new JLabel("\u6B3E\u9879\u540D\u79F0\uFF1A");
		label_1.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(242, 414, 160, 33);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u6536\u5165\uFF1A");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_2.setBounds(272, 478, 130, 35);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u652F\u51FA\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("汉仪南宫体简", Font.PLAIN, 25));
		label_3.setBounds(272, 552, 130, 23);
		contentPane.add(label_3);
		
		nameTxt = new JTextField();
		nameTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 23));
		nameTxt.setBounds(417, 414, 344, 33);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);
		
		inTxt = new JTextField();
		inTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 23));
		inTxt.setBounds(417, 480, 171, 33);
		contentPane.add(inTxt);
		inTxt.setColumns(10);
		
		outTxt = new JTextField();
		outTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 23));
		outTxt.setBounds(417, 546, 171, 33);
		contentPane.add(outTxt);
		outTxt.setColumns(10);
		
		JButton b_add = new JButton("\u63D0\u4EA4\u6B3E\u9879");
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
		new manageFinance().setVisible(true);
	}
	
	private void b_addActionPerformed(ActionEvent e) {
		String name=nameTxt.getText();
		String in_name=inTxt.getText();
		String out_name=outTxt.getText();
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null, "款项名称不能为空");
			return;
		}
		if(in_name.equals(""))
		{           
			in_name=0+"";
		}
		if(out_name.equals(""))
		{           
			out_name=0+"";
		}
		if(!StringUtil.isInt(in_name))
		{
			JOptionPane.showMessageDialog(null, "收入必须为数字");
			return;
		}
		if(!StringUtil.isInt(out_name))
		{
			JOptionPane.showMessageDialog(null, "支出必须为数字");
			return;
		}
		int in=Integer.parseInt(in_name);
		int out=Integer.parseInt(out_name);
		if(in==0&&out==0)
		{
			JOptionPane.showMessageDialog(null, "收入和支出不能都为空");
			return;
		}
		int total=in-out;
		if(Logon.associ.getFinance()+total<0)
		{
			JOptionPane.showMessageDialog(null, "总额不足");
			return;
		}
		Connection con=null;
		Finance fi=new Finance(name,in,out,Logon.associ.getId());
		try
		{
			con=dUtil.getCon();		
			int n=fDao.add(con, fi);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "款项添加成功");
				Logon.associ.setFinance(Logon.associ.getFinance()+total);
				aDao.updateFin(con, Logon.associ.getId(), Logon.associ.getFinance());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "款项添加失败");
			}
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "款项添加失败");
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
