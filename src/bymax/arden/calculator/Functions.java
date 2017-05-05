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
public class Functions {
    
    public Ecuation getEcuation (String ecuation, ArrayList<Character> simbols, ArrayList<Character> nodes){
        ecuation = ecuation.replace(" ", "");
        char name = ecuation.toCharArray()[0];
        ArrayList<String> elements_of_ecuation = new ArrayList<>();
        int parenthesis = 0;
        String tmp_string = "";
        char[] char_ecuation = ecuation.toCharArray();
        for (int i = 2; i < char_ecuation.length; i++) {
            
            if(char_ecuation[i] == '('){
                parenthesis++;
                tmp_string += '(';
            }else if(char_ecuation[i] == ')'){
                parenthesis--;
                tmp_string += ')';
            }

            if((simbols.contains(char_ecuation[i]))||(char_ecuation[i] == '*')||(char_ecuation[i] == '$')||(nodes.contains(char_ecuation[i]))){
                tmp_string += char_ecuation[i];
            }else if(char_ecuation[i] == '#'){
                tmp_string += char_ecuation[i];
                elements_of_ecuation.add(tmp_string);
                tmp_string = "";
            }else if((char_ecuation[i] == '+')&&(parenthesis > 0)){
                tmp_string += char_ecuation[i];
            }else if((char_ecuation[i] == '+')&&(parenthesis == 0)&&(tmp_string.length()>0)){
                if((tmp_string.contains("$"))&&(tmp_string.length()!=1)){
                    tmp_string = tmp_string.replace("$", "");
                }
                elements_of_ecuation.add(tmp_string);
                tmp_string = "";
            }
            
        }
        if (!tmp_string.equals("")){
            elements_of_ecuation.add(tmp_string);
        }
        
        Ecuation ecuation_Obj = new Ecuation(name, elements_of_ecuation);
        return ecuation_Obj;
    }
    
    public boolean canApplyArden(Ecuation ecuation){
        for (int i = 0; i < ecuation.getElements().size(); i++) {
            char[] element = ecuation.getElements().get(i).toCharArray();
            for (int j = 0; j < element.length; j++) {
                if(element[j] == ecuation.getName()){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public Ecuation applyArden(Ecuation ecuation, ArrayList<Character> simbols, ArrayList<Character> nodes) {
        if(isRAWEcuation(ecuation, simbols, nodes)){
            return ecuation;
        }
        ArrayList<String> elements_Autorefenced = new ArrayList<>();
        ArrayList<String> elements_Non_Autorefenced = new ArrayList<>();
        for (int i = 0; i < ecuation.getElements().size(); i++) {
            String name = Character.toString(ecuation.getName());
            if(ecuation.getElements().get(i).contains(name)){
                elements_Autorefenced.add(ecuation.getElements().get(i));
            }else{
                elements_Non_Autorefenced.add(ecuation.getElements().get(i));
            }
        }
        
        String new_ecuation = "";
        for (int i = 0; i < elements_Autorefenced.size(); i++) {
            new_ecuation += elements_Autorefenced.get(i);
            if(i != elements_Autorefenced.size()-1){
                new_ecuation += "+";
            }
        }
        
        new_ecuation = "(" +  new_ecuation.replace(String.valueOf(ecuation.getName()), "") + ")*";
        String final_ecuation = "";
        
        if (elements_Non_Autorefenced.size() > 0){
            new_ecuation += "(";
            for (int i = 0; i < elements_Non_Autorefenced.size(); i++) {
                final_ecuation += new_ecuation+elements_Non_Autorefenced.get(i);
                
                if(i != elements_Non_Autorefenced.size()-1){
                    final_ecuation += "+";
                }
                
            }
            final_ecuation += ")";
            System.out.println("FE: " + final_ecuation);
        }else{
            final_ecuation = "#";
        }
        final_ecuation = ecuation.getName() +"="+ final_ecuation;
        final_ecuation = final_ecuation.replace("()", "");
        final_ecuation = final_ecuation.replace("($)", "$");
        
        return this.getEcuation(final_ecuation, simbols, nodes);
    }
    
    public boolean isRAWEcuation(Ecuation ecuation, ArrayList<Character> simbols, ArrayList<Character> nodes ){
        for (int i = 0; i < ecuation.getElements().size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if(ecuation.getElements().get(i).contains(nodes.get(j).toString())){
                    return false;
                }
            }
        }
        return true;
    }
    
    public int numberDependences(Ecuation ecuation, ArrayList<Character> simbols, ArrayList<Character> nodes ){
        
        ArrayList<Character> nodes_into_ecuation = new ArrayList<>();
        for (int i = 0; i < ecuation.getElements().size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if(ecuation.getElements().get(i).contains(nodes.get(j).toString())){
                    if(!nodes_into_ecuation.contains(nodes.get(j))){
                        nodes_into_ecuation.add(nodes.get(j));
                    }
                }
            }
        }
        return nodes_into_ecuation.size();
    }
}
