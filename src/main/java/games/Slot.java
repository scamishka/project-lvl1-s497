package games;
import static java.lang.Math.random;
import static java.lang.Math.round;

public class Slot {

    public static void main(String... __) { 

        int capital = 100;
        int stavka = 10;
        int winSumma = 1000;
        int firstCounter = 0;
        int secondCounter = 0;
        int thirdCounter = 0;
        int size = 7;



        for (int i = capital; i >= 0; i -= stavka) {

            System.out.println("У Вас " + capital + "$, ставка - " + stavka + "$");
            System.out.println("Крутим барабаны!Розыгрыш принёс следующие результаты:");
            firstCounter = (firstCounter + (int) round(random() * 100)) % size;
            secondCounter = (secondCounter + (int) round(random() * 100)) % size;
            thirdCounter = (thirdCounter + (int) round(random() * 100)) % size;
            System.out.println("первый барабан - " + firstCounter + " второй барабан - " + secondCounter + " третий барабан -" + thirdCounter);
            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                capital += winSumma;
                i = capital;
                System.out.println("Выйгрыш " + winSumma + "$, ваш капитал теперь составляет: " + capital + "$");
            }
            else {
                capital -= stavka;
                i = capital;
                System.out.println("Проигрыш " + stavka + "$, ваш капитал теперь составляет: " + capital + "$");
            }

        }

    }
}
