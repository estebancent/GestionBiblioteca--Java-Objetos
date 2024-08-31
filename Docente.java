import java.util.Calendar;

/**
 * Clase Docente
 * 
 * @author Natalia Benitez 
 * @version 16.11.2023
 */
public class Docente extends Socio{
    //Variables de Instancia
    private String area;

    //Constructor
    public Docente(int p_dniSocio, String p_nombre,String p_area) {
        super(p_dniSocio,p_nombre,0);
        this.setArea(p_area);
    }

    public Docente(int p_dniSocio, String p_nombre, int p_diasPrestamo,String p_area) {
        super(p_dniSocio,p_nombre,p_diasPrestamo);
        this.setArea(p_area);
    }
    /* SETTERS & GETTERS */
    /**
     * This function sets the area of the object
     * 
     * @param p_area The area of the circle
     */
    private void setArea(String p_area) {
        this.area = p_area;
    }
    /**
     * This function returns the area of the object
     * 
     * @return The area of the rectangle.
     */
    public String getArea() {
        return this.area;
    }
    /* METODOS */
    /**
     * It checks if the user is responsible.
     */
    public boolean esResponsable(){
        Calendar fecha = Calendar.getInstance();
        boolean esResponsable = true;

        for (Prestamo auxPrestamo: super.getPrestamos()){ 

            if (auxPrestamo.vencido(fecha)){ 
                esResponsable = false;
            }
        }
        return esResponsable;
    }
    /**
     * The method , add loan days , adds loan days to the teacher . 
     * It is an award for responsibility
     * 
     * @param p_dias int
     * @return Nothing
     */
    public void agregarDiasDePrestamo(int p_dias){
        if(this.esResponsable() == true){
            this.setDiasPrestamo(p_dias);
        }else{
            System.out.println("*** No se pueden agregar dias de prestamo ya que el docente no es RESPONSABLE ***");
        }
    }
    /**
     * The function `soyDeLaClase()` returns the string `"Docente"`
     * 
     * @return The method soyDeLaClase() is being returned.
     */
    public String soyDeLaClase(){
        return "Docente";
    }
}
