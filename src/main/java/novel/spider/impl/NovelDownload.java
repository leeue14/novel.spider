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
 * 功能: 下载小说的实现类
 * 
 * @author:李月
 * @Version:
 * @Date 2017年10月11日 下午5:08:34
 * 
 *       实现多线程下载的步骤 1、拿到该网站的某本小说章节列表 2、通过计算，将这些章节分配给指定数量线程，让他们去解析然后保存到文本文件中去
 *       3、通知主线程合并一个文件
 */
public class NovelDownload implements INovelDownload {

	@Override
	public String download(String url, Configuration config) {

		IChapterSpider spider = ChapterSpiderFactory.getChapterSpider(url);// 首先确定用什么规则去爬文章目录
		List<Chapter> chapters = spider.getsChapter(url);// 抓取文章目录
		int size = config.getSize();// 设置每个线程最大能下载几个章节内容
		int maxThreadSize = (int) Math.ceil(chapters.size() * 1.0 / size);// ceil是取最大值，计算需要多少个线程下载
		Map<String, List<Chapter>> downloadTaskAlloc = new HashMap<String, List<Chapter>>();// 每个线程的任务分配

		for (int i = 0; i < maxThreadSize; i++) {// 0-99 100-199 200-299...
			int fromIndex = i * (config.getSize());
			int toIndex = i == maxThreadSize - 1 ? chapters.size() : i
					* config.getSize() + config.getSize();// 最后一个线程特殊处理
			downloadTaskAlloc.put(fromIndex + "-" + toIndex,
					chapters.subList(fromIndex, toIndex));// subList是分割成小块的list
		}
		ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);// 使用线程池来实现多线程下载
		Set<String> keys = downloadTaskAlloc.keySet();// 把每个key先取出来
		List<Future<String>> tasks = new ArrayList<>();// 每一个线程下载完成后会返回一个Future对象
		// 通过这两段代码就可以创建缺失的路径
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
				System.out.println(future.get() + ",下载完成！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		NovelSpiderUtil.multiFileMerge(savePath, null, true);
	
		return savePath + "/merge.txt";
	}

}

// 下载的一个内部类
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
						out.println(detail.getContent());// 向文件写入内容
						break;
					} catch (Exception e) {
						System.err.println("尝试第[" + (i + 1) + "/" + tryTimes
								+ "]次下载失败了！" + chapter);
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
