package poo;

import java.io.Serializable;
import java.util.ArrayList;

public class Caballo extends Pieza implements Serializable  {
	private String nombre,color,posicion;
	private int fila,columna,direccion,puntuacion;
	private boolean estado,estado2;
	
	public Caballo(String posicion,String color) {
		this.color = color;
		this.direccion=0;//1 avanza en la fila 0-7, 2 avanza en columna 0-7, 3 en ambos sentidos, 4 movimiento especial, 5 en todos los sentidos
		this.estado=true;
		this.estado2=true;
		this.posicion=posicion;
		
			this.puntuacion=30;
		
		this.posicion=posicion;
		darPosicion(posicion);
		 
	}
	public Caballo(String posicion,String color,boolean estado, boolean especial) {
		this.color = color;
		this.direccion=0;//1 avanza en la fila 0-7, 2 avanza en columna 0-7, 3 en ambos sentidos, 4 movimiento especial, 5 en todos los sentidos
		this.estado=estado;
		this.estado2=estado;
		this.posicion=posicion;
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
		String d;
		int aux1=fila+2,aux2=columna+1;
	    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
	    	if(tablero[aux1][aux2]=="____") {
				d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
				if(f)
					System.out.println(d);
				mov.add(d);
			}else {
				if((color.charAt(0) == 'B'&&tablero[aux1][aux2].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[aux1][aux2].charAt(3)=='B')) {
					d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}
			}
	    		
	    	
	    }
	    aux1=fila+2;
	    aux2=columna-1;
	    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
	    	if(tablero[aux1][aux2]=="____") {
				d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
				if(f)
					System.out.println(d);
				mov.add(d);
			}else {
				if((color.charAt(0) == 'B'&&tablero[aux1][aux2].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[aux1][aux2].charAt(3)=='B')) {
					d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}
			}
	    }
	    aux1=fila-2;
	    aux2=columna+1;
	    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
	    	if(tablero[aux1][aux2]=="____") {
				d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
				if(f)
					System.out.println(d);
				mov.add(d);
			}else {
				if((color.charAt(0) == 'B'&&tablero[aux1][aux2].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[aux1][aux2].charAt(3)=='B')) {
					d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}
			}
	    }
	    aux1=fila-2;
	    aux2=columna-1;
	    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
	    	if(tablero[aux1][aux2]=="____") {
				d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
				if(f)
					System.out.println(d);
				mov.add(d);
			}else {
				if((color.charAt(0) == 'B'&&tablero[aux1][aux2].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[aux1][aux2].charAt(3)=='B')) {
					d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}
			}
	    }
	    aux1=fila-1;
	    aux2=columna+2;
	    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
	    	if(tablero[aux1][aux2]=="____") {
				d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
				if(f)
					System.out.println(d);
				mov.add(d);
			}else {
				if((color.charAt(0) == 'B'&&tablero[aux1][aux2].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[aux1][aux2].charAt(3)=='B')) {
					d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}
			}
	    }
	    aux1=fila+1;
	    aux2=columna+2;
	    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
	    	if(tablero[aux1][aux2]=="____") {
				d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
				if(f)
					System.out.println(d);
				mov.add(d);
			}else {
				if((color.charAt(0) == 'B'&&tablero[aux1][aux2].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[aux1][aux2].charAt(3)=='B')) {
					d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}
			}
	    }
	    aux1=fila-1;
	    aux2=columna-2;
	    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
	    	if(tablero[aux1][aux2]=="____") {
				d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
				if(f)
					System.out.println(d);
				mov.add(d);
			}else {
				if((color.charAt(0) == 'B'&&tablero[aux1][aux2].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[aux1][aux2].charAt(3)=='B')) {
					d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
				}
			}
	    }
	    aux1=fila+1;
	    aux2=columna-2;
	    if(aux1>=0 && aux1<8 &&  aux2>=0 && aux2<8  ) {
	    	if(tablero[aux1][aux2]=="____") {
				d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
				if(f)
					System.out.println(d);
				mov.add(d);
			}else {
				if((color.charAt(0) == 'B'&&tablero[aux1][aux2].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[aux1][aux2].charAt(3)=='B')) {
					d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
					if(f)
						System.out.println(d);
					mov.add(d);
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
	public void reiniciarEspecial() {
		// TODO Auto-generated method stub
	
	}
	@Override
	public int puntos() {
		// TODO Auto-generated method stub
		return this.puntuacion;
	}
}
