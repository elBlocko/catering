package logic;


import userinterface.UMain;

public class TConstants {
	private static String CDatabaseName = "catering.db";
	
	// D:\\Desktop\\java\\Database\\
	public static String executableJarFilePath = UMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	//public static String executableJarFilePath = "bin/appData/db/";
	
	public static final String CDatabasePath = (executableJarFilePath + CDatabaseName).replaceAll("%20"," "); // erstellt im jarPath die Datenbank IF NOT EXISTS
	
	//"C:\\Users\\PC 1510\\Desktop\\JAVA\\Database\\" + CDatabaseName;
}
