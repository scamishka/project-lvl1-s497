package games;

import java.io.IOException;
import static games.Choice.getCharacterFromUser;

public class BlackJack {
    private static int[] cards; // Основная колода
    private static int cursor; // Счётчик карт основной колоды

    private static int[][] playersCards; // карты игроков. Первый индекс - номер игрока
    private static int[] playersCursors; // курсоры карт игроков. Индекс - номер игрока

    private static int[] playersMoney = {100, 100};
    private static final int MAX_VALUE = 21;
    private static final int MAX_CARDS_COUNT = 8;
    private static final int rate = 10;

    public static void main(String... __) throws IOException {

        while ((playersMoney[0] > 0) && (playersMoney[1] > 0)) {
            initRound();
            int nextCard;
            nextCard = addCard2Player(0);
            System.out.println("Вам выпала карта " + CardUtils.toString(nextCard));
            nextCard = addCard2Player(0);
            System.out.println("Вам выпала карта " + CardUtils.toString(nextCard));

            for (int i = 2; i < MAX_CARDS_COUNT && sum(0) < 20; i++) {
                if (!confirm("Берём карту?")) {
                    break;
                }
                nextCard = addCard2Player(0);
                System.out.println("Вам выпала карта " + CardUtils.toString(nextCard));
                System.out.println("Ваша сумма " + sum(0));
            }

            System.out.printf("Ход робота\n");
            nextCard = addCard2Player(1);
            System.out.println("Роботу выпала карта " + CardUtils.toString(nextCard));
            nextCard = addCard2Player(1);
            System.out.println("Роботу выпала карта " + CardUtils.toString(nextCard));
            System.out.println(sum(1));

            for (int i = 2; (i < MAX_CARDS_COUNT && sum(1) < 17); i++) {
                System.out.println("Робот решил взять ещё\n");
                nextCard = addCard2Player(1);
                System.out.println("Роботу выпала карта " + CardUtils.toString(nextCard));
            }

            System.out.printf("Сумма ваших очков - %d Сумма робота - %d\n", getFinalSum(0), getFinalSum(1));
            if (getFinalSum(0) == 0 && getFinalSum(1) == 0) {
                System.out.println("Победитель отсутствует. Ваши деньги остаются при вас.");
            } else if (getFinalSum(0) > getFinalSum(1)) {
                playersMoney[0] = playersMoney[0] + rate;
                playersMoney[1] = playersMoney[1] - rate;
                System.out.printf("Вы выйграли раунд! Получаете %d$\n", rate);
            } else if (getFinalSum(0) < getFinalSum(1)) {
                playersMoney[0] = playersMoney[0] - rate;
                playersMoney[1] = playersMoney[1] + rate;
                System.out.printf("Вы проиграли раунд! Теряете %d$\n", rate);
            } else
                System.out.println("Ничья! Ваши деньги остаются при вас.");

        }
        if (playersMoney[0] > 0)
            System.out.println("Вы выиграли! Поздравляем!");
        else
            System.out.println("Вы проиграли. Соболезнуем...");

    }

    private static int value(int card) {
        switch (CardUtils.getPar(card)) {
            case JACK:
                return 2;
            case QUEEN:
                return 3;
            case KING:
                return 4;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
                return 10;
            case ACE:
            default:
                return 11;
        }
    }

    private static void initRound() {
        System.out.printf("\nУ Вас %d$, у робота - %d$. Начинаем новый раунд!\n", playersMoney[0], playersMoney[1]);
        cards = CardUtils.getShuffleCards();
        playersCards = new int[2][MAX_CARDS_COUNT];
        playersCursors = new int[2];
        cursor = 0;
    }

    private static int addCard2Player(int playerIndex) {
        int nextCard = cards[cursor];
        playersCards[playerIndex][playersCursors[playerIndex]] = nextCard;
        playersCursors[playerIndex] = playersCursors[playerIndex] + 1;
        cursor = cursor + 1;
        return nextCard;
    }

    static int sum(int playerIndex) {

        int result = 0;
        for (int i = 0; i < playersCursors[playerIndex]; i++) {
            result += value(playersCards[playerIndex][i]);
        }
        return result;
    }

    private static int getFinalSum(int playerIndex) {
        return (sum(playerIndex) <= 21) ? sum(playerIndex) : 0;
    }

    private static boolean confirm(String message) throws IOException {
        System.out.println(message + " \"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)");
        switch (getCharacterFromUser()) {
            case 'Y':
            case 'y':
                return true;
            default:
                return false;
        }
    }
}
