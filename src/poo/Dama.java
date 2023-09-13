package poo;

import java.io.Serializable;
import java.util.ArrayList;

public class Dama extends Pieza implements Serializable{
	private String nombre,color,posicion;
	private int fila,columna,direccion,puntuacion;
	private boolean estado,estado2;
	
	public Dama (String posicion,String color) {
		this.color = color;
		this.direccion=5;//1 avanza en la fila 0-7, 2 avanza en columna 0-7, 3 en ambos sentidos, 4 movimiento especial, 5 en todos los sentidos
		this.estado=true;
		this.posicion=posicion;
		this.estado2=true;
		this.posicion=posicion;
		
			this.puntuacion=90;
		
		darPosicion(posicion);
		
	}
	public Dama (String posicion,String color,boolean estado, boolean especial) {
		this.color = color;
		this.direccion=5;//1 avanza en la fila 0-7, 2 avanza en columna 0-7, 3 en ambos sentidos, 4 movimiento especial, 5 en todos los sentidos
		this.estado=estado;
		this.posicion=posicion;
		this.estado2=estado;
		darPosicion(posicion);
		
	}
	public boolean isEspecial() {
		return false;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre; 
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	public int getDireccion() {
		return direccion;
	}
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado,int n) {
		int n1=1;
		
		if(n==2) {
			this.estado2=false;
		}
		if(estado) {
			if(n==1) {
				if(estado2) {
					
					this.estado=true;
				}else {
					this.estado=false;
				}
				
			}
			//n1=1;
		}else {
			
				this.estado=false;
			
		}
		
		
	}
	private void darPosicion(String posicion) {
		this.nombre=Extras.darNombre(posicion.charAt(0));
		this.columna=(int)(posicion.charAt(1)-97);
		this.fila=8-(int)(posicion.charAt(2)-48);
	}

	@Override
	public String obtenerPosicion() {
		// TODO Auto-generated method stub

		return posicion;
	}

	@Override
	public void cambiarPosicion(String posicion) {
		// TODO Auto-generated method stub
		this.columna=(int)(posicion.charAt(1)-97);
		this.fila=8-(int)(posicion.charAt(2)-48);
		this.fila=8-(int)(posicion.charAt(2)-48);
		this.posicion=posicion;
	}
	@Override
	public void mostrarPosiblesMovimientos(String[][] tablero,ArrayList<String> mov,boolean f) {
		// TODO Auto-generated method stub
		
		movDiagonalDerecha(tablero,mov,f);
		movDiagonalIzquierda(tablero,mov,f);
		movHorizontal(tablero,mov,f);
		movVertical(tablero,mov,f);
	}
	public void movDiagonalIzquierda(String[][] tablero,ArrayList<String> mov,boolean f) {
		String d;
		int ip=fila,jp=columna;
		int i=fila,j=columna;
		if(j<8 && i<8) {
			while(j!=7 && i!=7) {
				i++;
				j++;
				if(tablero[i][j]=="____") {
					d=Extras.posAlge(nombre.charAt(0), i, j, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}else {
					if((color.charAt(0) == 'B'&&tablero[i][j].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[i][j].charAt(3)=='B')) {
						d=Extras.posAlge(nombre.charAt(0), i, j, color.charAt(0));
						if(f)
							System.out.println(d);
						mov.add(d);
						i=7;
						j=7;
					}
					if((color.charAt(0) == 'B'&&tablero[i][j].charAt(3)=='B') || (color.charAt(0) == 'N'&&tablero[i][j].charAt(3)=='N')) {
						i=7;
						j=7;
					}
				}
				
			}
		}
		i=fila;
		j=columna;
		if(j>0 && i>0) {
			while(j!=0 && i!=0) {
				i--;
				j--;
				if(tablero[i][j]=="____") {
					d=Extras.posAlge(nombre.charAt(0), i, j, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}else {
					if((color.charAt(0) == 'B'&&tablero[i][j].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[i][j].charAt(3)=='B')) {
						d=Extras.posAlge(nombre.charAt(0), i, j, color.charAt(0));
						if(f)
							System.out.println(d);
						mov.add(d);
						i=0;
						j=0;
					}
					if((color.charAt(0) == 'B'&&tablero[i][j].charAt(3)=='B') || (color.charAt(0) == 'N'&&tablero[i][j].charAt(3)=='N')) {
						i=0;
						j=0;
					}
				}
				
			}
		}
	}
	public void movDiagonalDerecha(String[][] tablero,ArrayList<String> mov,boolean f) {
		String d;
		int ip=fila,jp=columna;
		int i=fila,j=columna;
		if(j<8 && i>0) {
			
			while(j!=7 && i!=0) {
				i--;
				j++;
				if(tablero[i][j]=="____") {
					d=Extras.posAlge(nombre.charAt(0), i, j, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}else {
					if((color.charAt(0) == 'B'&&tablero[i][j].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[i][j].charAt(3)=='B')) {
						d=Extras.posAlge(nombre.charAt(0), i, j, color.charAt(0));
						if(f)
							System.out.println(d);
						mov.add(d);
						i=0;
						j=7;
					}
					if((color.charAt(0) == 'B'&&tablero[i][j].charAt(3)=='B') || (color.charAt(0) == 'N'&&tablero[i][j].charAt(3)=='N')) {
						i=0;
						j=7;
					}
				}
				
			}
		}
		i=fila;
		j=columna;
		if(j>0 && i<8) {
			
			while(j!=0 && i!=7) {
				i++;
				j--;
				if(tablero[i][j]=="____") {
					d=Extras.posAlge(nombre.charAt(0), i, j, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}else {
					if((color.charAt(0) == 'B'&&tablero[i][j].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[i][j].charAt(3)=='B')) {
						d=Extras.posAlge(nombre.charAt(0), i, j, color.charAt(0));
						if(f)
							System.out.println(d);
						mov.add(d);
						i=7;
						j=0;
					}
					if((color.charAt(0) == 'B'&&tablero[i][j].charAt(3)=='B') || (color.charAt(0) == 'N'&&tablero[i][j].charAt(3)=='N')) {
						i=7;
						j=0;
					}
				}
				
			}
		}
	}
	private void movVertical(String[][] tablero,ArrayList<String> mov,boolean f){
		String d;
		int ip=fila,jp=columna;
		int i=fila;
		if(i<8) {
			while(i!=7) {
				i++;
				if(tablero[i][columna]=="____") {
					d=Extras.posAlge(nombre.charAt(0), i, columna, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}else {
					if((color.charAt(0) == 'B'&&tablero[i][columna].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[i][columna].charAt(3)=='B')) {
						d=Extras.posAlge(nombre.charAt(0), i, columna, color.charAt(0));
						if(f)
							System.out.println(d);
						mov.add(d);
						i=7;
					}
					if((color.charAt(0) == 'B'&&tablero[i][columna].charAt(3)=='B') || (color.charAt(0) == 'N'&&tablero[i][columna].charAt(3)=='N')) {
						
						i=7;
					}
				}
				
			}
		}
		i=fila;
		if(i>0) {
			while(i!=0) {
				i--;
				if(tablero[i][columna]=="____") {
					d=Extras.posAlge(nombre.charAt(0), i, columna, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}else {
					if((color.charAt(0) == 'B'&&tablero[i][columna].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[i][columna].charAt(3)=='B')) {
						d=Extras.posAlge(nombre.charAt(0), i, columna, color.charAt(0));
						if(f)
							System.out.println(d);
						mov.add(d);
						i=0;
					}
					if((color.charAt(0) == 'B'&&tablero[i][columna].charAt(3)=='B') || (color.charAt(0) == 'N'&&tablero[i][columna].charAt(3)=='N')) {
						
						i=0;
					}
				}
				
			}
		}
		
	}
	private void movHorizontal(String[][] tablero,ArrayList<String> mov,boolean f){
		String d;
		int ip=fila,jp=columna;
		int j=columna;
		if(j<8) {
			while(j!=7) {
				j++;
				if(tablero[fila][j]=="____") {
					d=Extras.posAlge(nombre.charAt(0), fila, j, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}else {
					if((color.charAt(0) == 'B'&&tablero[fila][j].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[fila][j].charAt(3)=='B')) {
						d=Extras.posAlge(nombre.charAt(0), fila, j, color.charAt(0));
						if(f)
							System.out.println(d);
						mov.add(d);
						j=7;
					}
					if((color.charAt(0) == 'B'&&tablero[fila][j].charAt(3)=='B') || (color.charAt(0) == 'N'&&tablero[fila][j].charAt(3)=='N')) {
						
						j=7;
					}
				}
			
			}
		}
		j=columna;
		if(j>0) {
			while(j!=0) {
				j--;
				if(tablero[fila][j]=="____") {
					d=Extras.posAlge(nombre.charAt(0), fila, j, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}else {
					if((color.charAt(0) == 'B'&&tablero[fila][j].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[fila][j].charAt(3)=='B')) {
						d=Extras.posAlge(nombre.charAt(0), fila, j, color.charAt(0));
						if(f)
							System.out.println(d);
						mov.add(d);
						j=0;
					}
					if((color.charAt(0) == 'B'&&tablero[fila][j].charAt(3)=='B') || (color.charAt(0) == 'N'&&tablero[fila][j].charAt(3)=='N')) {
						
						j=0;
					}
				}
				
			}
		}
		
	}
	
	@Override
	public String especial(boolean f) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean jaque(String[][] tablero, int f, int c, boolean b) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void reiniciarEspecial() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int puntos() {
		// TODO Auto-generated method stub
		return this.puntuacion;
	}
}
