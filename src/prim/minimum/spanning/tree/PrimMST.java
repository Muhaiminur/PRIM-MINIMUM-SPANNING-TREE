/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prim.minimum.spanning.tree;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author muhaiminur
 */
public class PrimMST {

    static ArrayList al = new ArrayList();
    boolean unsettled[];
    boolean settled[];
    int[] key;
    int[] parent;
    int ver;
    int INFINITE = 999;
    int[][] a;

    PrimMST() {
        try {
            Scanner abir = new Scanner(new File("Input"));
            ver = abir.nextInt();
            a = new int[ver + 1][ver + 1];
            abir.nextInt();
            while (abir.hasNext()) {
                int m = abir.nextInt();
                int n = abir.nextInt();
                int weight = abir.nextInt();
                add(m, n, weight);
            }

            for (int y = 0; y < a.length; y++) {
                for (int x = 0; x < a.length; x++) {
                    if (a[y][x] == 0) {
                        a[y][x] = INFINITE;
                    }
                }
            }

            unsettled = new boolean[ver + 1];
            settled = new boolean[ver + 1];
            key = new int[ver + 1];
            parent = new int[ver + 1];

        } catch (Exception e) {
        }
    }

    public void PrimMST(int[][] a, int s) {
        int evaluationVertex;
        for (int index = 1; index <= ver; index++) {
            key[index] = INFINITE;
        }
        key[1] = 0;
        unsettled[1] = true;
        parent[1] = 1;
        while (getUnsettledCount(unsettled) != 0) {
            evaluationVertex = getMimumKeyVertexFromUnsettled(unsettled);
            unsettled[evaluationVertex] = false;
            settled[evaluationVertex] = true;
            evaluateNeighbours(evaluationVertex);
        }
    }

    private int getUnsettledCount(boolean[] unsettled) {
        int count = 0;
        for (int index = 0; index < unsettled.length; index++) {
            if (unsettled[index]) {
                count++;
            }
        }
        return count;
    }

    private int getMimumKeyVertexFromUnsettled(boolean[] unsettled) {
        int min = Integer.MAX_VALUE;
        int node = 0;
        for (int vertex = 1; vertex <= ver; vertex++) {
            if (unsettled[vertex] == true && key[vertex] < min) {
                node = vertex;
                min = key[vertex];
            }
        }
        return node;
    }

    private void evaluateNeighbours(int evaluationVertex) {
        for (int destinationvertex = 1; destinationvertex <= ver; destinationvertex++) {
            if (settled[destinationvertex] == false) {
                if (a[evaluationVertex][destinationvertex] != INFINITE) {
                    if (a[evaluationVertex][destinationvertex] < key[destinationvertex]) {
                        key[destinationvertex] = a[evaluationVertex][destinationvertex];
                        parent[destinationvertex] = evaluationVertex;
                    }
                    unsettled[destinationvertex] = true;
                }
            }
        }
    }

    public int[][] getMST() {
        int arr[][] = new int[ver + 1][ver + 1];
        //System.out.println("SOURCE  : DESTINATION = WEIGHT");
        for (int vertex = 2; vertex <= ver; vertex++) {
            //System.out.println(parent[vertex] + "\t:\t" + vertex + "\t=\t" + adj[parent[vertex]][vertex]);
            arr[parent[vertex]][vertex] = a[parent[vertex]][vertex];
            arr[vertex][parent[vertex]] = a[parent[vertex]][vertex];
        }
        return arr;
    }

    public void add(int c, int d, int w) {
        if (c >= 0 && d >= 0) {
            a[c][d] = w;
            a[d][c] = w;
        }
    }

    public void exchange(int[] a, int c, int d) {
        int temp = a[c];
        a[c] = a[d];
        a[d] = temp;
    }

    public void PRINT(int[] a) {
        System.out.println("==============================================");
        for (int c = 0; c < a.length; c++) {
            System.out.print(a[c] + " ");

        }
        System.out.println("");
        System.out.println("==============================================");
    }

    public void printMatrix(int[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr.length; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
    }

}
