# Alg4_MST

----
Exploration of the Minimum Spanning Tree algorithms from [Algorithms 4th ed.](https://algs4.cs.princeton.edu/home/) by Robert Sedgewick and Kevin Wayne.

This project builds from first principles using data structures from the Algorithms 4th ed. book. Implemented data structures in use:

* `Bag` - Stores objects similar to a `Stack` but without the ability to remove items. Can return a `java.util.Iterator`.
* `WeightedUnionFind` - Disjoint-set data type with `union()` and `find()` methods.
* `MinPQ` - Minimum value `Priority Queue`.
* `Queue` - Basic `Queue` data type. Can return a `java.util.Iterator`.
* `IndexMinPQ` - Basic indexed mimimum value `Priority Queue`.

Both the `WeightedUnionFind` disjoint-set data type and the `IndexMinPQ` do not have parallels in the Java standard library. 
`Bag` can be implemented with any number of Java collections, `Queue` can be implemented using e.g. `java.util.ArrayDeque`, 
and `MinPQ` can be implemented using `java.util.PriorityQueue`.

This repo has methods to compute the MST of a connected, weighted, undirected graph using Kruskal's algorithm, a lazy 
version of Prim's algorithm, and an eager version of Prim's algorithm. These implementations can also compute a minimum-spanning forest 
of a disconnected graph.

* `KruskalMST` - Calculate the MST using Kruskal's algorithm.
* `LazyPrimMST` - Calculate the MST using a lazy version of Prim's algorithm.
* `EagerPrimMST` - Calculate the MST using an eager version of Prim's algorithm.

The aforementioned data structures are used in these algorithms.
