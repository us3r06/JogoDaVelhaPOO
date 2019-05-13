package com.hdanyllo.jogodavelha;


public class TicTacToe {
    private Moves[] doneMoves = {null, null, null, null, null, null, null, null, null};

    public boolean hasFinished() {
        if(hasWinner()) {
            return true;
        }

        for(Moves move : doneMoves) {
            if (move == null) {
                return false;
            }
        }

        return true;
    }

    public boolean hasWinner() {
        try {
            getWinner();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Moves getWinner() throws NoWinnerException {
        int[][] movesToWin = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        for(int[] moves : movesToWin) {
            if (doneMoves[moves[0]] == doneMoves[moves[1]] && doneMoves[moves[0]] == doneMoves[moves[2]]) {
                if (doneMoves[moves[0]] == Moves.O) {
                    return Moves.O;
                } else if (doneMoves[moves[0]] == Moves.X) {
                    return Moves.X;
                }
            }
        }
        throw new NoWinnerException("The should be a winner");
    }

    public enum Moves {
        X, O;
    }

    private Moves nextMove = Moves.X;

    public Moves getNextMove() {
        return nextMove;
    }

    public void move(int position) throws SamePositionMoveException, PositionBeyondBoundariesException {
        if(position < 0 || position > 8) {
            throw new PositionBeyondBoundariesException("Position cannot be grater than 8");
        }

        if(doneMoves[position] != null) {
            throw new SamePositionMoveException("Cannot pick same position twice");
        }

        doneMoves[position] = nextMove;

        if(nextMove == Moves.O) {
            nextMove = Moves.X;
        } else {
            nextMove = Moves.O;
        }
    }

}
