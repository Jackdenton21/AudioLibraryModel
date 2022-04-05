public class SetNameCommand implements PlaylistCommand{

    private PlayList playlist;
    private String prevName;
    private String curName;

    public SetNameCommand(PlayList playlist, String prevName){
        this.playlist = playlist;
        this.prevName = prevName;
    }

    @Override
    public void redo() {
        //Redo is not need as it will just set the name to the current name again
    }

    @Override
    public void undo() {
        playlist.setName(prevName);
    }
}
