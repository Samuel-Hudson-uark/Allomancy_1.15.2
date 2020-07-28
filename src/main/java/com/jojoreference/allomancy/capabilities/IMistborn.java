package com.jojoreference.allomancy.capabilities;

public interface IMistborn {
    public float burn(float points, boolean simulate);
    public float gain(float points, boolean simulate);
    public void set(float points);

    public float getResource();
}
