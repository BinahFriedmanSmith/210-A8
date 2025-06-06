import java.util.Collections;
import java.util.ListIterator;

public class InsertionSort {
  
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    
    // register the starting configuration with the recorder
    record.add(unsorted);

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
      
      record.next();        // tell recorder this is a new step
      record.add(sorted);   // add sorted pile
      record.add(unsorted); // add unsorted pile
    }
    // return the sorted result here
    return sorted;
  }
public static void main(String args[]) {

    // set up a class to record and display the sorting results
    SortRecorder recorder = new SortRecorder();

    // set up the deck of cards
    Card.loadImages(recorder);
    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);

    // mix up the cards
    Collections.shuffle(cards);

    //call sorting method
    cards = InsertionSort.sort(cards, recorder);

    //print out the sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: InsertionSort");
  }
}
