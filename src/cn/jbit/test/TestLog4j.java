package cn.jbit.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author 任锯东
 */
public class TestLog4j {

	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("C:/log4j.properties");
			Logger logger  =  Logger.getLogger(TestLog4j. class );
			logger.debug("debug");
			logger.error("error");
			logger.info("Info ...");
			logger.warn("Warn ...");
			logger.error("Error ...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
}