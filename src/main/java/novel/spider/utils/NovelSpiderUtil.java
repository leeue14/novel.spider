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
 * ����: ������Ѿ��ں�̨���غõĹ�����
 * @author:����
 * @Version:
 * @Date 2017��10��9�� ����8:20:54
 */
public class NovelSpiderUtil {
	private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<>();//��ŵ�ǰ����֧�ֵ���վ
	static {
		init();
	}
	/**
	 * �������õ�Spider-Rule.xml�ļ����õ����õĹ���
	 */
	private static void init(){
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File("config/Spider-Rule.xml"));
			Element root = doc.getRootElement();//��ȡ���ڵ�
			List<Element> sites = root.elements("site");//��ȡ���ڵ���������е�site�ڵ�
			for(Element site : sites){
				List<Element> subs = site.elements();
				Map<String, String> subMap = new HashMap<>();
				for(Element sub: subs){
					String name = sub.getName();
					String text = sub.getText();
					subMap.put(name, text);
				}
				CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")), subMap);//�����֧�������վ�ͻ᷵���Ѹ�ö�ٶ�����Ϊkey
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �õ���Ӧ��վ�Ľ�������,����Spider-Rule.xml���õ��ļ�
	 */
	public static Map<String, String> getContext(NovelSiteEnum novelSiteEnum){
		return CONTEXT_MAP.get(novelSiteEnum);
	}

	/**
	 * ����ļ��ϲ�Ϊһ���ļ����ϲ����򣺰��ļ����ָ�����
	 * @param path ����Ŀ¼���ø�Ŀ¼�µ������ļ������Ա��ϲ���mergeToFile
	 * @param mergeToFile ���ϲ����ļ��������������Ϊnull�ϲ�����ļ�������path/merge.txt
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
			throw new RuntimeException("���ص��ļ�������");
		}finally{
			out.close();
		}
		
	}
	/**
	 * ��ȡĳ��·��������fileType�����ļ�
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
