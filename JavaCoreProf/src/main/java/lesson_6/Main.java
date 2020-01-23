package lesson_6;

import java.io.IOException;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(lesson_6.Main.class.getName());

    public static void main( String[] args ) throws IOException {
        //System.out.println("start");
        logger.setLevel(Level.ALL);
        logger.getHandlers()[0].setLevel(Level.ALL);

        logger.getHandlers()[0].setFormatter(new Formatter() {
            @Override
            public String format( LogRecord record ) {
                return record.getLevel() + "\t" + record.getMessage() + "\t" + record.getMillis() + "\n";
            }
        });

        logger.getHandlers()[0].setFilter(new Filter() {
            @Override
            public boolean isLoggable( LogRecord record ) {
                return record.getMessage().startsWith("Java");
            }
        });

        Handler handler = new FileHandler("mylog.log", true);
        handler.setLevel(Level.ALL);
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);

        handler.setFilter(new Filter() {
            @Override
            public boolean isLoggable( LogRecord record ) {
                return record.getMessage().startsWith("Java");
            }
        });

        logger.log(Level.SEVERE, "Java1");
        logger.log(Level.INFO, "Java2");
        logger.log(Level.CONFIG, "C#");
        logger.log(Level.FINE, "Java4");

    }
}
