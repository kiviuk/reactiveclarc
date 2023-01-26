package application.green;

import application.green.talkToGreen.Payload;

/**
 * The OUTPUT PORT through which the Presenter communicates with the Blue layer
 */
public interface TalkToBlue {

    void setGreeting(Payload greets);

}
