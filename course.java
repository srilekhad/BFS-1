//Time Complexity: O(V + E) — where V is the number of courses and E is the number of prerequisite pairs.
//Space Complexity: O(V + E) — for the adjacency list, indegree array, and queue.

//Build a graph and indegree array from the prerequisites.
//Add all courses with 0 indegree to a queue and perform BFS (Kahn’s Algorithm).
//Count processed courses; if all are processed, return true (no cycle), else return false.

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return true;
        }

        int[] indegrees = new int[numCourses];
        HashMap<Integer, List<Integer>> adjacencyMap = new HashMap<>();

        // Build the graph and compute indegrees
        for (int[] prerequisite : prerequisites) {
            int to = prerequisite[0];
            int from = prerequisite[1];
            indegrees[to]++;
            adjacencyMap.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        }

        Queue<Integer> queue = new LinkedList<>();
        int count = 0;

        // Add all courses with 0 indegree (no prerequisites)
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }

        // Process the courses
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++; // ✅ Increment here when course is processed

            List<Integer> neighbors = adjacencyMap.get(current);
            if (neighbors == null) continue;

            for (int neighbor : neighbors) {
                indegrees[neighbor]--;
                if (indegrees[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return count == numCourses;
    }
}
