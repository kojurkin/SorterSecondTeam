package org.example.fillmassif;

public class FillMassifConsole extends Massif{
    public FillMassifConsole(){
        this.fillStrategy = new FillConsoleStrategy();
    }
}
