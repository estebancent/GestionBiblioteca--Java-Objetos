
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.applet.*;
/**
 * Ventana Carga para la Gestion de Carga de Datos de una Biblioteca.
 * 
 *  @author Centurion Esteban, Benitez Natalia, Benitez Gonzalo, Durand Emilio,Gonzales Francisco.

 */

public class VentanaCargaD extends JFrame implements ActionListener{
    private JPanel miPanel;
    private JButton miBotonEstudiante, miBotonDocente, miBotonLibro, miBotonPrestar, miBotonDevolver, miBotonAdministracion;
    private JLabel etiquetaTitulo;
    Biblioteca bibliotecas;
    private VentanaAdministracion ventanaAdm;
    /**
     * 
     */
    public VentanaCargaD(Biblioteca p_bibliotecas, VentanaAdministracion p_ventanaAdm){
        this.setBibliotecas(p_bibliotecas);  
        this.setVentanaAdm(p_ventanaAdm);
        
        this.setTitle("Carga de Datos en la Biblioteca");
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(200,200));
        this.setResizable(false);
        this.iniciarComponentesVentana();
        
    }
    //Setters
    private void setBibliotecas(Biblioteca p_biblioteca)
    {
        this.bibliotecas = p_biblioteca;
    }
    
    private void setVentanaAdm(VentanaAdministracion p_ventanaAdm)
    {
        this.ventanaAdm = p_ventanaAdm;
    }
    //Getters
    public Biblioteca getBibliotecas()
    {
        return this.bibliotecas;
    }
    
    public VentanaAdministracion getVentanaAdm()
    {
        return this.ventanaAdm;
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
     * Genera un Panel sobre el cual se trabajara con Etiquetas y Botones
     */
    private void crearPaneles(){
       miPanel = new JPanel();
       miPanel.setLayout(null);
       miPanel.setBackground(Color.black); 
       this.getContentPane().add(miPanel);
    }
    
    /**
     * Crea las Etiquetas que se usaran y las ubica en el Panel.
     */
    private void etiquetasDelPanel(){
        etiquetaTitulo = new JLabel("---Carga---");
        etiquetaTitulo.setForeground(Color. white);
        etiquetaTitulo.setFont( new Font( "Helvetica", Font.BOLD, 14 ));
        etiquetaTitulo.setBounds(215,30,400,20); 
        miPanel.add(etiquetaTitulo);
    }
    
    /**
     * Crea los Botones que se usaran y las ubica en el Panel.
     */
    private void botonesDelPanel(){
        miBotonEstudiante = new JButton("Estudiante");
        miBotonEstudiante.setBounds(190,60,120,30);
        miBotonEstudiante.setForeground(Color. blue);
        miPanel.add(miBotonEstudiante);
        miBotonEstudiante.addActionListener(this);
        
        miBotonDocente = new JButton("Docente");
        miBotonDocente.setBounds(190,100,120,30);
        miBotonDocente.setForeground(Color. BLUE);
        miPanel.add(miBotonDocente);
        miBotonDocente.addActionListener(this);
        
        miBotonLibro = new JButton("Libro");
        miBotonLibro.setBounds(190,140,120,30);
        miBotonLibro.setForeground(Color. BLUE);
        miPanel.add(miBotonLibro);
        miBotonLibro.addActionListener(this);
        
        miBotonPrestar = new JButton("Prestar Libro");
        miBotonPrestar.setBounds(190,180,120,30);
        miBotonPrestar.setForeground(Color. BLUE);
        miPanel.add(miBotonPrestar);
        miBotonPrestar.addActionListener(this);
        
        miBotonDevolver = new JButton("Devolver Libro");
        miBotonDevolver.setBounds(190,220,120,30);
        miBotonDevolver.setForeground(Color. BLUE);
        miPanel.add(miBotonDevolver);
        miBotonDevolver.addActionListener(this);
        
        
        miBotonAdministracion = new JButton("Volver al menu");
        miBotonAdministracion.setBounds(180,425,150,30);
        miBotonAdministracion.setForeground(Color. BLUE);
        miPanel.add(miBotonAdministracion);
        miBotonAdministracion.addActionListener(this);
    }
    
    /**
     * Gestiona la Escucha de los Botones del Panel y realiza la Accion correspondiente al boton presionado.
     */
    public void actionPerformed(ActionEvent event){
       if(event.getSource() == miBotonEstudiante){
           VentanaEstudiante ventanaEs = new VentanaEstudiante(this.getBibliotecas(), this);
           ventanaEs.setVisible(true);
           this.setVisible(false);
       }
        
       if(event.getSource() == miBotonDocente){
           VentanaDocente ventanaDo = new VentanaDocente(this.getBibliotecas(), this);
           ventanaDo.setVisible(true);
           this.setVisible(false);
       }
         
       if(event.getSource() == miBotonLibro){
           VentanaLibro ventanaLib = new VentanaLibro(this.getBibliotecas(), this);
           ventanaLib.setVisible(true);
           this.setVisible(false);
       }
       
       if(event.getSource() == miBotonAdministracion){
           this.getVentanaAdm().setVisible(true);
           this.setVisible(false);
       }
       
       if(event.getSource() == miBotonPrestar){
           if(this.getBibliotecas().getLibros().size() == 0)
           {
            JOptionPane.showMessageDialog(null, "No hay libros en la biblioteca");
            }else if(this.getBibliotecas().getSocios().size() == 0)
            {
                JOptionPane.showMessageDialog(null, "No hay socios en la biblioteca");
            }else{
               VentanaPrestar ventanaPre = new VentanaPrestar(this.getBibliotecas(), this);
               ventanaPre.setVisible(true);
               this.setVisible(false);
            }
       }
        
       if(event.getSource() == miBotonDevolver){
           boolean prestado = false;
           for(Libro e: this.getBibliotecas().getLibros())
           {
            if(e.prestado())
            {
                prestado = true;
            }
            }
            if(prestado)
            {
                VentanaDevolver ventanaDev = new VentanaDevolver(this.getBibliotecas(), this);
                ventanaDev.setVisible(true);
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "No hay libros prestados");
            }
       }
    }
}