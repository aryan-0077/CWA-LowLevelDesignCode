package Good_Code.Concrete_Iterators;

import Good_Code.Playlist;
import Good_Code.PlaylistIterator;

public class FavoritesPlaylistIterator implements PlaylistIterator {
    private Playlist playlist;
    private int index;
    public FavoritesPlaylistIterator(Playlist playlist) {
        this.playlist = playlist;
        this.index = 0;
    }
    @Override
    public boolean hasNext() {
        // Only return the next song if it's marked as a favorite
        while (index < playlist.getSongs().size()) {
            if (playlist.getSongs().get(index).contains(
                    "Fav")) { // Mark favorites with 'Fav' in name
                return true;
            }
            index++;
        }
        return false;
    }
    @Override
    public String next() {
        return playlist.getSongs().get(index++);
    }
}