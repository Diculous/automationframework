package hardcore.util;

import java.util.concurrent.TimeUnit;

public class Sleeper {

    public static void timeout(int timer) {
        try {
            TimeUnit.SECONDS.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}