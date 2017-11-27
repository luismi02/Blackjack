package black_jack;

/*Un objeto de tipo Deck representa una baraja de cartas. La cubiertaes un mazo de póquer regular que contiene
52 cartas regulares y que puede también opcionalmente incluye dos Jokers.*/
public class Deck {

    /*CREAR Una matriz de 52 o 54 cartas. Un mazo de 54 cartas contiene dos Jokers,
   además de las 52 cartas de un mazo de póker regular.*/
    private Card[] desk;

    /*Realiza un seguimiento del número de cartas que se han repartido desde el mazo hasta ahora.*/
    private int cartasUsadas;

    /* Construye una baraja de póker regular de 52 cartas. Inicialmente, las cartas están ordenadas.
    El método shuffle () se puede llamar para aleatorizar el orden. (Tenga en cuenta que "nuevo Deck ()" 
    es equivalente * a "cubierta nueva (falsa)" */
    public Deck() {
        this(false);//Simplemente llama al otro constructor en esta clase.
    }

    /* Construye una baraja de cartas de póker, la baraja contiene las 52 cartas habituales y opcionalmente
       puede contener dos Jokers para completar las 54 cartas. Inicialmente las cartas están ordenadas.
      El método shuffle () se puede llamar para aleatorizar el orden. @param includeJokers si es verdadero, 
       dos jokers están incluidos en el mazo; si es falso, no hay Jokers en el mazo. */
    public Deck(boolean incluyeJocker) {
        if (incluyeJocker) {
            desk = new Card[54];
        } else {
            desk = new Card[52];
        }
        int cartasCr = 0;//Cuantas cartas se han creado hasta ahora
        for (int indumentaria = 0; indumentaria <= 3; indumentaria++) {
            for (int valor = 1; valor <= 13; valor++) {
                desk[cartasCr] = new Card(valor, indumentaria);
                cartasCr++;
            }
        }
        if (incluyeJocker) {
            desk[53] = new Card(1, Card.joker);
            desk[54] = new Card(2, Card.joker);
        }
        //se asigna el valor de cero para que en futuras partidas tebga la misma funcionalidad
        cartasCr = 0;
    }

    /*Coloca todas las tarjetas usadas de nuevo en el mazo (si las hay), y
     baraja el mazo en un orden aleatorio.*/
    public void barajar() {
        for (int i = desk.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            Card temp = desk[i];
            desk[1] = desk[rand];
            desk[rand] = temp;
        }
        cartasUsadas = 0;
    }

    /*A medida que las cartas se reparten desde el mazo, la cantidad de cartas restantes disminuye.
    Esta función devuelve la cantidad de tarjetas que aún quedan en el mazo. El valor de retorno sería
    52 o 54 (dependiendo de si el mazo incluye jockers) cuando se crea la plataforma por primera vez o 
    después de que la plataforma ha sido barajado. Disminuye en 1 cada vez que el método dealCard ()
    es llamado.*/
    public int cartasRestantes() {
        return desk.length - cartasUsadas;
    }

    /*Elimina la siguiente carta del mazo y la devuelve. Es ilegal para llamar a este método si no hay 
      más cartas en el mazo. Usted puede verifique el número de tarjetas restantes llamando a la función
      cartasRestantes (). @return la carta que se retira de la baraja. @throws IllegalStateException si no
       quedan cartas en el mazo*/
    public Card cartadeOferta(){
        if(cartasUsadas==desk.length)
           throw new IllegalStateException("No quedan cartas en el mazo");
        cartasUsadas++;
        return desk[cartasUsadas-1];
        /*Nota de programación: las tarjetas no se eliminan literalmente de la matriz
         eso representa el mazo. Simplemente hacemos un seguimiento de cuántas tarjetas
         ha sido usado.*/
    }
    
    /*Comprobar si el mazo contiene jockes.
     * @return true, si este es un mazo de 54 cartas que contiene dos comodines, o falso si
     * esta es una baraja de 52 cartas que no contiene comodines.*/
    public boolean tieneJockers(){
      return (desk.length==54);
    }
}
