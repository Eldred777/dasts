package com.github.eldred777.dasts.stats.hexcards;

import com.github.eldred777.dasts.stats.Distribution;

public class HexCards extends Distribution<Hands, Double> {
    public HexCards() {
        super();
    }
    // Implements probability computations for Hex's hexes (D&D)
    //  - Joker wildcards with special effect
    //  - variable length hands
    //  - choose best hand
    // TODO implement
    // TODO compute probabilities of certain (results?), with or without joker wildcards, with variable length of hand
}
