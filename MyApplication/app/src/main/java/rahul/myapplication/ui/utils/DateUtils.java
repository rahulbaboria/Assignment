package rahul.myapplication.ui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by rahul on 5/8/18.
 */
public class DateUtils {

    public static String getFormattedDate(String date){
        if (date==null)return "";
        String prevDateFormat =  "yyyy-MM-dd'T'HH:mm:ss";
        String toFormat = "MMM d yyyy";
        SimpleDateFormat newSdf = new SimpleDateFormat(toFormat, Locale.getDefault());
        SimpleDateFormat prevSdf = new SimpleDateFormat(prevDateFormat,Locale.getDefault());
        try {
            Date date1 = prevSdf.parse(date);
            return newSdf.format(date1);
        } catch (ParseException e) {
            return "";
        }
    }
}
