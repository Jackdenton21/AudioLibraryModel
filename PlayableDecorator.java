public class PlayableDecorator implements Playable{
    protected Playable decoratedPlayable;

    public PlayableDecorator(Playable decoratedPlayable){
        this.decoratedPlayable = decoratedPlayable;
    }

    public void play(){
        decoratedPlayable.play();
    }
}
