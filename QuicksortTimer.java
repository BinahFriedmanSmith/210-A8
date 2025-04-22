

public class QuicksortTimer {
  
  public static CardPile sort(CardPile unsorted) {

    if (unsorted.peekFirst() == unsorted.peekLast()) {
      return unsorted; 
    }
    
    CardPile smaller = new CardPile();
    CardPile bigger = new CardPile();

    Card pivot = unsorted.remove();  
    while (unsorted.peek() != null){
      if (unsorted.peek().compareTo(pivot) < 0){
        smaller.add(unsorted.remove());
      }
      else{
        bigger.add(unsorted.remove());
      }
    }

    // This will hold the assembled result
    CardPile result = new CardPile();
    
    smaller = sort(smaller);
    bigger = sort(bigger);
    for (Card c : smaller){ result.add(c); }
    result.add(pivot);
    for (Card c : bigger){ result.add(c); }
    
    // return the sorted result here
    return result;
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
