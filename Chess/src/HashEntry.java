public class HashEntry
{
    public int hashKey;
    public int depth;
    public int flag;
    public float score;
    public int bestMove;

    public HashEntry()
    {
        hashKey = 0;
        depth = 0;
        flag = 0;
        score = 0;
        bestMove = 0;
    }
}
