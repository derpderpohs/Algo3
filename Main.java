
/*
 * Name: Seah Hao Jian
 * GUID: 2228156S
 * 16 November 2015
 * Title: Algorithmics I (H) Assessed Assignment
 * This is my own work which is referred from the Assessed Coursework warmup
 * Lab exercise on moodle  
 */
import java.io.*;
import java.util.*;

/**
 * program to find shortest path using Dijkstra's algorithm
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // input file name

		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);

		Vertex v = null;
		Vertex[] vertices = null;

		int source = -1, dest = -1;
		int noOfVertices = 0;

		// start reading from file
		if (in.hasNextInt()) {
			// get first initial matrix
			noOfVertices = in.nextInt();
			vertices = new Vertex[noOfVertices];
			in.nextLine();
		}

		for (int i = 0; i <= noOfVertices; i++) {
			if (in.hasNextLine()) {

				String line = in.nextLine();
				String[] matrix = line.split(" ");
				if (matrix != null && i < noOfVertices) {
					// insert AdjListNodes into the vertex
					v = processMatrix(matrix, noOfVertices, i);
					vertices[i] = v;
				} else {

					// get the source node id and end node id
					source = Integer.parseInt(matrix[0]);
					dest = Integer.parseInt(matrix[1]);
				}
			}
		}

		// close the IO scanner and FileReader
		in.close();
		reader.close();

		// create graph here
		Graph g = new Graph(vertices);
		vertices = g.dijkstra(source, dest);

		// get the best route in String Format
		String route = g.getPath(dest);

		// print out the required data
		System.out.println("Shortest distance from vertex " + source + " to vertex " + dest + " is "
				+ g.getVertex(dest).getWeight());
		System.out.println("Shortest path: " + route);

		// end timer and print total time
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (end - start) + " milliseconds");

	}

	// This helper function processes the list of edges and their values and
	// adds it into a Vertex
	public static Vertex processMatrix(String[] line, int total, int index) {
		// if we are still running through the matrix
		Vertex v = new Vertex(index);

		for (int i = 0; i < total; i++) {
			int w = Integer.parseInt(line[i]);
			if (w != 0) {
				// loop through line and add to adjmatrix
				v.addToAdjList(i, w);
			}
		}
		return v;
	}

}
