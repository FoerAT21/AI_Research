public abstract class Piece {
    protected OrderedPair[][] positions;
    public abstract OrderedPair[][] generatePieceOrienations();
    public OrderedPair[][] getPositions(){
        return positions;
    }
}

class O extends Piece{
    public O(){
        positions = generatePieceOrienations();
    }
    @Override
    public OrderedPair[][] generatePieceOrienations() {
        OrderedPair[] pos = new OrderedPair[4];
        pos[0] = new OrderedPair(0,0);
        pos[1] = new OrderedPair(0,1);
        pos[2] = new OrderedPair(1,0);
        pos[3] = new OrderedPair(1,1);
        return new OrderedPair[][]{pos};
    }
}

class I extends Piece{
    public I(){
        positions = generatePieceOrienations();
    }
    @Override
    public OrderedPair[][] generatePieceOrienations() {
        OrderedPair[] pos1 = new OrderedPair[4];
        OrderedPair[] pos2 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,0);
        pos2[0] = new OrderedPair(0,0);

        pos1[1] = new OrderedPair(0,1);
        pos2[1] = new OrderedPair(1,0);

        pos1[2] = new OrderedPair(0,2);
        pos2[2] = new OrderedPair(2,0);

        pos1[3] = new OrderedPair(0,3);
        pos2[3] = new OrderedPair(3,0);

        return new OrderedPair[][]{pos1, pos2};
    }
}

class S extends Piece{
    public S(){
        positions = generatePieceOrienations();
    }

    @Override
    public OrderedPair[][] generatePieceOrienations() {
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,1);
        pos1[1] = new OrderedPair(1,1);
        pos1[2] = new OrderedPair(1,0);
        pos1[3] = new OrderedPair(2,0);

        OrderedPair[] pos2 = new OrderedPair[4];
        pos2[0] = new OrderedPair(0,0);
        pos2[1] = new OrderedPair(0,1);
        pos2[2] = new OrderedPair(1,1);
        pos2[3] = new OrderedPair(1,2);

        return new OrderedPair[][]{pos1, pos2};
    }
}

class Z extends Piece{
    public Z(){
        positions = generatePieceOrienations();
    }

    @Override
    public OrderedPair[][] generatePieceOrienations() {
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,0);
        pos1[1] = new OrderedPair(1,0);
        pos1[2] = new OrderedPair(1,1);
        pos1[3] = new OrderedPair(2,1);

        OrderedPair[] pos2 = new OrderedPair[4];
        pos2[0] = new OrderedPair(1,0);
        pos2[1] = new OrderedPair(1,1);
        pos2[2] = new OrderedPair(0,1);
        pos2[3] = new OrderedPair(0,2);

        return new OrderedPair[][]{pos1, pos2};
    }
}

class L extends Piece{
    public L(){
        positions = generatePieceOrienations();
    }

    @Override
    public OrderedPair[][] generatePieceOrienations() {
        // Normal L
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,0);
        pos1[1] = new OrderedPair(0,1);
        pos1[2] = new OrderedPair(0,2);
        pos1[3] = new OrderedPair(1,2);

        // Rotated 90 degrees
        OrderedPair[] pos2 = new OrderedPair[4];
        pos2[0] = new OrderedPair(0,0);
        pos2[1] = new OrderedPair(1,0);
        pos2[2] = new OrderedPair(2,0);
        pos2[3] = new OrderedPair(0,1);

        // Rotate 180 degrees
        OrderedPair[] pos3 = new OrderedPair[4];
        pos3[0] = new OrderedPair(0,0);
        pos3[1] = new OrderedPair(1,0);
        pos3[2] = new OrderedPair(1,1);
        pos3[3] = new OrderedPair(1,2);

        // Rotate 270
        OrderedPair[] pos4 = new OrderedPair[4];
        pos3[0] = new OrderedPair(0,1);
        pos3[1] = new OrderedPair(1,1);
        pos3[2] = new OrderedPair(2,1);
        pos3[3] = new OrderedPair(2,0);

        return new OrderedPair[][]{pos1, pos2, pos3,pos4};
    }
}

class J extends Piece{
    public J(){
        positions = generatePieceOrienations();
    }

    @Override
    public OrderedPair[][] generatePieceOrienations() {
        // Normal L
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(1,0);
        pos1[1] = new OrderedPair(1,1);
        pos1[2] = new OrderedPair(1,2);
        pos1[3] = new OrderedPair(0,2);

        // Rotated 90 degrees
        OrderedPair[] pos2 = new OrderedPair[4];
        pos2[0] = new OrderedPair(0,0);
        pos2[1] = new OrderedPair(0,1);
        pos2[2] = new OrderedPair(1,1);
        pos2[3] = new OrderedPair(2,1);

        // Rotate 180 degrees
        OrderedPair[] pos3 = new OrderedPair[4];
        pos3[0] = new OrderedPair(0,0);
        pos3[1] = new OrderedPair(0,1);
        pos3[2] = new OrderedPair(0,2);
        pos3[3] = new OrderedPair(1,0);

        // Rotate 270
        OrderedPair[] pos4 = new OrderedPair[4];
        pos3[0] = new OrderedPair(0,0);
        pos3[1] = new OrderedPair(1,0);
        pos3[2] = new OrderedPair(2,0);
        pos3[3] = new OrderedPair(2,1);

        return new OrderedPair[][]{pos1, pos2, pos3,pos4};
    }
}
class T extends Piece{
    public T(){
        positions = generatePieceOrienations();
    }

    @Override
    public OrderedPair[][] generatePieceOrienations() {
        // Normal L
        OrderedPair[] pos1 = new OrderedPair[4];
        pos1[0] = new OrderedPair(0,0);
        pos1[1] = new OrderedPair(1,0);
        pos1[2] = new OrderedPair(2,0);
        pos1[3] = new OrderedPair(1,1);

        // Rotated 90 degrees
        OrderedPair[] pos2 = new OrderedPair[4];
        pos2[0] = new OrderedPair(1,0);
        pos2[1] = new OrderedPair(1,1);
        pos2[2] = new OrderedPair(1,2);
        pos2[3] = new OrderedPair(0,1);

        // Rotate 180 degrees
        OrderedPair[] pos3 = new OrderedPair[4];
        pos3[0] = new OrderedPair(1,0);
        pos3[1] = new OrderedPair(0,1);
        pos3[2] = new OrderedPair(1,1);
        pos3[3] = new OrderedPair(2,1);

        // Rotate 270
        OrderedPair[] pos4 = new OrderedPair[4];
        pos3[0] = new OrderedPair(0,0);
        pos3[1] = new OrderedPair(0,1);
        pos3[2] = new OrderedPair(0,2);
        pos3[3] = new OrderedPair(1,1);

        return new OrderedPair[][]{pos1, pos2, pos3,pos4};
    }
}