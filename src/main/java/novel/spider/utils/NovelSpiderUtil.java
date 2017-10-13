package novel.spider.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;

import org.apache.http.entity.InputStreamEntity;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import novel.spider.NovelSiteEnum;
/**
 * 
 * 功能: 这个是已经在后台加载好的工具类
 * @author:李月
 * @Version:
 * @Date 2017年10月9日 下午8:20:54
 */
public class NovelSpiderUtil {
	private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<>();//存放当前可以支持的网站
	static {
		init();
	}
	/**
	 * 解析配置的Spider-Rule.xml文件，拿到配置的规则
	 */
	private static void init(){
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File("config/Spider-Rule.xml"));
			Element root = doc.getRootElement();//获取根节点
			List<Element> sites = root.elements("site");//获取根节点的下面所有的site节点
			for(Element site : sites){
				List<Element> subs = site.elements();
				Map<String, String> subMap = new HashMap<>();
				for(Element sub: subs){
					String name = sub.getName();
					String text = sub.getText();
					subMap.put(name, text);
				}
				CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")), subMap);//如果有支持这个网站就会返回已给枚举对象做为key
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 拿到对应网站的解析规则,即在Spider-Rule.xml配置的文件
	 */
	public static Map<String, String> getContext(NovelSiteEnum novelSiteEnum){
		return CONTEXT_MAP.get(novelSiteEnum);
	}

	/**
	 * 多个文件合并为一个文件，合并规则：按文件名分割排序
	 * @param path 基础目录，该根目录下的所有文件都可以被合并到mergeToFile
	 * @param mergeToFile 被合并的文件，这个参数可以为null合并后的文件保存在path/merge.txt
	 * @param b
	 */
	public static void multiFileMerge(String path, String  mergeToFile, boolean deleteFile) {
		mergeToFile = mergeToFile == null?path+"/merge.txt":mergeToFile;
		
		File[] files = getPathAllFileList(path, ".leeue");
	
		Arrays.sort(files,new Comparator<File>() {

			@Override
			public int compare(File o1, File o2) {
				int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
				int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
				if(o1Index > o2Index){
					return 1;
				}else if (o1Index < o2Index) {
					return -1;
				}else{
					return 0;
				}
				
			}
		});
		for(File file : files){
			System.out.println(file.getName());
		}
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(mergeToFile),"UTF-8");
			for(File file : files){
				BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
				String line = null;
				while((line = buff.readLine())!=null){
					out.println(line);
				}
				buff.close();
				if(deleteFile){
					file.delete();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("下载的文件有问题");
		}finally{
			out.close();
		}
		
	}
	/**
	 * 获取某个路径下所有fileType类型文件
	 */
	public static File[]  getPathAllFileList(String path,final String fileType){
		File[] files = new File(path).listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(fileType);
			}
		});
		return files;
	}
	/**
	 * 
	 */
	
	
	
}
