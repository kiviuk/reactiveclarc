package application.red;

import application.yellow.VeryNiceGreeting;

/**
 * The OUTPUT PORT is the mechanism through which the use case
 * can return its result to the green layer.
 */
public interface TalkToGreen {

    void format(VeryNiceGreeting who);

}
