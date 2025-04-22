import java.util.Collections;

public class Quicksort {
  
  public static CardPile sort(CardPile unsorted, SortRecorder record) {

    if (unsorted.peekFirst() == unsorted.peekLast()) {
      return unsorted; 
    }

    // Here are the two partitions you will be creating
    CardPile smaller = new CardPile();
    CardPile bigger = new CardPile();

    // ***********************************************************
    // Here is where you'll do the partition part of Quicksort:
    //   - Choose a pivot
    //   - Partition the unsorted list into two piles
    // ***********************************************************
    Card pivot = unsorted.remove();  
    while (unsorted.peek() != null){
      if (unsorted.peek().compareTo(pivot) < 0){
        smaller.add(unsorted.remove());
      }
      else{
        bigger.add(unsorted.remove());
      }
      //records the splitting process which makes it pretty long idk
      //* add/remove one slash before this line to enable/disable
      record.add(smaller);
      record.add(pivot);
      record.add(bigger);
      record.next();
      //*/
    }
    
    // register the partitions with the recorder
    record.add(smaller);
    record.add(pivot);
    record.add(bigger);
    record.next();

    // This will hold the assembled result
    CardPile result = new CardPile();
    
    smaller = sort(smaller, record);
    bigger = sort(bigger, record);
    for (Card c : smaller){ result.add(c); }
    result.add(pivot);
    for (Card c : bigger){ result.add(c); }

    // record the sorted result
    record.add(result);
    record.next();
    
    // return the sorted result here
    return result;
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

    //call sorting method
    cards = Quicksort.sort(cards, recorder);

    // print out the sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: Quicksort");
  }
}
