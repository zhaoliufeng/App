package exception;

/**
 * 缺失请求参数异常
 */
public class MissParamException extends Throwable {

    public MissParamException() {
    }

    public MissParamException(String message) {
        super(message);
    }
}
