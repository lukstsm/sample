package com.bbt.sampleproject.ui;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

public class DateFormatter {

    private static final String DATE_FORMAT = "dd MMMM YYYY : hh mm";

    private final Locale mLocale;

    public DateFormatter(Locale locale) {
        mLocale = locale;
    }

    public String getFormattedDate(DateTime date) {
        final DateTimeFormatter dtf = DateTimeFormat.forPattern(DATE_FORMAT).withLocale(mLocale);
        return dtf.print(date);
    }
}
