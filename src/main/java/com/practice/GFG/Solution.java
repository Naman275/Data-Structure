package com.practice.GFG;

import java.awt.image.ImageProducer;
import java.util.HashMap;
import java.util.Map;

class Cache {
    int value,key;
    Cache prev,next;
}

public class Solution {
    public static void main(String [] args){
        Solution s=new Solution(7);
        s.set(2,1);s.traverse();
        s.set(1,10);s.traverse();
        s.set(8,13);s.traverse();
        System.out.println(s.get(12));



        s.set(2,8);s.traverse();

        System.out.println(s.get(11));
        System.out.println(s.get(7));
        s.set(14,7);s.traverse();
        s.set(12,9);s.traverse();
        s.set(7,10);s.traverse();



        System.out.println(s.get(11));
        s.set(9,3);s.set(14,5);

        System.out.println("15:"+s.get(15));
        System.out.println("9:"+s.get(9));
        s.set(4,13);
        System.out.println("3:"+s.get(3));
        s.set(13,7);
        System.out.println("2:"+s.get(2));s.set(5,9);
        System.out.println("6:"+s.get(6));
        System.out.println(":13:"+s.get(13));
        s.set(4,5);s.set(3,2);s.set(4,12);
        System.out.println(":13:"+s.get(13));
        System.out.println(":7:"+s.get(7));
        System.out.println();
        s.traverse();

      //  S 4 5 S 3 2 S 4 12 G 13 G 7 S 9 7 G 3 G 6 G 2 S 8 4 S 8 9 S 1 4 S 2 9 S 8 8 G 13 G 3 G 13 G 6 S 3 8 G 14 G 4 S 5 6 S 10 15 G 12 S 13 5 S 10 9 S 3 12 S 14 15 G 4 S 10 5 G 5 G 15 S 7 6 G 1 S 5 12 S 1 6 S 11 8

    }
    public void traverse(){
        Cache var=head;
        while (var!=null){
            System.out.print(" "+var.key+":"+var.value+" ");var=var.next;
        }
        System.out.println();
    }
    Cache head,tail;
    int max;
    Map<Integer,Cache> mapit=new HashMap<>();

    public Solution(int capacity) {
        max=capacity;
    }

    public int get(int key) {
        if(mapit.containsKey(key))
        {
            set(mapit.get(key).key,mapit.get(key).value);
            return mapit.get(key).value;
        }
        return -1;
    }

    public void set(int key, int value) {
        Cache ch=new Cache();ch.key=key;ch.value=value;
        if(mapit.containsKey(key)){
            deleteNode(mapit.get(key));
            insert(ch);return;
        }
        if(mapit.size()<max){
            insert(ch);return;
        }
        popFront();
        insert(ch);
    }
    public void deleteNode(Cache ch){
        if(head==ch){head=head.next;}
        if(tail==ch){tail=ch.prev;}
        if(ch.prev!=null)ch.prev.next=ch.next;
        if(ch.next!=null)ch.next.prev=ch.prev;
        mapit.remove(ch.key);
    }
    public void insert(Cache ch){
        if(head==null){
            head=ch;tail=ch;mapit.put(ch.key,ch);return;
        }
        tail.next=ch;
        ch.prev=tail;tail=ch;
        mapit.put(ch.key,ch);
    }
    public void popFront()
    {
        if(head==null)return;
        if(tail==mapit.get(head.key)){
            tail=tail.next;
        }
        mapit.remove(head.key);
        head=head.next;
    }
}
