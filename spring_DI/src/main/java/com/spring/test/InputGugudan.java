package com.spring.test;

import java.util.Scanner;

public class InputGugudan extends Gugudan {

   @Override
   public void execute() {
      
      int maxDan = 9;
      int maxGop = 9;
      
      Scanner scann = new Scanner(System.in);
      
      System.out.println("최고단수:");
      maxDan = Integer.parseInt(scann.nextLine());
      System.out.println("최고곱수:");
      maxGop = Integer.parseInt(scann.nextLine());
      
      for(int dan=2; dan<maxDan+1; dan++) {
         System.out.println(dan+"단");
         for(int gop=1;gop<maxGop+1;gop++) {
            System.out.println(dan+"*"+gop+"="+dan*gop);
         }
         System.out.println();
      }
      
   }
   
}