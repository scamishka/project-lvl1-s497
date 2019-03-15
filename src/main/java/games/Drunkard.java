package games;

import java.util.Arrays;
import java.util.Collections;

public class Drunkard {
    private static final int PARS_TOTAL_COUNT = Par.values().length; //9
    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36
    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];
    private static int[] playersCardTails = new int[2];
    private static int[] playersCardHeads = new int[2];
    private static int[] allCards = new int[CARDS_TOTAL_COUNT];

    public static void main(String... __) {

//        System.out.println("Масть 36-й карты - " + getSuit(35));
//        System.out.println("Размерность 36-й карты - " + getPar(35));
//        System.out.println( toString((int) round(random() * 100) % CARDS_TOTAL_COUNT ));

        for (int i = 0; i < allCards.length; i++) {
            allCards[i] = i + 1 ;
            //System.out.println(allCards[i] + " ");

        }

        Collections.shuffle(Arrays.asList(allCards));
        System.out.println(Arrays.toString(allCards));
        System.arraycopy(allCards, 0, playersCards[0], 0, 18);
        System.arraycopy(allCards, 18, playersCards[1], 0, 18);
        System.out.println(Arrays.toString(playersCards[0]));
        System.out.println(Arrays.toString(playersCards[1]));
        System.out.println(Arrays.toString(playersCards));




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


}

