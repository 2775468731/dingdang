package common;

/**
 * Title:
 * Description:
 * author :xbl
 * Date:2019/8/18
 * Time:11:35
 */
public class BaseReturn {
    private Integer code = 200;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BaseReturn(String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseReturn(String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public BaseReturn(Integer code,String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
