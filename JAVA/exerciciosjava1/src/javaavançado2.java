import java.util.Scanner;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class javaavançado2 {
    static void main(){
        Scanner input = new Scanner(System.in);
        System.out.print("How many hours worked? ");
        int hoursworked = input.nextInt();
        double mw = 8;
        if (hoursworked > 40);{
         double basepay = 40 * mw;
         int ot = hoursworked - 40;
         double extrapay = 1.5 * ot * mw ;
         double totalpay = basepay + extrapay;
         System.out.printf("the salary is $" + totalpay);
        }
        else{
            System.out.printf("Worked more than 60 hours!");

        }


    }
}
