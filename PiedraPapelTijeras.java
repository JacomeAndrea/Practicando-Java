import java.util.Locale;
import java.util.Scanner;

public class PiedraPapelTijeras {


    static Scanner sc;
    static final int PIEDRA = 1;
    static final int PAPEL = 2;
    static final int TIJERAS = 3;

    static final int EMPATADO = 0;
    static final int GANADO = 1;
    static final int PERDIDO = 2;
    static final int ERROR = 3;

    static int contadorUsuario = 0;
    static int contadorOrdenador = 0;

    public static void main(String[] args) {

        String continuar;
        sc = new Scanner(System.in);

        do {

            // Obtengo el valor del usuario mediante un método
            int valorUsuario = leerNumeroUsuario();
            int valorOrdenador;

            // La mitad de las veces hace trampa
            if (Math.random() > 0.5) {
                valorOrdenador = generarValorAleatorio();
            } else {
                valorOrdenador = trampas(valorUsuario);
            }

            // Muestro los valores elegidos y quién es el ganador
            System.out.println(" Usuario  :" + convertirNumJugadaANombre(valorUsuario));
            System.out.println(" Ordenador:" + convertirNumJugadaANombre(valorOrdenador));


            int ganador = calcularGanador(valorUsuario, valorOrdenador);
            mostrarGanador(ganador);
            System.out.print("\n ¿Otra Partidita? (s/n):");
            continuar = String.valueOf(sc.next().charAt(0));

        } while ((continuar.toLowerCase(Locale.ROOT).equals("s")));

        // Muestro el resultado final
        mostrarPuntuaciones();
        System.out.println("Fin de la partida.");
        sc.close();

    }


    private static void mostrarPuntuaciones() {
        System.out.println("Partidas ganadas por el ordenador :" + contadorOrdenador);
        System.out.println("Partidas ganadas por el usuario   :" + contadorUsuario);
    }

    private static void mostrarGanador(int ganador) {
        switch (ganador) {
            case EMPATADO -> System.out.println("Empate.");
            case GANADO -> {
                System.out.println(" Gana el Usuario.");
                contadorUsuario++;
            }
            case PERDIDO -> {
                System.out.println(" Gana el Ordenador");
                contadorOrdenador++;
            }
            case ERROR -> System.out.println();
        }
    }

    /*
     *  Leer la opción de usuario, solo admite valores correctos: 1,2 o 3
     */
    static int leerNumeroUsuario() { //TODO: INTENTARO CON TRY CATCH POR SI EL USUARIO INTRODUCE ALGO DISTINTO A UN NUM
        int valorUsuario;
        try {
            System.out.println("Escriba un número entre el 1 y el 3");
            valorUsuario = sc.nextInt();
            if (valorUsuario>3 || valorUsuario<0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.err.println("ERROR. Debe introducir un número entre el 1 y el 3.");
        }
        return ERROR;
    }



    static String convertirNumJugadaANombre(int jugada) {
        switch (jugada) {
            case 1 -> {
                return "Piedra";
            }
            case 2 -> {
                return "Papel";
            }
            case 3 -> {
                return "Tijera";
            }
            default -> {
                return "Error";
            }

        }
    }


    static int calcularGanador(int jugador, int ordenador) {
        if (jugador == ordenador) return EMPATADO;
        if (jugador == PIEDRA && ordenador == TIJERAS) return GANADO;
        if (ordenador == PIEDRA && jugador == TIJERAS) return PERDIDO;
        if (jugador == PAPEL && ordenador == PIEDRA) return GANADO;
        if (ordenador == PAPEL && jugador == PIEDRA) return PERDIDO;
        if (jugador == TIJERAS && ordenador == PAPEL) return GANADO;
        if (ordenador == TIJERAS && jugador == PAPEL) return PERDIDO;

        return ERROR;
    }

    /*
     * Genera aleatoriamente un valor entre 1 y 3 (PIEDRA, PAPEL o TIJERAS)
     */
    static int generarValorAleatorio() {
        int max = 3, min = 1;
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }


    static int trampas(int valorUsuario) {
        if (valorUsuario == 1) {
            return PAPEL;
        }
        if (valorUsuario == 2) {
            return TIJERAS;
        }
        return PIEDRA;
    }
}
