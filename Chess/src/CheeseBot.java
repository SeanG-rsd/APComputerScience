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
    final int a7 = 8, b7 = 9, c7 = 10, d7 = 11, e7 = 12, f7 = 13, g7 = 14, h7 = 15;
    final int a6 = 16, b6 = 17, c6 = 18, d6 = 19, e6 = 20, f6 = 21, g6 = 22, h6 = 23;
    final int a5 = 24, b5 = 25, c5 = 26, d5 = 27, e5 = 28, f5 = 29, g5 = 30, h5 = 31;
    final int a4 = 32, b4 = 33, c4 = 34, d4 = 35, e4 = 36, f4 = 37, g4 = 38, h4 = 39;
    final int a3 = 40, b3 = 41, c3 = 42, d3 = 43, e3 = 44, f3 = 45, g3 = 46, h3 = 47;
    final int a2 = 48, b2 = 49, c2 = 50, d2 = 51, e2 = 52, f2 = 53, g2 = 54, h2 = 55;
    final int a1 = 56, b1 = 57, c1 = 58, d1 = 59, e1 = 60, f1 = 61, g1 = 62, h1 = 63;


    final String startBoard = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 ";

    int[] pieceCount = new int[13]; // keeps track of the count of each piece type
    int[] piecePlaces = new int[14 * 10]; // keeps track of the pieces and their place

    int[] board = new int[] // what keeps track of the board
            {
                    r, n, b, q, k, b, n, r,
                    p, p, p, p, p, p, p, p,
                    e, e, e, e, e, e, e, e,
                    e, e, e, e, e, e, e, e,
                    e, e, e, e, e, e, e, e,
                    e, e, e, e, e, e, e, e,
                    P, P, P, P, P, P, P, P,
                    R, N, B, Q, K, B, N, R,
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
    int[] kingSquares = new int[]{d1, e8};
    final int[] castlableSqaures = new int[]{e1, e8};

    // moves for pieces

    int[] knightMoves = new int[]{6, -6, 10, -10, 15, -15, 17, -17};
    int[] bishopMoves = new int[]{7, 9, -7, -9};
    int[] rookMoves = new int[]{8, -8, 1, -1};
    int[] kingMoves = new int[]{-8, 8, 1, -1, 7, 9, -7, -9};

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

    int[][] pieceRankChanges = new int[][] // how the piece's rank is expected to change for each move
            {
                    new int[0],
                    new int[0],
                    new int[] {1,-1,1,-1,2,-2,2,-2},
                    new int[] {1, 1, -1, -1},
                    new int[] {1, -1, 0, 0},
                    new int[] {-1,1,0,0,1,1,-1,-1},
                    new int[] {-1,1,0,0,1,1,-1,-1}
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
                int direction = 8 * (1 - 2 * color);
                for (int lr = -1; lr <= 1; lr += 2) {
                    int targetSquare = square + direction + lr;
                    if (!IsWithinBoard(targetSquare) || targetSquare / 8 != square / 8 + direction) break;
                    if (pieceColorMap[board[targetSquare]] == color) {
                        return true;
                    }
                }
            } else {
                int[] directions = pieceMoves[pieceType];
                for (int d = 0; d < directions.length; ++d) {
                    int targetSquare = square;
                    int lastSquare = square;
                    do {
                        targetSquare += directions[d];
                        if (!IsWithinBoard(targetSquare) || targetSquare / 8 != lastSquare / 8 + pieceRankChanges[pieceType][d])
                            break;

                        int targetPiece = board[targetSquare];
                        if (targetPiece != e) {

                            if (pieceColorMap[targetPiece] == color && pieceType == pieceTypeMap[targetPiece]){
                                return true;
                            }
                            else
                            {
                                break;
                            }
                        }
                        lastSquare = targetSquare;
                    } while (pieceType != KING && pieceType != KNIGHT);
                }
            }
        }

        return false;
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
    int enpassant = 64;
    int castle = 15;
    int fiftyMoves = 0;

    private final int mateValue = 49000;

    private final int noEnpassant = 64;
    int[][] pawnStartingRank = new int[][] // keeps track of special pawn stuff like double moves and promotions
            {
                    new int[] {48, 55},
                    new int[] {8, 15}
            };
    int[][] pawnPromotingRank = new int[][]
            {
                    new int[] {0, 7},
                    new int[] {56, 63}
            };

    public boolean IsWithinBoard(int square)
    {
        return square >= 0 && square < 64;
    } // simple boolean to see if peice is within the board

    public void AddMove(List<Integer> moveList, int move)
    {
        moveList.add(move);
    } // will do more with this in the future to keep track of a move's estimated value

    public void GetMoves(List<Integer> moveList) // gets every move from the board for the color, legal or not
    {
        for (int startSquare = 0; startSquare < board.length; startSquare++)
        //for (int piece = P; piece <= k; piece++)
        {
            if (board[startSquare] != e)
            //for (int pieceIndex = 0; pieceIndex < pieceCount[piece]; pieceIndex++)
            {
                //int startSquare = piecePlaces[piece * 10 + pieceIndex];
                int pieceType = pieceTypeMap[board[startSquare]];
                int pieceColor = pieceColorMap[board[startSquare]];

                if (pieceColor == side)
                {
                    if (pieceType == PAWN) // pawn
                    {
                        int direction = -8 * (1 - (2 * side));
                        int targetSquare = startSquare + direction;

                        if (IsWithinBoard(targetSquare) && board[targetSquare] == e) // regular moves
                        {
                            if (targetSquare >= pawnPromotingRank[side][0] && targetSquare <= pawnPromotingRank[side][1]) // promotion
                            {
                                for (int promotedPiece = QUEEN; promotedPiece >= KNIGHT; promotedPiece--)
                                {
                                    AddMove(moveList, encodeMove(startSquare, targetSquare, colorTypeToPieceMap[promotedPiece | (side << 3)], 1, 0, 0, 0));
                                }
                                // add promotion move
                            }
                            else
                            {
                                // add regular single move
                                AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 0, 0, 0, 0));
                                int doubleMoveTarget = startSquare + (direction * 2);

                                if (startSquare >= pawnStartingRank[side][0] && startSquare <= pawnStartingRank[side][1] && board[doubleMoveTarget] == e)
                                {
                                    AddMove(moveList, encodeMove(startSquare, doubleMoveTarget, 0,0,1,0,0));
                                    // add double move
                                }
                            }
                        }

                        for (int diagonal = -1; diagonal <= 1; diagonal += 2) // takes
                        {
                            targetSquare = startSquare + diagonal + direction;
                            if (!IsWithinBoard(targetSquare) || startSquare / 8 - (1 - (2 * side)) != targetSquare / 8) continue;
                            int targetPiece = board[targetSquare];

                            if (targetPiece != e && pieceColorMap[targetPiece] != pieceColor)
                            {
                                if (targetSquare >= pawnPromotingRank[side][0] && targetSquare <= pawnPromotingRank[side][1]) // promotion take
                                {
                                    for (int promotedPiece = QUEEN; promotedPiece >= KNIGHT; promotedPiece--)
                                    {
                                        AddMove(moveList, encodeMove(startSquare, targetSquare, colorTypeToPieceMap[promotedPiece | side << 3], 1, 0, 0, 0));
                                    }
                                    // add promotion move with take
                                }
                                else
                                {
                                    AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 1, 0, 0, 0));
                                    // add normal take move
                                }
                            }

                            if (targetSquare == enpassant)
                            {
                                AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 1, 0, 1, 0));
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
                                    AddMove(moveList, encodeMove(ks, ks + 2, 0, 0, 0, 0, 1));
                                    // add castling move king side
                                }
                            }
                        }

                        if (ks == castlableSqaures[side] && GetCastle(side == white ? 2 : 0, castle) == 1)
                        {
                            if (board[ks - 1] == e && board[ks - 2] == e && board[ks - 3] == e)
                            {
                                if (pieceTypeMap[board[ks - 4]] == ROOK && !isSquareAttacked(ks, 1 - side) && !isSquareAttacked(ks - 1, 1 - side)) {
                                    AddMove(moveList, encodeMove(ks, ks - 2, 0, 0, 0, 0, 1));
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
                            int lastSquare = startSquare;
                            do
                            {
                                targetSquare += directions[d];
                                if (!IsWithinBoard(targetSquare) || targetSquare / 8 != lastSquare / 8 + pieceRankChanges[pieceType][d])
                                    break;

                                int targetPiece = pieceTypeMap[board[targetSquare]];
                                if (targetPiece != e)
                                {
                                    if (pieceColorMap[board[targetSquare]] != side)
                                    {
                                        AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 1, 0, 0, 0));
                                        // add take
                                    }

                                    break;
                                }

                                AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 0, 0, 0, 0));
                                // add normal move

                                lastSquare = targetSquare;
                            } while (pieceType != KING && pieceType != KNIGHT);
                        }
                    }
                }
            }
        }

        //System.out.println(Arrays.toString(piecePlaces));
    }

    public List<Integer> GetLegalMoves() // gets the legal moves by seeing if the king is attacked after the move is made
    {
        //float start = System.nanoTime();
        List<Integer> moveList = new ArrayList<>();
        List<Integer> legalMoves = new ArrayList<>();
        GetMoves(moveList);

        for (int move : moveList)
        {
            if (MakeMove(move) == 0) continue;
            legalMoves.add(move);
            UndoMove();
        }

        Collections.sort(legalMoves);
        Collections.reverse(legalMoves);

        //System.out.println(System.nanoTime() - start);
        return legalMoves;
    }

    public String MoveToString(int move) // puts the move to a string the user can read
    {
        if (getMovePromoted(move) != 0)
        {
            return coodinates[getMoveSource(move)] + coodinates[getMoveTarget(move)] + pieceCharMap[getMovePromoted(move)];
        }
        else
        {
            return coodinates[getMoveSource(move)] + coodinates[getMoveTarget(move)];
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
                    eval += pieceValues[piece] + openingSquareTables[pieceType][piecePlaces[piece * 10 + pieceIndex]];
                }
                else
                {
                    eval += pieceValues[piece] - openingSquareTables[pieceType][flip[piecePlaces[piece * 10 + pieceIndex]]];
                }
            }
        }

        return eval;
    }

    public float MiniMax(int depth, float alpha, float beta, boolean bot) // chess bot minimax algorithm
    {
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

        if (bot)
        {
            //System.out.println("move");
            float minEval = Float.POSITIVE_INFINITY;
            for (int m : movesForASide)
            {
                MakeMove(m);

                float eval = MiniMax(depth - 1, alpha, beta, false);
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
                UndoMove();

                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha)
                {
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
        int startSquare = getMoveSource(move);
        int targetSquare = getMoveTarget(move);
        int promotedPiece = getMovePromoted(move);
        int capturedPiece = board[targetSquare];

        moveStack.add(new BoardState(move, 0, side, enpassant, castle, fiftyMoves));

        MovePiece(board[startSquare], startSquare, targetSquare);

        fiftyMoves++;

        if (getMoveCapture(move) != 0)
        {
            if (capturedPiece != e)
            {
                moveStack.get(moveStack.size() - 1).capturedPiece = capturedPiece;
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
            enpassant = noEnpassant;
        }

        if (getMovePawn(move) != 0)
        {
            if (side == white)
            {
                enpassant = targetSquare + 8;
            }
            else
            {
                enpassant = targetSquare - 8;
            }
        }
        else if (getMoveEnpassant(move) != 0)
        {
            if (side == white)
            {
                board[targetSquare + 8] = e;
                RemovePiece(p, targetSquare + 8);
            }
            else
            {
                board[targetSquare - 8] = e;
                RemovePiece(P, targetSquare - 8);
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
                RemovePiece(P, targetSquare + 8);
            }
            else
            {
                RemovePiece(p, targetSquare - 8);
            }

            AddPiece(promotedPiece, targetSquare);
        }

        if (board[targetSquare] == K || board[targetSquare] == k) kingSquares[side] = targetSquare;
        if (kingSquares[side] != castlableSqaures[side] && pieceTypeMap[board[targetSquare]] == KING)
        {
            //System.out.println("set castle for : " + side);
            castle = SetCastle(0, side == white ? 3 : 1, castle);
            castle = SetCastle(0, side == white ? 2 : 0, castle);
        }

        side = side ^ 1;

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
                AddPiece(P, targetSquare - 8);
            }
            else
            {
                AddPiece(p, targetSquare + 8);
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
        //g7h8System.out.println(castle);

        moveStack.pop();
    }

    public void MovePiece(int piece, int startSquare, int targetSquare) // moves a piece
    {
        board[targetSquare] = board[startSquare];
        board[startSquare] = e;

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
        piecePlaces[piece * 10 + pieceCount[piece]] = square;
        pieceCount[piece]++;
    }

    public CheeseBot()
    {
        InitializePieceLists();
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

        System.out.println(getMoveSource(bestMove) + "->" + getMoveTarget(bestMove));
        System.out.println((System.nanoTime() - start) / 1000000000);
        MakeMove(bestMove);

        System.out.println(Arrays.toString(pieceCount));
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
        for (int c = 0; c < 8; ++c)
        {
            System.out.println("   ---------------------------------");
            System.out.print((8-c) + "  ");
            for (int r = 0; r < 8; ++r)
            {
                if (board[8 * c + r] != e)
                {
                    System.out.print("| " + pieceCharMap[board[8 * c + r]] + " ");
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
}
