
/**
 * Clase Estudiante
 * 
 * @author Natalia Benitez
 * @version 16.11.2023
 */
public class Estudiante extends Socio{
    //Variables de Instancia
    private String carrera;

    //Constructores
    public Estudiante(int p_dniSocio, String p_nombre, int p_diasPrestamo, String p_carrera) {
        super(p_dniSocio,p_nombre,p_diasPrestamo);
        this.setCarrera(p_carrera);
    }

    public Estudiante(int p_dniSocio, String p_nombre, String p_carrera) {
        super(p_dniSocio,p_nombre,0);
        this.setCarrera(p_carrera);
    }
    
    /* SETTER & GETTER */
    /** Setter del atributo Carrera */
    private void setCarrera(String p_carrera) {
        this.carrera = p_carrera;
    }
    /** Getter del atributo Carrera */
    public String getCarrera() {
        return this.carrera;
    }
    /* METODOS */

    /**
     * It checks if the user can borrow a book.
     */
    public boolean puedePedir(){
        if(this.cantLibrosPrestados() < 3 && (super.puedePedir())){
            return true;
        }else{
            return false;
        }
    }
    /**
     * The function returns the string "Estudiante"
     * 
     * @return The method is returning the string "Estudiante"
     */
    public String soyDeLaClase(){
        return ("Estudiante");
    }
}
