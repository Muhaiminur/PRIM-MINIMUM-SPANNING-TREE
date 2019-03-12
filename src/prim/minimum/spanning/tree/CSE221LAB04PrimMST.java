/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prim.minimum.spanning.tree;

/**
 *
 * @author muhaiminur
 */
public class CSE221LAB04PrimMST {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PrimMST p=new PrimMST();
        p.PrimMST(p.a, 0);
        int[][]a=p.getMST();
        p.printMatrix(a);
    }
    
}
