package com.github.eldred777.dasts.stats.relics;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private int numPeople;
    private RewardRarity rarity;

    public Calculator() {
        numPeople = 1;
        rarity = RewardRarity.COMMON;
    }

    public void setRarity(RewardRarity rarity) {
        this.rarity = rarity;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    // TODO Geometric distribution
    private double computePdf(Refinement refinement, int runNumber) {
        double x = 0.;
        switch (refinement) {
            case INTACT -> x = 1;
            case EXCEPTIONAL -> x = 2;
            case FLAWLESS -> x = 3;
            case RADIANT -> x = 4;
        }
        return runNumber * x; // TODO implement
    }

    public List<Double> computeProbabilities(int n) {
        ArrayList<Double> p = new ArrayList<>();
        p.add(computePdf(Refinement.INTACT, n));
        p.add(computePdf(Refinement.EXCEPTIONAL, n));
        p.add(computePdf(Refinement.FLAWLESS, n));
        p.add(computePdf(Refinement.RADIANT, n));
        return p;
    }

    public Run computeRun(int n) {
        return new Run(n, computeProbabilities(n));
    }

    public void updateRun(Run run) {
        int n = run.getRunNum();
        run.update(computeProbabilities(n));
    }
}
