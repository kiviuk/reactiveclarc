package boot;

import application.green.talkToGreen.Payload;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@ComponentScan({"application", "boot"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        GreetingClient greetingClient = context.getBean(GreetingClient.class);
        // We need to block for the content here or the JVM might exit before the message is logged
        System.out.println(">> message = " + greetingClient.getMessage().block());
    }

    @Component
    public static class GreetingClient {

        private final WebClient client;

        // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
        // We can use it to create a dedicated `WebClient` for our component.
        public GreetingClient(WebClient.Builder builder) {
            this.client = builder.baseUrl("http://localhost:8080").build();
        }

        public Mono<String> getMessage() {
            return this.client.get().uri("/say/hello/Fred").accept(MediaType.APPLICATION_JSON)
                              .retrieve()
                              .bodyToMono(Payload.class)
                              .map(Payload::getPayload)
                ;
        }

    }
}
