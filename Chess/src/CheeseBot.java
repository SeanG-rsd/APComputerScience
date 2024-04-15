import java.util.*;

public class CheeseBot {
    final int white = 0;
    final int black = 1;

    final int KING = 6;
    final int PAWN = 1;
    final int KNIGHT = 2;
    final int BISHOP = 3;
    final int ROOK = 4;
    final int QUEEN = 5;

    final int P = 1, N = 2, B = 3, R = 4, Q = 5, K = 6;
    final int p = 7, n = 8, b = 9, r = 10, q = 11, k = 12;

    final int[] colorTypeToPieceMap = new int[]{0, P, N, B, R, Q, K, 0, 0, p, n, b, r, q, k};

    final int[] pieceColorMap = new int[] {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};

    final char[] pieceCharMap = new char[] {0, 'P', 'N', 'B', 'R', 'Q', 'K', 'p', 'n', 'b', 'r', 'q', 'k'};
    final int[] pieceTypeMap = new int[] {0, PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING, PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING};

    final int e = 0;

    final int a8 = 0, b8 = 1, c8 = 2, d8 = 3, e8 = 4, f8 = 5, g8 = 6, h8 = 7;
    final int a7 = 16, b7 = 17, c7 = 18, d7 = 19, e7 = 20, f7 = 21, g7 = 22, h7 = 23;
    final int a6 = 32, b6 = 33, c6 = 34, d6 = 35, e6 = 36, f6 = 37, g6 = 38, h6 = 39;
    final int a5 = 48, b5 = 49, c5 = 50, d5 = 51, e5 = 52, f5 = 53, g5 = 54, h5 = 55;
    final int a4 = 64, b4 = 65, c4 = 66, d4 = 67, e4 = 68, f4 = 69, g4 = 70, h4 = 71;
    final int a3 = 80, b3 = 81, c3 = 82, d3 = 83, e3 = 84, f3 = 85, g3 = 86, h3 = 87;
    final int a2 = 96, b2 = 97, c2 = 98, d2 = 99, e2 = 100, f2 = 101, g2 = 102, h2 = 103;
    final int a1 = 112, b1 = 113, c1 = 114, d1 = 115, e1 = 116, f1 = 117, g1 = 118, h1 = 119;


    final String startBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 ";

    int[] pieceCount = new int[13]; // keeps track of the count of each piece type
    int[] piecePlaces = new int[13 * 10]; // keeps track of the pieces and their place

    int[] board = new int[] // what keeps track of the board
            {
                    r, n, b, q, k, b, n, r,    e,e,e,e,e,e,e,e,
                    p, p, p, p, p, p, p, p,    e,e,e,e,e,e,e,e,
                    e, e, e, e, e, e, e, e,    e,e,e,e,e,e,e,e,
                    e, e, e, e, e, e, e, e,    e,e,e,e,e,e,e,e,
                    e, e, e, e, e, e, e, e,    e,e,e,e,e,e,e,e,
                    e, e, e, e, e, e, e, e,    e,e,e,e,e,e,e,e,
                    P, P, P, P, P, P, P, P,    e,e,e,e,e,e,e,e,
                    R, N, B, Q, K, B, N, R,    e,e,e,e,e,e,e,e,
            };

    String[] coodinates = new String[] // puts a position to a coordinate
            {
                    "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
                    "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
                    "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
                    "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
                    "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
                    "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
                    "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
                    "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1",
            };

    int side = white;
    int[] kingSquares = new int[]{e1, e8};
    final int[] castlableSqaures = new int[]{e1, e8};

    // moves for pieces

    int[] knightMoves = new int[]{31, -31, 33, -33, 18, -18, 14, -14};
    int[] bishopMoves = new int[]{15, 17, -17, -15};
    int[] rookMoves = new int[]{16, -16, 1, -1};
    int[] kingMoves = new int[]{-16, 16, 1, -1, 17, 15, -17, -15};

    int[][] pieceMoves = new int[][] // all the possible moves for each piece
            {
                    new int[0], // empty
                    new int[0], // pawn
                    knightMoves,
                    bishopMoves,
                    rookMoves,
                    kingMoves, // Queen
                    kingMoves
            };

    public void InitializePieceLists() // starts the piece list
    {
        for (int piece = P; piece <= k; ++piece)
        {
            pieceCount[piece] = 0;
        }

        Arrays.fill(piecePlaces, 0);

        for (int square = 0; square < board.length; square++)
        {
            int piece = board[square];
            if (piece != e)
            {
                piecePlaces[piece * 10 + pieceCount[piece]] = square;
                pieceCount[piece]++;
            }
        }
    }


    public boolean isSquareAttacked(int square, int color) { // is the king at "square" attack by "color"
        for (int pieceType = KING; pieceType >= PAWN; pieceType--) {

            if (pieceType == PAWN) {
                int direction = -16 * (1 - 2 * color);
                for (int lr = -1; lr <= 1; lr += 2) {
                    int targetSquare = square + direction + lr;
                    if ((targetSquare & 0x88) == 0 && pieceColorMap[board[targetSquare]] == color) return true;
                }
            } else {
                int[] directions = pieceMoves[pieceType];
                for (int direction : directions) {
                    int targetSquare = square;
                    do {
                        targetSquare += direction;
                        if ((targetSquare & 0x88) != 0) break;

                        int targetPiece = board[targetSquare];
                        if (targetPiece != e) {

                            if (pieceColorMap[targetPiece] == color && pieceType == pieceTypeMap[targetPiece])
                                return true;
                            break;
                        }
                    } while (pieceType != KING && pieceType != KNIGHT);
                }
            }
        }

        return false;
    }

    private int convertToEightByEight(int hex)
    {
        return (hex + (hex & 7)) >> 1;
    }

    private int encodeMove(int source, int target, int piece, int capture, int pawn, int enpassant, int castling) { // encodes a move using binary
        return (source) |
                (target << 7) |
                (piece << 14) |
                (capture << 18) |
                (pawn << 19) |
                (enpassant << 20) |
                (castling << 21);
    }

    // decodes moves
    private int getMoveSource(int move) {
        return move & 0x7f;
    }

    private int getMoveTarget(int move) {
        return (move >> 7) & 0x7f;
    }

    private int getMovePromoted(int move) {
        return (move >> 14) & 0xf;
    }

    private int getMoveCapture(int move) {
        return (move >> 18) & 0x1;
    }

    private int getMovePawn(int move) {
        return (move >> 19) & 0x1;
    }

    private int getMoveEnpassant(int move) {
        return (move >> 20) & 0x1;
    }

    private int getMoveCastling(int move) {
        return (move >> 21) & 0x1;
    }

    // move generator
    Stack<BoardState> moveStack = new Stack<>(); // a stack to keep track of moves
    int enpassant = 128;
    int castle = 15;
    int fiftyMoves = 0;

    private final int mateValue = 49000;

    private final int noEnpassant = 128;
    int[][] pawnStartingRank = new int[][] // keeps track of special pawn stuff like double moves and promotions
            {
                    new int[] {a2, h2},
                    new int[] {a7, h7}
            };
    int[][] pawnPromotingRank = new int[][]
            {
                    new int[] {a8, h8},
                    new int[] {a1, h1}
            };

    public void AddMove(int[] moveList, int move, int count)
    {
        moveList[count - 1] = move;
    } // will do more with this in the future to keep track of a move's estimated value

    public int GetMoves(int[] moveList) // gets every move from the board for the color, legal or not
    {
        //for (int startSquare = 0; startSquare < board.length; startSquare++)
        int count = 0;
        for (int piece = P; piece <= k; piece++)
        {
            //if (board[startSquare] != e)
            for (int pieceIndex = 0; pieceIndex < pieceCount[piece]; pieceIndex++)
            {
                int startSquare = piecePlaces[piece * 10 + pieceIndex];
                int pieceType = pieceTypeMap[piece];
                int pieceColor = pieceColorMap[piece];

                if (pieceColor == side)
                {
                    if (pieceType == PAWN) // pawn
                    {
                        int direction = -16 * (1 - (2 * side));
                        int targetSquare = startSquare + direction;

                        if ((targetSquare & 0x88) == 0 && board[targetSquare] == e) // regular moves
                        {
                            if (targetSquare >= pawnPromotingRank[side][0] && targetSquare <= pawnPromotingRank[side][1]) // promotion
                            {
                                for (int promotedPiece = QUEEN; promotedPiece >= KNIGHT; promotedPiece--)
                                {
                                    count++;
                                    AddMove(moveList, encodeMove(startSquare, targetSquare, colorTypeToPieceMap[promotedPiece | (side << 3)], 1, 0, 0, 0), count);
                                }
                                // add promotion move
                            }
                            else
                            {
                                // add regular single move
                                count++;
                                AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 0, 0, 0, 0), count);
                                int doubleMoveTarget = startSquare + (direction * 2);

                                if (startSquare >= pawnStartingRank[side][0] && startSquare <= pawnStartingRank[side][1] && board[doubleMoveTarget] == e)
                                {
                                    count++;
                                    AddMove(moveList, encodeMove(startSquare, doubleMoveTarget, 0,0,1,0,0), count);
                                    // add double move
                                }
                            }
                        }

                        for (int diagonal = -1; diagonal <= 1; diagonal += 2) // takes
                        {
                            targetSquare = startSquare + diagonal + direction;
                            if ((targetSquare & 0x88) != 0) continue;
                            int targetPiece = board[targetSquare];

                            if (targetPiece != e && pieceColorMap[targetPiece] != pieceColor)
                            {
                                if (targetSquare >= pawnPromotingRank[side][0] && targetSquare <= pawnPromotingRank[side][1]) // promotion take
                                {
                                    for (int promotedPiece = QUEEN; promotedPiece >= KNIGHT; promotedPiece--)
                                    {
                                        count++;
                                        AddMove(moveList, encodeMove(startSquare, targetSquare, colorTypeToPieceMap[promotedPiece | side << 3], 1, 0, 0, 0), count);
                                    }
                                    // add promotion move with take
                                }
                                else
                                {
                                    count++;
                                    AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 1, 0, 0, 0),count );
                                    // add normal take move
                                }
                            }

                            if (targetSquare == enpassant)
                            {
                                count++;
                                AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 1, 0, 1, 0), count);
                                //moveCount++;
                                // enpassant move
                            }
                        }
                    }
                    else if (pieceType == KING) // castling
                    {
                        int ks = kingSquares[side];

                        if (ks == castlableSqaures[side] && GetCastle(side == white ? 3 : 1, castle) == 1)
                        {
                            if (board[ks + 1] == e && board[ks + 2] == e) // king side
                            {
                                if (pieceTypeMap[board[ks + 3]] == ROOK && !isSquareAttacked(ks, 1 - side) && !isSquareAttacked(ks + 1, 1 - side))
                                {
                                    count++;
                                    AddMove(moveList, encodeMove(ks, ks + 2, 0, 0, 0, 0, 1), count);
                                    // add castling move king side
                                }
                            }
                        }

                        if (ks == castlableSqaures[side] && GetCastle(side == white ? 2 : 0, castle) == 1)
                        {
                            if (board[ks - 1] == e && board[ks - 2] == e && board[ks - 3] == e)
                            {
                                if (pieceTypeMap[board[ks - 4]] == ROOK && !isSquareAttacked(ks, 1 - side) && !isSquareAttacked(ks - 1, 1 - side)) {
                                    count++;
                                    AddMove(moveList, encodeMove(ks, ks - 2, 0, 0, 0, 0, 1), count);
                                    // add castling move queen side
                                }
                            }
                        }
                    }

                    if (pieceType != PAWN)
                    {
                        int[] directions = pieceMoves[pieceType];
                        for (int d = 0; d < directions.length; ++d)
                        {
                            int targetSquare = startSquare;
                            do
                            {
                                targetSquare += directions[d];
                                if ((targetSquare & 0x88) != 0)
                                    break;

                                int targetPiece = pieceTypeMap[board[targetSquare]];
                                if (targetPiece != e)
                                {
                                    if (pieceColorMap[board[targetSquare]] != side)
                                    {
                                        count++;
                                        AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 1, 0, 0, 0), count);
                                        // add take
                                    }

                                    break;
                                }

                                count++;
                                AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 0, 0, 0, 0), count);
                                // add normal move

                            } while (pieceType != KING && pieceType != KNIGHT);
                        }
                    }
                }
            }


        }

        return count;
        //System.out.println(Arrays.toString(piecePlaces));
    }

    public List<Integer> GetLegalMoves() // gets the legal moves by seeing if the king is attacked after the move is made
    {

        int[] moveList = new int[100];

        List<Integer> legalMoves = new LinkedList<>();
        int count = GetMoves(moveList);

        for (int i = 0; i < count; i++)
        {
            if (MakeMove(moveList[i]) == 0) continue;
            legalMoves.add(moveList[i]);
            UndoMove();
        }

        return legalMoves;
    }

    public String MoveToString(int move) // puts the move to a string the user can read
    {
        if (getMovePromoted(move) != 0)
        {
            return coodinates[convertToEightByEight(getMoveSource(move))] + coodinates[convertToEightByEight(getMoveTarget(move))] + pieceCharMap[getMovePromoted(move)];
        }
        else
        {
            return coodinates[convertToEightByEight(getMoveSource(move))] + coodinates[convertToEightByEight(getMoveTarget(move))];
        }
    }

    private final int[] pieceValues = new int[] {0, 89, 308, 319, 488, 888, 20001, -92, -307, -323, -492, -888, -20002};

    private final int[] flip = new int[]{ // flips the PST from white to black
            56,  57,  58,  59,  60,  61,  62,  63,
            48,  49,  50,  51,  52,  53,  54,  55,
            40,  41,  42,  43,  44,  45,  46,  47,
            32,  33,  34,  35,  36,  37,  38,  39,
            24,  25,  26,  27,  28,  29,  30,  31,
            16,  17,  18,  19,  20,  21,  22,  23,
            8,   9,  10,  11,  12,  13,  14,  15,
            0,   1,   2,   3,   4,   5,   6,   7
    };

    // PST for all pieces, will add endgame tables later
    private final int[][] openingSquareTables = new int[][]
            {
                    new int[0],
                    new int[]{
                            0,   0,   0,   0,   0,   0,   0,   0,
                            -4,  68,  61,  47,  47,  49,  45,  -1,
                            6,  16,  25,  33,  24,  24,  14,  -6,
                            0,  -1,   9,  28,  20,   8,  -1,  11,
                            6,   4,   6,  14,  14,  -5,   6,  -6,
                            -1,  -8,  -4,   4,   2, -12,  -1,   5,
                            5,  16,  16, -14, -14,  13,  15,   8,
                            0,   0,   0,   0,   0,   0,   0,   0,
                    },
                    new int[]{
                            -55, -40, -30, -28, -26, -30, -40, -50,
                            -37, -15,   0,  -6,   4,   3, -17, -40,
                            -25,   5,  16,  12,  11,   6,   6, -29,
                            -24,   5,  21,  14,  18,   9,  11, -26,
                            -36,  -5,   9,  23,  24,  21,   2, -24,
                            -32,  -1,   4,  19,  20,   4,  11, -25,
                            -38, -22,   4,  -1,   8,  -5, -18, -34,
                            -50, -46, -32, -24, -36, -25, -34, -50,
                    },
                    new int[]{
                            -16, -15, -12,  -5, -10, -12, -10, -20,
                            -13,   5,   6,   1,  -6,  -5,   3,  -6,
                            -16,   6,  -1,  16,   7,  -1,  -6,  -5,
                            -14,  -1,  11,  14,   4,  10,  11, -13,
                            -4,   5,  12,  16,   4,   6,   2, -16,
                            -15,   4,  14,   8,  16,   4,  16, -15,
                            -5,   6,   6,   6,   3,   6,   9,  -7,
                            -14,  -4, -15,  -4,  -9,  -4, -12, -14,
                    },
                    new int[]{
                            5,  -2,   6,   2,  -2,  -6,   4,  -2,
                            8,  13,  11,  15,  11,  15,  16,   4,
                            -6,   3,   3,   6,   1,  -2,   3,  -5,
                            -10,   5,  -4,  -4,  -1,  -6,   3,  -2,
                            -4,   3,   5,  -2,   4,   1,  -5,   1,
                            0,   1,   1,  -3,   5,   6,   1,  -9,
                            -10,  -1,  -4,   0,   5,  -6,  -6,  -9,
                            -1,  -2,  -6,   9,   9,   5,   4,  -5,
                    },
                    new int[]{
                            -25,  -9, -11,  -3,  -7, -13, -10, -17,
                            -4,  -6,   4,  -5,  -1,   6,   4,  -5,
                            -8,  -5,   2,   0,   7,   6,  -4,  -5,
                            0,  -4,   7,  -1,   7,  11,   0,   1,
                            -6,   4,   7,   1,  -1,   2,  -6,  -2,
                            -15,  11,  11,  11,   4,  11,   6, -15,
                            -5,  -6,   1,  -6,   3,  -3,   3, -10,
                            -15,  -4, -13,  -8,  -3, -16,  -8, -24,
                    },
                    new int[]{
                            -30, -40, -40, -50, -50, -40, -40, -30,
                            -30, -37, -43, -49, -50, -39, -40, -30,
                            -32, -41, -40, -46, -49, -40, -46, -30,
                            -32, -38, -39, -52, -54, -39, -39, -30,
                            -20, -33, -29, -42, -44, -29, -30, -19,
                            -10, -18, -17, -20, -22, -21, -20, -13,
                            14,  18,  -1,  -1,   4,  -1,  15,  14,
                            21,  35,  11,   6,   1,  14,  32,  22,
                    },
            };

    public float EvaluateBoard() // evaulates the current state of the board
    {
        float eval = 0;
        for (int piece = P; piece <= k; piece++)
        {
            int pieceType = pieceTypeMap[piece];
            for (int pieceIndex = 0; pieceIndex < pieceCount[piece]; pieceIndex++)
            {
                if (pieceColorMap[piece] == white)
                {
                    eval += pieceValues[piece] + openingSquareTables[pieceType][convertToEightByEight(piecePlaces[piece * 10 + pieceIndex])];
                }
                else
                {
                    eval += pieceValues[piece] - openingSquareTables[pieceType][flip[convertToEightByEight(piecePlaces[piece * 10 + pieceIndex])]];
                }
            }
        }

        return eval;
    }

    // Transposition Table

    // seed
    int seed = 1804103856;
    public int randomNumber()
    {
        int number = seed;

        number ^= number << 13;
        number ^= number >> 17;
        number ^= number << 5;

        seed = number;
        return number;
    }
    int[] pieceKeys = new int[13 * 128];
    int[] castleKeys = new int[16];
    int sideKey;

    public void InitializeHashKeys()
    {
        for (int index = 0; index < 13 * 128; index++) pieceKeys[index] = randomNumber();
        for (int index = 0; index < 16; index++) castleKeys[index] = randomNumber();
        sideKey = randomNumber();
    }

    public int GenerateHashKey()
    {
        int finalKey = 0;

        for (int square = 0; square < 128; square++)
        {
            if ((square & 0x88) == 0)
            {
                int piece = board[square];
                if (piece != e) finalKey ^= pieceKeys[piece * 128] + square;
            }
        }

        if (side == white) finalKey ^= sideKey;
        if (enpassant != noEnpassant) finalKey ^= pieceKeys[enpassant];
        finalKey ^= castleKeys[castle];

        return finalKey;
    }

    // 16Mb default hash table size
    int hashEntries = 838860;

    int noHashEntry = 100000;
    int hashKey = 0;

    int searchPly = 0;
    int gamePly = 0;

    private final int HASH_EXACT = 0;
    private final int HASH_ALPHA = 1;
    private final int HASH_BETA = 2;

    Dictionary<Integer, HashEntry> hashTable;

    private void SetHashSize(int Mb)
    {
        hashTable = new Hashtable<>();

        if (Mb < 4) Mb = 4;
        if (Mb > 128) Mb = 128;

        hashEntries = (Mb * 0x100000 / 20);

    }

    private void InitializeHashTable()
    {
        SetHashSize(16);

        for (int index = 0; index < hashEntries; index++)
        {
            hashTable.put(index, new HashEntry());
        }
    }

    private int ReadHashEntry(float alpha, float beta, int depth)
    {
        HashEntry hashEntry = hashTable.get((hashKey & 0x7fffffff) % hashEntries);

        if (hashEntry.hashKey == hashKey)
        {
            if (hashEntry.depth >= depth)
            {
                int score = (int)hashEntry.score;

                if (score < -mateValue) score += searchPly;
                if (score > mateValue) score -= searchPly;

                if (hashEntry.flag == HASH_EXACT) return score;
                if ((hashEntry.flag == HASH_ALPHA) && (score <= alpha)) return (int)alpha;
                if (hashEntry.flag == HASH_BETA && score >= beta) return (int)beta;
            }
        }

        return noHashEntry;
    }

    private void WriteHashEntry(float score, int bestMove, int depth, int hashFlag)
    {
        HashEntry hashEntry = hashTable.get((hashKey & 0x7fffffff) % hashEntries);

        if (score < -mateValue) score -= searchPly;
        if (score > mateValue) score += searchPly;

        hashEntry.hashKey = hashKey;
        hashEntry.score = score;
        hashEntry.flag = hashFlag;
        hashEntry.depth = depth;
        hashEntry.bestMove = bestMove;
    }

    final int maxPly = 64;
    int followPv;
    int nodes;

    int[] pvTable = new int[maxPly * maxPly];
    int[] pvLength = new int[maxPly];

    int[] killerMoves = new int[2 * maxPly];

    int[] historyMoves = new int[13 * 128];

    int[] repetitionTable = new int[1000];

    public float MiniMax(int depth, float alpha, float beta, boolean bot) // chess bot minimax algorithm
    {
        nodes++;
        int bestMove;

        if (ReadHashEntry(alpha, beta, depth) != noHashEntry)
        {
            bestMove = ReadHashEntry(alpha, beta, depth);
            //return bestMove;
        }

        List<Integer> movesForASide = GetLegalMoves();
        boolean isInCheck = isSquareAttacked(kingSquares[side], side ^ 1);
        if (depth == 0)
        {
            return EvaluateBoard();
        }
        else if (movesForASide.isEmpty())
        {
            if (isInCheck) {
                return (mateValue + depth) * (side == white ? -1 : 1);
            }
            else return 0;
        }

        if (isInCheck) depth++;

        if (bot)
        {
            float minEval = Float.POSITIVE_INFINITY;
            for (int m : movesForASide)
            {
                MakeMove(m);

                float eval = MiniMax(depth - 1, alpha, beta, false);
                //WriteHashEntry((int)eval, m, depth, HASH_BETA);
                UndoMove();

                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha)
                {
                    break;
                }
            }
            return minEval;
        }
        else
        {

            float maxEval = Float.NEGATIVE_INFINITY;
            for (int m : movesForASide)
            {
                MakeMove(m);
                float eval = MiniMax(depth - 1, alpha, beta, true);
                //WriteHashEntry((int)eval, m, depth, HASH_ALPHA);
                UndoMove();

                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha)
                {
                    //WriteHashEntry(beta, bestMove, depth, HASH_BETA);
                    break;
                }
            }
            return maxEval;
        }
    }

    /*

    Castling is held from numbers 0-15
    It is seens as 4 number 0000-1111 in binary each digit representing a different castling spot
    First digit : white's kingside     3
    Second digit : white's queenside   2
    Third digit : black's kingside     1
    Fourth digit : black's queenside   0

     */

    public static int SetCastle(int can, int spot, int original) // sets the castle value in the intended spot
    {
        int output = 0;
        for (int i = 3; i >= 0; i--)
        {
            if (spot != i)
            {
                output += (1 << i) & original;
            }
            else
            {
                output += can >> spot;
            }
        }
        return output;
    }

    public static int GetCastle(int spot, int original)
    {
        return (original >> spot) & 1;
    } // gets the castle value for a specific caslte

    public int MakeMove(int move) // makes a move
    {
        gamePly++;
        searchPly++;

        repetitionTable[gamePly] = hashKey;

        int startSquare = getMoveSource(move);
        int targetSquare = getMoveTarget(move);
        int promotedPiece = getMovePromoted(move);
        int capturedPiece = board[targetSquare];

        moveStack.add(new BoardState(move, 0, side, enpassant, castle, fiftyMoves, hashKey));

        MovePiece(board[startSquare], startSquare, targetSquare);

        fiftyMoves++;

        if (getMoveCapture(move) != 0)
        {
            if (capturedPiece != e)
            {
                moveStack.get(moveStack.size() - 1).capturedPiece = capturedPiece;
                hashKey ^= pieceKeys[capturedPiece * 128 + targetSquare];
                RemovePiece(capturedPiece, targetSquare);
            }
            fiftyMoves = 0;
        }
        else if (board[targetSquare] == P || board[targetSquare] == p)
        {
            fiftyMoves = 0;
        }

        if (enpassant != noEnpassant)
        {
            hashKey ^= pieceKeys[enpassant];
            enpassant = noEnpassant;
        }

        if (getMovePawn(move) != 0)
        {
            if (side == white)
            {
                enpassant = targetSquare + 16;
                hashKey ^= pieceKeys[targetSquare + 16];
            }
            else
            {
                enpassant = targetSquare - 16;
                hashKey ^= pieceKeys[targetSquare - 16];
            }
        }
        else if (getMoveEnpassant(move) != 0)
        {
            if (side == white)
            {
                board[targetSquare + 16] = e;
                hashKey ^= pieceKeys[p * 128 + targetSquare + 16];
                RemovePiece(p, targetSquare + 16);
            }
            else
            {
                board[targetSquare - 16] = e;
                hashKey ^= pieceKeys[P * 128 + targetSquare - 16];
                RemovePiece(P, targetSquare - 16);
            }
        }
        else if (getMoveCastling(move) != 0)
        {
            if (targetSquare == g1)
            {
                MovePiece(R, h1, f1);
                castle = SetCastle(0, 3, castle);
            }
            else if (targetSquare == c1)
            {
                MovePiece(R, a1, d1);
                castle = SetCastle(0, 2, castle);
            }
            else if (targetSquare == g8)
            {
                MovePiece(r, h8, f8);
                castle = SetCastle(0, 1, castle);
            }
            else if (targetSquare == c8)
            {
                MovePiece(r, a8, d8);
                castle = SetCastle(0, 1, castle);
            }
        }

        if (promotedPiece != 0)
        {
            if (side == white)
            {
                hashKey ^= pieceKeys[P * 128 + targetSquare];
                RemovePiece(P, targetSquare + 16);
            }
            else
            {
                hashKey ^= pieceKeys[p * 128 + targetSquare];
                RemovePiece(p, targetSquare - 16);
            }

            AddPiece(promotedPiece, targetSquare);
        }

        hashKey ^= castleKeys[castle];

        if (board[targetSquare] == K || board[targetSquare] == k) kingSquares[side] = targetSquare;
        if (kingSquares[side] != castlableSqaures[side] && pieceTypeMap[board[targetSquare]] == KING)
        {
            //System.out.println("set castle for : " + side);
            castle = SetCastle(0, side == white ? 3 : 1, castle);
            castle = SetCastle(0, side == white ? 2 : 0, castle);
        }

        hashKey ^= castleKeys[castle];

        side = side ^ 1;
        hashKey ^= sideKey;

        if (isSquareAttacked(kingSquares[side ^ 1], side))
        {
            UndoMove();
            return 0;
        }
        else
        {
            return 1;
        }
    }

    public void UndoMove() // undos the last move made
    {
        gamePly--;
        searchPly--;

        BoardState current = moveStack.get(moveStack.size() - 1);
        int move = current.move;
        int startSquare = getMoveSource(move);
        int targetSquare = getMoveTarget(move);

        MovePiece(board[targetSquare], targetSquare, startSquare);

        if (getMoveCapture(move) != 0 && current.capturedPiece != e)
        {
            AddPiece(current.capturedPiece, targetSquare);
        }

        if (getMoveEnpassant(move) != 0)
        {
            if (side == white)
            {
                AddPiece(P, targetSquare - 16);
            }
            else
            {
                AddPiece(p, targetSquare + 16);
            }
        }
        else if (getMoveCastling(move) != 0)
        {
            switch (targetSquare) {
            case g1: MovePiece(R, f1, h1); break;
            case c1: MovePiece(R, d1, a1); break;
            case g8: MovePiece(r, f8, h8); break;
            case c8: MovePiece(r, d8, a8); break;
            }
        }
        else if (getMovePromoted(move) != 0)
        {
            if (current.side == white)
            {
                AddPiece(P, startSquare);
            }
            else
            {
                AddPiece(p, startSquare);
            }

            RemovePiece(getMovePromoted(move), startSquare);
        }

        if (board[startSquare] == K || board[startSquare] == k) kingSquares[side == white ? black : white] = startSquare;

        side = current.side;

        enpassant = current.enpassant;
        castle = current.castle;
        fiftyMoves = current.fifty;
        hashKey = current.hashKey;

        moveStack.pop();
    }

    public void MovePiece(int piece, int startSquare, int targetSquare) // moves a piece
    {
        board[targetSquare] = board[startSquare];
        board[startSquare] = e;

        hashKey ^= pieceKeys[piece * 128 + startSquare];
        hashKey ^= pieceKeys[piece * 128 + startSquare];

        for (int pieceIndex = 0; pieceIndex < pieceCount[piece]; pieceIndex++)
        {
            if (piecePlaces[piece * 10 + pieceIndex] == startSquare)
            {
                piecePlaces[piece * 10 + pieceIndex] = targetSquare;
                break;
            }
        }
    }

    public void RemovePiece(int piece, int square) // removes a piece
    {
        int capturedIndex = -1;
        for (int pieceIndex = 0; pieceIndex < pieceCount[piece]; pieceIndex++)
        {
            if (piecePlaces[piece * 10 + pieceIndex] == square)
            {
                capturedIndex = pieceIndex;
                break;
            }
        }

        pieceCount[piece]--;
        piecePlaces[piece * 10 + capturedIndex] = 0;

        for (int pieceIndex = capturedIndex + 1; pieceIndex <= pieceCount[piece]; pieceIndex++)
        {
            if (piecePlaces[piece * 10 + pieceIndex] != 0)
            {
                piecePlaces[piece * 10 + pieceIndex - 1] = piecePlaces[piece * 10 + pieceIndex];
                piecePlaces[piece * 10 + pieceIndex] = 0;
            }
            else break;
        }
    }

    public void AddPiece(int piece, int square) // adds a piece
    {
        board[square] = piece;

        hashKey ^= pieceKeys[piece * 128 + square];

        piecePlaces[piece * 10 + pieceCount[piece]] = square;
        pieceCount[piece]++;
    }

    public CheeseBot()
    {
        InitializeHashKeys();
        hashKey = GenerateHashKey();
        InitializePieceLists();
        InitializeHashTable();
    }

    public void MakeBestMove() // chess bot algorithm to find the best move for the bot
    {
        int DEPTH = 6;
        List<Integer> possibleMoves = GetLegalMoves();
        float start = System.nanoTime();
        int bestMove = -1;
        float bestEval = Float.POSITIVE_INFINITY;

        for (int i : possibleMoves)
        {
            MakeMove(i);

            float eval = MiniMax(DEPTH - 1, Float.NEGATIVE_INFINITY, bestEval, false);
            System.out.println(eval);
            if (eval < bestEval)
            {
                bestEval = eval;
                bestMove = i;
            }
            System.out.println(getMoveSource(i) + "->" + getMoveTarget(i));

            UndoMove();
        }

        System.out.println((System.nanoTime() - start) / 1000000000);
        System.out.println(getMoveSource(bestMove) + "->" + getMoveTarget(bestMove));

        MakeMove(bestMove);

        System.out.println(Arrays.toString(pieceCount));
        System.out.println(nodes);
    }

    public CheeseBot(String startBoard)
    {
        //InitializeBoard(startBoard);
        InitializePieceLists();
    }

    public void PrintBoard() // outputs the board to the console
    {
        System.out.println();
        System.out.println(EvaluateBoard());
        System.out.println(Arrays.toString(piecePlaces));
        for (int c = 0; c < 8; ++c)
        {
            System.out.println("   ---------------------------------");
            System.out.print((8-c) + "  ");
            for (int r = 0; r < 8; ++r)
            {
                if (board[16 * c + r] != e)
                {
                    System.out.print("| " + pieceCharMap[board[16 * c + r]] + " ");
                }
                else
                {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");

        }
        System.out.println("   ---------------------------------");
        System.out.println("     A   B   C   D   E   F   G   H  ");
    }

    public static void InitializeBoard(String coded) // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
    {
        // takes the given code and decodes it into an array of chars for the board to start in any position
        char[] codedBoard = new char[64];
        char[] codeReader = coded.toCharArray();

        int row = 0;
        int column = 0;
        for (char c : codeReader) {
            if (c != '/') {
                if (Character.isDigit(c)) {
                    int count = c - '0';
                    for (int i = 0; i < count; ++i) {
                        if (column < 8) {
                            column++;
                        }
                    }
                } else {
                    codedBoard[row * 8 + column] = c;
                    column++;
                }
            } else {
                row++;
                column = 0;
            }
        }

        for (char c : codedBoard)
        {

        }
    }

    public static void main(String[] args)
    {
        System.out.println((16 + (16 & 7)) >> 1);
    }
}
