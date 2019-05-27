package flylvzheng.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JaxbAdapter {
    /**
     * 日期
     * DATE_TIME_SHORT = "HHmmss";
     *     public static final String DATE_SHORT = "yyyyMMdd";
     */
    public static class DateAdapter extends XmlAdapter<String, Date> {
        @Override
        public Date unmarshal(String v) throws Exception {
            if (v == null) {
                return null;
            }
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            return format.parse(v);
        }

        @Override
        public String marshal(Date v) throws Exception {
            DateFormat format = new SimpleDateFormat("yyyyMMdd");
            return format.format(v);
        }
    }

    /**
     * 时间
     */
    public static class TimeAdapter extends XmlAdapter<String, Date> {
        @Override
        public Date unmarshal(String v) throws Exception {
            if (v == null) {
                return null;
            }
            DateFormat format = new SimpleDateFormat("HHmmss");
            return format.parse(v);
        }

        @Override
        public String marshal(Date v) throws Exception {
            DateFormat format = new SimpleDateFormat("HHmmss");
            return format.format(v);
        }
    }
}
