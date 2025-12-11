package org.example.listfiller;

public class ListFillerConsole extends ListFiller {
    public ListFillerConsole(){
        this.strategy = new StrategyConsole();
    }
}
