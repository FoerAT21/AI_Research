public abstract class Piece {
    protected OrderedPair[] positions;
    public abstract OrderedPair[] generatePieceOrientations();
    public OrderedPair[] getPositions(){
        return positions;
    }
    public String toString(){
        return "Piece";
    }
}

class O extends Piece{
    public O(){
        positions = generatePieceOrientations();
    }
    @Override
    public OrderedPair[] generatePieceOrientations() {
        OrderedPair[] pos = new OrderedPair[4];
        pos[0] = new OrderedPair(0,0);
        pos[1] = new OrderedPair(0,1);
        pos[2] = new OrderedPair(1,0);
        pos[3] = new OrderedPair(1,1);
        return pos;
    }

    public String toString(){
        return "O";
    }
}

class I extends Piece{
    public I(){
        positions = generatePieceOrientations();
    }
    @Override
    public OrderedPair[] generatePieceOrientations() {
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,0);
        pos1[1] = new OrderedPair(0,1);
        pos1[2] = new OrderedPair(0,2);
        pos1[3] = new OrderedPair(0,3);

        return pos1;
    }
    public String toString(){
        return "I";
    }
}

class S extends Piece{
    public S(){
        positions = generatePieceOrientations();
    }

    @Override
    public OrderedPair[] generatePieceOrientations() {
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,0);
        pos1[1] = new OrderedPair(1,0);
        pos1[2] = new OrderedPair(1,-1);
        pos1[3] = new OrderedPair(2,-1);

        return pos1;
    }

    public String toString(){
        return "S";
    }
}

class Z extends Piece{
    public Z(){
        positions = generatePieceOrientations();
    }

    @Override
    public OrderedPair[] generatePieceOrientations() {
        OrderedPair[] positions = new OrderedPair[4];
        positions[0] = new OrderedPair(0,0);
        positions[1] = new OrderedPair(1,0);
        positions[2] = new OrderedPair(1,1);
        positions[3] = new OrderedPair(2,1);


        return positions;
    }

    public String toString(){
        return "Z";
    }
}

class L extends Piece{
    public L(){
        positions = generatePieceOrientations();
    }

    @Override
    public OrderedPair[] generatePieceOrientations() {
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,0);
        pos1[1] = new OrderedPair(0,1);
        pos1[2] = new OrderedPair(0,2);
        pos1[3] = new OrderedPair(1,2);

        return pos1;
    }

    public String toString(){
        return "L";
    }
}

class J extends Piece{
    public J(){
        positions = generatePieceOrientations();
    }

    @Override
    public OrderedPair[] generatePieceOrientations() {
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,0);
        pos1[1] = new OrderedPair(0,1);
        pos1[2] = new OrderedPair(0,2);
        pos1[3] = new OrderedPair(-1,2);

        return pos1;
    }

    public String toString(){
        return "J";
    }
}
class T extends Piece{
    public T(){
        positions = generatePieceOrientations();
    }

    @Override
    public OrderedPair[] generatePieceOrientations() {
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,0);
        pos1[1] = new OrderedPair(1,0);
        pos1[2] = new OrderedPair(2,0);
        pos1[3] = new OrderedPair(1,1);

        return pos1;
    }

    public String toString(){
        return "T";
    }
}