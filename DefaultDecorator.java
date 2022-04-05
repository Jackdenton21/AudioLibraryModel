public class DefaultDecorator extends PlayableDecorator{

    public DefaultDecorator(Playable decoratedPlayable){
        super(decoratedPlayable);
        addDefault(decoratedPlayable);
    }

    @Override
    public void play() {
        decoratedPlayable.play();
        //addDefault(decoratedPlayable);
    }

    private void addDefault(Playable decoratedPlayable){
        Library library = Library.getLibrary();
        library.addDefaultPlayable(decoratedPlayable);
    }

}
