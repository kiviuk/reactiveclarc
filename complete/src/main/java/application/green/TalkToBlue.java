package application.green;

import application.green.talkToGreenImpl.Content;

/**
 * The OUTPUT PORT through which the Presenter communicates with the Blue layer
 */
public interface TalkToBlue {

    void setContent(Content greets);

}
