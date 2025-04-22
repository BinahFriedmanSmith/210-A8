import java.util.ArrayDeque;

public class MergeSortTimer {
  
  public static CardPile sort(CardPile unsorted) {
    
    ArrayDeque<CardPile> queue = new ArrayDeque<CardPile>(); //create a pile of piles
    for (Card c : unsorted){ //make every card into its own pile
      queue.addFirst(new CardPile());
      queue.peek().add(c);
    }
    CardPile list1; //initialize variables for sorting
    CardPile list2;
    while (queue.peekFirst() != queue.peekLast()) { //while the queue has multiple piles
      list1 = queue.remove();
      list2 = queue.remove();
      queue.addLast(MergeSortTimer.merge(list1,list2));
    }

    // return the sorted result here
    return queue.remove();
  }

  public static CardPile merge(CardPile l1, CardPile l2) {
    CardPile merged = new CardPile();
    while (l1.peek() != null && l2.peek() != null){ //while neither list is empty
      if (l1.peek().compareTo(l2.peek()) > 0){ //if the first of l2 is smaller
        merged.add(l2.remove()); //take off the first in l2 and append it to merged
      }
      else{
        merged.add(l1.remove());
      }
    }
    //only one of these will add cards but we dont know which one so it works just to run both. 
    //if statements would just make it more complex i think
    for (Card c : l1){ //add any remaining cards in l1
      merged.add(c);
    }
    for (Card c : l2){ //add any remaining cards in l1
      merged.add(c);
    }
    return merged;
  }

  /** Starts the program running */
  public static void main(String args[]) {
    
    if (args.length<1) {
      System.err.println("Please specify how many cards to sort!");
    } else {
      Card[] deck = Card.newDeck(true);
      CardPile cards = new CardPile();
      
      for (int i = 0; i<Integer.parseInt(args[0]); i++ ) {
        cards.add(deck[(int)(52*Math.random())]);
      }

      sort(cards);
      
    }
  }
}
