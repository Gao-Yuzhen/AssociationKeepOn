package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.associDao;
import dao.userAssociDao;
import model.Association;
import model.UserAssoci;
import util.dbUtil;
import javax.swing.JTextField;

public class checkAssoci extends JFrame {

	dbUtil dUtil=new dbUtil();
	userAssociDao uaDao=new userAssociDao();
	private JPanel contentPane;
	private JTable myAssoci;
	private JTextField searchTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					checkAssoci frame = new checkAssoci();
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
	public checkAssoci() {
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
		
		JLabel label = new JLabel("\u67E5\u8BE2\u793E\u56E2");
		label.setFont(new Font("◊÷ø·Ã√∫£≤ÿø¨ÃÂ", Font.PLAIN, 30));
		label.setBounds(273, 315, 196, 35);
		contentPane.add(label);
		
		JScrollPane myAssociList = new JScrollPane();
		myAssociList.setBounds(273, 375, 607, 289);
		contentPane.add(myAssociList);
		
		myAssoci = new JTable();
		myAssoci.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u793E\u56E2\u540D\u79F0", "\u72B6\u6001"
			}
		));
		myAssoci.getColumnModel().getColumn(0).setPreferredWidth(50);
		myAssoci.getColumnModel().getColumn(1).setPreferredWidth(300);
		myAssoci.setRowHeight(30);
		myAssoci.getTableHeader().setVisible(true); 
		myAssoci.setBounds(273, 339, 607, 289);
		myAssoci.setFont(new Font("∫∫“«ƒœπ¨ÃÂºÚ", Font.PLAIN, 20));
		myAssoci.getTableHeader().setFont(new Font("∫∫“«ƒœπ¨ÃÂºÚ", Font.PLAIN, 20));
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		myAssoci.setDefaultRenderer(Object.class, r);
		myAssociList.setViewportView(myAssoci);
		
		
		JButton b_exit = new JButton("\u9000\u56DE\u9996\u9875");
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b_exitActionPerformed(e);
			}
		});
		b_exit.setFont(new Font("∫∫“«ƒœπ¨ÃÂºÚ", Font.PLAIN, 20));
		b_exit.setBounds(916, 274, 130, 35);
		contentPane.add(b_exit);
		
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
		
		fillTable(Logon.user.getId());
	}
	
	public void fillTable(int uId)
	{
		int x=1;
		DefaultTableModel dtm=(DefaultTableModel) myAssoci.getModel();
		Connection con=null;
		dtm.setRowCount(0);
		try
		{
			con=dUtil.getCon();
			ResultSet rs=uaDao.myAssociList(con, uId,searchTxt.getText());
			while(rs.next())
			{
				Vector v=new Vector();
				v.add(x);
				x++;
				v.add(rs.getString("Name"));
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
	
	private void b_exitActionPerformed(ActionEvent e) {
		this.dispose();
		new Main().setVisible(true);
	}
	
	private void b_searchActionPerformed(ActionEvent arg0) {
		fillTable(Logon.user.getId());
	}
}
