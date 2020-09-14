给定一个二叉树，返回它的 后序 遍历。

示例:

输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//************************************************************************************
// 递归解法
class Solution {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        postOrder(root);
        return ans;
    }

    void postOrder(TreeNode node) {
        if (node == null) return;
        if (node.left != null) postOrder(node.left);
        if (node.right != null) postOrder(node.right);
        ans.add(node.val);
    }
}

//*************************************************************************************
// 迭代解法
class Solution {
    List<Integer> ans = new ArrayList<>();
    Stack<TreeNode> cache = new Stack<>();

    public List<Integer> postorderTraversal(TreeNode root) {
    	while(true) {
    		while(true) {//下一个访问的点
    			if (root == null) break;
    			goAlongLeft(root);
    			root = cache.peek().right;
    		}
    		visitAlongRight();
    		if (cache.empty()) break;
    		root = cache.peek().right;
    	}
        return ans;
    }

    void goAlongLeft(TreeNode node) {
        while (node != null) {
            cache.push(node);
            node = node.left;
        }
    }

    void visitAlongRight() {
    	if (cache.empty()) return;
    	TreeNode node = cache.pop();
    	ans.add(node.val);
    	while (!cache.empty() && node == cache.peek().right) {
    		node = cache.pop();
    		ans.add(node.val);
    	}
    }
}