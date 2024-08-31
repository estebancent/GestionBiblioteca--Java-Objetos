
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;
/**
 * Ventana Libro para la Gestion de los Libros de la Biblioteca.
 * 
 * @author Centurion Esteban, Benitez Natalia, Benitez Gonzalo, Durand Emilio,Gonzales Francisco.
 * 
 */
public class VentanaLibro extends JFrame implements ActionListener{
    private JPanel miPanel;
    private JButton miBotonCarga, miBotonAtras;
    private JTextField textoTituloLib, textoEditorial, textoEdicion, textoAnio;
    private JLabel etiquetaTituloLib, etiquetaEditorial, etiquetaEdicion, etiquetaTitulo, etiquetaAnio;
    private Biblioteca bibliotecas;
    private VentanaCargaD ventanaCarg;
    /**
     * Constructor de Objetos de la Clase Ventana Libro
     */
    public VentanaLibro(Biblioteca p_bibliotecas, VentanaCargaD p_ventana){
        this.setBibliotecas(p_bibliotecas);
        this.setVentanaCarga(p_ventana);
        
        this.setTitle("Libro");
        this.setSize(430, 400);
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
        etiquetaTitulo = new JLabel("---Libro---");
        etiquetaTitulo.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaTitulo.setBounds(200, 50, 200, 100);
        miPanel.add(etiquetaTitulo);
        
        etiquetaTituloLib = new JLabel("Titulo: ");
        etiquetaTituloLib.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaTituloLib.setBounds(30, 100, 75, 100);
        miPanel.add(etiquetaTituloLib);
        
        etiquetaEditorial = new JLabel("Editorial: ");
        etiquetaEditorial.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaEditorial.setBounds(30, 130, 75, 100);
        miPanel.add(etiquetaEditorial);
        
        etiquetaEdicion = new JLabel("Edicion: ");
        etiquetaEdicion.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaEdicion.setBounds(30, 160, 75, 100);
        miPanel.add(etiquetaEdicion);
        
        etiquetaAnio = new JLabel("Año: ");
        etiquetaAnio.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaAnio.setBounds(30, 190, 50, 100);
        miPanel.add(etiquetaAnio);
    }
    
    /**
     * Crea las Areas de Texto que se usaran y las ubica en el Panel
     */
    private void areaDeTextoDelPanel(){
        textoTituloLib = new JTextField(15);
        textoTituloLib.setBounds(105, 142, 250, 20);
        miPanel.add(textoTituloLib);
        
        textoEditorial = new JTextField(15);
        textoEditorial.setBounds(105, 172, 250, 20);
        miPanel.add(textoEditorial);
        
        textoEdicion = new JTextField(15);
        textoEdicion.setBounds(105, 202, 250, 20);
        miPanel.add(textoEdicion);
        this.areaDeTextosSoloNumeros(textoEdicion);
        
        textoAnio = new JTextField(15);
        textoAnio.setBounds(105, 232, 250, 20);
        miPanel.add(textoAnio);
        this.areaDeTextosSoloNumeros(textoAnio);
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
        miBotonCarga.setBounds(200, 262 , 100, 25);
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
           String d_titulo = textoTituloLib.getText();
           String d_editorial = textoEditorial.getText();
           String d_edicion = textoEdicion.getText();
           String d_anio = textoAnio.getText();
           
           if(textoTituloLib.getText().isEmpty() || textoEditorial.getText().isEmpty() || textoEdicion.getText().isEmpty() || textoAnio.getText().isEmpty()){
               JOptionPane.showMessageDialog(null, "Debe de Completar todos los Campos!!");
           }else{
               this.getBibliotecas().nuevoLibro(d_titulo, Integer.parseInt(d_edicion), d_editorial, Integer.parseInt(d_anio));
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
        textoTituloLib.setText(null);
        textoEditorial.setText(null);
        textoEdicion.setText(null);
        textoAnio.setText(null);
    }
}
