package com.springboot.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.ls.LSInput;

import com.springboot.model.AppUtil;

import net.sf.json.JSONObject;
public class Util {
	public final static MessageFormat IP_PATTERN = new MessageFormat(
			"{0}.{1}.{2}.{3}");
	private static final String PASSWORD_CRYPT_KEY = "__jDlog_";
	private final static String DES = "DES";
	private final static int fONE_DAY = 1;
	private final static int fFOUR_AM = 1;
	private final static int fZERO_MINUTES = 0;
	
	
    private static final long ONE_MINUTE = 60;  
    private static final long ONE_HOUR = 3600;  
    private static final long ONE_DAY = 86400;  
    private static final long ONE_MONTH = 2592000;  
    private static final long ONE_YEAR = 31104000;  
	

	private Util() {
	}

	/**
	 * 验证该值是否存在
	 * 
	 * @param param
	 * @param value
	 * @return
	 */
	public static boolean IsValue(JSONObject param, String value) {
		boolean flag = false;
		try {
			String str[] = value.split(",");
			for (String s : str) {
				if (!s.equals("") && getobj(param.get(s))) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	/**
	 * 对list进行排序
	 */
	public static List<BigDecimal> listSort(List<BigDecimal> tempList){
		for(int i=1;i<tempList.size();i++){
			for(int j=0;j<tempList.size()-1;j++){
				if(tempList.get(j+1).compareTo(tempList.get(j))==1){
					BigDecimal temp = tempList.get(j);
					tempList.set(j, tempList.get(j+1));
					tempList.set(j+1,temp);
				}
			}
		}
		return tempList;
	}
	
	
	/**
	 * @description 判断对象是否为空
	 * @author 徐作念
	 */
	public static boolean getobj(Object obj) {
		boolean flag = false;
		try {
			if (obj != null && obj.toString() != null
					&& !"null".equals(obj.toString().trim())
					&&!"\"null\"".equals(obj.toString().trim())
					&& !"".equals(obj.toString().trim())) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	

	/**
	 * 验证接口格式是否正确
	 * 
	 * @param format
	 * @return
	 */
	public static boolean RegAppUtil(AppUtil model) {
		boolean flag = false;
		if (getobj(model) && getobj(model.getQueryid())
				&& getobj(model.getData())) {
			flag = true;
		}
		try {
			@SuppressWarnings("unused")
			JSONObject param = JSONObject.fromObject(model.getData());
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 处理空字符串为null情况；为null返回""
	 * 
	 * @param s
	 * @return
	 */
	public static String deNull(String s) {
		return s == null ? "" : s.trim();
	}

	/**
	 * 将日期格式化为yyyy-MM-dd-HH-mm-ss形式；
	 * 
	 * @return
	 */
	public static String formatDate() {
		java.util.Date d = new java.util.Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		return f.format(d);
	}

	/**
	 * 将日期格式化为yyyy-MM-dd-HH-mm-ss形式；
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getFormateDate() throws ParseException {
		java.util.Date d = new java.util.Date();
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		// return formate.parse(d.toLocaleString());
		return formate.parse(d.toString());
	}

	public static Date FormateDate() throws ParseException {
		java.util.Date d = new java.util.Date();
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// return formate.parse(d.toLocaleString());
		return formate.parse(d.toString());
	}

	public static Date getFormateDate(String date) throws ParseException {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = formate.parse(date);

		return date2;
	}

	public static Date getFormateDate(Date date) throws ParseException {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = formate.format(date);
		Date date2 = formate.parse(date1);
		return date2;
	}

	/**
	 * 将给定的日期格式转化为：yyyy-MM-dd HH:mm:格式
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDate(String d) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(d);
	}

	/**
	 * 将日期格式转化为yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDate(Date d) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(d);
	}

	public static Date Dateformat2(String date) throws ParseException {

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date mydate = f.parse(date);
		return mydate;
	}

	public static String formatDate2(Date d) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(d);
	}

	public static String Date2String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}

	/**
	 * 时间转换
	 * 
	 * @param format
	 * @return
	 */
	public static String changeDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 
	 * @return
	 */
	public static Timestamp getTimestampNow() {
		java.util.Date date = new java.util.Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		return timeStamp;
	}
	public static String getTimes1(int days) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	        // 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
	        Date date = sdf.parse(sdf.format(new Date()), new ParsePosition(0));
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
	        calendar.add(Calendar.DATE, days);
	        Date date1 = calendar.getTime();
	        String out = sdf.format(date1);
		return out;
	}
	public static String getTimes2(int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
		Date date = sdf.parse(sdf.format(new Date()), new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
		calendar.add(Calendar.DATE, days);
		Date date1 = calendar.getTime();
		String out = sdf.format(date1);
		return out;
	}
	

	/**
	 * 周 -- 时间转换
	 * 
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * @Title: getStrFromTimestampFromNow
	 * @Description: 获取指定时间
	 * @author ydfeng
	 * @param t
	 *            时间
	 * @param time
	 *            间隔
	 * @return {刚刚发布，N分钟前发布，2016-4-26 10:20}
	 * @throws ParseException
	 * @throws
	 */
	public static String getStrFromTimestampFromNow(Timestamp t, long time)
			throws ParseException {
		String str = "";
		if (t != null) {
			if (time < 0L)
				time = 10L;
			long now = new Date().getTime();
			long t_ = t.getTime();
			if ((now - t_) <= (60 * 1000L)) {
				str = "刚刚发布";
			} else if ((now - t_) > (60 * 1000L)
					&& (now - t_) <= (time * 60 * 1000L)) {
				int now_ = new Long((now - t_) / 60L / 1000L).intValue();
				str = now_ + "分钟前发布";
			} else {
				str = DateformatStr(t.toString(), "yyyy-MM-dd HH:mm").substring(5);
			}
		}
		return str;
	}
	
	/**
	 * 
	 * 简历更新状态
	 * @author ydfeng
	 * @time 2018年1月5日
	 */
    public static String fromToday(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
   
        long time = date.getTime() / 1000;  
        long now = new Date().getTime() / 1000;  
        long ago = now - time;
        if (ago<=ONE_MINUTE) {
        	return "刚刚更新";  
		}else if(ago <= ONE_HOUR){
        	return ago / ONE_MINUTE + "分钟前更新";  
        }
        else if (ago <= ONE_DAY) {
        	return ago / ONE_HOUR + "小时前更新";
        }
        else if (ago <= ONE_MONTH) {  
            long day = ago / ONE_DAY;  
            return day + "天前更新"; 
        }else if (ago <= ONE_YEAR) {  
            long month = ago / ONE_MONTH;  
//            long day = ago % ONE_MONTH / ONE_DAY;  
//            return month + "个月"+ day + "天前更新";  
            return month + "个月前更新";  
        }else{  
            long year = ago / ONE_YEAR;  
//            long month = ago % ONE_YEAR / ONE_MONTH;
//            return year + "年"+month+"个月前更新";
            return year + "年前更新";
        }  
   
    } 
	
    
    
    
    
    public static String getWorkTimeOfYear(String[] times){
    	int totalYear=0;
    	int totalMonth=0;
    	for (String string : times) {
			//string 	3年11个月
    		if (string.contains("年")&&string.endsWith("月")) {
    			string=string.replace("个月", "");
    			String[] t=string.split("年");
				int year=Integer.valueOf(t[0]);
				int month=Integer.valueOf(t[1]);
				totalYear+=year;
				totalMonth+=month;
			}else if (string.endsWith("年")) {
				string=string.replace("年", "");
				int year=Integer.valueOf(string);
				totalYear+=year;
			}else if(string.endsWith("月")) {
				string=string.replace("个月", "");
				int month=Integer.valueOf(string);
				totalMonth+=month;
			}
		}
    	
		int y=totalMonth/12;
		int m=totalMonth%12;
    	if (m>=0&&m<=6) {
			return totalYear+y+"年";
		}
    	if (m>6&&m<12) {
			return totalYear+y+1+"年";
		}

    	
    	
    	return "";
    }

	public static Date parseDate(Object value, String format) {
		try {
			if (value != null && !value.toString().trim().equals("")) {
				return new SimpleDateFormat(format).parse(value.toString());
			}
		} catch (Exception e) {
			System.out.println("时间格式转换出错");
		}
		return null;
	}

	/**
	 * 获取当前时间前后多少天的时间
	 * 
	 * @return
	 */
	public static Date getDateByNumber(int day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, day);
		return parseDate(
				new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()),
				"yyyy-MM-dd");
	}
	/**
	 * 获取当前时间前后多少月的时间
	 * 
	 * @return
	 */
	public static Date getDateByMonth(int month) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		return parseDate(
				new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()),
				"yyyy-MM-dd");
	}
	/**
	 * 获取当前时间前后多少月的时间
	 * 
	 * @return
	 */
	public static Date getDateByMonthFromDate(Date date,int month) {
	       Calendar c = Calendar.getInstance();//获得一个日历的实例
	       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       c.setTime(date);//设置日历时间
	       c.add(Calendar.MONTH,month);//在日历的月份上增加6个月
	       System.out.println(sdf.format(c.getTime()));//的到你想要得6个月后的日期
	       Date times=c.getTime();
	       return times;
	      
	}

	/**
	 * 将当前时间格式化为yyyy-MM-dd HH:mm:ss形式
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getActicleTime() {
		String strDate = "";
		java.util.Date d = new java.util.Date();
		d.toLocaleString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strDate = sdf.format(d);
		return strDate;
	}

	/**
	 * 取得当前时间H小时后的时间
	 * 
	 * @param m
	 * @return
	 */
	public static String getCalendarTime(int h) {
		String str = "";
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.HOUR_OF_DAY, h);
		str = cl.get(Calendar.YEAR) + "年" + (cl.get(Calendar.MONTH) + 1) + "月"
				+ (cl.get(Calendar.DAY_OF_MONTH)) + "日"
				+ (cl.get(Calendar.HOUR_OF_DAY)) + "时"
				+ (cl.get(Calendar.MINUTE)) + "分" + (cl.get(Calendar.SECOND))
				+ "秒";
		return str;
	}

	/**
	 * yyyy年MM月dd日星期
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getDate() {
		StringBuffer sb = new StringBuffer();
		Date d = new Date();
		// int hours = d.getHours();
		// int minute = d.getMinutes();
		String[] weekdays = { "日", "一", "二", "三", "四", "五", "六" };
		sb.append((d.getYear() + 1900));
		sb.append("年");
		sb.append(d.getMonth() + 1);
		sb.append("月");
		sb.append(d.getDate());
		sb.append("日星期");
		sb.append(weekdays[d.getDay()]);
		return sb.toString();
	}

	/**
	 * 取得当前时间yyyy-MM-dd HH:mm:ss
	 * 
	 * @param fileName
	 */
	public static String getCalendarTime() {
		StringBuffer sb = new StringBuffer();
		Calendar cl = Calendar.getInstance();
		sb.append(cl.get(Calendar.YEAR));
		sb.append("-");
		sb.append(cl.get(Calendar.MONTH) + 1);
		sb.append("-");
		sb.append(cl.get(Calendar.DAY_OF_MONTH));
		sb.append(" ");
		sb.append(cl.get(Calendar.HOUR_OF_DAY));
		sb.append(":");
		sb.append(cl.get(Calendar.MINUTE));
		sb.append(":");
		sb.append(cl.get(Calendar.SECOND));
		return sb.toString();
	}

	public static String getLogDate() {
		StringBuffer sb = new StringBuffer();
		Calendar cl = Calendar.getInstance();
		sb.append(cl.get(Calendar.YEAR));
		sb.append("-");
		sb.append(cl.get(Calendar.MONTH) + 1);
		sb.append("-");
		sb.append(cl.get(Calendar.DAY_OF_MONTH));
		return sb.toString();
	}

	public static String getNowTime() {
		StringBuffer sb = new StringBuffer();
		Calendar cl = Calendar.getInstance();
		sb.append(cl.get(Calendar.HOUR_OF_DAY));
		sb.append(":");
		sb.append(cl.get(Calendar.MINUTE));
		sb.append(":");
		sb.append(cl.get(Calendar.SECOND));
		return sb.toString();
	}

	/**
	 * 返回明天凌晨1点时间
	 * 
	 * @return
	 */
	public static Date getTomorrowMorning1am() {
		Calendar tomorrow = new GregorianCalendar();
		tomorrow.add(Calendar.DATE, fONE_DAY);
		Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR),
				tomorrow.get(Calendar.MONTH), tomorrow.get(Calendar.DATE),
				fFOUR_AM, fZERO_MINUTES);
		return result.getTime();
	}

	public static Date Dateformat(String date) throws ParseException {

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date mydate = f.parse(date);
		return mydate;
	}

	public static Date Dateformat(String date, String format)
			throws ParseException {

		SimpleDateFormat f = new SimpleDateFormat(format);
		Date mydate = f.parse(date);
		return mydate;
	}

	public static String DateformatStr(String date, String formatstr)
			throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat(formatstr);
		return f.format(f.parse(date));
	}
	public static String DateformatStr(Date date, String formatstr)
			throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat(formatstr);
		return f.format(date);
	}

	public static String todayDate() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String today = f.format(date);
		return today;
	}

	public static String dataCompareTo(String date, String format) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		String ss = df.format(format);
		try {
			c1.setTime(df.parse(date));
			c2.setTime(df.parse(ss));
		} catch (ParseException e) {
			System.err.println("格式不正确");
		}
		int result = c1.compareTo(c2);
		String time = String.valueOf(result);
		return time;

	}

	/**
	 * MD5对字符串加密
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());

			byte[] b = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				int v = b[i];
				v = v < 0 ? 0x100 + v : v;
				String cc = Integer.toHexString(v);
				if (cc.length() == 1)
					sb.append('0');
				sb.append(cc);
			}

			return sb.toString();
		} catch (Exception e) {
		}

		return "";
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	/**
	 * 十六进制转化为二进制；
	 * 
	 * @param b
	 * @return
	 */
	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 取得4位随机数
	 * 
	 * @return
	 */
	public static String getRandNum() {
		Random random = new Random();
		Integer randNumber = random.nextInt();
		randNumber = Math.abs(randNumber);
		String result = Integer.toString(randNumber).substring(0, 4);
		return result;
	}

	/**
	 * 取得N位随机数
	 * 
	 * @return
	 */
	public static String getRandNum(int n) {
		Random random = new Random();
		Integer randNumber = random.nextInt();
		randNumber = Math.abs(randNumber);
		String result = Integer.toString(randNumber).substring(0, n);
		return result;
	}

	/**
	 * 
	 * 取得主机MAC地址；
	 * 
	 * @return
	 */

	public static String getMAC() {
		String mac = "";
		try {
			ProcessBuilder pb = new ProcessBuilder("ipconfig", "/all");
			Process p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf("Physical Address") != -1) {
					int index = line.indexOf(":");
					mac = line.substring(index + 1);
					break;
				}
			}
			br.close();
			return mac.trim();
		} catch (IOException e) {
		}
		return mac;
	}

	/**
	 * 取得系统硬盘使用情况；
	 * 
	 * @return
	 */
	public static String getDiskInfo() {
		StringBuffer sb = new StringBuffer();
		File[] roots = File.listRoots();// 获取磁盘分区列表
		for (File file : roots) {
			long totalSpace = file.getTotalSpace();
			// long freeSpace = file.getFreeSpace();
			long usableSpace = file.getUsableSpace();
			if (totalSpace > 0) {
				sb.append(file.getPath() + "(总计：");
				sb.append(Math
						.round(((double) totalSpace / (1024 * 1024 * 1024)) * 100 / 100.0)
						+ "GB  ");
				if (Math.round((((double) usableSpace / (1024 * 1024 * 1024)) * 100) / 100.0) <= 1) {
					sb.append("剩余："
							+ Math.round((((double) usableSpace / (1024 * 1024)) * 100) / 100.0)
							+ "MB)<br>");
				} else {
					sb.append("剩余："
							+ Math.round((((double) usableSpace / (1024 * 1024 * 1024)) * 100) / 100.0)
							+ "GB)<br>");
				}
				// sb.append("已使用" +
				// Math.round((((double)(totalSpace-usableSpace)/(1024*1024*1024))*100)/100.0)
				// + "G<br>");
			}
		}
		return sb.toString();
	}

	/**
	 * 取得硬盘下的目录和文件；
	 * 
	 * @return
	 */
	public static String getDiskFileList() {
		StringBuffer sb = new StringBuffer();
		String[] fileList = null;
		File[] roots = File.listRoots();// 获取硬盘分区列表；
		for (File file : roots) {
			long totalSpace = file.getTotalSpace();
			fileList = file.list();
			if (totalSpace > 0) {
				sb.append(file.getPath() + "下目录和文件：\n");
				for (int i = 0; i < fileList.length; i++) {
					sb.append(fileList[i] + "\n");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 遍历文件目录下的文件
	 * 
	 * @param filePath
	 *            文件夹路径
	 * @return
	 */
	public static ArrayList<String> getDirFileList(String filePath) {
		ArrayList<String> fileLists = new ArrayList<String>();
		File dir = new File(filePath);
		File[] fileArr = dir.listFiles();
		if (fileArr == null) {
			return null;
		}
		for (int i = 0; i < fileArr.length; i++) {
			if (fileArr[i].isDirectory()) {
				getDirFileList(fileArr[i].getAbsolutePath());
			} else {
				fileLists.add(fileArr[i].getAbsolutePath());
				// System.out.println(fileArr[i].getAbsolutePath());
			}
		}
		return fileLists;
	}

	/**
	 * 取得系统内存使用情况；
	 * 
	 * @return
	 * 
	 *         public static String getEMS() { StringBuffer sb = new
	 *         StringBuffer(); OperatingSystemMXBean osmb =
	 *         (OperatingSystemMXBean) ManagementFactory
	 *         .getOperatingSystemMXBean(); sb.append("系统总物理内存：" +
	 *         osmb.getTotalPhysicalMemorySize() / 1024 / 1024 + "MB");
	 *         sb.append("<br>
	 *         "); sb.append("系统物理可用内存：" + osmb.getFreePhysicalMemorySize() /
	 *         1024 / 1024 + "MB"); sb.append("<br>
	 *         "); sb.append("系统已使用物理内存:" + (osmb.getTotalPhysicalMemorySize() -
	 *         osmb .getFreePhysicalMemorySize()) / 1024 / 1024);
	 *         sb.append("MB"); return sb.toString(); }
	 */

	/**
	 * 取得系统内存
	 * 
	 * @return
	 */
	public static float getMem() {
		Runtime r = Runtime.getRuntime();
		float freeMemory = r.freeMemory();
		float totalMemory = r.totalMemory();
		float n = 1 - freeMemory / totalMemory;
		return n;
	}

	/**
	 * 取得主机名称
	 * 
	 * @return
	 */
	public static String getHostName() {
		String str = null;
		try {
			InetAddress ia = InetAddress.getLocalHost();
			str = ia.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 判断一个ip地址是否在一个网段中
	 * 
	 * @param iparea
	 * @param ip
	 * @return boolean
	 */
	public static boolean ipIsInNet(String iparea, String ip) {
		if (iparea == null)
			throw new NullPointerException("IP段不能为空！");
		if (ip == null)
			throw new NullPointerException("IP不能为空！");
		iparea = iparea.trim();
		ip = ip.trim();
		final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
		final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;
		if (!iparea.matches(REGX_IPB) || !ip.matches(REGX_IP))
			return false;
		int idx = iparea.indexOf('-');
		String[] sips = iparea.substring(0, idx).split("\\.");
		String[] sipe = iparea.substring(idx + 1).split("\\.");
		String[] sipt = ip.split("\\.");
		long ips = 0L, ipe = 0L, ipt = 0L;
		for (int i = 0; i < 4; ++i) {
			ips = ips << 8 | Integer.parseInt(sips[i]);
			ipe = ipe << 8 | Integer.parseInt(sipe[i]);
			ipt = ipt << 8 | Integer.parseInt(sipt[i]);
		}
		if (ips > ipe) {
			long t = ips;
			ips = ipe;
			ipe = t;
		}
		return ips <= ipt && ipt <= ipe;
	}

	/**
	 * 取得操作系统信息；
	 * 
	 * @return
	 */
	public static String getOsInfo() {
		return System.getProperty("os.name") + " "
				+ System.getProperty("sun.os.patch.level");
	}

	/**
	 * 根据路径获取文件的类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String fileName) {
		String type = "";
		int point = fileName.lastIndexOf(".");
		int length = fileName.length();
		if (point == -1 || point == length - 1) {
			type = "";
		} else {
			type = fileName.substring(point + 1, length);
		}

		return type;
	}

	/**
	 * 得到路径分隔符在文件路径中最后出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
	 * 
	 * @param fileName
	 *            文件路径
	 * @return 路径分隔符在路径中最后出现的位置，没有出现时返回-1。
	 * @since 0.5
	 */
	public static int getPathLsatIndex(String fileName) {
		int point = fileName.lastIndexOf('/');
		if (point == -1) {
			point = fileName.lastIndexOf('\\');
		}
		return point;
	}

	public static int getPathLsatIndex(String fileName, int fromIndex) {
		int point = fileName.lastIndexOf('/', fromIndex);
		if (point == -1) {
			point = fileName.lastIndexOf('\\', fromIndex);
		}
		return point;
	}

	/**
	 * 取得路径中文件名部分
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getNamePart(String fileName) {
		int point = getPathLsatIndex(fileName);
		int length = fileName.length();
		if (point == -1) {
			return fileName;
		} else if (point == length - 1) {
			int secondPoint = getPathLsatIndex(fileName, point - 1);
			if (secondPoint == -1) {
				if (length == 1) {
					return fileName;
				} else {
					return fileName.substring(0, point);
				}
			} else {
				return fileName.substring(secondPoint + 1, point);
			}
		} else {
			return fileName.substring(point + 1);
		}
	}

	/**
	 * 密码解密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data) {
		try {
			return new String(decryptDES(hex2byte(data.getBytes()),
					PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 密码加密；
	 * 
	 * @param password
	 * @return
	 */
	public static String encrypt(String password) {
		try {
			return byte2hex(encryptDES(password.getBytes(),
					PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * DES解密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decryptDES(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	/**
	 * DES加密
	 * 
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encryptDES(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	public static String getCalendarTimes() {
		StringBuffer sb = new StringBuffer();
		Calendar cl = Calendar.getInstance();
		sb.append(cl.get(Calendar.YEAR));
		sb.append("-");
		if (cl.get(Calendar.MONTH) + 1 < 10) {
			sb.append("0");
		}
		sb.append(cl.get(Calendar.MONTH) + 1);
		sb.append("-");
		if ((cl.get(Calendar.DAY_OF_MONTH)) < 10) {
			sb.append("0");
		}
		sb.append(cl.get(Calendar.DAY_OF_MONTH));
		sb.append(" ");
		if (cl.get(Calendar.HOUR_OF_DAY) < 10) {
			sb.append("0");
		}
		sb.append(cl.get(Calendar.HOUR_OF_DAY));
		sb.append(":");
		if (cl.get(Calendar.MINUTE) < 10) {
			sb.append("0");
		}
		sb.append(cl.get(Calendar.MINUTE));
		sb.append(":");
		if (cl.get(Calendar.SECOND) < 10) {
			sb.append("0");
		}
		sb.append(cl.get(Calendar.SECOND));
		return sb.toString();
	}

	/**
	 * 返回两个日期相差的天数
	 * 
	 * @param d1
	 *            长的时间
	 * @param d2
	 *            短的时间
	 * @return int
	 */
	public static int dispersionDay2(String strDate1, String strDate2) {
		int iDay = 0;// 记录相差的天数
		try {
			int index1, index2;
			index1 = strDate1.indexOf('-');
			index2 = strDate1.lastIndexOf('-');
			int nYear, nMonth, nDay;
			nYear = Integer.parseInt(strDate1.substring(0, index1));
			nMonth = Integer.parseInt(strDate1.substring(index1 + 1, index2));
			nDay = Integer.parseInt(strDate1.substring(index2 + 1));
			Calendar objCalendarDate1 = Calendar.getInstance();// 此函数new
																// GregorianCalendar()
			objCalendarDate1.set(nYear, nMonth, nDay);

			index1 = strDate2.indexOf('-');
			index2 = strDate2.lastIndexOf('-');

			nYear = Integer.parseInt(strDate2.substring(0, index1));
			nMonth = Integer.parseInt(strDate2.substring(index1 + 1, index2));
			nDay = Integer.parseInt(strDate2.substring(index2 + 1));
			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.set(nYear, nMonth, nDay);

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;

			if (objCalendarDate2.after(objCalendarDate1))// 如果strDate2>strDate1
			{
				while (!objCalendarDate2.equals(objCalendarDate1)) {
					objCalendarDate1.add(Calendar.DATE, 1);
					iDay++;
					System.out.println("打印＝＝＝＝＝＝＝");
				}
				iDay = -iDay;// 负号代表 还相距几天，正号代表已过去几天。。。
			} else
			// 如果strDate1>strDate2
			{
				while (!objCalendarDate2.equals(objCalendarDate1)) {
					objCalendarDate2.add(Calendar.DATE, 1);
					iDay++;
					System.out.println("打印＝＝＝＝＝＝＝＝");
				}
			}
		} catch (Exception e) {
		}
		return iDay;
	}
	public static int dispersionDay(String strDate1,String strDate2){
		 long quot = 0;  
		 SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");  
		 try {   
			 Date date1 = ft.parse(strDate1);   
			 Date date2 = ft.parse(strDate2);   
			 quot = date1.getTime() - date2.getTime();  
			 quot = quot / 1000 / 60 / 60 / 24;  
		 } catch (ParseException e) {   
			 e.printStackTrace();  
		 }  
		return new Long(quot).intValue();
	}
	/**
	 * 数字格式转换
	 * 
	 * @param args
	 */
	public static String getNum(int num) {
		String str = "一二三四五六七八九十";
		String cnum = "";
		if (num < 10) {
			cnum = str.substring(num - 1, num);
		} else {
			int m = Integer.valueOf(String.valueOf(num).substring(0, 1));
			int n = Integer.valueOf(String.valueOf(num).substring(1, 2));
			String cnumm = str.substring(m - 1, m);
			String cnumn = str.substring(n - 1, n);
			if (cnumm.equals("一")) {
				cnum = "十" + cnumn;
			} else {
				cnum = cnumm + "十" + cnumn;
			}
		}
		return cnum;
	}

	// public static String getTimeSerialNumber(String num){
	// DateTime in=new DateTime();
	// DateTimeFormatter fmt=DateTimeFormat.forPattern("yyyyMMddHHmmss");
	// return num+"_"+in.now().toString(fmt);
	//
	// }
	// 判断时间是否在某一时间段内
	public static boolean IsTimeIn(Date time, Date begin, Date end) {
		return time.getTime() >= begin.getTime()
				&& time.getTime() <= end.getTime();
	}

	// 获得本周一的日期
	public static Date getMondayOFWeek() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(Calendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		// DateFormat df = DateFormat.getDateInstance();
		// String preMonday = df.format(monday);
		return monday;
	}

	// 获得本周星期日的日期
	public static Date getCurrentWeekday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(Calendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		// DateFormat df = DateFormat.getDateInstance();
		// String preMonday = df.format(monday);
		return monday;
	}

	// 获得上周星期一的日期
	public static Date getPreviousWeekday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(Calendar.DATE, mondayPlus + 7 * -1);
		Date monday = currentDate.getTime();
		// DateFormat df = DateFormat.getDateInstance();
		// String preMonday = df.format(monday);
		return monday;
	}

	// 获得上周星期日的日期
	public static Date getPreviousWeekSunday() {
		// WEEKS--;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(Calendar.DATE, mondayPlus - 1);
		Date monday = currentDate.getTime();
		// DateFormat df = DateFormat.getDateInstance();
		// String preMonday = df.format(monday);
		return monday;
	}

	// 获取当月第一天
	public static String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 计算当月最后一天,返回字符串
	public static String getDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获取当天时间
	public static String getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String hehe = dateFormat.format(now);
		return hehe;
	}

	// 获得当前日期与本周日相差的天数
	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天……
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// 获得n天后的日期
	public static Date getTime(int day) {
		Calendar objCalendar = Calendar.getInstance();
		objCalendar.add(Calendar.DATE, day);
		Date objDate = objCalendar.getTime();
		System.out.print(objDate);
		return objDate;
	}

	/**
	 * 
	 * 方法描述：判断某一时间是否在一时间段内 参数名称：@param date 参数名称：type :　0：本周　1:上周　2:更早 返回值：
	 * 
	 * @throws ParseException
	 *
	 */
	public static String gettime(Date date) throws ParseException {
		String type = "";
		boolean currentweek = IsTimeIn(date, getFormateDate(getMondayOFWeek()),
				getFormateDate(getCurrentWeekday()));
		boolean previousweek = IsTimeIn(date,
				getFormateDate(getPreviousWeekday()),
				getFormateDate(getPreviousWeekSunday()));

		// System.out.println("参数日期："+date);
		// System.out.println("本周一："+getFormateDate(getMondayOFWeek()));
		// System.out.println("本周日："+getFormateDate(getCurrentWeekday()));
		// System.out.println("上周一："+getFormateDate(getPreviousWeekday()));
		// System.out.println("上周日："+getFormateDate(getPreviousWeekSunday()));

		if (currentweek) {
			type = "0";

		} else if (previousweek) {
			type = "1";

		} else {
			type = "2";
		}

		return type;
	}

	public static String getPreviousWeekdays() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(Calendar.DATE, mondayPlus + 7 * -1);
		Date monday = currentDate.getTime();
		String preMonday = formatDate2(monday);
		return preMonday;
	}

	public static String getPreviousWeekSundays() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(Calendar.DATE, mondayPlus - 1);
		Date monday = currentDate.getTime();
		String preMonday = formatDate2(monday);
		return preMonday;
	}

	/**
	 * 将字符串转换为asc编码
	 * 
	 * @param s
	 * @return
	 */
	public static String toASCII(String s) {
		char[] c = s.toCharArray();
		String ascs = "";
		for (int i = 0; i < c.length; i++) {
			ascs += (int) c[i];
		}
		return ascs;
	}

	/**
	 * 
	 * 以UUID的策略生成一个长度为32的字符串，在同一时空中具有唯一性。
	 * 
	 * @return UUID128位长度字符串
	 */
	public static String getUUIDString() {

		String id = UUID.randomUUID().toString();
		id = id.replace("-", "");
		return id;
	}

	/**
	 * 方法描述：生成订单编号 参数名称：@param order 订单首字母:　比如预约单为y,维修单为w... 参数名称：@param type
	 * 类型：1代表系统自然生成 0代表人工触发生成 参数名称：@param count 订单数量
	 */
	public static String getOrderNo(String order, String type, String count) {
		java.util.Date d = new java.util.Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String date = f.format(d);
		if (count.equals("null")) {
			count = "0";
		}
		switch (count.length()) {
		case 1:
			count = "000000" + count;
			break;
		case 2:
			count = "00000" + count;
			break;
		case 3:
			count = "0000" + count;
			break;
		case 4:
			count = "000" + count;
			break;
		case 5:
			count = "00" + count;
			break;
		case 6:
			count = "0" + count;
			break;
		case 7:
			// count=count;
			break;
		default:
			count = "0000000";
			break;
		}

		return order + type + date + count;
	}

	/**
	 * 获得指定日期的开始时间
	 * 
	 * @return
	 */
	public static Date getDateBegin(String date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date mydate = null;
		try {
			mydate = f.parse(date);
		} catch (ParseException e) {
		}
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(mydate);
		lastDate.set(Calendar.HOUR_OF_DAY, 0);//
		lastDate.add(Calendar.MINUTE, 0);//
		lastDate.add(Calendar.SECOND, 0);//

		return lastDate.getTime();
	}

	/**
	 * 获取指定日期的结束时间 00:00 00:00
	 * 
	 * @return
	 */
	public static Date getDateEnd(String date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date mydate = null;
		try {
			mydate = f.parse(date);
		} catch (ParseException e) {
		}
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(mydate);
		lastDate.set(Calendar.HOUR_OF_DAY, 23);//
		lastDate.add(Calendar.MINUTE, 59);//
		lastDate.add(Calendar.SECOND, 59);//
		return lastDate.getTime();
	}

	/**
	 * 获得上一个月的开始时间，0点0时0分。
	 * 
	 * @return
	 */
	public static Date getLastMonthBegin(Date date) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);

		lastDate.add(Calendar.MONTH, -1);
		lastDate.set(Calendar.DATE, 1);// 设为选中月的1号
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		return lastDate.getTime();
		// return new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastDate.getTime());
	}

	/**
	 * 获得指定月开始时间
	 * 
	 * @param date
	 * @return
	 */

	public static Date getMonthBegin(Date date) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		return lastDate.getTime();

	}

	/**
	 * 获得指定时间的下月第一天0:0:0
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextMonthFirst(Date date) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.set(Calendar.DATE, 1);// 设为选中月的1号
		lastDate.add(Calendar.MONTH, 1);
		lastDate.set(Calendar.HOUR_OF_DAY, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		return lastDate.getTime();

	}

	/**
	 * 根据指定时间增加或者减少时间
	 * 
	 * @param date
	 * @param hh
	 * @param mm
	 * @param ss
	 * @return
	 */
	public static Date getVarTime(Date date, int dd, int hh, int mm, int ss) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.add(Calendar.DATE, dd);
		lastDate.add(Calendar.HOUR_OF_DAY, hh);
		lastDate.add(Calendar.MINUTE, mm);
		lastDate.add(Calendar.SECOND, ss);
		return lastDate.getTime();
	}

	/**
	 * 指定小时的前后时间
	 * 
	 * @param date
	 * @param dd
	 * @param hh
	 * @param mm
	 * @param ss
	 * @return
	 */
	public static Date getTimeZoneAdd(Date date, int hh, int mm) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		// lastDate.add(Calendar.DATE, dd);
		lastDate.set(Calendar.HOUR_OF_DAY, hh);
		lastDate.add(Calendar.MINUTE, mm);
		// lastDate.add(Calendar.SECOND,ss);
		return lastDate.getTime();
	}

	public static Date getTimeZoneDel(Date date, int hh, int mm) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		// lastDate.add(Calendar.DATE, dd);
		lastDate.set(Calendar.HOUR_OF_DAY, hh);
		lastDate.add(Calendar.MINUTE, -mm);
		// lastDate.add(Calendar.SECOND,ss);
		return lastDate.getTime();
	}

	/**
	 * 获取时间之前或者之后几天的时间
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Timestamp getDay(Date date, int day) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.add(Calendar.DATE, day);
		return new Timestamp(lastDate.getTimeInMillis());
	}

	/**
	 * 首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toUpperCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	/**
	 * 反射方法
	 * 
	 * @param clazz
	 * @param method
	 * @param args
	 *            object 是执行的对象，本方法执行的是非静态方法，静态方法请参考play
	 *            Java.invokeChildOrStatic(Security.class, m, args);
	 */
	@SuppressWarnings("rawtypes")
	public static void nonStaticReflect(Object object, String methodName,
			Object... args) {

		Class[] types = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			types[i] = args[i].getClass();
		}
		try {
			Method method = object.getClass().getDeclaredMethod(methodName,
					types);
			method.setAccessible(true);
			method.invoke(object, args);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 按照策略生成唯一订单，时间加日最大订单处理量的long id截取后六位
	 * 
	 * @param id
	 * @return
	 */
	public static String getOrderNo(String id) {
		java.util.Date d = new java.util.Date();
		SimpleDateFormat f = new SimpleDateFormat("yyMMdd");
		String date = f.format(d);

		switch (id.length()) {
		case 1:
			id = "00000" + id;
			break;
		case 2:
			id = "0000" + id;
			break;
		case 3:
			id = "000" + id;
			break;
		case 4:
			id = "00" + id;
			break;
		case 5:
			id = "0" + id;
			break;
		case 6:
			break;
		default:
			id = id.substring(id.length() - 6);
			break;
		}
		return date + id;
	}

	/**
	 * @param Code
	 *            大写字母
	 * @param count
	 *            第几个
	 * @return
	 */
	public static String getBiddingInfoId(String Code, String count) {
		java.util.Date d = new java.util.Date();
		SimpleDateFormat f = new SimpleDateFormat("yyMMdd");
		String date = f.format(d);
		switch (count.length()) {
		case 1:
			count = "00000" + count;
			break;
		case 2:
			count = "0000" + count;
			break;
		case 3:
			count = "000" + count;
			break;
		case 4:
			count = "00" + count;
			break;
		case 5:
			count = "0" + count;
			break;
		case 6:
			break;
		}
		return Code.toUpperCase() + date + count;
	}

	/**
	 * 获取订单编号
	 * 
	 * @param Code
	 * @return
	 */
	public static String getOrderNo2(String Code) {
		java.util.Date d = new java.util.Date();
		Long l = d.getTime();
		Long x_x = new Long("1000010000001");
		l = l + x_x;
		return Code.toUpperCase() + l.toString();
	}

	/**
	 * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零 要用到正则表达式
	 */
	public static String moneyUppercase(double n) {
		String fraction[] = { "角", "分" };
		String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };

		String head = n < 0 ? "负" : "";
		n = Math.abs(n);

		String s = "";
		for (int i = 0; i < fraction.length; i++) {
			s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i])
					.replaceAll("(零.)+", "");
		}
		if (s.length() < 1) {
			s = "整";
		}
		int integerPart = (int) Math.floor(n);

		for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
			String p = "";
			for (int j = 0; j < unit[1].length && n > 0; j++) {
				p = digit[integerPart % 10] + unit[1][j] + p;
				integerPart = integerPart / 10;
			}
			s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i]
					+ s;
		}
		return head
				+ s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "")
						.replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
	}

	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public final static String getIpAddress(HttpServletRequest request)
			throws IOException {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}

	/**
	 * 从request中获得参数Map，并返回可读的Map
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map properties = request.getParameterMap();
		// 返回值Map
		Map<String, String> returnMap = new HashMap<String, String>();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}

	/**
	 * 从request中获得参数Map，并返回String map
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getParameterString(HttpServletRequest request) {
		Map<String, String> map = getParameterMap(request);
		StringBuffer sb = new StringBuffer("params:[");
		java.util.Map.Entry entry;
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			entry = (java.util.Map.Entry) iterator.next();
			sb.append("</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
					.append(entry.getKey().toString())
					.append("&nbsp;&nbsp;:&nbsp;&nbsp;")
					.append(null == entry.getValue() ? "" : entry.getValue()
							.toString())
					.append(iterator.hasNext() ? "," : "</br>]");
		}
		if (map.size() == 0) {
			sb.append("]");
		}
		return sb.toString();
	}

	/**
	 * 求百分比
	 * 
	 * @param x
	 * @param total
	 * @return
	 */
	public static int getPercent(long x, long total) {
		int result = 0;
		if (x == 0 || total == 0) {
			return result;
		}
		double x1 = x;
		double total1 = total;
		result = (int) ((x1 / total1) * 100);
		return result;
	}

	/**
	 * 匹配小数
	 */
	public static boolean isDecimal(String str) {
		Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 匹配整数
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNumber(String str) {
		str = subZeroAndDot(str);
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 得到不带后缀的文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileNameWithoutType(String fileName) {
		String name = null;
		if (fileName == null || "".equals(fileName)) {
			name = "";
			return name;
		}
		name = fileName.substring(0, fileName.lastIndexOf("."));
		return name;
	}

	/**
	 * 得到后缀
	 * 
	 * @param file
	 * @return
	 */
	public static String getTypeWithoutName(String fileName) {
		String name = null;
		if (fileName == null || "".equals(fileName)) {
			name = "";
			return name;
		}
		name = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
		return name;
	}

	/**
	 * 把指定字符添加到文件名字后
	 * 
	 * @return
	 */
	public static String editFileName(String fileNameOld, String name) {
		if (isEmpty(fileNameOld)) {
			return fileNameOld;
		}
		String fileType = getTypeWithoutName(fileNameOld);
		String fileName = getFileNameWithoutType(fileNameOld);
		return fileName + name + "." + fileType;
	}

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}

	public static boolean isEmptyMore(String... strs) {
		for (String str : strs) {
			if (str == null || "".equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

	public static boolean isEmptyList(List<?> lists) {
		if (lists == null || lists.size() == 0) {
			return true;
		}
		return false;
	}

	public static String getImgName(String url) {
		if (url == null || "".equals(url)) {
			return "";
		}
		int mark = url.lastIndexOf("/");
		return url.substring(mark + 1, url.length());
	}

	public static String getImgPath(String url) {
		if (url == null || "".equals(url)) {
			return "";
		}
		int mark = url.lastIndexOf("/");
		return url.substring(0, mark);
	}

	public static String getFileName(String url) {
		if (url == null || "".equals(url)) {
			return "";
		}
		int mark = url.lastIndexOf("/");
		return url.substring(mark + 1, url.length());
	}

	/**
	 * 计算当前进度占总进度的百分比
	 * 
	 * @return
	 */
	public static int progressInt(int totalProcess, int totalSize, int indexSize) {
		int process = 0;
		int i = (indexSize / totalSize);
		process = i * (totalProcess / 100);
		return process;
	}

	/**
	 * 数字转大写
	 */
	public static String numberUppercase(int d) {
		// String[] str = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String[] str = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		// String ss[] = new String[] { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟",
		// "亿" };
		String ss[] = new String[] { "", "十", "百", "千", "万", "十", "百", "千", "亿" };
		String s = String.valueOf(d);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			String index = String.valueOf(s.charAt(i));
			sb = sb.append(str[Integer.parseInt(index)]);
		}
		String sss = String.valueOf(sb);
		int i = 0;
		for (int j = sss.length(); j > 0; j--) {
			sb = sb.insert(j, ss[i++]);
		}
		return sb.toString();
	}

	public static String numToChineNum(int d) {
		String[] str = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		String ss[] = new String[] { "个", "十", "百", "千", "万", "十", "百", "千",
				"亿" };
		String s = String.valueOf(d);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			String index = String.valueOf(s.charAt(i));
			sb = sb.append(str[Integer.parseInt(index)]);
		}
		String sss = String.valueOf(sb);
		int i = 0;
		for (int j = sss.length(); j > 0; j--) {
			sb = sb.insert(j, ss[i++]);
		}
		return sb.toString();
	}

	public static String objToString(Object par) {
		String result = "";
		if (par == null || "".equals(par)) {
			result = "";
		} else {
			result = par.toString();
		}
		return result;
	}

	/**
	 * 
	 * @param arg1
	 *            参数一
	 * @param ag2
	 *            参数二
	 * @param type
	 *            0(+) 1(-) 2(*) 3(/)
	 * @param fixed
	 *            小数点位数 -1为默认
	 * @return
	 */
	public static String Operation(String arg1, String arg2, int type, int fixed) {
		if (Util.isEmptyMore(arg1, arg2)) {
			return "";
		}
		NumberFormat nf = NumberFormat.getInstance();
		BigDecimal arge1 = new BigDecimal(arg1.replaceAll(",", ""));
		BigDecimal arge2 = new BigDecimal(arg2.replaceAll(",", ""));
		BigDecimal result = null;
		/**
		 * 加减乘除操作
		 */
		switch (type) {
		case 0:
			result = arge1.add(arge2);
			break;
		case 1:
			result = arge1.subtract(arge2);
			break;
		case 2:
			result = arge1.multiply(arge2);
			break;
		case 3:
			result = arge1.divide(arge2);
			break;
		default:
			break;
		}
		if (fixed != -1) {
			result = result.setScale(fixed, BigDecimal.ROUND_HALF_UP);
		}
		return nf.format(Double.valueOf(result.toString()));
	}
	public static double Distance1(double long1, double lat1, double long2,  
	        double lat2) {  
	    double a, b, R;  
	    R = 6378137; // 地球半径  
	    lat1 = lat1 * Math.PI / 180.0;  
	    lat2 = lat2 * Math.PI / 180.0;  
	    a = lat1 - lat2;  
	    b = (long1 - long2) * Math.PI / 180.0;  
	    double d;  
	    double sa2, sb2;  
	    sa2 = Math.sin(a / 2.0);  
	    sb2 = Math.sin(b / 2.0);  
	    d = 2  
	            * R  
	            * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
	                    * Math.cos(lat2) * sb2 * sb2));  
	    return d;  
	}  


	/**
	 * @Title: Distance
	 * @Description: 计算地球上任意两点(经纬度)距离
	 * @author ydfeng
	 * @param long1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param long2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 * @throws
	 */
	public static String Distance(double long1, double lat1, double long2,
			double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2
				* R
				* Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
						* Math.cos(lat2) * sb2 * sb2));
		BigDecimal bd = new BigDecimal(d).setScale(2, 4);
		return bd.toString();
	}

	public static String changeToBig(double value) {

		char[] hunit = { '十', '百', '千' }; // 段内位置表示

		char[] vunit = { '万', '亿' }; // 段名表示

		char[] digit = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' }; // 数字表示

		long midVal = (long) (value * 100); // 转化成整形

		String valStr = String.valueOf(midVal); // 转化成字符串

		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分

		String rail = valStr.substring(valStr.length() - 2); // 取小数部分

		String prefix = ""; // 整数部分转化的结果

		String suffix = ""; // 小数部分转化的结果

		// 处理小数点后面的数

		if (rail.equals("00")) { // 如果小数部分为0

			// suffix="整";

		} else {

			// suffix=digit[rail.charAt(0)-'0']+"角"+digit[rail.charAt(1)-'0']+"分";
			// //否则把角分转化出来

		}

		// 处理小数点前面的数

		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组

		boolean preZero = false; // 标志当前位的上一位是否为有效0位（如万位的0对千位无效）

		byte zeroSerNum = 0; // 连续出现0的次数

		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字

			int idx = (chDig.length - i - 1) % 4; // 取段内位置

			int vidx = (chDig.length - i - 1) / 4; // 取段位置

			if (chDig[i] == '0') { // 如果当前字符是0

				preZero = true;

				zeroSerNum++; // 连续0次数递增

				if (idx == 0 && vidx > 0 && zeroSerNum < 4) {

					prefix += vunit[vidx - 1];

					preZero = false; // 不管上一位是否为0，置为无效0位

				}

			} else {

				zeroSerNum = 0; // 连续0次数清零

				if (preZero) { // 上一位为有效0位

					prefix += digit[0]; // 只有在这地方用到'零'

					preZero = false;

				}

				prefix += digit[chDig[i] - '0']; // 转化该数字表示

				if (idx > 0)
					prefix += hunit[idx - 1];

				if (idx == 0 && vidx > 0) {

					prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿

				}

			}

		}

		// if(prefix.length() > 0) prefix += '圆'; //如果整数部分存在,则有圆的字样

		return prefix + suffix; // 返回正确表示

	}
}
