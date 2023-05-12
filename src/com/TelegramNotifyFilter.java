package com;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class TelegramNotifyFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        return record.getLevel().intValue() >= Level.SEVERE.intValue();
    }
}
