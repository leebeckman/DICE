package org.jresearch.gossip;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TestLogger {
	private Logger logger;
	private static TestLogger self;
	
	public enum TaintLogType {
		PROPAGATION, FUZZYPROPAGATION, MODIFICATION, COMPOSITION, ASSOCIATION, CALLING, RETURNING
	}
	
	private TestLogger() {
		try {
			LogManager lm = LogManager.getLogManager();
			
			FileHandler jghand = new FileHandler("/home/lee/DICE/jglog.log");
			jghand.setFormatter(new SimpleFormatter());
			
//			SocketHandler shTaint = new SocketHandler("localhost", 8687);
//			shTaint.setFormatter(new SimpleFormatter());
			
			logger = Logger.getLogger("TestLogger");
			logger.setLevel(Level.INFO);

			logger.addHandler(jghand);
			
			lm.addLogger(logger);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static TestLogger getTestLogger() {
		if (self == null) {
			self = new TestLogger();
		}
		
		return self;
	}
	
	public void log(String message) {
		logger.log(Level.INFO, message);
	}
	
}