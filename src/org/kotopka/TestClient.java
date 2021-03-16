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

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: missing commandline argument.");
            System.exit(-1);
        }

        TestClient tck = new TestClient(new KruskalMST(GraphLoader.load(args[0])));
        TestClient tclp = new TestClient(new LazyPrimMST(GraphLoader.load(args[0])));
        TestClient tcep = new TestClient(new EagerPrimMST(GraphLoader.load(args[0])));

        System.out.println("Kruskal:");
        tck.printMst();
        System.out.println();

        System.out.println("Lazy Prim:");
        tclp.printMst();
        System.out.println();

        System.out.println("Eager Prim:");
        tcep.printMst();
    }

}
