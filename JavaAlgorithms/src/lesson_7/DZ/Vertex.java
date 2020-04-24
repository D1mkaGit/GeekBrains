package lesson_7.DZ;


import java.util.Objects;

/**
 * Класс вершина графа
 */
public class Vertex {

    private String value; //значение
    private boolean visited; //посещена или нет

    private Vertex previousVertex; //предыдущая вершина

    public Vertex(String _value){
        this.value = _value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if((obj == null) || (getClass() != obj.getClass())){
            return false;
        }

        Vertex vertex = (Vertex) obj;
        return Objects.equals(value,vertex.value);

    }

    public boolean isVisited(){
        return visited;
    }

    public void setVisited(boolean _visited){
        this.visited = _visited;
    }

    public Vertex getPreviousVertex(){
        return previousVertex;
    }

    public void setPreviousVertex(Vertex _previousVertex){
        this.previousVertex = _previousVertex;
    }
}