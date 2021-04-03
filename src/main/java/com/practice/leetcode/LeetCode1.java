package com.practice.leetcode;

import java.util.*;

public class LeetCode1 {
    int minCost=Integer.MAX_VALUE;
    int time=0;

    public static void main(String []args){

    }

    //https://leetcode.com/problems/ones-and-zeroes
    public int findMaxForm(String[] strs, int m, int n) {
        if(m==0 && n==0)return 1;
        Map<String,Integer> count=new HashMap<>();
        return traverse(strs,0,m,n,0,count);
    }

    public int traverse(String []strs,int index,int m,int n,int totalCount,Map<String,Integer> count){
        if(index>=strs.length)return totalCount;
        if(m<0 && n<0)return totalCount;
        String key=":index:"+index+":m:"+m+":n:"+n;
        if(count.get(key)!=null){
            return count.get(key)+totalCount;
        }
        int zeroes=countzeroes(strs[index]);
        int one=strs[index].length()-zeroes;
        int a=0,b=0;
        if(m>=zeroes && n>=one){//include current index
            a=traverse(strs,index+1,m-zeroes,n-one,totalCount+1,count);
        }
        //exclude current index
        b=traverse(strs,index+1,m,n,totalCount,count);
        count.put(key,Math.max(a,b)-totalCount);
        return Math.max(a,b);
    }

    public int countzeroes(String str){
        int count=0;
        for(char c:str.toCharArray())if(c=='0')count++;
        return count;
    }

    //https://leetcode.com/problems/cheapest-flights-within-k-stops
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if(src==dst)return 0;
        if(n==1 && src!=dst)return -1;
        Map<String,Integer> cost=prepCost(flights);
        if(K==0){
            if(cost.get("s:"+src+":d:"+dst)==null)return -1;
            return cost.get("s:"+src+":d:"+dst);
        }
        Map<Integer,List<Integer>> adjList=prepList(flights);
        traverse(adjList,cost,K,dst,src,0);
        return Integer.MAX_VALUE==minCost?-1:minCost;
    }

    public void traverse(Map<Integer,List<Integer>> adjList,Map<String,Integer> cost,int k,int dest,int v,int lcost){
        if(lcost>minCost || (adjList.get(v)==null && v!=dest) || (k==-1 && v!=dest) || k<-1)return;
        if(v==dest){
            minCost=Math.min(minCost,lcost);
            return;
        }
        //iterate all child node
        for(Integer child:adjList.get(v)){
            traverse(adjList,cost,k-1,dest,child,lcost+cost.get("s:"+v+":d:"+child));
        }

    }

    public Map<String,Integer> prepCost(int [][]flights){
        Map<String,Integer> cost=new HashMap<>();
        for(int a[]:flights){
            cost.put(("s:"+a[0]+":d:"+a[1]),a[2]);
        }
        return cost;
    }

    public Map<Integer,List<Integer>> prepList(int[][] flights){
        Map<Integer,List<Integer>> adjList=new HashMap<>();
        for(int []a:flights){
            List<Integer> addme=adjList.getOrDefault(a[0],new ArrayList<>());
            addme.add(a[1]);
            adjList.put(a[0],addme);
        }
        return adjList;
    }

    //https://leetcode.com/problems/critical-connections-in-a-network
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections){
        List<List<Integer>> result=new ArrayList<>();
        int min[]=new int[n];
        int disc[]=new int[n];
        Map<Integer,List<Integer>> adjList=prepareList(connections);
        traverse(adjList,0,0,new HashSet<>(),result,min,disc);
        return result;
    }

    public int traverse(Map<Integer,List<Integer>> adjList,int v,int parent,Set<Integer> visited,List<List<Integer>> result,int min[],int disc[]){
        if(visited.contains(v)){
            return min[v];
        }
        visited.add(v);
        disc[v]=time;min[v]=time;
        time++;
        if(adjList.get(v)==null)return disc[v];
        //iterate all child node
        for(Integer child:adjList.get(v)){
            if(child!=parent){
                int childMin=traverse(adjList,child,v,visited,result,min,disc);
                if(childMin>disc[v]){
                    List<Integer> addme=new ArrayList<>();
                    addme.add(v);addme.add(child);
                    result.add(addme);
                }
                min[v]=Math.min(min[v],childMin);
            }
        }
        return min[v];
    }
    public Map<Integer,List<Integer>> prepareList(List<List<Integer>> connections){
        Map<Integer,List<Integer>> mapit=new HashMap<>();
        for(List<Integer> con:connections){
            List<Integer> addme=mapit.getOrDefault(con.get(0),new ArrayList<>());
            addme.add(con.get(1));
            mapit.put(con.get(0),addme);

            addme=mapit.getOrDefault(con.get(1),new ArrayList<>());
            addme.add(con.get(0));
            mapit.put(con.get(1),addme);

        }
        return mapit;
    }

    //https://leetcode.com/problems/number-of-islands
    public int numIslands(char[][] grid) {
        if(grid.length==0)return 0;
        if(grid.length==1 && grid[0].length==1 && grid[0][0]==1)return 1;
        if(grid.length==1 && grid[0].length==1 && grid[0][0]==0)return 0;
        boolean [][]visited=new boolean[grid.length][grid[0].length];
        int island=0;
        for(int r=0;r<grid.length;r++){
            for(int c=0;c<grid[r].length;c++){
                if(visited[r][c]==false && grid[r][c]=='1'){
                    island++;
                    traverse(r,c,grid,visited);
                }
            }
        }
        return island;
    }
    public void traverse(int r,int c,char grid[][],boolean visited[][]){
        if(r<0 || c<0 || r>=grid.length || c>=grid[0].length || visited[r][c] || grid[r][c]=='0')return;
        visited[r][c]=true;
        traverse(r+1,c,grid,visited);
        traverse(r-1,c,grid,visited);
        traverse(r,c+1,grid,visited);
        traverse(r,c-1,grid,visited);
    }
}
