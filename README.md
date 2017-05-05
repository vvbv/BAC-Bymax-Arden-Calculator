# BAC-Bymax-Arden-Calculator 
[![(simple build status)](https://img.shields.io/teamcity/http/teamcity.jetbrains.com/s/bt345.svg)]()

Example:

```JAVA
        /*$(epsilon) + #(void)*/

        Arden arden = new Arden();
        ArrayList <Character> simbolos = new ArrayList<>();
        simbolos.add('0');
        simbolos.add('1');
        arden.setSimbols(simbolos);
        ArrayList <Character> nodes = new ArrayList<>();
        nodes.add('S');
        nodes.add('T');
        arden.setNodes(nodes);
        
        ArrayList <String> ecuations = new ArrayList<>();
        ecuations.add("S = 0S + 1T + 1U + $");
        ecuations.add("T = 1T + $");
        arden.setEcuations(ecuations);
        
        arden.solver();
```
