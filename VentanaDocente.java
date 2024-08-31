
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;
/**
 * Ventana Docente para la Gestion de Socios del Tipo Docente.
 * 
 *  @author Centurion Esteban, Benitez Natalia, Benitez Gonzalo, Durand Emilio,Gonzales Francisco.
 *
 */

public class VentanaDocente extends JFrame implements ActionListener{
    private JPanel miPanel;
    private JButton miBotonCarga, miBotonAtras;
    private JTextField textoNombre, textoArea, textoDNI;
    private JLabel etiquetaNombre, etiquetaArea, etiquetaDNI, etiquetaTitulo;
    private Biblioteca bibliotecas;
    private VentanaCargaD ventanaCarg;
    /**
     * Constructor de Objetos de la Clase Ventana Docente
     */
    public VentanaDocente(Biblioteca p_bibliotecas, VentanaCargaD p_ventana){
        this.setBibliotecas(p_bibliotecas);
        this.setVentanaCarga(p_ventana);
        
        this.setTitle("Docentes");
        this.setSize(500, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setMaximumSize(new Dimension(200, 200));
        this.setResizable(false);
        
        this.iniciarComponenetesVentana();
    }
    //Setters
    private void setBibliotecas(Biblioteca p_bibliotecas){
      this.bibliotecas = p_bibliotecas;
    }
    
    private void setVentanaCarga(VentanaCargaD p_ventanaCarga){
      this.ventanaCarg = p_ventanaCarga;
    }
    //Getters
    public Biblioteca getBibliotecas(){
      return this.bibliotecas;
    }
    
    public VentanaCargaD getVentanaCarga(){
      return this.ventanaCarg;
    }
    
    /**
     * Inicializa los Componentes que se usaran en la Ventana.
     */
    private void iniciarComponenetesVentana(){
        this.crearPaneles();
        this.etiquetasDelPanel();
        this.areaDeTextoDelPanel();
        this.botonesDelPanel();
    }
    
    /**
     * Genera un Panel sobre el cual se trabajara con Etiquetas, Botones y Areas de Texto.
     */
    private void crearPaneles(){
        miPanel = new JPanel();
        miPanel.setLayout(null);
        miPanel.setBackground(Color.lightGray);
        this.getContentPane().add(miPanel);
    }
    
    /**
     * Crea las Etiquetas que se usaran y las ubica en el Panel.
     */
    private void etiquetasDelPanel(){
        etiquetaTitulo = new JLabel("---Docente---");
        etiquetaTitulo.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaTitulo.setBounds(200, 50, 200, 100);
        miPanel.add(etiquetaTitulo);
        
        etiquetaNombre = new JLabel("Nombre: ");
        etiquetaNombre.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaNombre.setBounds(110, 100, 200, 100);
        miPanel.add(etiquetaNombre);
        
        etiquetaArea = new JLabel("Area: ");
        etiquetaArea.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaArea.setBounds(110, 130, 200, 100);
        miPanel.add(etiquetaArea);
        
        etiquetaDNI = new JLabel("DNI: ");
        etiquetaDNI.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaDNI.setBounds(110, 160, 200, 100);
        miPanel.add(etiquetaDNI);
    }
    
    /**
     * Crea las Areas de Texto que se usaran y las ubica en el Panel
     */
    private void areaDeTextoDelPanel(){
        textoNombre = new JTextField(15);
        textoNombre.setBounds(175, 142, 250, 20);
        miPanel.add(textoNombre);
        
        textoArea = new JTextField(15);
        textoArea.setBounds(175, 172, 250, 20);
        miPanel.add(textoArea);
        
        textoDNI = new JTextField(15);
        textoDNI.setBounds(175, 202, 250, 20);
        miPanel.add(textoDNI);
        this.areaDeTextosSoloNumeros(textoDNI);
    }
    
    /**
     * Asegura que solo se ingresen numeros en el Area de Texto
     */
    private void areaDeTextosSoloNumeros(JTextField at){
        at.addKeyListener(new KeyAdapter(){
            public void keyTyped (KeyEvent evt){
              char texto = evt.getKeyChar();
              
              if(!Character.isDigit(texto)){
                  evt.consume();
                }
            
            }
        });
    }
    
    /**
     * Crea los Botones que se usaran y las ubica en el Panel.
     */
    private void botonesDelPanel(){
        miBotonCarga = new JButton("Cargar");
        miBotonCarga.setBounds(200, 230, 100, 25);
        miBotonCarga.setForeground(Color.BLUE);
        miPanel.add(miBotonCarga);
        miBotonCarga.addActionListener(this);
        
        miBotonAtras = new JButton("Atras");
        miBotonAtras.setBounds(5, 5, 80, 20);
        miBotonAtras.setForeground(Color.BLUE);
        miPanel.add(miBotonAtras);
        miBotonAtras.addActionListener(this);
    }
    /**
     * Gestiona la Escucha de los Botones del Panel y realiza la Accion correspondiente al boton presionado.
     */
    public void actionPerformed(ActionEvent event){
       
       if(event.getSource() == miBotonCarga){
           String d_nombre = textoNombre.getText();
           String d_area = textoArea.getText();
           String d_dni = textoDNI.getText();
           
           if(textoNombre.getText().isEmpty() || textoArea.getText().isEmpty() || textoDNI.getText().isEmpty()){
               JOptionPane.showMessageDialog(null, "Debe de Completar todos los Campos!!");
           }else if(this.getBibliotecas().getSocios().containsKey(Integer.parseInt(d_dni))){
               JOptionPane.showMessageDialog(null, "Ya hay un socio con este DNI");
            }else{
               this.getBibliotecas().nuevoSocioDocente(Integer.parseInt(d_dni), d_nombre, d_area);
               JOptionPane.showMessageDialog(null, "Datos Cargados");
               this.limpiarAreaDeTextoDelPanel();
           }
       }
        
       if(event.getSource() == miBotonAtras){
           ventanaCarg.setVisible(true);
           this.setVisible(false);
       }
    }
    
    /**
     * Limpia el Area de Texto
     */
    public void limpiarAreaDeTextoDelPanel(){
        textoNombre.setText(null);
        textoArea.setText(null);
        textoDNI.setText(null);
    }
}
