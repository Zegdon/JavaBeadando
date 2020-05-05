package adatbaziskezelo;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewWorker extends JDialog {
	private JTextField id;
	private JTextField nev;
	private JTextField szid;
	private JTextField munk;
	private JTextField fiz;
	private DatabaseMethods dbm = new DatabaseMethods();
	

	
	public NewWorker() {
		setBounds(100, 100, 437, 400);
		getContentPane().setLayout(null);
		
		JLabel lblid = new JLabel("Azonos\u00EDt\u00F3:");
		lblid.setBounds(52, 31, 86, 17);
		getContentPane().add(lblid);
		
		
		JLabel lblnev = new JLabel("N\u00E9v:");
		lblnev.setBounds(52, 76, 46, 14);
		getContentPane().add(lblnev);
		
		JLabel lblszid = new JLabel("Sz\u00FClet\u00E9si id\u0151:");
		lblszid.setBounds(52, 121, 86, 14);
		getContentPane().add(lblszid);
		
		JLabel lblmunk = new JLabel("Munkak\u00F6r:");
		lblmunk.setBounds(52, 167, 62, 14);
		getContentPane().add(lblmunk);
		
		JLabel lblfiz = new JLabel("Fizet\u00E9s:");
		lblfiz.setBounds(52, 214, 46, 14);
		getContentPane().add(lblfiz);
		
		
		id = new JTextField();
		id.setBounds(184, 29, 86, 20);
		getContentPane().add(id);
		id.setColumns(10);
		
		nev = new JTextField();
		nev.setBounds(184, 73, 86, 20);
		getContentPane().add(nev);
		nev.setColumns(10);
		
		szid = new JTextField();
		szid.setBounds(184, 118, 86, 20);
		getContentPane().add(szid);
		szid.setColumns(10);
		
		munk = new JTextField();
		munk.setBounds(184, 164, 86, 20);
		getContentPane().add(munk);
		munk.setColumns(10);
		
		fiz = new JTextField();
		fiz.setBounds(184, 211, 86, 20);
		getContentPane().add(fiz);
		fiz.setColumns(10);

	

	JButton btnBeszur = new JButton("Besz\u00FAr");
	btnBeszur.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			DataVerify c = new DataVerify();
			if (c.goodInt(id, "Azonosító"))
				if (c.filled(nev, "Név"))
				if (c.goodDate(szid, "Születési idõ"))
						if(c.filled(munk, "Munkakör"))
							if (c.goodInt(fiz, "Fizetés")) {
			
			
			
			
			
			dbm.connect();
			dbm.InsertData(RTF(id), RTF(nev), RTF(szid), RTF(munk), RTF(fiz));
			dbm.DisConnect();
						}
		}
	});
	
	btnBeszur.setBounds(154, 305, 89, 23);
	getContentPane().add(btnBeszur);
	}
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}
