package application.yellow;

public class VeryNiceGreeting {

	private String message;

	public VeryNiceGreeting() {
	}

	public VeryNiceGreeting(String message) {
		this.message = message;
	}

	public String getName() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Greeting{" +
				"message='" + message + '\'' +
				'}';
	}
}
