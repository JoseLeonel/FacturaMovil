package com.emprendesoftcr;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Multiset.Entry;
import com.ibm.icu.util.BytesTrie.Iterator;
import com.itextpdf.text.DocumentException;


public class vivi {

	public static void main(String[] args) throws ParseException, FileNotFoundException, DocumentException {
		 int[] myArray = {1,2,9,2,5,3,5,1,5}; 
		 int n=3;
     int numeroMenor = myArray[0];
	   for (int i = 0; i < 3; i++) {
		  for (int j = 0; j < myArray.length; j++) {
			    if(numeroMenor < myArray[j] && numeroMenor != myArray[j] ) {
			    	numeroMenor = myArray[0];  
			    }
			}
		  System.out.print(numeroMenor + " ");
		}
		 int x,y,z;
		 x = 9;
		 y = 10;
		 
		 z = ++x+y++;
		 
		
		System.out.println("x:"+x + " y:"+ y+ "total:"+ z);
		//	  System.out.println("Number: "+numeroRepetido);				
	}
	
	

}


