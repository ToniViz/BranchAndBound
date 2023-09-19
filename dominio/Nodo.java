
package dominio;

/**
 * This class contains one of the problem solutions
 */
public class Nodo
{
    //Patry-cooks
    private int[] pasteleros;
    //Allows you to know if a pastry-cook already has an order assigned
    private boolean[] asignados;
    //Last order
    private int ultimoPedido;
    //Total cost
    private int costeTotal;
    //Optimistic estimate
    private int estimacionOptimista;
    
    /**
     * Nodo constructor with all its parameters
     * @param pastele
     * @param asignad
     * @param ultimoPedido
     * @param costeTotal
     * @param estimacionOptimista
     */
    public Nodo(final int[] pastele, final boolean[] asignad, final int ultimoPedido, final int costeTotal, final int estimacionOptimista) {
        this.pasteleros = new int[pastele.length];
        for (int i = 0; i < pastele.length; ++i) {
            this.pasteleros[i] = pastele[i];
        }
        this.asignados = new boolean[asignad.length];
        for (int i = 0; i < asignad.length; ++i) {
            this.asignados[i] = asignad[i];
        }
        this.ultimoPedido = ultimoPedido;
        this.costeTotal = costeTotal;
        this.estimacionOptimista = estimacionOptimista;
    }
    
    /**
     * Nodo empty constructor
     */
    public Nodo() {
    }
    
    /**
     * Return patry cooks
     * @return: array of pastry cooks
     */
    public int[] getPasteleros() {
        return this.pasteleros;
    }
    
    /**
     * Set the pastry-cooks
     * @param past
     */
    public void setPasteleros(final int[] past) {
        if (this.pasteleros == null) {
            this.pasteleros = new int[past.length];
        }
        for (int i = 0; i < past.length; ++i) {
            this.pasteleros[i] = past[i];
        }
    }
    
    /**
     * Set the value of a pastry-cook according to the value of the testigo parameter
     * @param valor
     * @param testigo
     */
    public void setPasteleros(final int valor, final boolean testigo) {
        if (testigo) {
            for (int i = 0; i < this.pasteleros.length; ++i) {
                if (this.pasteleros[i] == 0) {
                    this.pasteleros[i] = valor;
                    break;
                }
            }
        }
        else {
            for (int i = this.pasteleros.length - 1; i >= 0; --i) {
                if (this.pasteleros[i] == valor) {
                    this.pasteleros[i] = 0;
                    break;
                }
            }
        }
    }
    
    /**
     * Set asignados value
     * @return: array of asignados
     */
    public boolean[] getAsignados() {
        return this.asignados;
    }
    
    public void setAsignados(final boolean[] asig) {
        if (this.asignados == null) {
            this.asignados = new boolean[asig.length];
        }
        for (int i = 0; i < asig.length; ++i) {
            this.asignados[i] = asig[i];
        }
    }
    
    /**
     * Set asignados value of the index parameter
     * @param valor
     * @param i
     */
    public void setAsignados(final boolean valor, final int i) {
        this.asignados[i] = valor;
    }
    
    /**
     * Get last order
     * @return: last order
     */
    public int getUltimoPedido() {
        return this.ultimoPedido;
    }
    
    /**
     * Set the last order
     * @param valor
     */
    public void setUltimoPedido(final int valor) {
        this.ultimoPedido = valor + 1;
    }
    
    /**
     * Set the first order
     * @param valor
     */
    public void setPrimerPedido(final int valor) {
        this.ultimoPedido = valor;
    }
    
    /**
     * Return the total cost
     * @return: total cost
     */
    public int getCosteTotal() {
        return this.costeTotal;
    }
    
    /**
     * Set total cost
     * @param costeTotal
     */
    public void setCosteTotal(final int costeTotal) {
        this.costeTotal = costeTotal;
    }
    
    /**
     * Returns the optimistic estimate
     * @return: optimistic estimate
     */
    public int getEstimacionOptimista() {
        return this.estimacionOptimista;
    }
    
    /**
     * Set the optimistic estimate
     * @param estimacionOptimista
     */
    public void setEstimacionOptimista(final int estimacionOptimista) {
        this.estimacionOptimista = estimacionOptimista;
    }
}
