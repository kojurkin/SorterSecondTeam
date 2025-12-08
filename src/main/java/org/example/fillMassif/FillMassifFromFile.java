package org.example.fillMassif;

public class FillMassifFromFile extends Massif {

    public FillMassifFromFile(){
        this.fillStrategy = new FillFromFileStrategy();
    }
}
