package org.example.FillMassif;

public class FillMassifConsole extends Massif{
    public FillMassifConsole(){
        this.fillStrategy = new FillConsoleStrategy();
    }
}
