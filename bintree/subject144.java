给定一个二叉树，返回它的 前序 遍历。

 示例:

输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [1,2,3]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

//***********************************************************************************
// 递归解法
class Solution {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        preOrder(root);
        return ans;
    }

    void preOrder(TreeNode node) {
        if (node == null) return;
        ans.add(node.val);
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }
}

//************************************************************************************
// 迭代解法
class Solution {
    List<Integer> ans = new ArrayList<>();
    Stack<TreeNode> cache = new Stack<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        while (true) {
            visitAlongLeft(root);
            if (cache.empty()) break;
            root = cache.pop().right;
        }
        return ans;
    }

    void visitAlongLeft(TreeNode node) {
        while (node != null) {
            ans.add(node.val);
            cache.push(node);
            node = node.left;
        }
    }
}