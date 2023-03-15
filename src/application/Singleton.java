package application;

import java.io.IOException;

public class Singleton {
	
	// Fields
	private static Application app = null;
	
	// Methods
	public static void createApplication(){
		if(app == null)
			app = new Application();
	}
	
	public static void startApplication() throws IOException {
		app.startApplication();
	}
	
}
