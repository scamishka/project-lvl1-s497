package games;

import org.apache.commons.math3.util.MathArrays;

import java.util.Arrays;
import java.util.Collections;

public class Drunkard {
    private static final int PARS_TOTAL_COUNT = Par.values().length; //9
    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36
    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];
    private static int[] playersCardTails = new int[] {0, 0};
    private static int[] playersCardHeads = new int[] {18, 18};
    private static int[] allCards = new int[CARDS_TOTAL_COUNT];
    private static int i = 0;

    public static void main(String... __) {

//        System.out.println("Масть 36-й карты - " + getSuit(35));
//        System.out.println("Размерность 36-й карты - " + getPar(35));


        for (int i = 0; i < allCards.length; i++) {
            allCards[i] = i ;
        }
        MathArrays.shuffle(allCards);
        System.arraycopy(allCards, 0, playersCards[0], 0, 18);
        System.arraycopy(allCards, 18, playersCards[1], 0, 18);
        System.out.println(Arrays.toString(playersCards[0]));
        System.out.println(Arrays.toString(playersCards[1]));
       // playersCards[0][21] = 2;
//        System.out.println(playersCards[0][0] + " " + toString(playersCards[0][0]));
//        System.out.println(playersCards[1][0] + " " + toString(playersCards[1][0]));
      //  System.out.println(Arrays.toString(playersCards[0]));
      //  System.out.println(incrementIndex(1));


        while (i < 18) {

            int karta1 = playersCards[0][playersCardTails[0]];
            int karta2 = playersCards[1][playersCardTails[1]];
            int a = karta1 % 9;
            int b = karta2 % 9;

            System.out.println(playersCards[0][playersCardTails[0]] + " " + toString(playersCards[0][playersCardTails[0]]) + " " + a);
            System.out.println(playersCards[1][playersCardTails[1]] + " " + toString(playersCards[1][playersCardTails[1]]) + " " + b);

            int count = incrementIndex(i);
            System.out.printf("Итерация №%d Игрок №1 карта: %s; Игрок №2 карта: %s\n", count, toString(karta1),toString(karta2));

//            System.out.println(36%9);
//            System.out.println(b);
            if (a > b) {
                System.out.println("Выйграл Игрок №1!\n");

                playersCards[0][playersCardHeads[0]] = playersCards[0][playersCardTails[0]];
                playersCards[0][playersCardHeads[0]+1] = playersCards[1][playersCardTails[0]];

                playersCardTails[0] = playersCardTails[0] + 1;
                playersCardTails[1] = playersCardTails[1] + 1;

                playersCardHeads[0] = playersCardHeads[0] + 2;




            } else if (a < b) {
                System.out.println("Выйграл Игрок №2!\n");

                playersCards[1][playersCardHeads[1]] = playersCards[0][playersCardTails[0]];
                playersCards[1][playersCardHeads[1]+1] = playersCards[1][playersCardTails[0]];

                playersCardTails[0] = playersCardTails[0] + 1;
                playersCardTails[1] = playersCardTails[1] + 1;

                playersCardHeads[1] = playersCardHeads[1] + 2;

            }
            else {
                System.out.println("Ничья!Все остаются при своих!\n");
                playersCardTails[0] = playersCardTails[0] + 1;
                playersCardTails[1] = playersCardTails[1] + 1;
            }

            i++;

            System.out.println(Arrays.toString(playersCards[0]));
            System.out.println(Arrays.toString(playersCards[1]));
        }








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

//    private static int winnerPlayer {
//
//    }

}

