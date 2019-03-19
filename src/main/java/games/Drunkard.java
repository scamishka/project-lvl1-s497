package games;

import org.slf4j.Logger;

import static games.CardUtils.*;

public class Drunkard {

    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT + 1];
    private static int[] playersCardTails = new int[2];
    private static int[] playersCardHeads = {CARDS_TOTAL_COUNT / 2, CARDS_TOTAL_COUNT / 2}; //18
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Drunkard.class);

    public static void main(String... __) {
        int count = 0;
        cardsAddPlayers(CardUtils.getShuffleCards());

        while (getLostPlayer() == 0) {
            count++;
            int card1 = getCardFromPlayer(0);
            int card2 = getCardFromPlayer(1);
            log.info("Итерация №{}\n Игрок №1 карта: {}; Игрок №2 карта: {}", count, CardUtils.toString(card1), CardUtils.toString(card2));


            if (getWinnerPlayer(card1, card2) == 0) {
                addCards2Player(0, card1);
                addCards2Player(1, card2);
                checkTail();
                log.info("Ничья!Все остаются при своих!");
                log.info("У Игрока №1 {}, у игрока №2 {} карт", playerCardsLength(0), playerCardsLength(1));
            } else if (getWinnerPlayer(card1, card2) > 0) {
                addCards2Player(0, card1, card2);
                checkTail();
                log.info("Выйграл Игрок №1!");
                log.info("У Игрока №1 {}, у игрока №2 {} карт ", playerCardsLength(0), playerCardsLength(1));

            } else {
                addCards2Player(1, card1, card2);
                checkTail();
                log.info("Выйграл Игрок №2!");
                log.info("У Игрока №1 {}, у игрока №2 {} карт ", playerCardsLength(0), playerCardsLength(1));
            }
        }
        log.info("Победитель игрок №{}", getLostPlayer() == 1 ? "2" : "1");
    }

    private static int getWinnerPlayer(int card1, int card2) {

        int result = card1 % PARS_TOTAL_COUNT - card2 % PARS_TOTAL_COUNT;
        return Math.abs(result) == 8 ? -result : result;
    }

    private static int getLostPlayer() {
        int tailPlayer1 = playersCardTails[0];
        int headPlayer1 = playersCardHeads[0];
        int tailPlayer2 = playersCardTails[1];
        int headPlayer2 = playersCardHeads[1];

        if (tailPlayer1 == headPlayer1)
            return 1;
        if (tailPlayer2 == headPlayer2)
            return -1;
        else
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

    private static void addCards2Player(int playerIndex, int... cards) {
        int[] playerCards = playersCards[playerIndex];
        int end = playersCardHeads[playerIndex];

        for (int card : cards) {
            playerCards[end] = card;
            playersCardHeads[playerIndex] = incrementIndex(end);
            end = playersCardHeads[playerIndex];
        }
    }

    private static int incrementIndex(int i) {
        return (i + 1) % (CARDS_TOTAL_COUNT + 1);
    }

    private static int[] cardsAddPlayers(int[] arr) {
        System.arraycopy(arr, 0, playersCards[0], 0, CARDS_TOTAL_COUNT / 2);
        System.arraycopy(arr, CARDS_TOTAL_COUNT / 2, playersCards[1], 0, CARDS_TOTAL_COUNT / 2);
        return arr;
    }

    private static void checkTail() {
        playersCards[0][playersCardTails[0]] = 0;
        playersCardTails[0] = incrementIndex(playersCardTails[0]);
        playersCards[1][playersCardTails[1]] = 0;
        playersCardTails[1] = incrementIndex(playersCardTails[1]);

    }
}

