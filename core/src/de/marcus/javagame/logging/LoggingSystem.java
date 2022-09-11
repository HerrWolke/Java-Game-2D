package de.marcus.javagame.logging;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Logging(displayName = "LoggingSystem")
public class LoggingSystem {
    Logger logger = new Logger(this, Logger.Lvl.DEFAULT);
    public static String dateLogName;
    public static final String LOG_FOLDER = "Logs/";

    public LoggingSystem() {
        initialize();
    }

    public boolean initialize() {
        dateLogName = new SimpleDateFormat("dd.MM.yy").format(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime());

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                logger.log(Logger.LoggerLevel.INFO, "Getting the new date for the Logging System");
                dateLogName = new SimpleDateFormat("dd.MM.yy").format(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime());
            }
        };
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        timer.scheduleAtFixedRate(task, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
        new File("Logs").mkdirs();

        File todaysLogFile = new File(LOG_FOLDER + dateLogName + ".log");

        if (!todaysLogFile.exists()) {
            try {
                return todaysLogFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public Logger getLogger() {
        return logger;
    }
}
