package tree;

import java.util.*;

public class Traversal {

    /*
    https://leetcode.com/problems/binary-tree-preorder-traversal/description/
    The order is supposed to be root, left ,right.
    This is pretty straight forward. When a node is reached, pop it from the stack, add it to the list,
        then add the right and left elements to the stack.
     The reason why we add right first is because in a stack, it is lifo. So when popping, left will be popped and
        added first then right will be popped and added.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> trav = new ArrayList<Integer>();
        if(root == null)
            return trav;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        stack.add(root);
        while(!stack.isEmpty()) {
            root = stack.removeLast();
            trav.add(root.val);
            if(root.right != null) {
                stack.add(root.right);
            }
            if(root.left != null) {
                stack.add(root.left);
            }
        }
        return trav;
    }

    /*
    https://leetcode.com/problems/binary-tree-inorder-traversal/description/
    The order is supposed to be left, root, right.
    So here, we traverse all left and add it to stack. When no more left is present, then we pop it, add it to the list,
        and the proceed right.
    The loop ensures that whenever we move right, it checks if left exists or not.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> trav = new ArrayList<Integer>();
        if(root == null)
            return trav;

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null) {
            if(node != null) {
                stack.add(node);
                node = node.left;
            }else {
                node = stack.removeLast();
                trav.add(node.val);
                node = node.right;
            }
        }
        return trav;
    }

    /*
    https://leetcode.com/problems/binary-tree-postorder-traversal/description/
    The order is supposed to be left, right, root.
    Similar to inOrder, we keep moving left till we can't anymore. Then we start moving right.
    We only add to the list when there is no right available for the node.
    The reason for lastSeen is, it prevents us from adding the same right to the stack over and over
        which would read to an infinite loop.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> trav = new ArrayList<Integer>();
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        TreeNode node = root;
        TreeNode lastSeen = null;

        while(!stack.isEmpty() || node != null) {
            while(node != null) {
                stack.add(node);
                node = node.left;
            }
            TreeNode temp = stack.peekLast();
            if(temp.right != null && temp.right != lastSeen) {
                node = temp.right;
            }else {
                trav.add(temp.val);
                lastSeen = stack.removeLast();
            }
        }

        return trav;
    }

    /*
    https://leetcode.com/problems/binary-tree-level-order-traversal/description/
    Also known as Breadth First Traversal (BST).
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> travElements = new ArrayList<List<Integer>>();
        if(root == null)
            return travElements;

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<Integer>();
            for(int i = 0; i < size; i++) {
                if(queue.peek().left != null)
                    queue.add(queue.peek().left);
                if(queue.peek().right != null)
                    queue.add(queue.peek().right);
                level.add(queue.pop().val);
            }
            travElements.add(level);
        }
        return travElements;
    }

    /*
    https://www.naukri.com/code360/problems/tree-traversal_981269?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
     */
    //With recursion
    public static List<List<Integer>> getTreeTraversalAllInOne(TreeNode root) {
        List<List<Integer>> finalList = new ArrayList<List<Integer>>();
        List<Integer> inTrav = new ArrayList<Integer>();
        List<Integer> preTrav = new ArrayList<Integer>();
        List<Integer> postTrav = new ArrayList<Integer>();

        inOrder(inTrav, preTrav, postTrav, root);
        finalList.add(inTrav);
        finalList.add(preTrav);
        finalList.add(postTrav);
        return finalList;
    }

    public static void inOrder(List<Integer> inTrav, List<Integer> preTrav, List<Integer> postTrav, TreeNode node) {
        preTrav.add(node.val);
        if(node.left != null) {
            inOrder(inTrav, preTrav, postTrav, node.left);
        }
        inTrav.add(node.val);
        if(node.right != null) {
            inOrder(inTrav, preTrav, postTrav, node.right);
        }
        postTrav.add(node.val);
    }

    //Without recursion
    //1 is preorder, 2 is inorder and 3 is postorder
    public static List<List<Integer>> getTreeTraversalAllInOneNoRecursion(TreeNode root) {
        List<List<Integer>> finalList = new ArrayList<List<Integer>>();
        List<Integer> inTrav = new ArrayList<Integer>();
        List<Integer> preTrav = new ArrayList<Integer>();
        List<Integer> postTrav = new ArrayList<Integer>();


        LinkedList<Pair> stack = new LinkedList<Pair>();
        stack.add(new Pair(root, 1));

        while(!stack.isEmpty()) {
            Pair current = stack.removeLast();

            if(current.index == 1) {
                stack.add(new Pair(current.node, 2));
                preTrav.add(current.node.val);
                if(current.node.left != null)
                    stack.add(new Pair(current.node.left,1));
            }else if(current.index == 2) {
                stack.add(new Pair(current.node, 3));
                inTrav.add(current.node.val);
                if(current.node.right != null)
                    stack.add(new Pair(current.node.right,1));
            }else {
                postTrav.add(current.node.val);
            }
        }

        finalList.add(inTrav);
        finalList.add(preTrav);
        finalList.add(postTrav);

        return finalList;
    }

    /*
    https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> finalList = new ArrayList<List<Integer>>();
        if(root == null)
            return finalList;
        LinkedList<Pair> queue = new LinkedList<Pair>();
        Map<Integer, ArrayList<Integer>> tm = new TreeMap<Integer, ArrayList<Integer>>();
        queue.add(new Pair(root, 0));

        while(!queue.isEmpty()) {
            int size = queue.size();
            Map<Integer, ArrayList<Integer>> ts = new TreeMap<Integer, ArrayList<Integer>>();
            for(int i = 0; i < size; i++) {
                Pair temp = queue.pop();
                ts.computeIfAbsent(temp.index, j -> new ArrayList<Integer>()).add(temp.node.val);
                if(temp.node.left != null)
                    queue.add(new Pair(temp.node.left, temp.index-1));
                if(temp.node.right != null)
                    queue.add(new Pair(temp.node.right, temp.index+1));
            }
            for(int s : ts.keySet()) {
                Collections.sort(ts.get(s));
                tm.computeIfAbsent(s, j -> new ArrayList<Integer>()).addAll(ts.get(s));
            }
        }

        for(int k : tm.keySet()) {
            finalList.add(tm.get(k));
        }
        return finalList;
    }

    static class Pair {
        TreeNode node;
        int index;

        public Pair(TreeNode n, int i) {
            node = n;
            index = i;
        }
    }
}
