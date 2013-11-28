package utils;

/**
 *
 * @author mrkaczor
 */
public class Utils {

    public static String formatLongNumber(long number) {
    	String num = Long.toString(number);
    	String result = "";
    	for(int i=0; i<num.length(); i++) {
    		result = num.charAt(num.length()-i-1) + result;
    		if((i+1)%3==0 && i!=num.length()-1) {
    			result = " " + result;
    		}
    	}
    	return result;
    }

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
