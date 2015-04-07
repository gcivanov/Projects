package free.paper.toe.tac.tic.alphabetaalgorithm;


public class Point {

    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object obj) {
    	Point point = (Point) obj;
    	if(this.x == point.x && this.y == point.y)
    		return true;
    	return false;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
