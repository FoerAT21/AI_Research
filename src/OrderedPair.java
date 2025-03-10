/**
 * @author Andrew Foerst
 */
public class OrderedPair {
    private int x;
    private int y;
    public OrderedPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(OrderedPair other){
        return other.x == this.x && other.y == this.y;
    }

    public String toString(){
        return "("+y+","+x+")";
    }
}
