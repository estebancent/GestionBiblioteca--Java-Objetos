
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.ImageIcon;

/**
 * Ventana Administrativa para la seleccion de Carga o Visualizacion de Datos.
 * 
 *  @author Centurion Esteban, Benitez Natalia, Benitez Gonzalo, Durand Emilio,Gonzales Francisco.
 * 
 */

public class VentanaAdministracion extends JFrame implements ActionListener{
    private JPanel miPanel;
  
    private JButton miBotonCarga, miBotonConsulta;
  
    private JLabel etiquetaBienvenida, etiquetaNombre;
    
    private Biblioteca biblioteca;
    /**
     * Contructor de Objetos de la Clase Ventana Administracion
     */
    public VentanaAdministracion(){
        this.setTitle("Gestion Biblioteca");
        this.setSize(500,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(200,200));
        this.setResizable(false);
        this.setBiblioteca(new Biblioteca("F.A.C.E.N.A"));
        
        this.iniciarComponentesVentana();
  
    }
    
    private void setBiblioteca(Biblioteca p_biblioteca)
    {
        this.biblioteca = p_biblioteca;
    }
    
    public Biblioteca getBiblioteca()
    {
        return this.biblioteca;
    }
    
    /**
     * Inicializa los Componentes que se usaran en la Ventana.
     */
    private void iniciarComponentesVentana(){
        this.crearPaneles();
        this.etiquetasDelPanel();
        this.botonesDelPanel();
    }
    /**
     * Genera un Panel sobre el cual se trabajara con Etiquetas y Botones.
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
        etiquetaBienvenida = new JLabel("Gestion Biblioteca");
        etiquetaBienvenida.setForeground(Color. white);
        etiquetaBienvenida.setFont( new Font( "Helvetica", Font.BOLD, 24 ));
        etiquetaBienvenida.setBounds(140,50,400,25); 
        miPanel.add(etiquetaBienvenida);
        
        etiquetaNombre = new JLabel(this.getBiblioteca().getNombre());
        etiquetaNombre.setFont( new Font( "Arial", Font.BOLD, 24 ));
        etiquetaNombre.setForeground(Color. white);
        etiquetaNombre.setBounds(180,100,400,25); 
        miPanel.add(etiquetaNombre);
        
        JLabel etiquetaunne = new JLabel(new ImageIcon("facena.jpg"));
        etiquetaunne.setBounds(140,400,206,94);
        miPanel.add(etiquetaunne);
    }
    /**
     * Crea los Botones que se usaran y las ubica en el Panel.
     */
    private void botonesDelPanel(){
        miBotonCarga = new JButton("Carga de Datos");
        miBotonCarga.setBounds(170,220,150,30);
        miBotonCarga.setForeground(Color.darkGray);
        miPanel.add(miBotonCarga);
        miBotonCarga.addActionListener(this);
        
        miBotonConsulta = new JButton("Consulta de Datos");
        miBotonConsulta.setBounds(170,260,150,30);
        miBotonConsulta.setForeground(Color.darkGray);
        miPanel.add(miBotonConsulta);
        miBotonConsulta.addActionListener(this);
    }
    
    /**
     * Gestiona la Escucha de los Botones del Panel y realiza la Accion correspondiente al boton presionado.
     */
    public void actionPerformed(ActionEvent event){
       if(event.getSource() == miBotonCarga){
          VentanaCargaD ventanaCarga = new VentanaCargaD(this.getBiblioteca(),this);
             
          ventanaCarga.setVisible(true);
          this.setVisible(false);
        }
        
       if(event.getSource() == miBotonConsulta){
          VentanaConsultaD ventanaConsulta = new VentanaConsultaD(this.getBiblioteca(),this);
             
          ventanaConsulta.setVisible(true);
          this.setVisible(false);
       }
    }
}