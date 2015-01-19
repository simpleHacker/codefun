package algorithm.graph;

import interview.Today.Vex;

import java.util.*;

public class GraphOp {
// for this function also need to record the path to avoid cycle back	
	public boolean findPath(int[][] G, int i, int j){
		if(G[i][j] != 0) return true;
		for(int k = 0;k<G.length;++k){ // need to avoid cycle back
			if(!checkVisited(k) && G[i][k] != 0)
				if(findPath(G,k,j))
					return true;
		}
		return false;
	}
	public boolean checkConnect(int[][] G, int i, int j){
		return findPath(G,i,j) || findPath(G,j,i);
	}
	
/**	algorithm BFS
	def checkConnect(Graph g, Node p, Node q):
		initStack(s)
		for node in g.nodes:
			node.state = unvisited
		p.state = visted
		s.push(p)
		while !s.isEmpty():
			u = s.pop()
			for node in u.adjacent:
				if node.state == unvisited:
					if node == q:
						return true
					else
						node.state = visted
						s.push(node)
				
		return false
	*/
// --------------- graph structure -----------------------	
	public static class Graph{
		int vexnum;
		VexNode[] vertices;
	}
	
	public static class VexNode{
		int data;
		ArcNode firstArc;
		int index;
		
		VexNode[] getAllNeighbors(Graph g){
			ArcNode arc = this.firstArc;
			List<VexNode> arcList = new ArrayList<VexNode>();
			
			while(arc != null){
				arcList.add(g.vertices[arc.vexID]);
				arc = arc.nextArc;
			}
			return arcList.toArray(new VexNode[0]);
		}
	}
	
	public static class ArcNode{
		int weight;
		ArcNode nextArc;
		int vexID;
	}
// ---------------------------------------------	
	
// DFS the graph  --- better to use a explicit stack, not a implicit stack
	public void dfsGraph(Graph g){
		boolean visited[g.vexnum] = new boolean[g.vexnum];
		for(int i=0; i<g.vexnum; ++i){
			visited[i] = false;
		}
		for(int i=0;i<g.vexnum;++i){
			if(!visited[i])
				dtravese(g, i, visited);
		}
	}
	public void dtraverse(Graph g, int i,boolean[] visited){
		visited[i] = true;
		for(int v : g.vertex[i].getAdjVex()){
			if(!visited[v]) dtraverse(g,v,visited);
		}
	}
	
// DFS with stack allocated on heap
	public void dfsGraph_Stack(Graph g){
		boolean[] visited = new boolean[g.vexnum];
		Stack<VexNode> s = new Stack<VexNode>();
		
		for(int i=0; i<g.vexnum; ++i){
			visited[i] = false;
		}

		for(int i=0;i<g.vexnum;++i){
			if(!visited[i]){
				s.push(g.vertices[i]);
			}
			
			while(!s.isEmpty()){
				VexNode v = s.pop();
				visited[v.index] = true;
				// can do something here to compute the node
				VexNode[] neis = v.getAllNeighbors(g);
				for(VexNode vnode : neis){
					if(!visited[vnode.index])
						s.push(vnode);
				}
			}
		}
	}
	
// BFS the graph
	public void bfsGraph(Graph g){
		boolean[] visited = new boolean[g.vexnum];
		for(int i=0; i<g.vexnum; ++i){
			visited[i] = false;
		}
		for(int i=0;i<g.vexnum;++i){
			if(!visited[i])
				btraverse(g, i, visited);
		}
	}
	public void btraverse(Graph g, int i, boolean[] visited){
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(i);
		visited[i] = true;
		while(!q.isEmpty()){
			int v = q.poll();
			ArcNode arc = g.vertices[v].firstArc;
			while(arc != null){
				if(!visited[arc.vexID]){
					visited[arc.vexID] = true;
					q.offer(arc.vexID);
					arc = arc.nextArc;
				}
			}
		}
	}
	
	/**
	 * find the shortest path between two nodes with weight 1 for each edge
	 * 
	 * this is also the way to find the seperate degree of the graph bwteen two nodes
	 * 
	 * @param n1
	 * @param n2
	 * @param size
	 * @return
	 */
	public int findSep(Vex n1, Vex n2, int size){
		Queue<Vex> q1 = new LinkedList<Vex>();
		Queue<Vex> q2 = new LinkedList<Vex>();
		
		boolean[] visited1 = new boolean[size];
		boolean[] visited2 = new boolean[size];
		
		Vex dummy = new Vex();
		if(n1 == n2) return 0;
		
		q1.offer(n1);
		q1.offer(dummy);
		q2.offer(n2);
		q2.offer(dummy);
		
		int degree = 1;
		
		while(!q1.isEmpty() && !q2.isEmpty()){
			Vex n = q1.poll();
			if(n == dummy) {
				degree++;
				if(!q1.isEmpty())
					q1.offer(dummy);
				
			} else {
				for(Vex nei : n.adjList){
					if(visited2[nei.nid] == true) return degree; 
					visited1[nei.nid] = true;
					q1.offer(nei);
				}
				
			}
			
			n = q2.poll();
			if(n == dummy) {
				degree++;
				if(!q2.isEmpty())
					q2.offer(dummy);
			}else{
				for(Vex nei : n.adjList){
					if(visited1[nei.nid] == true) return degree; 
					visited2[nei.nid] = true;
					q2.offer(nei);
				}
			}
		}
		
		return -1; // disconnected
	}
	
// find the short path from one to the others O(n^2)
// generate the shortest path according to path length growing order	
	public void DIJ(int[][] g, int v0){
		int[] cost = new int[g.length];
		int Infinity = 65535;
		boolean[][] p = new boolean[g.length][g.length];
		boolean[] last = new boolean[g.length];
		for(int i=0;i<g.length;++i){
			cost[i] = g[v0][i];
			for(int j=0;j<g.length;++j){
				p[i][j] = false;
			}
			if(cost[i] < Infinity) {
				p[i][v0] = true;
				p[i][i] = true;
			}
		}
		cost[v0] = 0; last[v0] = true;
		int v = 0;
		for(int i=0;i<g.length;++i){ // every time, pick the shortest path endnode into the set
			int min = Infinity;
			for(int j=0;j<g.length;++j){
				if(!last[j]) 
					if(cost[j] < min){
						v = j;
						min = cost[j];
					}
			}
			last[v] = true;
			for(int j=0;j<g.length;++j){
				if(!last[j] && min+g[v][j]<cost[j]){
					cost[j] = min+g[v][j];
					p[j][0..g.length] = p[v][0..g.length];
					p[j][j] = true;
				}
			}
		}
	}
	
	/**
	 * DIJ using Heap struct. introduce another node structure
	 * Time complexity is : O((|v|+|e|)log(|v|))
	 * 
	 */
	class Node implements Comparable{
		int dist;
		int v;
		Node pre;
		
		public int compareTo(Object ob){
			Node o = (Node) ob;
			if(this.dist < o.dist) return -1;
			else if(this.dist > o.dist) return 1;
			else{
				if(this.v < o.v) return -1;
				else if(this.v > o.v) return 1;
				else return 0;
			}
		}
	}
	
	public void DIJ_PQ(Graph g, int v0){
		boolean[] visited = new boolean[g.vexnum];
		Node[] nodes = new Node[g.vexnum];
		for(int i=0;i<g.vexnum;++i){
			visited[i] = false;
			nodes[i].dist = Integer.MAX_VALUE;
			nodes[i].v = i;
			nodes[i].pre = null;
		}
		
		Queue<Node> pq = new PriorityQueue<Node>(); // works as priority queue
		nodes[v0].dist = 0;
		nodes[v0].pre = nodes[v0];
		
		pq.add(nodes[v0]);
		while(!pq.isEmpty()){
			Node n = pq.poll();
			int u = n.v;
			visited[u] = true;
			ArcNode arc = g.vertices[u].firstArc;
			while(arc.nextArc != null){
				int v = arc.vexID;				
				if(!visited[v] && arc.weight+n.dist < nodes[v].dist){
					nodes[v].dist = n.dist + arc.weight;
					nodes[v].pre = n;
					pq.offer(nodes[v]);
				}
				arc = arc.nextArc;
			}
		}
		
	}
	
// floyd, shortest path btween every node	O(n^3)
	public void floyd(int[][] g){
		int Infinity = 65532;
		int[][] cost = new int[g.length][g.length];
		boolean[][][] path = new boolean[g.length][g.length][g.length];
//		 initiate with direct weight, and initiate path array
		for(int i=0;i<g.length;++i){
			for(int j=0;j<g.length;++j){
				cost[i][j] = g[i][j];
				for(int k=0;k<g.length;++k)
					path[i][j][k] = false;
				if(cost[i][j] < Infinity){
					path[i][j][i] = true;
					path[i][j][j] = true;
				}
			}
		}
//  search for shorter path 		
		for(int i=0;i<g.length;++i)
			for(int j=0;j<g.length;++j){
				for(int k=0;k<g.length;++k){
					if(cost[i][k]+cost[k][j]<cost[i][j]){
						cost[i][j] = cost[i][k]+cost[k][j];
						for(int l = 0;l<g.length;++l)
							path[i][j][l] = path[i][k][l] || path[k][j][l];
					}
				}
			}
				
	}
	
/* generate minimum spanning tree
 * Kruskal is better for sparse graph with less edges O(eloge)
 * Prim is better for density graph with more edges O(n^2)
	public void Kruskal(int[][] G){
		A = null;
		for each vertex v in G.V:
			make-set(v)
		sort the edges of E into nondecreasing order by weight w;
		for each edge (u, v) in E, taken in nondecreasing order by weight:
			if find-set(u) != find-set(v):
				A = A.add(u,v)
				Union-set(u,v);
		return A;
	}
	*/
	
/**
 * IDDFS:
 * Iterative deepening depth-first search[1] (IDDFS) 
 * is a state space search strategy in which a depth-limited 
 * search is run repeatedly, increasing the depth limit with 
 * each iteration until it reaches d, the depth of the shallowest 
 * goal state. IDDFS is equivalent to breadth-first search, 
 * but uses much less memory; on each iteration, it visits 
 * the nodes in the search tree in the same order as depth-first search, 
 * but the cumulative order in which nodes are first visited is 
 * effectively breadth-first.
 * 
 * IDDFS combines depth-first search's space-efficiency and breadth-first 
 * search's completeness (when the branching factor is finite). 
 * It is optimal when the path cost is a non-decreasing function of the depth 
 * of the node.
The space complexity of IDDFS is O(bd), 
where b is the branching factor and d is the depth of shallowest goal. 
Since iterative deepening visits states multiple times, it may seem wasteful, 
but it turns out to be not so costly, since in a tree most of the nodes are 
in the bottom level, so it does not matter much if the upper levels are visited 
multiple times.[2]

recursive depth-limited DFS -- DLS
 */	
	
	public TNode iddfs(TNode root, int data){
		int depth = 0;
		TNode node;
		while(depth < MaxDepth){
			node = dls(root, data, depth);
			if(node != null) return node;
			depth++;
		}
	}
	
	public TNode dls(TNode node, int data, int depth){
		TNode n;
		if(node.data == data)
			return node;
		else if(depth > 0){
			for(int i=0;i<node.children.length;++i){
				n = dls(node.children[i],data,depth-1);
				if(n!= null) return n;
			}
			return null;
		}
		else return null;
	}
	
	/**
	 * find out how many paths from one node to another.
	 * (0,0) -> (n-1,m-1)
	 */
	public int findNpath(int[][] g){
		int n = g.length;
		int m = g[0].length;
		int[][] dp = new int[g.length][g[0].length];
		for(int i=0;i<n;++i){
			Arrays.fill(dp[i], 0);
		}
		Arrays.fill(dp[n-1], 1);
		dp[][m-1] = 1;
		
		for(int i=n-2;i>0;--i)
			for(int j=m-2;j>0;--j)
				dp[i][j] = dp[i+1][j]+dp[i][j+1];
				
		return dp[0][0];
	}
	
	/**
	 * clone a graph
	 * take care the cycle
	 */
	public ArrayList<Node> cloneGraph(ArrayList<Node> graph){
		HashMap<Node,Node> visited = new HashMap<Node,Node>();
		ArrayList<Node> cgraph = new ArrayList<Node>(graph.size());
		for(Node n : graph){
			if(!visited.containsKey(n))
				DFSClone(n,graph,visited);
		}
	}
	
	public Node DFSClone(Node n, ArrayList<Node> graph, HashMap<Node,Node> visited){
		
		Node cn = new Node(n->data);
		visited.put(n,cn);
		if(n.neighbors != null)
			for(Node v : n.neighbors){
				if(visited.containsKey(v))
					cn.list.add(visited.get(v));
				else
					cn.list.add(DFSClone(v,graph,visited));
			}
		graph.add(cn);
		return cn;
	}
}
