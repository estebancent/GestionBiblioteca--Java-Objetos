import java.time.LocalDate;
import java.util.*;

/**
 * Abstraccion del objeto Prestamo
 * 
 * @author Gonzalo Benitez
 * 
 */

public class Prestamo {
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Libro libro;
    private Socio socio;

    // **** CONSTRUCTOR **** //
    /**
     * @param p_fecharRetiro
     * @param p_socio
     * @param p_libro
     */
    Prestamo (Calendar p_fecharRetiro, Socio p_socio, Libro p_libro){
        this.setFechaRetiro(p_fecharRetiro);
        this.setSocio(p_socio);
        this.setLibro(p_libro);
    }

    // **** SETTER'S **** //
    /**
     * Establece el valor de la variable fechaRetiro al valor del parámetro p_fechaRetiro.
     * 
     * @param p_fechaRetiro java.util.Calendar
     */
    private void setFechaRetiro(Calendar p_fechaRetiro) {
        this.fechaRetiro = p_fechaRetiro;
    }

    /**
     * Establece la fecha de devolución del libro.
     * 
     * @param p_fechaDevolucion
     */
    private void setFechaDevolucion(Calendar p_fechaDevolucion) {
        this.fechaDevolucion = p_fechaDevolucion;
    }

    /**
     * Establece el valor de la variable privada libro al valor del parámetro p_libro.
     * 
     * @param p_libro Libro.
     */
    private void setLibro(Libro p_libro) {
        this.libro = p_libro;
    }

    /**
     * Establece el valor de la variable privada socio al valor del parámetro p_socio.
     * 
     * @param p_socio Socio.
     */
    private void setSocio(Socio p_socio) {
        this.socio = p_socio;
    }

    // **** GETTER'S **** //
    /**
     * Devuelve la fecha de retiro del libro.
     * 
     * @return fecha de retiro.
     */
    public Calendar getFechaRetiro() {
        return this.fechaRetiro;
    }

    /**
     * Ddevuelve la fecha de devolución del libro.
     * 
     * @return fecha de devolucion.
     */
    public Calendar getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    /**
     * Devuelve el libro que está asociado con la instancia actual de la clase
     * 
     * @return objeto de tipo Libro.
     */
    public Libro getLibro() {
        return this.libro;
    }

    /**
     * Devuelve el valor de la variable "socio"
     * 
     * @return objeto de tipo Socio.
     */
    public Socio getSocio() {
        return this.socio;
    }

    // **** METODOS **** //
    /**
     * Establece la fecha de devolución de un libro
     * 
     * @param p_fechaDevolucion Calendar
     */
    public void registrarFechaDevolucion (Calendar p_fechaDevolucion){
        this.setFechaDevolucion(p_fechaDevolucion);
        
    }

    /**
     * Devuelve verdadero si la fecha pasada como parámetro es mayor que la fecha de vencimiento del préstamo
     * 
     * @param p_fecha 
     * @return Valor Booleano;
     */
    public boolean vencido (Calendar p_fecha){
        Calendar fechaVencimiento = new GregorianCalendar();
        int diasDePrestamo = this.getSocio().getDiasPrestamo();

        fechaVencimiento.set(this.getFechaRetiro().get(Calendar.YEAR), this.getFechaRetiro().get(Calendar.MONTH), this.getFechaRetiro().get(Calendar.DATE));
        fechaVencimiento.add(Calendar.DAY_OF_YEAR, diasDePrestamo);

        return (p_fecha.compareTo(fechaVencimiento) > 0);
    }

    /**
     * Devuelve una cadena con la fecha del préstamo, la fecha de la devolución (si existe), el título del libro y el nombre del miembro
     * 
     * @return Fecha del préstamo, la fecha de la devolución, el título del libro y el nombre del
     * miembro.
     */
    public String toString (){
        Calendar fr = this.getFechaRetiro();
        Calendar fd = this.getFechaDevolucion();
        String devolucion = " ";

        String retiro = fr.get(Calendar.DATE) + "/" + fr.get((Calendar.MONTH)+1) + "/" + fr.get(Calendar.YEAR);
        
        if (this.getFechaDevolucion() != null) {
            devolucion = fd.get(Calendar.DATE) + "/" + fd.get((Calendar.MONTH)+1) + "/" + fd.get(Calendar.YEAR);
        }

        return "Retiro: " + retiro + " - Devolucion: " + devolucion + "\n" +
               "Libro: " + this.getLibro().getTitulo() + "\n" +
               "Socio: " + this.getSocio().getNombre();
    }
}
