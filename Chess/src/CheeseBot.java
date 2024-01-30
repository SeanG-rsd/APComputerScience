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

    final int[] mapFromOptimized = new int[]{0, P, N, B, R, Q, K, 0, 0, p, n, b, r, q, k};

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

    int[] board = new int[]
            {
                    r, n, b, q, k, b, n, r,
                    p, p, p, p, p, p, p, p,
                    e, e, e, e, e, e, e, e,
                    e, e, e, e, e, e, e, e,
                    e, e, e, e, e, e, e, e,
                    e, e, e, e, e, e, e, e,
                    P, P, P, P, P, P, P, P,
                    R, N, B, Q, K, B, N, R
            };

    String[] coodinates = new String[]
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

    // moves for pieces

    int[] knightMoves = new int[]{6, -6, 10, -10, 15, -15, 17, -17};
    int[] bishopMoves = new int[]{7, 9, -7, -9};
    int[] rookMoves = new int[]{8, -8, 1, -1};
    int[] kingMoves = new int[]{-8, 8, 1, -1, 7, 9, -7, -9};

    int[][] pieceMoves = new int[][]
            {
                    new int[0], // empty
                    new int[0], // pawn
                    knightMoves,
                    bishopMoves,
                    rookMoves,
                    kingMoves, // Queen
                    kingMoves
            };

    int[][] pieceRankChanges = new int[][]
            {
                    new int[0],
                    new int[0],
                    new int[] {1,-1,1,-1,2,-2,2,-2},
                    new int[] {1, 1, -1, -1},
                    new int[] {1, -1, 1, -1},
                    new int[] {-1,1,0,0,1,1,-1,-1},
                    new int[] {-1,1,0,0,1,1,-1,-1}
            };


    public boolean isSquareAttacked(int square, int color) {
        for (int pieceType = KING; pieceType >= PAWN; pieceType--) {
            int piece = pieceType | (color << 3);
            //System.out.println(piece);

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
                        if (!IsWithinBoard(targetSquare) || targetSquare / 8 + pieceRankChanges[pieceType][d] != lastSquare / 8) break;

                        int targetPiece = board[targetSquare];
                        if (targetPiece != e) {
                            if (targetPiece == mapFromOptimized[piece]) return true;
                            break;
                        }
                        lastSquare = targetSquare;
                    } while (pieceType != KING && pieceType != KNIGHT);
                }
            }
        }

        return false;
    }

    private int encodeMove(int source, int target, int piece, int capture, int pawn, int enpassant, int castling) {
        return (source) |
                (target << 7) |
                (piece << 14) |
                (capture << 18) |
                (pawn << 19) |
                (enpassant << 20) |
                (castling << 21);
    }

    // decode move
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
    Stack<BoardState> moveStack = new Stack<>();
    int enpassant = 64;
    int castle = 15;
    int fiftyMoves = 0;

    private final int noEnpassant = 64;

    int[][] castlingSide = new int[][]
            {
              new int[] {1, 2},
              new int[] {4, 8}
            };

    int[][] pawnStartingRank = new int[][]
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
    }

    public void AddMove(List<Integer> moveList, int move)
    {
        moveList.add(move);
    }

    public void GetMoves(List<Integer> moveList)
    {
        int moveCount = 0;
        for (int startSquare = 0; startSquare < board.length; ++startSquare)
        {
            if (board[startSquare] != e)
            {
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
                                    AddMove(moveList, encodeMove(startSquare, targetSquare, mapFromOptimized[promotedPiece | (side << 3)], 1, 0, 0, 0));
                                    moveCount++;
                                }
                                // add promotion move
                            }
                            else
                            {
                                // add regular single move
                                AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 0, 0, 0, 0));
                                moveCount++;
                                int doubleMoveTarget = startSquare + (direction * 2);

                                if (startSquare >= pawnStartingRank[side][0] && startSquare <= pawnStartingRank[side][1] && board[doubleMoveTarget] == e)
                                {
                                    AddMove(moveList, encodeMove(startSquare, doubleMoveTarget, 0,0,1,0,0));
                                    moveCount++;
                                    // add double move
                                }
                            }
                        }

                        for (int diagonal = -1; diagonal <= 1; diagonal += 2) // takes
                        {
                            targetSquare = startSquare + diagonal + direction;
                            if (!IsWithinBoard(targetSquare) || startSquare / 8 + direction != targetSquare / 8) continue;
                            int targetPiece = board[targetSquare];

                            if (targetPiece != e && pieceColorMap[targetPiece] != pieceColor)
                            {
                                if (targetSquare >= pawnPromotingRank[side][0] && targetSquare <= pawnPromotingRank[side][1]) // promotion take
                                {
                                    for (int promotedPiece = QUEEN; promotedPiece >= KNIGHT; promotedPiece--)
                                    {
                                        AddMove(moveList, encodeMove(startSquare, targetSquare, mapFromOptimized[promotedPiece | side << 3], 1, 0, 0, 0));
                                        moveCount++;
                                    }
                                    // add promotion move with take
                                }
                                else
                                {
                                    AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 1, 0, 0, 0));
                                    moveCount++;
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

                        if (board[ks + 1] == e && board[ks + 2] == e) // king side
                        {
                            if (!isSquareAttacked(ks, 1 - side) && !isSquareAttacked(ks + 1, 1 - side))
                            {
                                AddMove(moveList, encodeMove(ks, ks + 2, 0, 0, 0, 0, 1));
                                moveCount++;
                                // add castling move king side
                            }
                        }

                        if (board[ks - 1] == e && board[ks - 2] == e && board[ks - 3] == e)
                        {
                            if (!isSquareAttacked(ks, 1 - side) && !isSquareAttacked(ks - 1, 1 - side))
                            {
                                AddMove(moveList, encodeMove(ks, ks - 2, 0, 0, 0, 0, 1));
                                moveCount++;
                                // add castling move queen side
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
                                        moveCount++;
                                        // add take
                                    }

                                    break;
                                }

                                AddMove(moveList, encodeMove(startSquare, targetSquare, 0, 0, 0, 0, 0));
                                moveCount++;
                                // add normal move

                                lastSquare = targetSquare;
                            } while (pieceType != KING && pieceType != KNIGHT);
                        }
                    }
                }
            }
        }
    }

    public List<Integer> GetLegalMoves()
    {
        List<Integer> moveList = new ArrayList<>();
        List<Integer> legalMoves = new ArrayList<>();
        GetMoves(moveList);

        for (int move : moveList)
        {
            if (MakeMove(move) == 0) continue;
            legalMoves.add(move);
            System.out.println(MoveToString(move));
            UndoMove();
        }

        return legalMoves;
    }

    public String MoveToString(int move)
    {
        if (getMovePromoted(move) != 0)
        {
            return coodinates[getMoveSource(move)] + coodinates[getMoveTarget(move)] + coodinates[getMovePromoted(move)];
        }
        else
        {
            return coodinates[getMoveSource(move)] + coodinates[getMoveTarget(move)];
        }
    }

    public void EvaluateBoard()
    {

    }

    public void MiniMax()
    {

    }

    public int MakeMove(int move)
    {
        int startSquare = getMoveSource(move);
        int targetSquare = getMoveTarget(move);
        int promotedPiece = getMovePromoted(move);
        int capturedPiece = board[targetSquare];

        moveStack.add(new BoardState(move, 0, side, enpassant, castle, fiftyMoves));

        MovePiece(startSquare, targetSquare);

        fiftyMoves++;

        if (getMoveCapture(move) != 0)
        {
            if (capturedPiece != e)
            {
                moveStack.get(moveStack.size() - 1).capturedPiece = capturedPiece;
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
            }
            else
            {
                board[targetSquare - 8] = e;
            }
        }
        else if (getMoveCastling(move) != 0)
        {
            switch (targetSquare){
                case g1: MovePiece(h1, f1); break;
                case c1: MovePiece(a1, d1); break;
                case g8: MovePiece(h8, f8); break;
                case c8: MovePiece(a8, d8); break;
            }
        }

        if (promotedPiece != 0)
        {
            board[targetSquare] = promotedPiece;
        }

        if (board[targetSquare] == K || board[targetSquare] == k) kingSquares[side] = targetSquare;

        side = side == white ? black : white;

        if (isSquareAttacked(kingSquares[side == white ? black : white], side))
        {
            UndoMove();
            return 0;
        }
        else
        {
            return 1;
        }
    }

    public void UndoMove()
    {
        int moveIndex = moveStack.size() - 1;
        int move = moveStack.get(moveIndex).move;
        int startSquare = getMoveSource(move);
        int targetSquare = getMoveTarget(move);

        MovePiece(targetSquare, startSquare);

        if (getMoveCapture(move) != 0)
        {
            board[targetSquare] = moveStack.get(moveIndex).capturedPiece;
        }

        if (getMoveEnpassant(move) != 0)
        {
            if (side == white)
            {
                board[targetSquare - 8] = P;
            }
            else
            {
                board[targetSquare + 8] = p;
            }
        }
        else if (getMoveCastling(move) != 0)
        {
            switch (targetSquare) {
            case g1: MovePiece(f1, h1); break;
            case c1: MovePiece(d1, a1); break;
            case g8: MovePiece(f8, h8); break;
            case c8: MovePiece(d8, a8); break;
            }
        }
        else if (getMovePromoted(move) != 0)
        {
            if (side == white)
            {
                board[startSquare] = P;
            }
            else
            {
                board[startSquare] = p;
            }
        }

        if (board[startSquare] == K || board[startSquare] == k) kingSquares[side == white ? black : white] = startSquare;

        side = moveStack.get(moveIndex).side;

        enpassant = moveStack.get(moveIndex).enpassant;
        castle = moveStack.get(moveIndex).castle;
        fiftyMoves = moveStack.get(moveIndex).fifty;

        moveStack.pop();
    }

    public void MovePiece(int startSquare, int targetSquare)
    {
        board[targetSquare] = board[startSquare];
        board[startSquare] = e;
    }

    public CheeseBot()
    {

    }

    public CheeseBot(String startBoard)
    {
        InitializeBoard(startBoard);
    }

    public void PrintBoard() // outputs the board to the console
    {
        System.out.println();
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
        // takes the given code and decodes it into an array of chars
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
