
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.*;
/**
 * Ventana Consulta para la Gestion de Consulta de Datos de una Biblioteca.
 * 
 *  @author Centurion Esteban, Benitez Natalia, Benitez Gonzalo, Durand Emilio,Gonzales Francisco.
 *
 */

public class VentanaConsultaD extends JFrame implements ActionListener{
    JTextArea areaTexto;
    JButton btnCEstudiante, btnCDocente, btnVAdm, btnBuscarLibro, btnPVencidos, btnDResponsables, btnLsocios, btnLLibros, btnBuscarSocio;
    JPanel miPanel;
    JTextField buscarSocio,buscarLibro;
    JLabel etiqueta1;
  
    private Biblioteca biblioteca;
    private VentanaAdministracion ventanaAdm;
  
    public VentanaConsultaD(Biblioteca p_biblioteca, VentanaAdministracion p_ventanaAdm){
        this.setBiblioteca(p_biblioteca);
        this.setVentanaAdm(p_ventanaAdm);
        
        this.setTitle("Consultas de Datos en la Biblioteca");
        this.setSize(500,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.iniciarComponentesVentana();
    }
    //Setters
    private void setBiblioteca(Biblioteca p_biblioteca)
    {
        this.biblioteca = p_biblioteca;
    }
    
    private void setVentanaAdm(VentanaAdministracion p_ventanaAdm)
    {
        this.ventanaAdm = p_ventanaAdm;
    }
    //Getters
    public Biblioteca getBiblioteca()
    {
        return this.biblioteca;
    }
    
    public VentanaAdministracion getVentanaAdm()
    {
        return this.ventanaAdm;
    }
    
    /**
     * Inicializa los Componentes que se usaran en la Ventana.
     */
    public void iniciarComponentesVentana(){
        this.crearPanel();
        this.areaDeText();
        this.botonesDelPanel();
        this.areaDeTextosDelPanel();
        this.etiquetasDelPanel();
    }
    
    /**
     * Genera un Panel sobre el cual se trabajara con Etiquetas, Botones y Areas de Texto.
     */
    public void crearPanel(){
        miPanel = new JPanel();
        miPanel.setLayout(null);
        miPanel.setBackground(Color.darkGray);
        this.getContentPane().add(miPanel);
    }
    
    /**
     * Crea las Areas de Texto que se usaran y las ubica en el Panel
     */
    public void areaDeText(){
        areaTexto = new JTextArea();
        areaTexto.setLineWrap(false);
    
        JScrollPane scrollPanel = new JScrollPane(areaTexto,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
        scrollPanel.setBounds(20,30,440,300);
    
        miPanel.add(scrollPanel);
    }
    
    /**
     * Crea los Botones que se usaran y las ubica en el Panel.
     */
    private void botonesDelPanel(){
        btnCEstudiante = new JButton("Cant. Estudiantes");
        btnCEstudiante.setBounds(60,350,175,25);
        btnCEstudiante.setForeground(Color. BLUE);
        miPanel.add(btnCEstudiante);
        btnCEstudiante.addActionListener(this);
        
        btnCDocente = new JButton("Cant. Docentes");
        btnCDocente.setBounds(265,350,175,25);
        btnCDocente.setForeground(Color. BLUE);
        miPanel.add(btnCDocente);
        btnCDocente.addActionListener(this);
        
        btnPVencidos = new JButton("Prestamos Vencidos");
        btnPVencidos.setBounds(60,385,175,25);
        btnPVencidos.setForeground(Color. BLUE);
        miPanel.add(btnPVencidos);
        btnPVencidos.addActionListener(this);
        
        btnDResponsables = new JButton("Docentes Responsables");
        btnDResponsables.setBounds(265,385,175,25);
        btnDResponsables.setForeground(Color. BLUE);
        miPanel.add( btnDResponsables);
        btnDResponsables.addActionListener(this);
        
        btnLsocios = new JButton("Listar Socios");
        btnLsocios.setBounds(60,420,175,25);
        btnLsocios.setForeground(Color. BLUE);
        miPanel.add(btnLsocios);
        btnLsocios.addActionListener(this);
        
        btnLLibros = new JButton("Listar Libros");
        btnLLibros.setBounds(265,420,175,25);
        btnLLibros.setForeground(Color. BLUE);
        miPanel.add(btnLLibros);
        btnLLibros.addActionListener(this);
        
        btnVAdm = new JButton("Volver al menu");
        btnVAdm.setBounds(150,0,200,25);
        btnVAdm.setForeground(Color. BLUE);
        miPanel.add(btnVAdm);
        btnVAdm.addActionListener(this);
        
        btnBuscarLibro = new JButton("BuscarLibro");
        btnBuscarLibro.setBounds(150, 455,200,25);
        btnBuscarLibro.setForeground(Color. BLUE);
        miPanel.add(btnBuscarLibro);
        btnBuscarLibro.addActionListener(this);
        
        btnBuscarSocio = new JButton("BuscarSocio");
        btnBuscarSocio.setBounds(255,515,150,25);
        btnBuscarSocio.setForeground(Color. BLUE);
        miPanel.add(btnBuscarSocio);
        btnBuscarSocio.addActionListener(this);
    }
    
    /**
     * Crea las Areas de Texto que se usaran y las ubica en el Panel
     */
    private void areaDeTextosDelPanel(){
        buscarSocio = new JTextField(15);
        buscarSocio.setBounds(95,515,150,25);
        miPanel.add(buscarSocio);
        this.areaDeTextosSoloNumeros(buscarSocio);
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
     * Crea las Etiquetas que se usaran y las ubica en el Panel.
     */
    private void etiquetasDelPanel(){
        etiqueta1 = new JLabel("Ingrese DNI");
        etiqueta1.setForeground(Color. white);
        etiqueta1.setFont( new Font( "Helvetica", Font.BOLD, 14 ));
        etiqueta1.setBounds(130,495,150,25); 
        miPanel.add(etiqueta1);
    }
    
    /**
     * Gestiona la Escucha de los Botones del Panel y realiza la Accion correspondiente al boton presionado.
     */
    public void actionPerformed(ActionEvent event) {
     
      if(event.getSource() == btnCEstudiante){
          areaTexto.setText(null);
          areaTexto.append("********************************\nCantidad de socios Estudiantes: " + this.getBiblioteca().cantidadSociosPorTipo("Estudiante") + "\n********************************");
      }
  
      if(event.getSource() == btnCDocente){
          areaTexto.setText(null);
          areaTexto.append("********************************\nCantidad de socios Docentes: " + this.getBiblioteca().cantidadSociosPorTipo("Docente") + "\n********************************");
      } 
   
      if(event.getSource() == btnPVencidos){
          areaTexto.setText(null);
          areaTexto.append("*****************Prestamos Vencidos***************");
          if(this.getBiblioteca().prestamosVencidos().size() != 0)
          {
              for(Prestamo e: this.getBiblioteca().prestamosVencidos())
              {
                  areaTexto.append(e.toString());
              }
          }else{
              areaTexto.append("\nNo hay prestamos vencidos");
          }
      }
      
      if(event.getSource() == btnDResponsables){
              areaTexto.setText(null);
              areaTexto.append(this.getBiblioteca().listaDeDocentesResponsables());
      }
      
      if(event.getSource() == btnLsocios){
            areaTexto.setText(null);  
            areaTexto.append(this.getBiblioteca().listaDeSocios());
      }
        
      if(event.getSource() == btnLLibros){
            areaTexto.setText(null);  
            areaTexto.append(this.getBiblioteca().listaDeLibros());
      }
        
      if(event.getSource() == btnBuscarLibro){
          VentanaBuscarLibro ventanaBLibro = new VentanaBuscarLibro(this.getBiblioteca(), this);
           ventanaBLibro.setVisible(true);
           this.setVisible(false);
         }
                
      if(event.getSource() == btnBuscarSocio){
          if(buscarSocio.getText().isEmpty())
          {
            JOptionPane.showMessageDialog(null, "Ingrese el DNI del socio que desea buscar");
            }else{
                  areaTexto.setText(null);
                  if(this.getBiblioteca().getSocios().containsKey(Integer.parseInt(buscarSocio.getText())))
                   {
                       areaTexto.append(this.getBiblioteca().buscarSocio(Integer.parseInt(buscarSocio.getText())).toString());
                    }else{
                        areaTexto.append("No hay socio con ese DNI.\n");
                    }
        }
      }
        
      if(event.getSource() == btnVAdm){
          this.getVentanaAdm().setVisible(true);
           this.setVisible(false);
      } 
    }
}