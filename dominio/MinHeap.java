
package dominio;

/**
 *  This class orders the solutions according to the lowest cost using a minheap
 */
public class MinHeap
{
    //Node
    private Nodo[] monticulo;
    //Size of the minheap
    private int size;
    //Max size allowed
    private int maxsize;
    
    private static final int FRONT = 1;
    
    /**
     * MinHeap constructor with it max size
     * @param maxsize
     */
    public MinHeap(final int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        this.monticulo = new Nodo[this.maxsize + 1];
        (this.monticulo[0] = new Nodo()).setEstimacionOptimista(Integer.MIN_VALUE);
    }
    
    /**
     * Minheap empty constructor
     */
    public MinHeap() {
    }
    
    /**
     * Return the parent position
     * @param posicion
     * @return: parent position
     */
    private int padre(final int posicion) {
        return posicion / 2;
    }
    
    /**
     * Return the left child position
     * @param posicion
     * @return: left child position
     */
    private int izqHijo(final int posicion) {
        return 2 * posicion;
    }
    
    /**
     * Return the right child position
     * @param posicion
     * @return: right child position
     */
    private int derHijo(final int posicion) {
        return 2 * posicion + 1;
    }
    
    /**
     * Check if the node is a leaf
     * @param pos
     * @return: true is a leaj; otherwise false
     */
    private boolean esHoja(final int pos) {
        return pos > this.size / 2;
    }
    
    /**
     * Change the position of the nodes
     * @param actual
     * @param pactual
     */
    private void intercambio(final int actual, final int pactual) {
        final Nodo aux = this.monticulo[actual];
        this.monticulo[actual] = this.monticulo[pactual];
        this.monticulo[pactual] = aux;
    }
    
    /**
     * Sort the minheap nodes
     * @param pos
     */
    private void amontonar(final int pos) {
        if (!this.esHoja(pos)) {
            int aux = pos;
            if (this.derHijo(pos) <= this.size) {
                aux = ((this.monticulo[this.izqHijo(pos)].getEstimacionOptimista() < this.monticulo[this.derHijo(pos)].getEstimacionOptimista()) ? this.izqHijo(pos) : this.derHijo(pos));
            }
            else {
                aux = this.izqHijo(pos);
            }
            if (this.monticulo[this.derHijo(pos)] != null) {
                if (this.monticulo[pos].getEstimacionOptimista() > this.monticulo[this.izqHijo(pos)].getEstimacionOptimista() || this.monticulo[pos].getEstimacionOptimista() > this.monticulo[this.derHijo(pos)].getEstimacionOptimista()) {
                    this.intercambio(pos, aux);
                    this.amontonar(aux);
                }
            }
            else if (this.monticulo[pos].getEstimacionOptimista() > this.monticulo[this.izqHijo(pos)].getEstimacionOptimista()) {
                this.intercambio(pos, aux);
                this.amontonar(aux);
            }
        }
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    /**
     * Add a new node
     * @param nodo
     */
    public void insertar(final Nodo nodo) {
        if (this.size >= this.maxsize) {
            System.out.println("El monticulo ya est\u00e1 lleno");
            return;
        }
        this.monticulo[++this.size] = nodo;
        for (int actual = this.size; this.monticulo[actual].getEstimacionOptimista() < this.monticulo[this.padre(actual)].getEstimacionOptimista(); actual = this.padre(actual)) {
            this.intercambio(actual, this.padre(actual));
        }
    }
    
    /**
     * Return the first node
     */
    public Nodo primero() {
        if (this.isEmpty()) {
            System.out.println("No existen elementos en el monticulo");
            return null;
        }
        return this.monticulo[1];
    }
    
    /**
     * Return the top node
     * @return: top node
     */
    public Nodo devolverCima() {
        Nodo pop = null;
        pop = this.monticulo[1];
        this.monticulo[1] = this.monticulo[this.size];
        this.monticulo[this.size] = null;
        --this.size;
        this.amontonar(1);
        return pop;
    }
    
    /**
     * Return the minheap size
     */
    public int getSize() {
        return this.size;
    }
}
