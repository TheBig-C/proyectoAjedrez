package poo;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class modoDeJuego extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public modoDeJuego() {
		setBackground(new Color(128, 0, 0));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 376);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("PractiChess");
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 94));
		lblTitulo.setBounds(68, 10, 529, 134);
		contentPane.add(lblTitulo);
		
		JLabel lblTiempo = new JLabel("Tiempo:");
		lblTiempo.setForeground(Color.WHITE);
		lblTiempo.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblTiempo.setBounds(28, 143, 89, 21);
		contentPane.add(lblTiempo);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setBackground(new Color(139, 69, 19));
		comboBox.setBounds(180, 154, 207, 21);
		contentPane.add(comboBox);
		comboBox.addItem("Sin limites");
		comboBox.addItem("30 min");
		comboBox.addItem("15 min");
        comboBox.addItem("10 min");
        comboBox.addItem("5 min");
        comboBox.addItem("3 min");
        comboBox.addItem("1 min");
        // Establecer un valor predeterminado seleccionado
        comboBox.setSelectedItem("Sin Limites");
		JCheckBox chckbxBot = new JCheckBox("Bot");
		chckbxBot.setForeground(Color.WHITE);
		chckbxBot.setBackground(new Color(128, 0, 0));
		chckbxBot.setBorderPainted(true);
		chckbxBot.setFont(new Font("Times New Roman", Font.BOLD, 21));
		chckbxBot.setBounds(28, 200, 93, 21);
		contentPane.add(chckbxBot);
		
		
	
		
		JRadioButton rdbtnColorBlanco = new JRadioButton("Blancas");
		rdbtnColorBlanco.setForeground(Color.WHITE);
		rdbtnColorBlanco.setBackground(new Color(128, 0, 0));
		rdbtnColorBlanco.setFont(new Font("Times New Roman", Font.BOLD, 17));
		rdbtnColorBlanco.setBounds(28, 278, 103, 21);
		contentPane.add(rdbtnColorBlanco);
		
		JRadioButton rdbtnNewColorNegro = new JRadioButton("Negras");
		rdbtnNewColorNegro.setForeground(Color.WHITE);
		rdbtnNewColorNegro.setBackground(new Color(128, 0, 0));
		rdbtnNewColorNegro.setFont(new Font("Times New Roman", Font.BOLD, 17));
		rdbtnNewColorNegro.setBounds(154, 278, 103, 21);
		contentPane.add(rdbtnNewColorNegro);
		
		JRadioButton rdbtnNewRandom = new JRadioButton("Aleatoria");
		rdbtnNewRandom.setForeground(Color.WHITE);
		rdbtnNewRandom.setBackground(new Color(128, 0, 0));
		rdbtnNewRandom.setFont(new Font("Times New Roman", Font.BOLD, 17));
		rdbtnNewRandom.setBounds(281, 278, 103, 21);
		contentPane.add(rdbtnNewRandom);
		ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnColorBlanco);
        buttonGroup.add(rdbtnNewColorNegro);
        buttonGroup.add(rdbtnNewRandom);
        JCheckBox chckbxAutoAprendizaje = new JCheckBox("Autoaprendizaje");
        chckbxAutoAprendizaje.setForeground(Color.WHITE);
		chckbxAutoAprendizaje.setFont(new Font("Times New Roman", Font.BOLD, 21));
		chckbxAutoAprendizaje.setBorderPainted(true);
		chckbxAutoAprendizaje.setBackground(new Color(128, 0, 0));
		chckbxAutoAprendizaje.setBounds(281, 203, 188, 21);
		contentPane.add(chckbxAutoAprendizaje);
        // Agregar ActionListener a los botones
        rdbtnColorBlanco.setSelected(true);
        // Agregar los botones a la ventana
        getContentPane().add(rdbtnColorBlanco);
        getContentPane().add(rdbtnNewColorNegro);
        getContentPane().add(rdbtnNewRandom);
		JLabel lblColor = new JLabel("Color (Jugador principal):");
		lblColor.setForeground(Color.WHITE);
		lblColor.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblColor.setBounds(28, 237, 256, 21);
		contentPane.add(lblColor);
		  JButton btnEmpezar = new JButton("Empezar");
			btnEmpezar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean dg=true;
					if(rdbtnColorBlanco.isSelected()) {
						dg=true;
					}
					if(rdbtnNewColorNegro.isSelected()) {
						dg=false;
					}
					if(rdbtnNewRandom.isSelected()) {
						int randomNumber = (int) (Math.random() * 2) + 1;
						if(randomNumber==1) {
							dg=false;
						}else {
							dg=true;
						}
					}
					Main.nuevaPartida((String) comboBox.getSelectedItem(),chckbxBot.isSelected(),dg,chckbxAutoAprendizaje.isSelected());
					dispose();
				}
			});
			btnEmpezar.setForeground(Color.WHITE);
			btnEmpezar.setFont(new Font("Times New Roman", Font.BOLD, 18));
			btnEmpezar.setBackground(new Color(139, 69, 19));
			btnEmpezar.setBounds(427, 296, 170, 21);
			contentPane.add(btnEmpezar);
			
			
	}
}
