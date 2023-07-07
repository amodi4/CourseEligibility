package prereqchecker;

import java.util.*;

/**
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) 
    {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
    StdIn.setFile(args[0]);
    NewMaps a = new NewMaps();
    int courses = StdIn.readInt();
    for (int i = 0; i < courses; i++)
    {
        a.add(StdIn.readString());
    }

    int prereqs = StdIn.readInt();
    for (int i = 0; i < prereqs; i++)
    {
        a.update(StdIn.readString(), StdIn.readString());
    }

    StdIn.setFile(args[1]);
    ArrayList<String> takenCourse = new ArrayList<String>();
    int taken = StdIn.readInt();
    for (int i = 0; i < taken; i++)
    {
        takenCourse.add(StdIn.readString());
    }

    StdOut.setFile(args[2]);
    HashSet<String> prereq = a.getAll(takenCourse);

    for (String x : a.getcoursesAndPreReqs().keySet())
    {
        if (!prereq.contains(x))
        {
            if(a.eligibleCourse(x, prereq))
            {
                StdOut.println(x);
            }
        }
    }
    }
}
