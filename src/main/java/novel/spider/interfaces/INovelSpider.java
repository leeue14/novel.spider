package novel.spider.interfaces;

import java.util.Iterator;
import java.util.List;

import novel.spider.entitys.Novel;

/**
 * 
 * ����: ץȡС˵��վ��ȫ���鼮�б�
 * @author:����
 * @Version:
 * @Date 2017��10��13�� ����9:06:02
 */

public interface INovelSpider {
	
	/**ץȡĳ��ҳ��ʱ����Դ���**/
	public static final int MAX_TRY_TIMES = 3;
	/**
	 *����һ��С˵��վ������url�ͻ᷵��һ��С˵ʵ��
	 *
	 * @param url
	 * @param maxTryTims ��ҳ���ص������� (����ʧ�����ԵĴ���)
	 * @return
	 */
	public List<Novel> getsNovel(String url, Integer maxTryTims);
	/**
	 * �ж��Ƿ�����һҳС˵ʵ��
	 * @return
	 */
	public boolean hasNext();
	/**
	 * ������һҳС˵�б�ҳ��
	 * @return
	 */
	public String next();
	/**
	 * �����С˵����������ÿһҳ��С˵���ó���
	 * @param firstPage
	 * @param maxTryTimes
	 * @return
	 */
	public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes);
	
	/**
	 * ��ȡС˵�ķ���ͼ��
	 * @return
	 */
	public List<String> getsNovelOther(String url, Integer maxTryTims);
	

}
