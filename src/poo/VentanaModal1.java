package poo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class VentanaModal1 extends JDialog {
    private String result1 = "";
    private JTextField textField_1;

    public VentanaModal1(JFrame parent) {
        super(parent, "Custom Input Dialog", true);
        getContentPane().setBackground(new Color(139, 0, 0));
        setBackground(new Color(139, 0, 0));
        setForeground(Color.WHITE);

        getContentPane().setLayout(new BorderLayout(10, 10)); // Espaciado entre componentes

        JPanel panel = new JPanel();
        panel.setBackground(new Color(139, 0, 0));
        panel.setForeground(Color.WHITE);

        JLabel lblNewLabel = new JLabel("Nombre de la partida:");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
        panel.add(lblNewLabel);

        getContentPane().add(panel, BorderLayout.NORTH); // Layout en la parte superior

        JPanel centerPanel = new JPanel(new BorderLayout()); // Panel en el centro para hacerlo más grande
        centerPanel.setBackground(new Color(139, 0, 0));

        JPanel textFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Para el TextField
        textFieldPanel.setBackground(new Color(139, 0, 0));

        textField_1 = new JTextField(42);
        textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        textFieldPanel.add(textField_1);

        centerPanel.add(textFieldPanel, BorderLayout.NORTH); // Coloca el TextField en la parte superior

        getContentPane().add(centerPanel, BorderLayout.CENTER); // Layout en el centro

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Para el botón
        buttonPanel.setBackground(new Color(139, 0, 0));

        JButton btnNewButton = new JButton("Guardar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                result1 = textField_1.getText();
                dispose();
            }
        });
        btnNewButton.setBackground(new Color(139, 69, 19));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        buttonPanel.add(btnNewButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH); // Layout en la parte inferior

        pack(); // Ajusta automáticamente el tamaño al contenido
        setPreferredSize(new Dimension(495, 200)); // Establece el tamaño deseado (horizontal, vertical)
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    public static String showCustomInputDialog(JFrame parent) {
    	VentanaModal1 dialog = new VentanaModal1(parent);
        dialog.setVisible(true);

        // Lógica para devolver el valor de entrada
        String input = dialog.result1;

        return input;
    }
}