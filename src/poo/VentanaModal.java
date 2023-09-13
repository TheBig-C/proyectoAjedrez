package poo;
import java.awt.Color;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VentanaModal extends JDialog {
    private String result="";
	private Object textField;
    public VentanaModal(JFrame parent,String d) {
        super(parent, "Custom Input Dialog", true);

        
        JPanel panel = new JPanel();
        JButton button3 = new JButton("");
        button3.setBackground(new Color(128, 0, 0));
        button3.setToolTipText("");
        button3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        button3.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Alfil.png"));
        
                button3.addActionListener(e -> {
                    result = "A" + d.substring(1);
                    dispose(); // Cerrar la ventana
                });
                
                        
                
                        JButton button1 = new JButton("");
                        button1.setBackground(new Color(128, 0, 0));
                        button1.setToolTipText("");
                        button1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                        button1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Reina.png"));
                        
                                // Definir la acción cuando se hace clic en los botones
                                button1.addActionListener(e -> {
                                    result = "D" + d.substring(1);
                                   dispose(); // Cerrar la ventana
                                });
                                panel.setLayout(new GridLayout(1, 4, 0, 0));
                                panel.add(button1);
                JButton button2 = new JButton("");
                button2.setBackground(new Color(128, 0, 0));
                button2.setToolTipText("");
                button2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                button2.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Torre.png"));
                
                        button2.addActionListener(e -> {
                            result = "T" + d.substring(1);
                           dispose(); // Cerrar la ventana
                        });
                        panel.add(button2);
                panel.add(button3);
        JButton button4 = new JButton("");
        button4.setBackground(new Color(128, 0, 0));
        button4.setToolTipText("");
        button4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        button4.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\ProyectoAjedrez\\Imagenes\\Caballo.png"));
        
                button4.addActionListener(e -> {
                    result = "C" + d.substring(1);
                    dispose(); // Cerrar la ventana
                });
                panel.add(button4);
       

        getContentPane().add(panel);

        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    public static String showCustomInputDialog(JFrame parent,String d) {
    	VentanaModal dialog = new VentanaModal(parent,d);
        dialog.setVisible(true);

        // Lógica para devolver el valor de entrada
        String input = dialog.result;

        return input;
    }

    // Uso del cuadro de diálogo personalizado
    
}