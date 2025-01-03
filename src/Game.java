package src;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class Game
{
    ArrayList<Player> players;

    int player_idx;

    Stack<Card> deck;

    ArrayList<Card> board_cards;

    Random random;

    long seed;

    public Game()
    {
        deck = new Stack<>();
        seed = Time.valueOf(LocalTime.now()).getTime();
        random = new Random(seed);
        board_cards = new ArrayList<>();

        player_idx = 0;
    }

    public void deal_to_board()
    {
        for(int i = 0; i < 4; ++i)
        {
            Card dealt_card = deck.pop();
            board_cards.add(dealt_card);
        }
    }

    public void deal_to_players()
    {
        // Deal cards out to players
        for (int i = 0; i < 3 && !deck.isEmpty(); ++i)
        {
            for(int p = 0; p < players.size() && !deck.isEmpty(); ++p)
            {
                Card dealt_card = deck.pop();
                players.get(p).draw(dealt_card);
            }
        }
    }

    public void createDeck()
    {
        LinkedList<Card> unshuffled = new LinkedList<>();

        // Add Card to Unshuffled Deck
        for (int i = Card.MIN_VALUE; i < Card.MAX_VALUE; ++i)
        {
            for (int j = 0; j < Card.CardSuit.values().length; ++j)
            {
                unshuffled.add(
                        new Card(i, Card.CardSuit.values()[j])
                );
            }
        }

        // Randomly remove from unshuffled and add to deck
        while (!unshuffled.isEmpty())
        {
            Card popped =
                    unshuffled.remove(random.nextInt(0, unshuffled.size()));

            deck.add(popped);
        }
    }
}
