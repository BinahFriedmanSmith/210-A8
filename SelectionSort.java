import java.util.Collections;
import java.util.ListIterator;

public class SelectionSort {
  
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    
    // register the starting configuration with the recorder
    record.add(unsorted);

    // Here is the result list you will be creating
    CardPile sorted = new CardPile();
    
    Card min;   //initialize variable to store minimum

    // part of old ver (with more traversal)
    //   for (Card c : unsorted){
    //     if (c.compareTo(min) < 0) {
    //     min = c;
    //     }
    //   }

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
      
      record.next();        // tell recorder this is a new step
      record.add(sorted);   // add sorted pile
      record.add(unsorted); // add unsorted pile
    }

    // return the sorted result here
    return sorted;
  }

    /** Starts the program running */
  public static void main(String args[]) {

    // set up a class to record and display the sorting results
    SortRecorder recorder = new SortRecorder();

    // set up the deck of cards
    Card.loadImages(recorder);
    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);

    // for debugging purposes, uncomment this to
    // work with a smaller number of cards:
    // cards = cards.split(cards.get(39));

    // mix up the cards
    Collections.shuffle(cards);

    //call sorting algorithm
    cards = SelectionSort.sort(cards, recorder);

    //print out the sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: SelectionSort");
  }
}
