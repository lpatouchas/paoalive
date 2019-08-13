package com.paoalive.webhook.informer.helper;

import com.paoalive.webhook.informer.dto.PaoAliveException;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * @author leonidas_patouhas
 * on 8/8/2019.
 */
public class IntegerHelper {

    private static ThreadLocal<NumberFormat> numberFormatter = ThreadLocal.withInitial(() -> NumberFormat.getIntegerInstance(Locale.GERMAN));

    public static Integer parseString(String value) throws PaoAliveException {
        try {
            return numberFormatter.get().parse(value).intValue();
        } catch (ParseException e) {
            throw new PaoAliveException(e);
        }
    }

    public static String format(Integer value) {
        return numberFormatter.get().format(value);
    }
}
