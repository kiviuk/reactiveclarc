package application.green.api;

import application.red.api.UseCaseApi;
import application.yellow.VeryNiceGreeting;

/**
 * The Controller is responsible for managing the data flow into the red layer.
 */
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
