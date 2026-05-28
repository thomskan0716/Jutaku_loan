/**
 *
 */
package migration.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hi-takahashi
 */
public class DateUtil {
    private static final String DATE_FORMAT_YEARMONTH = "yyyy/MM";

    public static String getYearMonthString() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YEARMONTH);

        String yearMonth = sdf.format(date.getTime());

        return yearMonth;
    }

    /**
     * "yyyyMMdd" 形式の文字列を java.util.Date に変換する。無効な日付の場合は null を返す。
     *
     * @param str 変換する文字列
     * @return 変換された java.util.Date。無効な場合は null。
     */
    public static Date toDate(String str) {

        Date date;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            dateFormat.setLenient(false);
            date = dateFormat.parse(str);

            // パース後に再フォーマットして一致確認
            String reverse = dateFormat.format(date);
            if (!str.equals(reverse)) {
                return null;
            }

        } catch (ParseException e) {
            return null;
        }

        return date;
    }
}
