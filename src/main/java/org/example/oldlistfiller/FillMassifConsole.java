package org.example.oldlistfiller;

public class FillMassifConsole extends Massif{
    public FillMassifConsole(){
        this.fillStrategy = new FillConsoleStrategy();
    }
}
