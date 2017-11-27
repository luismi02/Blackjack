package black_jack;

import java.util.ArrayList;

/*Un objeto de tipo Mano representa una mano de cartas las tarjetas pertenecen a la clase Card. 
Una mano está vacía cuando se crea y se puede agregar cualquier cantidad de tarjetas.*/
public class Hand {

    private ArrayList<Card> mano;// Las cartas que estaran en la mano.

    // crea una mano que inicialmente esta vacia
    public Hand() {
        mano = new ArrayList<Card>();
    }

    //Retira todas las cartas de la mano, dejándolas vacías.
    public void limpiar() {
        mano.clear();
    }

    /*Agrega una carta a la mano. Se agrega al final de la mano actual.
     * @param c la tarjeta no nula que se agregará.
     * @throws NullPointerException si el parámetro c es nulo*/
    public void añadirCarta(Card c) {
        if (c == null) {
            throw new NullPointerException("No se puede agregar una carta nula a una mano");
        }
        mano.add(c);
    }

    /*Retire una carta de la mano, si está presente.
     * @param c la tarjeta que se eliminará. Si c es nulo o si la tarjeta no está en la mano, entonces 
    no se hace nada. */
    public void retirarCarta(Card c) {
        mano.remove(c);
    }

    /* Retire la tarjeta en una posición específica de la mano.
     * @param posicionar la posición de la tarjeta que se va a eliminar, donde as posiciones comienzan 
       desde cero.
     * @throws IllegalArgumentException si el puesto no existe en la mano, eso es si la posición es menor
       que 0 o mayor o igual a la cantidad de cartas en la mano.*/
    public void retirarCartaE(int posicion) {
        if (posicion < 0 || posicion >= mano.size()) {
            throw new IllegalArgumentException("La posición no existe en la mano: " + posicion);
        }
        mano.remove(posicion);
    }

    //Devuelve la cantidad de cartas en la mano.
    public int getCantidadCartas() {
        return mano.size();
    }

    /*Obtiene la carta en una posición específica en la mano. (Tenga en cuenta que esta tarjeta no se 
      elimina de la mano!)
     * @param posicionar la posición de la tarjeta que se va a devolver
     * @throws IllegalArgumentException si la posición no existe en la mano*/
    public Card getCarta(int posicion) {
        if (posicion < 0 || posicion >= mano.size()) {
            throw new IllegalArgumentException("La posición no existe en la mano: " + posicion);
        }
        return mano.get(posicion);
    }

    /*Clasifica las cartas en la mano para que las cartas del mismo palo sean agrupados, y dentro de un palo,
    las tarjetas se ordenan por valor.
     * Tenga en cuenta que se considera que los ases tienen el valor más bajo, 1.*/
    public void clasificarporPalo() {
        ArrayList<Card> nuevaMano = new ArrayList<Card>();
        while (mano.size() > 0) {
            int pos = 0;//posicion minima de la carta
            Card c = mano.get(0);//carta minima
            for (int i = 1; i < mano.size(); i++) {
                Card c1 = mano.get(i);
                if (c1.getIndumentaria() < c.getIndumentaria() || c1.getIndumentaria() == c.getIndumentaria() && c1.getValor() < c.getValor()) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            nuevaMano.add(c);
        }
        mano = nuevaMano;
    }

    /*Clasifica las cartas en la mano para que las cartas del mismo valor sean agrupados.
      Las cartas con el mismo valor se clasifican por palo.
     * Tenga en cuenta que se considera que los ases tienen el valor más bajo, 1.*/
    public void ordenarporValor() {
        ArrayList<Card> nuevaMano = new ArrayList<Card>();
        while (mano.size() > 0) {
            int pos = 0;//posicion minima de la carta
            Card c = mano.get(0);//carta minima
            for (int i = 1; i < mano.size(); i++) {
                Card c1 = mano.get(i);
                if (c1.getValor() < c.getValor() || c1.getValor() == c.getValor() && c1.getIndumentaria() < c.getIndumentaria()) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            nuevaMano.add(c);
        }
        mano=nuevaMano;
    }
}
