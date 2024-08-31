
import java.util.*;
import java.util.Scanner;
/**
 * Ejecutable para la Gestion de una Biblioteca
 * 
 *  @author Centurion Esteban, Benitez Natalia, Benitez Gonzalo, Durand Emilio,Gonzales Francisco.
 * 
 */

public class GestionBiblioteca{
    public static void main(String args[]){
        int iniciar = 0, opcion = 0, v_dni, v_edicion, v_anio, v_numSocio, v_numLibro;
        String v_editorial, v_titulo, v_nombre, v_carrera, v_area;
        
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("Instancia objetos de cada tipo, los asigna a una biblioteca y muestra los datos como pide la consigna");
        
        Biblioteca unaBiblioteca = new Biblioteca("FACENA");
        
        unaBiblioteca.nuevoLibro("Programando con JAVA", 5, "Santillana", 2001);
        unaBiblioteca.nuevoLibro("Diccionario Pocket", 2, "LongMan", 2010);
        unaBiblioteca.nuevoLibro("Vivir para contarla", 3, "Billiken", 2016);
        unaBiblioteca.nuevoLibro("Inteligencia Artifical para principiantes", 1, "NASA", 2008);
        
        Docente unDocente = new Docente (34789281, "Jorge", "Matematica");
        
        unaBiblioteca.nuevoSocioEstudiante(43346329, "Roberto", "LSI");
        unaBiblioteca.addSocio(unDocente);
        
        unaBiblioteca.prestarLibro(new GregorianCalendar(), unaBiblioteca.buscarSocio(43346329), unaBiblioteca.getLibros().get(0));
        
        System.out.println("Cantidad de socios de tipo Estudiante: " + unaBiblioteca.cantidadSociosPorTipo("Estudiante"));
        
        System.out.println(unaBiblioteca.listaDeDocentesResponsables());
        
        System.out.println(unaBiblioteca.listaDeLibros());
        
        System.out.println(unaBiblioteca.listaDeSocios());
        
        System.out.println("Socio con el libro Programando con Java: " + unaBiblioteca.quienTieneElLibro(unaBiblioteca.getLibros().get(0)));
        
        
        while(iniciar==0)
        {
               System.out.println("*****************MENU*****************");
               System.out.println("1-Agregar un socio Estudiante");
               System.out.println("2-Agregar un socio Docente");
               System.out.println("3-Agregar un libro");
               System.out.println("4-Prestar un libro");
               System.out.println("5-Cantidad de socios Estudiantes");
               System.out.println("6-Cantidad de socios Docentes");
               System.out.println("7-Prestamos Vencidos");
               System.out.println("8-Devolver Libro");
               System.out.println("9-Lista de Docentes Responsables");
               System.out.println("10-Buscar Libro");
               System.out.println("11-Lista De Socios");
               System.out.println("12-Lista De Libros");
               System.out.println("13-Buscar Socio");
               System.out.println("14-Finalizar programa");
               opcion = teclado.nextInt();
               System.out.println("******************************************");
               switch(opcion){
                   case 1:
             
                   System.out.println("*****************Agregar un socio Estudiante***************");
                   teclado.nextLine();
                    
                   System.out.println("Ingrese el nombre del Estudiante: ");
                   v_nombre = teclado.nextLine();
                   System.out.println("Ingrese su carrera: ");
                   v_carrera = teclado.nextLine();
                   System.out.println("Ingrese el DNI del estudiante: ");
                   v_dni = teclado.nextInt();
                   
                   if(unaBiblioteca.getSocios().containsKey(v_dni))
                   {
                       System.out.println("\nYa hay un socio con ese DNI.\n");
                   }else{
                        unaBiblioteca.nuevoSocioEstudiante(v_dni, v_nombre, v_carrera);
                   }
                   break;
                   
                   case 2:
             
                   System.out.println("*****************Agregar un socio Docente***************");
                   teclado.nextLine();
                    
                   System.out.println("Ingrese el nombre del Docente: ");
                   v_nombre = teclado.nextLine();
                   System.out.println("Ingrese su area: ");
                   v_carrera = teclado.nextLine();
                   System.out.println("Ingrese el DNI del docente: ");
                   v_dni = teclado.nextInt();
                   
                   if(unaBiblioteca.getSocios().containsKey(v_dni))
                   {
                       System.out.println("\nYa hay un socio con ese DNI.\n");
                   }else{
                        unaBiblioteca.nuevoSocioDocente(v_dni, v_nombre, v_carrera);
                   }
                   
                   break;
                
                   case 3:
             
                   System.out.println("*****************Agrega Libro***************");
                   teclado.nextLine();
                    
                   System.out.println("Ingrese el titulo: ");
                   v_titulo = teclado.nextLine();
                   System.out.println("Ingrese la editorial: ");
                   v_editorial = teclado.nextLine();
                   System.out.println("Ingrese la edicion: ");
                   v_edicion = teclado.nextInt();
                   System.out.println("Ingrese el año de produccion: ");
                   v_anio = teclado.nextInt();
                   
                   unaBiblioteca.nuevoLibro(v_titulo, v_edicion, v_editorial, v_anio);
                   break;
                   
                   case 4:
             
                   System.out.println("*****************Prestar Libro***************");
                   teclado.nextLine();
                    
                   System.out.println(unaBiblioteca.listaDeSocios());
                   System.out.println("Ingrese el DNI del socio que llevara el libro: ");
                   v_dni = teclado.nextInt();
                   System.out.println(unaBiblioteca.listaDeLibros());
                   System.out.println("Ingrese el numero del libro: ");
                   v_numLibro = teclado.nextInt();
                   if(unaBiblioteca.prestarLibro(new GregorianCalendar(), unaBiblioteca.buscarSocio(v_dni), unaBiblioteca.getLibros().get(v_numLibro-1)))
                   {
                       System.out.println("Operacion realizada con exito.\n");
                    }else{
                       System.out.println("No se pudo realizar la operación.\n");
                    }
                   
                   break;
                   
                   case 5:
                   
                   System.out.println("********************************");
                   System.out.println("Cantidad de socios Estudiantes: " + unaBiblioteca.cantidadSociosPorTipo("Estudiante"));
                   System.out.println("\n********************************");
                   
                   break;
                   
                   case 6:
                   
                   System.out.println("********************************");
                   System.out.println("Cantidad de socios Docentes: " + unaBiblioteca.cantidadSociosPorTipo("Docente"));
                   System.out.println("\n********************************");
                   
                   break;
                   
                   case 7:
                   
                   System.out.println("*****************Prestamos Vencidos***************");
                   for(Prestamo e: unaBiblioteca.prestamosVencidos())
                   {
                       System.out.println(e.toString());
                    }
                   
                   break;
                   
                   case 8:
                   System.out.println("*****************Devolver libro*****************");
                   System.out.println(unaBiblioteca.listaDeLibros());
                   System.out.println("Ingrese el numero del libro: ");
                   v_numLibro = teclado.nextInt();
                   if(v_numLibro > unaBiblioteca.getLibros().size())
                   {
                       System.out.println("Numero invalido.\n");
                    } else if(!unaBiblioteca.getLibros().get(v_numLibro-1).prestado())
                   {
                    System.out.println("El libro no se encuentra prestado\n");
                    }else{
                        unaBiblioteca.devolverLibro(unaBiblioteca.getLibros().get(v_numLibro-1));
                        System.out.println("Libro devuelto con exito.\n");
                        
                    }
                        
                   break;
                   
                   case 9:
                   
                   System.out.println("*****************Lista de Docentes Responsables*****************");
                   System.out.println(unaBiblioteca.listaDeDocentesResponsables());
                   
                   break;
                   
                   case 10:
                   
                   System.out.println("*****************Buscar Libro*****************");
                   System.out.println(unaBiblioteca.listaDeLibros());
                   System.out.println("Ingrese el numero del libro: ");
                   v_numLibro = teclado.nextInt();
                   if(v_numLibro > unaBiblioteca.getLibros().size())
                   {
                       System.out.println("Numero invalido.\n");
                    } else {
                        System.out.println(unaBiblioteca.quienTieneElLibro(unaBiblioteca.getLibros().get(v_numLibro-1)));
                    }
                   
                   
                   break;
                   
                   case 11:
                   
                   System.out.println("*****************Lista de Socios*****************");
                   System.out.println(unaBiblioteca.listaDeSocios());
                   
                   break;
                   
                   case 12:
                   
                   System.out.println("*****************Lista de Libros*****************");
                   System.out.println(unaBiblioteca.listaDeLibros());
                   
                   break;
                   
                   case 13:
                   
                   System.out.println("*****************Buscar Socios*****************");
                   
                   System.out.println("Ingrese el dni del socio: ");
                   v_dni = teclado.nextInt();
                   if(unaBiblioteca.getSocios().containsKey(v_dni))
                   {
                       System.out.println(unaBiblioteca.buscarSocio(v_dni).toString());
                    }else{
                        System.out.println("No hay socio con ese DNI.\n");
                    }
                   
                   break;
                   
                   case 14:
                   
                   iniciar = 10;
                   System.out.println("Programa finalizado.\n");
                   
                   break;
                   
                   default:
                    System.out.println("Numero no valido.\n");
                    break;
             }
        }
    }
}