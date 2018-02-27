package com.jason.framework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CookieManager {
	private static CookieManager cookieManager = new CookieManager();
	private Map<String, Map<String, String>> cookies = new ConcurrentHashMap<String, Map<String, String>>();

	private CookieManager() {
	}

	public String getCookies(final String domain) {
		Map<String, String> domainCookies = cookies
				.get(getTopLevelDomain(domain));
		if (domainCookies != null) {
			StringBuilder sb = new StringBuilder();
			boolean isFirst = true;
			for (Map.Entry<String, String> cookieEntry : domainCookies
					.entrySet()) {
				if (!isFirst) {
					sb.append("; ");
				} else {
					isFirst = false;
				}
				sb.append(cookieEntry.getKey()).append("=")
						.append(cookieEntry.getValue());
			}
			return sb.toString();
		}
		return "";
	}

	public void setCookies(final String domain, final String cookiesString) {
		Map<String, String> domainCookies = cookies
				.get(getTopLevelDomain(domain));
		if (domainCookies == null) {
			domainCookies = new ConcurrentHashMap<String, String>();
			cookies.put(getTopLevelDomain(domain), domainCookies);
		}
		String[] cookies = cookiesString.split("; ");
		for (String cookie : cookies) {
			if (cookie != null && !cookie.trim().isEmpty()
					&& cookie.indexOf("=") > 0) {
				int equalMarkIndex = cookie.indexOf("=");
				String key = cookie.substring(0, equalMarkIndex);
				String value = cookie.substring(equalMarkIndex + 1);
				domainCookies.put(key, value);
			}
		}
	}

	public void removeCookies(final String domain) {
		cookies.remove(getTopLevelDomain(domain));
	}

	public static CookieManager getInstance() {
		return cookieManager;
	}

	public String getTopLevelDomain(final String domain) {
		if (domain == null) {
			return null;
		}
		String http = "http://";
		String url = domain.substring(http.length());
		int index = url.indexOf("/");
		if (index > -1)
			url = url.substring(0, index);
		if (!domainToTopLevelDomainMap.containsKey(url)) {
			domainToTopLevelDomainMap.put(url, url);
		}
		return domainToTopLevelDomainMap.get(url);
	}

	private Map<String, String> domainToTopLevelDomainMap = new ConcurrentHashMap<String, String>();
}
