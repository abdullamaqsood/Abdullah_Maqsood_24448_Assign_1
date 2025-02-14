/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds_lab_project;

import java.util.ArrayDeque;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Abdullah Maqsood
 */

// The original 'HashGraph' class violates SRP as it combines multiple
// responsibilities:
// graph structure management, traversal operations, and pathfinding. Mixing
// these concerns
// makes the class complex and difficult to modify without affecting unrelated
// functionalities.

public class HashGraph {
    vertex[] adj_list;
    int count;

    public HashGraph(int s) {
        adj_list = new vertex[s];
        count = 0;
    }

    public void add_vertex(String n) {
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

    public void add_edge(String n1, String n2) {
        int index1 = 0;
        int index2 = 0;
        while (index1 < count && !n1.equals(adj_list[index1].name)) {
            index1++;
        }
        while (index2 < count && !n2.equals(adj_list[index2].name)) {
            index2++;
        }
        if ((index1 < count && index2 < count) && n1.equals(adj_list[index1].name)
                && n2.equals(adj_list[index2].name)) {
            if (adj_list[index1].friend_list.find(adj_list[index2])) {
                System.out.println("This edge already exists");
            } else {
                adj_list[index1].friend_list.insert(adj_list[index2]);
                adj_list[index2].friend_list.insert(adj_list[index1]);
            }
        } else {
            System.out.println("This edge can't be inserted.");
        }
    }

    public void delete_vertex(String n) {
        int index = 0;
        while (index < count && !n.equals(adj_list[index].name)) {
            index++;
        }
        if (index < count && n.equals(adj_list[index].name)) {
            node_list curr = adj_list[index].friend_list.head;
            while (curr != null) {
                delete_edge(n, curr.v.name);
                curr = curr.next;
            }
            adj_list[index] = null;
            shift_left(index);
            adj_list[count - 1] = null;
            count--;
        } else {
            System.out.println("vertex to be deleted not found.");
        }
    }

    public void shift_left(int k) {
        for (int i = k; i < count - 1; i++) {
            adj_list[i] = adj_list[i + 1];
        }
    }

    public void delete_edge(String n1, String n2) {
        int index1 = 0;
        int index2 = 0;
        while (index1 < count && !n1.equals(adj_list[index1].name)) {
            index1++;
        }
        while (index2 < count && !n2.equals(adj_list[index2].name)) {
            index2++;
        }
        if (index1 < count && index2 < count && n1.equals(adj_list[index1].name) && n2.equals(adj_list[index2].name)) {
            adj_list[index1].friend_list.delete(adj_list[index2]);
            adj_list[index2].friend_list.delete(adj_list[index1]);
        } else {
            System.out.println("Edge to be deleted not found.");
        }
    }

    public vertex find_vertex(String n) {
        int index = 0;
        while (index < count && !n.equals(adj_list[index].name)) {
            index++;
        }
        if (index < count && n.equals(adj_list[index].name)) {
            return adj_list[index];
        } else
            return null;
    }

    public vertex find_vertex_friends_list(String n) {
        int index = 0;
        while (index < count && !n.equals(adj_list[index].name)) {
            index++;
        }
        if (index < count && n.equals(adj_list[index].name)) {
            System.out.println(adj_list[index].friend_list);
            return adj_list[index];
        } else
            return null;
    }

    public String toString() {
        String a = "";
        for (int i = 0; i < count; i++) {
            a = a + "Name: " + adj_list[i].name + "\n";
            a = a + "Friends: " + adj_list[i].friend_list + "\n" + "\n";
        }
        return a;
    }

    public String shortestPath(String l1, String l2) {
        Queue<vertex> Q = new ArrayDeque<vertex>();
        LinkedList<vertex> LL = new LinkedList();
        Hashtable<vertex, Boolean> visited = new Hashtable();
        Hashtable<vertex, String> prev = new Hashtable();
        vertex S = find_vertex(l1);
        for (int i = 0; i < adj_list.length; i++) {
            visited.put(adj_list[i], false);
            prev.put(adj_list[i], "");
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
