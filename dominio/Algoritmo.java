

package dominio;

import java.util.ArrayList;

/**
 * This class contains the branch and bound algorithm to solve the pastry problem.
 *  It uses optimistic and pessimistic estimation, and also works with a Min-heap. Each node has an answer
 */
public class Algoritmo
{
    //Cost of each cake
    private int[][] costes;
    //orders
    private int[] pedido;
    //Pastry-cooks
    private int[] pasteleros;
    //Total cost
    private int costeTotal;
    //Min heap
    private final MinHeap monticulo;
    //Parent node
    private Nodo nodo;
    //Child node
    private Nodo hijo;
    //Stack trace
    private Traza traza;
    //Memory counter
    private int contadorMem;
    
    /**
     * Algiritmo constructor. Initialize data estructures to find the solution
     * @param listado
     * @param pedidos
     * @throws Exception
     */
    public Algoritmo(final ArrayList<Integer> listado, final ArrayList<Integer> pedidos) throws Exception {
        this.traza = new Traza();
        this.contadorMem = 0;
        int contador = 2;
        this.monticulo = new MinHeap(this.factorial(listado.get(0)));
        this.traza.factorial();
        final int correcto = listado.get(0) * listado.get(1) + 2;
        if (pedidos.size() <= listado.get(0) && correcto <= listado.size()) {
            this.pasteleros = new int[(int)listado.get(0)];
            this.pedido = new int[pedidos.size()];
            this.costes = new int[(int)listado.get(0)][(int)listado.get(1)];
            for (int i = 0; i < listado.get(0); ++i) {
                for (int j = 0; j < listado.get(1); ++j) {
                    this.costes[i][j] = listado.get(contador);
                    ++contador;
                }
            }
            for (int i = 0; i < pedidos.size(); ++i) {
                this.pedido[i] = pedidos.get(i);
            }
        }
        else {
            System.out.println("Número de valores incorrectos");
            System.exit(-1);
        }
    }
    
    
/**
 * Solves the factorial problem
 * @param numero
 * @return: current value
 */
    private int factorial(final int numero) {
        if (numero == 0) {
            return 1;
        }
        return numero * this.factorial(numero - 1);
    }
    /**
     * Find a solution to patry problem
     */
    public void asignarPastelero() {
        this.traza.variables();
        this.nodo = new Nodo();
        final boolean[] boleano = new boolean[this.pasteleros.length];
        int podados = 0;
        (this.nodo = new Nodo(this.pasteleros, boleano, 0, 0, 0)).setEstimacionOptimista(this.estimacionOpt(this.nodo, false));
        int cota = this.estimacionPes(this.nodo, false);
        this.traza.cota();
        System.out.println(cota);
        this.monticulo.insertar(this.nodo);
        boolean control = false;
        boolean testigo = true;
        this.traza.inicioWhile();
        while (this.monticulo.primero() != null && this.estimacionOpt(this.monticulo.primero(), true) <= cota) {
            this.nodo = this.monticulo.devolverCima();
            ++podados;
            (this.hijo = new Nodo()).setPasteleros(this.nodo.getPasteleros());
            if (control) {
                this.hijo.setUltimoPedido(this.nodo.getUltimoPedido());
            }
            else {
                this.hijo.setPrimerPedido(0);
                control = true;
            }
            this.hijo.setAsignados(this.nodo.getAsignados());
            for (int i = 0; i < this.pedido.length; ++i) {
                if (!this.hijo.getAsignados()[i]) {
                    this.hijo.setPasteleros(i + 1, true);
                    this.hijo.setAsignados(true, i);
                    this.hijo.setCosteTotal(this.nodo.getCosteTotal() + this.costes[i][this.pedido[this.hijo.getUltimoPedido()] - 1]);
                    if (this.hijo.getUltimoPedido() == this.pedido.length - 1) {
                        if (cota >= this.hijo.getCosteTotal() && testigo) {
                            this.setPasteleros(this.hijo.getPasteleros());
                            this.costeTotal = this.hijo.getCosteTotal();
                            cota = this.costeTotal;
                            testigo = false;
                        }
                        if (this.costeTotal > this.hijo.getCosteTotal()) {
                            this.setPasteleros(this.hijo.getPasteleros());
                            this.costeTotal = this.hijo.getCosteTotal();
                            cota = this.costeTotal;
                        }
                    }
                    else {
                        this.hijo.setEstimacionOptimista(this.estimacionOpt(this.hijo, true));
                        final int[] past = this.hijo.getPasteleros();
                        final boolean[] asig = this.hijo.getAsignados();
                        final int ultPed = this.hijo.getUltimoPedido();
                        final int cosT = this.hijo.getCosteTotal();
                        final int estOp = this.hijo.getEstimacionOptimista();
                        this.monticulo.insertar(new Nodo(past, asig, ultPed, cosT, estOp));
                        ++this.contadorMem;
                        final int estPesimista = this.estimacionPes(this.hijo, true);
                        if (cota > estPesimista) {
                            cota = estPesimista;
                        }
                    }
                    this.hijo.setAsignados(false, i);
                    this.hijo.setPasteleros(i + 1, false);
                }
            }
        }
        this.traza.cantidadNodos();
        System.out.print(this.contadorMem);
        System.out.println();
        this.traza.nodosPodados();
        System.out.print(podados);
        System.out.println();
    }
    
    /**
     * Set the patry-cooks
     * @param paste
     */
    private void setPasteleros(final int[] paste) {
        if (this.pasteleros == null) {
            this.pasteleros = new int[paste.length];
        }
        for (int i = 0; i < paste.length; ++i) {
            this.pasteleros[i] = paste[i];
        }
    }
    
    /**
     * Get the memory counter (number of nodos created)
     * @return
     */
    public int getContadorMem() {
        return this.contadorMem;
    }
    
    /**
     * Return the pastry-cooks
     * @return: array of pastry-cooks
     */
    public int[] getPasteleros() {
        return this.pasteleros;
    }
    
    /**
     * Return the total cost
     * @return: total cost
     */
    public int getCosteTotal() {
        return this.costeTotal;
    }
    
    /**
     * Return number of minheap created
     * @return
     */
    public int getMonticuloSize() {
        return this.monticulo.getSize();
    }
    
    /**
     * Optimistic estimate to solve the problem. Contains the current best answer
     * @param nodoAux
     * @param testigo
     * @return: estimate value
     */
    public int estimacionOpt(final Nodo nodoAux, final boolean testigo) {
        final int tamano = this.pedido.length;
        int estimacion = nodoAux.getCosteTotal();
        final int m = this.costes.length;
        int auxiliar = 0;
        if (testigo) {
            auxiliar = nodoAux.getUltimoPedido() + 1;
        }
        else {
            auxiliar = nodoAux.getUltimoPedido();
        }
        for (int i = auxiliar; i < tamano; ++i) {
            int menorC = this.costes[0][this.pedido[i] - 1];
            for (int j = 1; j < m; ++j) {
                if (menorC > this.costes[j][this.pedido[i] - 1]) {
                    menorC = this.costes[j][this.pedido[i] - 1];
                }
            }
            estimacion += menorC;
        }
        return estimacion;
    }
    
    /**
     * Pessimistic estimate to prune nodes and solve the problem
     * @param nodoAux
     * @param testigo
     * @return: estimate value
     */
    public int estimacionPes(final Nodo nodoAux, final boolean testigo) {
        final int m = this.costes.length;
        final int n = this.costes[0].length;
        final int tamano = this.pedido.length;
        int estimacion = nodoAux.getCosteTotal();
        int auxiliar = 0;
        if (testigo) {
            auxiliar = nodoAux.getUltimoPedido() + 1;
        }
        else {
            auxiliar = nodoAux.getUltimoPedido();
        }
        for (int i = auxiliar; i < tamano; ++i) {
            int mayorC = this.costes[0][this.pedido[i] - 1];
            for (int j = 1; j < m; ++j) {
                if (mayorC < this.costes[j][this.pedido[i] - 1]) {
                    mayorC = this.costes[j][this.pedido[i] - 1];
                }
            }
            estimacion += mayorC;
        }
        return estimacion;
    }
}
