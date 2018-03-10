package weather.com.theweatherapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateTimeUtil {

    private static DateTimeUtil instance;
    private SimpleDateFormat dateFormatService;
    private SimpleDateFormat dateOutput;

    public static DateTimeUtil getInstance() {
        if (instance == null)
            instance = new DateTimeUtil();
        return instance;
    }

    private DateTimeUtil() {
        dateFormatService = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        dateOutput = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.US);
    }

    public String getDateFormatOutput(String dateInputFromService) {
        try {
            Date varDate = dateFormatService.parse(dateInputFromService);
            return dateOutput.format(varDate);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
