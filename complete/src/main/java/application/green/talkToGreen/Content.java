package application.green.talkToGreen;

/**
 * The Content refers to the data that the Blue layer is able to process.
 */
public class Content {
    private String content;

	public Content() {
	}

    public Content(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Content{" +
            "message='" + content + '\'' +
            '}';
    }
}
