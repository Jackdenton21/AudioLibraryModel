import java.util.List;

public class ShuffleCommand implements PlaylistCommand{

    private PlayList playlist;
    private List<Playable> originalOrder;
    private List<Playable> newOrder;

    public ShuffleCommand(PlayList playlist, List<Playable> originalOrder, List<Playable> newOrder){
        this.playlist = playlist;
        this.originalOrder = originalOrder;
        this.newOrder = newOrder;
    }

    @Override
    public void redo() {
        playlist.shuffle();
    }

    @Override
    public void undo() {
        playlist.changeOrder(originalOrder);
    }
}
