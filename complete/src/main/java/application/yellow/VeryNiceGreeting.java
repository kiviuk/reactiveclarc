package application.yellow;


/**
 * Generic DOMAIN Entity
 */
public class VeryNiceGreeting {

	private String name;

	public VeryNiceGreeting() {
	}

	public VeryNiceGreeting(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Greeting{" +
				"message='" + name + '\'' +
				'}';
	}
}
