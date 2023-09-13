package poo;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBuscar;
	private static JTable table_1 = new JTable();
	private static ArrayList<Partidas> partidas =new ArrayList<>();
	private static ArrayList<Partidas> partidasRes =new ArrayList<>();
	/**
	 * Launch the application.
	 e frame.
	 */
	public Buscar() {
		setBackground(new Color(139, 0, 0));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1180, 672);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		textFieldBuscar = new JTextField();
		textFieldBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldBuscar.setBounds(106, 14, 156, 19);
		contentPane.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		
		try {
			partidas=Extras.cargarPartidas();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JLabel lblCod = new JLabel("Nombre:");
		lblCod.setForeground(new Color(255, 255, 255));
		lblCod.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblCod.setBounds(28, 15, 68, 13);
		contentPane.add(lblCod);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDatos = new JLabel("Partidas Guardadas");
		lblDatos.setForeground(new Color(255, 255, 255));
		lblDatos.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDatos.setBounds(28, 55, 176, 13);
		contentPane.add(lblDatos);
		/*ArrayList<String> p=null;
			p=Main.mostrarPartidas();
		for(String i: p) {
			txtEmpleados.append(i+"\n");
		}*/
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setBackground(new Color(139, 0, 0));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnVolver.setBounds(28, 586, 85, 21);
		contentPane.add(btnVolver);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(139, 0, 0));
		btnBuscar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!textFieldBuscar.getText().equals("")) {
					String cod=textFieldBuscar.getText();
					partidasRes=Extras.buscar(cod,partidas); 
				
					tablaResultado_1(partidasRes);
				}else {
					JOptionPane.showMessageDialog(null, "Primero llene los campos de texto solicitados para buscar");
				}
				
			}
		});
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnBuscar.setBounds(305, 15, 85, 21);
		contentPane.add(btnBuscar);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 97, 1093, 458);
		contentPane.add(scrollPane);
		table_1.setBackground(new Color(139, 69, 19));
		table_1.setForeground(Color.WHITE);

		
		table_1.setFillsViewportHeight(true);
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setViewportView(table_1);
		
		JLabel lblTitulo = new JLabel("PractiChess");
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 66));
		lblTitulo.setBounds(774, -21, 350, 134);
		contentPane.add(lblTitulo);
		tabla_1();
		
	}
	public static void tabla_1() {
		int cdd = partidas.size();
		String[][] data = new String[cdd][9];
		int c = 0;
		for (Partidas i : partidas) {
			
				data[c][0] = i.getCod()+"";
				data[c][1] = i.getNombre() + "";
				data[c][2] = i.getHora()+ "";
				data[c][3] = i.getFecha() + "";
				if(i.getTimeB()==0 && i.getTimeN()==0) {
					data[c][4] = "Ilimitado";
					data[c][5] = "Ilimitado";
				}else {
					data[c][4] = formatTime(i.getTimeB())+ "";
					data[c][5] = formatTime(i.getTimeN())+ "";
				}
				data[c][6] = i.isAuto()+ "";
				data[c][7] = i.isBot()+ "";
				if(i.isColorF()) {
					data[c][8] = "Fichas blancas";
				}else {
					data[c][8] = "Fichas negras";
				}
				
				c++;
			
		}

		// Nombres de columna
		String[] columnNames = { "Codigo partida", "Nombre Partida", "Hora", "Fecha", "Tiempo fichas nlancas","Tiempo fichas negras","Autoaprendizaje","Bot","Color" };
		// Crear el modelo de la tabla con los datos y nombres de columna
		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		table_1.setModel(model);
		//TableColumn column = table_1.getColumnModel().getColumn(0);
        //column.setPreferredWidth(500);
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table_1.getSelectedRow();
					if (selectedRow != -1) {
						
						int cod = Integer.parseInt((String) table_1.getValueAt(selectedRow, 0));
						
						JPopupMenu popupMenu = createPopupMenu(cod);
						popupMenu.show(table_1, 0, table_1.getRowHeight() * selectedRow);
					}
				}
			}
			}); 

	}

	public static void tablaResultado_1(ArrayList<Partidas> res) {
		int cdd = res.size();
		String[][] data = new String[cdd][9];
		int c = 0;
		for (Partidas i : res) {
			
				data[c][0] = i.getCod()+"";
				data[c][1] = i.getNombre() + "";
				data[c][2] = i.getHora()+ "";
				data[c][3] = i.getFecha() + "";
				if(i.getTimeB()==0 && i.getTimeN()==0) {
					data[c][4] = "Ilimitado";
					data[c][5] = "Ilimitado";
				}else {
					data[c][4] = formatTime(i.getTimeB())+ "";
					data[c][5] = formatTime(i.getTimeN())+ "";
				}
				data[c][6] = i.isAuto()+ "";
				data[c][7] = i.isBot()+ "";
				if(i.isAuto()) {
					data[c][8] = "Fichas blancas";
				}else {
					data[c][8] = "Fichas negras";
				}
				
				c++;
			
		}

		// Nombres de columna
		String[] columnNames = { "Codigo partida", "Nombre Partida", "Hora", "Fecha", "Tiempo fichas nlancas","Tiempo fichas negras","Autoaprendizaje","Bot","Color" };
		// Crear el modelo de la tabla con los datos y nombres de columna
		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		table_1.setModel(model);
		//TableColumn column = table_1.getColumnModel().getColumn(0);
        //column.setPreferredWidth(500);
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table_1.getSelectedRow();
					if (selectedRow != -1) {
						
						int cod = Integer.parseInt((String) table_1.getValueAt(selectedRow, 0));
						
						JPopupMenu popupMenu = createPopupMenu(cod);
						popupMenu.show(table_1, 0, table_1.getRowHeight() * selectedRow);
					}
				}
			}
			}); 

    }


	private static JPopupMenu createPopupMenu(int cod) {
        JPopupMenu popupMenu = new JPopupMenu();
        
        // Crear botones con las acciones deseadas
        JButton boton1 = new JButton("Continuar");
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Main.CargarPartida(cod); 
            }
        });

        JButton boton2 = new JButton("Eliminar");
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
				Extras.eliminar(cod);
				for(int i=0;i<partidas.size();i++) {
					if(partidas.get(i).getCod()==cod) {
						partidas.remove(i);
					}
				}
				tabla_1();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
 				
            }
            
        });

        

        

        // Agregar los botones al menú flotante
        popupMenu.add(boton1);
        popupMenu.add(boton2);
       
        //popupMenu.add(boton4);

        return popupMenu;
    }
	private static String formatTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
