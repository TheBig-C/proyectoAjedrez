package poo;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
	static final String DB_URl="jdbc:postgresql://localhost/Ajedrez";
	static final String USER ="postgres";
	static final String PASS= "D4l3mb3rt";
	public Connection getConexionPostgres() throws SQLException {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(DB_URl,USER,PASS);
			
		}catch (SQLException e) {
			//JOptionPane.showMessageDialog(null,"No se puede conectar");
			return null;
		}
		//JOptionPane.showMessageDialog(null,"Conexion exitosa");
		return conn;
	}
}
