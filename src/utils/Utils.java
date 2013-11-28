package utils;

/**
 *
 * @author mrkaczor
 */
public class Utils {

    public static String formatTime(long time) {
        String sTime;
        sTime="."+time%1000;
        time-=time%1000;
        time/=1000;
        sTime=(time%60<10?"0"+time%60:time%60)+sTime;
        time-=time%60;
        time/=60;
        sTime=(time%60<10?"0"+time%60:time%60)+":"+sTime;
        time-=time%60;
        time/=60;
        sTime=(time<10?"0"+time:time)+":"+sTime;
        return sTime;
    }

}
