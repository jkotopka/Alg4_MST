# Alg4_MST

----
Exploration of the Minimum Spanning Tree algorithms from [Algorithms 4th ed.](https://algs4.cs.princeton.edu/home/) by Robert Sedgewick and Kevin Wayne.

This project builds from first principles using data structures from the Algorithms 4th ed. book. Implemented data structures in use:

* `Bag` - Stores objects similar to a `Stack` but without the ability to remove items. Can return a `java.util.Iterator`.
* `WeightedUnionFind` - Disjoint-set data type with `union()` and `find()` methods.
* `MinPQ` - Minimum value `Priority Queue`.
* `Queue` - Basic `Queue` data type. Can return a `java.util.Iterator`.

Of these, only the `WeightedUnionFind` disjoint-set data type does not have a parallel in the Java standard library. 
`Bag` can be implemented with any number of Java collections, `Queue` can be implemented using e.g. `java.util.ArrayDeque`, 
and `MinPQ` can be implemented using `java.util.PriorityQueue`.