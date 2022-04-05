public class RemovePlayableCommand implements PlaylistCommand {

    private PlayList playlist;
    private int removedIndex;
    private Playable removedPlayable;

    public RemovePlayableCommand(PlayList playlist, int removedIndex, Playable removedPlayable){
        this.removedIndex = removedIndex;
        this.removedPlayable = removedPlayable;
        this.playlist = playlist;
    }

    @Override
    public void redo() {
        if(playlist.size() == 0) return;
        playlist.removePlayable(playlist.size() - 1);
    }

    @Override
    public void undo() {
        playlist.addPlayable(removedPlayable);
    }
}