package org.hy.foundation.utils.net;

import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.hy.foundation.utils.file.FileUtil;



/**
 * 网络请求常用类的整理，包括几大常用功能:<br>
 * <ol>
 * <li>发送网络请求</li>
 * <li>其他</li>
 * </ol>
 * 
 * @date 2011-4-21
 * @author MipatchTeam#chenc
 * 
 */
public class NetUtil {

	// --------------------- 1. --------------------------------
	/**
	 * 根据URL发送请求，并获得内容
	 */
	public static String sendRequest(String url) throws Exception {
		url = url.replaceAll(" ", "%20");
		GetMethod method = new GetMethod(url);
		HttpClient hc = new HttpClient();
		hc.executeMethod(method);
		InputStream resStream = method.getResponseBodyAsStream();
		String result = FileUtil.readStream(resStream);

		return result;
	}

	/**
	 * 根据URL发送请求，附加COOKIES，并获得内容
	 */
	public static String sendRequest(String url, String cookies)
			throws Exception {
		url = url.replaceAll(" ", "%20");
		GetMethod method = new GetMethod(url);
		method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		method.setRequestHeader("Cookie", cookies);

		HttpClient hc = new HttpClient();
		hc.executeMethod(method);
		InputStream resStream = method.getResponseBodyAsStream();
		String result = FileUtil.readStream(resStream);

		return result;
	}
	// -----------------------------------------------------

}
