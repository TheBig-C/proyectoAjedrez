package poo;

import java.io.Serializable;
import java.util.ArrayList;

public class Peon extends Pieza implements Serializable {
	private String nombre,color,posicion;
	private int fila,columna,direccion,puntuacion;
	private boolean estado,estado2,primerMovB;
	public Peon(String posicion,String color) {
		this.color = color;
		this.direccion=2;//1 avanza en la fila 0-7, 2 avanza en columna 0-7, 3 en ambos sentidos, 4 movimiento especial, 5 en todos los sentidos
		this.estado=true;
		this.primerMovB=true;
		this.estado2=true;
		this.posicion=posicion;
		this.posicion=posicion;
		
			this.puntuacion=10;
		
		darPosicion(posicion);
		
	}
	public Peon(String posicion,String color,boolean estado, boolean especial) {
		this.color = color;
		this.direccion=2;//1 avanza en la fila 0-7, 2 avanza en columna 0-7, 3 en ambos sentidos, 4 movimiento especial, 5 en todos los sentidos
		this.estado=estado;
		this.primerMovB=especial;
		this.estado2=estado;
		this.posicion=posicion;
		darPosicion(posicion);
		 
	}
	public boolean isEspecial() {
		return primerMovB;
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
		this.posicion=posicion;
	}
	@Override
	public void mostrarPosiblesMovimientos(String[][] tablero,ArrayList<String> mov,boolean f) {
		// TODO Auto-generated method stub
		
		String d;
		int aux1,aux2;
		if(color.equals("Blancas")) {
			aux2=columna;
		    aux1=fila-1;
		    if(aux1>=0  ) {
		    	if(tablero[aux1][aux2]=="____") {
		    		if(aux1-1>=0) {
		    			if(tablero[aux1-1][aux2]=="____") {
			    			if(primerMovB) {
			    				d=Extras.posAlge(nombre.charAt(0), aux1-1, aux2, color.charAt(0));
			    				mov.add(d);
			    				if(f)
									System.out.println(d);
			    			}
			    		}
		    		}
					d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
					mov.add(d);
					if(f)
						System.out.println(d);
				}
		    	
		    }
		    aux1=fila-1;
		    aux2=columna-1;
		    if(aux1>=0 && aux2>=0) {
		    	
					if((color.charAt(0) == 'B' && tablero[aux1][aux2].charAt(3)=='N') || (color.charAt(0) == 'N' && tablero[aux1][aux2].charAt(3)=='B'))  {
						d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
						if(f)
							System.out.println(d);
						mov.add(d);
						
					}
				
		    	
		    }
		    aux1=fila-1;
		    aux2=columna+1;
		    if(aux1>=0 && aux2<8) {
		    	
					if((color.charAt(0) == 'B'&&tablero[aux1][aux2].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[aux1][aux2].charAt(3)=='B')) {
						d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
						if(f)
							System.out.println(d);
						mov.add(d);
					}
				
		    	
		    }
		}else {
			aux2=columna;
		    aux1=fila+1; 
		    if(aux1<8  ) {
		    	if(tablero[aux1][aux2]=="____") {
		    		if(aux1+1<8) {
		    		if(tablero[aux1+1][aux2]=="____") {
		    			
		    			if(primerMovB) {
		    				
		    				d=Extras.posAlge(nombre.charAt(0), aux1+1, aux2, color.charAt(0));
		    				mov.add(d);
		    				if(f)
								System.out.println(d);
		    				
		    			}
		    		}
		    		}
					d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
					mov.add(d);
					if(f)
						System.out.println(d);
				}
		    	
		    }
		    aux1=fila+1;
		    aux2=columna+1;
		    if(aux1<8 && aux2<8) {
		    	
					if((color.charAt(0) == 'B' && tablero[aux1][aux2].charAt(3)=='N') || (color.charAt(0) == 'N' && tablero[aux1][aux2].charAt(3)=='B'))  {
						d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
						if(f)
							System.out.println(d);
						mov.add(d);
						
					}
				
		    	
		    }
		    aux1=fila+1;
		    aux2=columna-1;
		    if(aux1<8 && aux2>=0) {
		    	
					if((color.charAt(0) == 'B'&&tablero[aux1][aux2].charAt(3)=='N') || (color.charAt(0) == 'N'&&tablero[aux1][aux2].charAt(3)=='B')) {
						d=Extras.posAlge(nombre.charAt(0), aux1, aux2, color.charAt(0));
						if(f)
							System.out.println(d);
						mov.add(d);
					}
				
		    	
		    }
		}
	    
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public boolean isPrimerMov() {
		return primerMovB;
	}
	public void setPrimerMov(boolean primerMov) {
		this.primerMovB = primerMov;
	}
	public void setEspecial(boolean f) {
		
	}
	
	@Override
	public String especial(boolean f) {
		// TODO Auto-generated method stub
		int o=0;
	
		if(f) {
			
				this.primerMovB=false;
			
				if(fila==0 ||fila==7) {
					if(!Main.bot) {
						String input = VentanaModal.showCustomInputDialog(Main.tab,posicion);
						System.out.println("input: "+input);
				        return input;
					}else {
						if(fila==7) {
							System.out.println("input: "+"D"+posicion.substring(1));
							return "D"+posicion.substring(1);
							
						}else {
							String input = VentanaModal.showCustomInputDialog(Main.tab,posicion);
							System.out.println("input: "+input);
					        return input;
						}
						
					}
					
				}
		}
		return posicion;
	}
	@Override
	public boolean jaque(String[][] tablero, int f, int c, boolean b) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void reiniciarEspecial() {
		// TODO Auto-generated method stub
		this.primerMovB=true;
	}
	@Override
	public int puntos() {
		// TODO Auto-generated method stub
		return this.puntuacion;
	}
}
