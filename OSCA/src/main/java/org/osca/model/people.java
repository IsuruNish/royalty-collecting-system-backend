package org.osca.model;

public class people {
    private int id;
    private String name;
    private String uni;

    public people(){

    }

    public people(int id, String name, String uni) {
        this.id = id;
        this.name = name;
        this.uni = uni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    @Override
    public String toString() {
        return "people{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uni='" + uni + '\'' +
                '}';
    }
}
