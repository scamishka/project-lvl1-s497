package games;

import org.apache.commons.math3.util.MathArrays;

import java.util.Arrays;

public class Drunkard {
    private static final int PARS_TOTAL_COUNT = Par.values().length; //9
    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36
    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT + 1];
    private static int[] playersCardTails = new int[2];
    private static int[] playersCardHeads = {CARDS_TOTAL_COUNT / 2, CARDS_TOTAL_COUNT / 2}; //18
    private static int[] allCards = new int[CARDS_TOTAL_COUNT];


    public static void main(String... __) {

        int count = 0;

        cardbatch(allCards);

        while (getLostPlayer() == 0) {
            count++;
            int card1 = getCardFromPlayer(0);
            int card2 = getCardFromPlayer(1);
            System.out.printf("Итерация №%d%n Игрок №1 карта: %s; Игрок №2 карта: %s%n", count, toString(card1), toString(card2));

            switch (getWinnerCard(card1, card2)) {
                case 1:
                    addCards2Player(0, 2);

                    System.out.println("Выйграл Игрок №1!");
                    System.out.printf("У Игрока №1 %d, у игрока №2 %d карт %n %n", playerCardsLength(0), playerCardsLength(1));
                    break;
                case 2:
                    addCards2Player(1, 2);

                    System.out.println("Выйграл Игрок №2!");
                    System.out.printf("У Игрока №1 %d, у игрока №2 %d карт %n %n", playerCardsLength(0), playerCardsLength(1));
                    break;
                case 0:
                    addCards2Player(0, 1);

                    System.out.println("Ничья!Все остаются при своих! ");
                    System.out.printf("У Игрока №1 %d, у игрока №2 %d карт %n %n", playerCardsLength(0), playerCardsLength(1));
                    break;
            }
        }

        if (getLostPlayer() == 1) {
            System.out.println("Победитель игрок №2");
        } else
            System.out.println("Победитель игрок №1");
   }

    private static int getWinnerCard(int card1, int card2) {

        if (getSixAceRule(card1, card2) == 1)
            return 1;
        if (getSixAceRule(card1, card2) == -1)
            return 2;
        else {
            if (getPar(card1).ordinal() > getPar(card2).ordinal()) return 1;
            if (getPar(card1).ordinal() == getPar(card2).ordinal()) return 0;
            else return 2;
        }
    }

    private static int getSixAceRule(int card1, int card2) {
        if (getPar(card1).ordinal() == 0 && getPar(card2).ordinal() == (PARS_TOTAL_COUNT - 1))
            return 1;
        if (getPar(card2).ordinal() == 0 && getPar(card1).ordinal() == (PARS_TOTAL_COUNT - 1))
            return -1;
        else
            return 0;
    }

    private static int getLostPlayer() {
        int tailPlayer1 = playersCardTails[0];
        int headPlayer1 = playersCardHeads[0];

        int tailPlayer2 = playersCardTails[1];
        int headPlayer2 = playersCardHeads[1];

        if (tailPlayer1 == headPlayer1) {
            return 1;
        }
        if (tailPlayer2 == headPlayer2) {
            return -1;
        } else
            return 0;
    }

    private static int playerCardsLength(int playerIndex) {
        int tail = playersCardTails[playerIndex];
        int head = playersCardHeads[playerIndex];
        int total;


        if (head <= tail) {
            total = (head + (CARDS_TOTAL_COUNT + 1) - tail);
        } else
            total = (head - tail);
        if (total == (CARDS_TOTAL_COUNT + 1))
            return 0;
        else
            return total;


    }

    private static int getCardFromPlayer(int playerIndex) {
        int tail = playersCardTails[playerIndex];

        return playersCards[playerIndex][tail];
    }

    private static void addCards2Player(int playerIndex, int varargs) {

        switch (varargs) {
            case 1:
                playersCards[0][playersCardHeads[0]] = getCardFromPlayer(0);
                playersCards[1][playersCardHeads[1]] = getCardFromPlayer(1);
                checkTail();
                playersCardHeads[0] = incrementIndex(playersCardHeads[0]);
                playersCardHeads[1] = incrementIndex(playersCardHeads[1]);
                break;
            case 2:
                playersCards[playerIndex][playersCardHeads[playerIndex]] = getCardFromPlayer(0);
                playersCards[playerIndex][incrementIndex(playersCardHeads[playerIndex])] = getCardFromPlayer(1);

                checkTail();
                playersCardHeads[playerIndex] = incrementIndex(playersCardHeads[playerIndex]);
                playersCardHeads[playerIndex] = incrementIndex(playersCardHeads[playerIndex]);


                break;
            default:
                break;
        }

    }

    private static int incrementIndex(int i) {

        return (i + 1) % (CARDS_TOTAL_COUNT + 1);
    }

    private static int[] cardbatch(int[] arr) {

        for (int i = 0; i < allCards.length; i++) {
            allCards[i] = i;
        }

        MathArrays.shuffle(arr);
        System.arraycopy(arr, 0, playersCards[0], 0, CARDS_TOTAL_COUNT / 2);
        System.arraycopy(arr, CARDS_TOTAL_COUNT / 2, playersCards[1], 0, CARDS_TOTAL_COUNT / 2);
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

    private static String toString(int cardNumber) {

        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

    private static void checkTail() {
        playersCards[0][playersCardTails[0]] = 0;
        playersCardTails[0] = incrementIndex(playersCardTails[0]);
        playersCards[1][playersCardTails[1]] = 0;
        playersCardTails[1] = incrementIndex(playersCardTails[1]);

    }

}

