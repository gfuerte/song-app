public class SongApp {

  private Song[] items;
  private int numberOfItems;
  private int[] listens;

  SongApp () {
    items = new Song[5];
    this.listens = new int[5];
    this.numberOfItems = 0;
  }

  SongApp (int capacity) {
      this.items = new Song[capacity];
      this.listens = new int[capacity];
      this.numberOfItems = 0;
  }

  public void addSong (Song m) {
    if(numberOfItems < items.length){
    items[numberOfItems] = m;
    listens[numberOfItems] = 1;
    this.numberOfItems++;
    }
    else{
      if(getNumberOfItems() == listens.length){
int[] newListens = new int[listens.length * 2];
for(int i = 0; i < listens.length; i ++){
newListens[i] = listens[i];
}
listens = newListens;
}
    Song[] newList = new Song[items.length * 2];
    int i;
    for (i=0; i<this.numberOfItems; i++) {
    newList[i] = items[i];
    }
    newList[i] = m;
    this.numberOfItems++;
    items = newList;
  }
}

  public boolean removeSong (Song m) {
    int i;
       for (i=0; i<this.numberOfItems; i++)
           if (items[i].getName() == m.getName())
               break;
       if (i < this.numberOfItems) {
           numberOfItems--;
           for (int j=i; j < numberOfItems; j++) {
               items[j] = items[j+1];
               return true;
          }
        }
    return false;
   }

  public Song[] getSongs () {
     return this.items;
   }

  public int getNumberOfItems () {
     return this.numberOfItems;
   }

  public boolean updateRating (Song m, int rating) {
     for(int i = 0; i < this.numberOfItems; i++){
       if(items[i].getName() == m.getName()){
         items[i].setRating(rating);
         return true;
       }
     }
     return false;
   }

  public void print () {
       for (int i = 0; i < this.numberOfItems; i++) {
         System.out.println(items[i]);
       }
     }

  public Song[] getSongsBySongwriter (String songwriter) {
       int c = 0;
       for(int i = 0; i < this.numberOfItems; i++) {
         for (int j = 0; j < items[i].getNumberOfWriters(); j++ ) {
           if( items[i].getWriterAtIndex(j) == songwriter) {
             c++;
             break;
           }
         }
       }
       Song[] list = new Song[c];
       for(int i = 0, k = 0 ; i < this.numberOfItems; i++) {
         for (int j = 0 ; j < items[i].getNumberOfWriters(); j++ ) {
           if(items[i].getWriterAtIndex(j) == songwriter){
             list[k] = items[i];
             k++;
             break;
           }
         }
       }
       return list;
     }

  public Song[] getSongsByYear (int year) {
      int c = 0;
      for(int i = 0; i < this.numberOfItems; i++){
        if( items[i].getYear() == year) {
          c++;
        }
      }
      Song[] list = new Song[c];
      for(int i = 0, j = 0; i < this.numberOfItems; i++ ){
        if( items[i].getYear() == year ) {
          list[j] = items[i];
          j++;
        }
      }
      return list;
     }

  public Song[] getSongsWithRatingsGreaterThan (int rating) {
       int c = 0;
       for(int i=0; i<this.numberOfItems; i++){
        if( items[i].getRating() > rating){
          c++;
        }
      }
      Song[] list = new Song[c];
      for(int i = 0, j = 0; i < this.numberOfItems; i++){
        if( items[i].getRating() > rating ){
          list[j] = items[i];
          j++;
        }
      }
      return list;
     }

  public void sortByYear () {
       int len = items.length;
          for (int i = 1; i < len; ++i) {
            Song k = items[i];
            int j = i-1;
            while (j >= 0 && items[j].getYear() > k.getYear()) {
              items[j+1] = items[j];
              j = j-1;
             }
           items[j+1] = k;
         }
      }

  public void sortByName () {
    int len = items.length;
    for (int i = 0; i < len-1; i++) {
      int min = i;
      for (int j = i+1; j < len; j++)
        if (items[j].compareTo(items[min]) < 0)
        min = j;
        Song temp = items[min];
        items[min] = items[i];
        items[i] = temp;
    }
  }

  public static Song searchSongByName (String name, Song[] Songs, int l, int r) {
    if (r < l){
      return null;
    }
    if (Songs[l].getName() == name) {
      return Songs[l];
    }
    return searchSongByName(name,Songs, l+1, r);
  }
}
