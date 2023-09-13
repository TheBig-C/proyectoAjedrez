package poo;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Tablero extends JFrame {

    private JPanel contentPane;
    static JButton[][]  boton = new JButton[8][8] ;
    public static JTextArea textAreaJugadas;
    public static int tiempo=0;
    private static Timer timer;
    private JLabel labelPlayer1, labelPlayer2; 
    private int player1Time, player2Time;
    public static boolean isPlayer1Turn=true;

public Tablero() {
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boton[i][j] = new JButton();
                boton[i][j].addActionListener(new BotonActionListener(i, j));
            }
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1144, 750);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        JPanel panelIzquierdo = new JPanel(new GridLayout(1, 2));
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(new Color(139, 69, 19));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 2.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        contentPane.add(panelIzquierdo, gbc);
        panelDerecho.setLayout(null);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        contentPane.add(panelDerecho, gbc);

        JPanel tablero = new JPanel(new GridLayout(8, 8));
        panelIzquierdo.add(tablero);

        int d = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0) {
                    d = 0;
                } else {
                    d = 1;
                }
                if (((i * 10) + j) % 2 == d) {
                    boton[i][j].setBackground(new java.awt.Color(255, 255, 255));
                } else {
                    boton[i][j].setBackground(new java.awt.Color(51, 51, 51));
                }

                boton[i][j].setToolTipText("");
                boton[i][j].setBorder(javax.swing.BorderFactory.createEtchedBorder());
                tablero.add(boton[i][j]);
            }
        }

        // Componente de relleno en el panel derecho para hacerlo más delgado
        JPanel filler = new JPanel();
        filler.setBounds(188, 5, 10, 10);
        filler.setBackground(new Color(139, 69, 19));
        panelDerecho.add(filler);
        
        JLabel lblTitulo = new JLabel("PractiChess");
        lblTitulo.setBackground(new Color(178, 34, 34));
        lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblTitulo.setBounds(14, 56, 184, 48);
        panelDerecho.add(lblTitulo);
         
        textAreaJugadas = new JTextArea();
        textAreaJugadas.setEditable(false);
       
        JScrollPane scrollPane = new JScrollPane(textAreaJugadas);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(14, 114, 172, 488);
        panelDerecho.add(scrollPane);
        
        JButton btnGuardarCerrar = new JButton("Guardar y Cerrar");
        btnGuardarCerrar.setForeground(new Color(253, 245, 230));
        btnGuardarCerrar.setBackground(new Color(139, 0, 0));
        btnGuardarCerrar.setVerticalAlignment(SwingConstants.BOTTOM);
        btnGuardarCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
					try {
						String input = VentanaModal1.showCustomInputDialog(Main.tab);
						Main.guardarPartida(input,0,0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				
        	}
        });
        btnGuardarCerrar.setBounds(24, 612, 151, 21);
        panelDerecho.add(btnGuardarCerrar);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnCerrar.setBackground(new Color(139, 0, 0));
        btnCerrar.setForeground(new Color(255, 218, 185));
        btnCerrar.setBounds(24, 652, 151, 21);
        panelDerecho.add(btnCerrar);
        
        
    }
    public Tablero(int Tiempo) {
    	
        tiempo=Tiempo;
        tiempo = tiempo * 60;
        player1Time = player2Time = tiempo;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boton[i][j] = new JButton();
                boton[i][j].addActionListener(new BotonActionListener(i, j));
            }
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1144, 750);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        // 5 minutos para cada jugador
        
      
        JPanel panelIzquierdo = new JPanel(new GridLayout(1, 2));
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(new Color(139, 69, 19));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 2.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        contentPane.add(panelIzquierdo, gbc);
        panelDerecho.setLayout(null);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        contentPane.add(panelDerecho, gbc);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBackground(new Color(128, 0, 0));
        getContentPane().add(panel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPlayer1Turn) {
                	player2Time--;
                    labelPlayer2.setText(formatTime(player2Time));
                    if (player2Time <= 0) {
                        stopTimer();
                        Main.timeIsOver(true);
                        JOptionPane.showMessageDialog(Tablero.this, "¡Tiempo agotado! Negras ganan.");
                    }
                } else {
                    
                    player1Time--;
                    labelPlayer1.setText(formatTime(player1Time));
                    if (player1Time <= 0) {
                        stopTimer();
                        Main.timeIsOver(false);
                        JOptionPane.showMessageDialog(Tablero.this, "¡Tiempo agotado! Blancas ganan.");
                    }
                }
            }
        });

        JPanel tablero = new JPanel(new GridLayout(8, 8));
        panelIzquierdo.add(tablero);

        int d = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0) {
                    d = 0;
                } else {
                    d = 1;
                }
                if (((i * 10) + j) % 2 == d) {
                    boton[i][j].setBackground(new java.awt.Color(255, 255, 255));
                } else {
                    boton[i][j].setBackground(new java.awt.Color(51, 51, 51));
                }

                boton[i][j].setToolTipText("");
                boton[i][j].setBorder(javax.swing.BorderFactory.createEtchedBorder());
                tablero.add(boton[i][j]);
            }
        }

        // Componente de relleno en el panel derecho para hacerlo más delgado
        JPanel filler = new JPanel();
        filler.setBounds(188, 5, 10, 10);
        filler.setBackground(new Color(139, 69, 19));
        panelDerecho.add(filler);
         
        textAreaJugadas = new JTextArea();
        textAreaJugadas.setEditable(false);
       
        JScrollPane scrollPane = new JScrollPane(textAreaJugadas);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(14, 151, 172, 380);
        panelDerecho.add(scrollPane);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnCerrar.setBackground(new Color(139, 0, 0));
        btnCerrar.setForeground(new Color(255, 218, 185));
        btnCerrar.setBounds(24, 586, 151, 21);
        panelDerecho.add(btnCerrar);
        

        labelPlayer1 = new JLabel(formatTime(player1Time));
        labelPlayer1.setBounds(24, 25, 166, 56);
        panelDerecho.add(labelPlayer1);
        labelPlayer1.setBackground(new Color(128, 0, 0));
        labelPlayer1.setForeground(new Color(255, 255, 255));
        labelPlayer1.setFont(new Font("Arial", Font.BOLD, 32));
        labelPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        
                labelPlayer2 = new JLabel(formatTime(player2Time));
                labelPlayer2.setBounds(24, 617, 166, 56);
                panelDerecho.add(labelPlayer2);
                labelPlayer2.setForeground(new Color(255, 255, 255));
                labelPlayer2.setBackground(new Color(128, 0, 0));
                labelPlayer2.setFont(new Font("Arial", Font.BOLD, 32));
                labelPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
                
                JLabel lblTitulo = new JLabel("PractiChess");
                lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 30));
                lblTitulo.setBackground(new Color(178, 34, 34));
                lblTitulo.setBounds(14, 91, 184, 48);
                panelDerecho.add(lblTitulo);
                
                JButton btnGuardar = new JButton("Guardar y cerrar");
                btnGuardar.setForeground(new Color(255, 218, 185));
                btnGuardar.setBackground(new Color(139, 0, 0));
                btnGuardar.setBounds(24, 550, 151, 21);
                panelDerecho.add(btnGuardar);
               
                btnGuardar.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		

                		try {
                			String input = VentanaModal1.showCustomInputDialog(Main.tab);
    						Main.guardarPartida(input,player2Time,player1Time);
    					} catch (SQLException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
                		dispose();
                	}
                });
        
    }
 public Tablero(int TiempoB,int TiempoN) {
    	
        
      
        player1Time = TiempoN;
        player2Time = TiempoB;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boton[i][j] = new JButton();
                boton[i][j].addActionListener(new BotonActionListener(i, j));
            }
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1144, 750);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        // 5 minutos para cada jugador
        
      
        JPanel panelIzquierdo = new JPanel(new GridLayout(1, 2));
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(new Color(139, 69, 19));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 2.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        contentPane.add(panelIzquierdo, gbc);
        panelDerecho.setLayout(null);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        contentPane.add(panelDerecho, gbc);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBackground(new Color(128, 0, 0));
        getContentPane().add(panel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPlayer1Turn) {
                	player2Time--;
                    labelPlayer2.setText(formatTime(player2Time));
                    if (player2Time <= 0) {
                        stopTimer();
                        Main.timeIsOver(true);
                        JOptionPane.showMessageDialog(Tablero.this, "¡Tiempo agotado! Negras ganan.");
                    }
                } else {
                    
                    player1Time--;
                    labelPlayer1.setText(formatTime(player1Time));
                    if (player1Time <= 0) {
                        stopTimer();
                        Main.timeIsOver(false);
                        JOptionPane.showMessageDialog(Tablero.this, "¡Tiempo agotado! Blancas ganan.");
                    }
                }
            }
        });

        JPanel tablero = new JPanel(new GridLayout(8, 8));
        panelIzquierdo.add(tablero);

        int d = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0) {
                    d = 0;
                } else {
                    d = 1;
                }
                if (((i * 10) + j) % 2 == d) {
                    boton[i][j].setBackground(new java.awt.Color(255, 255, 255));
                } else {
                    boton[i][j].setBackground(new java.awt.Color(51, 51, 51));
                }

                boton[i][j].setToolTipText("");
                boton[i][j].setBorder(javax.swing.BorderFactory.createEtchedBorder());
                tablero.add(boton[i][j]);
            }
        }

        // Componente de relleno en el panel derecho para hacerlo más delgado
        JPanel filler = new JPanel();
        filler.setBounds(188, 5, 10, 10);
        filler.setBackground(new Color(139, 69, 19));
        panelDerecho.add(filler);
         
        textAreaJugadas = new JTextArea();
        textAreaJugadas.setEditable(false);
       
        JScrollPane scrollPane = new JScrollPane(textAreaJugadas);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(14, 151, 172, 380);
        panelDerecho.add(scrollPane);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnCerrar.setBackground(new Color(139, 0, 0));
        btnCerrar.setForeground(new Color(255, 218, 185));
        btnCerrar.setBounds(24, 586, 151, 21);
        panelDerecho.add(btnCerrar);
        

        labelPlayer1 = new JLabel(formatTime(player1Time));
        labelPlayer1.setBounds(24, 25, 166, 56);
        panelDerecho.add(labelPlayer1);
        labelPlayer1.setBackground(new Color(128, 0, 0));
        labelPlayer1.setForeground(new Color(255, 255, 255));
        labelPlayer1.setFont(new Font("Arial", Font.BOLD, 32));
        labelPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        
                labelPlayer2 = new JLabel(formatTime(player2Time));
                labelPlayer2.setBounds(24, 617, 166, 56);
                panelDerecho.add(labelPlayer2);
                labelPlayer2.setForeground(new Color(255, 255, 255));
                labelPlayer2.setBackground(new Color(128, 0, 0));
                labelPlayer2.setFont(new Font("Arial", Font.BOLD, 32));
                labelPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
                
                JLabel lblTitulo = new JLabel("PractiChess");
                lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 30));
                lblTitulo.setBackground(new Color(178, 34, 34));
                lblTitulo.setBounds(14, 91, 184, 48);
                panelDerecho.add(lblTitulo);
                
                JButton btnGuardar = new JButton("Guardar y cerrar");
                btnGuardar.setForeground(new Color(255, 218, 185));
                btnGuardar.setBackground(new Color(139, 0, 0));
                btnGuardar.setBounds(24, 550, 151, 21);
                panelDerecho.add(btnGuardar);
               
                btnGuardar.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		
                		try {
                			String input = VentanaModal1.showCustomInputDialog(Main.tab);
    						Main.guardarPartida(input,player2Time,player1Time);
    					} catch (SQLException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
                		dispose();
                	}
                });
        
    }
    private class BotonActionListener implements ActionListener {
        private int fila;
        private int columna;

        public BotonActionListener(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	if(!Main.bot) {
        		Main.conexion(fila,columna);
        	}else {
        		Main.conexionConBot(fila,columna);
        	}
            
        }
    }
    public static void actualizar() {
    	textAreaJugadas.setText("Jugadas: \n");
    	ArrayList<String> Jugadas= Main.leerPersonasTexto();
    	for(String i:Jugadas) {
    		textAreaJugadas.append(i+ "\n");
    	}
    }
    private String formatTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static void startTimer() {
        timer.start();
    }

    public static void stopTimer() {
        timer.stop();
    }

    public static void switchTurn() {
        isPlayer1Turn = !isPlayer1Turn;
        System.out.println("cambio de turno: "+isPlayer1Turn);
    }
    
    public void crearTablero(boolean f,boolean colorF) {
    	if(f) {
    		
            isPlayer1Turn = true;
            startTimer();
    	}
    	
    	String posicion,color="Negras";
		int columna=4;
		int fila=0,aux=-1;
		if(colorF) {
			for(int i=0;i<2;i++) {
				
				
				
				
				if(color.equals("Negras")) {
					
					boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\ReyNegro.png"));
				}else {
					boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Rey.png"));
				}
				
				 
				columna=3;
				if(color.equals("Negras")) {
					boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\ReinaNegra.png"));
				}else {
					boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Reina.png"));
				}
				columna=5;
				for(int j=0;j<2;j++) {
					if(color.equals("Negras")) {
						boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\AlfilNegro.png"));
					}else {
						boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Alfil.png"));
					}
					columna=2;
				}
				columna=1;
				for(int j=0;j<2;j++) {
					if(color.equals("Negras")) {
						boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\CaballoNegro.png"));
					}else {
						boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Caballo.png"));
					}
					columna=6;
				}
				columna=0;
				for(int j=0;j<2;j++) {
					if(color.equals("Negras")) {
						boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\TorreNegra.png"));
					}else {
						boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Torre.png"));
					}
					columna=7;
				}
				if(color.equals("Negras")) {
					fila=1;
				}else {
					fila=6;
				}
				
				columna=0;
				for(int j=0;j<8;j++) {
				
					if(color.equals("Negras")) {
						boton[fila][columna+j].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\PeonNegro.png"));
					}else {
						boton[fila][columna+j].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Peon.png"));
					
					
					}
				}
				color="Blancas";
				fila=7;
				aux=1;
				columna=4;
			}
		//	Main.conexionConBot(00, 0);
		}else {
			for(int i=0;i<2;i++) {
				
				
				
				
				if(color.equals("Negras")) {
					
					boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Rey.png"));
				}else {
					boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\ReyNegro.png"));
				}
				
				 
				columna=3;
				if(color.equals("Negras")) {
					boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Reina.png"));
				}else {
					boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\ReinaNegra.png"));
				}
				columna=5;
				for(int j=0;j<2;j++) {
					if(color.equals("Negras")) {
						boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Alfil.png"));
					}else {
						boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\AlfilNegro.png"));
					}
					columna=2;
				}
				columna=1;
				for(int j=0;j<2;j++) {
					if(color.equals("Negras")) {
						boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Caballo.png"));
					}else {
						boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\CaballoNegro.png"));
					}
					columna=6;
				}
				columna=0;
				for(int j=0;j<2;j++) {
					if(color.equals("Negras")) {
						boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Torre.png"));
					}else {
						boton[fila][columna].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\TorreNegra.png"));
					}
					columna=7;
				}
				if(color.equals("Negras")) {
					fila=1;
				}else {
					fila=6;
				}
				
				columna=0;
				for(int j=0;j<8;j++) {
				
					if(color.equals("Negras")) {
						boton[fila][columna+j].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Peon.png"));
					}else {
						boton[fila][columna+j].setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\PeonNegro.png"));
					
					
					}
				}
				color="Blancas";
				fila=7;
				aux=1;
				columna=4;
			}
			
		}

}
}