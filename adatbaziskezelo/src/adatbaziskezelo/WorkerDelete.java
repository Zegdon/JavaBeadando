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

public class WorkerDelete extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private WorkerTableModel etm;
	private DataVerify c = new DataVerify();
	private DatabaseMethods dbm = new DatabaseMethods();

	
	public WorkerDelete(JFrame f, WorkerTableModel betm) {
		super(f, "Dolgozók Törlése", true);
		etm = betm;
		
		setBounds(100, 100, 731, 460);
		getContentPane().setLayout(null);
		
		JButton btnBezar = new JButton("Bez\u00E1r");
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose(); 
			}
		});
		btnBezar.setBounds(309, 387, 89, 23);
		getContentPane().add(btnBezar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 630, 330);
		getContentPane().add(scrollPane);
		
		table = new JTable(etm);
		table.setForeground(Color.CYAN);
		table.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(table);
		
		JButton btnAdatokTrlse = new JButton("Adatok t\u00F6rl\u00E9se");
		btnAdatokTrlse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db=0, Jel=0, x=0;
				for(x = 0; x<etm.getRowCount();x++)
					if ((Boolean)etm.getValueAt(x,0)) {db++; Jel=x;}
					if (db==0) c.SM("Nincs kijelölve törlendõ Munkást!", 0);
					if (db>1) c.SM("Több Munkás van kijelölve! (Egyszerre csak egy törölhetõ)", 0); 
					if (db==1) {
						String id= etm.getValueAt(Jel, 1).toString();
						dbm.connect();
						dbm.DelData(id);
						dbm.DisConnect();
						etm.removeRow(Jel);
						c.SM("A Munkás törölve",1);
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
		
		btnAdatokTrlse.setBounds(46, 387, 117, 23);
		getContentPane().add(btnAdatokTrlse);
		TableRowSorter<WorkerTableModel> trs = (TableRowSorter<WorkerTableModel>)table.getRowSorter();
		trs.setSortable(0, false);
		

	}
}
