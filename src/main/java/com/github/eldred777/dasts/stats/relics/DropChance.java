package com.github.eldred777.dasts.stats.relics;

public enum DropChance {
    INTACT(25. + 1. / 3., 11., 2.),
    EXCEPTIONAL(23 + 1. / 3., 13., 4.),
    FLAWLESS(20., 17., 6.),
    RADIANT(16. + 2. / 3., 20, 10);

    public final double common;
    public final double uncommon;
    public final double rare;

    DropChance(double c, double u, double r) {
        this.common = c;
        this.uncommon = u;
        this.rare = r;
    }
}
