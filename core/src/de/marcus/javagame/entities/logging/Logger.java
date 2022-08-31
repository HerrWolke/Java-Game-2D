package de.marcus.javagame.entities.logging;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Logger {
    private Object obj;
    private Lvl lvl;

    public Logger(Object obj, Lvl lvl) {
        if (!obj.getClass().isAnnotationPresent(Logging.class))
            System.err.println("Error! Could not add object " + obj.getClass().getName() + " because it does not have an annotation");
        else {
            this.obj = obj;
            this.lvl = lvl;
        }
    }

    public void log(LoggerLevel level, String description) {
        File file = new File(LoggingSystem.LOG_FOLDER + LoggingSystem.dateLogName + ".log");
        String toLog = "";

        String date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime());
        if (obj == null) {
            toLog = String.format("[%s] [Class %s] [%s] %s", date, "System", level, description);
        } else {
            toLog = String.format("[%s] [Class %s] [%s] %s", date, obj.getClass().getDeclaredAnnotation(Logging.class).displayName(), level, description);
        }

        if (level.equals(LoggerLevel.SEVERE) || level.equals(LoggerLevel.ERROR)) {
            System.err.println(toLog);
        } else {
            System.out.println(toLog);
        }
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(toLog + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void debug(String description) {
        if (lvl.equals(Lvl.DEBUG)) {


            String date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime());
            String toLog = String.format("[%s] [Class %s]  %s", date, obj.getClass().getDeclaredAnnotation(Logging.class).displayName(), description);

            System.out.println(toLog);
        }
    }

    public void debug(Object object) {
        if (lvl.equals(Lvl.DEBUG)) {


            String date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime());
            String toLog = String.format("[%s] [Class %s]  %s", date, obj.getClass().getDeclaredAnnotation(Logging.class).displayName(), object);

            System.out.println(toLog);
        }
    }


    public enum LoggerLevel {
        SEVERE, ERROR, WARN, INFO
    }

    public enum Lvl {
        DEBUG, DEFAULT
    }


}
