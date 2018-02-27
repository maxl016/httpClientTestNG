package com.jason.framework;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 * http璁块棶宸ュ叿
 * 
 * @author yuhao
 * @date 20130820
 */
public class HttpUtils {


	private static final int TIME_OUT = 15;

	/**
	 * 閫氳繃HTTP GET 鍙戦�鍙傛暟
	 * 
	 * @param httpUrl
	 * @param parameter
	 * @param httpMethod
	 */
	public static String sendGet(String httpUrl, Map<String, String> parameter) {
		if (parameter == null || httpUrl == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<String, String>> iterator = parameter.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			if (sb.length() > 0) {
				sb.append('&');
			}
			Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String value;
			try {
				value = URLEncoder.encode(entry.getValue(),
						HttpConst.DEFAULT_HTTP_ENCODING);
			} catch (UnsupportedEncodingException e) {
				value = "";
			}
			sb.append(key).append('=').append(value);
		}
		String urlStr = null;
		if (httpUrl.lastIndexOf('?') != -1) {
			urlStr = httpUrl + '&' + sb.toString();
		} else {
			urlStr = httpUrl + '?' + sb.toString();
		}

		HttpURLConnection httpCon = null;
		String responseBody = null;
		try {
			URL url = new URL(urlStr);
			httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod(HttpConst.HTTP_GET);
			httpCon.setConnectTimeout(TIME_OUT * 1000);
			httpCon.setReadTimeout(TIME_OUT * 1000);
			// 寮�璇诲彇杩斿洖鐨勫唴瀹�
			InputStream in = httpCon.getInputStream();
			byte[] readByte = new byte[1024];
			// 璇诲彇杩斿洖鐨勫唴瀹�
			int readCount = in.read(readByte, 0, 1024);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while (readCount != -1) {
				baos.write(readByte, 0, readCount);
				readCount = in.read(readByte, 0, 1024);
			}
			responseBody = new String(baos.toByteArray(),
					HttpConst.DEFAULT_HTTP_ENCODING);
			baos.close();
		} catch (Exception e) {
		} finally {
			if (httpCon != null)
				httpCon.disconnect();
		}
		return responseBody;
	}

	/**
	 * 浣跨敤HTTP POST 鍙戦�鏂囨湰
	 * 
	 * @param httpUrl
	 *            鍙戦�鐨勫湴鍧�
	 * @param postBody
	 *            鍙戦�鐨勫唴瀹�
	 * @return 杩斿洖HTTP SERVER鐨勫鐞嗙粨鏋�濡傛灉杩斿洖null,鍙戦�澶辫触
	 */
	public static String sentPost(String httpUrl, String postBody) {
		return sentPost(httpUrl, postBody, HttpConst.DEFAULT_HTTP_ENCODING,
				null);
	}

	/**
	 * 浣跨敤HTTP POST 鍙戦�鏂囨湰
	 * 
	 * @param httpUrl
	 *            鍙戦�鐨勫湴鍧�
	 * @param postBody
	 *            鍙戦�鐨勫唴瀹�
	 * @return 杩斿洖HTTP SERVER鐨勫鐞嗙粨鏋�濡傛灉杩斿洖null,鍙戦�澶辫触
	 */
	public static String sentPost(String httpUrl, String postBody,
			String encoding) {
		return sentPost(httpUrl, postBody, encoding, null);
	}

	/**
	 * 浣跨敤HTTP POST 鍙戦�鏂囨湰
	 * 
	 * @param httpUrl
	 *            鐩殑鍦板潃
	 * @param postBody
	 *            post鐨勫寘浣�
	 * @param headerMap
	 *            澧炲姞鐨凥ttp澶翠俊鎭�
	 * @return
	 */
	public static String sentPost(String httpUrl, String postBody,
			Map<String, String> headerMap) {
		return sentPost(httpUrl, postBody, HttpConst.DEFAULT_HTTP_ENCODING,
				headerMap);
	}

	/**
	 * 浣跨敤HTTP POST 鍙戦�鏂囨湰
	 * 
	 * @param httpUrl
	 *            鍙戦�鐨勫湴鍧�
	 * @param postBody
	 *            鍙戦�鐨勫唴瀹�
	 * @param encoding
	 *            鍙戦�鐨勫唴瀹圭殑缂栫爜
	 * @param headerMap
	 *            澧炲姞鐨凥ttp澶翠俊鎭�
	 * @return 杩斿洖HTTP SERVER鐨勫鐞嗙粨鏋�濡傛灉杩斿洖null,鍙戦�澶辫触
	 */
	public static String sentPost(String httpUrl, String postBody,
			String encoding, Map<String, String> headerMap) {
		HttpURLConnection httpCon = null;
		String responseBody = null;
		URL url = null;
		try {
			url = new URL(httpUrl);
		} catch (MalformedURLException e1) {
			return null;
		}
		try {
			httpCon = (HttpURLConnection) url.openConnection();
		} catch (IOException e1) {
			return null;
		}
		if (httpCon == null) {
			return null;
		}
		httpCon.setDoOutput(true);
		httpCon.setConnectTimeout(TIME_OUT * 1000);
		httpCon.setReadTimeout(TIME_OUT * 1000);
		httpCon.setDoOutput(true);
		httpCon.setUseCaches(false);
		try {
			httpCon.setRequestMethod(HttpConst.HTTP_POST);
		} catch (ProtocolException e1) {
			return null;
		}
		if (headerMap != null) {
			Iterator<Entry<String, String>> iterator = headerMap.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				httpCon.addRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		OutputStream output;
		try {
			output = httpCon.getOutputStream();
		} catch (IOException e1) {
			return null;
		}
		try {
			output.write(postBody.getBytes(encoding));
		} catch (UnsupportedEncodingException e1) {
			return null;
		} catch (IOException e1) {
			return null;
		}
		try {
			output.flush();
			output.close();
		} catch (IOException e1) {
			return null;
		}

		int code = -1;
		String result = null;
		try {
			code = httpCon.getResponseCode();
			result = httpCon.getResponseMessage();
		} catch (IOException e) {
		}
		

		// 寮�璇诲彇杩斿洖鐨勫唴瀹�
		InputStream in;
		try {
			in = httpCon.getInputStream();
		} catch (IOException e1) {
			return null;
		}
		/**
		 * 杩欎釜鏂规硶鍙互鍦ㄨ鍐欐搷浣滃墠鍏堝緱鐭ユ暟鎹祦閲屾湁澶氬皯涓瓧鑺傚彲浠ヨ鍙栥� 闇�娉ㄦ剰鐨勬槸锛屽鏋滆繖涓柟娉曠敤鍦ㄤ粠鏈湴鏂囦欢璇诲彇鏁版嵁鏃讹紝涓�埇涓嶄細閬囧埌闂锛�
		 * 浣嗗鏋滄槸鐢ㄤ簬缃戠粶鎿嶄綔锛屽氨缁忓父浼氶亣鍒颁竴浜涢夯鐑︺�
		 * 姣斿锛孲ocket閫氳鏃讹紝瀵规柟鏄庢槑鍙戞潵浜�000涓瓧鑺傦紝浣嗘槸鑷繁鐨勭▼搴忚皟鐢╝vailable()鏂规硶鍗村彧寰楀埌900锛屾垨鑰�00锛岀敋鑷虫槸0锛�
		 * 鎰熻鏈夌偣鑾悕鍏跺锛屾�涔堜篃鎵句笉鍒板師鍥犮� 鍏跺疄锛岃繖鏄洜涓虹綉缁滈�璁線寰�槸闂存柇鎬х殑锛屼竴涓插瓧鑺傚線寰�垎鍑犳壒杩涜鍙戦�銆�
		 * 鏈湴绋嬪簭璋冪敤available()鏂规硶鏈夋椂寰楀埌0锛岃繖鍙兘鏄鏂硅繕娌℃湁鍝嶅簲锛屼篃鍙兘鏄鏂瑰凡缁忓搷搴斾簡锛屼絾鏄暟鎹繕娌℃湁閫佽揪鏈湴銆�
		 * 瀵规柟鍙戦�浜�000涓瓧鑺傜粰浣狅紝涔熻鍒嗘垚3鎵瑰埌杈撅紝杩欎綘灏辫璋冪敤3娆vailable()鏂规硶鎵嶈兘灏嗘暟鎹�鏁板叏閮ㄥ緱鍒般�
		 * 
		 * 缁忓父鍑虹幇size涓�鐨勬儏鍐碉紝瀵艰嚧涓嬮潰readCount涓�浣夸箣姝诲惊鐜�while (readCount != -1)
		 * {xxxx})锛屽嚭鐜版鏈洪棶棰�
		 */
		int size = 0;
		try {
			size = in.available();
		} catch (IOException e1) {
			return null;
		}
		if (size == 0) {
			size = 1024;
		}
		byte[] readByte = new byte[size];
		// 璇诲彇杩斿洖鐨勫唴瀹�
		int readCount = -1;
		try {
			readCount = in.read(readByte, 0, size);
		} catch (IOException e1) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while (readCount != -1) {
			baos.write(readByte, 0, readCount);
			try {
				readCount = in.read(readByte, 0, size);
			} catch (IOException e) {
				return null;
			}
		}
		try {
			responseBody = new String(baos.toByteArray(), encoding);
		} catch (UnsupportedEncodingException e) {
			return null;
		} finally {
			if (httpCon != null) {
				httpCon.disconnect();
			}
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
				}
			}
		}
		return responseBody;
	}

	

	/**
	 * 浣跨敤HTTP POST 鍙戦�鏂囨湰
	 * 
	 * @param httpUrl
	 *            鍙戦�鐨勫湴鍧�涓嶄负绌�
	 * @param postBody
	 *            鍙戦�鐨勫唴瀹�涓嶄负绌�
	 * @param encoding
	 *            鍙戦�鐨勫唴瀹圭殑缂栫爜 涓嶄负绌�
	 * @param sign
	 *            鍙戦�鐨勫瘑閽ョ鍚�涓嶄负绌�
	 * @return 杩斿洖HTTP SERVER鐨勫鐞嗙粨鏋�濡傛灉杩斿洖null,鍙戦�澶辫触
	 */

	public static void main(String[] args) { 
		// bd0517968eca3989de83d00dd13cde5f
		// sb.append(version).append(merID).append(payMoney).append(orderId).append(returnUrl).append(cardInfo).append("").append("1").append(privateKey);
		// try {
		// String t =
		// URLEncoder.encode("RdGy/mh3hhdwVPBKfQblxQ+w3w0YEtU1j3k0gJkV1YpCaQgvZcReDw==","utf-8");
		// System.out.println(t);
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// //ok String
		// sign="notify_data=%3Cnotify%3E%3Cpartner%3E2088701364541757%3C%2Fpartner%3E%3Cdiscount%3E0.00%3C%2Fdiscount%3E%3Cpayment_type%3E1%3C%2Fpayment_type%3E%3Csubject%3E%E6%80%AA%E7%89%A9%E5%90%83%E7%B3%96%E6%9E%9C%3C%2Fsubject%3E%3Ctrade_no%3E2012010464821875%3C%2Ftrade_no%3E%3Cbuyer_email%3E13147099771%3C%2Fbuyer_email%3E%3Cgmt_create%3E2012-01-04+11%3A44%3A19%3C%2Fgmt_create%3E%3Cquantity%3E1%3C%2Fquantity%3E%3Cout_trade_no%3E03112010411151850018_100212000945%3C%2Fout_trade_no%3E%3Cseller_id%3E2088701364541757%3C%2Fseller_id%3E%3Ctrade_status%3ETRADE_FINISHED%3C%2Ftrade_status%3E%3Cis_total_fee_adjust%3EN%3C%2Fis_total_fee_adjust%3E%3Ctotal_fee%3E0.01%3C%2Ftotal_fee%3E%3Cgmt_payment%3E2012-01-04+11%3A44%3A19%3C%2Fgmt_payment%3E%3Cseller_email%3Eyyhpay%40appchina.com%3C%2Fseller_email%3E%3Cgmt_close%3E2012-01-04+11%3A44%3A19%3C%2Fgmt_close%3E%3Cprice%3E0.01%3C%2Fprice%3E%3Cbuyer_id%3E2088702562178750%3C%2Fbuyer_id%3E%3Cuse_coupon%3EN%3C%2Fuse_coupon%3E%3C%2Fnotify%3E&sign=VAQYdhQzOld6jZb0QrCg16GNLMuutgTAiVLCUWc5qd5ebl5C3zdnVe3KCtLiEcD0EYzLbVVp%2B%2FATFLinqUf5uzyBOUXuEVU6ceIjLXc2C6rHARaFRDmrFJe4CPCYSB%2FO2NPCqPEesMtCOIyJPv5trX23KTeYWBzJdxnhr%2BnEyUk%3D";
		// String sign =
		// "sign=ODK%2Fux5nuv1MnZp10%2FmbnWGfh4BegNEjQpxtwMNKCkuZ4q0bS89q6Eelz%2F1enqMF7pRW4Ls0CpSzONI%2FwX9jqoFT0Evpf2lYuwmbrSJS2%2F3wsVpdjEP1Ym%2BxKulhM0CRqMLl%2BA9Ij6M010Go7r9qzx08XH9Qatst0Lb%2FxCaRtnY%3D&sign_type=RSA&notify_data=%3Cnotify%3E%3Cseller_email%3Eyyhpay%40appchina.com%3C%2Fseller_email%3E%3Cpartner%3E2088701364541757%3C%2Fpartner%3E%3Cpayment_type%3E1%3C%2Fpayment_type%3E%3Cbuyer_email%3E13147099771%3C%2Fbuyer_email%3E%3Ctrade_no%3E2012010467763575%3C%2Ftrade_no%3E%3Cbuyer_id%3E2088702562178750%3C%2Fbuyer_id%3E%3Cquantity%3E1%3C%2Fquantity%3E%3Ctotal_fee%3E0.01%3C%2Ftotal_fee%3E%3Cuse_coupon%3EN%3C%2Fuse_coupon%3E%3Cis_total_fee_adjust%3EY%3C%2Fis_total_fee_adjust%3E%3Cprice%3E0.01%3C%2Fprice%3E%3Cgmt_create%3E2012-01-04+14%3A15%3A14%3C%2Fgmt_create%3E%3Cout_trade_no%3E00012022713281500001%3C%2Fout_trade_no%3E%3Cseller_id%3E2088701364541757%3C%2Fseller_id%3E%3Csubject%3E%E6%80%AA%E7%89%A9%E5%90%83%E7%B3%96%E6%9E%9C%3C%2Fsubject%3E%3Ctrade_status%3ETRADE_FINISHED%3C%2Ftrade_status%3E%3Cdiscount%3E0.00%3C%2Fdiscount%3E%3C%2Fnotify%3E";
		// sentPost("http://192.168.0.143:8080/alipay/payment/notifyCallBackUrl",
		// sign, "UTF-8");

		String sign = "privateField=00012022713281500001&payResult=0&cardMoney=11";
		for (int i = 0; i < 10000; i++) {
			String result = sentPost("http://192.168.0.82:7771/wen", sign,
					"UTF-8");
			System.out.println(i + "---" + result);
		}

	}
}
