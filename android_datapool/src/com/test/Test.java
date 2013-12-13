package com.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.test.AndroidTestCase;
import android.util.Log;

import com.utils.DataPool;

public class Test extends AndroidTestCase {
	

	public void test1(){
		MyData1 m1 = new MyData1("Å£±Æ£¡", 9999);
		MyData1 m2 = new MyData1("android ", 99999);
		MyData1 m3 = new MyData1("123123", 999119);
		DataPool dp = new DataPool(getContext());
		System.out.println(dp.add("1",m1));
		System.out.println(dp.add("2",m2));
		System.out.println(dp.add("a",m3));
		Log.i("debug", "-->"+((MyData1)dp.get("1")).toString());
		Log.i("debug", "-->"+((MyData1)dp.get("2")).toString());
		Log.i("debug", "-->"+((MyData1)dp.get("a")).toString());
	}

	public void test2(){
		List<String> list = new ArrayList<String>();
		list.add("go");
		list.add("1");
		list.add("¶Ç×ÓºÃ¶ö°¡£¡");
	   Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("go",1);
		map.put("ÍÃ×Ó",333);
		map.put("2", 1111);
        MyData2 m = new MyData2(list, map);
        DataPool dp = new DataPool(getContext());
        System.out.println(dp.add("1",m));
        Log.i("debug", "-->"+((MyData2)dp.get("1")).toString());
	}
	 
    public void test3(){
    	MyData1 m1 = new MyData1("Å£±Æ£¡", 9999);
		MyData1 m2 = new MyData1("android ", 99999);
		MyData1 m3 = new MyData1("123123", 999119);
		DataPool dp = new DataPool("test",getContext());
		System.out.println(dp.add("1",m1));
		System.out.println(dp.add("2",m2));
		System.out.println(dp.add("a",m3));
		dp.remove("2");
		if(dp.contains("2"))System.out.println("remove error");
		else System.out.println("remove ok!");
		//Log.i("debug", "-->"+((MyData1)dp.get("2")).toString());
		dp.removeAll();
		if(dp.contains("a"))System.out.println("remove  all error");else System.out.println("remove all is ok");
    }
    
    public void test4(){
    	MyData1 m1 = new MyData1("Å£±Æ£¡", 9999);
		MyData1 m2 = new MyData1("android ", 99999);
		MyData1 m3 = new MyData1("123123", 999119);
		DataPool dp = new DataPool("test",getContext());
		System.out.println(dp.add("1",m1));
		System.out.println(dp.add("2",m2));
		System.out.println(dp.add("a",m3));
		
		System.out.println(dp.set("2",new MyData1("android lover", 1001))? "---ok!" :"key is not contain !");
		System.out.println(dp.set("ac",new MyData1("test 4 is ongoing", 1003))? "---ok!" :"key is not contain !");
		
		
    }
    
	
}

class MyData1 implements Serializable {
	private String key;
	private int value;
   
	public MyData1(String key, int value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "MyData1 [key=" + key + ", value=" + value + "]";
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	 
	
}

class MyData2 implements Serializable{
	List<String> list;
	Map<String, Integer> map;
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public Map<String, Integer> getMap() {
		return map;
	}
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
	public MyData2(List<String> list, Map<String, Integer> map) {
		super();
		this.list = list;
		this.map = map;
	}
	@Override
	public String toString() {
		return "MyData2 [list=" + list + ", map=" + map + "]";
	}
	
	
}