package be.toune.fabien.beid.util;

public class CharsetConverter {
	public static String toUTF8(String s) {
		try {
			return new String(s.getBytes(), "UTF8");
		} catch (Exception ex) {
	    	ex.printStackTrace();
	    	return null;
	    }		
	}
}
