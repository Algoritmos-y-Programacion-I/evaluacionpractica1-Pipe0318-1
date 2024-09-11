package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }
    /**
     * Descripcion: Este metodo se encarga de solicitar los precios y la cantidad de cada una de las referencias, guardandolos
     * en los arreglos, recorriendo el arreglo con la longitud ya dada por el usuario
     * pre: El scanner debe estar inicializados 
     * pre: Los arreglos precios y unidades deben estar inicializados
     * pos: Los datos deben quedar ingresados en los arreglos 
     * 
     */
    

    public static void solicitarDatos(){

      
        for (int i = 0; i < precios.length; i++) {


        System.out.println("Digite el precio de la referencia "+(i+1));
        
            precios[i] = reader.nextDouble();
        

        System.out.println("Digite la cantidad de la referencia "+(i+1));
        
            unidades[i] = reader.nextInt();
        
        }
        
     
    }

    /**
     * Descripcion:Este metodo se encarga de calcular el total de unidades vendidas en el dia recorriendo el arreglo unidades
     * y sumando los valores de cada uno de los espacios del arreglo
     * pre: el arreglo unidades debe estar inicializado
     * pre: el arreglo no puede estar vacio 
     * 
     * @return double total Se devuelve el total de ventas como un entero
     */

    public static int calcularTotalUnidadesVendidas(){

        int total = 0;

        for (int i = 0; i < unidades.length; i++){
            total += unidades[i];

        }



        return total;

    }
    /**
     * Descripcion: El metodo debe calcular el precio promedio de las referencias de producto vendidas en el dia, recorriendo el arreglo precios 
     * sumando cada uno de los espacios y sus valores, y luego sumando el total con la cantidad de espacios
     * pre: El arreglo precios debe estar inicializado
     * pre: el arreglo no puede estar vacio
     * @return double promedio El arreglo devolvera el calculo del precio promedio en formato double 
     */
    public static double calcularPrecioPromedio(){
        double promedio = 0.0;
        int suma = 0;

        for (int i = 0; i < precios.length; i++){

            suma+=precios[i];

            promedio = suma/precios.length;

        
        }

        return promedio;

    }
    /**
     * Descripcion: Calculara el dinero recaudado en las ventas totales durante el dia recorriendo los 2 arreglos. 
     * Multiplicando los valores de unidades y los de precios 
     * pre: Los arreglos unidades y precios deben estar inicializados
     * pre: el arreglo no puede estar vacio  
     * @return double ventasTotales El arreglo devolvera el total de dinero recaudado durante el dia 
     */

    public static double calcularVentasTotales(){
        double ventasTotales = 0;

        for (int i = 0; i < unidades.length; i++){

            ventasTotales += unidades[i]*precios[i];

        }


        return ventasTotales;

    }
    /**
     * Descripcion: el programa calculara el numero de referencias de productos que superaron un limite minimo de ventas, multiplicando
     * los valores de precios y unidades, evaluando si cada una supero el limite o no.
     * pre: los arreglos precios y unidades deben estar inicializados
     * pre: los arreglos no pueden estar vacios
     * @param double limite El limite de ventas dado por el usuario 
     * @return int contador El arreglo devolvera el numero de referencias que han superado el limite minimo de ventas
     */

    public static int consultarReferenciasSobreLimite(double limite){

        int contador = 0;

        for (int i = 0; i < precios.length;i++){

            if ((precios[i]*unidades[i])>limite){
                contador++;
            }
        }

        return contador;

    }

}
