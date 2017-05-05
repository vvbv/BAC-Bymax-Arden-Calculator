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
public class Ecuation {
    private char name;
    private ArrayList<String> elements;

    public Ecuation(char name, ArrayList<String> elements) {
        this.name = name;
        this.elements = elements;
    }

    Ecuation() {
        
    }
    
    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public ArrayList<String> getElements() {
        return elements;
    }

    public void setElements(ArrayList<String> elements) {
        this.elements = elements;
    }
    
    public void print(){

        System.out.println("== Ecuation " + this.name + " ==\n");
        System.out.println("    Name: " + name);
        System.out.println("    Elements: " + elements);
        System.out.println("\n== End ==");
    
    }
    public void printLinearLn(){

        
        String out = "";
        for (int i = 0; i < elements.size(); i++) {
            out += elements.get(i);
            if (i != elements.size()-1) {
                out += "+";
            }
            
        }
        System.out.println(this.name + "=" + out);
    
    }
    
    public String getEcuationWithOutName(){
        String out = "";
            for (int i = 0; i < elements.size(); i++) {
                out += elements.get(i);
                if (i != elements.size()-1) {
                    out += "+";
                }

            }
        return out;
    }
    
    public String getEcuation(){
        String out = "";
        for (int i = 0; i < elements.size(); i++) {
            out += elements.get(i);
            if (i != elements.size()-1) {
                out += "+";
            }
            
        }
        return this.name + "=" + out;
    }
    
}
