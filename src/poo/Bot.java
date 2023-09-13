package poo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Bot implements Runnable {
	public static boolean marcado=false,marcadoJaque=false;
	@Override
    public void run() {
		
       moverBot(Main.tB,Main.tN,Main.tP,Main.fig,Main.figP,Main.b,Main.fichas,Main.mov,Main.movidasJaque);
       Main.bots=true;
    }
	
	public static void moverBot(String[][] t1,String[][] t2,String[][] tP,ArrayList<Pieza> fig,ArrayList<Pieza> figP,boolean b, ArrayList<String> fichas, ArrayList<String> mov, ArrayList<String> movidasJaque) {
		String pos;
		
		pos=listarFichasPosiblesJaqueBot(t1,t2,tP,fig,figP,b,fichas,mov,movidasJaque,true,false);
			if(!Main.jaque(t1,fig,b,true)) {
				
				if(pos.length()!=0) {
					
					if(!pos.equals("dddd")) {
						Main.moverse=pos;
						
					}
					
				}
				if(fichas.size()==0) {
					if(Main.colorF) {
						JOptionPane.showMessageDialog(null, "Tablas, Rey negro ahogado");
					}else {
						JOptionPane.showMessageDialog(null, "Tablas, Rey blanco ahogado");
					}
					
				//	o=10;
				}
			}else {
				
				if(pos.length()!=0) {
					
					marcadoJaque=true;
					
				
					Main.moverse=pos;
					
					//moverRey(tB,tN,fig,b,mov,pos,movidasJaque,pos);
					
				}
				
				if(fichas.size()==0) {
					if(Main.colorF) {
						JOptionPane.showMessageDialog(null, "Jaque mate, rey negro pierde");
					}else {
						JOptionPane.showMessageDialog(null, "Jaque mate, rey blanco pierde");
					}
					
					
				//	o=10;
				}
			}
			
				if(marcado) {
					if(marcadoJaque) {
						Main.rearmar();
						
						String posic=pos.substring(1,3);
						//moverFicha(tB,tN,fig,b,mov,pos,posic);
						if(b) {
							Main.moverRey(t1,t2,fig,b,mov,Main.fichaDelBot2,movidasJaque,posic,true);
						}else {
							Main.moverRey(t1,t2,fig,b,mov,Main.fichaDelBot,movidasJaque,posic,true);
						}
						
						
						
					}else {
						Main.rearmar();
						if(!pos.equals("")) {
							String posic=pos.substring(1,3);
							
							System.out.println("posic: "+posic);
							System.out.println("ficha: "+Main.fichaDelBot);
							if(b) {
								Main.moverFicha(t1,t2,fig,b,mov,Main.fichaDelBot2,posic,movidasJaque,true);
							}else {
								Main.moverFicha(t1,t2,fig,b,mov,Main.fichaDelBot,posic,movidasJaque,true);
							}
							
						}
						
					}
					if(Main.movido) {
						Main.b=!Main.b;
						
						Main.movido=false;
						Tablero.actualizar();
						marcado=false;
						marcadoJaque=false;
					}
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
								
								
								Main.llenarTableroPB(tP,fig);
								
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
			
				ficha=Main.calificandoMovidas(t1,t2,tP,fig,figP,b,fichas,mov,movidas,fno,df,Main.dfd2);
				System.out.println("fichsssa: "+ficha);
			
				marcado=true;
				return ficha;
			
	}
}