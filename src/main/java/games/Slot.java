package games;
import static java.lang.Math.random;
import static java.lang.Math.round;

public class Slot {

    public static void main(String... __) { 

        int capital = 100;
        int stavka = 10;
        int winSumma = 1_000;
        int firstCounter = 0;
        int secondCounter = 0;
        int thirdCounter = 0;
        int size = 7;



        for (int i = capital; i >= 0; i -= stavka) {

            System.out.printf("У Вас %,d$, ставка - %d$", capital, stavka);
            System.out.println();
            System.out.println("Крутим барабаны!Розыгрыш принёс следующие результаты:");
            firstCounter = (firstCounter + (int) round(random() * 100)) % size;
            secondCounter = (secondCounter + (int) round(random() * 100)) % size;
            thirdCounter = (thirdCounter + (int) round(random() * 100)) % size;
            System.out.printf("первый барабан - %d второй барабан - %d третий барабан - %d", firstCounter, secondCounter, thirdCounter);
            System.out.println();
            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                capital += winSumma;
                i = capital;
                System.out.printf("Выйгрыш %,d$, ваш капитал теперь составляет: %,d$", winSumma, capital);
                System.out.println();
            }
            else {
                capital -= stavka;
                i = capital;
                System.out.printf("Проигрыш %d$, ваш капитал теперь составляет: %,d$", stavka, capital);
                System.out.println();
            }

        }

    }
}

