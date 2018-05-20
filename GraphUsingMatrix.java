
public class GraphUsingMatrix {
	private int numV;// num of vertices in the graph
	private int numE;// num of edges in the graph
	private int[][] adjacencyMatrix;// edges from one vertex to another
	boolean[] visitedV;// keep track of visited vertices

	public GraphUsingMatrix(int n) {
		numV = n;
		numE = 0;
		adjacencyMatrix = new int[n][n];
		visitedV = new boolean[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				adjacencyMatrix[i][j] = 0;// no edges just nodes
			}
			visitedV[i] = false;// clearing the visited nothing has been visited
		}
	}

	public void setEdge(Edge edge) {
		adjacencyMatrix[edge.getV()][edge.getW()] = edge.getWeight();
	}

	public void setEdge(int from, int to, int edgeWeight) {
		adjacencyMatrix[from][to] = edgeWeight;
	}

	public int getEdge(int from, int to) {
		return adjacencyMatrix[from][to];// return the edge that connects 2 vertices
	}

	public void displayMatrix() {
		for (int row = 0; row < numV; row++) {
			System.out.print(row + "\t");
			for (int col = 0; col < numV; col++) {
				System.out.print(adjacencyMatrix[row][col] + "  |  ");
			}
			// if(row < n-1).. put this condition if you don't want the last line
			System.out.println("\n--------------------------------");
		}
		System.out.println("\n\n");
	}

	public int getNextUnvisited(int v, boolean[] visitedVertices) {
		for (int w = 0; w < numV; w++) {
			// edge exists && visitedvertices was set to FALSE in constructor
			if ((adjacencyMatrix[v][w] > 0) && (!visitedVertices[w])) {
				return w;
			}
		}
		return -1;// no next to visit..any outgoing edges have been visited
	}

	// breadth first search
	public void bfs(int v) {
		visitedV[v] = true;// mark vertex as visited
		int w = getNextUnvisited(v, visitedV);
		if (w != -1) {// there is another node to visit
			System.out.print(w + " ");
			visitedV[w] = true; // mark vertex as visited
			bfs(v);
		}
		if (v < numV - 1) {
			bfs(v + 1);
		}
	}

	// depth first search
	public void dfs(int v) {
		if (!visitedV[v]) {
			visitedV[v] = true;// mark as visited
			for (int w = 0; w < numV; w++) {
				if ((adjacencyMatrix[v][w] > 0) && (!visitedV[w])) {
					System.out.print(w + " ");
					dfs(w);// visit the next unvisited vertex
				}
			}
		}
	}

	protected int getMinDist(int dist[], boolean[] visitedSet) {
		int min = Integer.MAX_VALUE;
		int indexOfMin = -1;
		for (int i = 0; i < numV; i++) {
			if ((!visitedSet[i]) && (dist[i]) <= min) {
				min = dist[i];
				indexOfMin = i;
			}
		}
		return indexOfMin;
	}

	// Dijkstra's algorithm min distance from node to node
	public void dijkstra(int v) {
		int dist[] = new int[numV];// store the least
		int spanTree[][] = new int[numV][numV];// matrix for spanning tree
		for (int i = 0; i < numV; i++) {
			dist[i] = Integer.MAX_VALUE;// infinite distance
			visitedV[i] = false;// set all to not visited yet
		}
		dist[v] = 0;// distance from self to self is always ZERO
		for (int count = 0; count < numV - 1; count++) {
			int w = getMinDist(dist, visitedV);// get the minimum distance
			visitedV[w] = true;// mark the minimum as visited
			for (int i = 0; i < numV; i++) {
				if ((!visitedV[i]) && (adjacencyMatrix[w][i] != 0) && (dist[w] != Integer.MAX_VALUE)) {
					if ((dist[w] + adjacencyMatrix[w][i]) < dist[i]) {// total weight < current dist tracked
						dist[i] = dist[w] + adjacencyMatrix[w][i];
					}
				}
			}

		}

	}

}
