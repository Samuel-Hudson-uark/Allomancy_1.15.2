package com.jojoreference.allomancy.capabilities;

public class Mistborn implements IMistborn {
    private float metal = 0;
    private final float metalMax = 100;
    @Override
    public float burn(float points, boolean simulate) {
        float amountToSubtract = Math.min(metal, points);
        if(!simulate) {metal -= amountToSubtract;}
        return amountToSubtract;
    }

    @Override
    public float gain(float points, boolean simulate) {
        float amountToGain = Math.min(points, metalMax - metal);
        if(!simulate) {metal += amountToGain;}
        return amountToGain;
    }

    @Override
    public void set(float points) {
        metal = points;
    }

    @Override
    public float getResource() {
        return metal;
    }
}
