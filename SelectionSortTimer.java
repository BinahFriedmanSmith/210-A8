
import java.util.ListIterator;

public class SelectionSortTimer {
  
  public static CardPile sort(CardPile unsorted) {

    // Here is the result list you will be creating
    CardPile sorted = new CardPile();
    
    Card min;   //initialize variable to store minimum

    while (unsorted.peek() != null){    //while there are cards left to sort
      min = unsorted.remove(); //take first card in unsorted and give to min  
      ListIterator<Card> i = unsorted.listIterator(0); //create an iterator to navigate the list
      while (i.hasNext()) { //if we reach the end of the sorted list, stop
        if (i.next().compareTo(min) < 0) { //if the next is less than min 
          i.previous(); //go back bc add() puts new thing before iterator
          i.add(min); //re-insert min into unsorted
          min = i.next(); //get the new min
          i.remove(); //removes the card we just scanned (the new min)
        }
      }
      //add min to sorted
      sorted.add(min);
    }

    // return the sorted result here
    return sorted;
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
