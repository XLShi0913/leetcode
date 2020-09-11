/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
    	List<String> ans = new ArrayList<String>();
    	Stack<TreeNode> cache = new Stack<TreeNode>();
    	Stack<Integer> status = new Stack<Integer>();
    	//用于记录节点状态，0表示当前左右子树都还未遍历，1表示当前左子树已经遍历过了，2表示左右子树都已遍历完毕

    	cache.push(root);
    	status.push(0);//启动！

    	while (!cache.empty()) {

    		if (cache.peek().left == null) {
    			//左子树空
    			if (cache.peek().right == null) {
    				//左右都空，说明栈顶节点为叶节点
    				addToAns(cache, ans);
    				status.pop();
    				int temp = status.pop();
    				status.push(temp + 1);
    			}
    		}
    	}
    	return ans;
    }

    public void addToAns(Stack<TreeNode> cache, List<String> ans){
    	StringBuffer oneAns = new StringBuffer();
    	oneAns.append(cache.pop().val);
    	while (!cache.empty()) {
    		oneAns.append(">-");
    		oneAns.append(cache.pop().val);
    	}
    	ans.add(oneAns.reverse().toString());
    }
}

//*******************************************************************************
//用栈状态设定太麻烦，参考了答案改用递归
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<String>();
        paths(root, "", ans);
        return ans;
    }

    public void paths(TreeNode node, String path, List<String> ans) {
        if (node == null) return;
        StringBuffer oneAns = new StringBuffer(path);
        oneAns.append(node.val);
        if (node.left == null && node.right == null) {
            ans.add(oneAns.toString());
        }else {
            oneAns.append("->");
            paths(node.left, oneAns.toString(), ans);
            paths(node.right, oneAns.toString(), ans);
        }
    }
}