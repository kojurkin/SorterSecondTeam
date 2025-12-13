package org.example.oldlistfiller;

public class FillMassifFromFile extends Massif {

    public FillMassifFromFile(){
        this.fillStrategy = new FillFromFileStrategy();
    }
}
