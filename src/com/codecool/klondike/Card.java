package com.codecool.klondike;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import com.codecool.klondike.Suit;
import com.codecool.klondike.Rank;
import java.util.*;

public class Card extends ImageView {

    private Suit suit;
    private Rank rank;
    private boolean faceDown;

    private Image backFace;
    private Image frontFace;
    private Pile containingPile;
    private DropShadow dropShadow;

    static Image cardBackImage;
    private static final Map<String, Image> cardFaceImages = new HashMap<>();
    public static final int WIDTH = 150;
    public static final int HEIGHT = 215;

    public Card(Suit suit, Rank rank, boolean faceDown) {
        this.suit = suit;
        this.rank = rank;
        this.faceDown = faceDown;
        this.dropShadow = new DropShadow(2, Color.gray(0, 0.75));
        backFace = cardBackImage;
        frontFace = cardFaceImages.get(getShortName());
        setImage(faceDown ? backFace : frontFace);
        setEffect(dropShadow);
    }

    public Suit getSuit() {return suit;}

    public Rank getRank() {
        return rank;
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public String getShortName() {
        return "S" + suit + "R" + rank;
    }

    public DropShadow getDropShadow() {
        return dropShadow;
    }

    public Pile getContainingPile() {
        return containingPile;
    }

    public void setContainingPile(Pile containingPile) {
        this.containingPile = containingPile;
    }

    public void moveToPile(Pile destPile) {
        this.getContainingPile().getCards().remove(this);
        destPile.addCard(this);
    }

    public void flip() {
        faceDown = !faceDown;
        setImage(faceDown ? backFace : frontFace);
    }

    @Override
    public String toString() {
        return "The " + "Rank " + rank + " of " + "Suit " + suit;
    }

    public static boolean isOppositeColor(Card card1, Card card2) {
        //TODO
        switch (card1.getSuit()) {
            case HEARTS:
            case DIAMONDS:
                if (card2.getSuit() == Suit.SPADES || card2.getSuit() == Suit.CLUBS) return true;
                break;
            case SPADES:
            case CLUBS:
                if (card2.getSuit() == Suit.HEARTS || card2.getSuit() == Suit.DIAMONDS ) return true;
                break;
        }
        return false;
    }

    public static boolean isSameSuit(Card card1, Card card2) {
        return card1.getSuit() == card2.getSuit();
    }

    public static List<Card> createNewDeck() {
        List<Card> result = new ArrayList<>();
        for (Suit suit:Suit.values()) {
            for (Rank rank:Rank.values()) {
                result.add(new Card(suit, rank, true));
            }
        }
        Collections.shuffle(result);
        return result;
    }

    public static void loadCardImages(String cardBackTheme) {
        cardBackImage = new Image(cardBackTheme);
        String suitName = "";
        for (Suit suit:Suit.values()) {
            switch (suit) {
                case HEARTS:
                    suitName = "hearts";
                    break;
                case DIAMONDS:
                    suitName = "diamonds";
                    break;
                case SPADES:
                    suitName = "spades";
                    break;
                case CLUBS:
                    suitName = "clubs";
                    break;
            }
            for (Rank rank:Rank.values()) {
                String cardName = suitName + rank.getValue();
                String cardId = "S" + suit + "R" + rank;
                String imageFileName = "card_images/" + cardName + ".png";
                cardFaceImages.put(cardId, new Image(imageFileName));
            }
        }
    }

}
