public class HttpResponseHandler {

    private int statusCode;
    private int size;
    private String content;

    public HttpResponseHandler() {}

    public HttpResponseHandler(int statusCode, int size, String content) {
        this.statusCode = statusCode;
        this.size = size;
        this.content = content;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getSize() {
        return size;
    }

    public String getContent() {
        return content;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
