package razgriz.self.appexercise1202.api.basic;

public class ApiException extends Exception {
    private final int code;
    private final String body;

    public ApiException(int code, String body) {
        this.code = code;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }
}
