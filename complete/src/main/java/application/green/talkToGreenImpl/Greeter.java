package application.green.talkToGreenImpl;

import application.green.TalkToBlue;
import application.red.TalkToGreen;
import application.yellow.VeryNiceGreeting;

/**
 * GREEN LAYER (DATA LAYER) = TEAM CONTENT
 * This Presenter is responsible for handling the flow of DATA back into the Blue layer
 */
public class Greeter implements TalkToGreen {

    private final TalkToBlue talkToBlue;

    public Greeter(TalkToBlue talkToBlue) {
        this.talkToBlue = talkToBlue;
    }

    /**
     * The Greeter converts a domain entity into a payload object and ensures that the presenter
     * in the TECHNICAL layer receives the data in the correct format.
     * @param who
     */
    @Override
    public void sendGrettings(VeryNiceGreeting who) {
        Content content = new Content(who.getName());
        talkToBlue.setContent(content);
    }
}
