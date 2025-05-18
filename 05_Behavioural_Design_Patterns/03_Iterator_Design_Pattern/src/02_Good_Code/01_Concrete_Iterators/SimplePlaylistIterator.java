package Good_Code.Concrete_Iterators;

import Good_Code.Playlist;
import Good_Code.PlaylistIterator;

public class SimplePlaylistIterator implements PlaylistIterator {
    private Playlist playlist;
    private int index;
    public SimplePlaylistIterator(Playlist playlist) {
        this.playlist = playlist;
        this.index = 0;
    }
    @Override
    public boolean hasNext() {
        return index < playlist.getSongs().size();
    }
    @Override
    public String next() {
        return playlist.getSongs().get(index++);
    }
}