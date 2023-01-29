package application.blue.talkToBlueImpl;

import application.green.TalkToBlue;
import application.green.talkToGreenImpl.Content;

public class ResultStash implements TalkToBlue {

    private PayloadDTO payloadDTO;

    public PayloadDTO getPayloadDTO() {
        return payloadDTO;
    }

    @Override
    public void setContent(Content greets) {
        this.payloadDTO = new PayloadDTO(greets.getContent());
    }
}
