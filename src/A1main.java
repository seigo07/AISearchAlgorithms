import java.util.*;

/********************Starter Code
 * 
 * This class contains some examples on how to handle the required inputs and outputs 
 * and other debugging options
 * 
 * @author at258
 * 
 * run with 
 * java A1main <Algo> <ConfID>
 * 
 */


public class A1main {

	static Solution s;

	public static void main(String[] args) {
		//Example: java A1main BFS JCONF03

		/* 
		 * 
		 * Retrieve input and configuration
		 * and run search algorithm
		 *
		 */

		Conf conf = Conf.valueOf(args[1]);

		System.out.println("Configuration:"+args[1]);
		System.out.println("Map:");
		printMap(conf.getMap(), conf.getS(), conf.getG());
		System.out.println("Departure port: Start (r_s,c_s): "+conf.getS());
		System.out.println("Destination port: Goal (r_g,c_g): "+conf.getG());
		System.out.println("Search algorithm: "+args[0]);
		System.out.println();

		//run your search algorithm 
		runSearch(args[0],conf.getMap(),conf.getS(),conf.getG());

		/*
		 * The system must print the following information from your search methods
		 * All code below is for demonstration purposes, and can be removed
		 */


		/*
		 * 1) Print the Frontier at each step before polling 
		 */

		boolean uninformed=true;
		String frontier_string="";

		if(uninformed) {

			//starting point (1,1), 
			//insert node in the frontier, then print the frontier:
			frontier_string="[(0,0)]";


			System.out.println(frontier_string);

			//extract (0,0)
			//insert successors in the frontier (0,1),(1,0) , then print the frontier,  and repeat for all steps until a path is found or not 
			
			
			frontier_string="[(1,1)]\n" +
			"[(1,2),(2,1),(1,0)]\n" +
			"[(2,1),(1,0),(1,3),(0,2)]\n" +
			"[(1,0),(1,3),(0,2),(2,0)]\n" +
			"[(1,3),(0,2),(2,0),(0,0)]\n" +
			"[(0,2),(2,0),(0,0),(1,4)]\n" +
			"[(2,0),(0,0),(1,4),(0,3),(0,1)]\n" +
			"[(0,0),(1,4),(0,3),(0,1),(3,0)]\n" +
			"[(1,4),(0,3),(0,1),(3,0)]\n" +
			"[(0,3),(0,1),(3,0),(1,5),(0,4)]\n" +
			"[(0,1),(3,0),(1,5),(0,4)]\n" +
			"[(3,0),(1,5),(0,4)]\n" +
			"[(1,5),(0,4),(3,1)]\n" +
			"[(0,4),(3,1),(2,5)]\n" +
			"[(3,1),(2,5),(0,5)]\n" +
			"[(2,5),(0,5),(3,2),(4,1)]\n" +
			"[(0,5),(3,2),(4,1),(2,4)]\n" +
			"[(3,2),(4,1),(2,4)]\n" +
			"[(4,1),(2,4),(3,3)]\n" +
			"[(2,4),(3,3),(4,2),(4,0)]\n" +
			"[(3,3),(4,2),(4,0),(3,4)]\n" +
			"[(4,2),(4,0),(3,4),(4,3)]\n" +
			"[(4,0),(3,4),(4,3),(5,2)]\n" +
			"[(3,4),(4,3),(5,2),(5,0)]\n";
			System.out.println(frontier_string);


		}else {
			//for informed searches the nodes in the frontier must also include the f-cost 
			//for example 


			frontier_string="[(1,1):5.0]\n" + 
			"[(1,2):5.0,(2,1):5.0,(1,0):7.0]\n" + 
			"[(1,3):5.0,(2,1):5.0,(1,0):7.0,(0,2):7.0]\n" +
			"...\n" +
			"(1,1)(2,1)(2,0)(3,0)(3,1)(3,2)(3,3)(3,4)\n" +
			"Down Left Down Right Right Right Right\n" +
			"7.0\n" +
			"14\n";
			System.out.println(frontier_string);

		}

		/*
		 * 2) The final three lines must be the path, the path in string, path cost, and number of nodes visited/explored, in this order
		 */

//		boolean path_found=true;
//		String path="(1,1)(1,2)(1,3)(1,4)(1,5)(2,5)(2,4)(3,4)";
//		String path_string = "Right Right Right Right Down Left Down";
//		double path_cost=7.0;
//		int n_explored=24;

		if(s.getPathFound()) {
			System.out.println(s.getPath());
			System.out.println(s.getPathString());
			System.out.println(s.getPathCost());
		}else {
			System.out.println("fail");
		}

		System.out.println(s.getNExplored());

	}

	private static void runSearch(String algo, Map map, Coord start, Coord goal) {
		switch(algo) {
		case "BFS": //run BFS
			BFS(map, start, goal);
			break;
		case "DFS": //run DFS
			DFS(map, start, goal);
			break;
		case "BestF": //run BestF
			break;
		case "AStar": //run AStar
			break;
		}
	}

	public static void BFS(Map problem, Coord initial_state, Coord goal) {

		Deque<Node> frontier = new ArrayDeque<>();
		Deque<Node> explored = new ArrayDeque<>();
		Node initialNode = new Node(null, initial_state);
		frontier.add(initialNode);

		// TODO delete
		Coord coord = new Coord(1,0);
		Node node = new Node(null, coord);
		expand(node, problem, frontier, explored);

		for (;;) {
			if (frontier.isEmpty()) {
				s = new Solution(false, "", "", 0, 0);
				return;
			}
			Node nd = frontier.removeFirst();
			explored.add(nd);
			if (nd.equals(goal)) {
				boolean path_found=true;
				String path="(1,1)(1,2)(1,3)(1,4)(1,5)(2,5)(2,4)(3,4)";
				String path_string = "Right Right Right Right Down Left Down";
				double path_cost=7.0;
				int n_explored=24;
				s = new Solution(path_found, path, path_string, path_cost, n_explored);
				return;
			} else {
				frontier.addAll(expand(nd, problem, frontier, explored));
			}
		}
	}

	public static void DFS(Map map, Coord start, Coord goal) {
		boolean path_found=true;
		String path="(1,1)(1,2)(1,3)(1,4)(1,5)(2,5)(2,4)(3,4)";
		String path_string = "Right Right Right Right Down Left Down";
		double path_cost=7.0;
		int n_explored=24;
		s = new Solution(path_found, path, path_string, path_cost, n_explored);
	}

	public static ArrayList<Node> expand(Node node, Map problem, Deque<Node> frontier, Deque<Node> explored) {
		ArrayList<Coord> next_states = successor(node.getState(), problem);
		ArrayList<Node> successors = new ArrayList<Node>();
		for (int i = 0; i < next_states.size(); i++) {
			Coord state = next_states.get(i);
			if (!state.equals(explored) || !state.equals(frontier)) {
				Node nd = new Node(node, next_states.get(i));
				successors.add(nd);
			}
		}
		return successors;
	}

	public static ArrayList<Coord> successor(Coord node_state, Map problem) {

		// Check direction of triangle
		int dir = (node_state.getC() + node_state.getR()) % 2 == 0 ? 0 : 1;
		ArrayList<Coord> next_states = getNextStates(problem, node_state, dir);

		// TODO delete
		System.out.print("next_states\n");
		for (Coord e : next_states) {
			System.out.print(e.getC() + "-" + e.getR() + "\n");
		}

		return next_states;
	}

	public static ArrayList<Coord> getNextStates(Map problem, Coord node_state, int dir) {

		ArrayList<Coord> next_states = new ArrayList<Coord>();
		// Add right node
		if (checkRightOrBottomNodeIsValid(node_state.getC(), problem.getMap().length)) {
			int fi = node_state.getC() + 1;
			int si = node_state.getR();
			if (checkNextValueIsValid(problem, si, fi)) {
				Coord coord = new Coord(fi, si);
				next_states.add(coord);
			}
		}
		// Upper case
		if (dir == 0) {
			// Add bottom node
			if (checkRightOrBottomNodeIsValid(node_state.getR(), problem.getMap().length)) {
				int fi = node_state.getC();
				int si = node_state.getR() + 1;
				if (checkNextValueIsValid(problem, si, fi)) {
					System.out.print("hoge\n");
					System.out.print(fi + "\n");
					System.out.print(si + "\n");
					Coord coord = new Coord(fi, si);
					next_states.add(coord);
				}
			}
		}
		// Add left node
		if (checkLeftOrTopNodeIsValid(node_state.getC())) {
			int fi = node_state.getC() - 1;
			int si = node_state.getR();
			if (checkNextValueIsValid(problem, si, fi)) {
				Coord coord = new Coord(fi, si);
				next_states.add(coord);
			}
		}
		// Upper case
		if (dir == 1) {
			// Add top node
			if (checkLeftOrTopNodeIsValid(node_state.getR())) {
				int fi = node_state.getC();
				int si = node_state.getR() - 1;
				if (checkNextValueIsValid(problem, si, fi)) {
					Coord coord = new Coord(fi, si);
					next_states.add(coord);
				}
			}
		}
		return next_states;
	}

	// Check if the next state is valid
	public static boolean checkNextValueIsValid(Map problem, int fi, int si) {
		return problem.getMap()[fi][si] != 1;
	}

	// Check if index - 1 < 0
	public static boolean checkLeftOrTopNodeIsValid(int index) {
		return index - 1 >= 0;
	}

	// Check if index + 1 < upper limit of index
	public static boolean checkRightOrBottomNodeIsValid(int index, int length) {
		return index + 1 <= length - 1;
	}

	public static boolean goalTest(Coord nd, Coord goal) {
		return nd.equals(goal);
	}

	private static void printMap(Map m, Coord init, Coord goal) {

		int[][] map=m.getMap();

		System.out.println();
		int rows=map.length;
		int columns=map[0].length;

		//top row
		System.out.print("  ");
		for(int c=0;c<columns;c++) {
			System.out.print(" "+c);
		}
		System.out.println();
		System.out.print("  ");
		for(int c=0;c<columns;c++) {
			System.out.print(" -");
		}
		System.out.println();

		//print rows 
		for(int r=0;r<rows;r++) {
			boolean right;
			System.out.print(r+"|");
			if(r%2==0) { //even row, starts right [=starts left & flip right]
				right=false;
			}else { //odd row, starts left [=starts right & flip left]
				right=true;
			}
			for(int c=0;c<columns;c++) {
				System.out.print(flip(right));
				if(isCoord(init,r,c)) {
					System.out.print("S");
				}else {
					if(isCoord(goal,r,c)) {
						System.out.print("G");
					}else {
						if(map[r][c]==0){
							System.out.print(".");
						}else{
							System.out.print(map[r][c]);
						}
					}
				}
				right=!right;
			}
			System.out.println(flip(right));
		}
		System.out.println();


	}

	private static boolean isCoord(Coord coord, int r, int c) {
		//check if coordinates are the same as current (r,c)
		if(coord.getR()==r && coord.getC()==c) {
			return true;
		}
		return false;
	}



	public static String flip(boolean right) {
        //prints triangle edges
		if(right) {
			return "\\"; //right return left
		}else {
			return "/"; //left return right
		}

	}

}