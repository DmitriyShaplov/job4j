package ru.job4j.tictactoe;

/**
 * @author shaplov
 * @since 25.04.2019
 */
public class SimpleGame implements Game {

    private final BoardCreator creator;

    private final Input input;

    private final Logic logic;

    private final LogicAI logicAI;

    private final Painter painter;

    private boolean playerTurn = true;
    private boolean playerOnX = true;

    public SimpleGame(BoardCreator creator, Input input, Logic logic, LogicAI logicAI, Painter painter) {
        this.creator = creator;
        this.input = input;
        this.logic = logic;
        this.logicAI = logicAI;
        this.painter = painter;
    }

    /**
     * Prepare engine for game.
     */
    private boolean init(boolean needInit) {
        boolean endInit = false;
        boolean exit = false;
        this.painter.drawHelp();
        if (needInit) {
            do {
                String commandString = this.input.getInitCommand();
                if ("SIZE".equals(commandString.toUpperCase())) {
                    int size = this.input.getNewSize();
                    if (size > 0) {
                        this.creator.setSize(size);
                    } else {
                        exit = true;
                    }
                } else if ("SIDE".equals(commandString.toUpperCase())) {
                    String side = this.input.getSide();
                    if ("X".equals(side.toUpperCase())) {
                        this.playerOnX = true;
                    } else if ("O".equals(side.toUpperCase())) {
                        this.playerOnX = false;
                    } else if ("EXIT".equals(side.toUpperCase())) {
                        exit = true;
                    }
                } else if ("START".equals(commandString.toUpperCase())) {
                    endInit = true;
                } else if ("EXIT".equals(commandString.toUpperCase())) {
                    endInit = true;
                    exit = true;
                }
            } while (!endInit);
        }
        if (!exit) {
            this.playerTurn = this.playerOnX;
            Cell[][] cells = this.creator.createBoard();
            this.painter.loadBoard(cells);
            this.logic.loadBoard(cells);
            this.logicAI.loadBoard(cells);
            this.logicAI.setMarkX(!this.playerOnX);
            this.painter.drawBoard();
        }
        return exit;
    }

    /**
     * Main game loop.
     */
    @Override
    public void start() {
        boolean exit = init(true);
        String commandStr = "";
        while (!exit) {
            if (!this.playerTurn) {
                if (this.logicAI.makeMove()) {
                    this.playerTurn = true;
                } else {
                    throw new IllegalStateException("Something wrong!");
                }
                this.painter.drawComputerMove();
                if (checkWinner()) {
                    exit = init(true);
                    continue;
                }
            }
            if (this.playerTurn) {
                commandStr = this.input.validateInput(this.logic.size());
            }
            if ("INIT".equals(commandStr.toUpperCase())) {
                exit = this.init(true);
            } else if ("START".equals(commandStr.toUpperCase())) {
                this.init(false);
                System.out.println("New game started.");
            } else if ("EXIT".equals(commandStr.toUpperCase())) {
                exit = true;
            } else {
                String[] rc = commandStr.split(":");
                int row = Integer.parseInt(rc[0]);
                int col = Integer.parseInt(rc[1]);
                if (this.logic.select(row, col, this.playerOnX)) {
                    this.playerTurn = false;
                    this.painter.drawPlayerMove();
                    if (checkWinner()) {
                        exit = init(true);
                    }
                }
            }
        }
    }

    /**
     * Checks is there a winner.
     */
    private boolean checkWinner() {
        boolean result = false;
        if (this.logic.isWinnerX()) {
            this.painter.drawCongratulate(true);
            result = true;
        } else if (this.logic.isWinnerO()) {
            this.painter.drawCongratulate(false);
            result = true;
        } else  if (this.logic.allFilled()) {
            this.painter.drawTie();
            result = true;
        }
        return result;
    }
}
