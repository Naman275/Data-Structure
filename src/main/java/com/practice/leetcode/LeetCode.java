package com.practice.leetcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.*;

public class LeetCode {
    public static void main(String[] args){
        LeetCode leetCode=new LeetCode();

    }
   // https://leetcode.com/problems/partition-equal-subset-sum
   public boolean canPartition(int[] nums) {
       if(nums.length==0)return false;
       int totalSum=0;
       for(int n:nums)totalSum+=n;
       if((totalSum & 1)==1)return false;
       boolean sumarray[][]=new boolean[nums.length+1][(totalSum/2)+1];
       for(int row=0;row<nums.length;row++)sumarray[row][0]=true;
       for(int row=1;row<sumarray.length;row++){
           for(int c=1;c<sumarray[row].length;c++){
               if((c>=nums[row-1] && sumarray[row-1][c-nums[row-1]]) || sumarray[row-1][c]){
                   if(c==(sumarray[row].length-1))return true;
                   sumarray[row][c]=true;
               }
           }
       }
       return false;
   }
    //https://leetcode.com/problems/sudoku-solver/
    public void solveSudoku(char[][] board) {
        found=false;
        solveit(board,0,0);
    }
    boolean found=false;
    public void solveit(char[][] board,int row,int column){
        if(found==true)return;
        if(column>=9){column=0;row++;}
        if(row>=9)return;
        if(row==8 && column ==8){
            if(board[row][column]=='.') {
                for (char y = '1'; y <= '9'; y++) {
                    if (check(board, row, column, y)) {
                        board[row][column] = y;
                        found = true;
                        System.out.println("found");
                        return;
                    }
                }
            }
            else{found = true;}
            return;
        }
        if(board[row][column]!='.'){solveit(board, row, column + 1);}
        else{
            for (char y = '1'; y <= '9'; y++) {
                if (check(board, row, column, y)) {
                    board[row][column] = y;
                    solveit(board, row, column + 1);
                    if(found==false)board[row][column] = '.';
                }
            }
        }
    }
    public boolean check(char[][]board,int row,int column,char ch){
        for(int c=0;c<board.length;c++)if(board[row][c]==ch)return false;
        for(int r=0;r<board.length;r++)if(board[r][column]==ch)return false;
        int sr=row-(row%3),sc=column-(column%3);
        //System.out.println(""+sr+":"+sc);
        for(int r=sr;r<sr+3;r++){
            for(int c=sc;c<sc+3;c++){
                if(board[r][c]==ch)return false;
            }
        }
        return true;
    }
    //https://leetcode.com/problems/remove-invalid-parentheses
    public List<String> removeInvalidParentheses(String s){
        int maxAllowed=tobeRemoved(s);
        Set<String> addme=new HashSet<>();
        if(maxAllowed==0){addme.add(s);return new ArrayList<>(addme);}
        removeInvalidParentheses(s,maxAllowed,addme,new HashSet<>());
        return new ArrayList<>(addme);
    }
    public void removeInvalidParentheses(String s,int max,Set<String> addme,Set<String> processed) {
        if(max>s.length() || processed.contains(s+"%"+max))return;
        if(max==0){
            if(0==tobeRemoved(s))addme.add(s);
            return;
        }
        for(int y=0;y<s.length();y++){
            while(y<s.length()-1 && s.charAt(y)==s.charAt(y+1))y++;
            removeInvalidParentheses(s.substring(0,y)+s.substring(y+1),max-1,addme,processed);
            processed.add(s+"%"+max);
        }
    }
    public Integer tobeRemoved(String s){//O(n)
        int opening=0,closing=0;
        int pointer=0;
        char ch;
        while(pointer<s.length()){
            ch=s.charAt(pointer);
            if(ch=='(')opening++;
            if(ch==')'){
                if(opening>0)opening--;
                else closing++;
            }
            pointer++;
        }
        return opening+closing;
    }
    //https://leetcode.com/problems/valid-parentheses
    public boolean isValid(String s) {
        Stack<Character> sk=new Stack<>();
        int pointer=0;
        char ch;
        while(pointer<s.length()){
            ch=s.charAt(pointer);
            switch(ch){
                case '(':{sk.push(ch);break;}
                case '[':{sk.push(ch);break;}
                case '{':{sk.push(ch);break;}
                case ')':{
                    while(!sk.isEmpty() && sk.peek()!='(')
                        return false;
                    if(sk.isEmpty())return false;
                    sk.pop();
                    break;
                }
                case ']':{
                    while(!sk.isEmpty() && sk.peek()!='[')
                        return false;
                    if(sk.isEmpty())return false;
                    sk.pop();break;
                }
                case '}':{
                    while(!sk.isEmpty() && sk.peek()!='{')
                        return false;
                    if(sk.isEmpty())return false;
                    sk.pop();break;
                }
            }
            pointer++;
        }
        return sk.size()==0?true:false;
    }
    //https://leetcode.com/problems/word-break-ii/
    public List<String> wordBreak(String s, List<String> wordDict) {
        boolean [][] result=new boolean[s.length()][s.length()];
        // fill up the first row
        List<String> addme=new ArrayList<>();
        for(int c=0;c<result.length;c++){
            if(wordDict.contains(s.substring(0,c+1)))
                result[0][c]=true;
        }
        for(int row=1;row<s.length();row++){
            for(int c=row;c<s.length();c++){
                if(wordDict.contains(s.substring(row,c+1))){
                    for(int localrow=row-1;localrow>=0;localrow--){
                        if(result[localrow][row-1]){
                            result[row][c]=true;break;
                        }
                    }
                }
            }
        }
        createWord(result,addme,new StringBuilder(),s.length()-1,s.length()-1,s);
        return addme;
    }
    public String reverseme(String text){
        List<String> addme=new ArrayList<>();
        for(String a:text.split(" ")){
            addme.add(a);
        }
        StringBuilder sb=new StringBuilder();
        for(int y=addme.size()-1;y>=0;y--){
            sb.append(addme.get(y)+" ");
        }
        sb.setLength(sb.length()-1);
        return sb.toString();
    }
    public void createWord(boolean[][] result,List<String> addme, StringBuilder useme,int row,int column,String text){
        if(row<0 && column<0){
         addme.add(reverseme(useme.toString()));
         return;
        }
        int length=useme.length();
        for(int y=row;y>=0;y--){
            useme.setLength(length);
            if(result[y][column]){
                useme.append(text.substring(y,column+1)+" ");
                createWord(result,addme,useme,y-1,y-1,text);
            }
        }
    }
    //https://leetcode.com/problems/n-queens/
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result=new ArrayList<>();
        boolean check[][]=new boolean[n][n];
        lol(0,check,n,result);
        return result;
    }
    public void lol(int row,boolean [][]check,int max,List<List<String>> result){
        if(row==(max-1)){
            for(int c=0;c<max;c++){
                if(traverse(row,c,check)){
                    check[row][c]=true;
                    List<String> addme=prepareResult(check);
                    result.add(addme);
                    check[row][c]=false;
                }
            }
            return;
        }
        for(int c=0;c<max;c++){
            if(traverse(row,c,check)){
                check[row][c]=true;
                lol(row+1,check,max,result);
                check[row][c]=false;
            }
        }
    }
    public List<String> prepareResult(boolean [][]check){
        List<String> result=new ArrayList<>();
        for(int r=0;r<check.length;r++){
            StringBuilder sb=new StringBuilder();
            for(int c=0;c<check.length;c++){
                sb.append(check[r][c]==true?'Q':'.');
            }
            result.add(sb.toString());
            sb.setLength(0);
        }
        return result;
    }
    public boolean traverse(int row,int column,boolean [][]check){
        for(int y=0;y<check.length;y++){
            if(check[row][y]==true)return false;
        }
        for(int r=0;r<check.length;r++){
            if(check[r][column]==true)return false;
        }

        for(int r=row,c=column;c<check.length && r>=0;r--,c++){
            if(check[r][c]==true)return false;
        }

        for(int r=row,c=column;c<check.length && r<check.length;r++,c++){
            if(check[r][c]==true)return false;
        }

        for(int r=row,c=column;c>=0 && r>=0;r--,c--){
            if(check[r][c]==true)return false;
        }

        for(int r=row,c=column;c>=0 && r<check.length;r++,c--){
            if(check[r][c]==true)return false;
        }

        return true;
    }
    //https://leetcode.com/problems/reorganize-string/
    public String reorganizeString(String S) {
        Map<Character,Integer> count=new HashMap<Character,Integer>();
        for(char c:S.toCharArray()){
            count.put(c,count.getOrDefault(c,0)+1);
        }
        PriorityQueue<Character> pq=new PriorityQueue<>((a,b)->{return count.get(b)-count.get(a);});
        pq.addAll(count.keySet());
        StringBuilder sb=new StringBuilder();
        char current,next;
        int time;
        while (pq.size()>1){
            current=pq.poll();
            next=pq.poll();
            sb.append(current);sb.append(next);
            time=count.get(current);
            if(time>1){count.put(current,time-1);pq.add(current);}
            time=count.get(next);
            if(time>1){count.put(next,time-1);pq.add(next);}
        }
        if(!pq.isEmpty()){
            current=pq.poll();
            time=count.get(current);
            if (time==1){sb.append(current);}
            else return "";
        }
        return sb.toString();
    }
    //https://leetcode.com/problems/distribute-candies/submissions/
    public int distributeCandies(int[] candyType) {
        Set<Integer> unique=new HashSet<>();
        for(int n:candyType){
            unique.add(n);
        }
        int max=candyType.length/2;
        if(unique.size()>=max)return max;
        return unique.size();
    }
    //https://leetcode.com/problems/kth-smallest-element-in-a-bst
    public int kthSmallest(TreeNode root, int k) {
        count=k;
        traverse(root);
        if(count>=1)return -1;
        return no;
    }
    public void traverse(TreeNode root){
        if(root==null || allow==false)return;
        traverse(root.left);
        count--;
        if(count==0){
            no=root.val;
            allow=false;
        }
        traverse(root.right);
    }
    int count=0,no=0;
    boolean allow=true;
    // https://leetcode.com/problems/balance-a-binary-search-tree
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> list=new ArrayList<>();
        traverse(root,list);
        return buildUp(0,list.size()-1,list);
    }
    public TreeNode buildUp(int min,int max,List<TreeNode> list){
        if(min<0 || max>=list.size() || min>max)return null;
        TreeNode newroot=(list.get((min+max)/2));
        newroot.left=buildUp(min,((min+max)/2)-1,list);
        newroot.right=buildUp(((min+max)/2)+1,max,list);
        return newroot;
    }
    public void traverse(TreeNode root,List<TreeNode> list){
        if(root==null)return;
        traverse(root.left,list);
        list.add(root);
        traverse(root.right,list);
    }
    //https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder,Integer.MAX_VALUE,Integer.MIN_VALUE);
    }
    int index=0;
    public TreeNode bstFromPreorder(int[] preorder,
                                    int max,int min) {
        if(index>=preorder.length)return null;
        TreeNode root=null;
        if(preorder[index]>min && preorder[index]<max){
            root=new TreeNode(preorder[index]);
            index++;
            root.left=bstFromPreorder(preorder,root.val,min);
            root.right=bstFromPreorder(preorder,max,root.val);
        }
        return root;
    }
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)return null;
        if(root.val>p.val && root.val>q.val)return lowestCommonAncestor1(root.left,p,q);
        if(root.val<p.val && root.val<q.val)return lowestCommonAncestor1(root.right,p,q);
        return root;
    }
    //https://leetcode.com/problems/validate-binary-search-tree
    public boolean isValidBST(TreeNode root) {
        if(root==null || (root.left==null && root.right==null))return true;
        return isValidBST(root,Integer.MIN_VALUE-1l,Integer.MAX_VALUE+1l);
    }
    public boolean isValidBST(TreeNode root,long min,long max){
        if(root==null) return true;
        if(root.val<=min || root.val>=max)return false;
        return isValidBST(root.left,min,root.val) && isValidBST(root.right,root.val,max);
    }
    public boolean isValidBST1(TreeNode root) {
        Stack<TreeNode> sk=new Stack<>();
        sk.push(root);
        TreeNode current=root.left,previous=null;
        while(current!=null){
            sk.push(current);
            current=current.left;
        }
        while(!sk.isEmpty()){
            current=sk.pop();
            previous=current;
            current=current.right;
            while(current!=null){
                sk.push(current);
                current=current.left;
            }
            if(sk.size()>0 && previous.val>=sk.peek().val)return false;
        }
        return true;
    }
    //https://leetcode.com/problems/delete-node-in-a-bst
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null)return null;
        if(root.val==key){
            if(root.left==null && root.right==null)return null;
            if(root.left!=null && root.right!=null){
                TreeNode temp=findLeftLargest(root.left);
                root.val=temp.val;
                temp.left=deleteNode(root.left,temp.val);
                temp.right=root.right;
                return temp;
            }
            if(root.left!=null)return root.left;
            return root.right;
        }
        if(root.val>key)root.left=deleteNode(root.left,key);
        if(root.val<key)root.right=deleteNode(root.right,key);
        return root;
    }
    public TreeNode findLeftLargest(TreeNode root){
        if(root==null)return null;
        while(root.right!=null){
            root=root.right;
        }
        return root;
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
