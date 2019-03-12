/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prim.minimum.spanning.tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Tushar
 */
public class Prims {
    
    static ArrayList al = new ArrayList();
    static int adjhelper = 0;
    private boolean unsettled[];
    private boolean settled[];
    private int key[];
    private int parent[];
    public static int [][] adj;
    private int ttlVer;
    public static final int INFINITE = 999;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner k = new Scanner(new File("Input"));
        int ttlVer = k.nextInt();
        adj = new int[ttlVer + 1][ttlVer + 1];
        while (k.hasNextLine()) {
            String s = k.nextLine();
            Scanner k2 = new Scanner(s);
            int one = -112;
            int b = -113;
            int weight = -114;
            if(k.hasNextInt()){
                one = k.nextInt();
            }
            while(k.hasNextInt() && adjhelper < 1){
                b = k.nextInt();
                weight = k.nextInt();
                adj[one][b] = weight;
                adj[b][one] = weight;
                adjhelper++;
            }
            adjhelper = 0;
        }
        
        for(int y = 0; y < adj.length; y++){
            for(int x = 0; x < adj.length; x++){
                if(adj[y][x] == 0){
                    adj[y][x] = INFINITE;
                }
            }
        }
        
        for(int y = 0; y < adj.length; y++){
            for(int x = 0; x < adj.length; x++){
                //System.out.print(adj[y][x] + " ");
            }
            //System.out.println();
        }
        
        Prims prims = new Prims(ttlVer);
        prims.primsAlgorithm(adj);
        prims.printMST();
        
    }

    private Prims(int ttlVer){
        this.ttlVer = ttlVer;
        unsettled = new boolean[ttlVer + 1];
        settled = new boolean[ttlVer + 1];
        key = new int[ttlVer + 1];
        parent = new int[ttlVer + 1];
    }

    private void primsAlgorithm(int[][] adj) {
        int evaluationVertex;
        for (int source = 1; source <= ttlVer; source++){
            for (int destination = 1; destination <= ttlVer; destination++){
                this.adj[source][destination] = adj[source][destination];
            }
        }
        for (int index = 1; index <= ttlVer; index++) {
            key[index] = INFINITE;
        }
        key[1] = 0;
        unsettled[1] = true;
        parent[1] = 1;
        while (getUnsettledCount(unsettled) != 0){
            evaluationVertex = getMimumKeyVertexFromUnsettled(unsettled);
            unsettled[evaluationVertex] = false;
            settled[evaluationVertex] = true;
            evaluateNeighbours(evaluationVertex);
        }
    }

    private void printMST() {
        int arr [][] = new int[ttlVer + 1][ttlVer + 1];
        //System.out.println("SOURCE  : DESTINATION = WEIGHT");
        for (int vertex = 2; vertex <= ttlVer; vertex++) {
            //System.out.println(parent[vertex] + "\t:\t" + vertex + "\t=\t" + adj[parent[vertex]][vertex]);
            arr[parent[vertex]][vertex] = adj[parent[vertex]][vertex];
            arr[vertex][parent[vertex]] = adj[parent[vertex]][vertex];
            //al.add(adj[parent[vertex]][vertex]);
        }
        
        for(int y = 0; y < arr.length; y++){
            for(int x = 0; x < arr.length; x++){
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
        
    }
    

    private int getUnsettledCount(boolean[] unsettled) {
        int count = 0;
        for (int index = 0; index < unsettled.length; index++){
            if (unsettled[index]) {
                count++;
            }
        }
        return count;
    }

    private int getMimumKeyVertexFromUnsettled(boolean[] unsettled) {
        int min = Integer.MAX_VALUE;
        int node = 0;
        for (int vertex = 1; vertex <= ttlVer; vertex++) {
            if (unsettled[vertex] == true && key[vertex] < min) {
                node = vertex;
                min = key[vertex];
            }
        }
        return node;
    }

    private void evaluateNeighbours(int evaluationVertex) {
        for (int destinationvertex = 1; destinationvertex <= ttlVer; destinationvertex++){
            if (settled[destinationvertex] == false){
                if (adj[evaluationVertex][destinationvertex] != INFINITE){
                    if (adj[evaluationVertex][destinationvertex] < key[destinationvertex]){
                        key[destinationvertex] = adj[evaluationVertex][destinationvertex];
                        parent[destinationvertex] = evaluationVertex;
                    }
                    unsettled[destinationvertex] = true;
                }
            }
        }
    }
    
}


/*input should be 6 
1 2 5
1 3 3
1 5 7
2 3 11
2 6 6
2 4 18
3 4 2
3 6 12
3 5 3
4 6 21
5 6 10*/
