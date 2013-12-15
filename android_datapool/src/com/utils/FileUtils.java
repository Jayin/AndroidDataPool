package com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {
	private final static  String FileUtils_Defalut_charsetName="UTF-8";
	
	  /**
     * get folder name from path
     *  
     * <pre>
     *      getFolderName(null)               =   null
     *      getFolderName("")                 =   ""
     *      getFolderName("   ")              =   ""
     *      getFolderName("a.mp3")            =   ""
     *      getFolderName("a.b.rmvb")         =   ""
     *      getFolderName("abc")              =   ""
     *      getFolderName("c:\\")              =   "c:"
     *      getFolderName("c:\\a")             =   "c:"
     *      getFolderName("c:\\a.b")           =   "c:"
     *      getFolderName("c:a.txt\\a")        =   "c:a.txt"
     *      getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
     *      getFolderName("/home/admin")      =   "/home"
     *      getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
     * </pre>
     * 
     * @param filePath
     * @return
     */
    public static String getFolderName(String filePath) {

        if (filePath==null||filePath.length()==0) {
            return filePath;
        }
        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

	
	/**
     * Creates the directory named by the trailing filename of this file, including the complete directory path required
     * to create this directory. <br/>
     * <br/>
     * <ul>
     * <strong>Attentions:</strong>
     * <li>makeDirs("C:\\Users\\Trinea") can only create users folder</li>
     * <li>makeFolder("C:\\Users\\Trinea\\") can create Trinea folder</li>
     * </ul>
     * 
     * @param filePath
     * @return true if the necessary directories have been created or the target directory already exists, false one of
     * the directories can not be created.
     * <ul>
     * <li>if {@link FileUtils#getFolderName(String)} return null, return false</li>
     * <li>if target directory already exists, return true</li>
     * <li>return {@link java.io.File#makeFolder}</li>
     * </ul>
     */
    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (folderName==null || folderName.length()==0) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }
	/**
     * read file
     * 
     * @param filePath
     * @param charsetName The name of a supported {@link java.nio.charset.Charset </code>charset<code>}
     * @return if file not exist, return null, else return content of file
     * @throws RuntimeException if an error occurs while operator BufferedReader
     */
	public static String readFile(String filePath, String charsetName) {
		File file = new File(filePath);
		StringBuilder fileContent = new StringBuilder("");
		if (file == null || !file.isFile()) {
			return null;
		}
		BufferedReader reader = null;
		try {
			InputStreamReader is = new InputStreamReader(new FileInputStream(
					file), charsetName);
			reader = new BufferedReader(is);
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (!fileContent.toString().equals("")) {
					fileContent.append("\r\n");
				}
				fileContent.append(line);
			}
			reader.close();
			return fileContent.toString();
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}
	/**
	 * read file <br>
	 * charsetName defalut UTF-8
	 * @param filePath  filePath,
	 * @return
	 */
	public static String readFile(String filePath){
		return readFile(filePath, FileUtils_Defalut_charsetName);
	}
	
	 /**
     * write file
     * 
     * @param filePath
     * @param content
     * @param append is append, if true, write to the end of file, else clear content of file and write into it
     * @return return true
     * @throws RuntimeException if an error occurs while operator FileWriter
     */
    public static boolean writeFile(String filePath, String content, boolean append) {
        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }
//	public static void main(String[] args) {
//		String filePath = "D:\\e.txt";
//		System.out.println(readFile(filePath));
//	}
}
