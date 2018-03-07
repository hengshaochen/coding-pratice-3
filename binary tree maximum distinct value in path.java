// Iterative:
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(Tree T) {
        // Algo: Use Depth First Search(DFS) to traverse each node in tree, and use a HashMap to store the node.value and frequency in path.
        // I choose to use iterative DFS because the height of tree may exceed 3500 to Avoid stack overflow if use recursive DFS.
        // Time Complexity: O(N) , Space: O(N), using HashMap and HashSet
        
        // Corner case
        if (T == null) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Tree> visited = new HashSet<>();
        int ans = 0;
        
        Stack<Tree> stack = new Stack<>();
        stack.push(T);
        
        while (!stack.isEmpty()) {
            Tree currentNode = stack.peek();
            
            // Put the current node into map
            if (!map.containsKey(currentNode.x)) {
                map.put(currentNode.x, 1);
            } else {
                map.put(currentNode.x, map.get(currentNode.x) + 1);
            }
            ans = Math.max(ans, map.keySet().size());
            
            // if current node is leaf node, or current node's left and right child already been visited --> pop stack and add the node to 
            if ( (visited.contains(currentNode.l) || currentNode.l == null) &&
                  (visited.contains(currentNode.r) || currentNode.r == null) ) {
                currentNode = stack.pop();
                visited.add(currentNode);
                
                if (currentNode.l == null && currentNode.r == null) {
                    // if node is leaf node, 
                    map.put(currentNode.x, map.get(currentNode.x) - 1);
                } else {
                    // if node is not leaf node, this kind of node has been visited twice 
                    // visit + backtrack
                    map.put(currentNode.x, map.get(currentNode.x) - 2);
                }
                if (map.get(currentNode.x) <= 0) {
                    map.remove(currentNode.x);
                }
                
            } else {
                // current node is not finish yet
                if (currentNode.r != null) {
                    stack.push(currentNode.r);
                }
                if (currentNode.l != null) {
                    stack.push(currentNode.l);
                }
            }
        }
        
        return ans;
    }
}

// Recursive:
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(Tree T) {
        // Algo: Use Depth First Search(DFS) to traverse each node in tree, and use a HashMap to store the node.value and frequency in path.
        // Time Complexity: O(N) , Space: O(N)
        
        // Corner case
        if (T == null) {
            return 0;
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        return maxUniquePath(map, T);
    }
    
    public int maxUniquePath(HashMap<Integer, Integer> map, Tree currentNode) {
        // base case: when 
        if (currentNode == null) {
            return map.keySet().size();
        }
        
        if (!map.containsKey(currentNode.x)) {
            map.put(currentNode.x, 1);
        } else {
            map.put(currentNode.x, map.get(currentNode.x) + 1);
        }
        
        int maxPath = Math.max(maxUniquePath(map, currentNode.l), maxUniquePath(map, currentNode.r));
        
        map.put(currentNode.x, map.get(currentNode.x) - 1);
        if (map.get(currentNode.x) == 0) {
            map.remove(currentNode.x);
        }
        
        return maxPath;
    }
}