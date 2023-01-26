package application.green.talkToGreen;

public class Payload {
	public Payload() {
	}

    private String payload;

    public Payload(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Payload{" +
            "message='" + payload + '\'' +
            '}';
    }
}
