/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    //不使用递归的尝试
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder("[");//结果字符串
        ArrayList<TreeNode> queue = new ArrayList<>();//队列
        TreeNode node;//指向当前被考察的元素
        queue.add(root);//加在列表尾部
        while( ! queue.isEmpty() ){
            node = queue.remove(0);//移除列表头部元素
            if (node == null) {
                res.append("none");
                res.append(",");
                continue;
            }
            res.append(node.val);
            res.append(",");
            queue.add(node.left);
            queue.add(node.right);//不管是否为空，都入队
        }
        res.setCharAt(res.length() - 1, ']');//将最后一个逗号改成方括号
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //去除头尾之后用逗号切分，获得每个元素
        String dataNew = data.substring(1, data.length() - 1);
        String[] datas = dataNew.split(",");

        //借鉴了一点双指针的思想
        int pointOfNow = 0;//当前被考察元素在datas中的秩
        int pointOfChild = 1;//当前被考察元素要插入的孩子在datas中的秩

        //一些初始化操作，一次性在堆中开辟所有节点的存储空间，构建root节点并插入到数组头部
        TreeNode[] treeNodes = new TreeNode[datas.length];
        TreeNode root = newlyBuildNode(datas, 0);
        treeNodes[0] = root;

        while(pointOfNow < datas.length){
            if ( !datas[pointOfNow].equals("none") ){
                //有对应孩子的情况，新建节点，存入数组的对应位置，并与当前节点建立联系
                TreeNode left = newlyBuildNode(datas, pointOfChild);
                TreeNode right = newlyBuildNode(datas, pointOfChild + 1);

                treeNodes[pointOfChild++] = left;
                treeNodes[pointOfChild++] = right;

                treeNodes[pointOfNow].left = left;
                treeNodes[pointOfNow].right = right;
            }
            //没有对应孩子的情况，不需要新建节点，直接跳过
            pointOfNow++;
        }
        return root;
    }

    //新建节点的方法
    private TreeNode newlyBuildNode(String[] datas, int index){
        if (datas[index].equals("none")) {
            return null;
        }else{
            return new TreeNode(Integer.parseInt(datas[index]));
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));