# Solving_Sliding_Puzzles
__Solving Sliding Puzzles Using Path Finding (Java ,BFS Method)__

A two-dimensional character array (char[ ][ ] mapGrid) works best for displaying the puzzle`s layout. The picture is demonstrated 
in the compensatory matrix where circles represent the arrangement of the puzzle. The obstacles are marked with the symbol (0) as 
well as empty spaces (.). The start (S) and end (F) positions are also given. The PathFinder class encapsulates coder files and 
successfully handles the applications for entering files, the status of the game and the necessary processes for the 
path finding logic by itself.

 
The selected technique to find the right solution is a variant of the breath-first search (BFS) algorithm, which will be slightly 
modified to allow for a more realistic movement that the sliding mechanisms of this puzzle require. 

The algorithm is based on a queue used for exploring a list of all moves applicable from starting point to the end in a continuous succession. 

The A* method might work well for this task despite having a quadratic order complexity since it does not only identify the shortest path as it does so for uniform cost.
