package black_jack;

/*Un objeto de tipo Carta representa un naipe de una baraja de poker estándar, incluidos comodines.
La tarjeta tiene un traje, que pueden ser espadas, corazones, diamantes, palos o joker.
Una pala, corazón,diamante, o club tiene uno de los 13 valores: as, 2, 3, 4, 5, 6, 7,8, 9, 10,
jota, reina o rey. Tenga en cuenta que se considera que "as" es el valor más pequeño. Un joker también
puede tener un valor asociado;
este valor puede ser cualquier cosa y se puede utilizar para realizar un seguimiento de varios
diferentes jokers.
 */
public class Card {

    //las cartas tienen un valor constante
    //valor de los 4 palos + el Joker
    public final static int espadas = 0;
    public final static int corazones = 1;
    public final static int diamantes = 2;
    public final static int treboles = 3;
    public final static int joker = 3;
    //codigo para las tarjetas no numericas
    public final static int as = 1;
    public final static int jack = 11;
    public final static int queen = 12;
    public final static int king = 13;

    /*El valor de esta carta, es una de las constantes spadas, corazones, diamantes,
    tréboles, o JOKER. La demanda no puede ser cambiada después de que la tarjeta es asignada.*/
    private final int indumentaria;
    /*El valor de la tarjeta. Para una carta normal, este es uno de los valores 1 a 13, 
     con 1 representando ACE. Para un JOKER, el valor puede ser cualquier cosa. 
     El valor no se puede cambiar después de la tarjeta esta construido.*/
    private final int valor;

    /*Crea un Joker, con 1 como el valor asociado. (Tenga en cuenta que"nueva Tarjeta ()"
      es equivalente a "nueva Tarjeta (1,Card.joker)".)*/
    public Card() {
        indumentaria = joker;
        valor = 1;
    }

    /*Crea una carta con un palo y valor especificados.
     @param theValue el valor de la nueva tarjeta. Para una tarjeta regular (no joker),
     el valor debe estar en el rango de 1 a 13, donde 1 representa un As.
     Puede usar las constantes Card.ACE, Card.JACK, Card.QUEEN y Card.KING.  
     Para un Joker, el valor puede ser cualquier cosa.
     @param theSuit el traje de la nueva tarjeta. Este debe ser uno de los valores
     Card.SPADES, Card.HEARTS, Card.DIAMONDS, Card.CLUBS o Card.JOKER.
     @throws IllegalArgumentException si los valores de los parámetros no están en el
     rangos permisibles*/
    public Card(int elValor, int laIndumentaria) {
        if (laIndumentaria != espadas && laIndumentaria != corazones && laIndumentaria != diamantes && laIndumentaria != treboles && laIndumentaria != joker) {
            throw new IllegalArgumentException("Indumentaria de traje ILEGAL");
        }
        if (laIndumentaria != joker & (elValor < 1 || elValor > 13)) {
            throw new IllegalArgumentException("Valor de carta ILEGAL");
        }
        valor = elValor;
        indumentaria = laIndumentaria;
    }

    /*Devuelve la indumentaruia de esta carta.
     devuelve el indumentaria, que es una de las constantes Card.spadas, 
     Card.corazones, Card.diamantes, Card.treboles o Card.joker*/
    public int getIndumentaria() {
        return indumentaria;
    }

    /* Devuelve el valor de la carta, que es uno de los números del 1 al 13, inclusive para
     una tarjeta regular, y que puede ser de algún valor para un Joker.*/
    public int getValor() {
        return valor;
    }

    /*Devuelve una representación de Cadena del palo de la carta.
    "Picas", "Corazones", "Diamantes", "Clubes" o "Joker"*/
    public String getIndumentariaString() {
        switch (indumentaria) {
            case espadas: return "espadas";
            case corazones: return "corazones";
            case diamantes: return "diamantes";
            case treboles: return "treboles";
            default: return "joker";
        }
    }

    /*Devuelve una representación de cadena del valor de la tarjeta.
     return para una carta regular, una de las cadenas "Ace", "2",
     "3", ..., "10", "Jack", "Queen" o "King". Para un Joker, el
     la cadena siempre es numérica.*/
    public String getValorString() {
        if (indumentaria == joker) {
            return " " + valor;
        } else {
            switch (valor) {
                case 1: return "As";
                case 2: return "2";
                case 3: return "3";
                case 4: return "4";
                case 5: return "5";
                case 6: return "6";
                case 7: return "7";
                case 8: return "8";
                case 9: return "9";
                case 10: return "10";
                case 11: return "Jack";
                case 12: return "Reina";
                default: return "Rey";
            }
        }
    }
    
    /*Devuelve una representación de cadena de esta tarjeta, incluidos su palo y su valor 
   (excepto que para un Joker con valor 1, el valor de retorno es solo "Joker"). 
   Valores de retorno de muestra son: 
   "Reina de Corazones", "10 de Diamantes", "As de Picas", "Joker", "Joker # 2"*/
    public String toString(){
    if(indumentaria==joker){
        if(valor==1)
            return "Joker";
        else
            return "Joker  #" +valor; 
    }else
        return getValorString() + "  de  " + getIndumentariaString();
    }
}
