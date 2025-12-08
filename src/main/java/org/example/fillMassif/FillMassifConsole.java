package org.example.fillMassif;

public class FillMassifConsole extends Massif{
    public FillMassifConsole(){
        this.fillStrategy = new FillConsoleStrategy();
    }
}
