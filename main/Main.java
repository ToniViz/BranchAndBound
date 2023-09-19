

package main;

import java.io.Writer;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import dominio.Algoritmo;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;

public class Main
{
    
    /**
     * Checks if the input file exists
     * @param archivo
     * @return: true if it exists and the otherwise is false
     */
    public static boolean checkInput(final String archivo) {
        final File documento = new File(archivo);
        if (!documento.exists() || !documento.isFile() || !documento.canRead()) {
            System.out.println("El archivo " + archivo + " no existe o no se puede leer.");
            System.out.println("\n");
            return false;
        }
        return true;
    }
    
    /**
     * Checks if the output file exists
     * @param archivo
     * @return: true if it exists and the otherwise is false
     */
    public static boolean checkOutput(final String archivo) {
        final Scanner scan = new Scanner(System.in);
        final File documento = new File(archivo);
        if (!documento.exists() || !documento.canWrite() || !documento.isFile()) {
            System.out.println("El archivo " + documento.getName() + " de salida no existe o no se puede escribir sobre el.");
            System.out.println("\n");
            System.out.println("Se crear\u00e1 un archivo dentro del directorio con el contenido de las respuestas y estas se mostrarán por pantalla.");
            return false;
        }
        return true;
    }
    
    /**
     * Write the algorithm response to the file with BufferedWriter
     * @param bw
     * @param pasteleros
     * @param costeTotal
     * @throws IOException
     */
    public static void writeFile(final BufferedWriter bw, int[] pasteleros,  int costeTotal) throws IOException {
        for (int i = 0; i < pasteleros.length; ++i) {
            bw.write(String.valueOf(pasteleros[i]));
            System.out.print(String.valueOf(pasteleros[i]));
            if (pasteleros.length - 1 != i) {
                bw.write("-");
                System.out.print("-");
            }
            bw.flush();
        }
        bw.newLine();
        bw.write(String.valueOf(costeTotal));
        System.out.println();
        System.out.print(String.valueOf(costeTotal));
        bw.close();
    }
    
    /**
     * Read the costs to solve branch and bound algorithm using pastry problem
     * @return: list of costs
     */
    public static ArrayList<Integer> leerValoresCostes() {
        final ArrayList<Integer> costes = new ArrayList<Integer>();
        System.out.println("Introduzca la cantidad de pasteleros: ");
        final int filas = devolverEntero();
        costes.add(filas);
        System.out.println("Introduzca los tipos de pastel: ");
        final int columnas = devolverEntero();
        costes.add(columnas);
        System.out.println("Introduzca los siguientes valores...");
        for (int i = 1; i <= filas; ++i) {
            System.out.println("Para el pastelero Nº: " + i);
            for (int j = 1; j <= columnas; ++j) {
                System.out.println("Tiempo de preparación del tipo de pastel " + j + " :");       
                costes.add(devolverEntero());
            }
        }
        return costes;
    }
    
    /**
     * Read the number of orders to solve the problem
     * @param pasteles
     * @param tipos
     * @return: ¡number of orders
     */
    public static ArrayList<Integer> leerValoresPedidos(final int pasteles, final int tipos) {
        final ArrayList<Integer> pedidos = new ArrayList<Integer>();
        int valor = 0;
        System.out.println("Introduzca el valor de " + pasteles + " pedidos desde 1 a " + tipos + ".");
        for (int i = 0; i < pasteles; ++i) {
            System.out.println("Pedido " + (i + 1) + " :");
            do {
                valor = devolverEntero();
            } while (valor <= 0 && valor >= tipos);
            pedidos.add(valor);
        }
        return pedidos;
    }
    
    /**
     * Checks if the value is not a number
     * @return: number entered
     */
    public static int devolverEntero() {
        final Scanner scanner = new Scanner(System.in);
        String linea = null;
        do {
            linea = scanner.nextLine();
            if (linea.equals("S")) {
                System.out.println("Saliendo de la aplicación...");
                System.exit(0);
            }
            if (!isNumber(linea)) {
                System.out.println();
                System.out.println("Debe introducir un valor numérico");
            }
        } while (!isNumber(linea));
        return Integer.valueOf(linea);
    }
    
    /**
     * Work with devolverentero method. 
     * @param cadena
     * @return: true if it's a number and the otherwise is false
     */
    public static boolean isNumber(final String cadena) {
        if (cadena == null || cadena.equals("")) {
            return false;
        }
        for (int i = 0; i < cadena.length(); ++i) {
            final char c = cadena.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Main method that reads the values and solves the pastry problem. Shows the results in an external file of the same folder
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        ArrayList<Integer> valores = new ArrayList<Integer>();
        ArrayList<Integer> pedidos = new ArrayList<Integer>();
        int[] pasteleros = null;
        int costeTotal = 0;
        final long inicio = System.currentTimeMillis();
        final String traza = args[0];
        if (!traza.equals("-t")) {
            System.out.println("Error: el primer argumento debe ser '-t' para mostrar la traza.");
            return;
        }
        System.out.println("Se mostrará la traza del algoritmo en el proceso de resolución del problema de la mochila planteado.");
        System.out.println();
        final String ayuda = args[1];
        if (ayuda.equals("-h")) {
            System.out.println("Comando útil si introducen los valores por teclado: salir de la aplicación\n");
            final String input = args[2];
            if (!(Main.archivo = checkInput(input))) {
                System.out.println("Debe introducir los valores por teclado: ");
                System.out.println("\n");
                valores = leerValoresCostes();
                final int filas = valores.get(0);
                final int columnas = valores.get(1);
                pedidos = leerValoresPedidos(filas, columnas);
            }
            else {
                int valor = 0;
                int pasteles = 0;
                final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input), "utf-8"));
                int contadorLinea = 1;
                String linea;
                while ((linea = br.readLine()) != null) {
                    final StringTokenizer elemento = new StringTokenizer(linea);
                    if (contadorLinea == 1) {
                        pasteles = Integer.parseInt(elemento.nextToken());
                        valores.add(pasteles);
                    }
                    if (contadorLinea == 2) {
                        valor = Integer.parseInt(elemento.nextToken());
                        valores.add(valor);
                    }
                    if (contadorLinea == 3) {
                        final String cadena = linea;
                        final String[] numeros = cadena.split("-");
                        for (int i = 0; i < numeros.length; ++i) {
                            pedidos.add(Integer.parseInt(numeros[i]));
                        }
                    }
                    if (contadorLinea > 3) {
                        final String cadena = linea;
                        final String[] numeros = cadena.split(" ");
                        for (int i = 0; i < numeros.length; ++i) {
                            if (!numeros[i].isEmpty() && !numeros[i].equals("")) {
                                valores.add(Integer.parseInt(numeros[i]));
                            }
                        }
                    }
                    ++contadorLinea;
                }
            }
            final Algoritmo pastelero = new Algoritmo(valores, pedidos);
            try {
                pastelero.asignarPastelero();
                pasteleros = pastelero.getPasteleros();
                costeTotal = pastelero.getCosteTotal();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            final String output = args[3];
            if (checkOutput(output)) {
                final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "utf-8"));
                writeFile(bw, pasteleros, costeTotal);
            }
            else {
                final File fichero = new File("fichero.txt");
                final BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichero), "utf-8"));
                writeFile(bw2, pasteleros, costeTotal);
            }
            final long fin = System.currentTimeMillis();
            final double tiempo = (double)(fin - inicio);
            System.out.println();
            System.out.println("El tiempo de ejecución es:" + tiempo);
            System.out.println("El contador de nodos creados es: " + pastelero.getContadorMem());
            return;
        }
        System.out.println("Error: el argumento debe ser '-h' para mostrar información sobre el comando.");
    }
}
