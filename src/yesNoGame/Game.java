package yesNoGame;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    static Scanner scanner = new Scanner(System.in);

    public static char getResponse(){
        System.out.println("Ingrese 's' o 'n'");
        char answer = scanner.next(".").charAt(0);
        scanner.nextLine();
        return answer;
    }

    public static void clear(){
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void displayMenu(){
        System.out.print("Presione x para salir");
        System.out.println();
        System.out.println("Ingrese i para imprimir el diagrama de árbol");
        System.out.println();
        System.out.println("Presione cualquier otra tecla para continuar");
        System.out.println();
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException{
        Preguntas game = new Preguntas();

        game.addPreguntaRaiz("Es un mamífero?", "Perro", "Pez");
        Game.clear();
        Game.displayMenu();

        char input = scanner.next(".").charAt(0);
        do{
            if(input == 'i'){
                Preguntas.printBinaryTree(game.preguntaRaiz, 0);
                Game.displayMenu();
                input = scanner.next(".").charAt(0); 
                continue;
            }
            if(!game.preguntaActual.isQuestion){
                System.out.println("Es piensas en " + game.preguntaActual.question);
                input = Game.getResponse();
                if(input == 's'){
                    System.out.println("¡Gané! Logré adivinar");
                    game.startGame();
                    Game.displayMenu();
                    continue;
                }
                Game.addNewPregunta(game);
                game.startGame();
                Game.displayMenu();
                continue;
            }
            System.out.println(game.ask());
            input = Game.getResponse();
            game.sigPregunta(input == 'y');
        } while (input != 'x');
        System.out.println("Fin del juego");
        scanner.close();
    }

    private static void addNewPregunta(Preguntas game){
        System.out.println("Creí que sabía todo...");
        System.out.println("¿Podrías decirme en qué pensabas?");

        String newRespuesta = scanner.nextLine();
        System.out.println("¿Qué pregunta que pueda responderse con 'sí' o 'no' preguntarías para adivinar " + newRespuesta);
        String newPregunta = scanner.nextLine();
        System.out.println("¿Responderías 's' o 'n' para llegar a tu respuesta?");
        char input = Game.getResponse();
        game.agregarPregunta(newPregunta, newRespuesta, input == 'y');
    }
}
