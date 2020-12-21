package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.helper;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class JellyfishSizeMap {
    protected final TreeMap<Float, Integer> sizeMap;
    protected final int weightTotal;

    public JellyfishSizeMap(TreeMap<Float, Integer> map) {
        this.sizeMap = map;
        int weightTotal = 0;
        for (int weight : this.sizeMap.values())
            weightTotal += weight;
        this.weightTotal = weightTotal;
    }

    public int size() {
        return this.sizeMap.size();
    }

    public int totalWeight() {
        return this.weightTotal;
    }

    public float randomSize(Random rand) throws RuntimeException {
        int denominator = totalWeight();
        for (Map.Entry<Float, Integer> sizeEntry : this.entrySet()) {
            int weight = sizeEntry.getValue();
            if (rand.nextInt(denominator) < weight) {
                return sizeEntry.getKey();
            }
            denominator = denominator - weight;
        }
       throw new RuntimeException("JellyfishSizeMap in Upgrade Aquatic has broken. This should not be happening!");
    }

    public boolean containsKey(Float key) {
        return this.sizeMap.containsKey(key);
    }

    public boolean containsValue(Integer value) {
        return this.sizeMap.containsValue(value);
    }

    public Integer get(Float key) {
        return this.sizeMap.get(key);
    }

    public Comparator<? super Float> comparator() {
        return this.sizeMap.comparator();
    }

    public Float firstKey() {
        return this.sizeMap.firstKey();
    }

    public Float lastKey() {
        return this.sizeMap.lastKey();
    }

    public Map.Entry<Float, Integer> firstEntry() {
        return this.sizeMap.firstEntry();
    }
    
    public Map.Entry<Float, Integer> lastEntry() {
        return this.sizeMap.lastEntry();
    }
    
    public Map.Entry<Float, Integer> lowerEntry(Float key) {
        return this.sizeMap.lowerEntry(key);
    }
    
    public Float lowerKey(Float key) {
        return this.sizeMap.lowerKey(key);
    }
    
    public Map.Entry<Float, Integer> floorEntry(Float key) {
        return this.sizeMap.floorEntry(key);
    }
    
    public Float floorKey(Float key) {
        return this.sizeMap.floorKey(key);
    }
    
    public Map.Entry<Float, Integer> ceilingEntry(Float key) {
        return this.sizeMap.ceilingEntry(key);
    }
    
    public Float ceilingKey(Float key) {
        return this.sizeMap.ceilingKey(key);
    }

    public Map.Entry<Float, Integer> higherEntry(Float key) {
        return this.sizeMap.higherEntry(key);
    }
    
    public Float higherKey(Float key) {
        return this.sizeMap.higherKey(key);
    }

    public Set<Float> keySet() {
        return this.sizeMap.keySet();
    }
    
    public Collection<Integer> values() {
        return this.sizeMap.values();
    }
    
    public Set<Map.Entry<Float, Integer>> entrySet() {
        return this.sizeMap.entrySet();
    }
}
