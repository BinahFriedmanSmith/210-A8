
import java.util.ListIterator;

public class InsertionSortTimer {
  
  public static CardPile sort(CardPile unsorted) {

    // Here is the result list you will be creating
    CardPile sorted = new CardPile();
  
    Card toSort; //initialise storage for the card to sort

    while (unsorted.peek() != null){    //while there are cards left to sort
      toSort = unsorted.peek(); //notes the card we're sorting
      
      if (sorted.peek() == null){ sorted.add(toSort); } //if sorted is empty, add the card
      else{
        ListIterator<Card> i = sorted.listIterator(0); //create an iterator to navigate the list
        while (true){ //iterates until broken when iterator is in the right position       
          if (!i.hasNext()) { //if we reach the end of the sorted list, break loop
            break;
          }
          else{  //if the next card is greater than toSort, go back and break the loop
            if (i.next().compareTo(toSort) > 0) {
              i.previous();             
              break;
            }
          }
        }
        i.add(toSort); // add the card to the location we found
      }

      //remove sorted card from unsorted
      unsorted.remove(toSort);
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
