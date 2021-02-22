package com.practice.leetcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.GFG.tree;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class LeetCode {
    public static void main(String[] args){
        LeetCode leetCode=new LeetCode();
        leetCode.checkME();
        System.out.println(minJumps(new int[]{2 ,3 ,1, 1, 2, 4 ,2 ,0 ,1 ,1}));
        // 10 17 5 3
        //
    }
    //https://leetcode.com/problems/find-duplicate-subtrees
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String,TreeNode> unique=new HashMap<>();
        Set<TreeNode> result=new HashSet<>();
        traverse(root,unique,result);
        return new ArrayList<>(result);
    }
    public String traverse(TreeNode root,Map<String,TreeNode> mapit,Set<TreeNode> result){
        if(root==null){
            return "";
        }
        String key="R:"+root.val;
        String left=traverse(root.left,mapit,result);
        String right=traverse(root.right,mapit,result);
        key=key+"L:"+left+"R:"+right;
        if(mapit.get(key)!=null){
            result.add(mapit.get(key));
        }
        else
        {
            mapit.put(key,root);
        }
        return key;
    }
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root ==q )return root;
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if(left!=null && right != null)return root;
        if(left!=null)return left;
        return right;
    }
    // https://leetcode.com/problems/binary-tree-maximum-path-sum
    public int maxPathSum(TreeNode root) {
        traverseSum(root);
        return max;
    }
    int max=Integer.MIN_VALUE;
    public int traverseSum(TreeNode root){
        if(root==null)return 0;
        int leftSum=traverseSum(root.left);
        int rightSum=traverseSum(root.right);
        int cmax=Math.max(root.val+leftSum,root.val+rightSum);
        cmax=Math.max(root.val,cmax);
        cmax=Math.max(cmax,root.val+leftSum+rightSum);
        max=Math.max(max,cmax);
        return Math.max(Math.max(root.val,root.val+leftSum),root.val+rightSum);
    }
    //https://leetcode.com/problems/path-sum-iii/
    public int pathSum(TreeNode root, int sum) {
        traverseSum(root,sum,new ArrayList<>());
        return count;
    }
    int count=0;
    public void traverseSum(TreeNode root,int sum , List<Integer> list){
        if(root==null)return ;
        list.add(root.val);
        int local=0;
        for(int y=list.size()-1;y>=0;y--){
            local+=list.get(y);
            if(local==sum)count++;
        }
        traverseSum(root.left,sum,list);
        traverseSum(root.right,sum,list);
        list.remove(list.size()-1);
    }
    private class Node
    {
        int data;
        Node left, right;

        Node(int item)
        {
            data = item;
            left = right = null;
        }
    }
    //https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/submissions/
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer,TreeMap<Integer,List<Integer>>> mapit=new TreeMap<>();
        traverse(root,0,0,mapit);
        List<List<Integer>> result=new ArrayList<>();
        for(int key:mapit.keySet()){
            List<Integer> addme=new ArrayList<>();
            TreeMap<Integer,List<Integer>> temp=mapit.get(key);
            for(int innerKey:temp.keySet()){
                addme.addAll(temp.get(innerKey));
            }
            result.add(addme);
        }
        return result;
    }
    public void traverse(TreeNode root,int horizontal,int height,TreeMap<Integer,TreeMap<Integer,List<Integer>>> mapit){
        if(root==null)return;
        TreeMap<Integer, List<Integer>> htemp=mapit.getOrDefault(horizontal,new TreeMap<>());
        List<Integer> addme=htemp.getOrDefault(height,new ArrayList<>());
        addme.add(root.val);
        Collections.sort(addme);
        htemp.put(height,addme);
        mapit.put(horizontal,htemp);
        traverse(root.left,horizontal-1,height+1,mapit);
        traverse(root.right,horizontal+1,height+1,mapit);
    }

     public  ArrayList<Integer> topView(Node root)
    {
        ArrayList<Integer> result=new ArrayList<>();
        Map<Integer,List<Integer>> mapit=new HashMap<>();
        verticalOrder(root,0,mapit);
        List<Integer> lvlorder=levelOrder(root);
        List<Integer> keyset=new ArrayList<>();
        keyset.addAll(mapit.keySet());
        Collections.sort(keyset,Collections.reverseOrder());
        int index=Integer.MAX_VALUE;
        for(int key:keyset){
            List<Integer> values=mapit.get(key);
            if(values.size()==1){
                result.add(values.get(0));
                continue;
            }
            else
            {
                index=Integer.MAX_VALUE;
                for(int val:values){
                    index=Math.min(index,lvlorder.indexOf(val));
                }
            }
            result.add(values.get(index));
        }
        return result;
    }
    public List<Integer> levelOrder(Node root){
        List<Integer> lvlorder=new ArrayList<>();
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            int size=q.size();
            while (size-->0){
                Node temp=q.poll();
                lvlorder.add(temp.data);
                if(temp.left!=null)q.add(temp.left);
                if(temp.right!=null)q.add(temp.right);
            }
        }
        return lvlorder;
    }
    public void verticalOrder(Node root,int index,Map<Integer,List<Integer>> mapit){
        if(root==null)return;
        List<Integer> addme=mapit.getOrDefault(index,new ArrayList<>());
        addme.add(root.data);
        mapit.put(index,addme);
        verticalOrder(root.left,index-1,mapit);
        verticalOrder(root.right,index+1,mapit);
        return;
    }
   // https://leetcode.com/problems/binary-tree-right-side-view/

      public class TreeNode {
          int val;
          TreeNode left;
         TreeNode right;
        TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result=new ArrayList<>();
        Queue<TreeNode> q=new LinkedList<>();
        TreeNode node;
        if(root!=null)q.add(root);
        int size=0;boolean first=true;
        while (!q.isEmpty()){
            size=q.size();
            while (size>0){
                node=q.poll();
                if(first){
                    result.add(node.val);first=false;
                }
                if(node.right!=null)q.add(node.right);
                if(node.left!=null)q.add(node.left);
                size--;
            }
            first=true;
        }
        return result;

    }
    //https://practice.geeksforgeeks.org/problems/minimum-number-of-jumps-1587115620/1
    static int minJumps(int[] arr){
        // your code here
        int allowed=arr[0],max=arr[0],position=1,jump=1;
        while(position<arr.length){
            if(max==0)return -1;
            if(allowed<position){
                allowed=max;
                jump++;
                if(allowed>=arr.length-1)return jump;
            }
            max=Math.max(max,position+arr[position]);
            position++;
        }
        return jump;
    }
    //https://practice.geeksforgeeks.org/problems/maximum-difference-of-zeros-and-ones-in-binary-string4111/1
    static int maxSubstring(String S) {
        // code here
        int result=0,sum=0;
        int a=0,value=1;
        while(a<S.length()){
            value=1;
            if(S.charAt(a)=='1')value=-1;
            if((sum+value)<=0){
                sum=0;
            }
            sum+=value;
            result=Math.max(result,sum);
            a++;
        }
        return result;
    }
    static int countFriendsPairings(int n)
    {
        int a = 1, b = 2, c = 0;
        if (n <= 2) {
            return n;
        }
        for (int i = 3; i <= n; i++) {
            c = b + (i - 1) * a;
            a = b;
            b = c;
        }
        return c;
    }

    class testMe{
         String entityType;
         String entityId;
         String taskName;
    }
    public void checkME() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String empty = "";
            testMe r = objectMapper.readValue(empty, testMe.class);
        }
        catch (IOException jk){
            System.out.println("Fhfh");
        }
    }
    //https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int leftColumn=0,rightColumn=0,sum=0,max=0,result=0;
        int temp[]=new int[matrix[0].length];
        while(leftColumn!=matrix.length){
            rightColumn=leftColumn;
            for(int y=leftColumn;y<=rightColumn;y++){
                for(int row=0;row<matrix.length;row++){
                    temp[row]+=matrix[row][y];
                }
            }
            temp=new int[matrix[0].length];
            max=temp[0];
            for(int t:temp){
                if((sum+t)<k){

                }
                sum+=t;
                max=Math.max(max,sum);
                if(sum<0)sum=0;
            }
            leftColumn++;
        }
        return 0;
    }
    // https://practice.geeksforgeeks.org/problems/optimal-strategy-for-a-game-1587115620/1
    static long countMaximum(int arr[], int n){
        // Your code here
        return findResult(arr,0,arr.length-1,true);
    }
    static Map<String,Long> result=new HashMap<>();

    static long findResult(int arr[],int startIndex,int endIndex,boolean pick){
        if(endIndex<startIndex)return 0;
        if(pick==true && endIndex==startIndex)return arr[startIndex];
        if(pick==false && endIndex==startIndex)return 0;
        String key=startIndex+":"+endIndex;
        if(result.containsKey(key))result.get(key);
        long nextValue=0;
        if(pick) {
            nextValue = Math.max(arr[startIndex] + findResult(arr, startIndex + 1, endIndex,!pick)
                    , arr[endIndex] + findResult(arr, startIndex, endIndex - 1,!pick));
        }
        else {
            if(arr[startIndex]>arr[endIndex])
            nextValue = findResult(arr, startIndex + 1, endIndex,!pick);
            else
                nextValue=findResult(arr, startIndex, endIndex - 1,!pick);
        }
        result.put(key,nextValue);
        return nextValue;
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
