package com.practice.GFG;

import java.lang.reflect.Array;
import java.util.*;

public class Recursion {
    public static void main(String[] args) {
        Recursion r = new Recursion();
        int array[]={0,0,5,5,0,0};
        System.out.println(r.findSubarray(array,9));
    }
    static void sortBySetBitCount(Integer arr[], int n)
    {
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return findSetBit(01)-findSetBit(o2);
            }
        });
    }
    static int findSetBit(int no){
        int count=0;
        while(no!=0){
            no=(no&no-1);
            count++;
        }
        return count;
    }
    public static int findSubarray(int[] arr ,int n) {
        // 0 0 5 5 10 0 0
        // 0 0 chan
        int count = 0, sum = 0, temp = 0;
        Map<Integer, Integer> mapSumToCount = new HashMap<>();
        for (int y = 0; y < arr.length; y++) {
            sum += arr[y];
            if(sum==0)count++;
            if (mapSumToCount.get(sum) != null) {
                count += mapSumToCount.get(sum);
            }
            temp = mapSumToCount.getOrDefault(sum, 0);
            mapSumToCount.put(sum, temp + 1);
        }
        return count;
    }
    public String smallestnum(String N)
    {
        Map<Integer,Integer> mapNoToCount=new HashMap<>();
        int y=0,no=0;
        while(y<N.length()){
            no=Integer.parseInt(""+N.charAt(y));
            int value=mapNoToCount.getOrDefault(no,0);
            mapNoToCount.put(no,value+1);
            y++;
        }
        boolean first=true;
        StringBuilder sb=new StringBuilder();
        for(int z=1;z<=9;z++){
           if(mapNoToCount.get(z)!=null){
               int value=mapNoToCount.get(z);
               while(value-->0){
                   sb.append(z);
               }
               if(first==true && mapNoToCount.get(0)!=null){
                   value=mapNoToCount.get(0);
                   while(value-->0){
                       sb.append(0);
                   }
                   first=false;
               }
           }
        }
        return sb.toString();
        //code here
    }
    public int[] meetingPlanner(int[][] slotsA, int[][] slotsB, int dur) {
        List<Integer> result=new ArrayList<Integer>();
        // your code goes here
        int slota=0;
        int slotb=0;
        int startMax=0;
        int endMin=0;
        while(slota<slotsA.length && slotb <slotsB.length){

            if(slotsA[slota][1]<slotsB[slotb][0]){ // if ending time of slota is lesser the starting of
                //slotb
                slota++;
                continue;
            }
            if(slotsA[slota][0]>slotsB[slotb][1]){
                slotb++;
                continue;
            }
            startMax=Math.max(slotsA[slota][0],slotsB[slotb][0]);
            endMin=Math.min(slotsA[slota][1],slotsB[slotb][1]);
            if(startMax+dur<=endMin){
                result.add(startMax);
                result.add(startMax+dur);
            }
            slota++;
            slotb++;
        }
        int array[]=new int[result.size()];
        int a=0;
        for(int aa:result){
            array[a]=aa;
            a++;
        }
        return array;
        //return result.toArray(new int[result.size()]]);
    }
    public int search(int[] nums, int target) {
        int pivot=-1;
        for(int y=0;y<nums.length-1;y++){
            if(nums[y]>nums[y+1]){
                pivot=y;
                break;
            }
        }
        int a=foundMe(nums,0,pivot,target);
        if(a==-1)return foundMe(nums,pivot+1,nums.length,target);
        return a;
    }
    public int foundMe(int [] nums,int start,int end,int target){
        Map<?,?> asasf=new HashMap<>();

        int mid=(end+start)/2;
        while(end>=start){
            mid=(end+start)/2;
            if(nums[mid]==target)return mid;
            if(nums[mid]>target){
                end=mid-1;
                continue;
            }
            start=mid+1;
        }
        return -1;
    }
    public String minWindow(String s, String t) {
        if(t.length()>s.length())return "";
        int []pat=new int[256];
        int []text=new int[256];
        boolean patFound=false;
        for(char ch:t.toCharArray()){
            pat[ch]++;
        }
        int i=0,j=0,count=0,min=Integer.MAX_VALUE,minx=0,miny=0;
        while (j<s.length()){
            text[s.charAt(j)]++;
            if(pat[s.charAt(j)]>0 && pat[s.charAt(j)]>=text[s.charAt(j)]){
                count++;
            }
            while (count==t.length()){
                patFound=true;
                if((j-i+1)<min){
                    min=j-i+1;
                    minx=i;
                    miny=j;
                }
                text[s.charAt(i)]--;
                if(pat[s.charAt(i)]>text[s.charAt(i)])count--;
                i++;
            }
            j++;
        }
        return patFound==true?s.substring(minx,miny+1):"";
    }
    public ArrayList<String> restoreIpAddresses(String A) {
        ArrayList<String> result=new ArrayList<>();
        Collections.sort(result,Collections.reverseOrder());
        result=(findAll(result,A,3,new ArrayList<>()));
        return result;
    }
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
    public ArrayList<String> findAll(ArrayList<String> result,String A,int dot,ArrayList<String> temp){
        //System.out.println(A);
        if(A.length()==0 || ( A.length()>3 && dot==0 ))return result;
        if(temp.size()==3 && dot==0 && Integer.parseInt(A)<=255 && Integer.parseInt(A)>=0){
            if(A.length()>1 && Integer.parseInt(A)==0)return result;
            temp.add(A);
            StringBuilder sb=new StringBuilder();
            for(String ss:temp)sb.append(ss+":");
            sb.setLength(sb.length()-1);
            result.add(sb.toString());
            temp.remove(temp.size()-1);
        }
        if(A.length()>=1  && Integer.parseInt(A.substring(0,1))<=255 && Integer.parseInt(A.substring(0,1))>=0){
            temp.add(A.substring(0,1));
            findAll(result,A.substring(1),dot-1,temp);temp.remove(temp.size()-1);}

        if(A.length()>=2 && Integer.parseInt(A.substring(0,2))<=255 && Integer.parseInt(A.substring(0,2))>0 && A.charAt(0)!='0'){
            temp.add(A.substring(0,2));
            findAll(result,A.substring(2),dot-1,temp);temp.remove(temp.size()-1);}

        if(A.length()>=3 && Integer.parseInt(A.substring(0,3))<=255 && Integer.parseInt(A.substring(0,3))>0 && A.charAt(0)!='0'){
            temp.add(A.substring(0,3));
            findAll(result,A.substring(3),dot-1,temp);temp.remove(temp.size()-1);}

        return result;
    }
    public int wordBreak(String A, ArrayList<String> B) {
        Set<String> unique=new HashSet<>();
        for(String s:B)unique.add(s);
        boolean array[]=new boolean[A.length()+1];
        array[0]=true;
        for(int i=1;i<=A.length();i++){
            for(int j=0;j<i;j++){
                if(array[j] && unique.contains(A.substring(j,i)))
                {array[i]=true;break;}
            }
        }
        return array[A.length()]==true?1:0;
    }
    public void nextPermutation(int[] nums)
    {
        Queue<Integer> q=new PriorityQueue<>();
        String ab="Dgdg";
        int index=0;
        for(int y=nums.length-1;y>0;y--){
            q.add(nums[y]);
            index=y;
            if(nums[y]-1<nums[y])break;
        }
        int number=nums[index-1];
        q.add(nums[index-1]);

    }
    public void rotate(int []nums,int limit){
        int loop=nums.length-1;
        while(loop>limit){
            nums[loop]=nums[loop-1];
            loop--;
        }
        return;
    }
    public void swapWhole(int []nums){
        int f=0,b=nums.length-1;
        while(f<b){
            nums[f]=nums[b]+nums[f];
            nums[b]=nums[f]-nums[b];
            nums[f]=nums[f]-nums[b];
            f++;b--;
        }
        return ;
    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return ints[0]-t1[0];
            }
        });
        ArrayList<int[]> result=new ArrayList<>();
        result.add(intervals[0]);
        int index=0;
        int temp[]=null;
        for(int y=1;y<intervals.length;y++){
            temp=result.get(index);
            if(intervals[y][0]<temp[1]){
                temp[1]=Math.max(intervals[y][1],temp[1]);
            }
            else
                index=y;
        }
        return result.toArray(new int[result.size()][]);

    }
    public void merge(int arr1[], int arr2[], int n, int m) {
        int mod=n+m;
        // code here
        int gap=(n+m)/2;
        int f=0,r=f+gap;
        int temp;
        while(gap>0){
            while(r<(n+m)){
                if(f>=n && r>=n && arr2[f-n]>arr2[r-n]){
                    temp=arr2[f-n];
                    arr2[f-n]=arr2[r-n];
                    arr2[r-n]=temp;
                }
                else if(r>=n && f<n && arr1[f]>arr2[r-n])
                {
                    temp=arr1[f];
                    arr1[f]=arr2[r-n];
                    arr2[r-n]=temp;
                }
                else if(r<n && f<n && arr1[f]>arr1[r]){
                    temp=arr1[f];
                    arr1[f]=arr1[r];
                    arr1[r]=temp;
                }
                r++;
                f++;
            }
            if(gap==1)gap=0;
            gap=gap/2 + gap%2;
            f=0;r=f+gap;
        }
        
    }
    int getMinDiff(int[] arr, int n, int k) {
        // code here
        Arrays.sort(arr);
        for(int y:arr) System.out.print(y+" ");
        System.out.println();
        int min=arr[0]+k;
        int max=arr[n-1]-k;
        int lmin=0,lmax=0;
        if(max<min){
            max=max+min;
            min=max-min;
            max=max-min;
        }
        for(int y=1;y<n-1;y++){
            lmin=arr[y]-k;
            lmax=arr[y]+k;
            if(lmin>=min || lmax<=max)continue;
            System.out.println("min:"+min+" max:"+max+"  lmin:"+lmin+"   lmax:"+lmax);
            if(Math.abs(min-lmin) >= Math.abs(lmax-max)){
                max=lmax;
                continue;
            }
            min=lmin;
        }
        return Math.min(Math.abs(arr[n-1]-(arr[0])),max-min);
    }
    public long divideme(long dividend,
                              long divisor)
    {

// Calculate sign of divisor
// i.e., sign will be negative
// only iff either one of them
// is negative otherwise it
// will be positive
        long sign = ((dividend < 0) ^
                (divisor < 0)) ? -1 : 1;

// remove sign of operands
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

// Initialize the quotient
        long quotient = 0, temp = 0;

// test down from the highest
// bit and accumulate the
// tentative value for
// valid bit
// 1<<31 behaves incorrectly and gives Integer
// Min Value which should not be the case, instead
        // 1L<<31 works correctly.
        for (int i = 31; i >= 0; --i)
        {

            if (temp + (divisor << i) <= dividend)
            {
                System.out.print("added result:"+temp + (divisor << i)+"      dividenet:"+dividend+" whyhell :"+(temp + (divisor << i) <= dividend));
                System.out.println("      temp="+temp+"  divisor:"+divisor+"    :"+dividend);
                temp += divisor << i;
                quotient |= 1L << i;
                System.out.println("quotient:"+quotient+"    temp=:"+temp);
            }
        }

        return (sign * quotient);
    }
    public int divide(int A, int B) {
        int sign=(A<0 ^ B<0)==true?-1:1;
        long a=A,b=B;
        a=Math.abs((long)A);
        b=Math.abs(B);
        long divident=0;
        long temp=0;
        for(int y=31;y>=0;y--){
            if((temp+(b<<y) <=a)){
                temp=temp+(b<<y);
                divident+= 1L<<y;
            }
        }
        divident=sign*divident;
        return divident>Integer.MAX_VALUE?Integer.MAX_VALUE:(int)divident;
    }
    public long reverse(long a) {
        long answer=0;
        int count=0;
        while(a!=0){
            answer=answer<<1;
            answer+=(a&1);
            a=a>>1;
            count++;
        }
        while (count<32){answer=answer<<1;count++;}
        return answer;
    }
    public int nTriang(List<Integer> A) {
        Set<ArrayList<Integer>> unique=new HashSet<>();
        ArrayList<Integer> useme=new ArrayList<>();
        int left,right;
        for(int y=0;y<A.size()-2;y++){
            useme.add(A.get(y));
            left=y+1;right=A.size()-1;
            while(left<A.size()-1){
                useme.add(A.get(left));right=A.size()-1;
                while(left<right){
                    useme.add(A.get(right));
                    if(!unique.contains(useme)){
                        unique.add(new ArrayList<>(useme));
                    }
                    useme.remove(useme.size()-1);
                    right--;
                }
                left++;
                useme.remove(useme.size()-1);
            }
            useme.remove(useme.size()-1);
        }
        return unique.size();
    }
    public ArrayList<ArrayList<Integer>> threeSum(List<Integer> A) {
        ArrayList<ArrayList<Integer>> result =new ArrayList<>();
        Collections.sort(A);
        if(A.size()<3)return result;
        ArrayList<Integer>  useme;
        Set<ArrayList<Integer>> unique=new HashSet<>();
        int a,b,c;
        int left,right;long sum;
        for(int y=0;y<A.size();y++){
            a=A.get(y);
            left=y+1;right=A.size()-1;
            while (left<right){
                sum=(long)a+A.get(left)+A.get(right);
                if(sum==0){
                    useme=new ArrayList<>();
                    useme.add(a);useme.add(A.get(left));useme.add(A.get(right));
                    Collections.sort(useme);
                    if(!unique.contains(useme)){
                        result.add(useme);
                        unique.add(useme);
                    }
                }
                if(sum<=0)left++;
                else right--;
            }
        }
        return result;
    }
    public String fractionToDecimal(int num, int den) {
        if(num==0)return "0";
        char sign=' ';
        if (num < 0 ^ den < 0) sign='-';
        Map<Integer,Integer> remtoIndex=new HashMap<>();
        StringBuilder result=new StringBuilder();
        StringBuilder secondPart=new StringBuilder();
        long index=0,rem=1;
        result.append((""+sign).trim()+Math.abs((long)num/den));
        rem=num%den;long second;
        while (rem!=0) {
            if (remtoIndex.get((int)rem) != null) {
                //if (num < 0 ^ den < 0) result.insert(0, '-');
                secondPart.insert(remtoIndex.get((int)rem), "(");
                secondPart.append(')');
                return result.toString().trim() + "." + secondPart.toString().trim();
            }
            remtoIndex.put((int)rem, (int)index);
            rem = (rem * 10);
            second = Math.abs((long)rem / den);
            rem=rem%den;
            System.out.println("rem=:"+rem);
            secondPart.append(second);
            index++;
        }
        if(secondPart.length()==0)return result.toString();
        return result.toString()+"."+secondPart.toString();
    }
    public void addme(){
        int testme[]={63850,
                27031,
                19781,
                16177,
                82775,
                12934,
                14747,
                14142,
                15632,
                13673,
                31643,
                14406,
                14231,
                14452,
                14546,
                16641,
                16665,
                14792,
                31716,
                58355,
                104414,
                68496
        };
        long sum=0;
        for(int y:testme)sum+=y;
        System.out.println("data="+sum);
    }
}
/*
    public int maxPointss(List<Integer> x, List<Integer> y) {
        if(x.size()<=0)return 0;
        Map<Double,Integer> mapit=new HashMap<>();
        int inf=1,count=0,max=1,same=0,top0,localmax=0;
        double aa,bb,slope=0.0;
        for(int a=0;a<x.size();a++){
            inf=1;same=0;count=0;localmax=0;
            mapit.clear();
            for(int b=a+1;b<x.size();b++){
                aa=(double)y.get(b)-(double)y.get(a);
                bb=(double)x.get(b)-(double)x.get(a);
                if(aa==0 && bb==0){same++;continue;}
                if(bb==0){inf++;continue;}
                slope=(double)aa/(double)bb;
                if(aa==0)slope=0;
                // System.out.println("slope="+slope);
                count=mapit.getOrDefault(slope,1);
                count++;
                localmax=Math.max(localmax,count);
                mapit.put(slope,count);
            }
            max=Math.max(localmax,inf);
            max=Math.max(max,localmax+same+1);
        }

        return max;
    }

}
/*
    public ArrayList<ArrayList<String>> partition(String a) {
        ArrayList<ArrayList<String>> result=new ArrayList<>();
        checkit(result,new ArrayList<>(),0,a);
        return result;
    }
    public void checkit(ArrayList<ArrayList<String>> result,ArrayList<String> temp,int index,String s){
        if(index>s.length())return;
        if(index==s.length()){
            if(temp.size()!=0){
                result.add(new ArrayList<>(temp));
                return;
            }
        }
        String ss="";
        for(int y=index;y<s.length();y++){
            ss+=s.charAt(y);
            if(isPalindrome(ss)){
                temp.add(ss);
                checkit(result,temp,y+1,s);
                temp.remove(temp.size()-1);
            }
        }
    }
    public boolean isPalindrome(String s){
        int i=0,j=s.length()-1;
        while (i<j){
            if(s.charAt(i)!=s.charAt(j))return false;
            i++;j--;
        }
        return true;
    }

}
/*
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<Integer> local=new ArrayList<>();
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        for(int y=1;y<=n;y++){
            local.add(y);
            addme(result,local,y+1,k,n);
            local.clear();
        }
        Collections.sort(result, (integers, t1) -> {
           for(int y=0;y<integers.size();y++){
               if(integers.get(y)>t1.get(y))return -1;
               else return 1;
           }
           return 0;
        });
        return result;
    }
    public ArrayList<ArrayList<Integer>> addme(ArrayList<ArrayList<Integer>> result,ArrayList<Integer> local,int localno,int max,int maxLimit){
        if(local.size()==max){
            result.add(new ArrayList<>(local));
            return result;
        }
        if(localno>maxLimit)return result;
        {
            addme(result,local,localno+1,max,maxLimit);
            local.add(localno);
            addme(result,local,localno+1,max,maxLimit);
            local.remove(local.size()-1);
        }
        return result;
    }
}
*/