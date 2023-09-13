package poo;

import java.util.ArrayList;

public interface Movimientos {
	public void mostrarPosiblesMovimientos(String[][] tablero,ArrayList<String> mov,boolean f);
	public boolean jaque(String[][] tablero,int f,int c,boolean b);
	void reiniciarEspecial();
}
