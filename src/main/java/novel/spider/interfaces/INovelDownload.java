package novel.spider.interfaces;

import novel.spider.configuration.Configuration;

public interface INovelDownload {
	
	/**
	 * 下载小说
	 * @param url 这个url是指某个小说的章节列表页面
	 * @param config 下载的配置类
	 * @return
	 */
	public String download(String url, Configuration config);

}
