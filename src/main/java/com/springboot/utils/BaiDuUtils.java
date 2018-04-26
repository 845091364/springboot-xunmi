package com.springboot.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import net.sf.json.JSONObject;
public class BaiDuUtils {
	
	/**
	 * 根据经度纬度返回省份，城市，等信息
	 * @param lat 纬度
	 * @param lng 经度
	 * @return
	 * @throws IOException
	 */
	public static BaiDuAPiEntity getAddress(String lng,String lat) throws IOException{
		URL resjson = new URL("http://api.map.baidu.com/geocoder/v2/?" + "callback=renderReverse&"
				+ "location="+lat+","+lng+"&output=json&pois=1&ak=2CwUk7PWEUqY1Ye156wCDdYp");
		BufferedReader in = new BufferedReader(new InputStreamReader(resjson.openStream(),"utf-8"));
		String res;
		StringBuilder sb = new StringBuilder("");
		while ((res = in.readLine()) != null) {
			sb.append(res.trim());
		}
		in.close();
		String str = sb.toString();
		JSONObject obj =JSONObject.fromObject(JSONObject.fromObject(str.substring(29, str.length()-1)).get("result"));
		BaiDuAPiEntity baiDuAPiEntity= (BaiDuAPiEntity) JSONObject.toBean(JSONObject.fromObject(obj.get("addressComponent")),BaiDuAPiEntity.class);
		return baiDuAPiEntity;
	}
	public static void main(String[] args) {
		
		try {
			BaiDuAPiEntity b = getXY("临河里地铁口", "北京市");
			BaiDuAPiEntity b2 = getXY("高楼金", "北京市");
			
			double m = getDistanceFromTwoPoints(39.871048,116.692375,39.886425,116.68843);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param address 详细地址(如，北京朝阳大悦城)
	 * @param city (城市，如，北京)
	 * @return 返回坐标(经纬度)
	 * @throws IOException
	 */
	
	public static BaiDuAPiEntity getXY(String address,String city) throws IOException{
		URL resjson = new URL("http://api.map.baidu.com/geocoder/v2/?callback=renderOption&output=json&address="+address+"&city="+city+"&ak=2CwUk7PWEUqY1Ye156wCDdYp");
		BufferedReader in = new BufferedReader(new InputStreamReader(resjson.openStream()));
		String res;
		StringBuilder sb = new StringBuilder("");
		while ((res = in.readLine()) != null) {
			sb.append(res.trim());
		}
		in.close();
		String str = sb.toString();
		JSONObject obj =JSONObject.fromObject(JSONObject.fromObject(str.substring(27, str.length()-1)).get("result"));
		BaiDuAPiEntity baiDuAPiEntity= (BaiDuAPiEntity) JSONObject.toBean(JSONObject.fromObject(obj.get("location")),BaiDuAPiEntity.class);
		return baiDuAPiEntity;
	}
	
	/**
	 * @return 返回亮点之间的距离
	 */
	private static final Double PI = Math.PI;  
    private static final Double PK = 180 / PI;
	 public static double getDistanceFromTwoPoints(double lat_a, double lng_a,double lat_b,double lng_b) {
		 	  
	        double t1 = Math.cos(lat_a / PK) * Math.cos(lng_a / PK) * Math.cos(lat_b / PK) * Math.cos(lng_b / PK);  
	        double t2 = Math.cos(lat_a / PK) * Math.sin(lng_a / PK) * Math.cos(lat_b / PK) * Math.sin(lng_b / PK);  
	        double t3 = Math.sin(lat_a / PK) * Math.sin(lat_b / PK);  
	        double tt = Math.acos(t1 + t2 + t3);  
	        System.out.println("两点间的距离：" + 6366000 * tt + " 米");  
	        return 6366000 * tt;  
	    } 
}
