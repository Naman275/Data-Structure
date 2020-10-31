package com.practice.GFG;


import java.util.*;

class Main {
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

    public static void main(String[] args) {
        ArrayList<Integer> useme = new ArrayList<>();
        useme.addAll(Arrays.asList(60, 100, 120));
        ArrayList<Integer> useme1 = new ArrayList<>();
        useme1.addAll(Arrays.asList(10, 20, 30));
        String av = "12";
        int array[][] = {
        {
            98, 894
        },
        {
            397, 942
        },
        {
            70, 519
        },
        {
            258, 456
        },
        {
            286, 449
        },
        {
            516, 626
        },
        {
            370, 873
        },
        {
            214, 224
        },
        {
            74, 629
        },
        {
            265, 886
        },
        {
            708, 815
        },
        {
            394, 770
        },
        {
            56, 252
        }
    };
        Main m = new Main();
        m.solve(array);
    }
    private int solve(int[][] A) {
        PriorityQueue<List<Integer>> pq=new PriorityQueue<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> integers, List<Integer> t1) {
                return integers.get(0)-t1.get(0);
            }
        });
        for(int y=0;y<A.length;y++){
            List<Integer> p=new ArrayList<>();
            p.add(A[y][1]);p.add(A[y][0]);
            pq.add(p);
        }
        int count=0;
        int previous=Integer.MIN_VALUE;
        while (!pq.isEmpty()){
            List p=pq.poll();
            if(previous<(int)p.get(1)){
                count++;
                previous=(int)p.get(0);
            }
        }
        return count;
    }
    public ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> A) {
        Collections.sort(A,Collections.reverseOrder());
        ArrayList<ArrayList<Integer>> returnme=new ArrayList<>();
        int totalSum=0;
        for(int y:A){
            totalSum+=y;
        }
        double average=(double)totalSum/A.size();
        int value=-1,sum=0;
        ArrayList<Integer> firstSet=new ArrayList<>();
        boolean result[][]=solve(A,totalSum);
        for(int y=1;y<A.size()-1;y++) {
            if ((average * y) == (int) (average * y)) {
                sum = (int) (average * y);
                if(result[A.size()][sum])break;
            }
        }
        if(sum==0)return  returnme;
        return findAnswer(sum);
    }
    public ArrayList<ArrayList<Integer>> findAnswer(int sum){
        return null;
    }
    public void findallSet(ArrayList<ArrayList<Integer>> result,ArrayList<Integer> temp,int sum,boolean[][] array,ArrayList<Integer> question){
        if(sum<0)return;
        if(sum==0){
            ArrayList<Integer> addme=new ArrayList<>() ;
            addme.addAll(temp) ;
            result.add(addme);
            return;
        }
        for(int row=1;row<array.length;row++){
            if(array[row][sum]==true){
                temp.add(question.get(row-1));
                findallSet(result,temp,sum-question.get(row-1),array,question);
                temp.remove(temp.size()-1);
            }
        }
    }
    public int findValue(boolean [][]array,int sum,ArrayList<Integer> A){
        int result=-1;
        if(array[0].length<sum)return -1;
        for(int row=0;row<A.size();row++){
            if(array[row][sum]==true){
                return A.get(row-1);
            }
        }
        return -1;
    }
    private boolean[][] solve(ArrayList<Integer> A, int B) {
        boolean result[][]=new boolean[A.size()+1][B+1];
        for(int r=0;r<A.size()+1;r++){
            for(int c=0;c<B+1;c++){
                if(c==0){result[r][c]=true;continue;}
                if(r==0)continue;
                if((A.get(r-1)==c) || result[r-1][c]==true){result[r][c]=true;continue;}
                if(c>A.get(r-1) && result[r-1][c-A.get(r-1)]){
                    result[r][c]=true;
                }
            }
        }
        return result;
    }

}
/*
    public int cnttrue(String A) {
        trueResult.put("T",1);
        trueResult.put("F",0);
        falseResult.put("F",1);
        falseResult.put("T",0);
        int gap=3;
        int i=0,j=i+gap,index=1;
        while(gap<=A.length()) {
            while (j <= A.length()) {
                String value = A.substring(i, j);
                index=1;
                while(trueResult.get(value)==null && index<value.length()){
                    filloperand(value,index);
                    index+=2;
                }
                i+=2;
                j+=2;
                System.out.println(value);
            }
            i=0;
            gap+=2;
            j=i+gap;
        }
        return trueResult.get(A);
    }
    Map<String,Integer> trueResult=new HashMap<>();
    Map<String,Integer> falseResult=new HashMap<>();
    public void filloperand(String A,int index){
        int value=0;
        String left=A.substring(0,index);
        String right=A.substring(index+1);
        if(A.charAt(index)=='&'){
            value=trueResult.get(left)*trueResult.get(right);
            trueResult.put(A,value);
            value=trueResult.get(left)*falseResult.get(right);
            value+=falseResult.get(left)*trueResult.get(right);
            value+=falseResult.get(left)*falseResult.get(right);
            falseResult.put(A,falseResult.getOrDefault(A,0)+value);
            return;
        }
        if(A.charAt(index)=='|'){
            value=falseResult.get(left)*falseResult.get(right);
            falseResult.put(A,value);
            value=trueResult.get(left)*falseResult.get(right);
            value+=falseResult.get(left)*trueResult.get(right);
            value+=trueResult.get(left)*trueResult.get(right);
            trueResult.put(A,trueResult.getOrDefault(A,0)+value);
            return;
        }
        if(A.charAt(index)=='^'){
            value=falseResult.get(left)*trueResult.get(right);
            value+=falseResult.get(right)*trueResult.get(left);
            trueResult.put(A,value);
            value=falseResult.get(left)*falseResult.get(right);
            value+=trueResult.get(left)*trueResult.get(right);
            falseResult.put(A,falseResult.getOrDefault(A,0)+value);
        }
    }
}
/*
    public int solve(int[][] A) {
        int max=1,local=1;
        for(int y=0;y<A.length-1;y++){
            local=1;
            for(int j=y+1;j<A.length;j++){
                if(A[y][1]<A[j][0])local++;
            }
            max=Math.max(max,local);
        }
        return max;
    }

    public int solve(ArrayList<Integer> value, ArrayList<Integer> weight, int max) {
        int array[][]=new int[value.size()+1][max+1];
        for(int row=1;row<=value.size();row++){
            for(int c=1;c<=max;c++){
                if(weight.get(row-1)>c){array[row][c]=array[row-1][c];}
                if(weight.get(row-1)==c){array[row][c]=Math.max(array[row-1][c],value.get(row-1));continue;}
                if(weight.get(row-1)<c){
                    array[row][c]=Math.max(array[row-1][c],value.get(row-1)+array[row-1][c-weight.get(row-1)]);
                }
            }
        }
        return array[value.size()][max];
    }

}
/*
    public int solve(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        return solve(A,B,C,0);
    }
    Map<Integer,Map<Integer,Integer>> mapit=new HashMap<>();
    public int solve(ArrayList<Integer> A, ArrayList<Integer> B, int C,int i) {
        if(i>=A.size() || C<=0)return 0;
        if(mapit.containsKey(i) && mapit.get(i).containsKey(C))return mapit.get(i).get(C);
        int a=0,b=0;
        if(B.get(i)>C){
            a=solve(A,B,C-B.get(i),i+1);
            a+=A.get(i);
        }
        b=solve(A,B,C,i+1);
        Map<Integer,Integer> mapme=new HashMap<>();
        mapme.put(C,b);
        mapit.put(i,mapme);
        return Math.max(a,b);
    }

    int countW (String A) {
        int cnt = 0;
        for (int i=0; i<A.length(); i++) {
            cnt += A.charAt(i) == 'W'?1:0;
        }
        return cnt;
    }
    Map<String, Map<Integer, Integer>> memo = new HashMap<>();
    public int arrange(String A, int B) {
        if (B > A.length() || B == 0) return -1;
        if (B==1) {
            int whites = countW(A);
            return whites * (A.length() - whites);
        }
        if (memo.containsKey(A) && memo.get(A).containsKey(B)) return memo.get(A).get(B);
        int min = Integer.MAX_VALUE;
        for (int i=1; i <= 1 + A.length() - B; i++) {
            String prefix = A.substring(0,i);
            int whitesPrefix = countW(prefix);
            String suffix = A.substring(i, A.length());
            min = Math.min (min, (whitesPrefix * (i - whitesPrefix)) + arrange(suffix, B-1));
        }
        memo.putIfAbsent(A, new HashMap<>());
        memo.get(A).put(B, min);
        return min;
    }

}/*
    public int findme(List<Integer> A){
        int max=A.get(0);
        int mintillnow=A.get(0);
        int maxtillnow=A.get(0);
        for(int y=1;y<A.size();y++){
            int a=A.get(y)*mintillnow;
            int b=A.get(y)*maxtillnow;
            mintillnow=Math.min(A.get(y),Math.min(a,b));
            maxtillnow=Math.max(A.get(y),Math.max(maxtillnow,maxtillnow*A.get(y)));

        }return 0;
    }
    public int maxProduct(final List<Integer> A) {
        int minmax=A.get(0);
        int positivemax=A.get(0);
        int max=A.get(0);
        int temp1=1;
        int temp2=1;
        for(int y=1;y<A.size();y++){
            temp1=minmax*A.get(y);
            temp2=positivemax*A.get(y);
            minmax=Math.min(A.get(y),Math.min(temp1,temp2));
            positivemax=Math.max(A.get(y),Math.max(temp2,temp1));
            System.out.println("y="+y+"  min="+minmax+"   max="+positivemax);
            max=Math.max(max,positivemax);
        }
        return max;
    }

}
/*
    public ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> returnme=new ArrayList<>();
        int totalSum=0;
        for(int y:A){
            totalSum+=y;
        }
        double average=(double)totalSum/A.size();
        int value=-1,sum=0;
        ArrayList<Integer> firstSet=new ArrayList<>();
        Collections.sort(A);
        boolean result[][]=solve(A,totalSum);
        for(int y=1;y<A.size()-1;y++){
            if((average*y)==(int)(average*y)){
                sum=(int)(average*y);
                while(sum>0) {
                    value = findValue(result, sum, A);
                    if (value == -1) break;
                    firstSet.add(value);
                    sum=sum-value;
                }
                if(firstSet.size()!=y){firstSet.clear();}
                else break;
            }
        }
        ArrayList<Integer> secondSet=new ArrayList<>();
        for(int y:A){
            if(!firstSet.contains(y))secondSet.add(y);
        }
        Collections.sort(firstSet);
        Collections.sort(secondSet);
        if(firstSet.size()==0 || secondSet.size()==0)return returnme;
        if(firstSet.size()>secondSet.size()){
            returnme.add(secondSet);
            returnme.add(firstSet);
        }
        else
        {
            returnme.add(secondSet);
            returnme.add(firstSet);
        }
        return returnme;
    }
    public int findValue(boolean [][]array,int sum,ArrayList<Integer> A){
        int result=-1;
        if(array[0].length<sum)return -1;
        for(int row=1;row<A.size();row++){
            if(array[row][sum]==true){
                return A.get(row-1);
            }
        }
        return -1;
    }
    public boolean[][] solve(ArrayList<Integer> A, int B) {
        boolean result[][]=new boolean[A.size()+1][B+1];
        for(int r=0;r<A.size()+1;r++){
            for(int c=0;c<B+1;c++){
                if(c==0){result[r][c]=true;continue;}
                if(r==0)continue;
                if((A.get(r-1)==c) || result[r-1][c]==true){result[r][c]=true;continue;}
                if(c>A.get(r-1) && result[r-1][c-A.get(r-1)]){
                    result[r][c]=true;
                }
            }
        }
        return result;
    }

}/*
    Map<String,Integer> mincut=new HashMap<>();
    public int minCut(String A) {
    }
    public int findanswer(String A){
        if(mincut.get(A)!=null)mincut.get(A);
        if(A.length()==1){
            mincut.putIfAbsent(A,0);
        }
        if(checkPalindrome(A)){
            mincut.putIfAbsent(A,0);
            return 0;
        }
        int a=findanswer(A.substring(1,A.length()));
        int b=findanswer(A.substring(0,A.length()-1));
        mincut.putIfAbsent(A,Math.min(a,b)+1);
        return Math.min(a,b);
    }
    public boolean checkPalindrome(String A){
        int a=0,b=A.length()-1;
        while(a<b){
            if(A.charAt(a)!=A.charAt(b))return false;
            a++;b--;
        }
        return true;
    }
    public int jump(ArrayList<Integer> A) {
        int result[]=new int[A.size()];
        result[A.size()-1]=0;
        int temp=0,min=Integer.MAX_VALUE;
        for(int y=A.size()-2;y>=0;y--){
            temp=A.get(y);min=Integer.MAX_VALUE;
            for(int i=y+1;i<A.size();i++){
                if(temp<=0)break;
                if(result[i]+1<min)min=result[i]+1;
                temp--;
            }
            result[y]=min;
            if(temp!=0)result[y]=1;
        }
        return result[0]==Integer.MAX_VALUE?-1:result[0];
    }

    public int solve(String A) {
        boolean ab[]=new boolean[234];
        return anytwo("aaa");

    }

    public int anytwo(String A) {
        if(A==null || A.length()<1)return 0;
        int count=2,temp=0;
        while(count<A.length()){
            temp=LCS(A.substring(0,count),A.substring(1,A.length()));
            if(temp>0)return 1;
            count++;
        }
        return 0;
    }
    Map<String,Integer> mapit=new HashMap<String,Integer>();
    public int LCS(String A,String B){
        if(A==null || B==null ||A.length()<1 || B.length()<1)return 0;
        if(mapit.get(A+":"+B)!=null)return mapit.get(A+":"+B);
        if(A.charAt(0)==B.charAt(0)){
            int value=LCS(A.substring(1,A.length()),B.substring(1,B.length()));
            mapit.put(A+":"+B,1+value);
            return 1+value;
        }
        int a=LCS(A.substring(1,A.length()),B);
        int b=LCS(A,B.substring(1,B.length()));
        mapit.put(A+":"+B,Math.max(a,b));
        return Math.max(a,b);
    }
    public int solve(String A, String B) {
        int array[][] = new int[A.length() + 1][B.length() + 1];
        for (int y = 1; y <= A.length(); y++) {
            for (int z = 1; z <= B.length(); z++) {
                if (A.charAt(y) == B.charAt(z)) {
                    array[y][z] = 1 + array[y - 1][z - 1];
                    continue;
                }
                array[y][z] = Math.max(array[y - 1][z], array[y][z - 1]);
            }
        }
        return array[A.length()][B.length()];
    }

}/*
    Map<String,Integer> mapit=new HashMap<>();
    public int minDistance(String A, String B) {
        if(mapit.get(A+":"+B)!=null)return mapit.get(A+":"+B);
        if(A.length()==0)return B.length();
        if(B.length()==0)return A.length();
        if(A.charAt(0)==B.charAt(0)){
            int value= minDistance(A.substring(1,A.length()),B.substring(1,B.length()));
            mapit.put(A+":"+B,value);
            return value;
        }
        else
        {
            int a=minDistance(A,B.substring(1,B.length()));//insert
            int b=minDistance(A.substring(1,A.length()),B);//delete
            int c=minDistance(A.substring(1,A.length()),B.substring(1,B.length()));//replace
            a++;b++;c++;
            c= Math.min(a,Math.min(b,c));
            mapit.put(A+":"+B,c);
            return c;
        }
    }
}
/*

    public int canCompleteCircuit(final List<Integer> A, final List<Integer> B) {
        if(A.size()==1)return A.get(0)>=B.get(0)?1:-1;
        int petrol=0,startIndex=0,travel=Integer.MAX_VALUE,counter=0;
        while(travel!=startIndex && startIndex<A.size()){
            while((petrol+A.get(counter))>=B.get(counter)){
                petrol=petrol+A.get(counter)-B.get(counter);
                counter=(counter+1)%A.size();
                travel=counter;
            }
            if(travel==startIndex)return startIndex;
            if(startIndex==A.size()-1 || travel<0)return -1;
            startIndex=travel;
            travel=Integer.MAX_VALUE;
        }
        return -1;

    }

    public int majorityElement(final List<Integer> A) {
        Map<Integer,Integer> mapit=new HashMap<>();
        for(int y:A){
            mapit.put(y,mapit.getOrDefault(y,0)+1);
        }
        int count=0;int no=0;
        for(int y:mapit.keySet()){
            if(mapit.get(y)>count){count= mapit.get(y);no=y;}
        }
        return no;
    }
    public int seats(String A) {
        int count=0;
        ArrayList<Integer> list=new ArrayList<>();
        for(int y=0;y<A.length();y++){
            if(A.charAt(y)=='x')list.add(y);
        }
        int median=list.get(list.size()/2);
        list.remove((Integer)median);
        int removeme=median+1;
        while(removeme<A.length()) {
            if (list.contains((Integer)removeme)) list.remove((Integer)removeme++);
            break;
        }
        removeme=median-1;
        while(removeme>=0) {
            if (list.contains((Integer)removeme)) list.remove((Integer)removeme--);
            break;
        }
        for(int y:list) {
            count+=Math.abs(median-y)-1;
        }
        return count;
    }
}
/*
    public int candy(ArrayList<Integer> A) {
        List<Integer> left=new ArrayList<>();
        List<Integer> right=new ArrayList<>();
        left.add(1);
        right.add(1);
        for(int y=1;y<A.size();y++){
            if(A.get(y-1)<A.get(y)){
                left.add(left.get(y-1)+1);
            }
            else if(A.get(y-1)==A.get(y)){
                left.add(left.get(y-1));
            }
            else
                left.add(1);
        }
        for(int y=A.size()-2;y>=0;y--){
            if(A.get(y+1)<A.get(y)){
                right.add(0,right.get(0)+1);
            }
            else if(A.get(y+1)==A.get(y)){
                right.add(0,right.get(y+1));
            }
            else
                right.add(0,1);
        }
        int count=0;
        for(int y=0;y<A.size();y++){
            count+=Math.max(left.get(y),right.get(y));
        }
        return count;
    }
    public int solve(ArrayList<ArrayList<Integer>> A) {
        ArrayList<Integer> entry = new ArrayList<>();
        ArrayList<Integer> exit = new ArrayList<>();
        for (ArrayList<Integer> a : A) {
            entry.add(a.get(0));
            exit.add(a.get(1));
        }
        Collections.sort(entry);
        Collections.sort(exit);
        int a = 1, b = 0, room = 1, local = 1;
        while (a < entry.size()) {
            if (exit.get(b) > entry.get(a)) {
                local++;
            } else {
                b++;
                local--;
            }
            if (room < local) room = local;
            a++;
        }
        return room;
    }
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        ArrayList<Integer> result=new ArrayList<>();
        PriorityQueue<Integer> minq=new PriorityQueue<>();
        int val;
        for(int y=A.size()-1;y>=0;y--){
            for(int z=B.size()-1;z>=0;z--){
                val=A.get(y)+A.get(z);
                if(minq.size()<C){minq.add(val);continue;}
                if(val<=minq.peek())break;
                if(val>minq.peek()){minq.poll();minq.add(val);}
            }
        }
        while(!minq.isEmpty()){
            result.add(0,minq.poll());
        }
        return result;
    }

}
/*
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        Collections.sort(A);
        Collections.sort(B);
        Collections.reverse(A);
        Collections.reverse(B);
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < B.size(); j++) {
                int val = A.get(i) + B.get(j);
                System.out.println("i="+i+"     j="+j);
                if (pq.size() < A.size()) {
                    pq.add(val);
                } else if (pq.peek() <= val) {
                    pq.add(val);
                } else if (val < pq.peek()) {
                    break;
                }
                if (pq.size() > B.size()) {
                    pq.poll();
                }
                System.out.println("peek="+pq.peek());
            }
        }
        ArrayList<Integer> ret = new ArrayList<Integer>();
        while (pq.size() > 0) {
            ret.add(pq.poll());
        }
        Collections.reverse(ret);
        return ret;
    }

}
/*

    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        ArrayList<Integer> result=new ArrayList<>();
        if(C==0)return result;
        if(C==1){result.add(A.get(A.size()-1)+B.get(B.size()-1));return result;}
        Collections.sort(A);
        Collections.sort(B);
        PriorityQueue<Pair<Integer, Pair<Integer,Integer>>> pq=new PriorityQueue<>();
        Set<Pair<Integer,Integer>> unqiue=new HashSet<>();
        unqiue.add(new Pair<>(A.size()-1,B.size()-1));
        Pair<Integer,Integer> pp=null;
        int x,y;
        while (C-->0){
            Pair<Integer, Pair<Integer,Integer>> mapit=pq.poll();
            result.add(mapit.getKey());
            x=mapit.getValue().getKey();
            y=mapit.getValue().getValue();
            pp=new Pair<>(x-1,y);
            if(!unqiue.contains(pp)){
                unqiue.add(pp);pq.add(new Pair<>((A.get(x-1)+B.get(y)),pp));
            }
            pp=new Pair<>(x,y-1);
            if(!unqiue.contains(pp)){
                unqiue.add(pp);pq.add(new Pair<>((A.get(x)+B.get(y-1)),pp));
            }
        }
        return result;
    }

}
/*
    public void mapme(ArrayList<Integer> a,ArrayList<Integer> b){
        System.out.println((0.1+0.2)==0.3);
    }
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(A);Collections.sort(B);
        ArrayList<Integer> result=new ArrayList<>();
        PriorityQueue<Integer> q=new PriorityQueue<>(Collections.reverseOrder()) ;
        int count=1;
        int a=A.size()-1;
        result.add(A.get(a)+B.get(a));
        a--;
        while(count<=A.size()){
            count+=2;
            q.add(A.get(a)+B.get(B.size()-1));
            q.add(A.get(A.size()-1)+B.get(a));
            a--;
            result.add(q.poll());
            result.add(q.poll());
        }
        while(result.size()>A.size())result.remove(result.size()-1);
            return  result;
    }
    public void tree(ArrayList<Integer> a,ArrayList<Integer> b){
        System.out.println(1<0);
        System.out.println(0<0);
        Map<Integer,Integer> hm=new HashMap<>();

        buildTree(a,b);
    }
    public TreeNode buildTree(ArrayList<Integer> A, ArrayList<Integer> B) {
        TreeNode root= buildMe(A,B,0,A.size()-1);
        return root;
    }
    public TreeNode buildMe(ArrayList<Integer> A, ArrayList<Integer> B,int ll,int ul){
        System.out.println("start="+ll);
        if(ll<0 || ll > ul || ul<0)return null;
        System.out.println("processed="+ul);
        TreeNode root=new TreeNode(B.get(ul));
        if(ll==ul)return root;
        root.left=buildMe(A,B,0,A.indexOf(root.val)-1);
        root.right=buildMe(A,B,A.indexOf(root.val)+1,ul);
        return root;
    }
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

    private TreeNode set1() {
        TreeNode a9 = new TreeNode(9);
        TreeNode a7 = new TreeNode(7);
        TreeNode a6 = new TreeNode(6);
        TreeNode a4 = new TreeNode(4);
        TreeNode a3 = new TreeNode(3);
        TreeNode a5 = new TreeNode(5);
        TreeNode a10 = new TreeNode(10);
        a9.left = a7;
        a9.right = a10;
        a7.left = a6;
        a6.left = a4;
        a4.left = a3;
        a4.right = a5;
        return a9;
    }

    public void runMe(ArrayList<Integer> useme, ArrayList<Integer> useme2) {
        Queue<Integer> p=new LinkedList<>();
        p.add(1);
        p.add(null);
        p.add(2);
        while(!p.isEmpty()){
            System.out.println("hi");p.poll();
        }
    }

    public ArrayList<Integer> solve(TreeNode A, int B) {
        ArrayList<Integer> result=new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(A);
        boolean carryon=true;
        while (!q.isEmpty() && carryon){
            for(int y=0;y<q.size();y++) {
                TreeNode temp = q.poll();
                if (temp.val == B) continue;
                if ((temp.left != null && temp.left.val == B) || (temp.right != null && temp.right.val == B)){carryon=false;continue;}
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }

        }
        while(!q.isEmpty()){
            TreeNode temp=q.poll();
            if(temp.left!=null)result.add(temp.left.val);
            if(temp.right!=null)result.add(temp.right.val);
        }
        return result;
    }


}/*
    public ArrayList<Integer> solve(TreeNode A, int B) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(A.val == B) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(A);
        int found = 0;
        TreeNode sibling = null;
        while(!q.isEmpty()) {
            int s = q.size();
            for(int i=0; i<s; i++) {
                TreeNode temp = q.remove();
                if(temp.left != null) {
                    if(temp.left.val == B) {
                        found = 1;
                        sibling = temp.right;
                    }
                    else {
                        q.add(temp.left);
                    }
                }
                if(temp.right != null) {
                    if(temp.right.val == B) {
                        found = 1;
                        sibling = temp.left;
                    }
                    else {
                        q.add(temp.right);
                    }
                }
            }
            if(found == 1) {
                break;
            }
        }
        while(!q.isEmpty()) {
            TreeNode temp = q.remove();
            if(sibling == null) {
                res.add(temp.val);
            }
            else if(temp.val != sibling.val) {
                res.add(temp.val);
            }
        }
        return res;
    }
    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        Map<Integer,Map<Integer,Integer>> result=new TreeMap<>();
        result=fillMe(result,1,A,1);
        ArrayList<ArrayList<Integer>> finalresult=new ArrayList<>();
        for(int a:result.keySet()){
            ArrayList<Integer> addme=new ArrayList<>();
            for(int y:result.get(a).keySet()){
                addme.add(y);
            }
            finalresult.add(addme);
        }
        return finalresult;
    }
    public Map<Integer,Map<Integer,Integer>> fillMe(Map<Integer,Map<Integer,Integer>> result,
                                                  int index,TreeNode A,int height){
        if(A==null)return result;
        fillValue(result,index,A.val,height);
        fillMe(result,index-1,A.left,height+1);
        fillMe(result,index+1,A.right,height+1);
        return result;
    }
    public void fillValue(Map<Integer,Map<Integer,Integer>> result,int index,int value,int height){
        Map<Integer,Integer> addme=null;
        if(result.get(index)==null){
            addme=new TreeMap<>();
        }
        else
        {
            addme=result.get(index);
        }
        addme.put(height,value);
        result.put(index,addme);
        return;
    }
    public ArrayList<Integer> postorderTraversal(TreeNode A) {
        Stack<TreeNode> sk=new Stack<>();
        ArrayList<Integer> result=new ArrayList<>();
        TreeNode last=null;
        sk.push(A);
        while(!sk.isEmpty()){
            TreeNode top=sk.peek();
            if(top.left==null && top.right==null){
                last=sk.pop();
                result.add(last.val);continue;
            }
            if(top.right!=null && top.right==last){
                last=sk.pop();
                result.add(last.val);continue;
            }
            if(top.left!=null && top.left!=last){
                sk.push(top.left);
                continue;
            }
            if(top.right!=null && top.right!=last)
            {
                sk.push(top.right);
                continue;
            }

            last=sk.pop();
            result.add(last.val);
        }
        return result;
    }

}
/*
    public int isBalanced(TreeNode A) {
        return checkMe(A)==-1?0:1;

    }

    public int checkMe(TreeNode A){
        if(A==null)return 0;
        int a=checkMe(A.left);
        System.out.println("a="+a);
        if(a==-1)return -1;
        int b=checkMe(A.right);
        System.out.println("b="+b);
        if(b==-1 || Math.abs(a-b)>1)return -1;
        return Math.max(a,b)+1;
    }

}
/*

    public int t2Sum(TreeNode A, int B) {
        Stack<TreeNode> small=new Stack<>();
        Stack<TreeNode> large=new Stack<>();
        TreeNode traverse=A;
        while(traverse!=null){
            small.push(traverse);
            traverse=traverse.left;
        }
        traverse=A;
        while(traverse!=null){
            large.push(traverse);
            traverse=traverse.right;
        }
        int smallest=small.peek().val,largest=large.peek().val;
        while(smallest<=largest){
            if((smallest+largest)<B){
                smallest=nextsmallest(small);
            }
            if((smallest+largest)>B){
                largest=nextLargest(large);
            }
            if((smallest+largest)==B)return 1;
        }
        return 0;
    }

    public int nextLargest(Stack<TreeNode> large){
        if(large.isEmpty())return 0;
        TreeNode root=large.pop();
        if(root.left!=null){
            large.push(root.left);
            while(large.peek().right!=null) {
                large.push(large.peek().right);
            }
        }
        System.out.println("next large="+root.val);
        return root.val;
    }

    public int nextsmallest(Stack<TreeNode> small){
        if(small.isEmpty())return 0;
        TreeNode root=small.pop();
        if(root.right!=null){
            small.push(root.right);
            while(small.peek().left!=null) {
                small.push(small.peek().left);
            }
        }
        System.out.println("next small="+root.val);
        return root.val;
    }
}
/*
    public TreeNode tree(HashMap<Integer, Integer> hm, ArrayList<Integer> post, int start, int end, int preroot, int leftflag) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(post.get(end));
        int length = Math.abs(hm.get(post.get(end)) - preroot) - 1;
        int leftidx = start + length - 1;
        if (leftflag == 1) {
            leftidx = end - length - 1;
        }
        int rightidx = end - 1;
        preroot = hm.get(post.get(end));
        root.left = tree(hm, post, start, leftidx, preroot, 1);
        root.right = tree(hm, post, leftidx + 1, rightidx, preroot, 0);
        return root;
    }

    public TreeNode buildTree(ArrayList<Integer> inorder, ArrayList<Integer> postorder) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.size(); i++) {
            hm.put(inorder.get(i), i);
        }
        TreeNode root = tree(hm, postorder, 0, postorder.size() - 1, -1, 0);
        return root;
    }

    public TreeNode sortedArrayToBST(List<Integer> A) {
        TreeNode ans = findRoot(A, 1, A.size());
        return ans;

    }

    public TreeNode findRoot(List<Integer> A, int left, int right) {
        if (left > right) return null;
        System.out.println("left=" + left + "  right=" + right + "    /2=" + (left + right) / 2);
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(A.get(mid - 1));
        root.left = findRoot(A, left, mid - 1);
        root.right = findRoot(A, mid + 1, right);
        return root;
    }

}
/*

    public TreeNode buildTree(ArrayList<Integer> A, ArrayList<Integer> B) {
        //A = left root right
        //B= left right root
        return fkMe(A,B,0,B.size());
    }
    public TreeNode fkMe(ArrayList<Integer> inorder,ArrayList<Integer> postorder,int left,int right){
        if(left<0 || left>=postorder.size() || left>right)return null;
        TreeNode root=returnRoot(inorder,postorder,left,right);
        if(root==null)return root;
        root.left=fkMe(inorder,postorder,left,inorder.indexOf(root.val));
        root.right=fkMe(inorder,postorder,inorder.indexOf(root.val)+1,right);
        return root;
    }
    public TreeNode giveNode(int value){
        return new TreeNode(value);
    }
    public TreeNode returnRoot(List<Integer> a,ArrayList<Integer> postOrder,int left,int right){
        Set<Integer> unique=new HashSet<>();
        for(int y=left;y<right;y++){
            unique.add(a.get(y));
        }
        for(int y=postOrder.size()-1;y>=0;y--){
            if(unique.contains(postOrder.get(y))){
                return giveNode(postOrder.get(y));
            }
        }
        return null;
    }

}
/*
    public TreeNode buildTree(ArrayList<Integer> A) {
        return buildTreeR(A,0,A.size());
    }
    public TreeNode buildTreeR(ArrayList<Integer> A,int left,int right) {
        if(left>=A.size() || left>right)return null;
        int rootIndex=findMax(A,left,right);
        TreeNode rootNode=new TreeNode(A.get(rootIndex));
        rootNode.left=buildTreeR(A,left,rootIndex-1);
        rootNode.right=buildTreeR(A,rootIndex+1,right);
        return rootNode;
    }
    public int findMax(ArrayList<Integer> A,int left,int right)
    {
        int index=left,sum=0;
        for(int y=left;y<right;y++){
            if(sum<A.get(y)){
                sum=A.get(y);index=y;
            }
        }
        return index;
    }
    boolean canRepresentBST(int pre[], int n) {
        // Create an empty stack
        Stack<Integer> s = new Stack<Integer>();

        // Initialize current root as minimum possible
        // value
        int root = Integer.MIN_VALUE;

        // Traverse given array
        for (int i = 0; i < n; i++) {
            System.out.println("element="+pre[i]+ "   root="+root);
            // If we find a node who is on right side
            // and smaller than root, return false
            if (pre[i] < root) {
                return false;
            }

            // If pre[i] is in right subtree of stack top,
            // Keep removing items smaller than pre[i]
            // and make the last removed item as new
            // root.
            while (!s.empty() && s.peek() < pre[i]) {
                System.out.println("peek="+s.peek());
                root = s.peek();
                s.pop();
            }

            // At this point either stack is empty or
            // pre[i] is smaller than root, push pre[i]
            s.push(pre[i]);
        }
        return true;
    }

    public int longestConsecutive(final List<Integer> A) {
        Set<Integer> unique=new TreeSet<Integer>();
        for(int y:A){
            unique.add(y);
        }
        int maxCount=1,localCount=1,c=0,n=0;
        Iterator<Integer> iter=unique.iterator();
        n=iter.next();
        while(iter.hasNext()){
            localCount=1;
            while(iter.hasNext()){
                c=n;n=iter.next();
                if(c+1==n)
                localCount++;
                else
                    break;
            }
            if(localCount>maxCount)maxCount=localCount;
        }
        return maxCount;
    }
    public ArrayList<ArrayList<Integer>> subsetsWithDup(List<Integer> a) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList());
        if (a == null || a.size() == 0) {
            return result;
        }

        Collections.sort(a);
        auxSubsetWithDup(a, 0, new ArrayList<>(), result);
        return result;
    }

    private void auxSubsetWithDup(List<Integer> nums, int start, ArrayList<Integer> temp, ArrayList<ArrayList<Integer>> result) {
        for (int i = start; i < nums.size(); i++) {
            if (i > start && nums.get(i) == nums.get(i - 1)) {
                continue;
            }

            temp.add(nums.get(i));
            result.add(new ArrayList<>(temp));
            auxSubsetWithDup(nums, i + 1, temp, result);
            temp.remove(temp.size() - 1);
        }
    }
}
    /*
    public ArrayList<ArrayList<Integer>> subsetsWithDup(List<Integer> A) {
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        Set<ArrayList<Integer>> unique=new HashSet<>();
        ArrayList<Integer> temp=new ArrayList<>();
        return findSubset(result,unique,0,A,temp);
    }

    public ArrayList<ArrayList<Integer>> findSubset(ArrayList<ArrayList<Integer>> result,Set<ArrayList<Integer>> unique,
                           int startIndex,List<Integer> A,ArrayList<Integer> temp){
        if(startIndex>=A.size())return result;
        if(startIndex==A.size()-1){
            ArrayList<Integer> addme=new ArrayList<>();
            addme.addAll(temp);
            if(!unique.contains(addme)){unique.add(addme);result.add(addme);}
            addme=new ArrayList<>();addme.addAll(temp);addme.add(A.get(startIndex));
            if(!unique.contains(addme)){unique.add(addme);result.add(addme);}
            return result;
        }
        for(int y=startIndex;y<A.size();y++){
            findSubset(result,unique,startIndex+1,A,temp);
            temp.add(A.get(startIndex));
            findSubset(result,unique,startIndex+1,A,temp);
            temp.remove(temp.size()-1);
        }
        return result;
    }
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {
        ArrayList<Integer> result=new ArrayList<Integer>();
        TreeMap<Integer,Integer> mapit=new TreeMap<>();
        for(int y:A){
            mapit.put(y,1);
        }
        for(int y:B){
            if(mapit.containsKey(y)){mapit.put(y,mapit.get(y)+1);continue;}
            mapit.put(y,1);
        }
        for(int y:C){
            if(mapit.containsKey(y)){mapit.put(y,mapit.get(y)+1);continue;}
            mapit.put(y,1);
        }
        for(int key:mapit.keySet()){
            if(mapit.get(key)>=2)result.add(key);
        }
        return result;
    }
    public int isValidSudoku(final List<String> A) {
        for (int y = 0; y < A.size(); y++) {
            if (checkColumn(A, y) && checkRow(A, y)) {
                continue;
            } else {
                return 0;
            }
        }
        for (int y = 0; y < A.size() - 2; y += 3) {
            for (int z = 0; z < A.size() - 2; z += 3) {
                if (false == checkGrid(A, y, z))
                    return 0;
            }
        }
        return 1;
    }

    public boolean checkGrid(List<String> A, int row, int col) {
        Set<Character> unique = new HashSet<>();
        for (int y = row; y < row + 3; y++) {
            for (int z = col; z < col + 3; z++) {
                if (A.get(y).charAt(z) == '.') continue;
                if (unique.contains(A.get(y).charAt(z))) return false;
                unique.add(A.get(y).charAt(z));
            }
        }
        return true;
    }

    public boolean checkColumn(List<String> A, int col) {
        Set<Character> unique = new HashSet<>();
        for (String check : A) {
            if (check.charAt(col) == '.') continue;
            if (unique.contains(check.charAt(col))) return false;
            unique.add(check.charAt(col));
        }
        return true;
    }

    public boolean checkRow(List<String> A, int row) {
        Set<Character> unique = new HashSet<>();
        String check = A.get(row);
        int a = 0;
        while (a < check.length()) {
            if (check.charAt(a) == '.') {a++;continue;}
            if (unique.contains(check.charAt(a))) return false;
            unique.add(check.charAt(a));
            a++;
        }
        return true;
    }
}
    /*
    public RandomListNode copyRandomList(RandomListNode head) {
        head=copyDuplicate(head);
        RandomListNode c=head,n=head.next;
        RandomListNode newhead=n;
        while(c!=null || n!=null){
            if(c.random!=null)
                n.random=c.random.next;
            if(c.random==null)
                n.random=null;
            c=c.next;
            if(c==null){n.next=null;break;}
            n.next=n.next.next;
            n=n.next;
        }
        return newhead;
    }
    class RandomListNode {
      int label;
      RandomListNode next, random;
     RandomListNode(int x) { this.label = x; }
    }

    public RandomListNode copyDuplicate(RandomListNode head){
        RandomListNode traverse=head;
        while(traverse!=null){
            RandomListNode addme=new RandomListNode(traverse.label);
            addme.next=traverse.next;
            traverse.next=addme;
            traverse=traverse.next;
            traverse=traverse.next;
        }
        return head;
    }
    public int colorful(int A) {
        Set<Integer> unique=new HashSet<>();
        String value=""+A;
        int product=1;
        for(int i=0;i<value.length();i++){
            product*=Integer.parseInt(""+value.charAt(i));
            if(unique.contains(product))return 0;
            for(int j=i+1;j<value.length();j++){
                if(unique.contains(product))return 0;
                unique.add(product);
                product*=Integer.parseInt(""+value.charAt(j));
            }
            if(unique.contains(product))return 0;
            unique.add(product);
            product=1;
        }
        return 1;
    }
    public int maxPoints(List<Integer> a, List<Integer> b) {
        int count =2,aa=0,bb=0;
        String value="";
        Map<String,Integer> slope=new HashMap<>();
        for(int y=0;y<a.size()-1;y++){
            for(int z=y+1;z<a.size();z++) {
                bb = b.get(y) - b.get(z);
                aa = a.get(y) - a.get(z);
                if (null == slope.get("" + (double) bb / aa))
                    slope.put("" + Math.abs((double) bb / aa), 1);
                else {
                    slope.put("" + Math.abs((double) bb / aa), slope.get("" + (double) bb / aa) + 1);
                    count = count > slope.get("" + (double) bb / aa) ? count : slope.get("" + (double) bb / aa);
                }
            }
            slope.clear();
        }
        return count;
    }
    public String fractionToDecimal(int A, int B) {
        if (A == 0) return "0";
        String append = "";
        if (A < 0 && B > 0 || A > 0 && B < 0) {
            append = "-";
        }
        return append + findRecurring(Math.abs((long) A), Math.abs((long) B));
    }

    public String findRecurring(long nemo, long deno) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, Integer> mapit = new HashMap<>();
        long rem = nemo % deno;
        sb.append(String.valueOf(nemo / deno));
        if (rem != 0) { sb.append(".");}
            while (rem != 0 && !mapit.containsKey((int) (rem))) {
                mapit.put((int) (rem), sb.length());
                rem *= 10;
                while (rem != 0 && rem < deno) {
                    sb.append("0");
                    rem *= 10;
                }
                sb.append(String.valueOf(rem / deno));
                rem = rem % deno;
            }
            if (mapit.containsKey((int) (rem))) {
                sb.insert((int) mapit.get((int) (rem)), '(');
                sb.append(')');
            }
            return sb.toString();
    }
}


    public void solveSudoku(ArrayList<ArrayList<Character>> a) {
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        for(int r=0;r<a.size();r++){
            ArrayList<Integer> addme=new ArrayList<Integer>();
            for(int c=0;c<a.size();c++) {
                if (a.get(r).get(c)=='.'){
                    addme.add(-1);
                    continue;
                }
                addme.add(Integer.parseInt(""+a.get(r).get(c)));
                result.add(addme);
            }
        }
        result=fillme(result,0,0,0,0);
        for(int r=0;r<a.size();r++){
            for(int c=0;c<a.size();c++){
                if(a.get(r).get(c)=='.'){
                    a.get(r).set(c,(""+result.get(r).get(c)).charAt(0));
                }
            }
        }
    }


    public ArrayList<ArrayList<Integer>> fillme(ArrayList<ArrayList<Integer>> result,
                                                int sr,int sc,int er,int ec){
        if(sr>=result.size() || sc>=result.size()
        if(sr==sc && (sr+1)==result.size()){
            if(result.get(sr).get(sc)!=-1)return result;
            for(int value=1;value<=9;value++){
                if(check(result,sr,sc,value)){
                    result.get(sr).set(sc,value);
                    er=sr;ec=sc;
                    return result;
                }
            }
        }
        if(result.get(sr).get(sc)==-1){
            for(int value=1;value<=9;value++){
                if(check(result,sr,sc,value)){
                    result.get(sr).set(sc,value);
                    if((sc)==result.size()){
                        fillme(result,sr+1,0,0,0);
                    }
                    else
                    {
                        fillme(result,sr,sc+1,0,0);
                    }
                    if(er==result.size()-1 && ec==result.size()-1)break;
                    result.get(sr).set(sc,-1);
                }
            }
        }
        else
        {
            if((sc)==result.size()){
                fillme(result,sr+1,0,0,0);
            }
            else
            {
                fillme(result,sr,sc+1,0,0);
            }
        }
        return result;
    }
    public boolean check(ArrayList<ArrayList<Integer>> result,int r,int c,int value){
        for(int i:result.get(r)){
            if(i==value)return false;
        }
        for(ArrayList<Integer> ar:result){
            if(ar.get(c)==value)return false;
        }
        return true;
    }


    public ArrayList<ArrayList<Integer>> combine(int A, int B) {
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        ArrayList<Integer> addme=new ArrayList<>();
        result= fuckOff(result,addme,A,1,B);
        System.out.println(result.size());
        return result;
    }
    public ArrayList<ArrayList<Integer>> fuckOff(ArrayList<ArrayList<Integer>> result,
                                                 ArrayList<Integer> addme,int A,int startIndex,int diff){
        if(startIndex>A || diff<1 )return result;
        if(diff==1){
            ArrayList<Integer> baby=new ArrayList<Integer>();
            baby.addAll(addme);baby.add(A-diff+1);
            System.out.println("ppjpbp");
            printArray(baby);
            result.add(baby);
            return result;
        }
        for(int y=startIndex;y<=A;y++){
            printArray(addme);
            fuckOff(result,addme,A,startIndex+1,diff);
            addme.add(startIndex);
            System.out.println("adding ''''");
            printArray(addme);
            fuckOff(result,addme,A,startIndex+1,diff-1);
            addme.remove(addme.size()-1);
        }
        return result;
    }

    public void printArray(ArrayList<Integer> p){
        for(Integer ii:p){
            System.out.print(ii+" ");
        }
        System.out.println();
    }
    Map<Integer,String> mapit=new HashMap<Integer,String>();
    public ArrayList<String> letterCombinations(String A) {
        mapit.put(0,"0"); mapit.put(1,"1");
        mapit.put(2,"abc");
        mapit.put(3,"def");
        mapit.put(4,"ghi");
        mapit.put(5,"jkl");
        mapit.put(6,"mno");
        mapit.put(7,"pqrs");
        mapit.put(8,"tuvw");
        mapit.put(9,"wxyz");
        ArrayList<String> result=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        return fuck(result,A,0,sb);
    }
    public ArrayList<String> fuck(ArrayList<String> result
            ,String A,int indexPosition,StringBuilder sb){
        if(indexPosition==A.length()-1){
            String value=mapit.get(Integer.parseInt(""+A.charAt(indexPosition)));
            for(int y=0;y<value.length();y++){
                result.add(sb.toString()+(value.charAt(y)));
            }
            return result;
        }
        boolean a[][];

        String value=mapit.get(Integer.parseInt(""+A.charAt(indexPosition)));
        for(int y=0;y<value.length();y++){
            sb.append(value.charAt(y));
            fuck(result,A,indexPosition+1,sb);
            sb.setLength(sb.length()-1);
        }
        return result;
    }












    /*



    public ArrayList<ArrayList<Integer>> combinationSum(List<Integer> a, int b) {
        Collections.sort(a);
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        return fuckOff(result,a,new ArrayList<>(),b,b,0);
    }
    public ArrayList<ArrayList<Integer>> fuckOff(ArrayList<ArrayList<Integer>> result,
                                                 List<Integer> A,ArrayList<Integer> temp,int B,int target,int startIndex){
        if(target<0){
            return result;
        }
        if(target==0){
            ArrayList<Integer> r1=new ArrayList<>();
            r1.addAll(temp);
            result.add(r1);
            return result;
        }
        if(target>0){
            for(int y=startIndex;y<A.size();y++){
                temp.add(A.get(y));
                fuckOff(result,A,temp,B,target-A.get(y),++startIndex);
                temp.remove(temp.size()-1);
            }
        }
        return result;
    }
    public ArrayList<Integer> twoSum(final List<Integer> A, int B) {
        ArrayList<Integer> result=new ArrayList<Integer> ();
        Map<Integer,Integer> mapit=new HashMap<Integer,Integer>();
        for(int y=0;y<A.size();y++){
            if(mapit.get(A.get(y))==null){
                mapit.put(A.get(y),y);
            }
        }

        boolean al=false;
        long x=Integer.MAX_VALUE,y=Integer.MAX_VALUE,lindex,rindex;
        for(int key:mapit.keySet()){
            lindex=mapit.get(key);
            if(mapit.get(B-key)!=null){
                rindex=mapit.get(B-key);
                if(lindex==rindex)continue;
                if((lindex+rindex)<(x+y)){
                    x=lindex;y=rindex;
                    al=true;
                }
            }
        }
        if(al==true){
            result.add((int)x+1);result.add((int)y+1);
        }
        return result;
    }
    public String simplifyPath(String A) {
        int y=0;
        Stack<String> ch=new Stack<>();
        String temp="";
        while(y<A.length()){
            temp="";
            while(y<A.length() && A.charAt(y)!='/'){
                temp+=A.charAt(y);y++;
            }
            while(y<A.length() && A.charAt(y)=='/')y++;
            if(temp.equals("..")){
                ch.pop();continue;
            }
            if(temp.equals(".")){
                continue;
            }
            ch.push(temp);
        }
        StringBuilder sb=new StringBuilder();
        while(!ch.empty()){
            sb.insert(0,"/"+ch.pop());
        }
        return sb.toString();
    }
    public ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {
        ArrayList<Integer> result=new ArrayList<Integer>();
        int max=Integer.MIN_VALUE;
        if(A.size()<=B){
            for(int aa:A){
                if(max<aa)max=aa;
            }
            result.add(max);
            return result;
        }
        Deque<Integer> dq=new ArrayDeque<>();
        StringBuilder sb=new StringBuilder();
        Stack<Integer> ss=new Stack<>();

        for(int y=0;y<A.size();y++){
            while(dq.size()>0 && (y-B)<=0){
                result.add(A.get(dq.peekFirst()));
                dq.removeFirst();
            }
            if(dq.size()!=0 && A.get(dq.peekLast())>A.get(y)){dq.addLast(y);continue;}
            while(dq.size()!=0 && A.get(dq.peekLast())<A.get(y)){
                dq.removeLast();
            }
            dq.addLast(y);
        }
        result.add(A.get(dq.peekFirst()));
        return result;
    }
    public int braces(String A) {
        Stack<Character> ss=new Stack();
        char ll;boolean flag=false;
        for(int y=0;y<A.length();y++){
            ll=A.charAt(y);
            if(ll==')'){
                if(ss.empty())return 1;
                while(ss.peek()!='('){
                    if(ss.peek()=='+' || ss.peek()=='-' || ss.peek()=='*' ||
                            ss.peek()=='/')
                        flag=true;
                    ss.pop();
                }
                if(ss.empty() && flag==false)return 1;
                ss.pop();
                if(ss.empty() && flag==false)return 1;
                flag=false;
                continue;
            }
            if(ll=='+' || ll=='-' || ll=='*' || ll=='/' || ll=='(')
                ss.push(ll);
        }
        while(!ss.empty()){
            if(ss.peek()=='(')return 1;
            ss.pop();
        }
        return 0;
    }
    public int largestRectangleArea(ArrayList<Integer> A) {
        return findanswer(A);
    }
    public int findanswer(ArrayList<Integer> A){
        int ans=0,local=0,left=0,right=0;
        Stack<Integer> ss=new Stack<>();
        int i=0,top=0;
        while(i<A.size()){
            if(ss.empty()){
                ss.push(i);i++;continue;
            }
            top=A.get(ss.peek());
            if(A.get(i)>=top){
                ss.push(i);i++;continue;
            }
            while(!ss.empty() && A.get(i)<top) {
                right=ss.peek();
                ss.pop();
                if(!ss.empty()){
                    left=ss.peek();top=A.get(ss.peek());
                    local = A.get(right) * (i - left-1);
                }
                else {
                    left = 0;
                    local = A.get(right) * (i - left);
                }
                if (local > ans) ans = local;
            }
            ss.push(i);
            i++;
        }
        if(!ss.empty()){
            top=ss.peek();
            while(!ss.empty()){
                right=ss.peek();
                ss.pop();
                if(!ss.empty()){
                    left=ss.peek();top=A.get(ss.peek());
                    local = A.get(right) * (i - left-1);
                }
                else {
                    left=0;
                    local = A.get(right) * (i - left);
                }
                if (local > ans) ans = local;
            }
        }
        return ans;
    }
    public int maxSpecialProduct(ArrayList<Integer> A) {
        long mod=(long)1e9+7;
        long product=0;
        ArrayList<Integer> left=findleft(A);
        ArrayList<Integer> right=findright(A);
        for(int y=0;y<left.size();y++){
            if(product<(left.get(y)*right.get(y))){
                product=(left.get(y)*right.get(y)%mod);
            }
        }
        return (int)(product);

    }
    public ArrayList<Integer> findright(ArrayList<Integer> A){
        ArrayList<Integer> result=new ArrayList<Integer>();
        Stack<Integer> ss=new Stack<>();
        int top;
        for(int y=A.size()-1;y>=0;y--){
            if(ss.empty()){
                result.add(0,0);ss.push(y);continue;
            }
            top=A.get(ss.peek());
            if(top<=A.get(y)){
                while(!ss.empty() && top<=A.get(y)){
                    ss.pop();if(!ss.empty())top=A.get(ss.peek());
                }
                if(ss.empty()){
                    result.add(0,0);ss.push(y);continue;
                }
            }
            if(top>A.get(y)){
                result.add(0,ss.peek());ss.push(y);continue;
            }
        }
        return result;
    }
    public ArrayList<Integer> findleft(ArrayList<Integer> A){
        ArrayList<Integer> result=new ArrayList<Integer>();
        Stack<Integer> ss=new Stack<>();
        int top,y;
        for(int z=0;z<A.size();z++){
            y=A.get(z);
            if(ss.empty()){
                result.add(0);ss.push(z);continue;
            }
            top=A.get(ss.peek());
            if(top<=y){
                while(!ss.empty() && top<=y){
                    ss.pop();
                    if(!ss.empty())top=A.get(ss.peek());
                }
                if(ss.empty()){
                    result.add(0);ss.push(z);continue;
                }
            }
            if(top>y){
                result.add(ss.peek());ss.push(z);continue;
            }
        }
        return result;
    }
    public void printme(ListNode node){
        while(node!=null){
            System.out.print(node.val+"  ");node=node.next;
        }
        System.out.println();
    }

    public ListNode dividemerge(ListNode a){
        System.out.println("divide");
        printme(a);
        if(a==null || a.next==null)return a;
        ListNode middle1=middle(a);
        ListNode middle2=null;
        if(middle1!=null)middle2=middle1.next;
        middle1.next=null;
        return merge(dividemerge(a),dividemerge(middle2));

    }
    public ListNode middle(ListNode a){
        ListNode s=a,f=a;
        while(f!=null && f.next!=null ){
            s=s.next;
            f=f.next.next;
        }
        return s;
    }
    public ListNode merge(ListNode a ,ListNode b){
        System.out.println("merging");
        printme(a);
        System.out.println();printme(b);
        ListNode head=a,p=null;
        if(a==null)return b;
        if(b==null)return a;
        ListNode temp=b.next;
        if(a.val>b.val){p=b;temp=b.next;b.next=a;head=b;b=temp;}
        while(a!=null && b!=null){
            temp=b.next;
            if(a.val>=b.val){
                b.next=p.next;p.next=b;p=b;b=temp;
            }
            while(a!=null && b!=null && a.val<b.val){
                p=a;a=a.next;
            }
        }
        if(a==null && b!=null)p.next=b;
        return head;
    }
    public ListNode reverseBetween(ListNode A, int B, int C) {
        if(A==null || A.next==null)return A;
        ListNode p=null,c=A,n=A.next,s=A;
        int count=1;
        while(n!=null && count<=C){
            if(count<=B){s=p;}
            if(count>B&&count<=C){c.next=p;}
            p=c;c=n;n=n.next;
            count++;
        }
        if(count>B&&count<=C){c.next=p;}
        s.next.next=n;s.next=c;
        return A;
    }
    class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x) { val = x; next = null; }
      }

    public boolean check(String A){
        if(A.length()<=1)return true;
        for(int y=0;y<A.length();y++){
            if(A.charAt(y)!=A.charAt(A.length()-1-y))return false;
        }
        return true;
    }


    public int solve(String A) {
        StringBuilder sb=new StringBuilder();
        for(int y=A.length()-1;y>=0;y--){
            sb.append(A.charAt(y));
        }
        sb.append("&");sb.append(A);
        int array[]=computeLPS(sb.toString());
        return A.length()-array[array.length-1];
    }
    public int[] computeLPS(String a){
        int array[]=new int[a.length()];
        int i=0,j=1;
        while(j!=a.length()){
            if(a.charAt(i)==a.charAt(j)){i++;array[j]=i;continue;}
            if(i==0){array[j]=0;j++;continue;}
            i=array[i-1];
        }
        return array;
    }
*//*

    public int isPalindrome(String A) {
        int s=0,e=A.length()-1;

        while(e>=s&&!(isalph(A.charAt(s)))){
            s++;
        }
        while(e>=s&&!(isalph(A.charAt(e)))){
            e--;
        }
        if(e<=s){
            return 1;
        }
        while(e>=s){
            while(e>=s&&!(isalph(A.charAt(s)))){
                s++;
            }
            while(e>=s&&!(isalph(A.charAt(e)))){
                e--;
            }
            if(Character.toUpperCase((A.charAt(s)))!=Character.toUpperCase(A.charAt(e)))return 0;
            s++;e--;
        }
        return 1;
    }
    public boolean isalph(char a){
        if(Character.toUpperCase(a)>='A' && Character.toUpperCase(a)<='Z' ||
                ( Character.toUpperCase(a)>='0' && Character.toUpperCase(a)<='9'))return true;
        return false;
    }
      public void prefixarray(@NotNull char []ab){
        int ar[]=new int[ab.length];
        int j=0,i=1;
        while(i<ab.length){
            if(ab[i]==ab[j]){ar[i]=j+1;i++;j++;}
            else
            {
               if(j!=0)j=ar[j-1];
               else {ar[i]=0;i++;}
            }
        }
        for(int y:ar){
            System.out.print(y+" ");
        }
      }

    public void testme(){
        ListNode a=new ListNode(1);
        ListNode b=new ListNode(2);
        ListNode c=new ListNode(1);
        a.next=b;b.next=c;
        lPalin(a);
    }


    static ListNode start;
    public int lPalin(ListNode A) {
        ListNode e=A;start=A;
        return traverse(e)==true?1:0;
    }
    public boolean traverse(ListNode end){
        if(end.next==null){
            if(start.val==end.val){start=start.next;return true;}
            return false;
        }
        boolean value=traverse(end.next);
        if(value==true){
            if(start.val==end.val){start=start.next;return true;}
            return false;
        }
        return false;
    }
    public int isPalindrome(int A) {
        if(A<=0)return 0;
        String ab=""+A;
        int s=0,e=ab.length()-1;
        while(e<s){
            if(ab.charAt(s)!=ab.charAt(e))return 0;
            s++;e--;
        }
        return 1;
    }

    public int solveme(int A) {
        long answer=0;
        int array[]=new int[(A/2)+1];
        for(int y=1;y<=A/2;y++){
            int bits=countBit(y);
            array[y]=bits;
            answer=(answer+bits)%1000000007l;
        }
        for(int y=((A/2)+1);y<=A;y++){
            if((y&1)==0){
               answer=(answer+array[y/2])%1000000007l;
            }
            else{
                answer=(answer+array[y/2]+1)%1000000007l;
            }
        }
        return (int)(answer%1000000007l);

    }
    public int countBit(int a){
        int count=0;
        while(a>0){
            a=(a&a-1);
            count++;
        }
        return count;
    }

    public boolean hotel(List<Integer> arrive, List<Integer> depart, int K) {
        Collections.sort(arrive);
        Collections.sort(depart);int high=Integer.MIN_VALUE,loop=0;
        int a=0,b=0;
        while(a!=arrive.size()){
            if(arrive.get(a)>depart.get(b)){
                loop++;a++;
                if(high<loop)high=loop;
            }
            else{
                loop--;b++;
            }
        }
        if(K>=high)return true;
        return false;
    }
    public ArrayList<Integer> searchRange(final List<Integer> A, int B) {
        ArrayList<Integer> result=new ArrayList<Integer>();
        TreeMap<String, Integer> sorted = new TreeMap<>();

        for(String y:sorted.keySet()){
        }
        if(A.get(0)>B || A.get(A.size()-1)<B){result.add(-1);result.add(-1);return result;}
        int s=0,e=A.size()-1,si=0,ei=0,mid=0;
        while(s<=e){
            mid=(s+e)/2;
            if(A.get(mid)<B)e=mid-1;
            if(A.get(mid)>B)s=mid+1;
            if(A.get(mid)==B){si=mid;break;}
        }
        if(A.get(mid)!=B){
            result.add(-1);result.add(-1);return result;
        }
        while(mid-1>=0 && A.get(mid-1)==B)mid--;{ei=si;si=mid;mid=ei;}
        while(mid+1<A.size() && A.get(mid+1)==B)mid++;
        result.add(si);result.add(ei);return result;
    }

    public int solve(ArrayList<Integer> A, int B) {
        Collections.sort(A);int c=0,t=0;

        int s=0,e=A.size()-1,mid=(s+e)/2;
        while(s<e && mid!=s){
            t=total(A,mid);
            if(t<B){e=mid;mid=(s+e)/2;continue;}
            if(t>B){s=mid;mid=(s+e)/2;continue;}
            if(t==B){return mid;}
        }
        if(total(A,e)<B && total(A,s)>=B)return s;
        return e;
    }
    public int total(ArrayList<Integer> A,int height){
        int count=0,value=0;
        for(int y=A.size()-1;y>0;y--){
            value=A.get(y);
            if((value-height)<=0)return count;
            count+=value-height;
        }
        return count;
    }
    public int setBit(long a){
        int count=0;
        while(a>0){
            a=(a&(a-1));
            count++;
        }
        return (int)count;
    }
    public int sqrt(int A) {
        long s=1,e=A,mid=(s+e)/2;
        while(s<e && s+1!=e){
            if(mid*mid>A){e=mid;}
            if(mid*mid<A){s=mid;}
            if(mid*mid==A)return (int)mid;
            mid=(s+e)/2;
        }
        if(e*e<=A)return (int)e;
        return (int)s;
    }
    public int solve(int A) {
        if(A==1)return 1;
        int a=0;
        int ar[]=new int[A/2+1];
        ar[0]=0;ar[1]=1;
        int value=0;
        for(int y=2;y<=A/2;y++){
            if((y&1)==0){value=ar[y/2];ar[y]=value;}
            else{value=ar[y/2]+1;ar[y]=value;}
            a+=value;
        }
        for(int y=(A/2)+1;y<=A;y++){
            if((y&1)==0){value=ar[y/2];}
            else{value=ar[y/2]+1;}
            a+=value;
        }
        return a;
    }

*/
