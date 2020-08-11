package com.practice.GFG;

import java.math.BigInteger;
import java.util.*;

class dm{
    int oldvalue,newvalue;
}

class Main{
    static int maxlimit=1;
    public int power(String A) {
        if(A.length()<=0 || Integer.parseInt(""+A.charAt(A.length()-1))<=1)return 0;
        while(!A.equals("-1") && (A.charAt(A.length()-1)!='0' && A.charAt(A.length()-1)!='1')){
            A=divide(A);
        }
        if(A.equals("1"))return 1;
        return 0;
    }
    public String divide(String A){
        if(Integer.parseInt(""+A.charAt(A.length()-1))%2!=0)return "-1";
        if(A.length()==0)return "0";
        StringBuilder sb=new StringBuilder();
        int divider=2,temp=0;
        temp=0;
        for(int y=0;y<A.length();y++){
            temp=(Integer.parseInt(""+temp+A.charAt(y)));
            if(divider<=temp){
                sb.append(temp/divider);
                temp=temp%divider;
            }
            else if(y<A.length()-1){
                sb.append("0");
            }
        }
        A=sb.toString();
        System.out.println(A);
        return A;
    }
    public int compareVersion(String A, String B) {
        String a[]=A.split("\\.");
        String b[]=B.split("\\.");
        int result=0;int value1=0,value2=0;
        for(int y=0;y<(a.length>b.length?a.length:b.length);y++){
            if(y>a.length-1){
                result=new BigInteger("0").compareTo(new BigInteger(b[y]));
            }
            else if(y>b.length-1){
                result=new BigInteger(a[y]).compareTo(new BigInteger("0"));
            }
            else{
            result=new BigInteger(a[y]).compareTo(new BigInteger(b[y]));}
            if(result!=0)return result;
        }
        return 0;
    }
    public int atoi(final String A) {
        long bound=(long)Integer.MAX_VALUE+2,num=0;
        boolean neg=false;
        int pointer=0;
        while(A.charAt(pointer)==' ')pointer++;
        if(A.charAt(pointer)=='-'){neg=true;pointer++;}
        if(A.charAt(pointer)=='+')pointer++;
        while(pointer<A.length() && num<bound && A.charAt(pointer)>='0' && A.charAt(pointer)<='9'){
            num=(num*10)+Integer.parseInt(""+A.charAt(pointer++));
        }
        if(neg==true){
            num=num*-1;
            if(Integer.MIN_VALUE>num)return Integer.MIN_VALUE;
        }
        if(num>Integer.MAX_VALUE)return Integer.MAX_VALUE;
        return (int)num;
    }
    public static void main(String []args) {
        System.out.println(new Main().atoi("7 U 0 T7165 0128862 089 39 5"));
    }
    static Map<String,Integer> values=new HashMap<String,Integer>();
    static{
        values.put("I",1);values.put("V",5);values.put("X",10);values.put("L",50);
        values.put("C",100);values.put("D",500);values.put("M",1000);
    }
    public int romanToInt(String A) {
        if(A.length()<=1)return values.get(""+A.charAt(A.length()-1));
        int no=values.get(""+A.charAt(A.length()-1));
        for(int y=A.length()-2;y>=0;y--){
            int c=values.get(""+A.charAt(y));
            int p=values.get(""+A.charAt(y+1));
            if(p>c){
                no-=c;continue;
            }
            if(c>=p){
                no+=c;
            }
        }
        return no;
    }


    public static int strStr(final String A, final String B) {
        int counter=0,index=0;boolean found=false;
        for(int y=0;y<A.length() && found==false;y++){
            counter=0;
            index=y;
            while(y<A.length() && counter<B.length() && B.charAt(counter)==A.charAt(y)){
                counter++;y++;
            }
            if(counter==B.length())found=true;
            y=index;
        }
        if(found==true)return index;
        return -1;
    }
    public static void printDeepNandu(int a){
        int arraySize=a*2-1;String value="nndu";
        for(int y=0;y<arraySize;y++) {
            for (int z = 0; z < arraySize; z++) {
                int no = Math.max(Math.abs(y - arraySize / 2), Math.abs(z - arraySize / 2)) + 1;
                if (no == 1) {
                    value = "DEEP ";
                } else {
                    value = "nndu ";
                }
                System.out.print(value);
            }
            System.out.println();
        }
    }

    public String countAndSay(int A) {
        String result="1";
        if(A<0)return "";
        if(A==1)return result;
        for(int y=2;y<=A;y++){
            result=generateNext(result);
        }
        return result;
    }
    public String generateNext(String s){
        StringBuilder sb=new StringBuilder();
        int count=0;
        for(int y=0;y<s.length();){
            count=0;
            char local=s.charAt(y);
            while( y<s.length() && local==s.charAt(y)){
                count++;y++;
            }
            sb.append(count+""+local);
        }
        return sb.toString();
    }



    public static String solve(String A) {
        if(A.length()<2)return A;
        boolean error=false;int er=0;
        StringBuilder sb=new StringBuilder();
        for(int y=A.length()-1;y>0;y--){
            if(A.charAt(y)>A.charAt(y-1)){
                sb.append(A.charAt(y));
                er=y-1;error=true;
                break;
            }
            sb.append(A.charAt(y));
        }
        if(error==false)return "-1";
        for(int y=0;y<sb.length();y++){
            if(sb.charAt(y)>A.charAt(er)){
                char temp=A.charAt(er);
                sb.insert(0,sb.charAt(y));
                sb.setCharAt(y+1,temp);
                break;
            }
        }
        if(sb.length()==1)sb.append(A.charAt(er));
        return A.substring(0,er)+sb.toString();
    }
    public static int uniquePaths(int A, int B) {
        int count=0;
        if(A<0 || B<0)return 0;
        if(A==1 && B==1)
        {
            return 1;
        }
        count+=uniquePaths(A,B-1);
        count+=uniquePaths(A-1,B);
        return count;
    }

    public String largestNumber(final List<Integer> A) {
            A.sort(new Comparator<Integer>(){
            @Override
            public int compare(Integer integer, Integer t1) {
                String a=""+integer+t1,b=""+t1+integer;
                return b.compareTo(a);
            }
        });
        boolean zero=true;
        StringBuilder sb=new StringBuilder();
        for(int i:A){
            if(i!=0)zero=false;
            sb.append(i);
        }
        if(zero==true)return "0";
        return sb.toString();
    }
    public static int multi(int no,int digit){
        int digits=findDigit(no);
        while(digits!=digit){
            no*=10;
            digits++;
        }
        return no;
    }
    public static int findDigit(int n){
        int digits=0;
        while(n!=0){
            n/=10;
            digits++;
        }
        return digits;
    }
    public static String solve(int A) {
        int arr[]=new int[101];
        arr[0]=1;
        if(A==0)return "0";
        int local=1;
        long fact=1;
        while(local<=A){
            arr=calculate(local,arr,maxlimit);
            local++;
        }
        StringBuilder sb=new StringBuilder();
        for(int y=maxlimit-1;y>=0;y--){
            sb.append(arr[y]);
        }
        return sb.toString();
    }
    public static int[] calculate(int no,int []ar,int maxlimit){
        int power=0,temp=1;
        for(int y=0;y<maxlimit;y++){
            temp=(ar[y]*no)+power;
            ar[y]=temp%10;
            power=temp/10;
        }
        while(power!=0){
            ar[maxlimit]=power%10;
            power/=10;
            maxlimit++;
        }
        return ar;
    }
    public static ArrayList<Integer> maxset(ArrayList<Integer> A) {
        ArrayList<Integer> sum1=new ArrayList<Integer>();
        ArrayList<Integer> sum2=new ArrayList<Integer>();
        long sumf=0,sums=0;
        for(int y=0;y<A.size();y++){
            if(A.get(y)>=0){
                sums+=A.get(y);
                sum2.add(A.get(y));
            }
            else{
                sums=0;sum2=new ArrayList<>();
            }
            if((sums>sumf || (sums==sumf && sum2.size()>sum1.size()) )){

                sum1=sum2;
                sumf=sums;
            }
        }
        return sum1;
    }
    public static List<Integer> spiralOrder(final List<List<Integer>> A) {
        List<List<Integer>> listoflist=new ArrayList<>();
        List<Integer> list1=new ArrayList<>();list1.add(1);//list1.add(2);list1.add(3);
        List<Integer> list2=new ArrayList<>();list2.add(2);//list2.add(5);list2.add(6);
        List<Integer> list3=new ArrayList<>();list3.add(3);//list3.add(8);list3.add(9);
        listoflist.add(list1);listoflist.add(list2);listoflist.add(list3);


        int arr[][]=new int[listoflist.size()][listoflist.get(0).size()];

        for(int y=0;y<listoflist.size();y++){
            for(int z=0;z<listoflist.get(y).size();z++){
                arr[y][z]=listoflist.get(y).get(z);
            }
        }
        for(int y=0;y<arr.length;y++)
            for(int z=0;z<arr[y].length;z++)
                System.out.print(arr[y][z]);

        System.out.println();
        int t=0,b=arr.length-1,l=0,r=arr[0].length-1,d=0;

        while(l<=r && t<=b){
            if(d++==0){//print t row
                for(int y=l;y<=r&&l<=r && t<=b;y++){
                    System.out.print(arr[t][y]);
                }
                t++;
            }
            System.out.println();
            if(d++==1){//print r column
                for(int y=t;y<=b&&l<=r && t<=b;y++){
                    System.out.print(arr[y][r]);
                }
                r--;
            }
            System.out.println();
            if(d++==2){//print b row
                for(int y=r;y>=l&&l<=r && t<=b;y--){
                    System.out.print(arr[b][y]);
                }
                b--;
            }
            System.out.println();
            if(d++==3){//print left row
                for(int y=b;y>=t&&l<=r && t<=b;y--){
                    System.out.print(arr[y][l]);
                }
                l++;
            }
            System.out.println();
            d=d%4;
        }

        //  System.out.println(spiralOrder(listoflist));
        // printArray(swapLeft(arr));
        //rintArray(swapRight(arr));
        //swapIndex(arr,1,3);
        //swapIndex(arr,1,3);
        // swapIndex(arr,1,4);
        return null;
    }


    public static int gcd(int A, int B) {
        if(A==0)return B;
        if(A>B)
            return gcd(A%B,A);
        else
            return gcd(B%A,B);
    }
    public String findDigitsInBinary(int A) {
        int temp=A;
        StringBuilder re=new StringBuilder();
        StringBuilder sb=new StringBuilder();
        while(temp>=A){
            sb.append(temp%2);
            temp/=2;
        }
        for(int y=sb.length()-1;y>=0;y--){
            re.append(sb.charAt(y));
        }
        return re.toString();
    }
    public static ArrayList<ArrayList<Integer>> squareSum(int A) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();

        for (int b = 1; b * b < A; b++) {
            for (int a = b; (a * a < A);a++) {
                if(((b * b) + (a * a) != A))continue;
                ArrayList<Integer> newEntry = new ArrayList<Integer>();
                newEntry.add(a);
                newEntry.add(b);
                ans.add(newEntry);
            }
        }
        return ans;
    }
   public static int [] swapIndex(int arr[] , int start,int end){
       System.out.println("Starting array");printArray(arr);
       for (int i=start;i<end;i++,end--){
           arr[i]=arr[i]+arr[end];
           arr[end]=arr[i]-arr[end];
           arr[i]=arr[i]-arr[end];
       }
       System.out.println("rotated array");
       printArray(arr);
       return arr;
   }
    public static void printArray(int []arr){
        for(int i:arr){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public static int [] swapRight(int []ar){
        int temp=ar[ar.length-1];
        for(int i=(ar.length-2);i>=0;i--){
            ar[i+1]=ar[i];
        }
        ar[0]=temp;
        return ar;
    }
    public static int [] swapLeft(int [] arr){
        printArray(arr);
        int temp=arr[0];
        for (int i=0;i<=(arr.length-2);i++){
            arr[i]=arr[i+1];
        }
        arr[arr.length-1]=temp;
        return arr;
    }
    public static void printRotated(int [] arr,int start){
        int length=arr.length;
        start=(start+length)%length;
        for(int i=0;i<length;i++){
            System.out.print(arr[(start+i)%length]+" ");
        }
        System.out.println();
    }
    /*
     public int atoi(final String A) {
        if(A.length()==0)return 0;
        String ar[]=A.trim().split(" ");
        StringBuilder sb=new StringBuilder();
        char base=ar[0].charAt(0);
        if(base!='+'&& base!='-' && base<'1' && base>'9' )return 0;
        if(base=='-' || base=='+')
        ar[0]=ar[0].replace(base,' ').trim();
        for(char c:ar[0].toCharArray()){
            if(c>='0' && c<='9')sb.append(c);
            else break;
        }
        if(sb.length()==0)return 0;
        if(sb.length()<=9 || sb.length()>10){
            if(base=='-')if(sb.length()>10)return Integer.MIN_VALUE;else return Integer.parseInt("-"+sb.toString());
            if(sb.length()>10)return Integer.MAX_VALUE;
            return Integer.parseInt(sb.toString());
        }
        String toCompare=base=='-'?(""+Integer.MIN_VALUE):(""+Integer.MAX_VALUE);
        for(int y=0;y<10;y++){
            if(toCompare.charAt(y)<sb.charAt(y)){
                return base=='-'?Integer.MIN_VALUE:Integer.MAX_VALUE;
            }
        }
        return Integer.parseInt(sb.toString());
    }
     */
}