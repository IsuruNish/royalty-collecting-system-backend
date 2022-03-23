package org.osca.model;

import java.util.List;

public class Songs {

    private List<String> memSingers;
    private List<String> memComposers;
    private List<String> memWritters;
    private List<String> NOmemSingers;
    private List<String> NOmemComposers;
    private List<String> NOmemWritters;
    private List<String> info;

    public List<String> getMemSingers() {
        return memSingers;
    }

    public void setMemSingers(List<String> memSingers) {
        this.memSingers = memSingers;
    }

    public List<String> getMemComposers() {
        return memComposers;
    }

    public void setMemComposers(List<String> memComposers) {
        this.memComposers = memComposers;
    }

    public List<String> getMemWritters() {
        return memWritters;
    }

    public void setMemWritters(List<String> memWritters) {
        this.memWritters = memWritters;
    }

    public List<String> getNOmemSingers() {
        return NOmemSingers;
    }

    public void setNOmemSingers(List<String> NOmemSingers) {
        this.NOmemSingers = NOmemSingers;
    }

    public List<String> getNOmemComposers() {
        return NOmemComposers;
    }

    public void setNOmemComposers(List<String> NOmemComposers) {
        this.NOmemComposers = NOmemComposers;
    }

    public List<String> getNOmemWritters() {
        return NOmemWritters;
    }

    public void setNOmemWritters(List<String> NOmemWritters) {
        this.NOmemWritters = NOmemWritters;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Songs{" +
                "memSingers=" + memSingers +
                ", memComposers=" + memComposers +
                ", memWritters=" + memWritters +
                ", NOmemSingers=" + NOmemSingers +
                ", NOmemComposers=" + NOmemComposers +
                ", NOmemWritters=" + NOmemWritters +
                ", info=" + info +
                '}';
    }
}
