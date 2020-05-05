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

public class WorkerList extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private WorkerTableModel etm;

	
	public WorkerList(JFrame f, WorkerTableModel betm) {
		super(f, "Dolgozók listája", true);
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
		
		TableColumn tc = null;
		for (int i = 0; i < 5; i++) {
			tc = table.getColumnModel().getColumn(i);
			if (i==0 || i==1 || i==4) tc.setPreferredWidth(30);
			else {tc.setPreferredWidth(100);}
		}
		table.setAutoCreateRowSorter(true);
		TableRowSorter<WorkerTableModel> trs = (TableRowSorter<WorkerTableModel>)table.getRowSorter();
		trs.setSortable(0, false);
		

	}
}
