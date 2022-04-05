import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Q2Test {

    @Test
    public void MultipleUndoTest() {

        PlayList playlist2 = new PlayList("Playlist2");
        //2 new songs are added to the playlist
        playlist2.addPlayable(new Song("Song1", "Artist1"));
        playlist2.addPlayable(new Song("Song2", "Artist2"));
        //Both commands are undone
        playlist2.undo();
        playlist2.undo();
        //The playlist should be empty
        assertEquals(playlist2.size(), 0);

    }

    @Test
    public void RedoingAnUndoTest() {

        PlayList playlist1 = new PlayList("Playlist1");
        //A new song is added to the playlist
        playlist1.addPlayable(new Song("Song1", "Artist1"));
        //Then the command in undone
        playlist1.undo();
        assertEquals(playlist1.size(), 0);
        //But then the command is redone
        playlist1.redo();
        assertEquals(playlist1.size(), 1);

    }

    @Test
    public void RedoingAMethod() {

        PlayList playlist57 = new PlayList("Playlist57");
        //A new song is added to the playlist
        playlist57.addPlayable(new Song("Song1", "Artist1"));
        playlist57.addPlayable(new Song("Song2", "Artist2"));

        playlist57.removePlayable(0);
        playlist57.redo();

        //The playlist should be empty after redoing the remove command
        assertEquals(playlist57.size(), 0);
    }



    @Test
    public void ShuffleTest() {

        PlayList playlist3 = new PlayList("Playlist3");
        PlayList playlist4 = new PlayList("Playlist4");


        //4 new songs are added to each playlist
        playlist3.addPlayable(new Song("Song1", "Artist1"));
        playlist3.addPlayable(new Song("Song2", "Artist2"));
        playlist3.addPlayable(new Song("Song3", "Artist3"));
        playlist3.addPlayable(new Song("Song4", "Artist4"));

        playlist4.addPlayable(new Song("Song1", "Artist1"));
        playlist4.addPlayable(new Song("Song2", "Artist2"));
        playlist4.addPlayable(new Song("Song3", "Artist3"));
        playlist4.addPlayable(new Song("Song4", "Artist4"));

        //The shuffle command is executed
        playlist3.shuffle();
        //The shuffle command is undone
        playlist3.undo();

        //The playlist should be equal (have the same order of the same songs)
        assertEquals(playlist3, playlist4);
    }

    @Test
    public void SetNameTest(){

        PlayList JacksPlayList = new PlayList("JacksPlayList");
        JacksPlayList.setName("SomeoneElsesPlayList");
        JacksPlayList.undo();
        assertEquals(JacksPlayList.getName(), "JacksPlayList");

    }



}
