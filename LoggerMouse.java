import java.awt.MouseInfo;
import java.awt.Point;
import java.util.concurrent.TimeUnit;
import java.lang.String;
import java.net.URL;
import java.net.HttpURLConnection;


public class LoggerMouse {

  /**
  * @param args
  */

  static int lastx = 0;
  static int lasty = 0;
  static int totalSeconds = 0;
  static boolean keyPressed;
  static String apikey="ENTER KEY HERE";
  static int deltaSeconds = 21;

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
          updateThingSpeak(1,1,1);
          System.out.println("Udating thingspeak "+x+":"+y);

        }
        System.out.println("Aktiv tid ved PC = " + totalSeconds / 60 + " minutter og " + totalSeconds % 60
        + " sekunder");

      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Screen is loccked or something else is bad");
      }

    }

  }

  public static void updateThingSpeak(int f1, int f2, int f3){

    String data = "api_key="+apikey+"\nfield1="+f1;
    try{
      URL url = new URL("https://api.thingspeak.com/update.xml");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setDoOutput(true);
      con.getOutputStream().write(data.getBytes("UTF-8"));
      con.getInputStream();
    }catch(Exception e){
      e.printStackTrace();
    }

  }

}
