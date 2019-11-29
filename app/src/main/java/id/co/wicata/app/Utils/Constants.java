package id.co.wicata.app.Utils;

public interface Constants {
    String API_VERSION = "v1";
    String API_URL = "http://10.0.2.2:8000/api/" + API_VERSION + "/";
//    String API_URL = "http://sedaph.localtunnel.me/api/" + API_VERSION + "/";

    int USER_EXISTS = 101;
    int USER_SUCCESS = 102;
    int USER_FAILED = 103;
    int USER_NOT_FOUND = 104;
    int USER_PASSWORD_INVALID = 105;
    int USER_REGISTERED = 106;
    int USER_REGISTER_FAILED = 107;
    int CODE_EXPIRED = 201;
    int CODE_INVALID = 202;
    int CODE_VALID = 203;
    int CODE_SENT = 204;
    int CODE_SENT_FAILED = 205;
}
