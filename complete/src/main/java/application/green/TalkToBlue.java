package application.green;

import application.green.talkToGreen.Content;

/**
 * The OUTPUT PORT through which the Presenter communicates with the Blue layer
 */
public interface TalkToBlue {

    void setContent(Content greets);

}
