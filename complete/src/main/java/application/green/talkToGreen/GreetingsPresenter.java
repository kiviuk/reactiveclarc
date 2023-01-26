package application.green.talkToGreen;

import application.green.TalkToBlue;
import application.red.TalkToGreen;
import application.yellow.VeryNiceGreeting;

/**
 * The Presenter is responsible for handling the flow of data back into the Blue layer
 */
public class GreetingsPresenter implements TalkToGreen {

    private final TalkToBlue talkToBlue;

    public GreetingsPresenter(TalkToBlue talkToBlue) {
        this.talkToBlue = talkToBlue;
    }

    @Override
    public void format(VeryNiceGreeting who) {
        Payload payload = new Payload(who.getName());
        talkToBlue.setGreeting(payload);
    }
}
