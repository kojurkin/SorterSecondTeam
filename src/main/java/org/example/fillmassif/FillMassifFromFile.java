package org.example.fillmassif;

public class FillMassifFromFile extends Massif {

    public FillMassifFromFile(){
        this.fillStrategy = new FillFromFileStrategy();
    }
}
