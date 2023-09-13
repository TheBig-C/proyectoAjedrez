package poo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Main1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Main1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("PractiChess");
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 66));
		lblTitulo.setBounds(122, 28, 350, 134);
		contentPane.add(lblTitulo);
		
		JButton btnEmpezar = new JButton("Nueva partida");
		btnEmpezar.setForeground(new Color(255, 255, 255));
		btnEmpezar.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnEmpezar.setBackground(new Color(139, 69, 19));
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modoDeJuego mod=new modoDeJuego();
				mod.setVisible(true);
			}
		});
		btnEmpezar.setBounds(168, 154, 170, 21);
		contentPane.add(btnEmpezar);
		
		JButton btnCargarPartida = new JButton("Cargar Partida");
		btnCargarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar b=new Buscar();
				b.setVisible(true);
				//Main.CargarPartida(j);
			}
		});
		btnCargarPartida.setForeground(new Color(255, 255, 255));
		btnCargarPartida.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCargarPartida.setBackground(new Color(139, 69, 19));
		btnCargarPartida.setBounds(168, 195, 170, 21);
		contentPane.add(btnCargarPartida);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(139, 69, 19));
		btnSalir.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnSalir.setBounds(168, 238, 170, 21);
		contentPane.add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Logo1.png"));
		lblNewLabel.setBounds(10, 49, 116, 127);
		contentPane.add(lblNewLabel);
	}
}
