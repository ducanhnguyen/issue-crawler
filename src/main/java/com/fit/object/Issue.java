package com.fit.object;

import java.net.URL;

public class Issue {
	private URL url;

	public Issue(URL url) {
		this.url = url;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url.toString();
	}
}
