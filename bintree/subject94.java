给定一个二叉树，返回它的中序 遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//****************************************************************************
// 递归方法
class Solution {
    List<Integer> ans = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return ans;
        midOrder(root);
        return ans;
    }

    void midOrder(TreeNode root) {
        if (root.left != null) midOrder(root.left);
        ans.add(root.val);
        if (root.right != null) midOrder(root.right);
    }
}

//**************************************************************************
// 迭代方法
class Solution {
	List<Integer> ans = new ArrayList<>();
	Stack<TreeNode> cache = new Stack<>();

	public List<Integer> inorderTraversal(TreeNode root) {
		while (true) {
			goAlongLeft(root);
			if (cache.empty()) break;
			root = cache.pop();
			ans.add(root.val);
			root = root.right;
		}
        return ans;
	}

	void goAlongLeft (TreeNode node) {
		while (node != null) {
			cache.push(node);
			node = node.left;
		}
	}
}
//**************************************************************************
// 参考答案
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode-solutio/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。