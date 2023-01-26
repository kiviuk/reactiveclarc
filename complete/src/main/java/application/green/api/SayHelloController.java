package application.green.api;

import application.red.api.UseCaseApi;
import application.yellow.VeryNiceGreeting;

/**
 * GREEN LAYER = DATA LAYER
 * The SayHelloController is responsible for managing the data flow into the red layer.
 */
public class SayHelloController implements SayHelloControllerApi {

    private final UseCaseApi useCaseApi;

    public SayHelloController(UseCaseApi useCaseApi) {
        this.useCaseApi = useCaseApi;
    }

    /**
     * The SayHelloController ensures that the LOGIC layer's Use Case
     * is provided with consistent and validated DATA.
     *
     * @param who
     */
    @Override
    public void sayHelloTo(String who) {

        if (who == null || who.length() == 0) {
            who = "NaN";
        }

        useCaseApi.beNiceTo(new VeryNiceGreeting(who));
    }
}
