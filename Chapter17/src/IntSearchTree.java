public class IntSearchTree
{
    private IntTreeNode overallRoot;
    public IntSearchTree()
    {
        overallRoot = null;
    }

    public void add(int value)
    {
        overallRoot = add(overallRoot, value);
    }

    private IntTreeNode add(IntTreeNode root, int value)
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
                root.left = add(root.left, value);
            }
        }
        else
        {
            root.right = add(root.right, value);
        }

        return root;
    }

    public boolean contains(int value)
    {
        return contains(overallRoot, value);
    }

    public boolean contains(IntTreeNode root, int value)
    {
        if (root == null)
        {
            return false;
        }
        else if (value == root.data)
        {
            return true;
        }
        else if (value <= root.data)
        {
            return contains(root.left, value);
        }
        else
        {
            return contains(root.right, value);
        }
    }

    public void print()
    {
        printInorder(overallRoot);
        System.out.println();
    }

    private void printInorder(IntTreeNode root)
    {
        if (root != null)
        {
            printInorder(root.left);
            System.out.print(root.data + " ");
            printInorder(root.right);
        }
    }

    public void printSideways()
    {
        printSideways(overallRoot, 0);
        System.out.println();
    }

    private void printSideways(IntTreeNode root, int level)
    {
        if (root != null)
        {
            printSideways(root.left, level + 1);
            for (int i = 0; i < level; ++i)
            {
                System.out.print("    ");
            }
            System.out.println(root.data);
            printSideways(root.right, level + 1);
        }
    }
}
