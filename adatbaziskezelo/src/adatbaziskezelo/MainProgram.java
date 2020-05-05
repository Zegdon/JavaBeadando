package adatbaziskezelo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class MainProgram extends JFrame {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DatabaseMethods dbm = new DatabaseMethods ();
	private WorkerTableModel etm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainProgram frame = new MainProgram();
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
	public MainProgram() {
		dbm.Reg();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 397);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLista = new JButton("Lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbm.connect();
				etm= dbm.ReadData();
				dbm.DisConnect();
				WorkerList el = new WorkerList(MainProgram.this, etm);
				el.setVisible(true);
			}
		});
		btnLista.setBounds(199, 24, 89, 23);
		contentPane.add(btnLista);
		
		JButton btnAbezaras = new JButton("Bezárás");
		btnAbezaras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose ();
			}
		});
		btnAbezaras.setBackground(Color.RED);
		btnAbezaras.setBounds(25, 324, 89, 23);
		contentPane.add(btnAbezaras);
		
		JButton btnUjAdat = new JButton("\u00DAj munk\u00E1s");
		btnUjAdat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewWorker ne = new NewWorker();
				ne.setVisible(true);
			}
		});
		btnUjAdat.setBounds(187, 101, 117, 23);
		contentPane.add(btnUjAdat);
		
		JButton btnTrls = new JButton("T\u00F6rl\u00E9s");
		btnTrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.connect();
				etm= dbm.ReadData();
				dbm.DisConnect();
				WorkerDelete el = new WorkerDelete(MainProgram.this, etm);
				el.setVisible(true);
			}
		});
		btnTrls.setBounds(199, 182, 89, 23);
		contentPane.add(btnTrls);
		
		JButton btnMdosts = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnMdosts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.connect();
				etm= dbm.ReadData();
				dbm.DisConnect();
				WorkerModify em = new WorkerModify(MainProgram.this, etm);
				em.setVisible(true);
			}
		});
		btnMdosts.setBounds(199, 258, 89, 23);
		contentPane.add(btnMdosts);
		
		Object emptmn[]= {"ID", "Név", "Születési idõ", "Munkakör", "Fizetés"};
		WorkerTableModel etm = new WorkerTableModel(emptmn,0);
	}
	
}

