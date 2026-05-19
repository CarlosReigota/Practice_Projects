import java.util.Scanner;

public class Main {
     static void main() {
    Scanner input = new Scanner(System.in);
    System.out.println("Digite seu saldo: ");
    int saldo = input.nextInt();
    double reajuste = saldo * 0.01;
    System.out.println(reajuste);

    }
}
