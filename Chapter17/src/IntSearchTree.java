public class IntSearchTree
{
    private IntTreeNode overallRoot;
    public IntSearchTree()
    {
        overallRoot = null;
    }

    public void add(int value)
    {
        add(overallRoot, value);
    }

    private void add(IntTreeNode root, int value)
    {
        if (root == null)
        {
            root = new IntTreeNode(value);
        }
        else if (value <= root.data)
        {
            if (root.left == null)
            {
                root.left = new IntTreeNode(value);
            }
            else
            {
                add(root.left, value);
            }
        }
        else
        {
            add(root.right, value);
        }
    }
}
