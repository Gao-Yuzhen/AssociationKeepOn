package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.activityDao;
import dao.researchDao;
import util.dbUtil;

public class manageSearch extends JFrame {

	dbUtil dUtil=new dbUtil();
	researchDao rDao=new researchDao();
	private JPanel contentPane;
	private JTable researchTable;
	private String mouseName;
	private JTextField searchTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageSearch frame = new manageSearch();
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
	public manageSearch() {
		mouseName="";
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
		
		JScrollPane researchList = new JScrollPane();
		researchList.setBounds(273, 375, 607, 289);
		contentPane.add(researchList);
		
		researchTable = new JTable();
		researchTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				researchTableMousePressed(arg0);
			}
		});
		researchTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u6807\u9898"
			}
		));
		researchTable.getColumnModel().getColumn(1).setPreferredWidth(407);
		researchTable.setRowHeight(30);
		researchTable.getTableHeader().setVisible(true); 
		researchTable.setBounds(273, 339, 607, 289);
		researchTable.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		researchTable.getTableHeader().setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		researchTable.setDefaultRenderer(Object.class, r);
		researchList.setViewportView(researchTable);
		
		JButton b_exit = new JButton("\u9000\u56DE\u9996\u9875");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		
		JButton b_add = new JButton("\u6DFB\u52A0\u8C03\u67E5");
		b_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_addActionPerformed(arg0);
			}
		});
		b_add.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_add.setBounds(916, 336, 130, 35);
		contentPane.add(b_add);
		
		JButton b_cancel = new JButton("\u5220\u9664\u8C03\u67E5");
		b_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_cancelActionPerformed(e);
			}
		});
		b_cancel.setFont(new Font("汉仪南宫体简", Font.PLAIN, 20));
		b_cancel.setBounds(916, 398, 130, 35);
		contentPane.add(b_cancel);
		
		JLabel label = new JLabel("\u67E5\u8BE2\u8C03\u67E5");
		label.setFont(new Font("字酷堂海藏楷体", Font.PLAIN, 30));
		label.setBounds(273, 315, 196, 35);
		contentPane.add(label);
		
		searchTxt = new JTextField();
		searchTxt.setFont(new Font("汉仪南宫体简", Font.PLAIN, 22));
		searchTxt.setBounds(416, 317, 285, 32);
		contentPane.add(searchTxt);
		searchTxt.setColumns(10);
		
		JButton b_search = new JButton("");
		b_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_searchActionPerformed(arg0);
			}
		});
		b_search.setBounds(720, 316, 33, 33);
		contentPane.add(b_search);
		ImageIcon i_search = new ImageIcon("src/icon/search.png");
		b_search.setIcon(i_search);
		
		fillTable(Logon.associ.getId());
	}
	
	public void fillTable(int aId)
	{
		int x=1;
		DefaultTableModel dtm=(DefaultTableModel) researchTable.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		try
		{
			con=dUtil.getCon();
			ResultSet rs=rDao.rList(con, aId,searchTxt.getText());
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(x);
				x++;
				v.add(rs.getString("Title"));
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
		new A_main().setVisible(true);
	}
	
	private void b_addActionPerformed(ActionEvent e) {
		this.dispose();
		new addSearch().setVisible(true);
	}

	public void researchTableMousePressed(MouseEvent arg0) {
		int row=researchTable.getSelectedRow();
		mouseName= (String) researchTable.getValueAt(row, 1);
	}
	
	private void b_cancelActionPerformed(ActionEvent e) {
		if(mouseName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "请选择要删除的调查");
			return;
		}
		Connection con =null;
		try {
			con=dUtil.getCon();
			int n=rDao.cancelR(con, Logon.associ.getId(),mouseName);
			if(n==1)
			{
				JOptionPane.showMessageDialog(null, "删除调查成功");
				fillTable(Logon.associ.getId());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "删除调查失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "删除调查失败");
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
	
	private void b_searchActionPerformed(ActionEvent arg0) {
		fillTable(Logon.associ.getId());
	}
}
