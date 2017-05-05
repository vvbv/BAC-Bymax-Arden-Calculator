/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bymax.arden.calculator;

import java.util.ArrayList;

/**
 *
 * @author administrator
 */
public class BymaxArdenCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*
        
            S = 0R u 0S u 1T u 0I u ε
            T = 1T u 0R u 1O u ε
            U = 1V u 1K u ε
            V = ε
            W = 1W u ε
        
        */
        
        
        Arden arden = new Arden();
        ArrayList <Character> simbolos = new ArrayList<>();
        simbolos.add('0');
        simbolos.add('1');
        arden.setSimbols(simbolos);
        ArrayList <Character> nodes = new ArrayList<>();
        /*nodes.add('S');
        nodes.add('T');
        nodes.add('U');
        nodes.add('V');
        nodes.add('W');*/
        nodes.add('A');
        nodes.add('B');
        nodes.add('C');
        nodes.add('D');
        nodes.add('E');
        nodes.add('F');
        nodes.add('G');
        nodes.add('H');
        nodes.add('I');
        nodes.add('J');
        nodes.add('K');
        nodes.add('L');
        nodes.add('M');
        nodes.add('N');
        nodes.add('O');
        nodes.add('P');
        nodes.add('Q');
        nodes.add('R');
        nodes.add('S');
        nodes.add('T');
        nodes.add('U');
        nodes.add('V');
        nodes.add('W');
        
        arden.setNodes(nodes);
        ArrayList <String> ecuations = new ArrayList<>();
        /*ecuations.add("S = 0S + 1T + 1U + $");
        ecuations.add("T = 1T + $");
        ecuations.add("U = 1V + W + $");
        ecuations.add("V = $");
        ecuations.add("W = 1W + $");*/
        /*ecuations.add("S = 0S + 1T + $");
        ecuations.add("T = 0T + $");*/
        ecuations.add("A = 0B + 1I");
        ecuations.add("B = 0B + 1C + 0F");
        ecuations.add("C = 1C + 1W + 0D");
        ecuations.add("D = 0D + 0E + $");
        ecuations.add("E = 1P + 1K + 0B + $");
        ecuations.add("F = 0F + 1H + 0G");
        ecuations.add("G = $");
        ecuations.add("H = 1H + $");
        ecuations.add("I = 1I + 1K + 0J");
        ecuations.add("J = 0N");
        ecuations.add("K = 0M + 1U + 1L");
        ecuations.add("L = 1L + 0Q + $");
        ecuations.add("M = 0M + 0U + $");
        ecuations.add("N = 0N + 1O + $");
        ecuations.add("O = 1O + 0R + $");
        ecuations.add("P = $");
        ecuations.add("Q = 0Q + 0K + 0B + $");
        ecuations.add("R = 0B + 0S +  $");
        ecuations.add("S = 0R + 0S + 1T + 0I + $");
        ecuations.add("T = 1T + 0R + 1O + $");
        ecuations.add("U = 1V + 1K + $");
        ecuations.add("V = $");
        ecuations.add("W = 1W + $");
        arden.setEcuations(ecuations);
        //arden.testArden();
        arden.solver();
    }
    
}
