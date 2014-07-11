package com.cyou.fz.common.base.util;

import com.cyou.fz.common.base.exception.UnCaughtException;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.net.URL;


/**
 * IO流工具.
 * 
 * @author yangz
 * @date 2013-1-31 下午3:07:15
 */
public class IOUtil {
	/**
	 * 读取classpath下的文件流.
	 * @param path 相对于classPath
	 * @return
	 * @author yangz
	 * @date 2012-7-28 下午04:50:52
	 */
	public static InputStream getClassPathInputStream(String path){
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	}
	/**
	 * 流拷贝.
	 * @param input
	 * @param output
	 * @author yangz
	 * @date 2013-2-19 下午6:53:27
	 */
	public static void copy(InputStream input, OutputStream output){
		try {
			IOUtils.copy(input, output);
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}
	
	/**
	 * 关闭输入输出流.
	 * @param stream
	 * @author yangz
	 * @date 2013-2-19 下午6:51:18
	 */
	public static void close(Object stream){
		try {
			if(stream == null){
				return;
			}else if (stream instanceof OutputStream) {
				OutputStream out = (OutputStream) stream;
				out.close();
			}else if(stream instanceof InputStream) {
				InputStream in = (InputStream) stream;
				in.close();
			}else if(stream instanceof PrintWriter) {
				PrintWriter pw = (PrintWriter) stream;
				pw.close();
			}else{
				stream.getClass().getMethod("close").invoke(stream);
			}
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}
	}
	
	/**
	 * 读取文件的二进制数据.
	 * @param file
	 * @return
	 * @author yangz
	 * @date 2013-5-7 上午12:34:26
	 */
	public static byte[] getBytes(File file){
		BufferedInputStream bufferedInputStream = null;
		try {
			byte[] result = new byte[(int) file.length()];
			bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
			if(bufferedInputStream.read(result) != (int) file.length()){
				throw new UnCaughtException("file read length error.");
			}
			return result;
		} catch (Exception e) {
			throw new UnCaughtException(e);
		}finally{
			close(bufferedInputStream);
		}
	}
	/**
	 * 网络资源是否存在.
	 * @param url
	 * @return
	 * @author yangz
	 * @date 2013-6-12 下午4:32:45
	 */
	public static boolean urlExists(String url){
		try {
			IOUtil.close(new URL(url).openStream());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
