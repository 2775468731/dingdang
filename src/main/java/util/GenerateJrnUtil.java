package util;

/**
 * Title:
 * Description:
 * author :xbl
 * Date:2019/8/17
 * Time:23:24
 */
public class GenerateJrnUtil {

    /**
     * 生成时间
     * @return
     */
    public static String generateJrnByTime(){
        String jrnNo = DateUtil.getDateStringByFormatString("yyyyMMddHHmmssSSS");
        int i = (int)(Math.random()*900 + 100);
        return jrnNo+String.valueOf(i);
    }
}
