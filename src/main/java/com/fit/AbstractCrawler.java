package com.fit;

public abstract class AbstractCrawler {
	private int crawlStrategy = CONTINUOUS_CRAWEL_STRATEGY;

	/**
	 * When sending too many requests to server, your IP may be banned. Therefore,
	 * in such cases, we need to store the results of the previous crawls.
	 * 
	 * We will merge the results of separate crawls into the final result.
	 */
	public static final int CONTINUOUS_CRAWEL_STRATEGY = 0;

	public static final int SEPARATE_CRAWEL_STRATEGY = 1;

	public void setCrawlStrategy(int crawlStrategy) {
		this.crawlStrategy = crawlStrategy;
	}

	public int getCrawlStrategy() {
		return crawlStrategy;
	}
}
