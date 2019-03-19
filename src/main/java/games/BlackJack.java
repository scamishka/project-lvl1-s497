package games;

import org.slf4j.Logger;

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
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlackJack.class);

    public static void main(String... __) throws IOException {

        while ((playersMoney[0] > 0) && (playersMoney[1] > 0)) {
            initRound();
            int nextCard;
            
            log.info("Ваш ход");
            setPlayPLayer(0);

            log.info("Ход робота");
            setPlayPLayer(1);
         
            log.info("Сумма ваших очков - {} Сумма робота - {}", getFinalSum(0), getFinalSum(1));
            if (getFinalSum(0) == 0 && getFinalSum(1) == 0) {
                log.info("Победитель отсутствует. Ваши деньги остаются при вас.");
            } else if (getFinalSum(0) > getFinalSum(1)) {
                setChangeMoney(0, 1);
                log.info("Вы выйграли раунд! Получаете {}$", rate);
            } else if (getFinalSum(0) < getFinalSum(1)) {
                setChangeMoney(1, 0);
                log.info("Вы проиграли раунд! Теряете {}$", rate);
            } else
                log.info("Ничья! Ваши деньги остаются при вас.");

        }
        if (playersMoney[0] > 0)
            log.info("Вы выиграли! Поздравляем!");
        else
            log.info("Вы проиграли. Соболезнуем...");

    }
    private static void setChangeMoney (int playerIndex1, int playerIndex2) {
        playersMoney[playerIndex1] = playersMoney[playerIndex1] + rate;
        playersMoney[playerIndex2] = playersMoney[playerIndex2] - rate;
    }
    private static void setPlayPLayer (int playerIndex )throws IOException {
        int nextCard;
        int criticalSum = 0;
        switch (playerIndex){
            case 0: criticalSum = 20;
                break;
            case 1: criticalSum = 17;
                break;
            default:
                break;
        }
        for (int i = 0; i < MAX_CARDS_COUNT && sum(playerIndex) < criticalSum; i++) {
            if (i >= 2) {
                log.info("Сумма {}", sum(playerIndex));
                if (playerIndex == 0) {
                    if (!confirm("Берём карту?")) {
                        break;
                    }
                }
            }
            nextCard = addCard2Player(playerIndex);
            log.info("Выпала карта {}", CardUtils.toString(nextCard));
        }
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
        log.info("У Вас {}$, у робота - {}$.\n Начинаем новый раунд!", playersMoney[0], playersMoney[1]);
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
        log.info(message + " \"Y\" - Да, {любой другой символ} - нет (Что бы выйти из игры, нажмите Ctrl + C)");
        switch (getCharacterFromUser()) {
            case 'Y':
            case 'y':
                return true;
            default:
                return false;
        }
    }
}
