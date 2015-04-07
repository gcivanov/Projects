package free.paper.toe.tac.tic.alphabetaalgorithm;

class PointsAndScores {

    int score;
    Point point;

    PointsAndScores(int score, Point point) {
        this.score = score;
        this.point = point;
    }
    
    @Override
    public boolean equals(Object obj) {
    	PointsAndScores o = (PointsAndScores) obj;
    	
    	if(this.score == o.score && this.point.equals(o.point)) 
    		return true;
    	
    	return false;
    }
}