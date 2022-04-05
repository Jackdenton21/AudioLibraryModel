public class addPlayableCommand implements PlaylistCommand{

    private PlayList playlist;
    private Playable playable;

    public addPlayableCommand(PlayList playlist, Playable pPlayable) {
        this.playable = pPlayable;
        this.playlist = playlist;
    }

    @Override
    public void redo() {
        playlist.addPlayable(this.playable);
    }

    @Override
    public void undo() {
        playlist.removePlayable(playlist.size()-1);
    }

}

