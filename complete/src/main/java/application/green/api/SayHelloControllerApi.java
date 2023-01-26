package application.green.api;

/**
 * The INPUT PORT or Controller API
 * through which the blue layer can communicate with the Controller.
 */
public interface SayHelloControllerApi {

    void sayHelloTo(String blue);
}
