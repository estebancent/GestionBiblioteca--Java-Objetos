
/**
 * Clase administradora del libro y sus prestamos.
 * 
 * @author Emilio Durand
 * @version (nov 2023)
 */
import java.util.*;
public class Libro
{  /**
    *Declaracion de Variables de Instancia
    **/
    private String titulo;
    private int edicion;
    private String editorial;
    private int anio;
    private ArrayList <Prestamo> prestamos;
    
    /**
     * contructor de la clase Libro
     * @param String p_titulo
     * @param int p_edicion
     * @param String p_editorial
     * @param int p_anio
     */
    public Libro( String p_titulo, int p_edicion,String p_editorial, int p_anio){
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(new ArrayList<Prestamo>());
    }
    
    
    /**
     * 
     * @param p_titulo
     */
    private void setTitulo(String p_titulo){ 
        this.titulo=p_titulo;
    }
     /**
     * 
     * @param  p_edicion
     */
    private void setEdicion(int p_edicion){
        this.edicion=p_edicion;
    }
     /**
     * 
     * @param  p_editorial
     */
    private void setEditorial(String p_editorial){
        this.editorial=p_editorial;
    }
    /**
     * 
     * @param p_anio
     */
    private void setAnio(int p_anio){
        this.anio=p_anio;
    }
    private void setPrestamos(ArrayList p_prestamos){
        this.prestamos=p_prestamos;
    }
    
    
    /**
     * Retorna el Titulo del libro
     * @return un dato de tipo String
     */
    public String getTitulo(){
        return this.titulo;
    }
    /**
     * Retorna la Edicion del libro
     * @return un dato de tipo int
     */
    public int getEdicion(){
        return this.edicion;
    }
    /**
     * Retorna La Editorial 
     * @return un dato de tipo String
     */
    public String getEditorial(){
        return this.editorial;
    }
    /**
     * Retorna El Año
     * @return un dato de tipo int
     */
    public int getAnio(){
        return this.anio;
    }
    /**
     * Retorna el último préstamo del libro.
     * @return  tipo ArrayList<Prestamo>
     */
    public ArrayList<Prestamo> getPrestamos(){
        return this.prestamos;
    }
    /**
     * Metodo que permite agregar un prestamo del libro
     * @param Prestamo p_prestamo
     * @return no retorna ningun valor
     */
    
    public void addPrestamo(Prestamo p_prestamo){
        this.getPrestamos().add(p_prestamo);
    }
    /**
     * Permite quitar un Prestamo de la colección de Prestamos.
     * @param Prestamo p_prestamo
     * @return no retorna ningun valor
     */
    public void removePrestamo(Prestamo p_prestamo){
        this.getPrestamos().remove(p_prestamo);
    }
    /**
     * Metodo que retorna el ultimo prestamo del libro
     * @return ultimo prestamo del libro
     */
    public Prestamo getPrestamo(){
         if (this.getPrestamos().isEmpty()){
            return null;
        }else{   
           return this.getPrestamos().get(this.getPrestamos().size()-1);
        }
    }
        
    /**
     * Metodo que devuelve true si el libro se encuentra prestado o false si no
     * @return tipo de dato boolean
     */
    public boolean prestado(){
        if(this.getPrestamo()!=null && this.getPrestamo().getFechaDevolucion()==null) {
            return true;//como solo se tiene una fecha de devolución cuando se regresa el libro, esta debe ser no null
        }
        return false;
    }     
     
    /**
     * Metodo toString() devuelve el titulo del Libro
     * @return retorna una cadena de tipo String
     */
    public String toString (){
        return "Titulo: " +this.getTitulo();
    }
    
        
}

