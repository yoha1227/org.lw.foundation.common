package org.hy.foundation.utils.framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取配置，填充至静态属性
 * 
 * @date 2009-04-22
 * @author MipatchTeam#chenc
 * 
 */
public class Config {

	private static Logger logger = Logger.getLogger(Config.class);
	private static String configFile = ClassLoader.getSystemResource("")
			.toString().replaceAll("file:/", "")
			+ "config/config.ini";// 配置文件
	// ====================数据库相关配置=======================
	private static String driver;
	private static String url;
	private static String user;
	private static String pwd;

	private static Integer initCon;
	private static Integer maxCon;
	private static Integer incCon;
	private static Integer overtime;
	private static Integer sleeptime;

	static {
		load();
	}

	/**
	 * 从文件中读取属性
	 */
	private static void load() {
		InputStreamReader fis = null;
		try {
			fis = new InputStreamReader(new FileInputStream(configFile),
					"utf-8");
			Properties dbField = new Properties();
			dbField.load(fis);

			driver = dbField.getProperty("driver", "");
			url = dbField.getProperty("url", "");
			user = dbField.getProperty("user", "");
			pwd = dbField.getProperty("password", "");
			initCon = Integer.parseInt(dbField.getProperty("initcon", "3"));
			maxCon = Integer.parseInt(dbField.getProperty("maxcon", "10"));
			incCon = Integer.parseInt(dbField.getProperty("inccon", "3"));
			overtime = Integer.parseInt(dbField.getProperty("overtime", "60"));
			sleeptime = Integer
					.parseInt(dbField.getProperty("sleeptime", "30"));

		} catch (FileNotFoundException e) {
			logger.error("config/config.ini 配置文件不存在");
		} catch (IOException e) {
			logger.error("读取 config/config.ini 配置文件错误");
		}
	}

	/**
	 * 重新获取属性
	 */
	public static void reload() {
		load();
	}

	public static String getDriver() {
		return driver;
	}

	public static String getUrl() {
		return url;
	}

	public static String getUser() {
		return user;
	}

	public static String getPwd() {
		return pwd;
	}

	public static Integer getInitCon() {
		return initCon;
	}

	public static Integer getMaxCon() {
		return maxCon;
	}

	public static Integer getIncCon() {
		return incCon;
	}

	public static Integer getOvertime() {
		return overtime;
	}

	public static Integer getSleeptime() {
		return sleeptime;
	}

}
