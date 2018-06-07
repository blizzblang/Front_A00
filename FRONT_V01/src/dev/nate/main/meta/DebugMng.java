package dev.nate.main.meta;
/*
 * TODO Ancestor to proper debugger;
 */
public class DebugMng {
	public static void closeApp(int errorCode) {
		ExitCode r = ExitCode.lookup(errorCode);
		System.err.println("The application was forced to close!");
		System.err.println(errorCode+ " : "+r.getMsg());
		System.exit(errorCode);
	}
}
/*
 * Enum for generic exit states. 
 */
enum ExitCode{
	unknown(-2,"Unknown error code"),
	normal(0,"Regular Exit State"),
	badParameter(1,"Bad parameter passed!"),
	uniformNotFound(2,"Uniform Variable not found!"),
	
	;
	private int number;
	private String msg;
	private ExitCode(int a,String b) {
		number=a;
		msg=b;
	}
	public String getMsg() {
		return msg;
	}
	public static final ExitCode lookup(int a) {
		if(a==0)
			return normal;
		if(a==1)
			return badParameter;
		if(a==2)
			return uniformNotFound;
		
		return ExitCode.unknown;
	}
}
