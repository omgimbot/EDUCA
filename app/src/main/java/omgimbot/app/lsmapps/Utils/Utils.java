package omgimbot.app.lsmapps.Utils;

import android.icu.util.ULocale;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.mapbox.mapboxsdk.geometry.LatLng;

/**
 * Created by fiyyanp on 2/1/2018.
 */

public class Utils {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_INDONESIA_PHONE_NUMBER_REGEX =
            Pattern.compile("\\(?(?:\\+62|62|0)(?:\\d{2,3})?\\)?[ .-]?\\d{2,4}[ .-]?\\d{2,4}[ .-]?\\d{2,4}");

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static String convertMongoDate(String val) {
        ISO8601DateFormat df = new ISO8601DateFormat();
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM, yyyy HH:mm");
        try {
            Date d = df.parse(val);
            String finalStr = outputFormat.format(d);
            val = finalStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return val;
    }

    public static String convertMongoDateWithoutTIme(String val) {
        Locale id = new Locale("in", "ID");
        ISO8601DateFormat df = new ISO8601DateFormat();
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE , dd MMM yyyy" , id);
        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
        outputFormat.setTimeZone(tz);
        try {
            Date d = df.parse(val);
            String finalStr = outputFormat.format(d);
            val = finalStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return val;
    }

    public static String convertRupiah(String val) {


        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        String biaya =kursIndonesia.format(Long.valueOf(val));
        return biaya;
//        int value = Integer.parseInt(val);
//        Locale localeID = new Locale("in", "ID");
//        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
//        String biaya =  formatRupiah.format(". "+(double)value);
//        return biaya ;
    }

}
