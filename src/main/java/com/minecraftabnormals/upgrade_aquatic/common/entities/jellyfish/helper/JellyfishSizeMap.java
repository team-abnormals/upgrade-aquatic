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
        return sizeMap.size();
    }

    public int totalWeight() {
        return this.weightTotal;
    }

    public Float randomSize(Random rand) throws RuntimeException {
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
        return sizeMap.containsKey(key);
    }

    public boolean containsValue(Integer value) {
        return sizeMap.containsValue(value);
    }

    public Integer get(Float key) {
        return sizeMap.get(key);
    }

    public Comparator<? super Float> comparator() {
        return sizeMap.comparator();
    }

    public Float firstKey() {
        return sizeMap.firstKey();
    }

    public Float lastKey() {
        return sizeMap.lastKey();
    }

    public Map.Entry<Float, Integer> firstEntry() {
        return sizeMap.firstEntry();
    }
    
    public Map.Entry<Float, Integer> lastEntry() {
        return sizeMap.lastEntry();
    }
    
    public Map.Entry<Float, Integer> lowerEntry(Float key) {
        return sizeMap.lowerEntry(key);
    }
    
    public Float lowerKey(Float key) {
        return sizeMap.lowerKey(key);
    }
    
    public Map.Entry<Float, Integer> floorEntry(Float key) {
        return sizeMap.floorEntry(key);
    }
    
    public Float floorKey(Float key) {
        return sizeMap.floorKey(key);
    }
    
    public Map.Entry<Float, Integer> ceilingEntry(Float key) {
        return sizeMap.ceilingEntry(key);
    }
    
    public Float ceilingKey(Float key) {
        return sizeMap.ceilingKey(key);
    }

    public Map.Entry<Float, Integer> higherEntry(Float key) {
        return sizeMap.higherEntry(key);
    }
    
    public Float higherKey(Float key) {
        return sizeMap.higherKey(key);
    }

    public Set<Float> keySet() {
        return sizeMap.keySet();
    }
    
    public Collection<Integer> values() {
        return sizeMap.values();
    }
    
    public Set<Map.Entry<Float, Integer>> entrySet() {
        return sizeMap.entrySet();
    }
}
