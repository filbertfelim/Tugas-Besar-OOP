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

    public int distance(Point point)
    { 
        double val = Math.sqrt(Math.pow((this.getX()-point.getX()),2)+Math.pow((this.getY()-point.getY()),2));
        float floatval = (float) val;
        int newval = Math.round(floatval);
        return newval;
    }
}
