package adatbaziskezelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DatabaseMethods {
	private Statement s = null;
	private Connection conn= null;
	private ResultSet rs = null;
	
	public void Reg() {
		try {
			Class.forName("org.sqlite.JDBC");
		//	SM("Sikeres driver regisztráció" ,1);
		} catch (ClassNotFoundException e ) {
		//	SM("Hibás driver regisztráció!"+e.getMessage(),0 );
		}
	}
	public void SM(String msg, int tipus) {
		JOptionPane.showMessageDialog(null, msg, "Program üzenet", tipus);
	}
	
	public void connect() {
		try {
			String url= "jdbc:sqlite:C:/Users/user/Desktop/adatbázis2/sqlite/adatbkezelo/empdb";
			conn = DriverManager.getConnection(url);
			//SM("Connection OK",1);
		}	catch (SQLException e) {
			SM("JDBC Connect: "+e.getMessage(),0);
			
		}
		
	}
	
	public void DisConnect() {
		try {
			conn.close();
			//SM("DisConnection OK!", 1);
		}	catch (SQLException e) {SM(e.getMessage(),0);}
		
	}
	
	public WorkerTableModel ReadData() {
		Object emptmn[]= {"Jel", "ID", "Név", "Születési idõ", "Munkakör", "Fizetés"};
		WorkerTableModel etm = new WorkerTableModel(emptmn,0);
		String nev= "", szid="", munk="", x="\t";
		int id=0, fiz=0;
		String sqlp= "select azonosito, nev, szulido, munkakor, fizetes from emp";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sqlp);
			while (rs.next()) {
				id= rs.getInt("azonosito");
				nev= rs.getString("nev");
				szid= rs.getString("szulido");
				munk= rs.getString("munkakor");
				fiz= rs.getInt("fizetes");
				etm.addRow(new Object[] {false, id, nev, szid, munk, fiz});
		
			}
			rs.close();
		}catch (SQLException e) {SM(e.getMessage(),0);}
		return etm;
	}
	
	public void InsertData(String id, String nev, String szid, String munk, String fiz ) {
		
		String sqlp ="insert into emp values("+id+",'"+nev+"', '"+szid+"', '"+munk+"',\""+fiz+"\")";
		try {
			s= conn.createStatement();
			s.execute(sqlp);
			SM("Új Munkatárs sikeresen Létrehozva!",1);
		}	catch(SQLException e) {
			SM("JDBC insert: "+e.getMessage(),0 );
		
		}
	}
	
	public void DelData(String id) {
		String sqlp = "delete from emp where azonosito =" +id;
		try {
			s = conn.createStatement();
			s.execute(sqlp);}
		catch (SQLException e) {
			SM("JDBC Delete: "+e.getMessage(),0);
			
		}
	}
	
	public void UpdateData(String azonosito, String mnev, String madat) {
		String sqlp = "update emp set "+mnev+"='"+madat+"' where azonosito="+azonosito;
		try {
			s = conn.createStatement();
			s.execute(sqlp);}
		catch (SQLException e) {
			SM("JDBC Update: "+e.getMessage(),0);
			
		}
		
	}
	
	
	
}


