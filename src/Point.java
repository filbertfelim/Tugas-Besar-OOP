public class Point {
    private int x;
    private int y;

    public Point()
    {
        x = 0;
        y = 0;
    }

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getX()
    {
        return x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getY()
    {
        return y;
    }

    public void move(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public String toString()
    {
        return "(" + getX() + "," + getY() + ")";
    }

    public static double distance(Point awal, Point akhir)
    {
        return(Math.sqrt(Math.pow((awal.getX()-akhir.getX()),2)+Math.pow((awal.getY()-akhir.getY()),2)));
    }
}
