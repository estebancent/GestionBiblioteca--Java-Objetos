
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;
/**
 * Ventana Devolver para la Gestion de Devolucion de los Libros.
 * 
 *  @author Centurion Esteban, Benitez Natalia, Benitez Gonzalo, Durand Emilio,Gonzales Francisco.
 * 
 */

public class VentanaDevolver extends JFrame implements ActionListener{
    private JPanel miPanel;
    private JButton miBotonCarga, miBotonAtras;
    private JTextField textoNumLib;
    private JLabel etiquetaTitulo, etiquetaNumLib;
    private JTextArea areaTextoPLibro;
    private Biblioteca bibliotecas;
    private VentanaCargaD ventanaCarg;
    
 
    
    /**
     * Constructor de Objetos de la Clase Ventana Devolver
     */
    public VentanaDevolver(Biblioteca p_bibliotecas, VentanaCargaD p_ventanaCarga){
        this.setBibliotecas(p_bibliotecas);
        this.setVentanaCarga(p_ventanaCarga);
        
        this.setTitle("Devolver Libro");
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
        this.areaDeTextPLibro();
    }
    
    /**
     * Crea un Area de Texto donde se mostrara el listado de libros prestados.
     */
    public void areaDeTextPLibro(){
      areaTextoPLibro = new JTextArea();
    
      areaTextoPLibro.setLineWrap(false);
    
      JScrollPane scrollPanel = new JScrollPane(areaTextoPLibro,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
      scrollPanel.setBounds(20,30,440,300);
      
      
      miPanel.add(scrollPanel);
      areaTextoPLibro.append(this.getBibliotecas().listaDeLibros());
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
        etiquetaTitulo = new JLabel("---Devolver Libro---");
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
               JOptionPane.showMessageDialog(null, "Debe ingresar el número del libro que desea devolver");
           }else if(Integer.parseInt(d_numLibro) > this.getBibliotecas().getLibros().size() || Integer.parseInt(d_numLibro) == 0){
               JOptionPane.showMessageDialog(null, "Numero inválido");
               this.limpiarAreaDeTextoDelPanel();
            }else if(!this.getBibliotecas().getLibros().get(Integer.parseInt(d_numLibro)-1).prestado()){
                JOptionPane.showMessageDialog(null, "El libro no se encuentra prestado");
               this.limpiarAreaDeTextoDelPanel();
            }else{
               this.getBibliotecas().devolverLibro(this.getBibliotecas().getLibros().get(Integer.parseInt(d_numLibro)-1));
               
               JOptionPane.showMessageDialog(null, "Libro devuelto con exito");
               areaTextoPLibro.setText(null); 
               areaTextoPLibro.append(this.getBibliotecas().listaDeLibros());
               
               this.limpiarAreaDeTextoDelPanel();
           }
       }
        
       if(event.getSource() == miBotonAtras){
           this.getVentanaCarga().setVisible(true);
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