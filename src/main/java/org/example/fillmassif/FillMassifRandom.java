package org.example.fillmassif;

public class FillMassifRandom extends Massif {

    public FillMassifRandom(){
        this.fillStrategy = new FillRandomStrategy();
    }
}
