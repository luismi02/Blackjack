
package black_jack;
public class BlackjackHand extends Hand{
     /*Calcula y devuelve el valor de esta mano en el juego de Blackjack.*/
    public int getValorBlackJack(){
    int val=0;// El valor calculado para la mano.
    boolean as=false;//Esto se establecerá en verdadero si el la mano contiene un as.
    int cartas=getCantidadCartas();// Número de cartas en la mano.(metodo definodo en la clase Hand)
    
        for (int i = 0; i < cartas; i++) {
            //Agrega el valor de la i-ésima carta en la mano.
                Card carta=getCarta(i); //La i-ésima tarjeta
                int valorCarta= carta.getValor();//El valor de blackjack de la i-ésima carta.(El valor normal, de 1 a 13.)
                
                if(valorCarta>10){
                    valorCarta=10;//Para un Jack, Queen o King.
                }if(valorCarta==1){
                  as=true;//Hay al menos un as.
                }
                val=val+valorCarta;
        }
        /* Ahora, val es el valor de la mano, contando cualquier as como 1.
        Si hay un as, y si cambia su valor de 1 a 11 dejaría el puntaje menor o igual a 21,
        /entonces hazlo sumando los 10 puntos adicionales a val. */
       if(as==true && val+10<=21)
           val=val+10;
       return val;
    }
    
}
