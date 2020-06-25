package com.emprendesoftcr;


public class Practica {

	public static void main(String[] args) {
		String str = "Sony is going to introduce Internet TV soon";
		System.out.println("Original String :" + str);
		String resultStr = new StringBuffer(str).reverse().toString();
		System.out.println(resultStr);
		resultStr = process(str);
		System.out.println(resultStr);
		resultStr = process2(str);
		System.out.println(resultStr);
		}
	public static String process(String str) {
	  StringBuilder stringBuilder = new StringBuilder();
	  char[] strChars = str.toCharArray();
	  for (int i = strChars.length-1; i >=0; i--) {
			stringBuilder.append(strChars[i]);
		}
	  return stringBuilder.toString();
		 
	}
	public static String process2(String str) {
		if(str.length() < 2) {
			return str;
		}
		return process2(str.substring(1))+ str.charAt(0);
		
	}
	

}
