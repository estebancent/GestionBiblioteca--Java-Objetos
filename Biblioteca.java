
import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.GregorianCalendar;
//import java.util.*;
/**
 * Clase Biblioteca - Laboratorio 1
 * 
 * @author Centurion Esteban,Gonzales Francisco 
 *
 */

public class Biblioteca
{
    private String nombre;
    private HashMap<Integer, Socio> socios;
    private ArrayList <Libro> libros;

    /**
     * Constructor para objetos de la clase Biblioteca
     */
    public Biblioteca(String p_nombre)
    {
        this.setNombre(p_nombre);
        this.setSocios(new HashMap<Integer, Socio> ());
        this.setLibros(new ArrayList<Libro> ());
    }

    //Setters
    private void setNombre (String p_nombre){
       this.nombre = p_nombre;
    }
    
    private void setSocios(HashMap<Integer, Socio>  p_socios){
       this.socios = p_socios;
    }
    
    private void setLibros(ArrayList<Libro> p_libros){
        this.libros = p_libros;
    }
    
    //Getters
    public String getNombre(){
      return this.nombre;
    }
    
    public HashMap<Integer, Socio> getSocios(){
     return this.socios;
    }
    
    public ArrayList<Libro> getLibros(){
      return this.libros;
    }
    
    /**
     * Añade un socio a la biblioteca
     * 
     * @param p_socio Representa el socio que se desea añadir
     */
    public void addSocio(Socio p_socio){
       this.getSocios().put(p_socio.getDniSocio(), p_socio);
    }
    
    /**
     * Remueve un socio de la biblioteca
     * 
     * @param Representa el socio que se desea eliminar
     */
    public void removeSocio(Socio p_socio){
       if(this.getSocios().containsValue(this.buscarSocio(p_socio.getDniSocio()))){
         this.getSocios().remove(p_socio);
       }
    }
    
    /**
     * Añade un libro a la biblioteca
     * 
     * @param p_libro Representa el libro que se desea añadir
     */
    private void addLibro(Libro p_libro){
      this.getLibros().add(p_libro);
    }
    
    /**
     * Remueve un libro a la biblioteca
     * 
     * @param p_socio Representa el socio que se desea eliminar
     */
    public void removeLibro(Libro p_libro){
      this.getLibros().remove(p_libro);
    }
    
    /**
     * Agrega un libro a la biblioteca
     */
    public void nuevoLibro(String p_titulo, int p_edicion, String p_editorial, int anio){
       this.addLibro(new Libro (p_titulo,p_edicion,p_editorial,anio));
    }
    
    /**
     * Agrega un nuevo socio Estudiante a la biblioteca
     */
    public void nuevoSocioEstudiante(int p_edniSocio, String p_nombre ,  String p_carrera){
      this.addSocio(new Estudiante(p_edniSocio, p_nombre, p_carrera));
    }
    
    /**
     * Agrega un nuevo socio Docente a la biblioteca
     */
    public void nuevoSocioDocente(int p_dniSocio, String p_nombre ,  String p_area){
      this.addSocio(new Docente (p_dniSocio, p_nombre, p_area));
    }
    
    /**
     * Devuelve la cantidad de socios del tipo que recibe como parametro
     * 
     * @param p_objeto Representa el tipo de objeto que se desea consultar
     */
    public int cantidadSociosPorTipo(String p_objeto){
        int cantidadDeSocioPorTipo=0;
        
        for(Map.Entry <Integer, Socio> e: socios.entrySet())
        {
           if(e.getValue().soyDeLaClase() == p_objeto){
             cantidadDeSocioPorTipo ++; 
           }
        }
        
        return cantidadDeSocioPorTipo;
    }
    
    /**
     * Devuelve True o False si puede prestar un Libro a un Socio
     * 
     * @param p_fechaRetiro Representa la fecha de retiro
     * @param p_socio Representa el socio que llevara el libro
     * @param p_libro Representa el libro que se llevara prestado
     */
    public boolean prestarLibro(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro){
        boolean prestarLibro = false;
        
        if((this.getSocios().containsValue(p_socio)) && (p_socio.puedePedir()) && (p_libro.prestado() == false)){
            Prestamo unPrestamo = new Prestamo(p_fechaRetiro,p_socio,p_libro);
            this.getLibros().get(this.getLibros().indexOf(p_libro)).addPrestamo(unPrestamo);
            this.buscarSocio(p_socio.getDniSocio()).addPrestamo(unPrestamo);
            prestarLibro = true;
        }
        
        return  prestarLibro;
     }
     /**
    public boolean devueltaLibro(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro){
        boolean devueltaLibro = false;
        
        if((this.getSocios().containsValue(p_socio))  && (p_libro.prestado() == false)){
            Prestamo unPrestamo = new Prestamo(p_fechaRetiro,p_socio,p_libro);
            this.getLibros().get(this.getLibros().indexOf(p_libro)).removePrestamo(unPrestamo);
            this.buscarSocio(p_socio.getDniSocio()).removePrestamo(unPrestamo);
            devueltaLibro = true;
        }
        return devueltaLibro = true;
    }**/
    
    /**
     * Devuelve un libro a la biblioteca
     */
    public void devolverLibro(Libro p_libro){
        this.getLibros().get(this.getLibros().indexOf(p_libro)).getPrestamo().registrarFechaDevolucion(new GregorianCalendar());
        
}
    /**
     * Devuelve una lista con los prestamos vencidos
     */
    public  ArrayList<Prestamo> prestamosVencidos(){
       ArrayList<Prestamo> prestamosVencidos = new ArrayList<Prestamo>();
        
        for(Map.Entry <Integer, Socio> e: socios.entrySet())
        {
            for(Prestamo i: e.getValue().getPrestamos())
            {
                if(i.vencido(new GregorianCalendar()))
                {
                    prestamosVencidos.add(i);
                }
            }
        }
        
       return prestamosVencidos;
    }
    
    /**
     * Devuelve una lista con los docentes Responsables
     */
    public  ArrayList<Docente> docentesResponsables(){
        ArrayList<Docente>  docentesResponsables = new ArrayList<Docente>();
        
        for(Map.Entry <Integer, Socio> e: socios.entrySet()){
             if (e.getValue().getClass() == Docente.class){
                
                if(((Docente)e.getValue()).esResponsable()){
                    docentesResponsables.add((Docente)e.getValue());
                }
                
             }
        }
        
        return docentesResponsables;
    }
    
    /**
     * Busca y devuelve el nombre del socio que tiene el libro que recibe como parametro
     * 
     * @param p_libro Representa el libro que se desea buscar
     */
    public String quienTieneElLibro(Libro p_libro){
        if(this.getLibros().get(this.getLibros().indexOf(p_libro)).prestado())
        {
            return this.getLibros().get(this.getLibros().indexOf(p_libro)).getPrestamo().getSocio().getNombre();
        }else{
            return "El libro esta en la biblioteca";
        }
    }
    
    /**
    * Devuelve una cadena con los socios de la biblioteca 
    */
    public String listaDeSocios(){
        if(this.getSocios().size() == 0){
           return "No hay socios en la biblioteca";   
        }else{
           int nroSocio = 0;
           String listaSocios = "Lista de Socios: \n";
           for(Map.Entry <Integer, Socio> e: socios.entrySet()){
               listaSocios = listaSocios + ++nroSocio + ")D.N.I.: " + e.getValue().getDniSocio() +
               "||" + e.getValue().getNombre() + "(" + e.getValue().soyDeLaClase() + ") ||" +
               "Libros Prestados: " + e.getValue().cantLibrosPrestados() + "\n";
           }  
             
           listaSocios = listaSocios + "******\n";
           listaSocios = listaSocios + "Cant. Socios tipo Estudiante: " + this.cantidadSociosPorTipo("Estudiante") + "\n";
           listaSocios = listaSocios + "Cant. Socios tipo Docente: " + this.cantidadSociosPorTipo("Docente") + "\n";
           listaSocios = listaSocios + "******";
           return listaSocios;
        }
    }
    
    /**
    * Devuelve una cadena con los libros de la biblioteca 
    */
    public String listaDeLibros(){
        if(this.getLibros().size() == 0){
            return "No hay libros en la biblioteca.\n";
        }else{
           int nroLibro = 0;
           String prestado, listaLibros = "Lista de libros: \n";
           for (Libro e: libros){
               prestado = "No";
               if(e.prestado()){
                  prestado = "Si";
                }
               listaLibros = listaLibros + ++nroLibro +  ") Titulo: " + e.getTitulo() + " || Prestado: (" + prestado + ")\n" ;
           } 
           return listaLibros;
        }
    }
    
    /**
    * Devuelve una cadena con los socios que sean Docentes Responsables de la biblioteca 
    */
    public String listaDeDocentesResponsables(){
        if(this.docentesResponsables().size() == 0)
        {
         return "No hay docentes responsables.\n";
        }else{
           int nroSocio = 0;
           String listaDocentesResponsables = "Lista de Docentes Responsables: \n";
           for(Docente  e: this.docentesResponsables())
            {
               listaDocentesResponsables = listaDocentesResponsables + ++nroSocio + ") D.N.I.: " + e.getDniSocio() +
               "||" + e.getNombre() + "(" + e.soyDeLaClase() + ") ||" +
               "Libros Prestados: " + e.cantLibrosPrestados() + "\n";
            }
           return listaDocentesResponsables;
       }
    }
    
    /**
     * Devuelve el socio que coincida con el DNI que recibe como parametro
     * 
     * @param p_dni Representa el DNI del socio que se desea buscar
     */
    public Socio buscarSocio(int p_dni){
        return this.getSocios().get(p_dni);
    }
}