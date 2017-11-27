package black_jack;

import java.io.*;
import java.util.Scanner;

/*Este programa permite al usuario jugar BlacJack. El ordenador actua como el distribuidor (Crupier).
Al inicio el usuario tiene $100 con lo que podra hacer una apuesta en cada juego (el mon to que usuario desee)
y será expulsado (ya no podra jugar mas) cuandio el el monto de dinero llegue a 0 
Reglas de la casa: el repartidor golpea a un total de 16 o menos y se encuentra en un total de 17 o más.
El distribuidor gana lazos. Se usa una nueva baraja de cartas para cada juego.
 */
public class BlackJack {

    public static void main(String[] args) {
        int dinero;
        int apuestas = 0;
        boolean usuarioGano;
        System.out.println("Bienvenido al juego de Blackjack");
        System.out.println("");//OJO
        //monto para apuestas
        dinero = 100;

        while (true) {
            System.out.println("Usted tiene: $" + dinero + "  para realizar apuestas");
            do {
                System.out.println("Cuanto dinero desea apostar?(Ingrese 0 para finalizar)");
                System.out.println("?");
                Scanner entrada = new Scanner(System.in);
                apuestas = Integer.parseInt(entrada.nextLine());
                if (apuestas < 0 || apuestas > dinero) {
                    System.out.println("Su respuesta debe estar entre 0 y " + dinero + " ");
                }
            } while (apuestas < 0 || apuestas > dinero);
            if (apuestas == 0) {
                break;
            }
            usuarioGano = playBlackjack();
            if (usuarioGano) {
                dinero = dinero + apuestas;
            } else {
                dinero = dinero - apuestas;
            }
            System.out.println();
            if (dinero == 0) {
                System.out.println("Parece que te has quedado sin dinero!!!");
                break;
            }
        }
        System.out.println();
        System.out.println("Sales con $ " + dinero + " ");
    }//termina el metodo main

    /* Permita que el usuario juegue un juego de Blackjack, con la computadora como distribuidor.
    * @return true si el usuario gana el juego, falso si el usuario pierde.*/
    static boolean playBlackjack() {
        Deck deck = new Deck();//una nueva baraja de cartas para el juego
        BlackjackHand manoDistribuidor = new BlackjackHand();//la mano de crupier
        BlackjackHand manoUsuario = new BlackjackHand();//la mano del usuario

        // Baraja el mazo, reparte dos cartas a cada jugador. *
        deck.barajar();
        manoDistribuidor.añadirCarta(deck.cartadeOferta());
        manoDistribuidor.añadirCarta(deck.cartadeOferta());
        manoUsuario.añadirCarta(deck.cartadeOferta());
        manoUsuario.añadirCarta(deck.cartadeOferta());

        System.out.println();
        System.out.println();

        /*Verifica si uno de los jugadores tiene Blackjack (dos cartas que suman un total de 21).
       El jugador con Blackjack gana el juego. El distribuidor gana lazos.*/
        if (manoDistribuidor.getValorBlackJack() == 21) {
            System.out.println("El distribuidor tiene la " + manoDistribuidor.getCarta(0) + " y la " + manoDistribuidor.getCarta(1) + ".");
            System.out.println("El usuario tiene la " + manoUsuario.getCarta(0) + " y la" + manoUsuario.getCarta(1) + " . ");
            System.out.println();
            System.out.println("El distribuidor tiene Blackjack. El distribuidor gana");
            return false;
        }
        if (manoUsuario.getValorBlackJack() == 21) {
            System.out.println("El distribuidor tiene la " + manoDistribuidor.getCarta(0) + " y la " + manoDistribuidor.getCarta(1) + ".");
            System.out.println("El usuario tiene la " + manoUsuario.getCarta(0) + " y la" + manoUsuario.getCarta(1) + " . ");
            System.out.println();
            System.out.println("Usted tiene Blackjack. !!FELICEDADES!! Usted gana!!");
            return true;
        }
        /*Si ninguno de los jugadores tiene Blackjack, juega el juego. Primero el usuario
          tiene la oportunidad de robar cartas (es decir, de "golpear"). El bucle while termina
          cuando el usuario elige "Stand". Si el usuario supera los 21,
          el usuario pierde inmediatamente.*/
        while (true) {
            //Mostrar tarjetas de usuario, y dejar que el usuario decida golpear o pararse. 
            System.out.println();
            System.out.println();
            System.out.println("Tus cartas son: ");
            for (int i = 0; i < manoUsuario.getCantidadCartas(); i++) {
                System.out.println("      " + manoUsuario.getCarta(i));
            }
            System.out.println("Su total es " + manoUsuario.getValorBlackJack());
            System.out.println();
            System.out.println("distribuidor está mostrando el " + manoDistribuidor.getCarta(0));
            System.out.println();
            System.out.println("Golpear (G) o Levantarse (L) ?");
            char accionUsuario = 'H'; // Respuesta del usuario, 'H' o 'S'
            do {
                Scanner entrada = new Scanner(System.in);
                accionUsuario = entrada.nextLine().charAt(0);
                accionUsuario=Character.toUpperCase(accionUsuario);
                if (accionUsuario != 'G' && accionUsuario != 'L') {
                    System.out.println("Por favor responda G o L:  ");
                }
            } while (accionUsuario != 'G' && accionUsuario != 'L');
            /*Si el usuario tiene éxito, el usuario obtiene una tarjeta. Si el usuario está parado,
              el bucle termina (y es el turno del crupier de robar cartas)*/
            if (accionUsuario == 'L') {
                //Finaliza el ciclo; el usuario ha terminado de tomar cartas.
                break;
            } else {
                //userAction es 'G'. Dale una tarjeta al usuario.  
                // Si el usuario supera los 21, el usuario pierde.
                Card nuevaCarta = deck.cartadeOferta();
                manoUsuario.añadirCarta(nuevaCarta);
                System.out.println();
                System.out.println("El usuario acierta");
                System.out.println("Su tarjeta es la " + nuevaCarta);
                System.out.println("Su total es ahora " + manoUsuario.getValorBlackJack());
                if (manoUsuario.getValorBlackJack() > 21) {
                    System.out.println();
                    System.out.println("Has fallado al pasar de 21. Usted Pierde");
                    System.out.println("Otra tarjeta del distribuidor fue el " + manoDistribuidor.getCarta(1));
                    return false;
                }
            }
        }//fin del While
        /* Si llegamos a este punto, el usuario tiene valor de 21 o menos. Ahora es
         la oportunidad del distribuidor para dibujar. El distribuidor roba cartas hasta que el crupier
         el total es> 16. Si el crupier supera los 21, el crupier pierde.*/
        System.out.println();
        System.out.println("Usuario parado");
        System.out.println("Las tarjetas del distribuidor son");
        System.out.println("     " + manoDistribuidor.getCarta(0));
        System.out.println("     " + manoDistribuidor.getCarta(1));
        while (manoDistribuidor.getValorBlackJack() <= 16) {
            Card nuevaCarta = deck.cartadeOferta();
            System.out.println("El distribuidor golpea y obtiene la " + nuevaCarta);
            manoDistribuidor.añadirCarta(nuevaCarta);
            if (manoDistribuidor.getValorBlackJack() > 21) {
                System.out.println();
                System.out.println("Distribuidor detenido por pasar de 21. Usted gana");
                return true;
            }
        }
        System.out.println("El total del distribuidor es " + manoDistribuidor.getValorBlackJack());
        /* Si llegamos a este punto, ambos jugadores tienen 21 o menos. Nosotros
         puede determinar el ganador comparando los valores de sus manos*/
        System.out.println();
        if (manoDistribuidor.getValorBlackJack() == manoDistribuidor.getValorBlackJack()) {
            System.out.println("El distribuidor gana en un empate. Pierdes");
            return false;
        } else if (manoDistribuidor.getValorBlackJack() > manoUsuario.getValorBlackJack()) {
            System.out.println("El distribuidor gana " + manoDistribuidor.getValorBlackJack() + " tu puntuacion " + manoUsuario.getValorBlackJack() + " . ");
            return false;
        } else {
            System.out.println("Usted gana " + manoUsuario.getValorBlackJack() + " puntuacion del crupier " + manoDistribuidor + " . ");
            return true;
        }

    }
}
