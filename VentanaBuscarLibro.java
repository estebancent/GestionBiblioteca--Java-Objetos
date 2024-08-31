
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;
/**
 * Ventana Buscar Libro para la Gestion de la  Busqueda de Libros de una Biblioteca.
 * 
 *  @author Centurion Esteban, Benitez Natalia, Benitez Gonzalo, Durand Emilio,Gonzales Francisco..

 */

public class VentanaBuscarLibro extends JFrame implements ActionListener{
    private JPanel miPanel;
    private JButton miBotonCarga, miBotonAtras;
    private JTextField textoNumLib;
    private JLabel etiquetaTitulo, etiquetaNumLib;
    private JTextArea areaTextoPLibro;
    private Biblioteca bibliotecas;
    private VentanaConsultaD ventanaConD;
    /**
     * Constructor de Objetos de la Clase Ventana Buscar Libro
     */
    public VentanaBuscarLibro(Biblioteca p_bibliotecas, VentanaConsultaD p_ventanaCondD){
        this.setBibliotecas(p_bibliotecas);
        this.setVentanaConsultaD(p_ventanaCondD);
        
        this.setTitle("Buscar Libro");
        this.setSize(500, 500);
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
    
    private void setVentanaConsultaD(VentanaConsultaD p_ventanaConD){
      this.ventanaConD = p_ventanaConD;
    }
    //Getters
    public Biblioteca getBibliotecas(){
      return this.bibliotecas;
    }
    
    public VentanaConsultaD getVentanaConD(){
      return this.ventanaConD;
    }
    
    /**
     * Inicializa los Componentes que se usaran en la Ventana.
     */
    private void iniciarComponenetesVentana(){
        this.crearPaneles();
        this.etiquetasDelPanel();
        this.areaDeTextoDelPanel();
        this.botonesDelPanel();
        this.areaDeTextPLibro();
    }
    
    /**
     * Crea un Area de Texto donde se mostraran los libros que se busquen.
     */
    public void areaDeTextPLibro(){
      areaTextoPLibro = new JTextArea();
    
      areaTextoPLibro.setLineWrap(false);
    
      JScrollPane scrollPanel = new JScrollPane(areaTextoPLibro,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
      scrollPanel.setBounds(20,30,440,300);
      areaTextoPLibro.append(this.getBibliotecas().listaDeLibros());
      miPanel.add(scrollPanel);
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
        etiquetaTitulo = new JLabel("---Ingrese numero del libro---");
        etiquetaTitulo.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaTitulo.setBounds(200, 310, 200, 100);
        miPanel.add(etiquetaTitulo);
        
        etiquetaNumLib = new JLabel("Numero del Libro: ");
        etiquetaNumLib.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaNumLib.setBounds(30, 340, 200, 100);
        miPanel.add(etiquetaNumLib);
    }
    
    /**
     * Crea las Areas de Texto que se usaran y las ubica en el Panel
     */
    private void areaDeTextoDelPanel(){
        textoNumLib = new JTextField(15);
        textoNumLib.setBounds(180, 380, 200, 20);
        miPanel.add(textoNumLib);
        this.areaDeTextosSoloNumeros(textoNumLib);
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
        miBotonCarga.setBounds(200, 410, 100, 25);
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
           String d_numLibro = textoNumLib.getText();
           
           if(textoNumLib.getText().isEmpty()){
               JOptionPane.showMessageDialog(null, "Debe ingresar el número del libro que desea buscar");
           }else if(Integer.parseInt(d_numLibro) > this.getBibliotecas().getLibros().size() || Integer.parseInt(d_numLibro) == 0){
               JOptionPane.showMessageDialog(null, "Numero inválido");
               this.limpiarAreaDeTextoDelPanel();
            }else{
               JOptionPane.showMessageDialog(null, "Poseedor del libro: " + this.bibliotecas.quienTieneElLibro(this.bibliotecas.getLibros().get(Integer.parseInt(d_numLibro)-1)));
               this.limpiarAreaDeTextoDelPanel();
           }
       }
        
       if(event.getSource() == miBotonAtras){
           ventanaConD.setVisible(true);
           this.setVisible(false);
       }
    }
    
    /**
     * Limpia el Area de Texto
     */
    public void limpiarAreaDeTextoDelPanel(){
        textoNumLib.setText(null);
    }
}