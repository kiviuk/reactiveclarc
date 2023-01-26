package application.red.api;

import application.red.TalkToGreen;
import application.yellow.VeryNiceGreeting;

public class UseCaseImpl implements UseCaseApi {

    private final TalkToGreen talkToGreen;

    public UseCaseImpl(TalkToGreen talkToGreen) {
        this.talkToGreen = talkToGreen;
    }

    @Override
    public void beNiceTo(VeryNiceGreeting who) {
        String newGreeting = "Hi " + who.getName() + "!";
        talkToGreen.format(new VeryNiceGreeting(newGreeting));
    }

}
