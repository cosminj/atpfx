package com.google.code.atpfx.handler;

import com.google.code.atpfx.data.Pair;
import com.google.code.atpfx.data.Tick;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IDFMemoryBuffer {

    private static IDFMemoryBuffer _instance;

    private Map<Pair, Tick> latestTicks;
    private Map<Pair, List<Tick>> ticks;

    private IDFMemoryBuffer() {
        latestTicks = new HashMap<Pair, Tick>();
        ticks = new HashMap<Pair, List<Tick>>();
    }

    static {
        _instance = new IDFMemoryBuffer();
    }

    public static IDFMemoryBuffer getIDFMemoryBuffer() {
        return _instance;
    }

    public void pushTick(Tick newTick) {
        Pair _pair = newTick.getPair();
        latestTicks.put(_pair, newTick);

        List<Tick> _ticks;
        if(ticks.containsKey(_pair)) {
            _ticks = ticks.get(_pair);
        } else {
            _ticks = new LinkedList<Tick>();
            ticks.put(_pair, _ticks);
        }

        _ticks.add(newTick);
    }

    public Map<Pair, Tick> getLatestTicks() {
        return latestTicks;
    }

    public Map<Pair, List<Tick>> getTicks() {
        return ticks;
    }
}