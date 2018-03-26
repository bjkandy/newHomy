package common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.ServiceCommonException;


/** 
* 压缩文件工具类
*/
public class ZipUtils {
    
	final Logger logger = LoggerFactory.getLogger(ZipUtils.class);
    public static void doCompress(String srcFile, String zipFile) throws Exception {
        doCompress(new File(srcFile), new File(zipFile));
    }
    
    /**
     * 文件压缩
     * @param srcFile  目录或者单个文件
     * @param destFile 压缩后的ZIP文件
     */
    public static void doCompress(File srcFile, File destFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destFile));
        if(srcFile.isDirectory()){
            File[] files = srcFile.listFiles();
            for(File file : files){
                doCompress(file, out);
            }
        }else {
            doCompress(srcFile, out);
        }
    }
    
    /**
     * 文件压缩
     * @param file  单个文件
     * @param out 输出流io
     * @param pathName 从压缩文件夹开始的子路径
     */
    public static void doCompress(File file, ZipOutputStream out, String pathName) throws IOException{
        if( file.exists() ){
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(pathName));
            int len = 0 ;
            // 读取文件的内容,打包到zip文件    
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
//            out.flush();
//          out.closeEntry();
            fis.close();
        }else{
        	throw new ServiceCommonException("4001","文件:"+file.getAbsolutePath()+"不存在！");
        }
    }
    
    public static void doCompress(String pathname, ZipOutputStream out) throws IOException{
    	File file = new File(pathname);
    	
        doCompress(file, out);
    }
    
    public static void doCompress(File file, ZipOutputStream out) throws IOException{
        if( file.exists() ){
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(file.getName()));
            int len = 0 ;
            // 读取文件的内容,打包到zip文件    
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.closeEntry();
            fis.close();
        }else{
        	throw new ServiceCommonException("4001","文件不存在！");
        }
    }
    //二维码下载 不压缩
    public static void qrcodeUtil(File file, OutputStream out) throws IOException{
        if( file.exists() ){
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            int len = 0 ;
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
            fis.close();
        }else{
        	throw new ServiceCommonException("4001","文件不存在！");
        }
    }
    
    //用户部分照片下载
    public static void downloadPicture(File file, OutputStream out) throws IOException{
        if( file.exists() ){
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            int len = 0 ;
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
            fis.close();
        }else{
        	throw new ServiceCommonException("4001","文件不存在！");
        }
    }
    
    
}