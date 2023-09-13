package poo;

import java.io.Serializable;
import java.util.ArrayList;

public class Rey extends Pieza implements Serializable{
	
	private String nombre;
	private String color;
	private  String posicion;
	private int fila,columna,direccion,puntuacion;
	private boolean estado,primerMov;
	
	public Rey(String posicion,String color) {
		this.color = color;
		this.direccion=5;//1 avanza en la fila 0-7, 2 avanza en columna 0-7, 3 en ambos sentidos, 4 movimiento especial, 5 en todos los sentidos
		this.estado=true;
		this.primerMov=true; 
		this.posicion=posicion;
		
			this.puntuacion=900;
		
		
		darPosicion(posicion);
		
	}
	public Rey(String posicion,String color,boolean estado, boolean especial) {
		this.color = color;
		this.direccion=5;//1 avanza en la fila 0-7, 2 avanza en columna 0-7, 3 en ambos sentidos, 4 movimiento especial, 5 en todos los sentidos
		this.estado=estado;
		this.primerMov=especial; 
		this.posicion=posicion;
		darPosicion(posicion);
		
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
	public boolean isEspecial() {
		return primerMov;
	}
	public void setEstado(boolean estado,int n) {
		
	}
	private void darPosicion(String posicion) {
		this.nombre=Extras.darNombre(posicion.charAt(0));
		this.columna=(int)(posicion.charAt(1)-97);
		this.fila=8-(int)(posicion.charAt(2)-48);
	}

	@Override
	public String obtenerPosicion() {

		return posicion;
	}

	@Override
	public void cambiarPosicion(String posicion) {
		// TODO Auto-generated method stub
		
		if(!posicion.substring(0,3).equals("O-O")) {
			this.columna=(int)(posicion.charAt(1)-97);
			this.fila=8-(int)(posicion.charAt(2)-48);
			this.posicion=posicion;
		}else {
			if(posicion.length()>4) {
				if(posicion.charAt(5)=='B') {
					this.columna=2;
					this.fila=7;
					this.posicion=Extras.posAlge('R', fila, columna, 'B');
				}else {
					this.columna=2;
					this.fila=0;
					this.posicion=Extras.posAlge('R', fila, columna, 'N');
				}
			}else {
				if(posicion.charAt(3)=='B') {
					this.columna=6;
					this.fila=7;
					this.posicion=Extras.posAlge('R', fila, columna, 'B');
				}else {
					this.columna=6;
					this.fila=0;
					this.posicion=Extras.posAlge('R', fila, columna, 'N');
				}
			}
		}
	}
	@Override
	public void mostrarPosiblesMovimientos(String[][] tablero,ArrayList<String> mov,boolean f) {
		// TODO Auto-generated method stub
		String d;
		int aux1=fila+1,aux2=columna+1;
	    
	    	if(aux1<8 && aux2<8) {
	    		if(!jaque(tablero,aux1,aux2,false)) {
	    			
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
	    
	    aux1=fila-1;
	    aux2=columna-1;
	    
	    if(aux1>=0 && aux2>=0) {
	    	if(!jaque(tablero,aux1,aux2,false)) {
	    	
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
	    aux1=fila+1;
	    aux2=columna-1;
	    
	    if(aux1<8 && aux2>=0) {
	    	
	    	if(!jaque(tablero,aux1,aux2,false)) {
	    		
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
	    aux1=fila-1;
	    aux2=columna+1;
	    
	    if(aux2<8 && aux1>=0) {
	    	
	    	if(!jaque(tablero,aux1,aux2,false)) {
	    		
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
	   
	    aux2=columna+1;
	    aux1=fila;
	    
	    if(aux2<8  ) {
	    	 if(!jaque(tablero,aux1,aux2,false)) {
	    		
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
	    aux2=columna-1;
	    aux1 =fila;
	    int count=0;
	    if(aux2>=0 ) {
	    	if(!jaque(tablero,aux1,aux2,false)) {
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
	    aux2=columna;
	    aux1=fila+1;
	    
	    if(aux1<8  ) {
	    	if(!jaque(tablero,aux1,aux2,false)) {
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
	    aux2=columna;
	    aux1 =fila-1;
	    
	    if(aux1>=0 ) {
	    	if(!jaque(tablero,aux1,aux2,false)) {
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
	    if(!jaque(tablero,fila,columna,false)) {
	    if(primerMov) {
	    	aux2=columna;
	    	aux1=fila;
	    	count=0;
	    	while(aux2<8) {
	    		aux2++;
	    		if(aux2<8) {
	    			if(aux2<7) {
	    				if(!jaque(tablero,aux1,aux2,false)) {
	    		    		if(tablero[aux1][aux2]=="____"||(count==2)) {
	    		    			count++;
	    		    			if(aux2==7) {
	    		    				if(tablero[aux1][aux2].charAt(0)=='T') {
	    		    					d="O-O"+color.charAt(0);
	    								if(f)
	    									System.out.println(d);
	    								mov.add(d);
	    		    				}else {
	    			    				aux2=7;
	    			    			}
	    		    				
	    		    			}
	    		    		}
	    		    	}	
	    			}else {
	    				if(tablero[aux1][aux2]=="____"||(count==2)) {
    		    			count++;
    		    			if(aux2==7) {
    		    				if(tablero[aux1][aux2].charAt(0)=='T') {
    		    					d="O-O"+color.charAt(0);
    								if(f)
    									System.out.println(d);
    								mov.add(d);
    		    				}else {
    			    				aux2=7;
    			    			}
    		    				
    		    			}
    		    		}
	    			}
	    			
	    		}
	    	}
	    	aux2=columna;
	    	aux1=fila;
	    	count=0;
	    	while(aux2>=0) {
	    		aux2--;
	    		if(aux2>=0) {
	    			if(aux2>1) {
	    				if(!jaque(tablero,aux1,aux2,false)) {
	    		    		if(tablero[aux1][aux2]=="____"||(count==3)) {
	    		    			count++;
	    		    			if(aux2==0) {
	    		    				if(tablero[aux1][aux2].charAt(0)=='T') {
	    		    					d="O-O-O"+color.charAt(0);
	    								if(f)
	    									System.out.println(d);
	    								mov.add(d);
	    		    				}else {
	    			    				aux2=-1;
	    			    			}
	    			    			
	    		    				
	    		    			}
	    		    		}
	    		    			
	    		    		
	    		    	}
	    			}else {
	    				if(tablero[aux1][aux2]=="____"||(count==3)) {
    		    			count++;
    		    			if(aux2==0) {
    		    				if(tablero[aux1][aux2].charAt(0)=='T') {
    		    					d="O-O-O"+color.charAt(0);
    								if(f)
    									System.out.println(d);
    								mov.add(d);
    		    				}else {
    			    				aux2=-1;
    			    			}
    			    			
    		    				
    		    			}
    		    		}
	    			}
	    			
	    		}
	    	}
	    }
	    }
	}
	@Override
	public boolean jaque(String[][] tablero,int f,int c,boolean band ) {
		// TODO Auto-generated method stub
		boolean b=false;
		String d=verdiagonal_izq1(tablero,Extras.posAlge(posicion.charAt(0), f, c, posicion.charAt(3)));
		
	    if(d.length()==4 && d.charAt(0)!='_' && d.charAt(3)!=color.charAt(0)) {
	    	if(band)
	    		System.out.println("Rey en jaque por la ficha: "+d);
	    	b=true;
	    }
	     d=verdiagonal_izq2(tablero,Extras.posAlge(posicion.charAt(0), f, c, posicion.charAt(3)));
	    
	    if(d.length()==4 && d.charAt(0)!='_' && d.charAt(3)!=color.charAt(0)) {
	    	if(band)
	    		System.out.println("Rey en jaque por la ficha: "+d);
	    	b=true;
	    }
	    
	    	d=verdiagonal_der1(tablero,Extras.posAlge(posicion.charAt(0), f, c, posicion.charAt(3)));
	    	
	    	if(d.length()==4&& d.charAt(0)!='_'&& d.charAt(3)!=color.charAt(0)) {
	    		if(band)
	    			System.out.println("Rey en jaque por la ficha: "+d);
	        	b=true;
	    	}
	    	d=verdiagonal_der2(tablero,Extras.posAlge(posicion.charAt(0), f, c, posicion.charAt(3)));
	    	
	    	if(d.length()==4&& d.charAt(0)!='_'&& d.charAt(3)!=color.charAt(0)) {
	    		if(band)
	    			System.out.println("Rey en jaque por la ficha: "+d);
	        	b=true;
	    	}
	        	d=verdiagonal_arri1(tablero,Extras.posAlge(posicion.charAt(0), f, c, posicion.charAt(3)));
	        	if(d.length()==4&& d.charAt(0)!='_'&& d.charAt(3)!=color.charAt(0)) {
	        		if(band)
	        			System.out.println("Rey en jaque por la ficha: "+d);			            	
	            	b=true;
	            }
	        	d=verdiagonal_arri2(tablero,Extras.posAlge(posicion.charAt(0), f, c, posicion.charAt(3)));
	        	if(d.length()==4&& d.charAt(0)!='_'&& d.charAt(3)!=color.charAt(0)) {
	        		if(band)
	        			System.out.println("Rey en jaque por la ficha: "+d);
	            	b=true;
	            }
	            	d=verdiagonal_abajo1(tablero,Extras.posAlge(posicion.charAt(0), f, c, posicion.charAt(3)));
	            	
	            	if(d.length()==4&& d.charAt(0)!='_'&& d.charAt(3)!=color.charAt(0)) {
	            		if(band)
	            			System.out.println("Rey en jaque por la ficha: "+d);
	                	b=true;
	                }
	            	d=verdiagonal_abajo2(tablero,Extras.posAlge(posicion.charAt(0), f, c, posicion.charAt(3)));
	            	
	            	if(d.length()==4&& d.charAt(0)!='_'&& d.charAt(3)!=color.charAt(0)) {
	            		if(band)
	            			System.out.println("Rey en jaque por la ficha: "+d);
	                	b=true;
	                }
	                	d=verdiagonal_cab(tablero,Extras.posAlge(posicion.charAt(0), f, c, posicion.charAt(3)));
	                	
	                	if(d.length()==4&& d.charAt(0)!='_'&& d.charAt(3)!=color.charAt(0)) {
	                		if(band)
	                			System.out.println("Rey en jaque por la ficha: "+d);
	                    	b=true;
	                    }
	               return b;
	}
	


public  String verdiagonal_cab(String[][] t,String d) {
	
	int ir=(8-(int)(d.charAt(2)-48));
   
    int jr=(int)(d.charAt(1)-97);
    
    int aux1=ir+2,aux2=jr+1;
    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
    	if(t[aux1][aux2].charAt(0)=='C'&& t[aux1][aux2].charAt(3)!=color.charAt(0)) {
    		return t[aux1][aux2];
    	}
    }
    aux1=ir+2;
    aux2=jr-1;
    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
    	if(t[aux1][aux2].charAt(0)=='C'&& t[aux1][aux2].charAt(3)!=color.charAt(0)) {
    		return t[aux1][aux2];
    	}
    }
    aux1=ir-2;
    aux2=jr+1;
    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
    	if(t[aux1][aux2].charAt(0)=='C'&& t[aux1][aux2].charAt(3)!=color.charAt(0)) {
    		return t[aux1][aux2];
    	}
    }
    aux1=ir-2;
    aux2=jr-1;
    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
    	if(t[aux1][aux2].charAt(0)=='C'&& t[aux1][aux2].charAt(3)!=color.charAt(0)) {
    		return t[aux1][aux2];
    	}
    }
    aux1=ir-1;
    aux2=jr+2;
    
    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
    	if(t[aux1][aux2].charAt(0)=='C'&& t[aux1][aux2].charAt(3)!=color.charAt(0)) {
    		return t[aux1][aux2];
    	}
    }
    aux1=ir+1;
    aux2=jr+2;
    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
    	if(t[aux1][aux2].charAt(0)=='C'&& t[aux1][aux2].charAt(3)!=color.charAt(0)) {
    		return t[aux1][aux2];
    	}
    }
    aux1=ir-1;
    aux2=jr-2;
    if(aux1>=0 && aux1<8 && aux2>=0 && aux2<8  ) {
    	
    	if(t[aux1][aux2].charAt(0)=='C'&& t[aux1][aux2].charAt(3)!=color.charAt(0)) {
    		
    		return t[aux1][aux2];
    	}
    }
    aux1=ir+1;
    aux2=jr-2;
  
    if(aux1>=0 && aux1<8 &&  aux2>=0 && aux2<8  ) {
    	
    	if(t[aux1][aux2].charAt(0)=='C') {
    		
    		return t[aux1][aux2];
    	}
    }
    return "";
}
public String verdiagonal_izq1(String[][] t,String d) {
	
	int ir=(int)(d.charAt(2)-48);
	ir=8-ir;
    
    int jr=(int)(d.charAt(1)-'a');
    int aux1=ir,aux2=jr;
    
    while(aux1!=0 && aux2!=0) {
    	aux1--;
    	aux2--;
    	
    	if(t[aux1][aux2].length()==4) {
    		switch(t[aux1][aux2].charAt(0)) {
    		case 'D':
    			return t[aux1][aux2];
    		case 'A':
    			return t[aux1][aux2];  
    		case 'R':
    			if((ir+1)<7 && (jr+1)<7) {
    				if((ir-1)>0 &&(jr-1)>0){
    					if(t[ir+1][jr+1].charAt(0)=='R'|| t[ir-1][jr-1].charAt(0)=='R') {
    	    				if(aux1<ir-1 &&aux2<jr-1 ) {
    	    					return "";
    	    				}
    	    				if((t[ir+1][jr+1].charAt(3)==color.charAt(0)&&!t[ir+1][jr+1].equals(posicion)) || t[ir-1][jr-1].charAt(3)==color.charAt(0)  ) {
    	    					break;
    	    				}else {
    	    					return t[aux1][aux2];
    	    				}
    	    			}else {
    	    				return "";
    	    			}
    				}else {
    					if((ir+1)<7) {
    						if((jr+1)<7) {
    					if(t[ir+1][jr+1].charAt(0)=='R') {
    	    				if(aux1<ir-1 &&aux2<jr-1 ) {
    	    					return "";
    	    				}
    	    				if((t[ir+1][jr+1].charAt(3)==color.charAt(0)&&!t[ir+1][jr+1].equals(posicion)) ) {
    	    					break;
    	    				}else {
    	    					return t[aux1][aux2];
    	    				}
    	    			}else {
    	    				return "";
    	    			}
    						}
    					}
    				}
    			}else {
    				if((ir-1)>0) {
						if((jr-1)>0) {
    				if(t[ir-1][jr-1].charAt(0)=='R') {
	    				if(aux1<ir-1 &&aux2<jr-1 ) {
	    					return "";
	    				}
	    				if(t[ir-1][jr-1].charAt(3)==color.charAt(0)  ) {
	    					break;
	    				}else {
	    					return t[aux1][aux2];
	    				}
	    			}else {
	    				return "";
	    			}
						}
    				}
    			}
    			
    		case 'P':
    			if(t[aux1+1][aux2+1].charAt(0)=='R') {
    				if(color.charAt(0)=='B'){
    					return t[aux1][aux2];
    				}
    				
    			}else {
    				return "";
    			}
    		case 'C':
    			return "";
    		case 'T':
    			return "";
    		}
    	}
    	}
    	
    
    return "";
}
public String verdiagonal_izq2(String[][] t,String d) {
	int ir=(int)(d.charAt(2)-48);
	ir=8-ir;
    
    int jr=(int)(d.charAt(1)-'a');
    int aux1=ir,aux2=jr;
    while(aux1!=7 && aux2!=7) {
    	aux1++;
    	aux2++;
    	
    	if(t[aux1][aux2].length()==4) {
    		switch(t[aux1][aux2].charAt(0)) {
    		case 'D':
    			return t[aux1][aux2];
    		case 'A':
    			return t[aux1][aux2];
    		case 'P':
    			if(t[aux1-1][aux2-1].charAt(0)=='R') {
    				if(color.charAt(0)=='N'){
    					return t[aux1][aux2];
    				}
    				
    			}else {
    				return "";
    			}
    		case 'R':
    			if((ir+1)<7 && (jr+1)<7) {
    				if((ir-1)>0 &&(jr-1)>0){
    					if(t[ir-1][jr-1].charAt(0)=='R'||t[ir+1][jr+1].charAt(0)=='R') {
    	    				if(aux1>ir+1 &&aux2>jr+1 ) {
    	    					return "";
    	    				}
    	    				if((t[ir-1][jr-1].charAt(3)==color.charAt(0)&&!t[ir-1][jr-1].equals(posicion))|| t[ir+1][jr+1].charAt(3)==color.charAt(0)  ) {
    	    					break;
    	    				}else {
    	    					return t[aux1][aux2];
    	    				}
    	    				
    	    			}else {
    	    				return "";
    	    			}
    				}else {
    					if((ir+1)<7) {
    						if((jr+1)<7) {
    					if((ir+1)<7) {
    						if((jr+1)<7) {
    					if(t[ir+1][jr+1].charAt(0)=='R') {
    	    				if(aux1>ir+1 &&aux2>jr+1 ) {
    	    					return "";
    	    				}
    	    				if(t[ir+1][jr+1].charAt(3)==color.charAt(0)  ) {
    	    					break;
    	    				}else {
    	    					return t[aux1][aux2];
    	    				}
    	    				
    	    			}else {
    	    				return "";
    	    			}
    						}
    					}
    						}
    					}
    				}
    			}else {
    				if((ir-1)<7) {
						if((jr-1)<7) {
    				if((ir-1)>0) {
						if((jr-1)>0) {
    				if(t[ir-1][jr-1].charAt(0)=='R') {
	    				if(aux1>ir+1 &&aux2>jr+1 ) {
	    					return "";
	    				}
	    				if((t[ir-1][jr-1].charAt(3)==color.charAt(0)&&!t[ir-1][jr-1].equals(posicion)) ) {
	    					break;
	    				}else {
	    					return t[aux1][aux2];
	    				}
	    				
	    			}else {
	    				return "";
	    			}
						}
    				}
						}
    				}
    			}
    			
    		case 'C':
    			return "";
    		case 'T':
    			return "";
    		}
    	}
    	
    	}
    
    return "";
}
public  String verdiagonal_der1(String[][] t,String d) {
	int ir=(int)(d.charAt(2)-48);
	ir=8-ir;
    
    int jr=(int)(d.charAt(1)-'a');
    int aux1=ir,aux2=jr;
    
    while(aux1!=7 && aux2!=0) {
    	aux1++;
    	aux2--;
    	
    	if(t[aux1][aux2].length()==4) {
    		switch(t[aux1][aux2].charAt(0)) {
    		case 'D':
    			return t[aux1][aux2];
    		case 'A':
    			return t[aux1][aux2];
    		case 'R':
    			if((ir-1)>0 && (jr+1)<7) {
    				if((ir+1)<7 &&(jr-1)>0){
    					if(t[ir-1][jr+1].charAt(0)=='R' ||t[ir+1][jr-1].charAt(0)=='R') {
    	    				if(aux1>ir+1 &&aux2<jr-1 ) {
    	    					return "";
    	    				}
    	    				if((t[ir-1][jr+1].charAt(3)==color.charAt(0)&&!t[ir-1][jr+1].equals(posicion)) || t[ir+1][jr-1].charAt(3)==color.charAt(0) ) {
    	    					break;
    	    				}else {
    	    					return t[aux1][aux2];
    	    				}
    	    				
    	    			}else {
    	    				return "";
    	    			}
    				}else {
    					if((ir-1)>0) {
    						if((jr+1)<7) {
    					if(t[ir-1][jr+1].charAt(0)=='R' ) {
    	    				if(aux1>ir+1 &&aux2<jr-1 ) {
    	    					return "";
    	    				}
    	    				if((t[ir-1][jr+1].charAt(3)==color.charAt(0)&&!t[ir-1][jr+1].equals(posicion))  ) {
    	    					break;
    	    				}else {
    	    					return t[aux1][aux2];
    	    				}
    	    				
    	    			}else {
    	    				return "";
    	    			}
    						}
    					}
    				}
    			}else {
    				if((ir+1)<7) {
						if((jr-1)>0) {
    				if(t[ir+1][jr-1].charAt(0)=='R') {
        				if(aux1>ir+1 &&aux2<jr-1 ) {
        					return "";
        				}
        				if(t[ir+1][jr-1].charAt(3)==color.charAt(0) ) {
        					break;
        				}else {
        					return t[aux1][aux2];
        				}
        				
        			}else {
        				return "";
        			}
						}
    				}
    			}
    			
    		case 'P':
    			if(t[aux1-1][aux2+1].charAt(0)=='R') {
    				if(color.charAt(0)=='N'){
    					return t[aux1][aux2];
    				}
    				
    			}else {
    				return "";
    			}
    		case 'C':
    			return "";
    		case 'T':
    			return "";
    		}
    	}
    	
    	}
    
    return "";
}
public  String verdiagonal_der2(String[][] t,String d) {
	int ir=(int)(d.charAt(2)-48);
	ir=8-ir;
    
    int jr=(int)(d.charAt(1)-'a');
    int aux1=ir,aux2=jr;
    
    	while(aux1!=0 && aux2!=7) {
    		aux1--;
        	aux2++;
        	
        	if(t[aux1][aux2].length()==4) {
        		switch(t[aux1][aux2].charAt(0)) {
        		case 'D':
        			return t[aux1][aux2];
        		case 'A':
        			return t[aux1][aux2];
        		case 'P':
        			if(t[aux1+1][aux2-1].charAt(0)=='R') {
        				if(color.charAt(0)=='B'){
        				return t[aux1][aux2];
        				}
        			}else {
        				return "";
        			}
        		case 'R':
        			if((ir-1)>0 && (jr+1)<7) {
        				if((ir+1)<7 &&(jr-1)>0){
        					if(t[ir+1][jr-1].charAt(0)=='R'|| t[ir-1][jr+1].charAt(0)=='R') {
                				if(aux1<ir-1 &&aux2>jr+1 ) {
                					return "";
                				}
                				if((t[ir+1][jr-1].charAt(3)==color.charAt(0)&&!t[ir+1][jr-1].equals(posicion)) || t[ir-1][jr+1].charAt(3)==color.charAt(0)  ) {
                					break;
                				}else {
                					return t[aux1][aux2];
                				}
                				
                			}else {
                				return "";
                			}
        				}else {
        					if((ir-1)>0) {
        						if((jr+1)<7) {
        					if(t[ir-1][jr+1].charAt(0)=='R') {
                				if(aux1<ir-1 &&aux2>jr+1 ) {
                					return "";
                				}
                				if(t[ir-1][jr+1].charAt(3)==color.charAt(0)  ) {
                					break;
                				}else {
                					return t[aux1][aux2];
                				}
                				
                			}else {
                				return "";
                			}
        					}
        					}
        				}
        			}else {
        				if((ir+1)<7) {
    						if((jr-1)>0) {
        				if(t[ir+1][jr-1].charAt(0)=='R') {
            				if(aux1<ir-1 &&aux2>jr+1 ) {
            					return "";
            				}
            				if((t[ir+1][jr-1].charAt(3)==color.charAt(0)&&!t[ir+1][jr-1].equals(posicion)) ) {
            					break;
            				}else {
            					return t[aux1][aux2];
            				}
            				
            			}else {
            				return "";
            			}
        			}
        				}
        			}
        			
	    		case 'C':
        			return "";
	    		case 'T':
        			return "";
        		
        		}
        	}
    		
        	}
        	
        
    return "";
}
public String verdiagonal_arri1(String[][] t,String d) {
	
	int i=(int)(d.charAt(2)-48);
    i=8-i;
    
    int j=(int)(d.charAt(1)-'a');
    int aux1=i;
    
    while(aux1!=0) {
    	aux1--;
    	
    	if(t[aux1][j].length()==4) {
    		switch(t[aux1][j].charAt(0)) {
    		case 'D':
    			return t[aux1][j];
    		case 'T':
    			return t[aux1][j];
    		case 'R':
    			if((i-1)>0) {
    				if((i+1)<7){
    					if(t[i+1][j].charAt(0)=='R' || t[i-1][j].charAt(0)=='R') {
    	    				if(aux1<i-1) {
    	    					return "";
    	    				}
    	    				
    	    				if((t[i+1][j].charAt(3)==color.charAt(0)&&!t[i+1][j].equals(posicion))|| t[i-1][j].charAt(3)==color.charAt(0)) {
    	    					
    	    					break;
    	    				}else {
    	    					
    	    					return t[aux1][j];
    	    				}
    	    				
    	    			}else {
    	    				return "";
    	    			}
    				}else {
    					
    					if(t[i-1][j].charAt(0)=='R') {
    	    				if(aux1<i-1) {
    	    					return "";
    	    				}
    	    				;
    	    				if(t[i-1][j].charAt(3)==color.charAt(0)) {
    	    					
    	    					break;
    	    				}else {
    	    					
    	    					return t[aux1][j];
    	    				}
    	    				
    	    			}else {
    	    				return "";
    	    			}
    				}
    			}else {
    				if(t[i+1][j].charAt(0)=='R' ) {
        				if(aux1<i-1) {
        					return "";
        				}
        				
        				if((t[i+1][j].charAt(3)==color.charAt(0)&&!t[i+1][j].equals(posicion))) {
        					
        					break;
        				}else {
        					
        					return t[aux1][j];
        				}
        				
        			}else {
        				return "";
        			}
    			}
    		
    		case 'P':
    			return "";
    		case 'C':
    			return "";
    		case 'A':
    			return "";
    		}
    		
        		
        	
    	}
    	
    	}	
    
    return "";
}
public  String verdiagonal_arri2(String[][] t,String d) {
	int i=(int)(d.charAt(2)-48);
    i=8-i;
    
    int j=(int)(d.charAt(1)-'a');
    int aux1=i;
	
	while(aux1!=7) {
		aux1++;
		
			
		if(t[aux1][j].length()==4) {
    		switch(t[aux1][j].charAt(0)) {
    		case 'D':
    			return t[aux1][j];
    		case 'T':
    			return t[aux1][j];
    		case 'R':
    			if((i-1)>0) {
    				if((i+1)<7){
    					if(t[i-1][j].charAt(0)=='R'|| t[i+1][j].charAt(0)=='R') {
    	    				if(aux1>i+1) {
    	    					return "";
    	    				}
    	    				if((t[i-1][j].charAt(3)==color.charAt(0)&&!t[i-1][j].equals(posicion))|| t[i+1][j].charAt(3)==color.charAt(0)  ) {
    	    					
    	    					break;
    	    				}else {
    	    					
    	    					return t[aux1][j];
    	    				}
    	    				
    	    			}else {
    	    				return "";
    	    			}
    				}else {
    					if(t[i-1][j].charAt(0)=='R') {
    	    				if(aux1>i+1) {
    	    					return "";
    	    				}
    	    				if((t[i-1][j].charAt(3)==color.charAt(0)&&!t[i-1][j].equals(posicion)) ) {
    	    					
    	    					break;
    	    				}else {
    	    					
    	    					return t[aux1][j];
    	    				}
    	    				
    	    			}else {
    	    				return "";
    	    			}
    				}
    			}else {
    				if(t[i+1][j].charAt(0)=='R') {
        				if(aux1>i+1) {
        					return "";
        				}
        				if(t[i+1][j].charAt(3)==color.charAt(0)  ) {
        					
        					break;
        				}else {
        					
        					return t[aux1][j];
        				}
        				
        			}else {
        				return "";
        			}
    			}
    			
    		case 'P':
    			return "";
    		case 'C':
    			return "";
    		case 'A':
    			return "";
    		}
    		
        		
        	
    	}    		
    	
		}
    


    return "";

}
public  String verdiagonal_abajo1(String[][] t,String d ) {
	int i=(int)(d.charAt(2)-48);
    i=8-i;
    
   // if(d.charAt(3)=='N') {
    	//i=7-i;
    //}
    int j=(int)(d.charAt(1)-'a');
	int aux1=j;
	
	
    while(aux1!=0){
    	aux1--;
    	//System.out.println("i"+i);
    	//System.out.println("axu1: "+aux1);
    	if(t[i][aux1].length()==4) {
    	
    		switch(t[i][aux1].charAt(0)) {
    		case 'D':
    			return t[i][aux1];
    		case 'T':
    			return t[i][aux1];
    		case 'R':
    			if((j-1)>0) {
    				if((j+1)<7){
    	//				System.out.println("22dfsdf: "+t[i][aux1]);
    		//			System.out.println("22dfsdf: "+t[i][j-1]);
    			///		System.out.println("dfsdf: "+d);
    				//	System.out.println("procesado: "+t[i][j]);
    					//System.out.println("color: "+color);
    					if(t[i][j+1].charAt(0)=='R'|| t[i][j-1].charAt(0)=='R') {
    	    				if(aux1<j-1) {
    	    					return "";
    	    				}
    	    				if((t[i][j+1].charAt(3)==color.charAt(0)&&!t[i][j+1].equals(posicion)) || t[i][j-1].charAt(3)==color.charAt(0)  ) {
    	    					break;
    	    				}else {
    	    					if(t[i][aux1].equals("Re6B")) {
    	    						//System.out.println("i: "+i);
    	    						//System.out.println("j: "+aux1);
    	    					}
    	    					return t[i][aux1];
    	    				}
    	    				
    	    			}else {
    	    				
    	    				return "";
    	    			}
    				}else {
    					
    					if( t[i][j-1].charAt(0)=='R') {
    	    				if(aux1<j-1) {
    	    					return "";
    	    				}
    	    				if( t[i][j-1].charAt(3)==color.charAt(0)  ) {
    	    				//	System.out.println("wfrd");
    	    					break;
    	    				}else {
    	    					//System.out.println("dwtdf");
    	    					if(t[i][aux1].equals("Re6B")) {
    	    						//System.out.println("i: "+i);
    	    					//	System.out.println("j: "+aux1);
    	    					}
    	    					return t[i][aux1];
    	    					
    	    				}
    	    				
    	    			}else {
    	    				
    	    				return "";
    	    			}
    				}
    			}else {
    				//System.out.println("2dfsdf: "+t[i][aux1]);
    				if(t[i][j+1].charAt(0)=='R') {
        				if(aux1<j-1) {
        					return "";
        				}
        				if((t[i][j+1].charAt(3)==color.charAt(0)&&!t[i][j+1].equals(posicion)) ) {
        					break;
        				}else {
        					if(t[i][aux1].equals("Re6B")) {
	    				//		System.out.println("di: "+i);
	    					//	System.out.println("d2j: "+aux1);
	    					}
        					return t[i][aux1];
        				}
        				
        			}else {
        				
        				return "";
        			}
    			}
    			
    		case 'P':
    			return "";
    		case 'C':
    			return "";
    		case 'A':
    			return "";
    		}
    	}
    	}
    	
    return "";

}
public  String verdiagonal_abajo2(String[][] t,String d ) {
	int i=(int)(d.charAt(2)-48);
    i=8-i;
 
    int j=(int)(d.charAt(1)-'a');
	int aux1=j;
    
	
	while(aux1!=7) {
		aux1++;
    	
		if(t[aux1][j].length()==4) {
    		switch(t[i][aux1].charAt(0)) {
    		case 'D':
    			return t[i][aux1];
    		case 'T':
    			return t[i][aux1];
    		case 'R':
    			if((j-1)>0) {
    				if((j+1)<7){
    					if(t[i][j-1].charAt(0)=='R' || t[i][j+1].charAt(0)=='R') {
    	    				if(aux1>j+1) {
    	    					return "";
    	    				}
    	    				if((t[i][j-1].charAt(3)==color.charAt(0)&&!t[i][j-1].equals(posicion)) || t[i][j+1].charAt(3)==color.charAt(0) ) {
    	    					break;
    	    				}else {
    	    					return t[i][aux1];
    	    				}
    	    				
    	    			}else {
    	    				return "";
    	    			}
    				}else {
    					if(t[i][j-1].charAt(0)=='R' ) {
    	    				if(aux1>j+1) {
    	    					return "";
    	    				}
    	    				if((t[i][j-1].charAt(3)==color.charAt(0)&&!t[i][j-1].equals(posicion))  ) {
    	    					break;
    	    				}else {
    	    					return t[i][aux1];
    	    				}
    	    				
    	    			}else {
    	    				return "";
    	    			}
    				}
    			}else {
    				if( t[i][j+1].charAt(0)=='R') {
        				if(aux1>j+1) {
        					return "";
        				}
        				if(t[i][j+1].charAt(3)==color.charAt(0) ) {
        					break;
        				}else {
        					return t[i][aux1];
        				}
        				
        			}else {
        				return "";
        			}
    			}
    			
    		case 'P':
    			return "";
    		case 'C':
    			return "";
    		case 'A':
    			return "";
    		}
    		
        		
        	
    	} 
		
    	
		}
    
	return "";
       


}





@Override
public String especial(boolean f) {
	// TODO Auto-generated method stub
	this.primerMov=false;
	return null;
}
@Override
public void reiniciarEspecial() {
	// TODO Auto-generated method stub
	this.primerMov=true;
}
@Override
public int puntos() {
	// TODO Auto-generated method stub
	return this.puntuacion;
}



}
