package com.practice.leetcode;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode {
    public static void main(String[] args){
        LeetCode leetCode=new LeetCode();
        leetCode.isInterleave("aabcc","dbbca","aadbbcbcac");
    }
    // https://practice.geeksforgeeks.org/problems/mobile-numeric-keypad5456/1
    public int calculateSum(int [][]array,int column[],int row){
        int sum=0;
        for(int c:column){
            sum+=array[row][c];
        }
        return sum;
    }
    public long getCount(int N)
    {
        int array[][]=new int[N+1][10];
        long sum=10;
        Arrays.fill(array[1],1);
        for(int row=2;row<=N;row++){
            for(int key=0;key<=9;key++){
                sum=0;
                switch (key){
                    case 0:{
                        array[row][key]=calculateSum(array, new int[]{0, 8},row-1);break;
                    }
                    case 1:{
                        array[row][key]=calculateSum(array, new int[]{1,2, 4},row-1);break;
                    }
                    case 2:{
                        array[row][key]=calculateSum(array, new int[]{2,1,3,5},row-1);break;
                    }
                    case 3:{
                        array[row][key]=calculateSum(array, new int[]{3,2, 6},row-1);break;
                    }
                    case 4:{
                        array[row][key]=calculateSum(array, new int[]{1,4,5,7},row-1);break;
                    }
                    case 5:{
                        array[row][key]=calculateSum(array, new int[]{2,4,6,8,5},row-1);break;
                    }
                    case 6:{
                        array[row][key]=calculateSum(array, new int[]{3,5,6,9},row-1);break;
                    }
                    case 7:{
                        array[row][key]=calculateSum(array, new int[]{4,7,8},row-1);break;
                    }
                    case 8:{
                        array[row][key]=calculateSum(array, new int[]{7,5,9,0,8},row-1);break;
                    }
                    case 9:{
                        array[row][key]=calculateSum(array, new int[]{6,8,9},row-1);break;
                    }
                }
                sum+=array[row][key];
            }
        }
        return sum;
        // Your code goes here
    }
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
    public int maxProfit(int k, int[] prices) {
        int profitArray[][]=new int[k+1][prices.length];
        int max=0;
        for(int day=1;day<=k;day++){
            for(int price=1;price<=prices.length;price++){
                max=profitArray[day][price-1];
                for(int priceRange=0;priceRange<price;priceRange++){
                    max=Math.max(max,profitArray[day-1][priceRange]+prices[price]-prices[priceRange]);
                }
                profitArray[day][price]=max;
            }
        }
        return profitArray[k][prices.length];
    }
    // https://leetcode.com/problems/interleaving-string/
    public boolean isInterleave(String s1, String s2, String s3){
        return checkInterleaving(s1,s2,s3,0,0,0);
    }
    Map<String, Boolean> mem=new HashMap<>();
    public boolean checkInterleaving(String a,String b,String c,int aPointer,int bPointer,int cPointer){
        if(cPointer==c.length()){
            if(aPointer==a.length() && bPointer==b.length())return true;
            return false;
        }
        boolean result=false;
        String key=aPointer+"/"+bPointer+"/"+cPointer;
        if(mem.containsKey(key))return mem.get(key);
        if(aPointer<a.length() && bPointer<b.length() && c.charAt(cPointer)==a.charAt(aPointer) && c.charAt(cPointer)==b.charAt(bPointer)){
             result= checkInterleaving(a,b,c,aPointer+1,bPointer,cPointer+1)
                    || checkInterleaving(a,b,c,aPointer,bPointer+1,cPointer+1);
        }
       else  if (aPointer<a.length() && c.charAt(cPointer)==a.charAt(aPointer)){
            result= checkInterleaving(a,b,c,aPointer+1,bPointer,cPointer+1);
        }
        else if (bPointer<b.length() && c.charAt(cPointer)==b.charAt(bPointer)){
            result= checkInterleaving(a,b,c,aPointer,bPointer+1,cPointer+1);
        }
        mem.put(key,result);
        return result;
    }
}
