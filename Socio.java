import java.util.ArrayList;
import java.util.Calendar;

/**
 * Abstraccion del objeto Socio.
 * 
 * @author Gonzalo Benitez
 * 
 */

abstract public class Socio {
    private int dniSocio;
    private String nombre;
    private int diasPrestamo;
    private ArrayList <Prestamo> prestamos = new ArrayList<Prestamo>();
    
    // **** CONSTRUCTOR **** //
    /**
     * @param p_dniSocio
     * @param p_nombre
     * @param p_diasPrestamo
     */
    Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
    }

    // **** SETTER'S **** //
    /**
     * Establece la variable dniSocio al valor del parámetro p_dniSocio.
     * 
     * @param p_dniSocio int.
     */
    private void setDniSocio(int p_dniSocio){
        this.dniSocio = p_dniSocio;
    }

    /**
     * Establece el valor de la variable nombre al valor del parámetro p_nombre.
     * 
     * @param p_nombre String.
     */
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }

    /**
     * Establece el número de días que un libro puede ser prestado.
     * 
     * @param p_dias int.
     */
    public void setDiasPrestamo(int p_dias){
        this.diasPrestamo = p_dias;
    }

    // **** GETTER'S **** //
    /**
     * Devuelve el DNI del Socio.
     * 
     * @return socio.
     */
    public int getDniSocio() {
        return this.dniSocio;
    }

    /**
     * Devuelve el valor de la variable nombre
     * 
     * @return nombre.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve el número de días que el libro ha estado prestado
     * 
     * @return prestamo.
     */
    public int getDiasPrestamo() {
        return this.diasPrestamo;
    }

    /**
     * Devuelve el ArrayList de prestamos.
     * 
     * @return ArrayList.
     */
    public ArrayList<Prestamo> getPrestamos() {
        return this.prestamos;
    }

    // **** METODOS **** //
    /**
     * Agrega un préstamo al ArrayList de préstamos.
     * 
     * @param p_Prestamo Prestamo
     */
    public void addPrestamo(Prestamo p_Prestamo) {
        this.getPrestamos().add(p_Prestamo);
    }

   /**
    * Elimina un préstamo del ArrayList de préstamos.
    * 
    * @param p_Prestamo Prestamo
    */
    public void removePrestamo(Prestamo p_Prestamo) {
        this.getPrestamos().remove(p_Prestamo);
    }
    
    /**
     * Devuelve el número de libros que han sido prestados.
     * 
     * @return El número de libros que se han prestado.
     */
    public int cantLibrosPrestados() {
        int cant = 0;
        
        for (int i = 0; i < this.getPrestamos().size(); i++) {
            if (this.getPrestamos().get(i).getFechaDevolucion() == null) {
                cant++;
            }
        }

        return cant;
    }

    /**
     * Devuelve un string con el DNI, nombre y apellidos del socio, la clase a la que pertenece y la cantidad de libros que ha tomado prestados
     * 
     * @return un String.
     */
    public String toString(){
        return "DNI: " + this.getDniSocio() + " || " + "Nombre y Apellido: " + this.getNombre() + " (" + this.soyDeLaClase() + ") " + " || " + this.cantLibrosPrestados();
    }

    /**
     *  Devuelve true si el socio no tiene ningún préstamo vencido.
     * 
     * @return Un valor Booleano.
     */
    public boolean puedePedir() {
        Calendar fechaActual = Calendar.getInstance();
        boolean valor = true;

        for (int i = 0; i < this.getPrestamos().size(); i++) {
            if (this.getPrestamos().get(i).vencido(fechaActual) ) {
                valor = false;
                break;
            }
        }

        return valor;
    }

    /**
     * Devuelve una cadena que dice qué clase es el objeto.
     * 
     * @return tipo de la clase.
     */
    abstract public String soyDeLaClase();
}
