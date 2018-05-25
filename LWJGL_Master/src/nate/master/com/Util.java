package nate.master.com;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Util {
	  public static String loadResource(String fileName) {
	        String result="";
	         
	        try (InputStream in = Class.forName(Util.class.getName()).getResourceAsStream(fileName);
	        Scanner scanner = new Scanner(in, "UTF-8")) {
	        
	            result = scanner.useDelimiter("\\A").next();
	        } catch (ClassNotFoundException | IOException e) {e.printStackTrace();}
	        return result;
	    }
}
