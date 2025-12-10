package org.example.oldlistfiller;

public class FillMassifRandom extends Massif {

    public FillMassifRandom(){
        this.fillStrategy = new FillRandomStrategy();
    }
}
