package com.mycompany.ds_lab_project;

import java.util.ArrayDeque;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

// The refactored version follows SRP by splitting responsibilities into
// separate classes:
// 'Graph' for structure management, 'GraphTraversal' for searching operations,
// and 'PathFinder' for shortest path calculations. This modular approach
// improves maintainability,
// readability, and scalability by ensuring each class has a single,
// well-defined responsibility.

class Graph {
    vertex[] adj_list;
    int count;

    public Graph(int s) {
        adj_list = new vertex[s];
        count = 0;
    }

    public void addVertex(String n) {
        if (count == adj_list.length) {
            System.out.println("The list of vertices is full. Can't add a vertex.");
        } else {
            int index = 0;
            while (index < count && !n.equals(adj_list[index].name)) {
                index++;
            }
            if (index < count && n.equals(adj_list[index].name)) {
                System.out.println("This vertex is already present.");
            } else {
                adj_list[count] = new vertex(n);
                count++;
            }
        }
    }

    public void addEdge(String n1, String n2) {
        int index1 = findIndex(n1);
        int index2 = findIndex(n2);
        if (index1 != -1 && index2 != -1) {
            if (adj_list[index1].friend_list.find(adj_list[index2])) {
                System.out.println("This edge already exists.");
            } else {
                adj_list[index1].friend_list.insert(adj_list[index2]);
                adj_list[index2].friend_list.insert(adj_list[index1]);
            }
        } else {
            System.out.println("This edge can't be inserted.");
        }
    }

    public void deleteVertex(String n) {
        int index = findIndex(n);
        if (index != -1) {
            node_list curr = adj_list[index].friend_list.head;
            while (curr != null) {
                deleteEdge(n, curr.v.name);
                curr = curr.next;
            }
            shiftLeft(index);
            adj_list[count - 1] = null;
            count--;
        } else {
            System.out.println("Vertex to be deleted not found.");
        }
    }

    public void deleteEdge(String n1, String n2) {
        int index1 = findIndex(n1);
        int index2 = findIndex(n2);
        if (index1 != -1 && index2 != -1) {
            adj_list[index1].friend_list.delete(adj_list[index2]);
            adj_list[index2].friend_list.delete(adj_list[index1]);
        } else {
            System.out.println("Edge to be deleted not found.");
        }
    }

    private int findIndex(String n) {
        for (int i = 0; i < count; i++) {
            if (n.equals(adj_list[i].name)) {
                return i;
            }
        }
        return -1;
    }

    private void shiftLeft(int k) {
        for (int i = k; i < count - 1; i++) {
            adj_list[i] = adj_list[i + 1];
        }
    }

    public vertex findVertex(String n) {
        int index = findIndex(n);
        return (index != -1) ? adj_list[index] : null;
    }
}

class GraphTraversal {
    Graph graph;

    public GraphTraversal(Graph graph) {
        this.graph = graph;
    }

    public vertex findVertexFriendsList(String n) {
        vertex v = graph.findVertex(n);
        if (v != null) {
            System.out.println(v.friend_list);
        }
        return v;
    }

    public String toString() {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < graph.count; i++) {
            a.append("Name: ").append(graph.adj_list[i].name).append("\n");
            a.append("Friends: ").append(graph.adj_list[i].friend_list).append("\n\n");
        }
        return a.toString();
    }
}

class PathFinder {
    Graph graph;

    public PathFinder(Graph graph) {
        this.graph = graph;
    }

    public String shortestPath(String l1, String l2) {
        Queue<vertex> Q = new ArrayDeque<>();
        LinkedList<vertex> LL = new LinkedList<>();
        Hashtable<vertex, Boolean> visited = new Hashtable<>();
        Hashtable<vertex, String> prev = new Hashtable<>();

        vertex S = graph.findVertex(l1);
        if (S == null)
            return "Start vertex not found.";

        for (int i = 0; i < graph.adj_list.length; i++) {
            visited.put(graph.adj_list[i], false);
            prev.put(graph.adj_list[i], "");
        }

        Q.add(S);
        while (!Q.isEmpty()) {
            vertex temp = Q.remove();
            if (!visited.get(temp)) {
                System.out.print(temp.name + " ");
            }
            visited.put(temp, true);
            LL.add(temp.friend_list.head.v);

            vertex h;
            node_list t = temp.friend_list.head;
            while (!LL.isEmpty()) {
                h = LL.getFirst();
                if (!visited.get(h)) {
                    Q.add(h);
                    visited.put(h, true);
                    prev.put(h, temp.name);
                    System.out.print(h.name + " ");
                }
                if (t.next != null) {
                    t = t.next;
                    LL.add(t.v);
                }
                LL.remove(h);
            }
        }
        vertex D = find_vertex(l2);
        System.out.println();
        String answer = "";
        String t1 = D.name;
        answer = t1 + " " + answer;
        while (!t1.equals(S.name)) {
            t1 = prev.get(D);
            answer = t1 + " " + answer;
            D = find_vertex(t1);
        }
        return answer;
    }
}
