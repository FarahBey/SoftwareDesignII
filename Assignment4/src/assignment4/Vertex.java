package assignment4;

import java.util.Map;
import java.util.*;
public class Vertex<T>{
    private T name;
    private Map<T, Integer> edges;

    public T getName() {
        return name;
    }

    public Set<T> getEdgesKey(){
       return edges.keySet();
    }
    public Collection<Integer> getEdgesValue(){
        return edges.values();
    }

    public boolean containsEdge(T word){
        return edges.containsValue(word);
    }
    public void setName(T name) {
        this.name = name;
    }
    public void addEdge(T hold){
        int temp = edges.getOrDefault(hold, 0) + 1;
        edges.put( hold, temp);
    }
    public Vertex(T name){
        this.name = name;
        this.edges = new HashMap<>();
        }
    public Vertex(){
        this.name = null;
        this.edges = null;
    }
    public boolean isRepeated(T hold){
        return edges.containsValue(hold);
    }
    @Override
    public boolean equals(Object hold){
        if(!(hold instanceof Vertex)){
            return false;
        }
        if(((Vertex)hold).getName().equals(getName())){
            return true;
        }
        else{ return false;}
    }

    @Override
    public String toString(){
        return (getName() + " AdjacenyList: " + edges.toString());
    }
}
