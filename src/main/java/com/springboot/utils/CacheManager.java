package com.springboot.utils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 管理缓存
 * 
 * @author ydfeng
 * 
 */
public class CacheManager {

	private static int CacheMapSize = 10000;

	private static int ClearCacheMapSize = 3000;

	private static CacheManager cacheManager = null;

	private static HashMap<String, CacheClass> cacheMap = new HashMap<String, CacheClass>();

	/**
	 * 获取cacheManager
	 * 
	 * @return
	 */
	public static CacheManager getInstance() {
		if (cacheManager == null) {
			cacheManager = new CacheManager();
		}
		return cacheManager;
	}

	/**
	 * 单实例构造方法
	 */
	private CacheManager() {
		super();
		String CacheMapSizeStr = WebAppConfig.getInstance().getByKey(
				"CacheMapSize");
		String ClearCacheMapSizeStr = WebAppConfig.getInstance().getByKey(
				"ClearCacheMapSize");
		if (!CacheMapSizeStr.equals("") || CacheMapSizeStr != null) {
			CacheMapSize = Integer.parseInt(CacheMapSizeStr);
		}
		if (!ClearCacheMapSizeStr.equals("") || ClearCacheMapSizeStr != null) {
			ClearCacheMapSize = Integer.parseInt(ClearCacheMapSizeStr);
		}
	}

	/**
	 * 同步静态方法 得到缓存
	 * 
	 * @param key
	 *            -缓存key
	 * @return
	 */
	private synchronized static CacheClass getCacheClass(String key) {
		return cacheMap.get(key);
	}

	/**
	 * 判断是否存在一个缓存
	 * 
	 * @param key
	 *            -缓存key
	 * @return
	 */
	private synchronized static boolean hasCacheClass(String key) {
		return cacheMap.containsKey(key);
	}

	/**
	 * 清除所有缓存
	 */
	public synchronized static void clearAll() {
		cacheMap.clear();
	}

	/**
	 * 清除指定的缓存
	 * 
	 * @param key
	 *            -缓存key
	 */
	public synchronized static void clearOnly(String key) {
		cacheMap.remove(key);
	}

	/**
	 * 写入缓存记录
	 * 
	 * @param key
	 * @param obj
	 */
	public synchronized static void putCacheClass(String key, CacheClass obj) {
		if (cacheMap.size() >= CacheMapSize) {
			clearCacheClassSize(ClearCacheMapSize);
		}
		cacheMap.put(key, obj);
	}

	/**
	 * 获得未终止的缓存
	 * 
	 * @param key
	 *            -缓存key
	 * @return
	 */
	public static CacheClass getCacheClassInfo(String key) {
		if (hasCacheClass(key)) {
			CacheClass CacheClass = getCacheClass(key);
			if (cacheClassExpired(CacheClass)) { // 调用判断是否终止方法
				CacheClass.setExpired(true);
			}
			return CacheClass;
		} else
			return null;
	}

	/**
	 * 写入缓存记录
	 * 
	 * @param key
	 *            -缓存key
	 * @param obj
	 *            -缓存value
	 * @param dt
	 *            -缓存超时时间
	 * @param expired
	 *            -flase不超时true超时
	 */
	public static void putCacheClassInfo(String key, CacheClass obj, long dt,
			boolean expired) {
		if (cacheMap.size() >= CacheMapSize) {
			clearCacheClassSize(ClearCacheMapSize);
		}
		CacheClass CacheClass = new CacheClass();
		CacheClass.setKey(key);
		CacheClass.setTimeOut(dt + System.currentTimeMillis());
		CacheClass.setValue(obj);
		CacheClass.setExpired(expired);
		cacheMap.put(key, CacheClass);
	}

	/**
	 * 判断缓存是否超时
	 * 
	 * @param CacheClass
	 *            -缓存对象
	 * @return
	 */
	public static boolean cacheClassExpired(CacheClass CacheClass) {
		if (null == CacheClass) {
			return false;
		}
		long now_ms = System.currentTimeMillis();
		long CacheClassDt = CacheClass.getTimeOut();
		if (CacheClassDt <= 0 || CacheClassDt > now_ms) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取缓存大小
	 * 
	 * @return
	 */
	public static int getCacheClassSize() {
		return cacheMap.size();
	}

	/**
	 * 获取缓存中所有key
	 * 
	 * @return
	 */
	public static List<String> getCacheAllkey() {
		List<String> list = new ArrayList<String>();
		if (!cacheMap.isEmpty()) {
			Iterator<String> i = cacheMap.keySet().iterator();
			while (i.hasNext()) {
				list.add(i.next());
			}
		}
		return list;
	}

	/**
	 * 清理缓存指定大小-按照载入时间处理
	 * 
	 * @param clearSize
	 * @return
	 */
	public static boolean clearCacheClassSize(int clearSize) {
		boolean clearFlag = false;
		if (!cacheMap.isEmpty()) {
			List<Entry<String, CacheClass>> list = new ArrayList<Map.Entry<String, CacheClass>>(
					cacheMap.entrySet());
			// 排序
			Collections.sort(list,
					new Comparator<Map.Entry<String, CacheClass>>() {
						public int compare(Map.Entry<String, CacheClass> o1,
								Map.Entry<String, CacheClass> o2) {
							CacheClass c1 = (CacheClass) o1.getValue();
							CacheClass c2 = (CacheClass) o2.getValue();
							return Long.valueOf(
									c1.getTimeOut() - c2.getTimeOut())
									.intValue();
						}
					});
			// 截取新的缓存数据
			List<Entry<String, CacheClass>> list_new = list.subList(clearSize,
					list.size());
			HashMap<String, CacheClass> map = new HashMap<String, CacheClass>();
			for (Entry<String, CacheClass> e : list_new) {
				map.put(e.getKey(), e.getValue());
			}
			// 清空当前缓存
			cacheMap.clear();
			// 复制给当前map
			cacheMap.putAll(map);
			clearFlag = true;
		}
		return clearFlag;
	}
}
