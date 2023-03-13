package testme;

import java.io.IOException;
import application.Singleton;

public class Run {
	
	public static void main(String[] args) throws IOException{
		Singleton.createApplication();
		Singleton.startApplication();
	}

}