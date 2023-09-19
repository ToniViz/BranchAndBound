

package dominio;


/**
 * Stack trace of the execution
 */
public class Traza
{
    public void factorial() {
        System.out.println("Se calcula el factorial del n\u00famero de pasteleros utilizados en el problema para dar tama\u00f1o al mont\u00edculo de m\u00ednimos utilizado en el problema.");
        System.out.println();
    }
    
    public void cota() {
        System.out.print("Se calcula la cota, que en este caso se corresponde con la estimaci\u00f3n pesimista del problema, que en este caso es: ");
    }
    
    public void variables() {
        System.out.println("Se crean las variables utilizadas dentro del m\u00e9todo que calcula el resultado: ");
        System.out.println("-Matriz de boleanos para registrar los pasteleros ya asignados.");
        System.out.println("-Cota pesimista y estimaci\u00f3n optimista.");
        System.out.println("-Nodo que inicializa el mont\u00edculo y su posterior introducci\u00f3n en el mismo.");
    }
    
    public void inicioWhile() {
        System.out.println("Se inicia el ciclo While que se detendr\u00e1 cuando el primer elemento del mont\u00edculo sea nulo o la cota actual sea mayor o igual a la");
        System.out.println("estimaci\u00f3n optimista del nodo actual evaluado.");
        System.out.println("Cada nodo extiende las posibles soluciones del ejercicio y se podan las que no constituyen una soluci\u00f3n \u00f3ptima.");
        System.out.println("De cada nodo se obtiene una cota que, si es inferior a la cota establecida, se convierte en la nueva cota del problema.");
        System.out.println("Se establece una posible soluci\u00f3n cuando el nodo extendido alcanza el \u00faltimo pastelero y se actualiza este siempre y cuando una soluci\u00f3n");
        System.out.println("tenga un coste inferior a la soluci\u00f3n actual.");
    }
    
    public void cantidadNodos() {
        System.out.print("El algoritmo ha creado una cantidad de nodos de: ");
    }
    
    public void nodosPodados() {
        System.out.print("Se han podado una cantidad de nodos de: ");
    }
}
