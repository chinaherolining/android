package com.example.myapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 功能：文件操作工具类
 *
 * @author lin
 * @2015-6-9 上午10:23:57
 */
public class FileUtil {
    private static final int BUFF_SIZE = 256 * 1024;
    public static final String PICT_NAME = "pict";
    public static final String VIDEO_NAME = "video";
    public static final String AUDIO_NAME = "audio";

    public static final long SIZE_BT = 1024L;
    public static final long SIZE_KB = SIZE_BT * 1024L;
    public static final long SIZE_MB = SIZE_KB * 1024L;
    public static final long SIZE_GB = SIZE_MB * 1024L;
    public static final long SIZE_TB = SIZE_GB * 1024L;
    public static final int SACLE = 2;
    private String PATH = "Android/data/";
    public String WIFI_FILE_NAME = "wifi_connection_log.txt";
    public String NET_FILE_NAME = "net_log.txt";
    public static final String HTTP_CONNECTION_TIME_FILE_NAME = "ALT_HTTP_CONNECTION_TIME.txt";

    /**
     * 功能：获取sd卡根目录
     *
     * @author lin
     * @2015-6-9 上午10:25:22
     */
    public static File getSDCard() {

        return Environment.getExternalStorageDirectory();

    }

    /**
     * 功能：sd卡是否存在
     *
     * @author lin
     * @2015-6-9 上午10:25:03
     */
    public static boolean isSDcardExist() {

        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static void changeFileMode(String f) throws IOException {

        String[] command = {"chmod", "777", f};
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.start();

    }

    /**
     * 功能：写文件
     *
     * @author lin
     * @2015-6-9 上午10:24:28
     */
    public static void writeFile(File f, byte[] bS) throws Exception {

        if (f.isFile() && f.exists()) {
            f.delete();
        }

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(bS, 0, bS.length);
        fos.flush();
        fos.close();
    }

    /**
     * 功能：
     *
     * @author lin
     * @2015-6-9 下午4:15:31
     */
    public File getFileDir(Context context, String fileName) {
        if (isSDcardExist()) {
            //获取sd卡根目录，跟应用的是否卸载无关。
            File sdCardDir = Environment.getExternalStorageDirectory().getAbsoluteFile();//获取SDCard目录
            //  获取该程序的安装包路径
            String path = context.getApplicationContext().getPackageName();
            String paths = sdCardDir + File.separator + PATH + path + File.separator;
            File destDir = new File(paths);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            File saveFile = new File(destDir, fileName);
            return saveFile;
        } else {
            return null;
        }
    }

    /**
     * 功能：向SD卡File中保存数据
     *
     * @author lin
     * @2015-6-9 下午4:52:32
     */
    public void saveDataToSDFile(Context context, String content, String fileName) {
//    	Environment.getExternalFilesDir()获取应用程序下的存储目录，/data/data/your_package/，随着应用的卸载存储的文件被删除
//    	Environment.getExternalStorageState()方法用于获取SDCard的状态，如果手机装有SDCard，并且可以进行读写，那么方法返回的状态等于Environment.MEDIA_MOUNTED。
//    	Environment.getExternalStorageDirectory()方法用于获取SDCard的目录，当然要获取SDCard的目录，你也可以这样写：
//    	File sdCardDir = new File(“/sdcard”); //获取SDCard目录
//    	File saveFile = new File(sdCardDir, “itcast.txt”); 
//    	//上面两句代码可以合成一句： File saveFile = new File(“/sdcard/a.txt”);
//    	FileOutputStream outStream = new FileOutputStream(saveFile);
//    	outStream.write(“test”.getBytes());
//    	outStream.close();
        if (isSDcardExist()) {
            File file = getFileDir(context, fileName);
            FileOutputStream outStream;
            if (file != null) {
                try {
                    int fileSize = (int) (file.length() / 1024 / 1024);
                    if (fileSize < 10) {//小于10M用追加模式
                        outStream = new FileOutputStream(file, true);
                    } else {//大于10M用覆盖模式
                        outStream = new FileOutputStream(file, false);

                    }
                    content = "\n" + content;
                    outStream.write(content.getBytes());
                    outStream.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 功能：向SD卡File中保存数据
     *
     * @author lin
     * @2015-6-9 下午4:52:32
     */
    public void saveHttpClientDataToSDFile(Context context, String content, String fileName) {
        if (isSDcardExist()) {
            File file = getFileDir(context, fileName);
            FileOutputStream outStream;
            if (file != null) {
                try {
                    outStream = new FileOutputStream(file);
//					outStream = new FileOutputStream(file,true);
//					content = "\n"+content;
                    outStream.write(content.getBytes());
                    outStream.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 功能：从SDFile中读取数据
     *
     * @author lin
     * @2015-6-9 下午4:52:17
     */
    public String getDataFromSDFile(Context context, String fileName) {
        String log = "";
        if (isSDcardExist()) {
            File file = getFileDir(context, fileName);
            FileInputStream fileInputStream = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            StringBuilder stringBuilder = null;
            String line = null;
            if (file != null) {
                try {
                    stringBuilder = new StringBuilder();
                    fileInputStream = new FileInputStream(file);
                    inputStreamReader = new InputStreamReader(fileInputStream);
                    bufferedReader = new BufferedReader(inputStreamReader);
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    log = stringBuilder.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return log;
    }

    /**
     * 功能：从SDFile中删除数据
     *
     * @author lin
     * @2015-6-9 下午4:51:10
     */
    public void deleteDataFromSDFile(Context context, String fileName) {
        if (isSDcardExist()) {
            File file = getFileDir(context, fileName);
            FileOutputStream outStream;
            if (file != null) {
                try {
                    outStream = new FileOutputStream(file, true);
                    outStream.write("".getBytes());
                    outStream.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteFile(String srcFilePath) {
        File srcFile = new File(srcFilePath);
        try {
            if (srcFile.exists()) {
                srcFile.delete();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void del(String filepath) {
        File f = new File(filepath);// 定义文件路径
        boolean k = f.exists();
        boolean t = f.isDirectory();
        if (f.exists()) {// 判断是文件还是目录
            if (f.isDirectory()) { //判断是否目录
                if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
                    f.delete();
                } else {// 若有则把文件放进数组，并判断是否有下级目录
                    File delFile[] = f.listFiles();
                    int i = f.listFiles().length;
                    for (int j = 0; j < i; j++) {
                        if (delFile[j].isDirectory()) {
                            del(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
                        }
                        delFile[j].delete();// 删除文件
                    }
                }
            } else {//是文件，直接删除
                f.delete();
            }
        }
    }

    public static void delWithDirectory(String filepath) {
        File f = new File(filepath);
        if (f.exists()) {
            if (f.isDirectory()) {
                if (f.listFiles().length > 0) {
                    File delFile[] = f.listFiles();
                    for (int j = 0; j < delFile.length; j++) {
                        if (delFile[j].isDirectory()) {
                            del(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
                        }
                        delFile[j].delete();// 删除文件
                    }
                }
            }
            f.delete();
        }
    }

    public static boolean moveFile(String oldPath, String newPath) {
        boolean succeed = false;
        try {
            if (copyFile(oldPath, newPath)) {
                new File(oldPath).delete();
                succeed = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return succeed;
    }

    // 复制单个文件
    public static boolean copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            String newFile = "";
            File oldFile = new File(oldPath);
            if (newPath.endsWith(File.separator)) {
                newFile = newPath + oldFile.getName();
            } else {
                newFile = newPath + File.separator + oldFile.getName();
            }

            File newDir = new File(newPath);
            if (!newDir.exists()) {
                newDir.mkdirs();
            }

            File file = new File(newFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (oldFile.exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newFile);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.flush();
                fs.close();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean copyFile(InputStream inStream, String name,
                                   String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            String newFile = newPath + File.separator + name;

            File newDir = new File(newPath);
            if (!newDir.exists()) {
                newDir.mkdirs();
            }

            File file = new File(newFile);
            if (!file.exists()) {

                FileOutputStream fs = new FileOutputStream(newFile);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }

                fs.flush();
                fs.close();
            }

            inStream.close();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String getMjdzfPackagePath(Context context) {
        String baseDirPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + "/Android/data/"
                + context.getPackageName();
        return baseDirPath;
    }

    public static String getMediaZipName(Context context) {
        String baseDirPath = Environment.getExternalStorageDirectory()
                + "/data/" + context.getPackageName() + "/media.zip";
        return baseDirPath;
    }

    public static String getMediaBasePath(Context context) {
        String mediaBaseDirPath = Environment.getExternalStorageDirectory()
                + "/data/" + context.getPackageName() + "/media/";
        return mediaBaseDirPath;
    }
    public static String getDownloadPath(Context context) {
        String downloadDirPath = Environment.getExternalStorageDirectory()
                + "/Download/123.png";
        return downloadDirPath;
    }
    /**
     * 功能：到照片文件夹的路径
     *
     * @author lin 2017-8-26 上午11:45:06
     */
    public static String getMediaPictPath(Context context, String superviseId) {
        return getMediaBasePath(context) + superviseId + "/" + PICT_NAME + "/";
    }

    /**
     * 功能：到视频文件夹的路径
     *
     * @author lin 2017-8-26 上午11:45:06
     */
    public static String getMediaVideoPath(Context context, String superviseId) {
        return getMediaBasePath(context) + superviseId + "/" + VIDEO_NAME + "/";
    }

    /**
     * 功能：到音频文件夹的路径
     *
     * @author lin 2017-8-26 上午11:45:06
     */
    public static String getMediaAudioPath(Context context, String superviseId) {
        return getMediaBasePath(context) + superviseId + "/" + AUDIO_NAME + "/";
    }

    /**
     * 功能：到照片文件夹的路径
     *
     * @author lin 2017-8-26 上午11:45:06
     */
    public static String getMediaPictPath(Context context, String zyid, String recordDirectory) {
        return getMediaBasePath(context) + zyid + "/" + recordDirectory + "/" + PICT_NAME + "/";
    }

    /**
     * 功能：到视频文件夹的路径
     *
     * @author lin 2017-8-26 上午11:45:06
     */
    public static String getMediaVideoPath(Context context, String zyid, String recordDirectory) {
        return getMediaBasePath(context) + zyid + "/" + recordDirectory + "/" + VIDEO_NAME + "/";
    }

    /**
     * 功能：到音频文件夹的路径
     *
     * @author lin 2017-8-26 上午11:45:06
     */
    public static String getMediaAudioPath(Context context, String zyid, String recordDirectory) {
        return getMediaBasePath(context) + zyid + "/" + recordDirectory + "/" + AUDIO_NAME + "/";
    }

    /**
     * 功能：得到某条记录多媒体文件夹的路径
     *
     * @author lin 2017-8-26 上午11:45:06
     */
    public static String getMediaRecordPath(Context context, String zyid, String recordDirectory) {
        return getMediaBasePath(context) + zyid + "/" + recordDirectory + "/";
    }

//    public static boolean zipFile(String srcfile, String destfile)
//            throws IOException {
//        File resFile = new File(srcfile);
//        File zipFile = new File(destfile);
//        zipFile.deleteOnExit();
//        ZipOutputStream zipout = new ZipOutputStream(new BufferedOutputStream(
//                new FileOutputStream(zipFile), BUFF_SIZE));
//        StringBuilder exist = new StringBuilder();
//        zipFile(resFile, zipout, "", exist);
//        if (!StringUtils.isBlank(exist.toString())) {
//            zipout.close();
//            return true;
//        }
//        return false;
//    }

    /**
     * 解压缩一个文件
     *
     * @param zipFile    压缩文件
     * @param folderPath 解压缩的目标目录
     * @throws IOException 当解压缩过程出错时抛出
     */
    public static void upZipFile(File zipFile, String folderPath)
            throws ZipException, IOException {
        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            desDir.mkdirs();
        }
        ZipFile zf = new ZipFile(zipFile);
        for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = ((ZipEntry) entries.nextElement());
            InputStream in = zf.getInputStream(entry);
            String str = folderPath + File.separator + entry.getName();
            str = new String(str.getBytes("8859_1"), "GB2312");
            File desFile = new File(str);
            if (!desFile.exists()) {
                File fileParentDir = desFile.getParentFile();
                if (!fileParentDir.exists()) {
                    fileParentDir.mkdirs();
                }
                desFile.createNewFile();
            }
            OutputStream out = new FileOutputStream(desFile);
            byte buffer[] = new byte[BUFF_SIZE];
            int realLength;
            while ((realLength = in.read(buffer)) > 0) {
                out.write(buffer, 0, realLength);
            }
            in.close();
            out.close();
        }
    }

    /**
     * 压缩文件
     *
     * @param resFile  需要压缩的文件（夹）
     * @param zipout   压缩的目的文件
     * @param rootpath 压缩的文件路径
     * @throws FileNotFoundException 找不到文件时抛出
     * @throws IOException           当压缩过程出错时抛出
     */
    private static void zipFile(File resFile, ZipOutputStream zipout,
                                String rootpath, StringBuilder exist) throws FileNotFoundException,
            IOException {
        rootpath = rootpath
                + (rootpath.trim().length() == 0 ? "" : File.separator)
                + resFile.getName();
        if (resFile.isDirectory()) {
            File[] fileList = resFile.listFiles();
            for (File file : fileList) {
                zipFile(file, zipout, rootpath, exist);
            }
        } else {
            byte buffer[] = new byte[BUFF_SIZE];
            BufferedInputStream in = new BufferedInputStream(
                    new FileInputStream(resFile), BUFF_SIZE);
            zipout.putNextEntry(new ZipEntry(rootpath));
            int realLength;
            while ((realLength = in.read(buffer)) != -1) {
                Log.d("zip_fileutil_ziplength", realLength + "");
                zipout.write(buffer, 0, realLength);
            }
            in.close();
            zipout.flush();
            zipout.closeEntry();
            exist.append("1");
        }
    }

    /**
     * 得到文件大小
     *
     * @param longSize
     * @return
     * @throws Exception
     */
    public static String toFileSize(long longSize) {
        if (longSize >= 0 && longSize < SIZE_BT) {
            return longSize + "B";
        } else if (longSize >= SIZE_BT && longSize < SIZE_KB) {
            return longSize / SIZE_BT + "KB";
        } else if (longSize >= SIZE_KB && longSize < SIZE_MB) {
            return longSize / SIZE_KB + "MB";
        } else if (longSize >= SIZE_MB && longSize < SIZE_GB) {
            BigDecimal longs = new BigDecimal(Double.valueOf(longSize + "")
                    .toString());
            BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_MB + "")
                    .toString());
            String result = longs.divide(sizeMB, SACLE,
                    BigDecimal.ROUND_HALF_UP).toString();
            return result + "GB";
        } else {
            BigDecimal longs = new BigDecimal(Double.valueOf(longSize + "")
                    .toString());
            BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_GB + "")
                    .toString());
            String result = longs.divide(sizeMB, SACLE,
                    BigDecimal.ROUND_HALF_UP).toString();
            return result + "TB";
        }
    }

    public static int getFileCount(String filePath) {
        int fileCount = 0;
        File file = new File(filePath);
        if (filePath != null && file.isDirectory()) {
            File list[] = file.listFiles();
            for (int i = 0; i < list.length; i++) {
                if (list[i].isFile()) {
                    fileCount++;
                }
            }
        }
        return fileCount;
    }

    public static String toTimeString(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    public static boolean isExistFile(File resFile) {
        if (resFile.listFiles().length == 0) {
            return true;
        }

        return false;
    }

    //	创建指定路径的文件
    public static File createAppointPathFile(String dirName) {
        File file = null;
        if (dirName != null && dirName.length() > 0) {
            dirName = "/" + dirName;
            file = new File(getSDPath() + dirName);
        } else {
            file = new File(getSDPath());
        }

        return file;
    }

    //创建目录
    public static void makeDir(File dir) {
        if (!dir.getParentFile().exists()) {
            makeDir(dir.getParentFile());
        }
        dir.mkdir();
    }

    //	获取sd卡路径
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        String dir = sdDir.toString();
        return dir;

    }

    // 判断数据库文件是否存在
    public boolean DBExists(String DATABASE_PATH, String databaename) {
        SQLiteDatabase db = null;
        try {
            String databasePath = DATABASE_PATH + databaename;
            db = SQLiteDatabase.openDatabase(databasePath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            Log.e("DatabaseHelper", "Database Not Found", e);
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Database Not Found", e);
        }
        if (db != null) {
            db.close();
        }
        return db != null ? true : false;
    }

    /**
     * 创建数据库
     */
    public void createDatabase(String DATABASE_PATH, String databaename, String DB_PREFIX, Context myContext) {
        boolean dbExist = DBExists(DATABASE_PATH, databaename);
        if (!dbExist) {
            try {
                File dir = new File(DATABASE_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File dbFile = new File(DATABASE_PATH + databaename);
                copyDBFromAssets(DATABASE_PATH, databaename, DB_PREFIX, myContext);
                SQLiteDatabase.openOrCreateDatabase(dbFile, null);
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    /**
     * 从assets中拷贝数据库文件
     *
     * @throws IOException
     */
    private void copyDBFromAssets(String DATABASE_PATH, String databaename, String DB_PREFIX, Context myContext) throws IOException {

        String dbFilePath = DATABASE_PATH + databaename;
        OutputStream outputStream = new FileOutputStream(dbFilePath);
        InputStream inputStream = myContext.getAssets().open(
                DB_PREFIX + databaename);
        try {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (Exception ex) {
            Log.e("dbhelp", "copydb", ex);
        } finally {
            inputStream.close();
        }

        outputStream.flush();
        outputStream.close();

    }

    /**
     * 功能：文件是否存在
     *
     * @author lin 2017-8-30 下午1:48:24
     */
    public static boolean fileExists(String srcFilePath) {
        boolean exists = false;
        File srcFile = new File(srcFilePath);
        try {
            if (srcFile.exists()) {
                exists = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exists;
    }

    /**
     * 功能：将assets中的文件拷贝到指定路径
     *
     * @author lin 2017-9-2 下午6:25:19
     */
    public static void copyBigData(String path, String name, Context context) throws IOException {
//        File path = context.getFilesDir();
        File fs = new File(path);

        if (!fs.exists()) {
            InputStream myInput;
            OutputStream myOutput = new FileOutputStream(fs);
            myInput = context.getAssets().open(name);
            byte[] buffer = new byte[1024];
            int length = myInput.read(buffer);
            while (length > 0) {
                myOutput.write(buffer, 0, length);
                length = myInput.read(buffer);
            }

            myOutput.flush();
            myInput.close();
            myOutput.close();
        }
    }

    /**
     * 功能：获取文件字节流
     *
     * @author lin 2017-8-22 上午8:32:53
     */
    public byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        fi.close();
        return buffer;
    }

    /**
     * 传统的输入输出方式
     * 功能：获取文件字节流
     *
     * @author lin 2017-8-22 上午8:32:53
     */
    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    /**
     * NIO的方式
     * 功能：获取文件字节流
     *
     * @author lin 2017-8-22 上午8:32:53
     */
    public static byte[] toByteArray2(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 映射文件的方式  可以在处理大文件时，提升性能
     * 功能：获取文件字节流
     *
     * @author lin 2017-8-22 上午8:32:53
     */
    public static byte[] toByteArray3(String filename) throws IOException {

        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(filename, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                // System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 功能：如果是多及目录需要逐级创建
     *
     * @author lin
     * @2015-8-5 下午4:28:10
     */
    public static void MakePath(String path) {
        String[] pathSet = path.split("/");

        String increamentPath = new String("");

        int pathSetLen = pathSet.length;

        for (int index = 1; index < pathSetLen; index++) {

            increamentPath += ("/" + pathSet[index]);

            File increDir = new File(increamentPath + "/");

            if (increDir.exists()) {

                continue;

            } else if (increDir.mkdir()) {

                continue;

            } else {

            }

        }

    }

    /**
     * @功能 得到缓存人体图片的路径
     * @作者 lin
     * @创建日期 2020-02-28 13:40
     */
    public static String getCachePhotoBasePath(Context context) {
        String mediaBaseDirPath = Environment.getExternalStorageDirectory()
                + "/data/" + context.getPackageName() + "/cache_body_photo/";
        return mediaBaseDirPath;
    }
}
