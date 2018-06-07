package dev.nate.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Util {
	public final static float[] UP = new float[] {0,1,0};

	  public static String loadResource(String fileName) {
	        String result="";
	         
	        try (InputStream in = Class.forName(Util.class.getName()).getResourceAsStream(fileName);
	        Scanner scanner = new Scanner(in, "UTF-8")) {
	        
	            result = scanner.useDelimiter("\\A").next();
	        } catch (ClassNotFoundException | IOException e) {e.printStackTrace();}
	      return result;
	    }
	  public static float[] arrayCopy(float[] src, int limit) {
		  float[] ret=new float[src.length];
		  for(int i=0;i<limit;i++) {
			  if(i >= src.length)break;
			  ret[i]=src[i];
			  
		  }
			src=null;
			return ret;
		}
}

