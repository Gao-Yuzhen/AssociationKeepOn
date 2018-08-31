package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import dao.associDao;
import dao.userAssociDao;
import model.Association;
import model.UserAssoci;
import util.dbUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class searchAssoci extends JFrame {
	
	private JPanel contentPane;
	private JTable table;
	private JScrollPane associList;
	private String aName;
	
	dbUtil dUtil=new dbUtil();
	associDao aDao=new associDao();
	userAssociDao uaDao=new userAssociDao();
	private JButton b_bbs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchAssoci frame = new searchAssoci();
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
	public searchAssoci() {
		aName="";
		setResizable(false);
		setTitle("\u793E\u56E2KeepOn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1219, 866);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// ����Frame������ʾ
		this.setLocationRelativeTo(null);
						
		//���ñ���
		JPanel p = new JPanel();
		ImageIcon i = new ImageIcon("src/back.jpg");
		JLabel l = new JLabel(i);
		l.setBounds(0, 0, 1230, 830);
		getLayeredPane().add(l, new Integer(Integer.MIN_VALUE));
		p = (JPanel) this.getContentPane();
		p.setOpaque(false);
		p.setBounds(0, 0, 1219, 866);
		
		JButton b_exit = new JButton("\u9000\u56DE\u9996\u9875");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("�����Ϲ����", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		
		associList = new JScrollPane();
		associList.setBounds(273, 339, 607, 289);
		contentPane.add(associList);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableMousePressed(e);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u793E\u56E2\u540D\u79F0"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.setRowHeight(30);
		table.getTableHeader().setVisible(true); 
		table.setBounds(273, 339, 607, 289);
		table.setFont(new Font("�����Ϲ����", Font.PLAIN, 20));
		table.getTableHeader().setFont(new Font("�����Ϲ����", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class, r);
		associList.setViewportView(table);
		
		JButton b_in = new JButton("\u7533\u8BF7\u52A0\u5165");
		b_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_inActionPerformed(arg0);
			}
		});
		b_in.setFont(new Font("�����Ϲ����", Font.PLAIN, 20));
		b_in.setBounds(916, 336, 130, 35);
		contentPane.add(b_in);
		
		b_bbs = new JButton("\u793E\u56E2\u8BBA\u575B");
		b_bbs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_bbsActionPerformed(e);
			}
		});
		b_bbs.setFont(new Font("�����Ϲ����", Font.PLAIN, 20));
		b_bbs.setBounds(916, 398, 130, 35);
		contentPane.add(b_bbs);
		
	}

	public void fillTable(Association a)
	{
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		try
		{
			con=dUtil.getCon();
			int x=1;
			ResultSet rs=aDao.associList(con, a);
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(x);
				x++;
				v.add(rs.getString("Name"));
				dtm.addRow(v);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new Main().setVisible(true);
	}
	
	private void tableMousePressed(MouseEvent e) {
		int row=table.getSelectedRow();
		aName= (String) table.getValueAt(row, 1);
	}
	
	private void b_inActionPerformed(ActionEvent e) {
		if(aName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ���������");
			return;
		}
		int inId=0;
		Connection con =null;
		try {
			con=dUtil.getCon();
			ResultSet rs=aDao.checkName(con, aName);
			while(rs.next())
			{
				inId=rs.getInt("ID");
			}
		}catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		UserAssoci ua=new UserAssoci(Logon.user.getId(),inId,"��������");
		try {
			con=dUtil.getCon();
			ResultSet rs=uaDao.check(con, ua);
			while(rs.next())
			{
				if(rs.getString("Status").equals("�˳������"))
					JOptionPane.showMessageDialog(null, "��δ�˳�������");
				if(rs.getString("Status").equals("��������"))
					JOptionPane.showMessageDialog(null, "�����ѷ��ͣ��벻Ҫ�ظ�����");
				if(rs.getString("Status").equals("�Ѽ���"))
					JOptionPane.showMessageDialog(null, "�Ѽ��������");
					
				return;
			}
			int n=uaDao.add(con, ua);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "���뷢�ͳɹ�����ȴ����");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "���뷢��ʧ�ܣ�������");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "���뷢��ʧ�ܣ�������");
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
	
	private void b_bbsActionPerformed(ActionEvent e) {
		if(aName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�����������̳");
			return;
		}
		Connection con =null;
		int inId=0;
		try {
			con=dUtil.getCon();
			ResultSet rs=aDao.checkName(con, aName);
			while(rs.next())
			{
				inId=rs.getInt("ID");
			}
		}catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
			JOptionPane.showMessageDialog(null, "���뷢��ʧ�ܣ�������");
		}
		this.dispose();
		checkBBS cb=new checkBBS();
		cb.fillTable(inId);
		cb.setVisible(true);
	}
}
