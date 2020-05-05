package adatbaziskezelo;

import javax.swing.table.DefaultTableModel;

public class WorkerTableModel extends DefaultTableModel {
	public WorkerTableModel (Object fildNames[], int rows) {
		super(fildNames, rows);
		}
	
	public boolean isCelleditable(int row, int col) {
		if (col == 0) {return true ;}
		return false;
	}
	

	public Class<?> getColumnClass (int index){
		if (index == 0) return (Boolean.class);
			else if (index==1 || index==4 || index==5 ) return (Integer.class);
		return(String.class);
	}
		
}


