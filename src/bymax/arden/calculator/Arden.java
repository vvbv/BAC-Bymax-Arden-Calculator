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
public class Arden {
    
    private ArrayList<Character> simbols;
    private ArrayList<Character> nodes;
    private ArrayList<String> ecuations;
    
    private ArrayList<ArrayList<Ecuation>> ecuations_order_by_number_of_dependences;
    
    private final Functions functions = new Functions();

    public Arden(ArrayList<Character> simbols, ArrayList<Character> nodes, ArrayList<String> ecuations) {
        this.simbols = simbols;
        this.nodes = nodes;
        this.ecuations = ecuations;
    }

    Arden() {
       
    }

    public ArrayList<Character> getSimbols() {
        return simbols;
    }

    public void setSimbols(ArrayList<Character> simbols) {
        this.simbols = simbols;
    }

    public ArrayList<Character> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Character> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<String> getEcuations() {
        return ecuations;
    }

    public void setEcuations(ArrayList<String> ecuations) {
        this.ecuations = ecuations;
    }

    public void testArden(){
         
         System.out.println(this.functions.applyArden(this.functions.getEcuation(ecuations.get(1), simbols, nodes), simbols, nodes).getEcuation());
         
    
    };
    
    public String solver(){
        
        ArrayList<Ecuation> raw_ecuations = new ArrayList<>();
        ArrayList<Ecuation> other_ecuations = new ArrayList<>();
        
        for (int i = 0; i < ecuations.size(); i++) {
            Ecuation ecuation = this.functions.getEcuation(this.ecuations.get(i),simbols,nodes);
            switch (this.functions.numberDependences(ecuation, simbols, nodes)) {
                case 0:
                    raw_ecuations.add(ecuation);
                    break;
                case 1:
                    raw_ecuations.add(this.functions.applyArden(ecuation, simbols, nodes));
                    break;
                default:
                    other_ecuations.add(ecuation);
            }
        }
        
        
        ArrayList<Ecuation> ecuations_with_raw = remplaceRAWoverOthersEcuations(raw_ecuations, other_ecuations);
        Ecuation out = new Ecuation();
       
        while (true) {
           
            raw_ecuations =  new ArrayList<>();
            other_ecuations = new ArrayList<>();
            
            for (int i = 0; i < ecuations_with_raw.size(); i++) {
                Ecuation ecuation = this.functions.getEcuation(ecuations_with_raw.get(i).getEcuation(),simbols,nodes);
                switch (this.functions.numberDependences(ecuation, simbols, nodes)) {
                    case 0:
                        raw_ecuations.add(ecuation);
                        break;
                    case 1:
                        if(this.functions.canApplyArden(ecuation)){
                            Ecuation tmp_ecuation = this.functions.applyArden(ecuation, simbols, nodes);
                            if(this.functions.numberDependences(tmp_ecuation, simbols, nodes) == 0){
                                raw_ecuations.add(tmp_ecuation);
                            }else{
                                other_ecuations.add(tmp_ecuation);
                            }
                            
                            
                        }
                    default:
                        other_ecuations.add(ecuation);
                }
            }
            for (int i = 0; i < raw_ecuations.size(); i++) {
                System.out.println(">>> " + raw_ecuations.get(i).getEcuation());
                
            }
            for (int i = 0; i < other_ecuations.size(); i++) {
                System.out.println("<<< " + other_ecuations.get(i).getEcuation());
                
            }
            
            if (other_ecuations.size() == 1){ //TEST
                out = ecuations_with_raw.get(0);
                ecuations_with_raw.clear();
                break;
            }
            
            ecuations_with_raw = remplaceRAWoverOthersEcuations(raw_ecuations, other_ecuations);
            System.out.println("------------------------------\n");
        }
        
        
         out.print();
        
        return "";
    }
    
    private ArrayList<Ecuation> remplaceRAWoverOthersEcuations(ArrayList<Ecuation> raw_ecuations,ArrayList<Ecuation> other_ecuations ){
        ArrayList<Ecuation> news_ecuations = new ArrayList<>();
        for (int i = 0; i < other_ecuations.size(); i++) {
            System.err.println(other_ecuations.get(i).getEcuation());
            String ecuation = other_ecuations.get(i).getEcuationWithOutName();
            for (int j = 0; j < raw_ecuations.size(); j++) {
                String rawEcuation = raw_ecuations.get(j).getEcuationWithOutName();
                ecuation = ecuation.replace(String.valueOf(raw_ecuations.get(j).getName()), "(" + rawEcuation + ")");
                System.out.println(raw_ecuations.get(j).getEcuation());
            }
            news_ecuations.add(this.functions.getEcuation(String.valueOf(other_ecuations.get(i).getName())+"="+ecuation, simbols, nodes));
        }
                
        return news_ecuations;
    }
    
}
