package org.example.FillMassif;

public class FillMassifFromFile extends Massif {

    public FillMassifFromFile(){
        this.fillStrategy = new FillFromFileStrategy();
    }
}
