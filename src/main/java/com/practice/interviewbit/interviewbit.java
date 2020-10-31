package com.practice.interviewbit;

import java.util.ArrayList;
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
public class interviewbit {
    public static void main(String []args){
        interviewbit runme=new interviewbit();
        System.out.println(runme.generateTrees(3).size());
    }
    public ArrayList<TreeNode> generateTrees(int a) {
        return generateMe(1,a);
    }
    public ArrayList<TreeNode> generateMe(int start,int end){
        ArrayList<TreeNode> result=new ArrayList<>();
        if(end<start){result.add(null);return result;}
        for(int i=start;i<=end;i++){
            ArrayList<TreeNode> left=generateMe(start,i-1);
            ArrayList<TreeNode> right=generateMe(i+1,end);
            for(int ii=0;ii<left.size();ii++){
                for(int j=0;j<right.size();j++){
                    TreeNode root=new TreeNode(i);
                    root.left=left.get(ii);
                    root.right=right.get(j);
                    result.add(root);
                }
            }
        }
        return result;
    }
}
