//Time Complexity: O(N) – Every node is visited once.
//Space Complexity: O(N) – For the queue and result list, in the worst case when the tree is completely balanced.

//Use a queue to perform BFS level by level starting from the root.
//For each level, store node values in a sublist and enqueue their non-null children.
//Add each level’s sublist to the final result list.



class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> outerlist = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root == null)
        {
            return outerlist;
        }

        q.offer(root);
        while(!q.isEmpty())
        {
            int n = q.size();
            List<Integer> sublist = new ArrayList<>();
            for(int i =0; i<n; i++)
            {
                TreeNode curr = q.poll();
                sublist.add(curr.val);
                if(curr.left != null)
                {
                    q.offer(curr.left);
                }
                if(curr.right != null)
                {
                    q.offer(curr.right);
                }

            }
           
            outerlist.add(sublist);
        }
        return outerlist;
    }
}