package org.example.sorter;

class ChainSorterBuilder {
    private ChainSorter rootSorter;
    private ChainSorter tailSorter;

    public void nextSorter(ASorter nextSorter) {
        if (rootSorter == null) {
            setRootSorter(nextSorter);
        }
        tailSorter = tailSorter.setNextSorter(nextSorter);
    }

    private void setRootSorter(ASorter rootSorter) {
        this.rootSorter = new ChainSorter(rootSorter);
        tailSorter = this.rootSorter;
    }

    public ChainSorter build() {
        return rootSorter;
    }
}
