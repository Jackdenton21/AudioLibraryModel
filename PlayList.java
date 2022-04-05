import java.util.*;

/**
 * Represents a sequence of playables to play in FIFO order.
 */
public class PlayList implements Playable {


    private ArrayList<PlaylistCommand> executedCommands = new ArrayList<>();
    private ArrayList<PlaylistCommand> undoneCommands = new ArrayList<>();
    PlaylistCommand mostRecentCommand = null;
    boolean mostRecentCommandWasUndone = false;


    private List<Playable> aList = new LinkedList<>();
    private String aName;

    /**
     * Creates a new empty playlist.
     *
     * @param pName
     *            the name of the list
     * @pre pName!=null;
     */
    public PlayList(String pName) {
        assert pName != null;
        aName = pName;
    }

    /**
     * Adds a playable at the end of this playlist.
     *
     * @param pPlayable
     *            the content to add to the list
     * @pre pPlayable!=null;
     */
    public void addPlayable(Playable pPlayable) {
        assert pPlayable != null;
        aList.add(pPlayable);
        mostRecentCommand = new addPlayableCommand(this, pPlayable);
        executedCommands.add(mostRecentCommand);
        mostRecentCommandWasUndone = false;
    }

    /**
     * remove a playable from the Playlist given its index
     * @param pIndex
     *          the index of playable to be removed
     * @return the removed playable
     */
    public Playable removePlayable(int pIndex) {
        assert pIndex >= 0 && pIndex < aList.size();

        Playable removedPlayable = aList.remove(pIndex);
        mostRecentCommand = new RemovePlayableCommand(this, pIndex, removedPlayable);
        executedCommands.add(mostRecentCommand);
        mostRecentCommandWasUndone = false;
        return removedPlayable;
    }

    /**
     * @return The name of the playlist.
     */
    public String getName() {
        return aName;
    }

    /**
     * @return the number of playables in the playlist
     */
    public int size() {
        return aList.size();
    }

    /**
     * modify the name of the playlist
     * @param pName
     *          the new name of the playlist
     */
    public void setName(String pName) {
        assert pName != null;
        String prevName = this.aName;
        this.aName = pName;
        mostRecentCommand = new SetNameCommand(this, prevName);
        executedCommands.add(mostRecentCommand);
        mostRecentCommandWasUndone = false;
    }

    /**
     * Iterating through the playlist to play playable content.
     */
    @Override
    public void play() {
        for(Playable playable:aList){
            playable.play();
        }
    }

    /**
     * changes the order that the playlist plays the playables it contains
     */
    public void shuffle() {

        List<Playable> preShuffleOrder = new LinkedList<>(aList);
        Collections.shuffle(aList);
        List<Playable> postShuffleOrder = new LinkedList<>(aList);
        mostRecentCommand = new ShuffleCommand(this, preShuffleOrder, postShuffleOrder);
        executedCommands.add(mostRecentCommand);
        mostRecentCommandWasUndone = false;
    }

    /**
     * changes the order that the playlist plays the playables it contains based on another playlist with the same playables
     * in a different order
     */
    public void changeOrder(List<Playable> newOrder) {
        this.aList = newOrder;
    }


    /**
     * Checks is two playlists are equal based on playable objects and their order
     *
     * @param o
     *            The object to compare a playlist to
     * @return    This method returns true if the playlist is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayList playList = (PlayList) o;
        return this.aList.equals(playList.aList);
    }

    /**
     * Equal playlists have the same hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(aList);
    }

    public void undo() {

        if(executedCommands.size() == 0) {
            System.out.println("No commands to undo");
            return;
        }
        mostRecentCommand = executedCommands.get(executedCommands.size() - 1);
        undoneCommands.add(mostRecentCommand);
        mostRecentCommand.undo();
        executedCommands.remove(executedCommands.size() - 1);
        mostRecentCommandWasUndone = true;
    }

    public void redo() {
        assert mostRecentCommand != null;

        if(mostRecentCommandWasUndone) {
            undoneCommands.get(undoneCommands.size() - 1).redo();
        }
        else {
            mostRecentCommand.redo();
        }
    }
}


