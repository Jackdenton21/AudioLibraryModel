import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Q1Test {

    @Test
    public void DynamicDefaultSongTest() {
        Library.createLibrary("Jack");
        //Dynamically adds a song to arraylist that represents the library's defaults.
        Playable defaultSong = new DefaultDecorator(new Song("Song1", "Artist1"));
        assertEquals(1, Library.getDefaultPlayables().size());
    }

    @Test
    public void DynamicDefaultPlayListTest() {
        //The song previously added to the library's list of default playables is removed.
        Library.resetDefaultPlayables();
        //Dynamically adds a playlist to arraylist that represents the library's defaults.
        Playable defaultPlaylist = new DefaultDecorator( new PlayList("My Playlist"));
        //Gets the default playables saved by the library
        ArrayList<Playable> defaultPlayables = Library.getDefaultPlayables();
        //Selects the first default (the one that was just added)
        assertEquals(((PlayList)defaultPlayables.get(0)).getName(), "My Playlist");
    }


    @Test
    public void ResettingDefaultTest() {

        //Multiple playlist are added to the library's default playables list
        Playable defaultPlaylist1 = new DefaultDecorator( new PlayList("One Playlist"));
        Playable defaultPlaylist2 = new DefaultDecorator( new PlayList("Another Playlist"));
        Playable defaultPlaylist3 = new DefaultDecorator( new PlayList("Final Playlist"));
        //Resets the library's list of defaults.
        Library.resetDefaultPlayables();
        assertEquals(0, Library.getDefaultPlayables().size());
    }
}
