
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;
/**
 * Ventana Prestar para la Gestion de Prestamos de Libros de una Biblioteca.
 * 
 *  @author Centurion Esteban, Benitez Natalia, Benitez Gonzalo, Durand Emilio,Gonzales Francisco.
 * 
 */

public class VentanaPrestar extends JFrame implements ActionListener{
    private JPanel miPanel;
    private JButton miBotonCarga, miBotonAtras;
    private JTextField textoDNI, textoNumLib;
    private JLabel etiquetaTitulo, etiquetaDNI, etiquetaNumLib;
    private Biblioteca bibliotecas;
    private VentanaCargaD ventanaCarg;
    private JTextArea areaTextoSocio, areaTextoLibro;
    /**
     * Constructor de Objetos de la Clase Ventana Prestar
     */
    public VentanaPrestar(Biblioteca p_bibliotecas, VentanaCargaD p_ventanaCarga){
        this.setBibliotecas(p_bibliotecas);
        this.setVentanaCarga(p_ventanaCarga);
        
        
        this.setTitle("Prestar Libro");
        this.setSize(800, 700);
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
        this.areaDeTextSocio();
        this.areaDeTextLibro();
    }
    
    /**
     * Crea un Area de Texto donde se mostrara la Lista de Socios de la Biblioteca
     */
    public void areaDeTextSocio(){
      areaTextoSocio = new JTextArea();
    
      areaTextoSocio.setLineWrap(false);
    
      JScrollPane scrollPanel = new JScrollPane(areaTextoSocio,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
      scrollPanel.setBounds(30,30,350,300);
    
      areaTextoSocio.append(this.getBibliotecas().listaDeSocios());
      miPanel.add(scrollPanel);
    }
    
    /**
     * Crea un Area de Texto donde se mostrara la Lista de Libros de la Biblioteca
     */
    public void areaDeTextLibro(){
      areaTextoLibro = new JTextArea();
    
      areaTextoLibro.setLineWrap(false);
    
      JScrollPane scrollPanel = new JScrollPane(areaTextoLibro,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
      scrollPanel.setBounds(400,30,350,300);
    
      areaTextoLibro.append(this.getBibliotecas().listaDeLibros());
      miPanel.add(scrollPanel);
    }
   
    /**
     * Genera un Panel sobre el cual se trabajara con Etiquetas, Botones y Areas de Texto.
     */
    private void crearPaneles(){
        miPanel = new JPanel();
        miPanel.setLayout(null);
        miPanel.setBackground(Color.darkGray);
        this.getContentPane().add(miPanel);
    }
    
    /**
     * Crea las Etiquetas que se usaran y las ubica en el Panel.
     */
    private void etiquetasDelPanel(){
        etiquetaTitulo = new JLabel("---Prestar Libro---");
        etiquetaTitulo.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaTitulo.setForeground(Color. white);
        etiquetaTitulo.setBounds(200, 310, 200, 100);
        miPanel.add(etiquetaTitulo);
        
        etiquetaDNI = new JLabel("DNI del Socio: ");
        etiquetaDNI.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaDNI.setForeground(Color. white);
        etiquetaDNI.setBounds(30, 340, 200, 100);
        miPanel.add(etiquetaDNI);
        
        etiquetaNumLib = new JLabel("Numero del Libro: ");
        etiquetaNumLib.setFont(new Font("Helvetica", Font.BOLD, 15));
        etiquetaNumLib.setForeground(Color. white);
        etiquetaNumLib.setBounds(30, 370, 200, 100);
        miPanel.add(etiquetaNumLib);
    }
    
    /**
     * Crea las Areas de Texto que se usaran y las ubica en el Panel
     */
    private void areaDeTextoDelPanel(){
        textoDNI = new JTextField(15);
        textoDNI.setBounds(180, 380, 250, 20);
        miPanel.add(textoDNI);
        this.areaDeTextosSoloNumeros(textoDNI);
        
        textoNumLib = new JTextField(15);
        textoNumLib.setBounds(180, 410, 250, 20);
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
        miBotonCarga.setBounds(200,440, 100, 25);
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
           String dniSocio = textoDNI.getText();
           String d_numLibro = textoNumLib.getText();
           
           if(textoDNI.getText().isEmpty() || textoNumLib.getText().isEmpty()){
               JOptionPane.showMessageDialog(null, "Debe de Completar todos los Campos");
           }else if(!this.getBibliotecas().getSocios().containsKey(Integer.parseInt(dniSocio))){
               JOptionPane.showMessageDialog(null, "Numero de DNI inválido");
            }else if(Integer.parseInt(d_numLibro) > this.getBibliotecas().getLibros().size() || Integer.parseInt(d_numLibro) == 0){
                JOptionPane.showMessageDialog(null, "Numero de libro inválido");
            }else if(!this.getBibliotecas().buscarSocio(Integer.parseInt(dniSocio)).puedePedir()){
                JOptionPane.showMessageDialog(null, "Este socio no puede pedir mas libros");
            }else if(this.getBibliotecas().getLibros().get(Integer.parseInt(d_numLibro)-1).prestado()){
                JOptionPane.showMessageDialog(null, "Este libro ya esta prestado");
            }else{
               this.getBibliotecas().prestarLibro(new GregorianCalendar(), this.getBibliotecas().buscarSocio(Integer.parseInt(dniSocio)), 
               this.getBibliotecas().getLibros().get(Integer.parseInt(d_numLibro)-1));
               JOptionPane.showMessageDialog(null, "Libro prestado con éxito");
               areaTextoSocio.setText(null); 
               areaTextoLibro.setText(null); 
               areaTextoSocio.append(this.getBibliotecas().listaDeSocios());
               areaTextoLibro.append(this.getBibliotecas().listaDeLibros());
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
        textoDNI.setText(null);
        textoNumLib.setText(null);
    }
}
