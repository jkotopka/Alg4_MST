package org.kotopka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphLoader {

    private static EdgeWeightedGraph ewg = null;

    public static EdgeWeightedGraph load(String filename) {
        try (Scanner reader = new Scanner(new File(filename))) {
            int vertexCount = reader.nextInt();
            reader.nextInt(); // burn this int, not needed...

            ewg = new EdgeWeightedGraph(vertexCount);

            while (reader.hasNext()) {
                ewg.addEdge(new Edge(reader.nextInt(), reader.nextInt(), reader.nextDouble()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ewg;
    }

}
