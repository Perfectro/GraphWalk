# GraphWalk

Let’s consider a walk W in graph G, which consists of L vertices W1, W2, …, WL. A string S of L letters 'A' – 'E' is realized by walk W if the sequence of letters written along W is equal to S. Vertices can be visited multiple times while walking along W.

For example, S = 'ABBECCD' is realized by W = (0, 1, 6, 9, 7, 2, 3). Determine whether there is a walk W which realizes a given string S in graph G, and if so then find the lexicographically least such walk. The only line of input contains one string S. If there is no walk W which realizes S, then output -1 otherwise, you should output the least lexicographical walk W which realizes S.

## Other examples:
'ABBECCD' => 0169723
'ABB' => 016
'AABE' => -1

## Graph representation :

Edges = ["A", "B", "C", "D", "E", "A", "B", "C", "D", "E"]

Vertices = [(0, 1), (1, 2), (2, 3), (3, 4), (4, 0), (0, 5), (1, 6), (2, 7), (3, 8), (4, 9), (5, 7), (5, 8), (6, 8), (6, 9), (7,9) ]
