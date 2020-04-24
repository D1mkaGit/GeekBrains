package lesson_7.DZ;


import java.util.*;

/**
 * Граф
 */
public class Graph {

    private List<Vertex> vertexList; //лист вершин
    private boolean[][] adjArr; //смежная матрица
    private int size; //размер графа

    //конструктор
    public Graph(int vertexCount){
        this.vertexList = new ArrayList<>(vertexCount);
        this.adjArr = new boolean[vertexCount][vertexCount];
    }

    //добавляем вершину
    public void addVertex(String value){
        vertexList.add(new Vertex(value));
        size++;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return getSize() == 0;
    }

    //получить индекс вершины
    private int indexOf(String value){
        for(int i = 0; i < size; i++){
            if(vertexList.get(i).getValue().equals(value)){
                return i;
            }
        }
        return  -1;
    }


    //добавить ребро
    public void addEdge(String v1, String v2){
        int v1Index = indexOf(v1);
        int v2Index = indexOf(v2);

        if((v1Index == -1) || (v2Index == -1)){
            throw new IllegalArgumentException("Invalid value for vertex");
        }

        adjArr[v1Index][v2Index] = true;
        adjArr[v2Index][v1Index] = true;
    }

    //вывод графа на консоль
    public void show(){
        for(int i = 0; i < size; i++){
            System.out.println(vertexList.get(i).getValue());
            for(int j = 0; j < size; j++){
                if(adjArr[i][j]){
                    System.out.println(" -> " + vertexList.get(j).getValue());
                }
            }
            System.out.println();
        }
    }

    //снять посещения со всех вершин
    private void dropVertexState(){
        for (int i = 0; i < size; i++){
            vertexList.get(i).setVisited(false);
        }
    }


    private void visitVertex(Stack<Vertex> stack, Vertex vertex){
        stack.push(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Queue<Vertex> queue, Vertex vertex){
        queue.add(vertex);
        vertex.setVisited(true);
    }

    private Vertex getNearUnvisitedVertex(Vertex vertex){
        int vertexIndex = vertexList.indexOf(vertex);
        for(int i = 0; i < size; i++){
            if(adjArr[vertexIndex][i] && !vertexList.get(i).isVisited()){
                return vertexList.get(i);
            }
        }
        return null;
    }

    //обход в глубину
    public void dfs(String value){
        int startIndex = indexOf(value);

        if(startIndex == -1){
            throw new IllegalArgumentException("Invalid value: " + value);
        }

        Stack<Vertex> stack = new Stack<>();

        Vertex vertex = vertexList.get(startIndex);
        visitVertex(stack, vertex);

        while(!stack.isEmpty()){
            vertex = getNearUnvisitedVertex(stack.peek());
            if(vertex != null){
                visitVertex(stack, vertex);
            }else{
                stack.pop();
            }
        }
        dropVertexState();
    }

    //обход в ширину
    public void bfs(String value){
        int startIndex = indexOf(value);

        if(startIndex == -1){
            throw  new IllegalArgumentException("Invalid value: " + value);
        }

        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);
        visitVertex(queue, vertex);

        while(!queue.isEmpty()){
            vertex = getNearUnvisitedVertex(queue.peek());
            if(vertex != null){
                visitVertex(queue, vertex);
            }else{
                queue.remove();
            }
        }
        dropVertexState();
    }

    //построить путь до вершины
    private Stack<String> buildPath(Vertex vertex){
        Stack<String> stack = new Stack<>();

        Vertex current = vertex;
        while(current != null){
            stack.push(current.getValue());
            current = current.getPreviousVertex();
        }
        return stack;
    }


    //найти короткий путь
    public Stack<String> shortPath(String v1, String v2){
        int v1Index = indexOf(v1);
        int v2Index = indexOf(v2);

        if(v1Index == -1){
            throw new IllegalArgumentException("Invalid vertex1: " + v1);
        }

        if(v2Index == -1){
            throw new IllegalArgumentException("Invalid vertex2: " + v2);
        }

        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(v1Index);
        visitVertex(queue, vertex);
        while(!queue.isEmpty()){
            vertex = getNearUnvisitedVertex(queue.peek());
            if(vertex != null){
                visitVertex(queue, vertex);
                vertex.setPreviousVertex(queue.peek());
                if(vertex.getValue().equals(v2)){
                    return buildPath(vertex);
                }
            }else{
                queue.remove();
            }
        }
        dropVertexState();
        return null;
    }
}
