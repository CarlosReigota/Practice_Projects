import java.util.Scanner;

public class sucessoranterior {
     static void main() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite um numero: ");
        int numero = input.nextInt();
        int ant = numero - 1;
        int suc = numero + 1;
        System.out.println(ant);
        System.out.println(numero);
        System.out.println(suc);
    }
}
