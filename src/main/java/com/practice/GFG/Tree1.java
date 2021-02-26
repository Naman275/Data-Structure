package com.practice.GFG;



import java.util.*;

public class Tree1 {

    class Node
    {
        int data;
        Node left;
        Node right;

        Node(int item)
        {
            data = item;
            left = right = null;
        }
    }
    class Util{
        int min,max,size;
        boolean isBst;
    }
    // Return the size of the largest sub-tree which is also a BST
    int largestBst(Node root)
    {
        // Write your code here
        Util result=traverse(root);
        return result.size;
    }
    public Util traverse(Node root){
        if(root==null)return null;
        Util u=new Util();
        Util left=traverse(root.left);
        Util right=traverse(root.right);
        if(left==null && right==null){
            u.isBst=true;u.max=root.data;u.min=root.data;u.size=1;
            return u;
        }
        if(left !=null && right!=null){
            if(left.isBst && right.isBst && left.max<root.data && root.data < right.min){
                u.isBst=true;u.max=right.max;u.min=left.min;u.size=left.size+right.size+1;
            }
            else{
                u.isBst=false;
                u.max=Math.max(root.data,Math.max(left.max,right.max));
                u.min=Math.min(root.data,Math.min(left.min,right.min));
                u.size=Math.max(left.size,right.size);
                //System.out.println("inside df ="+u.max+". size="+u.size);
            }
            return u;
        }
        if(left==null){
            if(right.isBst && root.data<right.min){
                u.isBst=true;u.size=right.size+1;u.max=right.max;u.min=root.data;
            }
            else
            {
                u.isBst=false;u.size=right.size;u.max=Math.max(root.data,right.max);
                u.min=Math.min(root.data,right.min);
            }
        }
        if(right==null){
            if(left.isBst && root.data>left.max){
                u.isBst=true;u.size=left.size+1;u.max=root.data;u.min=left.min;
            }
            else
            {
                u.isBst=false;u.size=left.size;u.max=Math.max(root.data,left.max);
                u.min=Math.min(root.data,left.min);
            }
        }
        //System.out.println("inside="+u.size);
        return u;
    }


    //https://practice.geeksforgeeks.org/problems/count-bst-nodes-that-lie-in-a-given-range/1
    int getCount(Node root,int l, int h)
    {
        getCountit(root,l,h);
        return count;
        //Your code here
    }
    int count=0;
    void getCountit(Node root,int l, int h)
    {
        if(root==null)return;
        if(root.data>=l && root.data<=h)count++;
        if(root.data>l)getCountit(root.left,l,h);
        if(root.data<h)getCountit(root.right,l,h);
        //Your code here
    }
    //https://practice.geeksforgeeks.org/problems/check-if-tree-is-isomorphic/1
    boolean isIsomorphic(Node root1, Node root2)
    {
        if(root1==null && root2==null) return true;
        if((root1 ==null ^ root2 ==null )|| root1.data!=root2.data)return false;
        // code here.
        return isIsomorphic(root1.left,root2.left)
                || isIsomorphic(root1.right,root2.left);
    }
    //https://practice.geeksforgeeks.org/problems/duplicate-subtree-in-binary-tree/1
    public List<Node> findDuplicateSubtrees(Node root) {
        Map<String,Node> unique=new HashMap<>();
        Set<Node> result=new HashSet<>();
        traverse(root,unique,result);
        return new ArrayList<Node>(result);
    }
    public String traverse(Node root, Map<String,Node> mapit, Set<Node> result){
        if(root==null){
            return "";
        }
        String key="R:"+root.data;
        String left=traverse(root.left,mapit,result);
        String right=traverse(root.right,mapit,result);
        if(left.equals("") || right.equals(""))return key;
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
    public static void main(String [] args){
        Tree1 t=new Tree1();
        Node root=t.buildTree(new int[]{3,1,4,0, 5 ,2},new int[]{0, 1, 3 ,4 ,2 ,5},6);
        System.out.println(root.data);
    }
    //https://practice.geeksforgeeks.org/problems/leaf-at-same-level/1
    int leaveLevel=-1;
    boolean allow=true;
    boolean check(Node root)
    {
        // Your code here
        traverse(root,0);
        return allow;
    }
    public void traverse(Node root,int height){
        if(root==null)return;
        if(root.left==null && root.right==null){
            if(leaveLevel==-1){
                leaveLevel=height;
                return;
            }
            if(leaveLevel!=height)allow=false;return;
        }
        if(allow){
            traverse(root.left,height+1);
        }
        if(allow){
            traverse(root.right,height+1);
        }
    }
    //https://practice.geeksforgeeks.org/problems/sum-tree/1
    boolean result=true;
    boolean isSumTree(Node root)
    {
        if(root==null|| ( root.left==null && root.right==null))return true;
        sum(root);
        return result;
        // Your code here
    }
    int sum(Node root){
        if(root==null)return 0;
        if(root.left==null && root.right==null)return root.data;
        int lvalue=0,rvalue=0;
        if(result){
            lvalue=sum(root.left);
        }
        if(result) rvalue=sum(root.right);
        if(result && root.data != (lvalue+rvalue))result=false;
        return root.data+lvalue+rvalue;
    }
    //https://practice.geeksforgeeks.org/problems/construct-tree-1/1

    public  Node buildTree(int inorder[], int preorder[], int n)
    {
        if(n==0)return null;
        if(n==1)return new Node(inorder[0]);
        Map<Integer,Integer> inorderMap=new HashMap<>();
        for(int y=0;y<inorder.length;y++){
            inorderMap.put(inorder[y],y);
        }
        return buildTree(inorder,preorder,0,inorder.length-1,inorderMap);
    }
    int pointer=0;
    public  Node buildTree(int inorder[],int preorder[],int left,int right,
                                  Map<Integer,Integer> inorderMap){
        if(left>right)return null;
        Node root=new Node(preorder[pointer++]);
        int index=inorderMap.get(root.data);
        if(index>0){
            root.left=buildTree(inorder,preorder,left,index-1,inorderMap);
        }
        if(index<inorder.length-1){
            root.right=buildTree(inorder,preorder,index+1,right,inorderMap);
        }
        return root;
    }

    //https://practice.geeksforgeeks.org/problems/transform-to-sum-tree/1
    public int toSumTree(Node root){
        if(root==null)return 0;
        if(root.left==null && root.right==null){
            int value=root.data;
            root.data=0;
            return value;
        }
        int value=root.data;
        root.data=toSumTree(root.left)+toSumTree(root.right);
        return root.data+value;
    }
    // https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1
    Node bToDLL(Node root)
    {
        Stack<Node> sk=new Stack<>();
        Node leftNode=root;
        while (leftNode!=null){
            sk.add(leftNode);
            leftNode=leftNode.left;
        }
        Node previous=null,head=null;
        while (!sk.isEmpty()){
            Node last=sk.pop();
            if(head==null)head=last;
            last.left=previous;
            if(previous!=null)previous.right=last;
            if(last.right!=null){
                Node temp=last.right;
                sk.push(temp);
                temp=temp.left;
                while (temp!=null){sk.push(temp);temp=temp.left;}
            }
            previous=last;
        }
        return head;
        //  Your code here
    }
    //https://practice.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1

    ArrayList <Integer> printBoundary(Node node)
    {
        ArrayList<Integer> result=new ArrayList<>();
        if(node==null)return result;
        List<Integer> lfnode=new ArrayList<>();
        leafNode(node,lfnode);
        result.add(node.data);
        leftBoundary(node.left,result);
        if(result.size()>1)result.remove(result.size()-1);
        result.addAll(lfnode);
        Stack<Integer> reverse=new Stack<>();
        rightBoundary(node.right,reverse);
        if(!reverse.isEmpty())reverse.pop();
        while(!reverse.isEmpty()){
            result.add(reverse.pop());
        }
        return result;
    }
    public void leftBoundary(Node node,List<Integer> addme){
        if(node==null)return;
        addme.add(node.data);
        if(node.left!=null){
            leftBoundary(node.left,addme);
            return;
        }
        leftBoundary(node.right,addme);
    }
    public void rightBoundary(Node node,Stack<Integer> sk){
        if(node==null)return;
        sk.add(node.data);
        if(node.right!=null){
            rightBoundary(node.right,sk);
            return;
        }
        rightBoundary(node.left,sk);
    }
    public void leafNode(Node node,List<Integer> addme){
        if(node==null)return;
        if(node.left==null && node.right==null){addme.add(node.data);return;}
        leafNode(node.left,addme);leafNode(node.right,addme);
    }

    // https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
    public ArrayList <Integer> bottomView(Node root)
    {
        return (ArrayList<Integer>) verticalBottomTraversal(root);
    }
    public List<Integer> verticalBottomTraversal(Node root) {
        TreeMap<Integer, TreeMap<Integer,List<Integer>>> mapit=new TreeMap<>();
        bottomtraverse(root,0,0,mapit);
        List<Integer> result=new ArrayList<>();
        for(int key:mapit.keySet()){
            List<Integer> addme=new ArrayList<>();
            TreeMap<Integer,List<Integer>> temp=mapit.get(key);
            if(temp.size()==1){
                for(int innerKey:temp.keySet())result.add(temp.get(innerKey).get(0));
            }
            else
            {
                {result.add(temp.get(temp.lastKey()).get(0));}
            }
        }
        return result;
    }
    public void bottomtraverse(Node root, int horizontal, int height, TreeMap<Integer,TreeMap<Integer,List<Integer>>> mapit){
        if(root==null)return;
        TreeMap<Integer, List<Integer>> htemp=mapit.getOrDefault(horizontal,new TreeMap<>());
        List<Integer> addme=new ArrayList<>();
        addme.add(root.data);
        htemp.put(height,addme);
        mapit.put(horizontal,htemp);
        traverse(root.left,horizontal-1,height+1,mapit);
        traverse(root.right,horizontal+1,height+1,mapit);
    }
    // https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1#
    public ArrayList<Integer> topView(Node root)
    {
        return (ArrayList<Integer>) verticalTraversal(root);
    }
    public List<Integer> verticalTraversal(Node root) {
        TreeMap<Integer, TreeMap<Integer,List<Integer>>> mapit=new TreeMap<>();
        traverse(root,0,0,mapit);
        List<Integer> result=new ArrayList<>();
        for(int key:mapit.keySet()){
            List<Integer> addme=new ArrayList<>();
            TreeMap<Integer,List<Integer>> temp=mapit.get(key);
            if(temp.size()==1){
                for(int innerKey:temp.keySet())result.add(temp.get(innerKey).get(0));
            }
            else
            {
                for(int innerKey:temp.keySet()){result.add(temp.get(innerKey).get(0));break;}
            }
        }
        return result;
    }
    public void traverse(Node root, int horizontal, int height, TreeMap<Integer,TreeMap<Integer,List<Integer>>> mapit){
        if(root==null)return;
        TreeMap<Integer, List<Integer>> htemp=mapit.getOrDefault(horizontal,new TreeMap<>());
        List<Integer> addme=htemp.getOrDefault(height,new ArrayList<>());
        addme.add(root.data);
        Collections.sort(addme);
        htemp.put(height,addme);
        mapit.put(horizontal,htemp);
        traverse(root.left,horizontal-1,height+1,mapit);
        traverse(root.right,horizontal+1,height+1,mapit);
    }
}
