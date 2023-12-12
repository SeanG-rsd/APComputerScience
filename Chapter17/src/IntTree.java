public class IntTree
{
    // red/black binary tree, priority queue, heap
    // print tree from top down
    private IntTreeNode overallRoot;

    public IntTree(int max)
    {
        overallRoot = buildTree(1, max);
    }

    private IntTreeNode buildTree(int n, int max)
    {
        if (n > max)
        {
            return null;
        }
        else {
            IntTreeNode left = buildTree(2 * n, max);
            IntTreeNode right = buildTree(2 * n, max);
            return new IntTreeNode(n, left, right);
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

    public int sum()
    {
        return sum(overallRoot);
    }

    private int sum(IntTreeNode root)
    {
        if (root == null)
        {
            return 0;
        }
        else
        {
            return root.data + sum(root.left) + sum(root.right);
        }
    }

    public int countLevels()
    {
        return countLevels(overallRoot);
    }

    private int countLevels(IntTreeNode root)
    {
        if (root == null)
        {
            return 0;
        }
        else
        {
            return 1 + Math.max(countLevels(root.left), countLevels(root.right));
        }
    }

    public int countLeaves()
    {
        return countLeaves(overallRoot);
    }

    private int countLeaves(IntTreeNode root)
    {
        if (root == null)
        {
            return 0;
        }
        else if (root.left == null && root.right == null)
        {
            return 1;
        }
        else
        {
            return countLeaves(root.left) + countLeaves(root.right);
        }
    }
}
