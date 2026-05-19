import java.util.Random;
import java.util.Scanner;


public class Game {
    public static void main(String[] args) {
        int maquina = new Random().nextInt(100);
        System.out.println(maquina);
        Scanner leitor = new Scanner(System.in);

        System.out.println("Digite um numero de 0-100: ");
        int numero = leitor.nextInt();

        while (maquina != numero) {
            System.out.println("Errado! Tente novamente");
            System.out.println("Digite um numero de 0-100: ");
            numero = leitor.nextInt();
            break;
        }
        System.out.println("Parabéns!!! Você adivinhou o número correto");
    }
}
