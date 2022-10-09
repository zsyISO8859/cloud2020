import com.thoughtworks.xstream.converters.time.ZonedDateTimeConverter;

import java.time.ZonedDateTime;

/**
 * @author : zhousy
 * @version : 1.0
 * @date : 2022/9/6 14:38
 */


public class Test {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
