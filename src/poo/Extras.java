package poo;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Extras {
	static int[][] Rey = {
			{-30,-40,-40,-50,-50,-40,-40,-30},
			{-30,-40,-40,-50,-50,-40,-40,-30},
			{-30,-40,-40,-50,-50,-40,-40,-30},
			{-30,-40,-40,-50,-50,-40,-40,-30},
			{-20,-30,-30,-40,-40,-30,-30,-20},
			{-10,-20,-20,-20,-20,-20,-20,-10},
			{0,0,-10,-15,-15,-10,0,0},
			{20,30,10,0,0,10,30,20},
	};
	static int[][] Dama = {
			{-20,-10,-10,-5,-5,-10,-10,-20},
			{-10,0,0,0,0,0,0,-10},
			{-10,0,5,5,5,5,0,-10},
			{-5,0,5,5,5,5,0,-5},
			{0,0,5,5,5,5,0,0},
			{-10,5,5,5,5,5,5,-10},
			{-10,0,5,0,0,0,0,-10},
			{-20,-10,-10,-5,-5,-10,-10,-20},
	};
	static int[][] Torre = {
			{0,0,0,0,0,0,0,0},
			{5,10,10,10,10,10,10,5},
			{-5,0,0,0,0,0,0,-5},
			{-5,0,0,0,0,0,0,-5},
			{-5,0,0,0,0,0,0,-5},
			{-5,0,0,0,0,0,0,-5},
			{-5,0,0,0,0,0,0,-5},
			{0,0,0,5,5,0,0,0},
	};
	static int[][] Caballo = {
			{-10,-5,-5,-5,-5,-5,-5,-10},
			{-5,0,0,0,0,0,0,-5},
			{-5,0,5,5,5,5,0,-5},
			{-5,0,5,10,10,5,0,-5},
			{-5,0,5,10,10,5,0,-5},
			{-5,0,5,5,5,5,0,-5},
			{-5,0,0,0,0,0,0,-5},
			{-10,-5,-5,-5,-5,-5,-5,-10},
	};
	static int[][] Alfil = {
			{-1,-5,-3,-5,-5,-3,-5,-1},
			{-3,10,0,10,10,0,10,-3},
			{-1,3,6,10,10,6,3,-1},
			{-1,10,10,3,3,10,10,-1},
			{-1,10,10,3,3,10,10,-1},
			{-1,3,6,10,10,6,3,-1},
			{-3,10,0,10,10,0,10,-3},
			{-1,-5,-3,-5,-5,-3,-5,-1},
	};
	static int[][] PeonI = {
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{5,10,15,20,20,15,10,5},
			{4,8,12,16,16,12,8,4},
			{3,6,9,12,12,9,6,3},
			{2,4,6,8,8,6,4,2},
			{1,2,3,4,4,3,2,1},
			{0,0,0,0,0,0,0,0},
	};
	static int[][] PeonF = {
			{20,30,40,50,50,40,30,20},
			{12,24,36,48,48,36,24,12},
			{10,20,30,40,40,30,20,10},
			{8,16,24,32,32,24,16,8},
			{6,12,18,24,24,18,12,6},
			{4,8,12,16,16,12,8,4},
			{2,4,6,8,8,6,4,2},
			{0,0,0,0,0,0,0,0},
	};
	public static int leer(String d) {
		Scanner nd=new Scanner(System.in);
		System.out.print(d);
		int s=nd.nextInt();
		return s;
	}
	public static String leer_S(String d) {
		Scanner nd=new Scanner(System.in);
		System.out.print(d);
		String s=nd.nextLine();
		return s;
	}
	public static String color(char c) {
		if(c=='B') {
			return "Blancas";
			
		}else {
			return "Negras";
		}
	}
	public static void mostrarV(ArrayList<String> d) {
		for(String i:d) {
			System.out.print("fsdfs"+i+"\t");
		}
		System.out.println();
	}
	public static void limpiar(String[][] tp,String[][] tb,String[][] tn) {
		for(int i=0;i<tp.length;i++) {
			for(int j=0;j<tp[0].length;j++) {
			tp[i][j]=null;
			tb[i][j]=null;
			tn[i][j]=null;
			}
			
			
		}
	}
	public static void eliminar(int cod)throws SQLException{
		eliminarPartidaFichas(cod);
		eliminarPartidaMovimientos(cod);
		eliminarPartida(cod);
		
	}
	public static void eliminarPartidaFichas(int cod) throws SQLException{
		Conexion con=new Conexion();
		Connection conexion = (Connection) con.getConexionPostgres();
		
		PreparedStatement s;
		String query="delete from partidafichas where partidas_cp = ?";		
		
		try {
			s=(PreparedStatement) conexion.prepareStatement(query);
			s.setInt(1, cod);
			s.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e);
		}
		conexion.close();
	}
	public static void eliminarPartidaMovimientos(int cod) throws SQLException{
		Conexion con=new Conexion();
		Connection conexion = (Connection) con.getConexionPostgres();
		
		PreparedStatement s;
		String query="delete from partidamovidas where partidas_cp = ?";		
		
		try {
			s=(PreparedStatement) conexion.prepareStatement(query);
			s.setInt(1, cod);
			s.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e);
		}
		conexion.close();
	}
	public static void eliminarPartida(int cod) throws SQLException{
		Conexion con=new Conexion();
		Connection conexion = (Connection) con.getConexionPostgres();
		
		PreparedStatement s;
		String query="delete from partidas where cp = ?";		
		
		try {
			s=(PreparedStatement) conexion.prepareStatement(query);
			s.setInt(1, cod);
			s.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e);
		}
		conexion.close();
	}
	public static ArrayList<Partidas> buscar(String bus,ArrayList<Partidas> partidas) {
		int c=bus.length(),j=0,cont=0;
		ArrayList<Partidas> res=new ArrayList<Partidas>();
		for(Partidas i:partidas) {
			
				j=0;
				cont=0;
				while(cont<i.getNombre().length()) {
					if(cont+bus.length()<=i.getNombre().length()) {
						System.out.println(i.getNombre().substring(j,j+c));
						if(i.getNombre().toLowerCase().substring(j,j+c).equals(bus.toLowerCase())) {
							res.add(i);
						}
						j++;
					}
					
					cont++;
				}
			
			
		}
		return res;
	}
	public static ArrayList<Partidas> cargarPartidas()throws SQLException{
		Conexion con=new Conexion();
		int total=0,c=0;
		ArrayList<Partidas> dfd2=new ArrayList<Partidas>();
		Partidas par=null;
		Connection conexion = con.getConexionPostgres();
		java.sql.Statement s= conexion.createStatement();
		ResultSet rs=  s.executeQuery("select a.cp, a.hora,a.date,a.bot,a.colorf,a.auto,a.tiempob,a.tiempon,a.nombre from partidas a");
		
		while (rs.next()) {
			par=new Partidas(rs.getInt(1),rs.getInt(7), rs.getInt(8), rs.getString(9), (rs.getDate(3)).toLocalDate(),(rs.getTime(2)).toLocalTime(), rs.getBoolean(4), rs.getBoolean(5), rs.getBoolean(6));
			
			dfd2.add(par);
			
		}
		
		conexion.close();
		System.out.println(dfd2);
			return dfd2;
	}
public static ArrayList<String> traer() throws SQLException{
	Conexion con=new Conexion();
	int total=0,c=0;
	ArrayList<String> dfd2=new ArrayList<String>();
	
	Connection conexion = con.getConexionPostgres();
	java.sql.Statement s= conexion.createStatement();
	ResultSet rs=  s.executeQuery("select a.movidab from movidas a order by a.cm");
	
	while (rs.next()) {
		
		
		dfd2.add((String) rs.getString(1));
		
	}
	
	conexion.close();
	System.out.println(dfd2);
		return dfd2;
	}
	public static ArrayList<String> comprobar(String ant,ArrayList<String> doop) {
		//ArrayList<String> jugadas=new ArrayList<String>();
		
		
		ArrayList<String> dfd2=new ArrayList<String>();
		for(int i=0;i<doop.size();i++) {
			if(doop.get(i).equals(ant)) {
				if((i+1)<(doop.size())) {
				
					for(int j=0;j<Main.jugadasPartida.size();j++) {
						
						if(Main.jugadasPartida.get(j).equals(doop.get(i+1))) {
							if(i-5>=0 && j-5>=0  ) {
								if(doop.get(i-5).equals(Main.jugadasPartida.get(j-5))) {
									dfd2.add(doop.get(i+1));
									dfd2.add(doop.get(i+1));
									dfd2.add(doop.get(i+1));
								}
							}else if(i-3>=0 && j-3>=0) {
								if(doop.get(i-3).equals(Main.jugadasPartida.get(j-3))) {
									dfd2.add(doop.get(i+1));
									dfd2.add(doop.get(i+1));
								
								}
								
							}else if(i-1>=0 && j-1>=0) {
								if(doop.get(i-1).equals(Main.jugadasPartida.get(j-1))) {
									dfd2.add(doop.get(i+1));
									
								
								}
							}else {
								if(!dfd2.contains(doop.get(i+1))) {
									dfd2.add(doop.get(i+1));
								}
							}
						}
					}
					
					
				}
			}
		}
		
		return dfd2;
	}
	public static String darNombre(char d) {
		switch(d) {
		case 'R':
			return "Rey";
		case 'D':
			return "Dama";
		case 'P':
			return "Peon";
		case 'A':
			return "Alfil";
		case 'C':
			return "Caballo";
		case 'T':
			return "Torre";
		}
		return "";
	}
	public static String posAlge(char n,int fila, int columna, char c) {
		
		return ""+n+(char)(columna+97)+(int)(56-(fila+48))+c;
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
	public static void copiarT(String[][] t1,String[][] t2) {
		
		int num=8;
		for(int i=0;i<t1.length;i++) {
			for(int j=0;j<t1[0].length;j++) {
				t2[i][j]=t1[i][j];
			}
			
			
		} 
		
	}
	
	public static void crear(ArrayList<Pieza> d1,ArrayList<Pieza> d2) {
		Pieza pieza;
		d2.clear();
		for(Pieza i:d1) {
			
			switch(i.obtenerPosicion().charAt(0)) {
			case 'R':
				pieza =new Rey(i.obtenerPosicion(),color(i.obtenerPosicion().charAt(3)));
				d2.add(pieza);
				break;
			case 'D':
				pieza =new Dama(i.obtenerPosicion(),color(i.obtenerPosicion().charAt(3)));
				d2.add(pieza);
				break;
			case 'P':
				pieza =new Peon(i.obtenerPosicion(),color(i.obtenerPosicion().charAt(3)));
				d2.add(pieza);
				break;
			case 'A':
				pieza =new Alfil(i.obtenerPosicion(),color(i.obtenerPosicion().charAt(3)));
				d2.add(pieza);
				break;
			case 'C':
				pieza =new Caballo(i.obtenerPosicion(),color(i.obtenerPosicion().charAt(3)));
				d2.add(pieza);
				break;
			case 'T':
				pieza =new Torre(i.obtenerPosicion(),color(i.obtenerPosicion().charAt(3)));
				d2.add(pieza);
				break;
			}
		}
		
	}
	public static int tablero(String s,boolean d,ArrayList<Pieza> fig) {
		char w=s.charAt(0);
		int total=0,f=0,c=0;
		switch(w) {
		case 'R':
			if(d) {
				c=(int)(s.charAt(1)-97);
				f=8-(int)(s.charAt(2)-48);
				
			}else {
				c=(int)(s.charAt(1)-97);
				f=8-(int)(s.charAt(2)-48);
				f=7-f;
				
			}
			total=Rey[f][c];
			break;
		case 'D':
			if(d) {
				c=(int)(s.charAt(1)-97);
				f=8-(int)(s.charAt(2)-48);
				
			}else {
				c=(int)(s.charAt(1)-97);
				f=8-(int)(s.charAt(2)-48);
				f=7-f;
				
			}
			total=Dama[f][c];
			break;
		case 'P':
			if(d) {
				c=(int)(s.charAt(1)-97);
				f=8-(int)(s.charAt(2)-48);
				
				
			}else {
				c=(int)(s.charAt(1)-97);
				f=8-(int)(s.charAt(2)-48);
				f=7-f;
				
			}
			if(PeonEspecial(d,fig)) {
				total=PeonI[f][c];
			}else {
				total=PeonI[f][c];
			}
			
			break;
		case 'A':
			if(d) {
				c=(int)(s.charAt(1)-97);
				f=8-(int)(s.charAt(2)-48);
				
			}else {
				c=(int)(s.charAt(1)-97);
				f=8-(int)(s.charAt(2)-48);
				f=7-f;
			
			}
			total=Alfil[f][c];
			break;
		case 'C':
			if(d) {
				c=(int)(s.charAt(1)-97);
				f=8-(int)(s.charAt(2)-48);
				
			}else {
				c=(int)(s.charAt(1)-97);
				f=8-(int)(s.charAt(2)-48);
				f=7-f;
				
			}
			total=Caballo[f][c];
			break;
		case 'T':
			if(d) {
				c=(int)(s.charAt(1)-97);
				f=8-(int)(s.charAt(2)-48);
				
			}else {
				c=(int)(s.charAt(1)-97);
				f=8-(int)(s.charAt(2)-48);
				f=7-f;
			
			}
			total=Torre[f][c];
			break;
		}
		return total;
	}
	public static boolean PeonEspecial(boolean b, ArrayList<Pieza> fig) {
			int total=0;
		for(Pieza i: fig) {
			if(i.isEstado()) {
				if(b) {
					if(i.obtenerPosicion().charAt(3)=='B') {
						total=total+i.puntos();
					}else {
						total=total-i.puntos();
					}
				}else {
					if(i.obtenerPosicion().charAt(3)=='N') {
						total=total+i.puntos();
					}else {
						total=total-i.puntos();
					}
				}
			}
		}
		if(total>=1110) {
			return true; 
		}else {
			return false;
		}
	}
	public static Pieza ficha(String z) {
		char d=z.charAt(0);
		Pieza aux;
		switch(d) {
		
		case 'D':
			aux=new Dama(z,Extras.color(z.charAt(3)));
			return aux;
		
		case 'A':
			aux=new Alfil(z,Extras.color(z.charAt(3)));
			return aux;
		case 'C':
			aux=new Caballo(z,Extras.color(z.charAt(3)));
			return aux;
		case 'T':
			aux=new Torre(z,Extras.color(z.charAt(3)));
			return aux;
		}
		return null;
	}
	public static int tiempo(String d) {
		String z=d.substring(0,2);
		
		switch(z) {
		
		case "Si":
		
			return -1;
		
		case "30":
			
			return 30;
		case "15":
			
			return 15;
		case "10":
			
			return 10;
		case "5 ":
			
			return 5;
		case "3 ":
			
			return 3;
		case "1 ":
			
			return 1;
		}
		return 0;
	}
	public static Pieza crearFicha(String z,boolean e, boolean s) {
		char d=z.charAt(0);
		Pieza aux;
		switch(d) {
		
		case 'D':
			aux=new Dama(z,Extras.color(z.charAt(3)),e,s);
			return aux;
		
		case 'A':
			aux=new Alfil(z,Extras.color(z.charAt(3)),e,s);
			return aux;
		case 'C':
			aux=new Caballo(z,Extras.color(z.charAt(3)),e,s);
			return aux;
		case 'T':
			aux=new Torre(z,Extras.color(z.charAt(3)),e,s);
			return aux;
		case 'R':
			aux=new Rey(z,Extras.color(z.charAt(3)),e,s);
			return aux;
		case 'P':
			aux=new Peon(z,Extras.color(z.charAt(3)),e,s);
			return aux;
		}
		return null;
	}
	public static void menu() {
		System.out.println("Menu coronacion");
		System.out.println("1.-Dama");
		System.out.println("2.-Alfil");
		System.out.println("3.-Caballo");
		System.out.println("4.-Torre");
		

	}
}
