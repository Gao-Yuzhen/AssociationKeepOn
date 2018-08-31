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

import dao.placeDao;
import util.dbUtil;

public class selectPlace extends JFrame {

	dbUtil dUtil=new dbUtil();
	placeDao pDao=new placeDao();
	private JPanel contentPane;
	private JTable pTable;
	private JScrollPane pList;
	private JTextField searchTxt;
	private String mName;
	private String name;
	private String time;
	private String target;
	private String intro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectPlace frame = new selectPlace();
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
	public selectPlace() {
		mName="";
		setResizable(false);
		setTitle("\u793E\u56E2KeepOn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1219, 866);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// …Ë÷√Frameæ”÷–œ‘ æ
		this.setLocationRelativeTo(null);
						
		//…Ë÷√±≥æ∞
		JPanel p = new JPanel();
		ImageIcon i = new ImageIcon("src/back.jpg");
		JLabel l = new JLabel(i);
		l.setBounds(0, 0, 1230, 830);
		getLayeredPane().add(l, new Integer(Integer.MIN_VALUE));
		p = (JPanel) this.getContentPane();
		p.setOpaque(false);
		p.setBounds(0, 0, 1219, 866);
		
		JButton b_exit = new JButton("\u9000\u56DE\u4E0A\u9875");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("∫∫“«ƒœπ¨ÃÂºÚ", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		
		pList = new JScrollPane();
		pList.setBounds(273, 375, 607, 289);
		contentPane.add(pList);
		
		pTable = new JTable();
		pTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				pTableMousePressed(arg0);
			}
		});
		pTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u5730\u70B9\u540D\u79F0", "\u4EBA\u5458\u5BB9\u91CF", "\u72B6\u6001"
			}
		));
		pTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		pTable.getColumnModel().getColumn(1).setPreferredWidth(267);
		pTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		pTable.getColumnModel().getColumn(3).setPreferredWidth(140);
		pTable.setRowHeight(30);
		pTable.getTableHeader().setVisible(true); 
		pTable.setBounds(273, 339, 607, 289);
		pTable.setFont(new Font("∫∫“«ƒœπ¨ÃÂºÚ", Font.PLAIN, 20));
		pTable.getTableHeader().setFont(new Font("∫∫“«ƒœπ¨ÃÂºÚ", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		pTable.setDefaultRenderer(Object.class, r);
		pList.setViewportView(pTable);
		
		JButton b_in = new JButton("\u9009\u62E9\u5730\u70B9");
		b_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_inActionPerformed(arg0);
			}
		});
		b_in.setFont(new Font("∫∫“«ƒœπ¨ÃÂºÚ", Font.PLAIN, 20));
		b_in.setBounds(916, 336, 130, 35);
		contentPane.add(b_in);
		
		searchTxt = new JTextField();
		searchTxt.setFont(new Font("∫∫“«ƒœπ¨ÃÂºÚ", Font.PLAIN, 22));
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
		
		JLabel label = new JLabel("\u67E5\u8BE2\u5730\u70B9");
		label.setFont(new Font("◊÷ø·Ã√∫£≤ÿø¨ÃÂ", Font.PLAIN, 30));
		label.setBounds(273, 315, 196, 35);
		contentPane.add(label);
		
		fillTable();
	}

	public void fillTable()
	{
		DefaultTableModel dtm=(DefaultTableModel) pTable.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		int x=1;
		try
		{
			con=dUtil.getCon();
			ResultSet rs=pDao.pList(con, 1,searchTxt.getText());
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(x);
				x++;
				v.add(rs.getString("Name"));
				v.add(rs.getString("Capacity"));
				v.add(rs.getString("Status"));
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
	
	private void pTableMousePressed(MouseEvent e) {
		int row=pTable.getSelectedRow();
		mName= (String) pTable.getValueAt(row, 1);
	}
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new addActivity().setVisible(true);
	}
	
	private void b_inActionPerformed(ActionEvent e) {

		if(mName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "«Î—°‘Òµÿµ„");
			return;
		}
		this.dispose();
		addActivity addAc=new addActivity();
		addAc.place(name,mName,time,target,intro);
		addAc.setVisible(true);

	}
	
	public void txt(String n,String t,String ta,String i) 
	{
		name=n;
		time=t;
		target=ta;
		intro=i;
	}
	
	private void b_searchActionPerformed(ActionEvent arg0) {
		fillTable();
	}
}
