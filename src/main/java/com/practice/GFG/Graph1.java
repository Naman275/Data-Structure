package com.practice.GFG;
import java.util.*;

public class Graph1 {
    public static void main(String[] args) {
        Graph1 runme = new Graph1();
        String av = "01";
        System.out.println(av.substring(av.length() - 1));
        System.out.println(Integer.parseInt(av));
        //System.out.println(runme.canCompleteCircuit(Arrays.asList(2, 3, 4), Arrays.asList(3, 4, 3)));
    }
    class doit{
        doit prev,next;
        int value;
        int key;
    }
    int max=0;
    doit head,tail;
    Map<Integer,doit> mapit=new HashMap<>();

    public void set(int key, int value) {
        if(mapit.size()<max){
            doit addme=new doit();
            addme.key=key;addme.value=value;
            tail.next=addme;
            mapit.put(key,addme);
        }
        else {
            doit temp=head;mapit.remove(temp.key);
            head=head.next;
            temp.next=null;
            temp.value=value;
            temp.key=key;
        }
    }

}
/*
    public int canCompleteCircuit(final List<Integer> gas, final List<Integer> cost) {
        int i=0,j=1;
        int petrol=gas.get(i)-cost.get(i);
        int limit=gas.size();
        while (i!=j){
            while(petrol<0){
                petrol=petrol-(gas.get(i)-cost.get(i));
                i++;
                if(i==0)return -1;
            }
            if(i==j){
                j++;
                j=j%limit;
                petrol=gas.get(i)-cost.get(i);
                continue;
            }
            petrol+=gas.get(j)-cost.get(j);
            j++;
            j=j%limit;
        }
        if(petrol<0)return -1;
        return i;
    }

}
/*
    public int numDecodings(String A) {
        if(A.length()<=1 && isValid(A))return 1;
        if(A.length()<=1 && !isValid(A))return 0;
        int array[]=new int[A.length()+1];
        array[A.length()]=1;
        if(isValid(A.substring(A.length()-1)))array[A.length()-1]=1;
        boolean valid1,valid2;
        for(int y=A.length()-2;y>=0;y--){
            valid1=isValid(A.substring(y,y+1));
            valid2=isValid(A.substring(y,y+2));
            if(valid1==true && valid2==true){array[y]=array[y+1]+array[y+2];continue;}
            if(valid1==true)array[y]=array[y+1];
            if(valid2==true)array[y]=array[y+2];
        }
        return array[0];
    }
    public boolean isValid(String number){
        if(number.charAt(0)=='0')return false;
        int no=Integer.parseInt(number);
        return no>=1 && no<=26;
    }

}
/*
    public ArrayList<Integer> solve(int A, int B, int C, int D) {
        ArrayList<Integer> result=new ArrayList<>();
        Set<Integer> unique=new HashSet<>();
        int currmin=Math.min(A,Math.min(B,C));
        Queue<Integer> minq=new PriorityQueue<>();
        minq.add(A);minq.add(B);minq.add(C);
        while (result.size()!=D){
            currmin=minq.poll();
            if(unique.contains(currmin))continue;
            minq.add(currmin*A);
            minq.add(currmin*B);
            minq.add(currmin*C);
            unique.add(currmin);
            result.add(currmin);
        }
        return result;
    }
    }
    /*
    public int justdoit(ArrayList<Integer> input){
        int f[]=new int[input.size()];
        int b[]=new int[input.size()];
        Arrays.fill(f,1);
        Arrays.fill(b,1);
        for(int y=0;y<input.size();y++)
            for(int j=0;j<=y;j++){
                if(input.get(y)>input.get(j) && f[j]+1>f[y])
                    f[y]=f[j]+1;
            }
        for(int y=input.size()-1;y>=0;y--){
            for(int j=input.size()-1;j>=y;j--){
                if(input.get(y)>input.get(j) && b[j]+1>b[y])
                    b[y]=b[j]+1;
            }
        }
        int max=0;
        for(int y=0;y<input.size();y++)max=Math.max(f[y]+b[y]-1,max);
        return max;
    }
}
/*
    public boolean isMatch(String s, String p) {
        boolean array[][]=new boolean[p.length()+1][s.length()+1];
        array[0][0]=true;
        for(int row=1;row<p.length()+1;row++){
            if(p.charAt(row-1)=='*')array[row][0]=array[row-2][0];
        }
        for(int row=1;row<p.length()+1;row++){
            for(int column=1;column<s.length()+1;column++){
                if(p.charAt(row-1)!='.' && p.charAt(row-1)!='*'){
                    array[row][column]=p.charAt(row-1)==s.charAt(column-1) && array[row-1][column-1];
                    continue;
                }
                if(p.charAt(row-1)=='.'){
                    array[row][column]=array[row-1][column-1];continue;
                }
                if(row==0)continue;
                char previous=p.charAt(row-2);
                if(previous==s.charAt(column-1) || previous=='.'){
                    array[row][column]=array[row-2][column] || array[row][column-1];
                }
                else
                {
                    array[row][column]=array[row-2][column];
                }
            }
        }
        return array[p.length()][s.length()];
    }
}
/*
    public int isMatch(final String B, final String A) {
        boolean array[][]=new boolean[A.length()+1][B.length()+1];
        array[A.length()][B.length()]=true;
        for(int y=A.length()-1;y>=0;y--){
            for(int j=B.length();j>=0;j--){
                if(j==B.length()){
                    if(A.charAt(y)=='*')array[y][j]=array[y+1][j];
                    continue;}
                if(A.charAt(y)!='*' && A.charAt(y)!='?')array[y][j]=(A.charAt(y)==B.charAt(j) && array[y+1][j+1]);
                if(A.charAt(y)=='?')array[y][j]=array[y+1][j+1];
                else
                    array[y][j]=array[y+1][j] || array[y][j+1];
            }
        }
        return array[0][0]==true?1:0;
    }
}
/*
    public int isMatch(final String A, final String B) {
        //if(A.charAt(A.length()-1)!='*' && B.charAt(B.length()-1)!='*' && A.length()!=B.length())return 0;
        int a=0,b=0;
        while(a<A.length() && b<B.length()){
            if(A.charAt(a++)=='?' || B.charAt(b++)=='?')continue;
            while (a<A.length() && A.charAt(a)=='*'){a++; b=a;}
            while (b<B.length() && B.charAt(b)=='*'){b++; a=b;}
            if(a<A.length() && b<B.length() && A.charAt(a)!=B.charAt(b))return 0;
        }
        if(a!=b || a!=A.length()-1)return 0;
        return 1;
    }
    public int anytwo(String A) {
        int count[][]=new int[A.length()+1][A.length()+1];
        for(int y=1;y<=A.length();y++){
            for(int z=1;z<=A.length();z++){
                if(A.charAt(y-1)==A.charAt(z-1) && y!=z){
                    count[y][z]=1+count[y-1][z-1];
                    continue;
                }
                count[y][z]=Math.max(count[y-1][z],count[y][z-1]);
            }
        }
        return count[A.length()][A.length()];
    }

}
/*
    public int longestValidParentheses(String A) {
        int left=0,right=0,max=0;
        for(int y=0;y<A.length();y++){
            if(A.charAt(y)=='(')left++;
            else right++;
            if(right>left)left=right=0;
            System.out.println("left="+left+"    right:"+right+"    "+right*2);
            if(left>=right)max=Math.max(max,right*2);
        }
        return max;
    }

}
/*
    public String multiple(int A) {
        Queue<String> q=new LinkedList<>();
        Set<Integer> rem=new HashSet<>();
        q.add("1");String value=null;
        int reme=0;
        while (!q.isEmpty()){
            int size=q.size();
            while (size-->0){
                value=q.poll();
                reme=findit(value,A);
                if(reme==0)return value;
                if(rem.contains(reme))continue;
                rem.add(reme);
                q.add(value+"0");
                q.add(value+"1");
            }
        }
        return value;

    }
    public int findit(String value,int A){
        int rem=0;
        for(int y=0;y<value.length();y++){
            rem=(rem*10+Integer.parseInt(""+value.charAt(y)))%A;
        }
       return rem;
    }
    public int deleteEdge(int[] A, int[][] B) {
        int mod=(int)1e9+7;
        int totalSum=0;
        for(int a:A){
            totalSum+=a;
        }
        Map<Integer,List<Integer>> adlist=new HashMap<>();
        List<Integer> useme=null;
        for(int y=0;y<B.length;y++){
            useme=adlist.getOrDefault(B[y][0],new ArrayList<Integer>());
            useme.add(B[y][1]);
            adlist.put(B[y][0],useme);
            useme=adlist.getOrDefault(B[y][1],new ArrayList<Integer>());
            useme.add(B[y][0]);
            adlist.put(B[y][1],useme);
        }
        int max=0;
        boolean visited[]=new boolean[A.length+1];
        for(int key:adlist.keySet()){
           max= Math.max(max,performDFS(adlist,key,0,totalSum,max,visited,A));
            visited=new boolean[A.length+1];
        }
        return max%(int)1e9+7;
    }
    public int performDFS(Map<Integer,List<Integer>> adlist,int value,int localSum,int totalSum,int max,boolean visited[],int weight[]) {
        if(visited[value]==true || adlist.get(value)==null)return max;
        visited[value]=true;
        int temp=(((localSum+weight[value-1])*(totalSum-(localSum+weight[value-1])))%(int)1e9+7);
        if(temp>max)max=temp;
        for(int a:adlist.get(value)){
            max=Math.max(max,performDFS(adlist,a,localSum+weight[value-1],totalSum,max,visited,weight)%(int)1e9+7);
        }
        return max;
    }
}
/*
    public int solve(int[][] A) {
        boolean visited[][]=new boolean[A.length][A[0].length];
        for(int y=0;y<A.length;y++){
            for(int j=0;j<A[0].length;j++){
                if(A[y][j]==1)
                    traverse(A,visited,y,j,1);
            }
        }
        return maxCount;
    }
    int maxCount=0;
    public int traverse(int [][]A,boolean visited[][],int x,int y,int count){
        if(x<0 || x>= A.length || y<0 || y>=A[0].length || visited[x][y] || A[x][y]==0)return count;
        if(count>maxCount)maxCount=count;
        visited[x][y]=true;
        count=traverse(A,visited,x+1,y,count+1);
        count=traverse(A,visited,x-1,y,count+1);
        count=traverse(A,visited,x,y+1,count+1);
        count=traverse(A,visited,x,y-1,count+1);
        count=traverse(A,visited,x+1,y+1,count+1);
        count=traverse(A,visited,x-1,y+1,count+1);
        count=traverse(A,visited,x-1,y-1,count+1);
        count=traverse(A,visited,x+1,y-1,count+1);
        return count;
    }
}
/*
    public int snakeLadder(ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> B) {
        boolean visited[]=new boolean[101];
        Map<Integer,Integer> ladder=new HashMap<>();
        Map<Integer,Integer> snake=new HashMap<>();
        for(ArrayList<Integer> a:A){
           ladder.put(a.get(0),a.get(1));
        }
        for(ArrayList<Integer> a:B){
            snake.put(a.get(0),a.get(1));
        }
        killit(ladder,snake,1,0,visited);
        Queue<Integer> q=new LinkedList<>();
        q.add(1);visited[1]=true;
        int countme=0,value=0,size=0,temp=0;
        while (!q.isEmpty()){
            countme++;
            size=q.size();
            while (size-- > 0) {
                value=q.poll();

                if(value==100)return countme;

                    if(value+1 < 100 && visited[value+1]==false){
                    temp=value+1;
                    if(ladder.get(temp)!=null)temp=ladder.get(temp);
                    if(snake.get(temp)!=null)value=snake.get(temp);
                    visited[temp]=true;q.add(temp);
                }
                if(value+2 < 100 && visited[value+2]==false){
                    temp=value+2;
                    if(ladder.get(temp)!=null)temp=ladder.get(temp);
                    if(snake.get(temp)!=null)value=snake.get(temp);
                    visited[temp]=true;q.add(temp);
                }
                if(value+3 < 100 && visited[value+3]==false){
                    temp=value+3;
                    if(ladder.get(temp)!=null)temp=ladder.get(temp);
                    if(snake.get(temp)!=null)value=snake.get(temp);
                    visited[temp]=true;q.add(temp);
                }
                if(value+4 < 100 && visited[value+4]==false){
                    temp=value+4;
                    if(ladder.get(temp)!=null)temp=ladder.get(temp);
                    if(snake.get(temp)!=null)value=snake.get(temp);
                    visited[temp]=true;q.add(temp);
                }
                if(value+5 < 100 && visited[value+5]==false){
                    temp=value+5;
                    if(ladder.get(temp)!=null)temp=ladder.get(temp);
                    if(snake.get(temp)!=null)value=snake.get(temp);
                    visited[temp]=true;q.add(temp);
                }
                if(value+6 < 100 && visited[value+6]==false){
                    temp=value+6;
                    if(ladder.get(temp)!=null)temp=ladder.get(temp);
                    if(snake.get(temp)!=null)value=snake.get(temp);
                    visited[temp]=true;q.add(temp);
                }
            }
        }
        return -1;
    }
    int countme=Integer.MAX_VALUE;
    public void killit(Map<Integer,Integer> ladder,Map<Integer,Integer> snake,int currentNo,int count,boolean visited[]){
        if(currentNo>100 || visited[currentNo]==true)return;
        if(ladder.get(currentNo)!=null)currentNo=ladder.get(currentNo);
        if(snake.get(currentNo)!=null)currentNo=snake.get(currentNo);
        if(currentNo==100){
            if(countme>count)countme=count;
            return;
        }
        for(int y=1;y<=6;y++){
            visited[currentNo]=true;
            killit(ladder,snake,currentNo+y,count+1,visited);
            visited[currentNo]=false;
        }
    }
    public void solve(ArrayList<ArrayList<Character>> a) {
        if(a.size()==0)return;
        for(int c=0;c<a.get(0).size();c++){
            if(a.get(0).get(c)=='O')markit(a,0,c);
        }
        for(int c=0;c<a.get(a.size()-1).size();c++){
            if(a.get(a.size()-1).get(c)=='O')markit(a,a.size()-1,c);
        }
        for(int r=0;r<a.size();r++){
            if(a.get(r).get(0)=='O')markit(a,r,0);
            if(a.get(r).get(a.get(r).size()-1)=='O')markit(a,r,a.get(r).size()-1);
        }
        for(int y=0;y<a.size();y++){
            for(int j=0;j<a.get(y).size();j++){
                if(a.get(y).get(j)=='Q')a.get(y).set(j,'O');
                else a.get(y).set(j,'X');
            }
        }

    }
    public void markit(ArrayList<ArrayList<Character>> board , int x,int y){
        if(x<0 || x>=board.size() || y >=board.get(x).size() || y<0 || board.get(x).get(y)=='X' || board.get(x).get(y)=='Q')return;
        board.get(x).set(y,'Q');
        markit(board,x+1,y);
        markit(board,x-1,y);
        markit(board,x,y+1);
        markit(board,x,y-1);
    }
    public void solve(char[][] board) {
        if(board.length==0)return;
        for(int c=0;c<board[0].length;c++){
            if(board[0][c]=='O')markit(board,0,c);
        }
        for(int c=0;c<board[board.length-1].length;c++){
            if(board[board.length-1][c]=='O')markit(board,board.length-1,c);
        }
        for(int r=0;r<board.length;r++){
            if(board[r][0]=='O')markit(board,r,0);
            if(board[r][board[r].length-1]=='O')markit(board,r,board[r].length-1);
        }
        for(int y=0;y<board.length;y++){
            for(int j=0;j<board[y].length;j++){
                if(board[y][j]=='Q')board[y][j]='O';
                else board[y][j]='X';
            }
        }
        return;
    }
    public void markit(char[][]board , int x,int y){
        if(x<0 || x>=board.length || y >=board[x].length || y<0 || board[x][y]=='X' || board[x][y]=='Q')return;
        board[x][y]='Q';
        markit(board,x+1,y);
        markit(board,x-1,y);
        markit(board,x,y+1);
        markit(board,x,y-1);
    }
}/*
    public ArrayList<Integer> stepnum(int A, int B) {
        ArrayList<Integer> result=new ArrayList<>();
        Queue<Integer> q=new LinkedList<>();q.add(0);
        q.add(1);q.add(2);q.add(3);q.add(4);q.add(5);q.add(6);q.add(7);
        q.add(8);q.add(9);
        int value=0,rem=0;
        while (!q.isEmpty()){
            value=q.poll();
            System.out.println(value);
            if(value>B)return result;
            if(value>=A && value<=B)result.add(value);
            if(value==0)continue;
            rem=value%10;
            if(rem!=0 && (value*10 + (rem-1))<B)q.add(value*10 + rem-1);
            if(rem!=9 && (value*10 + (rem+1)<B))q.add(value*10 + rem+1);

        }
        return result;
    }

}
/*
    public boolean exist(String[] board, String word) {
        for(int y=0;y<board.length;y++){
            for(int j=0;j<board[y].length();j++){
                if(board[y].charAt(j)==word.charAt(0) && findMe(board,word,0,y,j)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean findMe(String []board,String word,int wordIndex,int x,int y){
        if(x<0 || x>=board.length || y<0 || y>=board[0].length() || board[x].charAt(y)!=word.charAt(wordIndex))return false;
        if(wordIndex==word.length()-1)return true;
        if (findMe(board,word,wordIndex+1,x+1,y) ||
                findMe(board,word,wordIndex+1,x-1,y)||
                findMe(board,word,wordIndex+1,x,y+1)||
                findMe(board,word,wordIndex+1,x,y-1))
            return true;

        return false;
    }
}
*/