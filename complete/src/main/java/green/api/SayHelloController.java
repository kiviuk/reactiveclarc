package green.api;

import red.api.UseCaseApi;
import yellow.VeryNiceGreeting;

public class SayHelloController implements SayHelloControllerApi {

    private final UseCaseApi useCaseApi;

    public SayHelloController(UseCaseApi useCaseApi) {
        this.useCaseApi = useCaseApi;
    }

    @Override
    public void sayHelloTo(String who) {

        if (who == null || who.length() == 0) {
            who = "NaN";
        }

        useCaseApi.beNiceTo(new VeryNiceGreeting(who));
    }
}
