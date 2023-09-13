package poo;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.security.Guard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class Main implements Serializable {
	public static int o=0;
	public static int band1 = 1,TiempoB=0,TiempoN=0;
	public static String pos,fichaDelBot,fichaDelBot1,fichaDelBot2,fichaant=null;
	public static boolean b=true,movido=false,tiempoBo=true,ganador,bot=false,colorF,auto,time,bots=true;
	public static boolean marcado=false,marcadoJaque=false;
	public static String[][] tB=new String[8][8];
	public static String[][] tN=new String[8][8];
	public static String[][] tP=new String[8][8];
	public static ArrayList<String> jugadasPartida=new ArrayList<String>();
	public static ArrayList<String> dfd2=new ArrayList<String>();
	public static ArrayList<String> mov=new ArrayList<String>(); 
	public static ArrayList<String> fichas=new ArrayList<String>(); 
	public static ArrayList<String> movidasJaque=new ArrayList<String>(); 
	public static ArrayList<Pieza> fig=new ArrayList<Pieza>();
	public static ArrayList<Pieza> figP=new ArrayList<Pieza>();
	public static String moverse="";
	public static String jugadas="Jugadas.txt";
	public static Tablero tab=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Main1 main=new Main1();
		main.setVisible(true);
		
		
		
		
	}
	static void cargarEspecificaciones(int ddd)throws SQLException {
		Conexion con=new Conexion();
		int tiempobl, tiempone;
		Connection conexion = con.getConexionPostgres();
		java.sql.Statement s= conexion.createStatement();
		ResultSet rs=  s.executeQuery("select a.bot, a.colorF,a.auto, a.tiempob,a.tiempon from partidas a,usuario b where b.cu=a.usuario_cu  and b.cu='1' and a.cp='"+ddd+"'");
		
		while (rs.next()) {
			
			
			bot=(boolean) rs.getBoolean(1);
			colorF=(boolean ) rs.getBoolean(2);
			auto=(boolean) rs.getBoolean(3);
			tiempobl=(int) rs.getInt(4);
			tiempone=(int) rs.getInt(5);
			if(tiempobl!=0 && tiempone!=0) {
				TiempoB=tiempobl;
				TiempoN=tiempone;
				time=true;
			}else {
				time=false;
				TiempoB=-1;
				TiempoN=-1;
			}
		}
		
		conexion.close();
	}
	static void CargarPartida(int ddd) {
		jugadasPartida.clear();
			try {
				cargarEspecificaciones(ddd);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			fig.clear();
			try {
				fig=leerFichas(ddd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File archivo = new File(jugadas);
			archivo.delete();

				ArrayList<String> jugadas= new ArrayList<>();
				try {
					jugadas = leerJugadas(ddd);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("jugadas:dfd "+jugadas);
				for(int i=jugadas.size()-1;i>=0;i--) {
					try {
						registrarJugadas(jugadas.get(i),false);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(jugadas.get(i).contains('B'+"")) {
						if(colorF) {
							b=false;
						}else {
							b=true;
						}
						
					}else {
						
						if(colorF) {
							b=true;
						}else {
							b=false;
						}
						
						
					}
				}
				System.out.println("b: "+b);
				if(time) {
					tab=new Tablero(TiempoB,TiempoN);
					tab.setVisible(true);
					Tablero.startTimer();
				}else {
					tab=new Tablero();
					tab.setVisible(true);
				}
				
				rearmar();
				tab.actualizar();
				Extras.crear(fig, figP);
				llenarTableroPB(tP,fig);
				llenarTableroN(tN,fig);
				llenarTableroB(tB,fig);
				band1=1;
				 
		if(!b) {
			conexionConBot(0, 0);
		}
		Tablero.isPlayer1Turn=b;
	}
	static String ultimaMovida(boolean fs) {
		String dfd=null;
		try {
			BufferedReader lector = new BufferedReader(new FileReader(jugadas));
			String linea;
			while((linea = lector.readLine())!= null) {
				
				
				String c = linea;
			
				
				if(fs) {
					if(c.contains('N'+"")) {
						dfd=c;
					}
				}else {
					if(c.contains('B'+"")) {
						dfd=c;
					}
				}
				
			}
			lector.close();
		} catch (IOException ioe) {
			//JOptionPane.showMessageDialog(null, ioe.toString() + "No se logro leer");
		}
		return dfd;
	}
	static ArrayList<String> mostrarPartidas(){
		 ArrayList<String> jugada = new ArrayList<String>();
		 int d=0;
		try {
			d=ultimaPartida();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		d=d-1;
		for(int i=1;i<=d;i++) {
			jugada.add("Partida "+i);
		}
			return jugada;
	}
	static void guardarPartida(String nombre,int tiempoB,int tiempoN) throws SQLException{
		int d=0,a=0,n=0;
		ArrayList<String> l=leerPersonasTexto();
		
		try {
			d=ultimaPartida();
			a=ultimaFicha();
			n=ultimoMovimientoD();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			Conexion con1=new Conexion();
			Connection conexion1 = (Connection) con1.getConexionPostgres();
			PreparedStatement s1;
			
			String query1="insert into partidas"
					+ "(cp, hora,date,usuario_cu,bot,colorf,auto,tiempob,tiempon,nombre) values "
					+ "(?,?,?,?,?,?,?,?,?,?)";
			try {
				s1=(PreparedStatement) conexion1.prepareStatement(query1);
				s1.setInt(1, d);
				s1.setTime(2,java.sql.Time.valueOf(LocalTime.now()));
				s1.setDate(3,java.sql.Date.valueOf(LocalDate.now()));
				s1.setInt(4, 1);
				s1.setBoolean(5, bot);
				s1.setBoolean(6, colorF);
				s1.setBoolean(7, auto);
				if(time) {
					s1.setInt(8, tiempoB);
					s1.setInt(9, tiempoN);
				}else {
					s1.setInt(8, 0);
					s1.setInt(9, 0);
				}
				s1.setString(10, nombre);
				s1.executeUpdate();
				System.out.println("Datos ingresados correctamente");
				
			} catch (SQLException e) {
				System.out.println("Productovendido"+e.getMessage());
			}
			
			conexion1.close();
		
		
		for(Pieza i:fig) {
			Conexion con=new Conexion();
			Connection conexion = (Connection) con.getConexionPostgres();
			PreparedStatement s;
			
			String query="insert into fichas"
					+ "(cf, ficha,estado,especial) values "
					+ "(?,?,?,?)";
			try {
				s=(PreparedStatement) conexion.prepareStatement(query);
				s.setInt(1, a);
				s.setString(2,i.obtenerPosicion());
				s.setBoolean(3, i.isEstado() );
				s.setBoolean(4, i.isEspecial());
				
				s.executeUpdate();
				System.out.println("Datos ingresados correctamente");
				
			} catch (SQLException e) {
				System.out.println("Productovendido"+e.getMessage());
			}
			registrarInterPartidaFig(d,a);
			a++;
			conexion.close();
		}
		System.out.println(l);
		for(int i=0;i<(l.size());i++) {
			Conexion con=new Conexion();
			Connection conexion = (Connection) con.getConexionPostgres();
			PreparedStatement s;
			
			String query="insert into movidas"
					+ "(cm, movidab) values "
					+ "(?,?)";
			try {
				s=(PreparedStatement) conexion.prepareStatement(query);
				s.setInt(1, n);
				s.setString(2,l.get(i));
				
				
				
				
				s.executeUpdate();
				System.out.println("Datos ingresados correctamente");
				
			} catch (SQLException e) {
				System.out.println("Productovendido"+e.getMessage());
			}
			registrarInterPartidaMov(d,n);
			n++;
			conexion.close();
		}
		System.out.println("Datos ingresados correctamente");
		
	}
	static void registrarInterPartidaFig(int d, int a)throws SQLException  {
		int i=0;
		try {
			i = ultimoRegInterParFig();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Conexion con=new Conexion();
		Connection conexion = con.getConexionPostgres();
		PreparedStatement s2;
		
		String query="insert into partidafichas"
				+ "(cpf, fichas_cf,partidas_cp) values "
				+ "(?,?,?)";
		try {
			s2=(PreparedStatement) conexion.prepareStatement(query);
			s2.setInt(1, i);
			s2.setInt(2, a);
			s2.setInt(3, d);
			
			s2.executeUpdate();
			System.out.println("Datos ingresados correctamente");
			
		} catch (SQLException e) {
			
			System.out.println("VentaProducto"+e.getMessage());
		}
		conexion.close();
		System.out.println("Datos ingresados correctamente");
	}
	static void registrarInterPartidaMov(int d, int a)throws SQLException  {
		int i=0;
		try {
			i = ultimoRegInterParMov();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Conexion con=new Conexion();
		Connection conexion = con.getConexionPostgres();
		PreparedStatement s2;
		
		String query="insert into partidamovidas"
				+ "(cpm,partidas_cp, movidas_cm) values "
				+ "(?,?,?)";
		try {
			s2=(PreparedStatement) conexion.prepareStatement(query);
			s2.setInt(1, i);
			s2.setInt(2, d);
			s2.setInt(3, a);
			
			s2.executeUpdate();
			System.out.println("Datos ingresados correctamente");
			
		} catch (SQLException e) {
			
			System.out.println("VentaProducto"+e.getMessage());
		}
		conexion.close();
		System.out.println("Datos ingresados correctamente");
	}
	static int ultimoRegInterParFig()throws SQLException {
		Conexion con=new Conexion();
		int ultimo=0,max=0;
		Connection conexion = con.getConexionPostgres();
		java.sql.Statement s= conexion.createStatement();
		ResultSet rs=  s.executeQuery("select c.cpf from partidas a,usuario b,partidafichas c where b.cu=a.usuario_cu and a.cp=c.partidas_cp   and b.cu='1'");
		
		while (rs.next()) {
			
			
			ultimo=(int) rs.getLong(1);
			//System.out.println(" dfd "+ultimo);
			if(ultimo>max) {
				max=ultimo;
			}
		}
		max++;
		conexion.close();
		
		return max;
	}
	static int ultimoRegInterParMov()throws SQLException {
		Conexion con=new Conexion();
		int ultimo=0,max=0;
		Connection conexion = con.getConexionPostgres();
		java.sql.Statement s= conexion.createStatement();
		ResultSet rs=  s.executeQuery("select c.cpm from partidas a,usuario b,partidamovidas c where b.cu=a.usuario_cu and a.cp=c.partidas_cp   and b.cu='1'");
		
		while (rs.next()) {
			
			
			ultimo=(int) rs.getLong(1);
			//System.out.println(" dfd "+ultimo);
			if(ultimo>max) {
				max=ultimo;
			}
		}
		max++;
		conexion.close();
		
		return max;
	}
	static int ultimaPartida()throws SQLException {
		Conexion con=new Conexion();
		int ultimo=0,max=0;
		Connection conexion = con.getConexionPostgres();
		java.sql.Statement s= conexion.createStatement();
		ResultSet rs=  s.executeQuery("select a.cp from partidas a,usuario b where b.cu=a.usuario_cu and b.cu='1'");
		
		while (rs.next()) {
			
			
			ultimo=(int) rs.getLong(1);
			//System.out.println(" dfd "+ultimo);
			if(ultimo>max) {
				max=ultimo;
			}
		}
		max++;
		conexion.close();
		
		return max;
	}
	static int ultimaFicha()throws SQLException {
		Conexion con=new Conexion();
		int ultimo=0,max=0;
		Connection conexion = con.getConexionPostgres();
		java.sql.Statement s= conexion.createStatement();
		ResultSet rs=  s.executeQuery("select d.cf from partidas a,usuario b,partidafichas c, fichas d where b.cu=a.usuario_cu and a.cp=c.partidas_cp and c.fichas_cf=d.cf  and b.cu='1'");
		
		while (rs.next()) {
			
			
			ultimo=(int) rs.getLong(1);
			//System.out.println(" dfd "+ultimo);
			if(ultimo>max) {
				max=ultimo;
			}
		}
		max++;
		conexion.close();
		
		return max;
	}
	
	static int ultimoMovimientoD()throws SQLException {
		Conexion con=new Conexion();
		int ultimo=0,max=0;
		Connection conexion = con.getConexionPostgres();
		java.sql.Statement s= conexion.createStatement();
		ResultSet rs=  s.executeQuery("select d.cm from  movidas d ");
		
		while (rs.next()) {
			
			
			ultimo=(int) rs.getLong(1);
			//System.out.println(" dfd "+ultimo);
			if(ultimo>max) {
				max=ultimo;
			}
		}
		max++;
		conexion.close();
		System.out.println("max" +max);
		return max;
	}
	 static ArrayList<String> leerJugadas(int num) throws SQLException {
		 int d=num;
		 ArrayList<String> jugada = new ArrayList<>();
		 Conexion con=new Conexion();
			Pieza p=null;
			Connection conexion = con.getConexionPostgres();
			java.sql.Statement s= conexion.createStatement();
			ResultSet rs=  s.executeQuery("select distinct a.movidab from movidas a, usuario b, partidas c, partidamovidas d where b.cu=c.usuario_cu and c.cp=d.partidas_cp and d.movidas_cm=a.cm and b.cu='1' and c.cp='"+num+"'");
			while (rs.next()) {
			
				jugada.add(rs.getString(1));
				
				//System.out.println(rs.getLong(1)+", "+);
			}
			conexion.close();
			
	        return ordenar(jugada);
	    }
	 public static ArrayList<String> ordenar(ArrayList<String> or){
		 ArrayList<String> b=new ArrayList<>(),n =new  ArrayList<>(),nuevo=new ArrayList<>();
		 if(or.get(or.size()-1).charAt(3)=='B') {
			// System.out.println(or);
			 return or;
		 }else {
			 for(String i: or) {
				 if(i.charAt(3)=='B') {
					 b.add(i);
				 }else {
					 n.add(i);
				 }
			 }
		 }
		 int cb=0,cn=0;
		 for(int i=0;i<(b.size()+n.size());i++) {
			 if(i%2==0) {
				 nuevo.add(n.get(cn));
				 cn++;
				 
			 }else {
				 nuevo.add(b.get(cb));
				 cb++;
			 }
		 }
		 System.out.println(nuevo);
		 return nuevo;
	 }
	
	static ArrayList<Pieza> leerFichas(int num) throws SQLException {
		int d=num;
		 ArrayList<Pieza> fg = new ArrayList<>();
		 Conexion con=new Conexion();
			Pieza p=null;
			Connection conexion = con.getConexionPostgres();
			java.sql.Statement s= conexion.createStatement();
			ResultSet rs=  s.executeQuery("select distinct a.ficha, a.estado, a.especial from fichas a, usuario b, partidas c, partidafichas d where b.cu=c.usuario_cu and c.cp=d.partidas_cp and d.fichas_cf=a.cf and b.cu='1'and c.cp='"+num+"'");
			while (rs.next()) {
				p =Extras.crearFicha(rs.getString(1), rs.getBoolean(2),rs.getBoolean(3));
				fg.add(p);
				//System.out.println(rs.getLong(1)+", "+);
			}
			conexion.close();
	        return fg;
	    }
	static void nuevaPartida(String d,boolean a,boolean c,boolean dbd) {
		jugadasPartida.clear();
		int tiempo=Extras.tiempo(d);
		colorF= c;
		auto=dbd;
		time=(tiempo!=-1);
		if(auto) {
		
		
			try {
				dfd2=Extras.traer( );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println(dfd2);
		}
		
		System.out.println("tiempo: "+tiempo+" booelan"+ a);
		 bot=a;
		fig.clear();
		crearTablero(fig,tiempo,c);
		
			b=c;
		
		Extras.limpiar(tP,tB,tN);
		Extras.crear(fig, figP);
		llenarTableroPB(tP,fig);
		llenarTableroN(tN,fig);
		llenarTableroB(tB,fig);
		band1=1;
		File archivo = new File(jugadas);
		archivo.delete();
		if(!c&& a) {
			conexionConBot(00, 0);
		}
	}
	static void registrarJugadas(String d,boolean xd) throws SQLException{
		jugadasPartida.add(d);
		int n=0;
		try {
			n = ultimoMovimientoD();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("dfsdfsd: "+n);
		if(xd) {
			if(!colorF) {
				if(d.charAt(3)=='B') {
					d=d.substring(0,3)+'N';
				}else {
					d=d.substring(0,3)+'B';
				}
			}
		}
		Conexion con=new Conexion();
		Connection conexion = (Connection) con.getConexionPostgres();
		PreparedStatement s;
		
		String query="insert into movidas"
				+ "(cm, movidab) values "
				+ "(?,?)";
		try {
			s=(PreparedStatement) conexion.prepareStatement(query);
			s.setInt(1, n);
			s.setString(2,d);
			
			
			
			
			s.executeUpdate();
			System.out.println("Datos ingresados correctamente");
			
		} catch (SQLException e) {
			System.out.println("Productovendido"+e.getMessage());
		}
		try {
			PrintWriter escritor = new PrintWriter( new FileWriter(jugadas,true));
			escritor.println(d);
			escritor.close();
			
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, ioe.toString() + "No se logro escribir");
		}
	}
	static ArrayList<String> leerPersonasTexto(){
		ArrayList<String> Jugadas = new ArrayList<String>();
		try {
			BufferedReader lector = new BufferedReader(new FileReader(jugadas));
			String linea;
			while((linea = lector.readLine())!= null) {
				
				
				String c = linea;
			
				
				
				Jugadas.add(c);
			}
			lector.close();
		} catch (IOException ioe) {
			//JOptionPane.showMessageDialog(null, ioe.toString() + "No se logro leer");
		}
		return Jugadas;
		
	}
	public static void timeIsOver(boolean t) {
		tiempoBo=false;
		ganador=t;
	}
	public static void mostrarLugares(String pos) {
		mov.clear();
		int c,f;
		for(Pieza i: fig) {
			if(i.obtenerPosicion().equals(pos)) {
				i.mostrarPosiblesMovimientos(tB,mov,false);
			}
		}
			
		for(String i: mov) {
			
			if(i.charAt(0)=='O' ) {
				if(i.equals("O-O-OB")||i.equals("O-O-ON")) {
						System.out.println("dfasdfasdfsadf"+ pos);
						if(i.charAt(5)=='B') {
							Tablero.boton[7][2].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\correcto.png"));
						}else {
							Tablero.boton[0][2].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\correcto.png"));
						}
					
					
					
				}else {
					if(i.charAt(3)=='B') {
						Tablero.boton[7][6].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\correcto.png"));
					}else {
						Tablero.boton[0][6].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\correcto.png"));
					}
				}
				
			}else {
				c=(int)(i.charAt(1)-97);
				f=8-(int)(i.charAt(2)-48);
				Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\correcto.png"));
			}
			
		}
		moverse=pos;
		
	}
	public static void mostrarLugaresJaque(String pos,ArrayList<String> jaques) {
		mov.clear();
		ArrayList<String> juga=new ArrayList<>();
		int c,f;
		for(Pieza i: fig) {
			if(i.obtenerPosicion().equals(pos)) {
				i.mostrarPosiblesMovimientos(tB,mov,false);
				
			}
		}
	
		for(String i:mov) {
			for(String j:jaques) {
				
				if(i.equals(j)) {
					
					juga.add(i);
				}
			}
		}
			
		for(String i: juga) {
			System.out.println("mostrar mov: "+i);
			if(i.charAt(0)=='O' ) {
				if(i.equals("O-O-OB")||i.equals("O-O-ON")) {
						//System.out.println("dfasdfasdfsadf"+ pos);
						if(i.charAt(5)=='B') {
							Tablero.boton[7][2].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\correcto.png"));
						}else {
							Tablero.boton[0][2].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\correcto.png"));
						}
					
					
					
				}else {
					if(i.charAt(3)=='B') {
						Tablero.boton[7][6].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\correcto.png"));
					}else {
						Tablero.boton[0][6].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\correcto.png"));
					}
				}
				
			}else {
				c=(int)(i.charAt(1)-97);
				f=8-(int)(i.charAt(2)-48);
				Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\correcto.png"));
			}
			
		}
		moverse=pos;
		
	}
	public static void rearmar() {
		//moverse="";
		String d,aux;
		int f,c;
		if(colorF) {
			for(Pieza i: fig) {
				if(i.isEstado()) {
					d=i.obtenerPosicion();
					c=(int)(d.charAt(1)-97);
					f=8-(int)(d.charAt(2)-48);
					switch(d.charAt(0)) {
					case 'R':
						if(d.substring(3).equals(""+'B')) {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Rey.png"));
						}else {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\ReyNegro.png"));
						}
						break;
					case 'D':
						if(d.substring(3).equals(""+'B')) {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Reina.png"));
						}else {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\ReinaNegra.png"));
						}
						break;
					case 'C':
						if(d.substring(3).equals(""+'B')) {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Caballo.png"));
						}else {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\CaballoNegro.png"));
						}
						break;
					case 'P':
						if(d.substring(3).equals(""+'B')) {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Peon.png"));
						}else {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\PeonNegro.png"));
						}
						break;
					case 'A':
						if(d.substring(3).equals(""+'B')) {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Alfil.png"));
						}else {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\AlfilNegro.png"));
						}
						break;
					case 'T':
						if(d.substring(3).equals(""+'B')) {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Torre.png"));
						}else {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\TorreNegra.png"));
						}
						break;
					}
					
				}
				
			}
		}else {
			for(Pieza i: fig) {
				if(i.isEstado()) {
					d=i.obtenerPosicion();
					c=(int)(d.charAt(1)-97);
					f=8-(int)(d.charAt(2)-48);
					switch(d.charAt(0)) {
					case 'R':
						if(d.substring(3).equals(""+'B')) {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\ReyNegro.png"));
						}else {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Rey.png"));
						}
						break;
					case 'D':
						if(d.substring(3).equals(""+'B')) {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\ReinaNegra.png"));
						}else {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Reina.png"));
						}
						break;
					case 'C':
						if(d.substring(3).equals(""+'B')) {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\CaballoNegro.png"));
						}else {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Caballo.png"));
						}
						break;
					case 'P':
						if(d.substring(3).equals(""+'B')) {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\PeonNegro.png"));
						}else {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Peon.png"));
						}
						break;
					case 'A':
						if(d.substring(3).equals(""+'B')) {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\AlfilNegro.png"));
						}else {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Alfil.png"));
						}
						break;
					case 'T':
						if(d.substring(3).equals(""+'B')) {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\TorreNegra.png"));
						}else {
							Tablero.boton[f][c].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Torre.png"));
						}
						break;
					}
					
				}
				
			}
		}
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(tB[i][j]=="____") {
					Tablero.boton[i][j].setIcon(new javax.swing.ImageIcon(""));
				}
			}
		}
		
	}
	public static void conexion(int fila, int columna) {
		String pos="";
		String posicionElegida=tB[fila][columna];
		char d='B';
		
		
		if(b) {
			d='B';
		}else {
			d='N';
		}
		
		if(marcado) {
			rearmar();
		}
		if(tiempoBo) {
			if(b) {
				if(!posicionElegida.equals("____" )&&(posicionElegida.substring(3).equals(""+d))) {
				if(!jaque(tB,fig,b,true)) {
					pos=listarFichasPosiblesJaque(tB,tN,tP,fig,figP,b,fichas,mov,movidasJaque,posicionElegida);
					//pos=listarFichasPosibles(tB,tN,fig,b,fichas,mov,posicionElegida);
					if(pos.length()!=0) {
						
						if(!pos.equals("dddd")) {
							marcado=true;
							
							mostrarLugaresJaque(pos,movidasJaque);
							//mostrarLugares(pos);
						}
						
					}
					
					if(fichas.size()==0) {
						if(colorF) {
							JOptionPane.showMessageDialog(null, "Tablas, Rey blanco ahogado");
						}else {
							JOptionPane.showMessageDialog(null, "Tablas, Rey negro ahogado");
						}
						
						
					//	o=10;
					}
				}else {
				
					pos=listarFichasPosiblesJaque(tB,tN,tP,fig,figP,b,fichas,mov,movidasJaque,posicionElegida);
					if(pos.length()!=0) {
						if(!pos.equals("dddd")) {
							marcado=true;
							marcadoJaque=true;
							
							
							mostrarLugaresJaque(pos,movidasJaque);
						}
						
						
						
						
					}
					
					if(movidasJaque.size()==0) {
						
						if(colorF) {
							JOptionPane.showMessageDialog(null, "Jaque mate, rey blanco pierde");
						}else {
							JOptionPane.showMessageDialog(null, "Jaque mate, rey negro pierde");
						}
					//	o=10;
					}
				}
				}else {
					if(marcado) {
						if(marcadoJaque) {
							rearmar();
							pos=moverse;
							String posic=""+(char)(columna+97)+(int)(56-(fila+48));
							//moverFicha(tB,tN,fig,b,mov,pos,posic);
							moverRey(tB,tN,fig,b,mov,pos,movidasJaque,posic,true);
							
							
						}else {
							rearmar();
							pos=moverse;
							String posic=""+(char)(columna+97)+(int)(56-(fila+48));
							moverFicha(tB,tN,fig,b,mov,pos,posic,movidasJaque,true);
							
						}
						if(movido) {
							b=!b;
							movido=false;
							Tablero.actualizar();
							marcado=false;
							marcadoJaque=false;
						}
						
					}
				}
			}else {
				if(!posicionElegida.equals("____" )&&(posicionElegida.substring(3).equals(""+d))) {
				if(!jaque(tB,fig,b,true)) {
					pos=listarFichasPosiblesJaque(tB,tN,tP,fig,figP,b,fichas,mov,movidasJaque,posicionElegida);
					if(pos.length()!=0) {
						
						if(!pos.equals("dddd")) {
							mostrarLugaresJaque(pos,movidasJaque);
							marcado=true;
						}
						
					}
					if(fichas.size()==0) {
						if(colorF) {
							JOptionPane.showMessageDialog(null, "Tablas, Rey negro ahogado");
						}else {
							JOptionPane.showMessageDialog(null, "Tablas, Rey blanco ahogado");
						}
						
					//	o=10;
					}
				}else {
					pos=listarFichasPosiblesJaque(tB,tN,tP,fig,figP,b,fichas,mov,movidasJaque,posicionElegida);
					if(pos.length()!=0) {
						marcado=true;
						marcadoJaque=true;
						
					
						mostrarLugaresJaque(pos,movidasJaque);
						
						//moverRey(tB,tN,fig,b,mov,pos,movidasJaque,pos);
						
					}
					
					if(fichas.size()==0) {
						
						if(colorF) {
							JOptionPane.showMessageDialog(null, "Jaque mate, rey negro pierde");
						}else {
							JOptionPane.showMessageDialog(null, "Jaque mate, rey blanco pierde");
						}
					//	o=10;
					}
				}
				}else {
					if(marcado) {
						if(marcadoJaque) {
							rearmar();
							
							pos=moverse;
							String posic=""+(char)(columna+97)+(int)(56-(fila+48));
							//moverFicha(tB,tN,fig,b,mov,pos,posic);
							moverRey(tB,tN,fig,b,mov,pos,movidasJaque,posic,true);
							
							
						}else {
							
							rearmar();
							pos=moverse;
							String posic=""+(char)(columna+97)+(int)(56-(fila+48));
							moverFicha(tB,tN,fig,b,mov,pos,posic,movidasJaque,true);
							
						}
						if(movido) {
							b=!b;
							movido=false;
							Tablero.actualizar();
							marcado=false;
							marcadoJaque=false;
							
						}
					}
				}
				
			}
		}else {
			if(colorF) {
				if(ganador){
					JOptionPane.showMessageDialog(tab, "¡Tiempo agotado! Negras ganan.");;
				}else {
					JOptionPane.showMessageDialog(tab, "¡Tiempo agotado! Blancas ganan.");
				}
			}else {
				if(ganador){
					JOptionPane.showMessageDialog(tab, "¡Tiempo agotado! Blancas ganan.");;
				}else {
					JOptionPane.showMessageDialog(tab, "¡Tiempo agotado! Negras ganan.");
				
			}
			
		}
		}
		
			
		
		 
	}
	public static void conexionConBot(int fila, int columna) {
		String pos="";
		String posicionElegida=tB[fila][columna];
		char d='B';
		
		
		if(b) {
			d='B';
		}else { 
			d='N';
		}
		
		if(marcado) {
			rearmar();
		}
		if(bots) {
		if(tiempoBo) {
			if(b) {
				if(!posicionElegida.equals("____" )&&(posicionElegida.substring(3).equals(""+d))) {
				if(!jaque(tB,fig,b,true)) {
					pos=listarFichasPosiblesJaque(tB,tN,tP,fig,figP,b,fichas,mov,movidasJaque,posicionElegida);
					//pos=listarFichasPosibles(tB,tN,fig,b,fichas,mov,posicionElegida);
					if(pos.length()!=0) {
						
						if(!pos.equals("dddd")) {
							marcado=true;
							
							mostrarLugaresJaque(pos,movidasJaque);
							
							//mostrarLugares(pos);
						}
						
					}
					
					if(fichas.size()==0) {
						if(colorF) {
							JOptionPane.showMessageDialog(null, "Tablas, Rey blanco ahogado");
						}else {
							JOptionPane.showMessageDialog(null, "Tablas, Rey Negro ahogado");
						}
						
						
					//	o=10;
					}
				}else {
				
					pos=listarFichasPosiblesJaque(tB,tN,tP,fig,figP,b,fichas,mov,movidasJaque,posicionElegida);
					if(pos.length()!=0) {
						if(!pos.equals("dddd")) {
							marcado=true;
							marcadoJaque=true;
							
							
							mostrarLugaresJaque(pos,movidasJaque);
						}
						
						
						
						
					}
					
					if(movidasJaque.size()==0) {
						
						if(colorF) {
							JOptionPane.showMessageDialog(null, "Jaque mate, rey blanco pierde");
						}else {
							JOptionPane.showMessageDialog(null, "Jaque mate, rey negro pierde");
						}
					//	o=10;
					}
				}
				}else {
					if(marcado) {
						if(marcadoJaque) {
							rearmar();
							pos=moverse;
							String posic=""+(char)(columna+97)+(int)(56-(fila+48));
							//moverFicha(tB,tN,fig,b,mov,pos,posic);
							moverRey(tB,tN,fig,b,mov,pos,movidasJaque,posic,true);
							
							
						}else {
							rearmar();
							pos=moverse;
							String posic=""+(char)(columna+97)+(int)(56-(fila+48));
							moverFicha(tB,tN,fig,b,mov,pos,posic,movidasJaque,true);
							
						}
						if(movido) {
							b=!b;
							rearmar();
							
							movido=false;
							Tablero.actualizar();
							Bot chessBot = new Bot();
					        bots=false;
					        // Crear un hilo para ejecutar el bot
					        Thread botThread = new Thread(chessBot);
					        
					        // Iniciar el hilo del bot
					        botThread.start();
						
							marcado=false;
							marcadoJaque=false;
						}
						
					}
				}
			}else {
				
				rearmar();
				
				movido=false;
				Tablero.actualizar();
				Bot chessBot = new Bot();
		        
		        // Crear un hilo para ejecutar el bot
		        Thread botThread = new Thread(chessBot);
		        
		        // Iniciar el hilo del bot
		        botThread.start();
				
				marcado=false;
				marcadoJaque=false;
				
			}
		}else {
			if(colorF) {
				if(ganador){
					JOptionPane.showMessageDialog(tab, "¡Tiempo agotado! Negras ganan.");;
				}else {
					JOptionPane.showMessageDialog(tab, "¡Tiempo agotado! Blancas ganan.");
				}
			}else {
				if(ganador){
					JOptionPane.showMessageDialog(tab, "¡Tiempo agotado! Blancas ganan.");;
				}else {
					JOptionPane.showMessageDialog(tab, "¡Tiempo agotado! Negras ganan.");
				
			}}
		}
		}
			
		
		 
	}
	
	public static String listarFichasPosiblesJaque(String[][] t1,String[][] t2,String[][] tP,ArrayList<Pieza> fig,ArrayList<Pieza> figP,boolean b, ArrayList<String> fichas, ArrayList<String> mov,ArrayList<String> movidas,String posicionElegida) {
		//Extras.copiar(fig,figP);
		String d = null,ficha="",aux1,aux2,ss;
		int colu,fila;
		boolean vaCo=false;
		char c;
		if(b) {
			c='B';
		}else {
			c='N';
		}
		movidas.clear();
			fichas.clear();
			
			if(b) {
				mostrar_t(t1);
			}else {
				mostrar_t(t2);
			}
			
			System.out.println("Fichas: ");
			for(Pieza i:fig) {
				if(i.obtenerPosicion().charAt(3)==c) {
					if(i.isEstado()) {
						mov.clear();	
				
					if(b) {
						i.mostrarPosiblesMovimientos(t1,mov,false);
					}else {
						i.mostrarPosiblesMovimientos(t1,mov,false);//k
					}
					if(mov.size()>0) {
				
					
						fichas.add(i.obtenerPosicion());
						
					}
					
				}
				
			}
			
			}
			if(fichas.size()==0) {
				return "";
			}
			
			for(int i=0;i<fichas.size();i++) {
				
				for(Pieza n:fig) {
					vaCo=false;
					ss=n.obtenerPosicion();
					
					if(n.isEstado()) {
						if(fichas.size()==0) {
							return "";
						}
						if(n.obtenerPosicion().equals(fichas.get(i))) {
							mov.clear();
							if(b) {
								n.mostrarPosiblesMovimientos(t1,mov,false);
								
							}else {
								n.mostrarPosiblesMovimientos(t1,mov,false);//k
							}
							for(String j: mov) {
								
								
								llenarTableroPB(tP,fig);
								
								//Extras.copiar(fig,figP);
								
											colu=(int)(ss.charAt(1)-97);
											fila=8-(int)(ss.charAt(2)-48);
											tP[fila][colu]="____";
											 
											n.cambiarPosicion(j);
											
											
											if(!j.substring(0,3).equals("O-O")) {
												colu=(int)(j.charAt(1)-97);
												fila=8-(int)(j.charAt(2)-48);
											}else {
												if(j.length()>4) {
													if(j.charAt(5)=='B') {
														colu=2;
														fila=7;
													}else {
														colu=2;
														fila=0;
													}
												}else {
													if(j.charAt(3)=='B') {
														colu=6;
														fila=7;
													}else {
														colu=6;
														fila=0;
													}
												}
											}
											
											
											if(tP[fila][colu].length()==4) {
												aux1=j.substring(1);
												for(Pieza l:fig) {
													
													aux2=l.obtenerPosicion().substring(1);
													
													if(aux2.substring(0, 2).equals(aux1.substring(0, 2))&&aux2.charAt(2)!=aux1.charAt(2)) {
														if(!l.isEstado()) {
														l.setEstado(false,1);
														}
													}
													
												}
												
												tP[fila][colu]=n.obtenerPosicion();
												
												
											}
											
										
										
										int f,cc;
										
										for(Pieza ii:fig) {
											if((ii.obtenerPosicion()).charAt(0)=='R' && (ii.obtenerPosicion()).charAt(3)==c){
													String dd=ii.obtenerPosicion();
													cc=(int)(dd.charAt(1)-97);
													f=8-(int)(dd.charAt(2)-48);
													
													if(!ii.jaque(tP,f,cc,true)) {
														
														//System.out.println("movidas permitidas: "+ii);
														movidas.add(j);
														vaCo=true;
													}
												
											}
										}
										if(tP[fila][colu].length()==4) {
											aux1=j.substring(1);
											for(Pieza l:fig) {
												
												aux2=l.obtenerPosicion().substring(1);
												
												if(aux2.substring(0, 2).equals(aux1.substring(0, 2))&&aux2.charAt(2)!=aux1.charAt(2)) {
													
														l.setEstado(true,1);
													
													
												}
												
											}
											
											
											
											
										}
										n.cambiarPosicion(ss);
							}
							if(!vaCo) {
								fichas.remove(fichas.get(i));
								if(i>0) {
									i--;
								}
								
							}
							
						}
					
					
				}
				}
				
			}
			
			System.out.println(movidas);
				
				ficha=posicionElegida;
			if((verificarSele(ficha,fig)||verificarRey(ficha,fig)||verificarTurno(ficha,b)||verificarLista(fichas,ficha))) {
				return "dddd";
			}else {
				return ficha;
			}
	}
	public static String listarFichasPosiblesJaqueBot(String[][] t1,String[][] t2,String[][] tP,ArrayList<Pieza> fig,ArrayList<Pieza> figP,boolean b, ArrayList<String> fichas, ArrayList<String> mov,ArrayList<String> movidas,boolean fno,boolean df) {
		//Extras.copiar(fig,figP);
		String d = null,ficha="",aux1,aux2,ss;
		int colu,fila;
		boolean vaCo=false;
		char c;
		if(b) {
			c='B';
		}else {
			c='N';
		}
		movidas.clear();
			fichas.clear();
			
			if(b) {
				mostrar_t(t1);
			}else {
				mostrar_t(t2);
			}
			
			System.out.println("Fichas: ");
			for(Pieza i:fig) {
				if(i.obtenerPosicion().charAt(3)==c) {
					if(i.isEstado()) {
						mov.clear();	
				
					if(b) {
						i.mostrarPosiblesMovimientos(t1,mov,false);
					}else {
						i.mostrarPosiblesMovimientos(t1,mov,false);//k
					}
					if(mov.size()>0) {
				
					
						fichas.add(i.obtenerPosicion());
						
					}
					
				}
				
			}
			
			}
			if(fichas.size()==0) {
				return "";
			}
			
			for(int i=0;i<fichas.size();i++) {
				
				for(Pieza n:fig) {
					vaCo=false;
					ss=n.obtenerPosicion();
					
					if(n.isEstado()) {
						if(fichas.size()==0) {
							return "";
						}
						if(n.obtenerPosicion().equals(fichas.get(i))) {
							mov.clear();
							if(b) {
								n.mostrarPosiblesMovimientos(t1,mov,false);
								
							}else {
								n.mostrarPosiblesMovimientos(t1,mov,false);//k
							}
							for(String j: mov) {
								
								
								llenarTableroPB(tP,fig);
								
								//Extras.copiar(fig,figP);
								
											colu=(int)(ss.charAt(1)-97);
											fila=8-(int)(ss.charAt(2)-48);
											tP[fila][colu]="____";
											 
											n.cambiarPosicion(j);
											
											
											if(!j.substring(0,3).equals("O-O")) {
												colu=(int)(j.charAt(1)-97);
												fila=8-(int)(j.charAt(2)-48);
											}else {
												if(j.length()>4) {
													if(j.charAt(5)=='B') {
														colu=2;
														fila=7;
													}else {
														colu=2;
														fila=0;
													}
												}else {
													if(j.charAt(3)=='B') {
														colu=6;
														fila=7;
													}else {
														colu=6;
														fila=0;
													}
												}
											}
											
											
											if(tP[fila][colu].length()==4) {
												aux1=j.substring(1);
												for(Pieza l:fig) {
													
													aux2=l.obtenerPosicion().substring(1);
													
													if(aux2.substring(0, 2).equals(aux1.substring(0, 2))&&aux2.charAt(2)!=aux1.charAt(2)) {
														if(!l.isEstado()) {
														l.setEstado(false,1);
														}
													}
													
												}
												
												tP[fila][colu]=n.obtenerPosicion();
												
												
											}
											
										
										
										int f,cc;
										
										for(Pieza ii:fig) {
											if((ii.obtenerPosicion()).charAt(0)=='R' && (ii.obtenerPosicion()).charAt(3)==c){
													String dd=ii.obtenerPosicion();
													cc=(int)(dd.charAt(1)-97);
													f=8-(int)(dd.charAt(2)-48);
													
													if(!ii.jaque(tP,f,cc,true)) {
														
														//System.out.println("movidas permitidas: "+ii);
														movidas.add(j);
														vaCo=true;
													}
												
											}
										}
										if(tP[fila][colu].length()==4) {
											aux1=j.substring(1);
											for(Pieza l:fig) {
												
												aux2=l.obtenerPosicion().substring(1);
												
												if(aux2.substring(0, 2).equals(aux1.substring(0, 2))&&aux2.charAt(2)!=aux1.charAt(2)) {
													
														l.setEstado(true,1);
													
													
												}
												
											}
											
											
											
											
										}
										n.cambiarPosicion(ss);
							}
							if(!vaCo) {
								fichas.remove(fichas.get(i));
								if(i>0) {
									i--;
								}
								
							}
							
						}
					
					
				}
				}
				
			}
			
			//System.out.println(movidas);
			
				ficha=calificandoMovidas(t1,t2,tP,fig,figP,b,fichas,mov,movidas,fno,df,dfd2);
				System.out.println("fichsssa: "+ficha);
				marcado=true;
			
				return ficha;
			
	}
	
	public static String calificandoMovidas(String[][] t1,String[][] t2,String[][] tP,ArrayList<Pieza> fig,ArrayList<Pieza> figP,boolean b, ArrayList<String> fichas, ArrayList<String> mov,ArrayList<String> movidas,boolean fno,boolean df,ArrayList<String> dfd2) {
		ArrayList<String> si=new ArrayList<String>();
		String d = null,ficha="",aux1,aux2,ss,anterior;
		int max=Integer.MIN_VALUE,count=0;
		int colu,fila;
		boolean vaCo=false;
		String fichaMax="";
		char c;
		if(b) {
			c='B';
		}else {
			c='N';
		}
		for(Pieza n:fig) {
			
			ss=n.obtenerPosicion();
			if(n.obtenerPosicion().charAt(3)==c) {
			if(n.isEstado()) {
				if(fichas.contains(n.obtenerPosicion())){
					
				
					mov.clear();
					if(b) {
						n.mostrarPosiblesMovimientos(t1,mov,false);
						
					}else {
						n.mostrarPosiblesMovimientos(t1,mov,false);//k
					}
					
					for(String j: movidas) {
						
							
						
						if(mov.contains(j)) {
						llenarTableroPB(tP,fig);
						
						//Extras.copiar(fig,figP);
						
									colu=(int)(ss.charAt(1)-97);
									fila=8-(int)(ss.charAt(2)-48);
									tP[fila][colu]="____";
									 
									n.cambiarPosicion(j);
									
									
									if(!j.substring(0,3).equals("O-O")) {
										colu=(int)(j.charAt(1)-97);
										fila=8-(int)(j.charAt(2)-48);
									}else {
										if(j.length()>4) {
											if(j.charAt(5)=='B') {
												colu=2;
												fila=7;
											}else {
												colu=2;
												fila=0;
											}
										}else {
											if(j.charAt(3)=='B') {
												colu=6;
												fila=7;
											}else {
												colu=6;
												fila=0;
											}
										}
									}
									
									
									if(tP[fila][colu].length()==4) {
										aux1=j.substring(1);
										for(Pieza l:fig) {
											
											aux2=l.obtenerPosicion().substring(1);
											
											if(aux2.substring(0, 2).equals(aux1.substring(0, 2))&&aux2.charAt(2)!=aux1.charAt(2)) {
												if(l.isEstado()) {
												l.setEstado(false,1);
												
												}
											}
											
										}
										
										tP[fila][colu]=n.obtenerPosicion();
										
										
									}
									
								
								
								int f,cc;
								
								int total=0;
								
								for(Pieza ii:fig) {
									//System.out.println(ii.obtenerPosicion());
									//System.out.println(ii.isEstado());
									if(ii.isEstado()) {
										
										if(b) {
											if(ii.obtenerPosicion().charAt(3)=='B') {
												total=total+ii.puntos();
											}else {
												total=total-ii.puntos();
											}
										}else {
											if(ii.obtenerPosicion().charAt(3)=='N') {
												total=total+ii.puntos();
											}else {
												total=total-ii.puntos();
											}
										}
									}
									
								}
								total=total+Extras.tablero(j,b,fig);
								if(!fno) {
									if(fichaDelBot1==null && fichaDelBot2==null) {
										anterior=fichaDelBot;
										System.out.println("fichaant1: "+anterior);
									}else {
										if(fichaDelBot1!=null) {
											anterior=fichaDelBot1;
											System.out.println("fichaant2: "+anterior);
										}else {
											anterior=fichaDelBot2;
											System.out.println("fichaant3: "+anterior);
										}
									}
									si=Extras.comprobar(anterior, dfd2);
										if(colorF) {
											if(df) {
												//System.out.println("fichaant: "+ultimaMovida(colorF));
												
												
												count=0;
												if(auto) {
												for(String ne:si) {
													if(ne.equals(j)) {
														count++;
														
													}
												}
												total=total+5*count;
												}
												
											}else {
												total=total+0;
											}
										}else {
											if(df) {
												total=total+0;
												
											}else {
												count=0;
												if(auto){
													for(String ne:si) {
														if(ne.equals(j)) {
															count++;
															
														}
													}
													total=total+5*count;
													}
											}
											
										}
										
									
								}
								
								
								if(fno) {
									System.out.println("asdfasdfsdfasdf"+b);
									total=total+movidaFuturo(j,tP,fig,!b);
									System.out.println("dfsdfd: "+total);
								}
								
								if(total>max) {
									max=total;
									
									fichaMax=j;
									
									if(!fno) {
										if(df) {
											fichaDelBot2=ss;
										}else {
											fichaDelBot1=ss;
										}
									}else {
										fichaDelBot=ss;
									}
									
									
								}
								
								
								if(tP[fila][colu].length()==4) {
									aux1=j.substring(1);
									for(Pieza l:fig) {
										
										aux2=l.obtenerPosicion().substring(1);
										
										if(aux2.substring(0, 2).equals(aux1.substring(0, 2))&&aux2.charAt(2)!=aux1.charAt(2)) {
											
												l.setEstado(true,1);
											
											
										}
										
									}
									
									
									
									
								}
								n.cambiarPosicion(ss);
								
								
								
								
					}
						
					
					}
				
			
			
		}
			}
			}
		}
		
		System.out.println("fichadelbot222: "+fichaDelBot1);
		System.out.println("fichamax: "+fichaMax);
		
		return fichaMax;
	}
	
	public static int movidaFuturo(String movida,String[][] tP,ArrayList<Pieza> fig,boolean b) {
		String movi=movida;
		
		int total=0;
		String[][] tp1 = new String[8][8];
		String[][] tp2 = new String[8][8];
		String[][] tp3 = new String[8][8];
		ArrayList<Pieza> figad=new ArrayList<Pieza>();
		ArrayList<String> mov=new ArrayList<String>(); 
	 ArrayList<String> fichas=new ArrayList<String>(); 
		 ArrayList<String> movidasJaque=new ArrayList<String>(); 
		 String moverse;
		 boolean marcadoJaque=false,marcado=false,bb=b;
		Extras.crear(fig, figad);
		Extras.copiarT(tP,tp1);
		System.out.println("fig: "+fig);
		System.out.println("figad: "+figad);
		String pos;
		int i=0;
		int dif=1;
		while(i<dif) {
		pos=listarFichasPosiblesJaqueBot(tp1,tp2,tp3,figad,figad,bb,fichas,mov,movidasJaque,false,bb);
		//System.out.println("movidasjaque+ "+movidasJaque);
		System.out.println("movida futuro+ "+bb);
		Extras.mostrar_t(tP);
			if(!jaque(tp1,figad,bb,true)) {
				System.out.println("adfdffddd");
				if(pos.length()!=0) {
					
					if(!pos.equals("dddd")) {
						moverse=pos;
						marcado=true;
					}
					 
				}
				
			}else {
				
				if(pos.length()!=0) {
					marcado=true;
					marcadoJaque=true;
					
				
					moverse=pos;
					
				}
				
				
			}
			if(movidasJaque.size()==0) {
				System.out.println("dfadsfsdfdsfdf");
				movido=true;
				marcado=false;
				marcadoJaque=false;
				
					if(bb) {
						total=total+900;
					}else {
						total=total-900;
					}
					i=dif;
			}
				if(marcado) {
					System.out.println("dfasdfsadfsdfsdfdfsd");
					if(marcadoJaque) {
						//rearmar();
						
						String posic=pos.substring(1,3);
						//moverFicha(tB,tN,fig,b,mov,pos,posic);
						if(bb) {
							moverRey(tp1,tp2,figad,bb,mov,fichaDelBot2,movidasJaque,posic,false);
						}else {
							moverRey(tp1,tp2,figad,bb,mov,fichaDelBot1,movidasJaque,posic,false);
						}
						 
						
						
					}else {
						
						//rearmar();
						if(!pos.equals("")) {
							String posic=pos.substring(1,3);
							
							System.out.println("posic: "+posic);
							System.out.println("ficha: "+fichaDelBot2);
							if(bb) {
								moverFicha(tp1,tp2,figad,bb,mov,fichaDelBot2,posic,movidasJaque,false);
							}else {
								moverFicha(tp1,tp2,figad,bb,mov,fichaDelBot1,posic,movidasJaque,false);
							}
							
						}
						
					}
					
				
					if(movido) {
						System.out.println("dafsdfd");
						if(!bb) {
							for(Pieza ii:figad) {
								//System.out.println(ii.obtenerPosicion());
								//System.out.println(ii.isEstado());
								if(ii.isEstado()) {
									if(bb) {
										if(ii.obtenerPosicion().charAt(3)=='B') {
											total=total+ii.puntos();
										}else {
											total=total-ii.puntos();
										}
									}else {
										if(ii.obtenerPosicion().charAt(3)=='N') {
											total=total+ii.puntos();
										}else {
											total=total-ii.puntos();
										}
									}
									System.out.println(ii.obtenerPosicion()+total);
									//System.out.println("puntos: "+ii.puntos() );
									
									
								}
								
							}
							i++;
							System.out.println("total:++ "+total);
							
						}
						
						
						bb=!bb;
						
						movido=false;
						//Tablero.actualizar();
						marcado=false;
						marcadoJaque=false;
					}
				}
		}
		fichaDelBot1=null;
		fichaDelBot2=null;
				return total;
	}
	public static boolean verificarLista(ArrayList<String> fichas,String ficha) {
		
		return !fichas.contains(ficha);
	}
	public static String listarFichasPosibles(String[][] t1,String[][] t2,ArrayList<Pieza> fig,boolean b,ArrayList<String> fichas,ArrayList<String> mov,String posicionElegida) {
		String d,ficha="",aux1,aux2;
		int colu,fila;
		char c;
		if(b) {
			c='B';
		}else {
			c='N';
		}
			fichas.clear();
			mov.clear();
			if(b) {
				mostrar_t(t1);
			}else {
				mostrar_t(t2);
			}
			
			System.out.println("Fichas: ");
			for(Pieza i:fig) {
			mov.clear();	
				
					if(b) {
						i.mostrarPosiblesMovimientos(t1,mov,false);
					}else {
						i.mostrarPosiblesMovimientos(t1,mov,false);//k
					}
			
			if(mov.size()>0) {
				if(i.obtenerPosicion().charAt(3)==c) {
					if(i.isEstado()) {
						fichas.add(i.obtenerPosicion());
					}
					
				}
				
			}
			}
			if(fichas.size()==0) {
				return "";
			}
			
				/*for(String i: fichas) {
					System.out.println(i);
				}*/
				ficha=posicionElegida;
			if(verificarSele(ficha,fig)||verificarRey(ficha,fig)||verificarTurno(ficha,b)){
				return "dddd";
			}else {
				return ficha;
			}
		
		
	}
	public static void ingresarFicha(ArrayList<Pieza> fig,String[][] t1) {
		int o,f,c,f1=0,c1=0,cont=0;
		String color,posicion;
		Pieza extra;
		do {
			menuColor();
			o=Extras.leer("Ingrese una opcion: ");
			
		}while(o<0||o>2);
		if(o==1) {
			color="Blancas";
		}else {
			color="Negras";
		}
		do {
			menuFicha();
			o=Extras.leer("Ingrese una opcion: ");
			
		}while(o<0||o>6);
		switch(o) {
		case 1:
			for(Pieza i:fig) {
				if(i.obtenerPosicion().charAt(0)=='R') {
					cont++;
				}
			}
			if(cont<2) {
				do {
					posicion=Extras.leer_S("Ingrese la posicion del rey de las fichas "+color+" :(ej: Re2) " );
					posicion=posicion+color.charAt(0);
					extra=new Rey(posicion,color);
					c=(int)(posicion.charAt(1)-97);
					f=8-(int)(posicion.charAt(2)-48);
					
					t1[f][c]=posicion;
					if(extra.jaque(t1,f,c,true)) {
						System.out.println("Si ingresa el rey en esta posicion, estara en jaque coloquela en otra posicion");
						
						f1=f;
						c1=c;
					}
				
				}while(verificarIng(posicion,fig)|| extra.jaque(t1,f,c));
				t1[f1][c1]="____";
				
				fig.add(extra);
			}else {
				System.out.println("Ya hay dos reyes en el tablero");
			}
			
			break;
		case 2:
			do {
				posicion=Extras.leer_S("Ingrese la posicion de la Dama:(ej: Te4) " );
				posicion=posicion+color.charAt(0);
			}while(verificarIng(posicion,fig));
		
			extra=new Dama(posicion,color);
			fig.add(extra);
			c=(int)(posicion.charAt(1)-97);
			f=8-(int)(posicion.charAt(2)-48);
			
			t1[f][c]=posicion;
			break;
		case 3:
			do {
				posicion=Extras.leer_S("Ingrese la posicion del Peon:(ej: Te4) " );
				posicion=posicion+color.charAt(0);
			}while(verificarIng(posicion,fig));
		
			extra=new Peon(posicion,color);
			fig.add(extra);
			c=(int)(posicion.charAt(1)-97);
			f=8-(int)(posicion.charAt(2)-48);
			
			t1[f][c]=posicion;
			break;
		case 4:
			do {
				posicion=Extras.leer_S("Ingrese la posicion del Alfil:(ej: Te4) " );
				posicion=posicion+color.charAt(0);
			}while(verificarIng(posicion,fig));
		
			extra=new Alfil(posicion,color);
			fig.add(extra);
			c=(int)(posicion.charAt(1)-97);
			f=8-(int)(posicion.charAt(2)-48);
			
			t1[f][c]=posicion;
			break;
		case 5:
			do {
				posicion=Extras.leer_S("Ingrese la posicion del caballo:(ej: Te4) " );
				posicion=posicion+color.charAt(0);
			}while(verificarIng(posicion,fig));
		
			extra=new Caballo(posicion,color);
			fig.add(extra);
			c=(int)(posicion.charAt(1)-97);
			f=8-(int)(posicion.charAt(2)-48);
			
			t1[f][c]=posicion;
			break;
		case 6:
			do {
				posicion=Extras.leer_S("Ingrese la posicion de la torre:(ej: Te4) " );
				posicion=posicion+color.charAt(0);
			}while(verificarIng(posicion,fig));
		
			extra=new Torre(posicion,color);
			fig.add(extra);
			c=(int)(posicion.charAt(1)-97);
			f=8-(int)(posicion.charAt(2)-48);
			
			t1[f][c]=posicion;
			break;
		}
	}
	public static void menuColor() {
		System.out.println("Ingrese el color de la ficha a ingresar: ");
		System.out.println("1.- Blancas");
		System.out.println("2.- Negras");
	}
	public static void menuFicha() {
		System.out.println("Ingrese el color de la ficha a ingresar: ");
		System.out.println("1.- Rey");
		System.out.println("2.- Dama");
		System.out.println("3.- Peon");
		System.out.println("4.- Alfil");
		System.out.println("5.- Caballo");
		System.out.println("6.- Torre");
	}
	public static void moverRey(String[][] t1,String[][] t2,ArrayList<Pieza> fig, boolean b,ArrayList<String> mov,String pos,ArrayList<String> movimientos,String movElegido,boolean sd) {
		boolean bandera=true;
		mov.clear();
		char c;
		
		String ficha = null,d = null,aux2,aux1;
		int colu,fila; 
		if(b) {
			c='B';
		}else {
			c='N';
		}
		ficha=pos;
		for(Pieza i:fig) {
			
			if(i.obtenerPosicion().equals(ficha)) {
				
				if(b) {
					i.mostrarPosiblesMovimientos(t1,mov,false);
				}else {
					i.mostrarPosiblesMovimientos(t1,mov,false);//k
				}
				
				
			}
			
		}
		for(String i: movimientos) {
			if(i.charAt(0)==pos.charAt(0)) {
				System.out.println(i);
			}
		}
		
		System.out.println("d: "+movElegido);
		for(String dfd:mov) {
		System.out.println("dfd: "+dfd);
		
			if(dfd.substring(1,3).equals(movElegido)) {
				d=dfd;
			}
		}
		
		if(verificarSele(d,fig)|| verificarMov(d,mov)||verificarMov(d,movimientos)) {
			bandera=false;
		}else {
			bandera=true;
		}
		if(bandera) {
			System.out.println("si: "+d);
			if(sd)
				try {
					registrarJugadas(d,true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			movido=true;
			for(Pieza i:fig) {
				
				if(i.obtenerPosicion().equals(ficha)) {
					
					colu=(int)(ficha.charAt(1)-97);
					fila=8-(int)(ficha.charAt(2)-48);
					t1[fila][colu]="____";
					i.cambiarPosicion(d);
					if(i.obtenerPosicion().charAt(0)=='P') {
						i.especial(true);
					}
					if(i.obtenerPosicion().charAt(0)=='R') {
						i.especial(false);
					}
					colu=(int)(d.charAt(1)-97);
					fila=8-(int)(d.charAt(2)-48);
					if(t1[fila][colu].length()==4) {
						
						for(Pieza j:fig) {
							aux1=d.substring(1);
							aux2=j.obtenerPosicion().substring(1);
							
							if(aux2.substring(0, 2).equals(aux1.substring(0, 2))&&aux2.charAt(2)!=aux1.charAt(2)) {
								
								j.setEstado(false,2);
							}
							
						}
						t1[fila][colu]=d;
					}else {
						t1[fila][colu]=d;
					}
					
					
				}
				
				
			}
			for(int i=0;i<t2.length;i++) {
				for(int j=0;j<t2[0].length;j++) {
					t2[i][j]="____";
				}
				
				
			}
			if(b) {
				llenarTableroN(t2,fig);
				rearmar();
			}else {
				llenarTableroB(t2,fig);
				rearmar();
			}
		}
		Tablero.switchTurn();
	}
	public static boolean jaque(String[][] t,ArrayList<Pieza> fig, boolean b,boolean band) {
		char c;
		int f,cc;
		if(b) {
			c='B';
		}else {
			c='N';
		}
		for(Pieza i:fig) {
			if((i.obtenerPosicion()).charAt(0)=='R' && (i.obtenerPosicion()).charAt(3)==c){
					String d=i.obtenerPosicion();
					cc=(int)(d.charAt(1)-97);
					f=8-(int)(d.charAt(2)-48);
					System.out.println(i.obtenerPosicion());
					return i.jaque(t,f,cc,band);
				
			}
		}
		return false;
			
	}
	public static void moverFicha(String[][] t1,String[][] t2, ArrayList<Pieza> fig, boolean b,ArrayList<String> mov,String pos,String movElegido,ArrayList<String> movidasJaque,boolean sd) {
		// TODO Auto-generated method stub
		boolean fd=false;
		String d = null,ficha,aux1,aux2,z = null,pos1;
		int colu,fila;
		char c;
		boolean bandera=true;
		Pieza aux=null;
		String torre;
		if(b) {
			c='B';
		}else {
			c='N';
		}
			mov.clear();
			if(b) {
				mostrar_t(t1);
				
			}
			
			
			ficha=pos;
			for(Pieza i:fig) {
				if(i.isEstado()) {
					if(i.obtenerPosicion().equals(ficha)) {
						if(b) {
							
							i.mostrarPosiblesMovimientos(t1,mov,true);
						}else {
							
							i.mostrarPosiblesMovimientos(t1,mov,true);//k
						}
						
						
					}
				}
				
				
			}
			System.out.println(movidasJaque);
			System.out.println("ficha seleccionada: "+movElegido);
			String dfs="";
			if(!movElegido.equals("g1")&&!movElegido.equals("g8")&&!movElegido.equals("c1")&&!movElegido.equals("c8")) {
				dfs=ficha.charAt(0)+movElegido+ficha.charAt(3);
				System.out.println("ficha: "+dfs);
			}else {
				
				if(ficha.charAt(0)=='R') {
					if(ficha.equals("Re1B")||ficha.equals("Re8N")) {
						
					
					if(movElegido.equals("g1")||movElegido.equals("g8")) {
						if(movElegido.equals("g1")) {
							dfs="O-OB";
						}else {
							dfs="O-ON";
						}
						
					}else {
						if(movElegido.equals("c1")) {
							dfs="O-O-OB";
						}else {
							dfs="O-O-ON";
						}
					}
					}else {
						dfs=ficha.charAt(0)+movElegido+ficha.charAt(3);
					}
				}else {
					dfs=ficha.charAt(0)+movElegido+ficha.charAt(3);
				}
				
			}
			
			if(movidasJaque.contains(dfs)) {
			if(mov.size()>0) {
				for(String dfd:mov) {
					
					if(dfd.charAt(0)=='O') {
						if(!(dfd.length()>4)) {
							if(movElegido.equals("g1")||movElegido.equals("g8")) {
								if(movElegido.equals("g1")) {
									d="O-OB";
								}else {
									d="O-ON";
								}
								
							}
						}else {
							
							if(movElegido.equals("c1")||movElegido.equals("c8")) {
								if(movElegido.equals("c1")) {
									d="O-O-OB";
								}else {
									d="O-O-ON";
								}
								
							}
						}
					}
					
					if(dfd.substring(1,3).equals(movElegido)) {
						d=dfd;
					}
				}
				
				
				if(d!=null) {
					if(verificarSele(d,fig)|| verificarMov(d,mov)) {
						bandera=false;
					}else {
						bandera=true;
					}
				}else {
					bandera=false;
				}
				
				
				if(bandera) {
					
					if(sd)
						try {
							registrarJugadas(d,true);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					for(Pieza i:fig) {
						
						if(i.obtenerPosicion().equals(ficha)) {
							colu=(int)(ficha.charAt(1)-97);
							fila=8-(int)(ficha.charAt(2)-48);
							t1[fila][colu]="____";
							i.cambiarPosicion(d);
							//System.out.println("dfasdfasdfasdfasdfasdfasdfsdf");
							if(i.obtenerPosicion().charAt(0)=='P') {
								if(sd) {
									z=i.especial(true);
								}else {
									
									int ff=8-(int)(i.obtenerPosicion().charAt(2)-48);
									if(ff==0 ||ff==7) {
										z="D"+i.obtenerPosicion().substring(1);
										
									}else {
										z=i.especial(true);
									}
								}
								
								//System.out.println("z: "+z);
								//System.out.println("d: "+d );
								if(z!=d) {
									
									aux=Extras.ficha(z);
									i.setEstado(false,2);
									//fig.remove(i);
									//fig.add(aux);
									fd=true;
									d=z;
								}
							}
							if(d.substring(0, 3).equals("O-O") ){
								
								if(d.length()==4) {
									if(d.substring(0, 3).equals("O-O") ) {
										if(b) {
											//System.out.println("dfsdfsdfsdfsdfsdfsdf");
											torre="Th1B";
											pos1="Rg1B";
											d=pos1;
											i.cambiarPosicion(pos1);
											i.especial(false);
											pos1="Tf1B";
										}else{
											pos1="Rg8N";
											torre="Th8N";
											d=pos1;
											i.cambiarPosicion(pos1);
											i.especial(false);
											pos1="Tf8N";
											
										}
										
										
										for(Pieza w:fig) {
											
											if(w.obtenerPosicion().equals(torre)) {
												
												colu=(int)(w.obtenerPosicion().charAt(1)-97);
												fila=8-(int)(w.obtenerPosicion().charAt(2)-48);
												t1[fila][colu]="____";
												w.cambiarPosicion(pos1);
												colu=(int)(w.obtenerPosicion().charAt(1)-97);
												fila=8-(int)(w.obtenerPosicion().charAt(2)-48);
												t1[fila][colu]=w.obtenerPosicion();
											}
										}
									}
								}else {
									if(d.substring(0, 5).equals("O-O-O") ) {
										
										
										if(b) {
											torre="Ta1B";
											pos1="Rc1B";
											d=pos1;
											i.cambiarPosicion(pos1);
											i.especial(false);
											pos1="Td1B";
										}else{
											torre="Ta8N";
											pos1="Rc8N";
											d=pos1;
											i.cambiarPosicion(pos1);
											i.especial(false);
											pos1="Td8N";
											
										}
										for(Pieza w:fig) {
											
											if(w.obtenerPosicion().equals(torre)) {
												colu=(int)(w.obtenerPosicion().charAt(1)-97);
												fila=8-(int)(w.obtenerPosicion().charAt(2)-48);
												t1[fila][colu]="____";
												w.cambiarPosicion(pos1);
												colu=(int)(w.obtenerPosicion().charAt(1)-97);
												fila=8-(int)(w.obtenerPosicion().charAt(2)-48);
												t1[fila][colu]=w.obtenerPosicion();
											}
										}
									}
								}
								
								
							}
							if(i.obtenerPosicion().charAt(0)=='R') {
								i.especial(false);
							}
							
							colu=(int)(d.charAt(1)-97);
							fila=8-(int)(d.charAt(2)-48);
							if(t1[fila][colu].length()==4) {
								
								for(Pieza j:fig) {
									aux1=d.substring(1);
									aux2=j.obtenerPosicion().substring(1);
									//System.out.println("aux1: "+aux1);
									//System.out.println("aux2: "+aux2);
									if(aux2.substring(0, 2).equals(aux1.substring(0, 2))&&aux2.charAt(2)!=aux1.charAt(2)) {
										
										j.setEstado(false,2);
									}
									
								}
								t1[fila][colu]=d;
							}else {
								t1[fila][colu]=d;
							}
							
							
						}
						
						
					}
					if(fd) {
						fig.add(aux);
						
					}
					movido=true;
					if(b) {
						
						llenarTableroN(t2,fig);
						rearmar();
					
						mostrar_t(t1);
						
					}else {
						llenarTableroB(t2,fig);
						rearmar();
					}
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "La ficha no puede moverse elija otra");
				
				
			}
			}
			Tablero.switchTurn();		
	}
	public static void fBot(String[][] t1,String[][] t2, ArrayList<Pieza> fig, boolean b,ArrayList<String> mov,String movElegido) {
		// TODO Auto-generated method stub
		boolean fd=false;
		String d = null,ficha,aux1,aux2,z,pos1;
		int colu,fila;
		char c;
		boolean bandera=true;
		Pieza aux=null;
		String torre;
		if(b) {
			c='B';
		}else {
			c='N';
		}
			mov.clear();
			if(b) {
				mostrar_t(t1);
				
			}
			
			
			ficha=fichaDelBot;
			for(Pieza i:fig) {
				if(i.isEstado()) {
					if(i.obtenerPosicion().equals(ficha)) {
						if(b) {
							
							i.mostrarPosiblesMovimientos(t1,mov,true);
						}else {
							
							i.mostrarPosiblesMovimientos(t1,mov,true);//k
						}
						
						
					}
				}
				
				
			}
			
			
			if(mov.size()>0) {
				for(String dfd:mov) {
					
					if(dfd.charAt(0)=='O') {
						if(!(dfd.length()>4)) {
							if(movElegido.equals("g1")||movElegido.equals("g8")) {
								if(movElegido.equals("g1")) {
									d="O-OB";
								}else {
									d="O-ON";
								}
								
							}
						}else {
							
							if(movElegido.equals("c1")||movElegido.equals("c8")) {
								if(movElegido.equals("c1")) {
									d="O-O-OB";
								}else {
									d="O-O-ON";
								}
								
							}
						}
					}
					
					if(dfd.substring(1,3).equals(movElegido)) {
						d=dfd;
					}
				}
				
				
				if(d!=null) {
					if(verificarSele(d,fig)|| verificarMov(d,mov)) {
						bandera=false;
					}else {
						bandera=true;
					}
				}else {
					bandera=false;
				}
				
				
				if(bandera) {
					movido=true;
					try {
						registrarJugadas(d,true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(Pieza i:fig) {
						
						if(i.obtenerPosicion().equals(ficha)) {
							colu=(int)(ficha.charAt(1)-97);
							fila=8-(int)(ficha.charAt(2)-48);
							t1[fila][colu]="____";
							i.cambiarPosicion(d);
							if(i.obtenerPosicion().charAt(0)=='P') {
								
								z=i.especial(true);
								
								if(z!=d) {
									
									aux=Extras.ficha(z);
									i.setEstado(false,2);
									//fig.remove(i);
									//fig.add(aux);
									fd=true;
									d=z;
								}
							}
							if(d.substring(0, 3).equals("O-O") ){
								
								if(d.length()==4) {
									if(d.substring(0, 3).equals("O-O") ) {
										if(b) {
											torre="Th1B";
											pos1="Rg1B";
											d=pos1;
											i.cambiarPosicion(pos1);
											i.especial(false);
											pos1="Tf1B";
										}else{
											pos1="Rg8N";
											torre="Th8N";
											d=pos1;
											i.cambiarPosicion(pos1);
											i.especial(false);
											pos1="Tf8N";
											
										}
										
										
										for(Pieza w:fig) {
											
											if(w.obtenerPosicion().equals(torre)) {
												
												colu=(int)(w.obtenerPosicion().charAt(1)-97);
												fila=8-(int)(w.obtenerPosicion().charAt(2)-48);
												t1[fila][colu]="____";
												w.cambiarPosicion(pos1);
												colu=(int)(w.obtenerPosicion().charAt(1)-97);
												fila=8-(int)(w.obtenerPosicion().charAt(2)-48);
												t1[fila][colu]=w.obtenerPosicion();
											}
										}
									}
								}else {
									if(d.substring(0, 5).equals("O-O-O") ) {
										
										
										if(b) {
											torre="Ta1B";
											pos1="Rc1B";
											d=pos1;
											i.cambiarPosicion(pos1);
											i.especial(false);
											pos1="Td1B";
										}else{
											torre="Ta8N";
											pos1="Rc8N";
											d=pos1;
											i.cambiarPosicion(pos1);
											i.especial(false);
											pos1="Td8N";
											
										}
										for(Pieza w:fig) {
											
											if(w.obtenerPosicion().equals(torre)) {
												colu=(int)(w.obtenerPosicion().charAt(1)-97);
												fila=8-(int)(w.obtenerPosicion().charAt(2)-48);
												t1[fila][colu]="____";
												w.cambiarPosicion(pos1);
												colu=(int)(w.obtenerPosicion().charAt(1)-97);
												fila=8-(int)(w.obtenerPosicion().charAt(2)-48);
												t1[fila][colu]=w.obtenerPosicion();
											}
										}
									}
								}
								
								
							}
							if(i.obtenerPosicion().charAt(0)=='R') {
								i.especial(false);
							}
							
							colu=(int)(d.charAt(1)-97);
							fila=8-(int)(d.charAt(2)-48);
							if(t1[fila][colu].length()==4) {
								
								for(Pieza j:fig) {
									aux1=d.substring(1);
									aux2=j.obtenerPosicion().substring(1);
									//System.out.println("aux1: "+aux1);
									//System.out.println("aux2: "+aux2);
									if(aux2.substring(0, 2).equals(aux1.substring(0, 2))&&aux2.charAt(2)!=aux1.charAt(2)) {
										
										j.setEstado(false,2);
									}
									
								}
								t1[fila][colu]=d;
							}else {
								t1[fila][colu]=d;
							}
							
							
						}
						
						
					}
					if(fd) {
						fig.add(aux);
						
					}
					
					if(b) {
						
						llenarTableroN(t2,fig);
						//Frearmar();
					
						mostrar_t(t1);
						
					}else {
						llenarTableroB(t2,fig);
						//rearmar();
					}
				}
				
			}
	}
	public static boolean verificarMov(String ficha,ArrayList<String> mov) {
			
					for(String j: mov) {
						if(j.equals(ficha)) {
							return false;
						}
					}
		return true;
	}
	public static boolean verificarRey(String ficha,ArrayList<Pieza> fig) {
	
		for(Pieza i: fig) {
			if(i.obtenerPosicion().equals(ficha)) {
				return false;
			}
		}
			
		return true;
	}
	public static boolean verificarTurno(String ficha,boolean b) {
		boolean f=true;
		if(b) {
			if(ficha.charAt(3)=='B') {
				f=false;
			}
			
		}else {
			if(ficha.charAt(3)=='N') {
				f=false;
			}
		}
return f;
		
	}
	public static void mostrar_t(String[][] t) {
		int num=8;
		for(int i=0;i<t.length;i++) {
			for(int j=0;j<t[0].length;j++) {
				System.out.print(t[i][j]+"\t");
			}
			System.out.println();
			
		}
	}
	
	public static void llenarTableroB(String [][] t,ArrayList<Pieza> piezas) {
		String d,aux;
		int f,c;
		for(Pieza i: piezas) {
			if(i.isEstado()) {
				d=i.obtenerPosicion();
				c=(int)(d.charAt(1)-97);
				f=8-(int)(d.charAt(2)-48);
				
				t[f][c]=d;
			}
			
		}
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(t[i][j]==null) {
					t[i][j]="____";
				}
			}
		}
		mostrar_t(t);
		
		
	}
	public static void llenarTableroPB(String [][] t,ArrayList<Pieza> piezas) {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				
					t[i][j]=null;
				
			}
		}
		
		String d,aux;
		int f,c;
		for(Pieza i: piezas) {
			if(i.isEstado()) {
				d=i.obtenerPosicion();
				c=(int)(d.charAt(1)-97);
				f=8-(int)(d.charAt(2)-48);
				
				t[f][c]=d;
			}
			
		}
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(t[i][j]==null) {
					t[i][j]="____";
				}
			}
		}
		
		
		
	}
	public static void llenarTableroN(String [][] t,ArrayList<Pieza> piezas) {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				
					t[i][j]=null;
				
			}
		}
		String d,aux;
		int f,c;
		for(Pieza i: piezas) {
			if(i.isEstado()) {
				d=i.obtenerPosicion();
				c=(int)(d.charAt(1)-97);
				f=8-(int)(d.charAt(2)-48);
				f=7-f;
				t[f][c]=d;
			}
			
		}
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(t[i][j]==null) {
					t[i][j]="____";
				}
			}
		}
		//mostrar_t(t);
		
		
	}
	
	public static void crearTablero(ArrayList<Pieza> piezas,int tiempo,boolean colorF) {
		String posicion,color="Negras";
		char columna='e';
		int fila=8,aux=-1;
		int c,f;
		if(tiempo==-1) {
			tab=new Tablero();
			tab.setVisible(true);
			tab.crearTablero(false,colorF);
		}else {
			tab=new Tablero(tiempo);
			tab.setVisible(true);
			tab.crearTablero(true,colorF);
		}
		
		for(int i=0;i<2;i++) {
			
			posicion="R"+columna+fila+color.charAt(0);
			
			Rey rey=new Rey(posicion,color);
			piezas.add(rey);
			
			
			 
			columna='d';
			posicion="D"+columna+fila+color.charAt(0);
			Dama dama=new Dama(posicion,color);
			piezas.add(dama);
			columna='c';
			for(int j=0;j<2;j++) {
				posicion="A"+columna+fila+color.charAt(0);
				Alfil alfil=new Alfil(posicion,color);
				piezas.add(alfil);
				columna='f';
			}
			columna='b';
			for(int j=0;j<2;j++) {
				posicion="C"+columna+fila+color.charAt(0);
				Caballo caballo=new Caballo(posicion,color);
				piezas.add(caballo);
				columna='g';
			}
			columna='a';
			for(int j=0;j<2;j++) {
				posicion="T"+columna+fila+color.charAt(0);
				Torre torre=new Torre(posicion,color);
				piezas.add(torre);
				columna='h';
			}
			if(color.equals("Negras")) {
				fila=7;
			}else {
				fila=2;
			}
			
			columna='a';
			for(int j=0;j<8;j++) {
			
				
				posicion="P"+(char)(columna+j)+(fila)+color.charAt(0);
				
				Peon peon=new Peon(posicion,color);
				piezas.add(peon);
				
			}
			color="Blancas";
			fila=1;
			aux=1;
			columna='e';
		}
	}
	public static void mostrar(String[][] t) {
		for(int i=0;i<t.length;i++) {
			for(int j=0;j<t[0].length;j++) {
				if(t[i][j].length()==1) {
					System.out.print(""+"\t");
				}else {
					System.out.print(t[i][j]+"\t");
				}
			}
			System.out.println();
			
		}
	}


public static boolean verificarIng(String d,ArrayList<Pieza> piezas) {
	boolean b=true;
	boolean f=true;
	String ss=d.substring(1,3);
	for(Pieza i:piezas) {
		if((ss.equals(i.obtenerPosicion().substring(1, 3)))) {
			b=false;
		}
	}
	if(b) {
		if(d.length()==4) {
			if(d.charAt(0)=='R' || d.charAt(0)=='D' || d.charAt(0)=='P' || d.charAt(0)=='C' || d.charAt(0)=='A' || d.charAt(0)=='T' ){
				
				int s=(int)(d.charAt(2))-48;
				if(s>0 && s<=8) {
					
					for(int i=0;i<8;i++) {
						if(d.charAt(1)==(char)('a'+i)) {
							f=false;
						}
					}
				}else {
					
					System.out.println("Posicion ingresada incorrectamente");
				}
			}else {
				
				System.out.println("Posicion ingresada incorrectamente");
			}
		}else {
			
			System.out.println("Posicion ingresada incorrectamente");
		}
	}else {
		System.out.println("Posicion ocupada por otra ficha");
	}
	return f;
}
public static boolean verificarSele(String d,ArrayList<Pieza> piezas) {
	
	boolean f=true;
	if(d!=null) {
		 
	
	if(d.length()>0) {
		if(d.substring(0, 3).equals("O-O")) {
			return false;
		}else {
			if(d.length()>4) {
				if(d.substring(0, 5).equals("O-O-O")) {
					return false;
				}
			}
		}
	}else {
		return false;
	}
	
		if(d.length()==4) {
			if(d.charAt(0)=='R' || d.charAt(0)=='D' || d.charAt(0)=='P' || d.charAt(0)=='C' || d.charAt(0)=='A' || d.charAt(0)=='T' ){
				
				if(d.charAt(3)=='N' || d.charAt(3)=='B') {
					int s=(int)(d.charAt(2))-48;
					if(s>0 && s<=8) {
						
						for(int i=0;i<8;i++) {
							if(d.charAt(1)==(char)('a'+i)) {
								f=false;
							}
						}
					}else {
						System.out.println("Posicion ingresada incorrectamente");
					}
				}
			}else {
				System.out.println("Posicion ingresada incorrectamente");
			}
		}else {
			System.out.println("Posicion ingresada incorrectamente");
		}
	}
	return f; 
}
}
