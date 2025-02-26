public abstract class Piece {
    protected OrderedPair[] positions;
    public abstract OrderedPair[] generatePieceOrienations();
    public OrderedPair[] getPositions(){
        return positions;
    }
}

class O extends Piece{
    public O(){
        positions = generatePieceOrienations();
    }
    @Override
    public OrderedPair[] generatePieceOrienations() {
        OrderedPair[] pos = new OrderedPair[4];
        pos[0] = new OrderedPair(0,0);
        pos[1] = new OrderedPair(0,1);
        pos[2] = new OrderedPair(1,0);
        pos[3] = new OrderedPair(1,1);
        return pos;
    }
}

class I extends Piece{
    public I(){
        positions = generatePieceOrienations();
    }
    @Override
    public OrderedPair[] generatePieceOrienations() {
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,0);
        pos1[1] = new OrderedPair(0,1);
        pos1[2] = new OrderedPair(0,2);
        pos1[3] = new OrderedPair(0,3);

        return pos1;
    }
}

class S extends Piece{
    public S(){
        positions = generatePieceOrienations();
    }

    @Override
    public OrderedPair[] generatePieceOrienations() {
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,1);
        pos1[1] = new OrderedPair(1,1);
        pos1[2] = new OrderedPair(1,0);
        pos1[3] = new OrderedPair(2,0);
        return pos1;
    }
}

class Z extends Piece{
    public Z(){
        positions = generatePieceOrienations();
    }

    @Override
    public OrderedPair[] generatePieceOrienations() {
        OrderedPair[] positions = new OrderedPair[4];
        positions[0] = new OrderedPair(0,0);
        positions[1] = new OrderedPair(1,0);
        positions[2] = new OrderedPair(1,1);
        positions[3] = new OrderedPair(2,1);


        return positions;
    }
}

class L extends Piece{
    public L(){
        positions = generatePieceOrienations();
    }

    @Override
    public OrderedPair[] generatePieceOrienations() {
        // Normal L
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,0);
        pos1[1] = new OrderedPair(0,1);
        pos1[2] = new OrderedPair(0,2);
        pos1[3] = new OrderedPair(1,2);

        return pos1;
    }
}

class J extends Piece{
    public J(){
        positions = generatePieceOrienations();
    }

    @Override
    public OrderedPair[] generatePieceOrienations() {
        // Normal L
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(1,0);
        pos1[1] = new OrderedPair(1,1);
        pos1[2] = new OrderedPair(1,2);
        pos1[3] = new OrderedPair(0,2);

        return pos1;
    }
}
class T extends Piece{
    public T(){
        positions = generatePieceOrienations();
    }

    @Override
    public OrderedPair[] generatePieceOrienations() {
        // Normal L
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,0);
        pos1[1] = new OrderedPair(1,0);
        pos1[2] = new OrderedPair(2,0);
        pos1[3] = new OrderedPair(1,1);


        return pos1;
    }
}