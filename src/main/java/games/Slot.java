package games;
import org.slf4j.Logger;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class Slot {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Slot.class);

    public static void main(String... __) { 

        int capital = 100;
        int rate = 10;
        int winSumma = 1_000;
        int firstCounter = 0;
        int secondCounter = 0;
        int thirdCounter = 0;
        int size = 7;

        do {

            log.info("У Вас {}$, ставка - {}$", capital, rate);
            log.info("Крутим барабаны!Розыгрыш принёс следующие результаты:");
            firstCounter = (firstCounter + (int) round(random() * 100)) % size;
            secondCounter = (secondCounter + (int) round(random() * 100)) % size;
            thirdCounter = (thirdCounter + (int) round(random() * 100)) % size;
            log.info("первый барабан - {} второй барабан - {} третий барабан - {}", firstCounter, secondCounter, thirdCounter);
            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                capital = capital + winSumma;
                log.info("Выйгрыш {}$, ваш капитал теперь составляет: {}$", winSumma, capital);
            }
            else {
                capital = capital - rate;
                log.info("Проигрыш {}$, ваш капитал теперь составляет: {}$", rate, capital);
            }
        }
    while (capital > 0);
    }
}

