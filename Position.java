

public class Position
{
    private int row;
    private int col;
    private int value;

   Position()
    {
        this.row = 0;
        this.col = 0;
        this.value = 0;
    }

    Position(int row, int col)
    {
        this.row = row;
        this.col = col;
        this.value = -1;
    }

    Position(int value)
    {
        this.row = -1;
        this.col = -1;
        this.value = value;
    }

    Position(int row, int col, int value)
    {
        this.row = row;
        this.col = col;
        this.value= value;
    }

    int getRow()
    {
        return this.row;
    }

    int getCol()
    {
        return this.col;
    }

    int getSymbol()
    {
        return this.value;
    }

    void setRow(int row)
    {
        this.row = row;
    }

    void setCol(int col)
    {
        this.col = col;
    }

    void setSymbol(int value)
    {
        this.value = value;
    }
}
