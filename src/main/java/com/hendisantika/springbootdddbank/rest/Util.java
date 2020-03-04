package com.hendisantika.springbootdddbank.rest;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 05/03/20
 * Time: 06.46
 */
public class Util {
    /**
     * Formats a LocalDate in Germany as 31.12.1999.
     */
    static final DateTimeFormatter MEDIUM_DATE_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

}
