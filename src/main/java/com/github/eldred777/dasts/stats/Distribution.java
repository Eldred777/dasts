package com.github.eldred777.dasts.stats;

import java.util.HashMap;
import java.util.Optional;

public class Distribution<K, V> {
    // Models a discrete, finite distribution, or samples of a discrete distribution at certain coordinates.
    protected final HashMap<K, V> distribution;


    public Distribution(HashMap<K, V> dist) {
        this.distribution = dist;
    }

    public Distribution() {
        this.distribution = new HashMap<>();
    }

    public void addProb(K k, V v) {
        this.distribution.put(k, v);
    }

    V get(K k) {
        return this.distribution.get(k);
    }

    /**
     * Safer getter that returns an optional. Prefer this when you do not know already that a value is in the underlying
     * map, and handle the optional as required.
     *
     * @param k
     *         Key value to search for (usually a coordinate)
     *
     * @return An Optional that is empty when the key is not a member of the event space underlying the Distribution,
     * otherwise returns an Optional containing the corresponding probability.
     */
    public Optional<V> getOpt(K k) {
        V v = this.distribution.get(k);
        if (v == null) {
            return Optional.empty();
        } else {
            return Optional.of(v);
        }
    }

}
