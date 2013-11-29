package utils;

import java.io.File;

import javax.swing.JFileChooser;

/**
 *
 * @author mrkaczor
 */
public class Utils {

	private static String _lastImportPath = System.getProperty("user.dir");
	private static final JFileChooser _fileChooser = new JFileChooser(_lastImportPath); 

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

    public static File loadFile() {
    	_fileChooser.setCurrentDirectory(new File(_lastImportPath));
    	_fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    	_fileChooser.setMultiSelectionEnabled(false);
    	if(_fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
    		_lastImportPath = _fileChooser.getSelectedFile().getAbsolutePath();
    		return _fileChooser.getSelectedFile();
    	}
    	return null;
    }

    public static File[] loadFiles() {
    	_fileChooser.setCurrentDirectory(new File(_lastImportPath));
    	_fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    	_fileChooser.setMultiSelectionEnabled(true);
    	if(_fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
    		_lastImportPath = _fileChooser.getSelectedFile().getAbsolutePath();
    		return _fileChooser.getSelectedFiles();
    	}
    	return null;
    }

}
