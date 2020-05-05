package adatbaziskezelo;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class WorkerModify extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private WorkerTableModel etm;
	private DataVerify c = new DataVerify();
	private DatabaseMethods dbm = new DatabaseMethods();
	private JTextField id;
	private JTextField nev;
	private JTextField szid;
	private JTextField munk;
	private JTextField fiz;

	
	public WorkerModify(JFrame f, WorkerTableModel betm) {
		super(f, "Munkatársak adatainak módositása", true);
		etm = betm;
		
		setBounds(100, 100, 882, 555);
		getContentPane().setLayout(null);
		
		JButton btnBezar = new JButton("Bez\u00E1r");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose(); 
			}
		});
		btnBezar.setBounds(753, 482, 89, 23);
		getContentPane().add(btnBezar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 815, 276);
		getContentPane().add(scrollPane);
		
		table = new JTable(etm);
		table.setForeground(Color.CYAN);
		table.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(table);
		
		JButton btnmodosítas = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnmodosítas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, Jel=0, x=0;
				for(x = 0; x<etm.getRowCount();x++)
					if ((Boolean)etm.getValueAt(x,0)) {db++; Jel=x;}
					if (db==0) c.SM("Nincs kijelölve módositandó Munkás!", 0);
					if (db>1) c.SM("Több Munkás van kijelölve! (Egyszerre csak egy modositando)", 0); 
					if (db==1) {
						if (modDataPc() >0) {
							boolean ok= true;
							if (c.filled(id)) ok= c.goodInt(id,"Azonosító");
							if (ok && c.filled(fiz)) ok = c.goodInt(fiz, "Fizetés");
							if(ok) {
								String mkod = etm.getValueAt(Jel, 1).toString();
								dbm.connect();
								if(c.filled(id)) dbm.UpdateData(mkod, "azonosito", c.RTF(id));
								if(c.filled(nev)) dbm.UpdateData(mkod, "nev", c.RTF(nev));
								if(c.filled(szid)) dbm.UpdateData(mkod, "szulido", c.RTF(szid));
								if(c.filled(munk)) dbm.UpdateData(mkod, "munkakor", c.RTF(munk));
								if(c.filled(fiz)) dbm.UpdateData(mkod, "fizetes", c.RTF(fiz));
								dbm.DisConnect();
								
								if (c.filled(id)) etm.setValueAt(c.strinToInt(c.RTF(id)), Jel, 1);
								if (c.filled(nev)) etm.setValueAt(c.RTF(nev), Jel, 2);
								if (c.filled(szid)) etm.setValueAt(c.RTF(szid), Jel, 3);
								if (c.filled(munk)) etm.setValueAt(c.RTF(munk), Jel, 4);
								if (c.filled(fiz)) etm.setValueAt(c.strinToInt(c.RTF(fiz)), Jel, 5);
								c.SM("A munkatárs Módosítva",1);
								
								
							
							}
							else {
						
								c.SM("Nincs kitöltve egy módosító mezezõ sem!",0);
				
							}
						}
					}	
			}
		});
		
		TableColumn tc = null;
		for (int i = 0; i < 5; i++) {
			tc = table.getColumnModel().getColumn(i);
			if (i==0 || i==1 || i==4) tc.setPreferredWidth(30);
			else {tc.setPreferredWidth(100);}
		}
		table.setAutoCreateRowSorter(true);
		
		btnmodosítas.setBounds(34, 482, 117, 23);
		getContentPane().add(btnmodosítas);
		
		id = new JTextField();
		id.setBounds(34, 313, 75, 20);
		getContentPane().add(id);
		id.setColumns(10);
		
		nev = new JTextField();
		nev.setBounds(188, 313, 150, 20);
		getContentPane().add(nev);
		nev.setColumns(10);
		
		szid = new JTextField();
		szid.setBounds(439, 313, 86, 20);
		getContentPane().add(szid);
		szid.setColumns(10);
		
		munk = new JTextField();
		munk.setBounds(630, 313, 117, 20);
		getContentPane().add(munk);
		munk.setColumns(10);
		
		fiz = new JTextField();
		fiz.setBounds(253, 384, 292, 20);
		getContentPane().add(fiz);
		fiz.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 316, 46, 14);
		getContentPane().add(lblId);
		
		JLabel lblNv = new JLabel("N\u00E9v:");
		lblNv.setBounds(162, 316, 46, 14);
		getContentPane().add(lblNv);
		
		JLabel lblSzletsiId = new JLabel("Sz\u00FClet\u00E9si id\u0151:");
		lblSzletsiId.setBounds(377, 316, 63, 14);
		getContentPane().add(lblSzletsiId);
		
		JLabel lblMunkakr = new JLabel("Munkak\u00F6r:");
		lblMunkakr.setBounds(579, 316, 50, 14);
		getContentPane().add(lblMunkakr);
		
		JLabel lblFizets = new JLabel("Fizet\u00E9s:");
		lblFizets.setBounds(211, 387, 46, 14);
		getContentPane().add(lblFizets);
		
		
		TableRowSorter<WorkerTableModel> trs = (TableRowSorter<WorkerTableModel>)table.getRowSorter();
		trs.setSortable(0, false);
		

	}
	public int modDataPc() {
		int pc=0;
		if (c.filled(id)) pc++;
		if (c.filled(nev)) pc++;
		if (c.filled(szid)) pc++;
		if (c.filled(munk)) pc++;
		if (c.filled(fiz)) pc++;
		return pc;
	}
}
