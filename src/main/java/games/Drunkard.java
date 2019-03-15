package games;

import org.apache.commons.math3.util.MathArrays;

import java.util.Arrays;
import java.util.Collections;

public class Drunkard {
    private static final int PARS_TOTAL_COUNT = Par.values().length; //9
    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36
    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT +1];
    private static int[] playersCardTails = new int[] {0, 0};
    private static int[] playersCardHeads = new int[] {18, 18};
    private static int[] allCards = new int[CARDS_TOTAL_COUNT];
    private static int count = 0;
    private static int playerIndex;

    public static void main(String... __) {

        for (int i = 0; i < allCards.length; i++) {
            allCards[i] = i ;
        }

        shuffleKoloda(allCards);

        do {
            count = count + 1;
            int card1 = (playersCards[0][playersCardTails[0]])%9;
            int card2 = (playersCards[1][playersCardTails[1]])%9;

            System.out.printf("Итерация №%d Игрок №1 карта: %s; Игрок №2 карта: %s%n", count, toString(card1),toString(card2));

            if (card1 > card2) {
                System.out.println("Выйграл Игрок №1!");
                playerIndex = 0;
                checkCard(playerIndex);
                checkTail();
                checkHead(playerIndex);
                System.out.printf("У Игрока №1 %d, у игрока №2 %d карт %n", Cards(0), Cards(1));

            } else if (card1 < card2) {
                System.out.println("Выйграл Игрок №2!");
                playerIndex = 1;
                checkCard(playerIndex);
                checkTail();
                checkHead(playerIndex);

                System.out.printf("У Игрока №1 %d карт, у игрока №2 %d карт %n", Cards(0), Cards(1));
            }
            else {
                System.out.println("Ничья!Все остаются при своих!");

                playersCards[0][playersCardHeads[0]] = playersCards[0][playersCardTails[0]];
                playersCards[1][playersCardHeads[1]] = playersCards[1][playersCardTails[0]];
                checkTail();
                playersCardHeads[0] = incrementIndex(playersCardHeads[0]);
                playersCardHeads[1] = incrementIndex(playersCardHeads[1]);

                System.out.printf("У Игрока №1 %d, у игрока №2 %d карт %n", Cards(0), Cards(1));

            }
            count++;
        } while (!playerCardsIsEmpty(0) || !playerCardsIsEmpty(1));

    }

    private static int [] shuffleKoloda (int[] arr) {

        MathArrays.shuffle(arr);
        System.arraycopy(arr, 0, playersCards[0], 0, CARDS_TOTAL_COUNT/2);
        System.arraycopy(arr, CARDS_TOTAL_COUNT/2, playersCards[1], 0, CARDS_TOTAL_COUNT/2);
        return arr;
    }


    enum Suit {
        SPADES, // пики
        HEARTS, // червы
        CLUBS, // трефы
        DIAMONDS // бубны
    }
    private static Suit getSuit(int cardNumber) {

        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    enum Par {
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK, // Валет
        QUEEN, // Дама
        KING, // Король
        ACE // Туз
    }

    private static Par getPar(int cardNumber) {

        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    private static int incrementIndex(int i) {

        return (i + 1) % CARDS_TOTAL_COUNT;
    }

    private static boolean playerCardsIsEmpty(int playerIndex) {
        int tail = playersCardTails[playerIndex];
        int head = playersCardHeads[playerIndex];
        return tail == head;
    }

    private static String toString(int cardNumber) {

        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

    private static void checkTail () {
        playersCardTails[0] = incrementIndex(playersCardTails[0]);
        playersCardTails[1] = incrementIndex(playersCardTails[1]);

    }

    private static void checkHead (int playerIndex) {
        playersCardHeads[playerIndex] = incrementIndex(playersCardHeads[playerIndex]+1);
    }

    private static void checkCard (int playerIndex) {
        playersCards[playerIndex][playersCardHeads[playerIndex]] = playersCards[0][playersCardTails[0]];
        playersCards[playerIndex][playersCardHeads[playerIndex]+1] = playersCards[1][playersCardTails[0]];
    }

    private static int Cards (int playerIndex) {
        if (playersCardHeads[playerIndex] < playersCardTails[playerIndex])
            return (CARDS_TOTAL_COUNT + playersCardHeads[playerIndex] - playersCardTails[playerIndex]);
        else
            return (playersCardHeads[playerIndex] - playersCardTails[playerIndex]);
    }

}

