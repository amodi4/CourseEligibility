package prereqchecker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

public class NewMaps {
    private HashMap<String, ArrayList<String>> coursesAndPreReqs;
	private HashMap<String, Boolean> marked;
    HashSet<String> needTake;
    HashSet<String> directIndirectPrereqs;
    HashSet<String> allPreReqs;
    

	// constructor
	public NewMaps() {
		coursesAndPreReqs = new HashMap<String, ArrayList<String>>();
		marked = new HashMap<String, Boolean>();
	}

    public void add(String courseId)
    {
        coursesAndPreReqs.put(courseId, new ArrayList<String>()); 
        marked.put(courseId, false);   
    }

    public void update(String courseId, String preReq)
    {
        coursesAndPreReqs.get(courseId).add(preReq);
    }

    public HashMap<String, ArrayList<String>> getcoursesAndPreReqs()
    {
        return coursesAndPreReqs;
    }

    public void DFS(String courseId, HashSet<String> preReqs)
    {
        marked.replace(courseId, true);
        preReqs.add(courseId);
        for (int i = 0; i < coursesAndPreReqs.get(courseId).size(); i++)
        {
            if (marked.get(coursesAndPreReqs.get(courseId).get(i))== false);
            {
                DFS(coursesAndPreReqs.get(courseId).get(i),preReqs);
            } 
        }
    }

    public HashSet<String> getDirectIndirectPreqs(String courseId)
    {
        directIndirectPrereqs = new HashSet<String>();
        for (int i = 0; i < coursesAndPreReqs.get(courseId).size(); i++)
        {
            directIndirectPrereqs.add(coursesAndPreReqs.get(courseId).get(i));
            DFS(coursesAndPreReqs.get(courseId).get(i), directIndirectPrereqs);
        }
        return directIndirectPrereqs;
    }


    public HashSet<String> getAll(ArrayList<String> taken)
    {
        allPreReqs = new HashSet<String>();
        for (int i = 0; i < taken.size(); i++)
        {
            marked.replace(taken.get(i), true);
            allPreReqs.add(taken.get(i));
            for (int j = 0; j < coursesAndPreReqs.get(taken.get(i)).size(); j++)
            {
                allPreReqs.add(coursesAndPreReqs.get(taken.get(i)).get(j));
                DFS(coursesAndPreReqs.get(taken.get(i)).get(j), allPreReqs);
            }
        }
        return allPreReqs;
    }

    public boolean eligibleCourse(String courseId, HashSet<String> taken)
    {
        for (int i = 0; i < coursesAndPreReqs.get(courseId).size(); i++)
        {
            if (!taken.contains(coursesAndPreReqs.get(courseId).get(i)))
            {
                return false;
            }
        }
        return true;
    }

    public HashSet<String> needToTake(String courseId, ArrayList<String> taken)
    {
        HashSet<String> takenPrereq = getAll(taken);
        HashSet<String> takePrereqs = getDirectIndirectPreqs(courseId);
        takePrereqs.removeAll(takenPrereq);
        return takePrereqs;

    }
    
    public String toString()
    {
        String x = "";
        for (String courseId : coursesAndPreReqs.keySet())
        {
            x = x + courseId;
            for (int i = 0; i < coursesAndPreReqs.get(courseId).size(); i++)
            {
                x = x +  " " + coursesAndPreReqs.get(courseId).get(i);
            }
            x = x +  "\n";
        }
        return x;
    }
}
