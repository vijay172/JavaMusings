package com.vijay.cake;
//import java.util.Optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vkbalakr on 6/23/17.
 */
public class GraphColoring {

    public static class GraphNode {
        private String label;
        //private Optional<String> color;
        private String color;
        private Set<GraphNode> neighbors;

        public GraphNode(String label) {
            this.label = label;
            this.neighbors = new HashSet<>();
            this.color = null;//Optional.empty()
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Set<GraphNode> getNeighbors() {
            return neighbors;
        }

        public void setNeighbors(Set<GraphNode> neighbors) {
            this.neighbors = neighbors;
        }

        public void addNeighbor(GraphNode neighbor) {
            neighbors.add(neighbor);
        }

        public void removeNeighbor(GraphNode neighbor) {
            neighbors.remove(neighbor);
        }
    }

    public static void colorGraph(List<GraphNode> graph, List<String> colors ) {

        for (GraphNode node: graph) {

            Set<GraphNode> neighbors = node.getNeighbors();
            if (neighbors.contains(node)) {
                throw new IllegalArgumentException(String.format("Legal coloring impossible for node with label:%s", node.getLabel()));
            }

            //get node's neighbors colors to rule them out
            Set<String> neighborsColors = new HashSet<>();
            for (GraphNode neighbor: node.getNeighbors()) {
                    neighborsColors.add(neighbor.getColor());
            }
            //Set<String> legalColors = new HashSet<>();
            for (String color: colors) {
                if (!neighborsColors.contains(color)) {
                    node.setColor(color);
                }
            }
            /*if (legalColors.iterator().hasNext()) {
                node.setColor(legalColors.iterator().next());
            }*/
        }

    }

    public static void main(String[] args) {

        GraphNode a = new GraphNode("a");
        GraphNode b = new GraphNode("b");
        GraphNode c = new GraphNode("c");

        a.addNeighbor(b);
        b.addNeighbor(a);
        b.addNeighbor(c);
        c.addNeighbor(b);

        List<GraphNode> graph = Arrays.asList(a,b,c);
        List<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("green");
        colors.add("yellow");
        colors.add("blue");

        colorGraph(graph, colors);
    }
}
