package id.co.wicata.app.model;

import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("error")
    private boolean error;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("user")
    private User user;

    public Response(boolean error, int code, String message, User user) {
        this.error = error;
        this.code = code;
        this.message = message;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
