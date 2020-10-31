package com.practice.GFG;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.*;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}
class ListNode {
     public int val;
     public ListNode next;
      ListNode(int x) { val = x; next = null; }
  }

public class Graph {
    public static void main(String[] args) {
        Graph g = new Graph();
        ArrayList<String> a = new ArrayList<>();
        a.add("hot");
        a.add("dot");
        a.add("dog");
        a.add("lot");
        a.add("log");
        a.add("cog");
        String A = "hit", B = "cog";
    }
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        return null;
    }
}
/*
    public int solve(String beginWord, String endWord, ArrayList<String> wordList) {
        if(!wordList.contains(endWord)) {
            return 0;
        }
        Queue<String> q = new LinkedList<>();
        int target = 1;
        q.add(beginWord);
        HashSet<String> hs = new HashSet<String>();
        hs.add(beginWord);
        while(q.size() > 0) {
            target++;
            int size = q.size();
            for(int i=0; i<size; i++) {
                String word = q.remove();
                if(word.equals(endWord)) {
                    return target;
                }
                int count = 0;
                for(int k=0; k<word.length(); k++) {
                    if(word.charAt(k) != endWord.charAt(k)) {
                        count++;
                    }
                }
                if(count == 1) {
                    return target;
                }
                for(int j=0; j<wordList.size(); j++) {
                    int sum = 0;
                    for(int k=0; k<word.length(); k++) {
                        if(word.charAt(k) != wordList.get(j).charAt(k)) {
                            sum++;
                        }
                    }
                    if(sum == 1) {
                        if(!hs.contains(wordList.get(j))) {
                            q.add(wordList.get(j));
                            hs.add(wordList.get(j));
                        }
                    }
                }
            }
        }
        return 0;
    }
}
/*


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return solve(beginWord,endWord,wordList);

    }
    public int solve(String A, String B, List<String> C) {
        if(A==B)return 0;
        Set<String> unique=new HashSet<>();
        for(String s:C){
            unique.add(s);
        }
        if(!unique.contains(B))return 0;
        Map<String,ArrayList<String>> mapit=new HashMap<>();
        createList(A,mapit,unique);
        for(String s:unique){
            createList(s,mapit,unique);

        }
        Map<String,Boolean> visited=new HashMap<>();
        traverse(A,B,mapit,visited,1);
        return totalCount==Integer.MAX_VALUE?0:totalCount;
    }
    int totalCount=Integer.MAX_VALUE;
    Map<String,Integer> bestCount;
    public int traverse(String value,String targetValue,Map<String, ArrayList<String>> mapit,Map<String,Boolean> visited,int count){
        if(visited.getOrDefault(value,false) || mapit.get(value)==null || totalCount<count)return count;
        if(value.equals(targetValue)){
            totalCount=totalCount>count?count:totalCount;
            return count;
        }
        if(bestCount.containsKey(value)){
            int vl=bestCount.get(value);
            if(vl!=-1){
                totalCount=totalCount>(count+vl)?(count+vl):totalCount;
            }
            return count;
        }
        ArrayList<String> useme=mapit.get(value);
        for(int y=0;y<useme.size();y++){
            visited.put(value,true);
            int val=traverse(useme.get(y),targetValue,mapit,visited,count+1);
            if(val!=-1)
            bestCount.put(value,val-count);
            visited.put(value,false);
        }
        return -1;
    }
    public void createList(String value, Map<String, ArrayList<String>> mapit,Set<String> unique) {
        ArrayList<String> paired=new ArrayList<>();
        String replaced;
        for(int y=0;y<value.length();y++){
            for(char a='a';a<='z';a++){
                replaced=value.substring(0,y)+a+value.substring(y+1);
                if(replaced.equals(value))continue;
                if(unique.contains(replaced))paired.add(replaced);
            }
        }
        mapit.put(value,paired);
    }

}
/*
    public int solve(String A, String B, List<String> C) {
        if(A==B)return 0;
        Set<String> unique=new HashSet<>();
        for(String s:C){
            unique.add(s);
        }
        if(!unique.contains(B))return 0;
        Map<String,ArrayList<String>> mapit=new HashMap<>();
        createList(A,mapit,unique);
        for(String s:unique){
            createList(s,mapit,unique);

        }
        Map<String,Boolean> visited=new HashMap<>();
        traverse(A,B,mapit,visited,1);
        return totalCount==Integer.MAX_VALUE?0:totalCount;
    }
    int totalCount=Integer.MAX_VALUE;
    Map<String,Integer> bestCount;
    public void traverse(String value,String targetValue,Map<String, ArrayList<String>> mapit,Map<String,Boolean> visited,int count){
        if(visited.getOrDefault(value,false) || mapit.get(value)==null || totalCount<count)return;
        if(value.equals(targetValue)){
            totalCount=totalCount>count?count:totalCount;
            return;
        }

        ArrayList<String> useme=mapit.get(value);
        for(int y=0;y<useme.size();y++){
            visited.put(value,true);
            traverse(useme.get(y),targetValue,mapit,visited,count+1);
            bestCount.put(value,totalCount-count);
            visited.put(value,false);
        }
        return;
    }
    public void createList(String value, Map<String, ArrayList<String>> mapit,Set<String> unique) {
        ArrayList<String> paired=new ArrayList<>();
        String replaced;
        for(int y=0;y<value.length();y++){
            for(char a='a';a<='z';a++){
                replaced=value.substring(0,y)+a+value.substring(y+1);
                if(mapit.containsKey(replaced))continue;
                if(replaced.equals(value))continue;
                if(unique.contains(replaced))paired.add(replaced);
            }
        }
        mapit.put(value,paired);
    }
}
/*
    public int knight(int A, int B, int C, int D, int E, int F) {
        if(C==E && D==F)return 0;
        E--;F--;C--;D--;
        Map<String,Boolean> visited=new HashMap<>();
        int chessboard[][]=new int[A][B];A--;B--;
        Queue<ArrayList<Integer>> q=new LinkedList<>();
        ArrayList<Integer> addme=new ArrayList<>();
        ArrayList<Integer> useme=null;
        addme.add(C);addme.add(D);q.add(addme);
        int size=0,r=0,c=0,count=0;
        while (!q.isEmpty()){
            size=q.size();
            count++;
            while (size-->0){
                useme=q.poll();
                r=useme.get(0);c=useme.get(1);
                //x+1
                if(checkLimit(r-2,c+1,A,B,visited)){
                    if(r-2==E && c+1==F)return count;
                    useme=new ArrayList<>();useme.add(r-2);useme.add(c+1);q.add(useme);
                }
                if(checkLimit(r+2,c+1,A,B,visited)){
                    if(r+2==E && c+1==F)return count;
                    useme=new ArrayList<>();useme.add(r+2);useme.add(c+1);q.add(useme);
                }
                if(checkLimit(r-1,c+2,A,B,visited)){
                    if(r-1==E && c+2==F)return count;
                    useme=new ArrayList<>();useme.add(r-1);useme.add(c+2);q.add(useme);
                }
                if(checkLimit(r+1,c+2,A,B,visited)){
                    if(r+1==E && c+2==F)return count;
                    useme=new ArrayList<>();useme.add(r+1);useme.add(c+2);q.add(useme);
                }
                if(checkLimit(r-2,c-1,A,B,visited)){
                    if(r-2==E && c-1==F)return count;
                    useme=new ArrayList<>();useme.add(r-2);useme.add(c-1);q.add(useme);
                }
                if(checkLimit(r+2,c-1,A,B,visited)){
                    if(r+2==E && c-1==F)return count;
                    useme=new ArrayList<>();useme.add(r+2);useme.add(c-1);q.add(useme);
                }
                if(checkLimit(r-1,c-2,A,B,visited)){
                    if(r-1==E && c-2==F)return count;
                    useme=new ArrayList<>();useme.add(r-1);useme.add(c-2);q.add(useme);
                }
                if(checkLimit(r+1,c-2,A,B,visited)){
                    if(r+1==E && c-2==F)return count;
                    useme=new ArrayList<>();useme.add(r+1);useme.add(c-2);q.add(useme);
                }
            }
        }
        return -1;
    }
    public boolean checkLimit(int r,int c,int A,int B,Map<String,Boolean> visited){
        if(r<0 || r>A || c<0 || c>B || visited.getOrDefault("r"+r+"c"+c,false))return false;
        System.out.println("r="+r+"  c:"+c);
        visited.put("r"+r+"c"+c,true);
        return true;
    }
}
/*

    public int fibsum(int A) {
        ArrayList<Integer> fiblist=new ArrayList<>();
        int a=1,b=1;
        fiblist.add(a);
        fiblist.add(b);
        while((a+b)<A){
            fiblist.add(a+b);
            b=a+b;a=b-a;
        }
        int sum=A,count=0,value=0;int counter=fiblist.size()-1;
        while(sum>0){
            while(counter>=0 && fiblist.get(counter)>sum)counter--;
            value=fiblist.get(counter);
            count=count+(sum/value);
            sum=sum-((sum/value)*value);
        }
        return count;
    }

}
/*
    public TreeNode sortedListToBST(ListNode a) {
        ArrayList<Integer> addme=new ArrayList<>();
        while(a!=null){
            addme.add(a.val);
            a=a.next;
        }
        return convertMe(0,addme.size()-1,addme);
    }
    public TreeNode convertMe(int start,int end,ArrayList<Integer> addme){
        if(start>end)return null;
        int mid=(start+end)/2;
        TreeNode current=new TreeNode(addme.get(mid));
        current.left=convertMe(start,mid-1,addme);
        current.right=convertMe(mid+1,end,addme);
        return current;
    }

}
/*
    public int solve(ArrayList<Integer> A) {
        Map<Integer,ArrayList<Integer>> mapit=new HashMap<>();
        ArrayList<Integer> useme=null;
        int root=0,value=0;
        for(int y=0;y<A.size();y++){
            value=A.get(y);
            if(value==-1){root=y;continue;}
            useme=mapit.getOrDefault(value,new ArrayList<>());
            useme.add(y);
            mapit.put(value,useme);
        }
        return 0;
    }


}
/*
    public int black(ArrayList<String> A) {
        int result=0;
        boolean visited[][]=new boolean[A.size()][A.get(0).length()];
        for(int r=0;r<A.size();r++){
            for(int c=0;c<A.get(r).length();c++){
                if(A.get(r).charAt(c)=='X' && visited[r][c]==false){
                    result++;
                    performTraversal(r,c,A,visited);
                }
            }
        }
        return result;
    }
    public void performTraversal(int x,int y,ArrayList<String> A,boolean visited[][]){
        if(x<0 || x>=A.size() || y<0 || y > A.get(0).length() || visited[x][y]==true || A.get(x).charAt(y)!='X')return;
        if(A.get(x).charAt(y)=='X'){
            visited[x][y]=true;
        }
        performTraversal(x-1,y,A,visited);
        performTraversal(x+1,y,A,visited);
        performTraversal(x,y-1,A,visited);
        performTraversal(x,y+1,A,visited);
    }

}/*
    public int solve(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
        boolean globalVisited[]=new boolean[A+1];
        Map<Integer,ArrayList<Integer>> mapit=new HashMap<>();
        ArrayList<Integer> useme=null;
        for(int y=0;y<B.size();y++){
            useme=mapit.getOrDefault(B.get(y),new ArrayList<>());
            useme.add(C.get(y));
            mapit.put(B.get(y),useme);
        }
       for(int key:mapit.keySet()){
           globalVisited=new boolean[A+1];
           if(true==performDFS(globalVisited,key,mapit))return 0;
       }
        return 1;
    }

    public boolean performDFS(boolean visited[],int value,Map<Integer,ArrayList<Integer>> mapit){
        if(visited[value]==true)return true;//yes cycle is there
        ArrayList<Integer> map=mapit.get(value);
        if(map==null)return false;
        visited[value]=true;
        for(int y=0;y<map.size();y++){
            if(true==performDFS(visited,map.get(y),mapit))return true;
        }
        return false;
    }
}
/*
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        ArrayList<Integer> addme=null;
        Queue<TreeNode> q=new LinkedList<>();
        q.add(A);int size=0;
        while(!q.isEmpty()){
            size=q.size();
            addme=new ArrayList<>();
            while(size-->0){
                TreeNode temp=q.poll();
                if(temp.left!=null)q.add(temp.left);
                if(temp.right!=null)q.add(temp.right);
                addme.add(temp.val);
            }
            result.add(addme);
        }
        return result;
    }
}
/*
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        Collections.sort(B,(a,b)->(a.get(2)-b.get(2)));
        int parent[]=new int[A+1];
        for(int y=0;y<parent.length;y++){
            parent[y]=y;
        }
        int sum=0;
        for(int y=0;y<B.size();y++){
            if(true==cycle(B.get(y).get(0),B.get(y).get(1),parent))continue;
            sum+=B.get(y).get(2);
        }
        return sum;
    }
    public int findParent(int a,int parent[]){
        if(parent[a]==a)return a;
        parent[a]=findParent(parent[a],parent);
        return parent[a];
    }
    public int compress(int a,int parent[],int value){
        if(parent[a]==a){parent[a]=value;return value;}
        compress(parent[a],parent,value);
        parent[a]=value;
        return value;
    }
    public boolean cycle(int a,int b,int parent[]){
        int parenta=findParent(a,parent);
        int parentb=findParent(b,parent);
        if(parenta!=parentb){
            compress(a,parent,parentb);
            return false;
        }
        else {
            return true;
        }
    }
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    };
}/*

    public int solve(int A, int[][] B) {

        System.out.println(B[0][2]);
        System.out.println(B[1][2]);
        System.out.println(B[2][2]);
        Arrays.sort(B);
        return 0;
    }


}
/*

    Map<Integer, UndirectedGraphNode> mapit=null;
    Map<Integer,Boolean> visited=null;
    public UndirectedGraphNode fillMe(UndirectedGraphNode node) {
        if(visited.get(node.label)!=null && visited.get(node.label)==true)return null;
        mapit.put(node.label,new UndirectedGraphNode(node.label));
        visited.put(node.label,true);
        for(int y=0;y<node.neighbors.size();y++){
            fillMe(node.neighbors.get(y));
        }
        UndirectedGraphNode newme=null;
        if(mapit.get(node.label)==null){
            newme=new UndirectedGraphNode(node.label);
            mapit.put(node.label,newme);
        }
        else
        {
            newme=mapit.get(node.label);
        }
        for(int y=0;y<node.neighbors.size();y++){
            newme.neighbors.add(mapit.get(node.neighbors.get(y).label));
        }
        return newme;
    }


}
/*
    public int solve(int A, int[][] B) {
        boolean visited[]=new boolean[A+1];
        for(int y=0;y<B.length;y++){
            if(visited[B[y][1]]==true)return 1;
            visited[B[y][1]]=true;
        }
        return 0;
    }
    public String multiple(int A) {
        String array[]=new String[A];
        int cu=0,n=0;
        Queue<String> q=new LinkedList<>();
        boolean visited[]=new boolean[A];
        q.add("1");
        int rem=0;
        String checkme="";
        while(!q.isEmpty()){
            checkme=q.poll();
            rem=remi(checkme,A);
            if(visited[rem]==true)continue;
            visited[rem]=true;
            if(rem==0)break;
            q.add(checkme+"0");
            q.add(checkme+"1");
        }
        return checkme;
    }
    public int remi(String A,int no){
        int r=0,digit=0;
        for(int y=0;y<A.length();y++){
            digit=A.charAt(y)-'0';
            r=(r*10)+digit;
            r=r%no;
        }
        return r;
    }
}
/*
    public String solve(int rows, int column, int circles, int radius, int[] X, int[] Y) {
        int array[][]=new int[rows+1][column+1];
        List<Integer> addme;
        for(int k=0;k<circles;k++){
            for(int i=0;i<array.length;i++){
                for(int j=0;j<array[i].length;j++){
                    int x=Math.abs(X[k]-i);
                    int y=Math.abs(Y[k]-j);
                    if(radius*radius>=(x*x + y*y))array[i][j]=-1;
                }
            }
        }
        Stack<List<Integer>> sk=new Stack<>();
        addme=new ArrayList<>();
        addme.add(0);addme.add(0);
        sk.push(addme);
        while (array[rows][column]!=1 && !sk.isEmpty()){
            List<Integer> list=sk.pop();
            int x=list.get(0);
            int y=list.get(1);
            if(array[x][y]==0){
                if(x+1<=rows && array[x+1][y]==0){
                    addme=new ArrayList<>();
                    addme.add(x+1);
                    addme.add(y);
                    sk.push(addme);
                }
                if(x-1>=0 && array[x-1][y]==0){
                    addme=new ArrayList<>();
                    addme.add(x-1);
                    addme.add(y);
                    sk.push(addme);
                }
                if(y+1<=column && array[x][y+1]==0){
                    addme=new ArrayList<>();
                    addme.add(x);
                    addme.add(y+1);
                    sk.push(addme);

                }
                if(y-1>=0 && array[x][y-1]==0){
                    addme=new ArrayList<>();
                    addme.add(x);
                    addme.add(y-1);
                    sk.push(addme);
                }
                if(x+1<=rows && y+1 <=column && array[x+1][y+1]==0){
                    addme=new ArrayList<>();
                    addme.add(x+1);
                    addme.add(y+1);
                    sk.push(addme);
                }
                if(x+1<=rows && y-1 >=0 && array[x+1][y-1]==0){
                    addme=new ArrayList<>();
                    addme.add(x+1);
                    addme.add(y-1);
                    sk.push(addme);
                }
                if(x-1>=0 && y+1<=column && array[x-1][y+1]==0){
                    addme=new ArrayList<>();
                    addme.add(x-1);
                    addme.add(y+1);
                    sk.push(addme);
                }
                if(x-1>=0 && y-1>=0 && array[x-1][y-1]==0){
                    addme=new ArrayList<>();
                    addme.add(x-1);
                    addme.add(y-1);
                    sk.push(addme);
                }
                array[x][y]=1;
            }
        }
        return array[rows][column]==1?"YES":"NO";
    }
}
*/
