import java.util.ArrayDeque;
import java.util.Collections;

public class MergeSort {
  
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    
    ArrayDeque<CardPile> queue = new ArrayDeque<CardPile>(); //create a pile of piles
    for (Card c : unsorted){ //make every card into its own pile
      queue.addFirst(new CardPile());
      queue.peek().add(c);
    }
   
    for (CardPile pile: queue) { // add all piles
      record.add(pile);
    }
    CardPile list1; //initialize variables for sorting
    CardPile list2;
    while (queue.peekFirst() != queue.peekLast()) { //while the queue has multiple piles
      list1 = queue.remove();
      list2 = queue.remove();
      queue.addLast(MergeSort.merge(list1,list2));
      record.next();        // tell recorder this is a new step
      for (CardPile pile: queue) { // add all piles
        record.add(pile);
      }
    }
    
    // ***********************************************************
    // Here is where you'll do the "work" of MergeSort:
    //   - Use queue to store the intermediate piles
    //   - Don't forget to register the new state with the
    //     recorder after each merge step:
    //        record.next();        // tell it this is a new step
    //        for (CardPile pile: queue) { // add all piles
    //           record.add(pile);
    //        }
    // ***********************************************************

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

    // set up a class to record and display the sorting results
    SortRecorder recorder = new SortRecorder();

    // set up the deck of cards
    Card.loadImages(recorder);
    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);

    // for debugging purposes, uncomment this to
    // work with a smaller number of cards:
    //cards = cards.split(cards.get(39));

    // mix up the cards
    Collections.shuffle(cards);

    // call sorting algorithm
    cards = MergeSort.sort(cards, recorder);

    // print out the sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: MergeSort");
  }
}
