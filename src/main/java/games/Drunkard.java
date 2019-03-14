package games;

public class Drunkard {

    public static void main(String... __) {
         System.out.println(toString(35));
    }
    enum Suit {
        SPADES, // пики
        HEARTS, // червы
        CLUBS, // трефы
        DIAMONDS // бубны
    }
    private static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / 9];
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
        return Par.values()[cardNumber % 9];
    }
    private static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }
}
