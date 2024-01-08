import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.nio.Buffer;

public class GamePanel extends JPanel
{
    private static final int pieceWidth = 100;
    private static final int boardDimension = 8;

    private static Piece[][] board;
    private static Piece[] blackPieces;
    private static Piece[] whitePieces;

    private static int[][] startingBoard = new int[][]
            {
                    {8,9,10,11,12,10,9,8},
                    {7,7,7,7,7,7,7,7},
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0},
                    {1,1,1,1,1,1,1,1},
                    {2,3,4,5,6,4,3,2}
            };

    private static String[] imagePath = new String[]
            {
                    "D:\\Documents\\GitHub\\APComputerScience\\Chess\\images\\WhitePawn.png",
                    "D:\\Documents\\GitHub\\APComputerScience\\Chess\\images\\WhiteRook.png",
                    "D:\\Documents\\GitHub\\APComputerScience\\Chess\\images\\WhiteKnight.png",
                    "D:\\Documents\\GitHub\\APComputerScience\\Chess\\images\\WhiteBishop.png",
                    "D:\\Documents\\GitHub\\APComputerScience\\Chess\\images\\WhiteQueen.png",
                    "D:\\Documents\\GitHub\\APComputerScience\\Chess\\images\\WhiteKing.png",
                    "D:\\Documents\\GitHub\\APComputerScience\\Chess\\images\\BlackPawn.png",
                    "D:\\Documents\\GitHub\\APComputerScience\\Chess\\images\\BlackRook.png",
                    "D:\\Documents\\GitHub\\APComputerScience\\Chess\\images\\BlackKnight.png",
                    "D:\\Documents\\GitHub\\APComputerScience\\Chess\\images\\BlackBishop.png",
                    "D:\\Documents\\GitHub\\APComputerScience\\Chess\\images\\BlackQueen.png",
                    "D:\\Documents\\GitHub\\APComputerScience\\Chess\\images\\BlackKing.png"
            };

    public GamePanel()
    {
        initializeBoard();
        this.setPreferredSize(new Dimension(pieceWidth * boardDimension, pieceWidth * boardDimension));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        drawBoard(g);
        drawPieces(g);

    }

    public void Repaint()
    {
        repaint();
    }

    public static void drawBoard(Graphics g)
    {
        g.setColor(Color.PINK);

        for (int x = 0; x < boardDimension; ++x)
        {
            for (int y = 0; y < boardDimension; ++y)
            {
                if ((x % 2 == 1 || y % 2 == 1) && !(x % 2 == 1 && y % 2 == 1))
                {
                    g.setColor(Color.PINK);

                    if (clickedPoint != null && (clickedPoint.getX() == x && clickedPoint.getY() == y))
                    {
                        g.setColor(Color.BLUE);
                    }

                    g.fillRect(pieceWidth * x, pieceWidth * y, pieceWidth, pieceWidth);
                }
                else
                {
                    g.setColor(Color.WHITE);

                    if (clickedPoint != null && (clickedPoint.getX() == x && clickedPoint.getY() == y))
                    {
                        g.setColor(Color.BLUE);
                    }
                    g.fillRect(pieceWidth * x, pieceWidth * y, pieceWidth, pieceWidth);
                }
            }
        }
    }

    public static void drawPieces(Graphics g)
    {
        for (int x = 0; x < boardDimension; ++x)
        {
            for (int y = 0; y < boardDimension; ++y)
            {
                if (board[y][x] != null)
                {
                    BufferedImage newPiece = getPieceImage(board[y][x]);
                    g.drawImage(newPiece, x * pieceWidth, y * pieceWidth, pieceWidth, pieceWidth, null);
                }
            }
        }
    }

    public static BufferedImage getPieceImage(Piece piece)
    {
        BufferedImage output;
        try {
            output = ImageIO.read(new File(imagePath[piece.getIndex()]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return output;
    }

    public static void initializeBoard()
    {
        board = new Piece[boardDimension][boardDimension];
        whitePieces = new Piece[6];

        whitePieces[0] = new Piece(Piece.PieceType.PAWN, Piece.PieceColor.WHITE);
        whitePieces[1] = new Piece(Piece.PieceType.ROOK, Piece.PieceColor.WHITE);
        whitePieces[2] = new Piece(Piece.PieceType.KNIGHT, Piece.PieceColor.WHITE);
        whitePieces[3] = new Piece(Piece.PieceType.BISHOP, Piece.PieceColor.WHITE);
        whitePieces[4] = new Piece(Piece.PieceType.QUEEN, Piece.PieceColor.WHITE);
        whitePieces[5] = new Piece(Piece.PieceType.KING, Piece.PieceColor.WHITE);

        blackPieces = new Piece[6];

        blackPieces[0] = new Piece(Piece.PieceType.PAWN, Piece.PieceColor.BLACK);
        blackPieces[1] = new Piece(Piece.PieceType.ROOK, Piece.PieceColor.BLACK);
        blackPieces[2] = new Piece(Piece.PieceType.KNIGHT, Piece.PieceColor.BLACK);
        blackPieces[3] = new Piece(Piece.PieceType.BISHOP, Piece.PieceColor.BLACK);
        blackPieces[4] = new Piece(Piece.PieceType.QUEEN, Piece.PieceColor.BLACK);
        blackPieces[5] = new Piece(Piece.PieceType.KING, Piece.PieceColor.BLACK);

        for (int i = 0; i < boardDimension; ++i)
        {
            for (int y = 0; y < boardDimension; ++y)
            {
                if (startingBoard[i][y] != 0)
                {
                    if (startingBoard[i][y] <= 6) // WHITE
                    {
                        board[i][y] = whitePieces[startingBoard[i][y] - 1];
                        board[i][y].setIndex(startingBoard[i][y] - 1);
                    }
                    else // BLACK
                    {
                        board[i][y] = blackPieces[(startingBoard[i][y] - 1) % 6];
                        board[i][y].setIndex(startingBoard[i][y] - 1);
                    }
                }
            }
        }
    }

    private static Point clickedPoint;

    public void ClickPiece(int x, int y)
    {
        clickedPoint = new Point(x / pieceWidth, y / pieceWidth);
        Repaint();
    }
}
