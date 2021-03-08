package com.practice.GFG;

import java.util.HashSet;
import java.util.Set;

public class GFGPractice {
    public static void main(String[] args){

    }
    //https://practice.geeksforgeeks.org/problems/the-celebrity-problem/1
    int celebrity(int M[][], int n){
        // code here
        Set<Integer> celebList=new HashSet<>();
        for(int c=0;c<M.length;c++)celebList.add(c);
        for(int r=0;r<M.length;r++){
            for(int c=0;c<M.length;c++){
                if(celebList.contains(c) && M[r][c]==0 && c!=r){
                    celebList.remove(c);
                }
            }
        }
        //System.out.println("ll"+celebList.size());
        if(celebList.size()==1){
            return celebList.iterator().next();}
        return -1;
    }
}
