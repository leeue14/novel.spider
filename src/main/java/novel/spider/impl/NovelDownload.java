package novel.spider.impl;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import novel.spider.NovelSiteEnum;
import novel.spider.configuration.Configuration;
import novel.spider.entitys.Chapter;
import novel.spider.entitys.ChapterDetail;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.utils.ChapterDetailSpiderFactory;
import novel.spider.utils.ChapterSpiderFactory;
import novel.spider.utils.NovelSpiderUtil;

/**
 * 
 * ����: ����С˵��ʵ����
 * 
 * @author:����
 * @Version:
 * @Date 2017��10��11�� ����5:08:34
 * 
 *       ʵ�ֶ��߳����صĲ��� 1���õ�����վ��ĳ��С˵�½��б� 2��ͨ�����㣬����Щ�½ڷ����ָ�������̣߳�������ȥ����Ȼ�󱣴浽�ı��ļ���ȥ
 *       3��֪ͨ���̺߳ϲ�һ���ļ�
 */
public class NovelDownload implements INovelDownload {

	@Override
	public String download(String url, Configuration config) {

		IChapterSpider spider = ChapterSpiderFactory.getChapterSpider(url);// ����ȷ����ʲô����ȥ������Ŀ¼
		List<Chapter> chapters = spider.getsChapter(url);// ץȡ����Ŀ¼
		int size = config.getSize();// ����ÿ���߳���������ؼ����½�����
		int maxThreadSize = (int) Math.ceil(chapters.size() * 1.0 / size);// ceil��ȡ���ֵ��������Ҫ���ٸ��߳�����
		Map<String, List<Chapter>> downloadTaskAlloc = new HashMap<String, List<Chapter>>();// ÿ���̵߳��������

		for (int i = 0; i < maxThreadSize; i++) {// 0-99 100-199 200-299...
			int fromIndex = i * (config.getSize());
			int toIndex = i == maxThreadSize - 1 ? chapters.size() : i
					* config.getSize() + config.getSize();// ���һ���߳����⴦��
			downloadTaskAlloc.put(fromIndex + "-" + toIndex,
					chapters.subList(fromIndex, toIndex));// subList�Ƿָ��С���list
		}
		ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);// ʹ���̳߳���ʵ�ֶ��߳�����
		Set<String> keys = downloadTaskAlloc.keySet();// ��ÿ��key��ȡ����
		List<Future<String>> tasks = new ArrayList<>();// ÿһ���߳�������ɺ�᷵��һ��Future����
		// ͨ�������δ���Ϳ��Դ���ȱʧ��·��
		String savePath = config.getPath() + "/"+ NovelSiteEnum.getEnumByUrl(url).getUrl();
		new File(savePath).mkdirs();
		
		
		for (String key : keys) {
			tasks.add(service.submit(new DownloadCallabel(savePath
					+ "/" + key + ".leeue", downloadTaskAlloc.get(key), config
					.getTryTimes())));
			// System.out.println(key);
		}
		service.shutdown();
		for (Future<String> future : tasks) {
			try {
				System.out.println(future.get() + ",������ɣ�");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		NovelSpiderUtil.multiFileMerge(savePath, null, true);
	
		return savePath + "/merge.txt";
	}

}

// ���ص�һ���ڲ���
class DownloadCallabel implements Callable<String> {
	private List<Chapter> chapters;
	private String path;
	private int tryTimes;

	public DownloadCallabel(String path, List<Chapter> chapters, int tryTimes) {
		this.path = path;
		this.chapters = chapters;
		this.tryTimes = tryTimes;
	}

	@Override
	public String call() throws Exception {

		// PrintWriter out = null;
		try (PrintWriter out = new PrintWriter(new File(path), "UTF-8")) {

			// out = new PrintWriter(new File(path));
			for (Chapter chapter : chapters) {
				IChapterDetailSpider spider = ChapterDetailSpiderFactory
						.getChapterDetailSpider(chapter.getUrl());
				ChapterDetail detail = null;
				for (int i = 0; i < tryTimes; i++) {
					try {
						detail = spider.getChapterDetail(chapter.getUrl());
						out.println(detail.getTitle());
						out.println(detail.getContent());// ���ļ�д������
						break;
					} catch (Exception e) {
						System.err.println("���Ե�[" + (i + 1) + "/" + tryTimes
								+ "]������ʧ���ˣ�" + chapter);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return path;
	}

}
