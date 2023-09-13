package poo;

import java.util.ArrayList;

public abstract class Pieza  implements Movimientos{
	public Pieza() {
	}
	public abstract String obtenerPosicion();
	public abstract void cambiarPosicion(String posicion);
	public void mostrarPosiblesMovimientos(String[][] tablero,ArrayList<String> mov,boolean f) {
		
	}
	public boolean jaque(String[][] tablero,int f,int c) {
		return false;
		
	}
	public abstract int puntos();
	public boolean isEstado() {
		return false;
	}
	
	protected abstract void setEstado(boolean estado,int num);
	public abstract String especial(boolean f);
	public abstract boolean isEspecial();
	public abstract void reiniciarEspecial();
	}
