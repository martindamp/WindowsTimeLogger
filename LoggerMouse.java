import java.awt.MouseInfo;
import java.awt.Point;
import java.util.concurrent.TimeUnit;

public class LoggerMouse {

    /**
     * @param args
     */

    static int lastx = 0;
    static int lasty = 0;
    static int totalSeconds = 0;
    static boolean keyPressed;

    static int deltaSeconds = 60;

    public static void main(String[] args) {

        while (true) {  
            try {
                TimeUnit.SECONDS.sleep(deltaSeconds);
            } catch (InterruptedException e) {

            }

           

            try {
                Point loc = MouseInfo.getPointerInfo().getLocation();
                int x = loc.x;
                int y = loc.y;
                if ((x != lastx || y != lasty) && (x!=3 && y!=0)) {
                    totalSeconds += deltaSeconds;

                    lastx = x;
                    lasty = y;
                    System.out.println(x+":"+y);

                }
                System.out.println("Aktiv tid ved PC = " + totalSeconds / 60 + " minutter og " + totalSeconds % 60
                        + " sekunder");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Screen is loccked or something else is bad");
            }

        }

    }
}
