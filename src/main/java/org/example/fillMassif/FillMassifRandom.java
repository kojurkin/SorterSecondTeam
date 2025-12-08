package org.example.fillMassif;

public class FillMassifRandom extends Massif {

    public FillMassifRandom(){
        this.fillStrategy = new FillRandomStrategy();
    }
}
