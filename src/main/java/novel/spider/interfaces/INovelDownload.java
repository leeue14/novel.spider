package novel.spider.interfaces;

import novel.spider.configuration.Configuration;

public interface INovelDownload {
	
	/**
	 * ����С˵
	 * @param url ���url��ָĳ��С˵���½��б�ҳ��
	 * @param config ���ص�������
	 * @return
	 */
	public String download(String url, Configuration config);

}
