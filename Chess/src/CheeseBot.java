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

    final int[] mapFromOptimized = new int[]{0, K, P, N, B, R, Q, 0, 0, k, p, n, b, r, q};

    final int[] pieceColorMap = new int[] {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
    final int[] pieceTypeMap = new int[] {0, PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING, PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING};

    final int e = 0;

    int a8 = 0, b8 = 1, c8 = 2, d8 = 3, e8 = 4, f8 = 5, g8 = 6, h8 = 7;
    int a7 = 8, b7 = 9, c7 = 10, d7 = 11, e7 = 12, f7 = 13, g7 = 14, h7 = 15;
    int a6 = 16, b6 = 17, c6 = 18, d6 = 19, e6 = 20, f6 = 21, g6 = 22, h6 = 23;
    int a5 = 24, b5 = 25, c5 = 26, d5 = 27, e5 = 28, f5 = 29, g5 = 30, h5 = 31;
    int a4 = 32, b4 = 33, c4 = 34, d4 = 35, e4 = 36, f4 = 37, g4 = 38, h4 = 39;
    int a3 = 40, b3 = 41, c3 = 42, d3 = 43, e3 = 44, f3 = 45, g3 = 46, h3 = 47;
    int a2 = 48, b2 = 49, c2 = 50, d2 = 51, e2 = 52, f2 = 53, g2 = 54, h2 = 55;
    int a1 = 56, b1 = 57, c1 = 58, d1 = 59, e1 = 60, f1 = 61, g1 = 62, h1 = 63;

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

    int side = black;
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
            System.out.println(piece);

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
    List<Integer> moveStack = new ArrayList<>();
    int enpassant = 120;
    int castle = 15;

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

    public void GetMoves()
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
                                    System.out.println("move");
                                    moveCount++;
                                }
                                // add promotion move
                            }
                            else
                            {
                                // add regular single move
                                System.out.println("pawn reg");
                                moveCount++;
                                int doubleMoveTarget = startSquare + (direction * 2);

                                if (startSquare >= pawnStartingRank[side][0] && startSquare <= pawnStartingRank[side][1] && board[doubleMoveTarget] == e)
                                {
                                    System.out.println("pawn double");
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
                                        System.out.println("move");
                                        moveCount++;
                                    }
                                    // add promotion move with take
                                }
                                else
                                {
                                    System.out.println("move");
                                    moveCount++;
                                    // add normal take move
                                }
                            }

                            if (targetSquare == enpassant)
                            {
                                System.out.println("move");
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
                                System.out.println("move");
                                moveCount++;
                                // add castling move king side
                            }
                        }

                        if (board[ks - 1] == e && board[ks - 2] == e && board[ks - 3] == e)
                        {
                            if (!isSquareAttacked(ks, 1 - side) && !isSquareAttacked(ks - 1, 1 - side))
                            {
                                System.out.println("move");
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
                                        System.out.println("move : " + lastSquare + "->" + targetSquare);
                                        moveCount++;
                                        // add take
                                    }

                                    break;
                                }

                                System.out.println("move : " + lastSquare + "->" + targetSquare);
                                moveCount++;
                                // add normal move

                                lastSquare = targetSquare;
                            } while (pieceType != KING && pieceType != KNIGHT);
                        }
                    }
                }
            }
        }

        System.out.println(moveCount);
    }

    public void GetLegalMoves()
    {

    }

    public void EvaluateBoard()
    {

    }

    public void MiniMax()
    {

    }

    public void MakeMove(int move)
    {

    }

    public void UndoMove()
    {

    }



    public CheeseBot()
    {

    }

    public static void main(String[] args)
    {
        boolean move = (55 & 63) == 0x00;
        System.out.println(0x04);
    }
}
