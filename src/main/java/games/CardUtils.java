package games;

import org.apache.commons.math3.util.MathArrays;

public class CardUtils {
    public static final int PARS_TOTAL_COUNT = Par.values().length; //9
    public static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36

    enum Suit {
        SPADES, // пики
        HEARTS, // червы
        CLUBS, // трефы
        DIAMONDS // бубны
    }

    public static Suit getSuit(int cardNumber) {
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

    public static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    public static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

    public static int[] cardbatch() {
        int [] cards = new int [36];
        for (int i = 0; i < CARDS_TOTAL_COUNT; i++) {
            cards[i] = i;
        }
        MathArrays.shuffle(cards);
        return cards;
    }
}
