package org.kotopka;

public class TestClient {

    private final MST mst;

    public TestClient(MST mst) {
        this.mst = mst;
    }

    public void printMst() {
        System.out.println("MST Vertices: " + mst.vertexCount());
        System.out.println("MST Edges: " + mst.edgeCount());
        System.out.println("MST Total Weight: " + mst.totalWeight());
        System.out.println("MST:");

        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
    }

}
