package org.example.FillMassif;

public class FillMassifRandom extends Massif {

    public FillMassifRandom(){
        this.fillStrategy = new FillRandomStrategy();
    }
}
