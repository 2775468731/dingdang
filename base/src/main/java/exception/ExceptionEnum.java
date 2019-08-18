package exception;

/**
 * Title:
 * Description:
 * author :xbl
 * Date:2019/8/17
 * Time:21:31
 */
public enum ExceptionEnum {

    ERROR_NOFOUND("无法找到相应的数据");
    private String value;

    public String getValue() {
        return value;
    }

    ExceptionEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

}
