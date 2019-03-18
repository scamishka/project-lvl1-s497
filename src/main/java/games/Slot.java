package games;
import static java.lang.Math.random;
import static java.lang.Math.round;

public class Slot {

    public static void main(String... __) { 

        int capital = 100;
        int rate = 10;
        int winSumma = 1_000;
        int firstCounter = 0;
        int secondCounter = 0;
        int thirdCounter = 0;
        int size = 7;

        do {

            System.out.printf("У Вас %,d$, ставка - %d$\n", capital, rate);
            System.out.println("Крутим барабаны!Розыгрыш принёс следующие результаты:");
            firstCounter = (firstCounter + (int) round(random() * 100)) % size;
            secondCounter = (secondCounter + (int) round(random() * 100)) % size;
            thirdCounter = (thirdCounter + (int) round(random() * 100)) % size;
            System.out.printf("первый барабан - %d второй барабан - %d третий барабан - %d\n", firstCounter, secondCounter, thirdCounter);
            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                capital = capital + winSumma;
                System.out.printf("Выйгрыш %,d$, ваш капитал теперь составляет: %,d$\n", winSumma, capital);
            }
            else {
                capital = capital - rate;
                System.out.printf("Проигрыш %d$, ваш капитал теперь составляет: %,d$\n", rate, capital);
            }
        }
    while (capital > 0);
    }
}

