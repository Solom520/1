package com.frans.lifegame.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.frans.lifegame.Cell;
import com.frans.lifegame.Game;

public class GameFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Game game;
    private CellsPanel leftPanel;
    private JPanel rightPanel;
    private JButton startBtn;
    private JButton pauseBtn;
    private JButton randomBtn;
    private JButton clearBtn;
    private Thread gameThread;       
    private int width = 80;
    private int height = 80;
    private int randomProbability = 30;
    private boolean run = false;;
    
    public GameFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        game = new Game(width, height, 1000).init();   //初始化宽高和迭代的时间以ms为单位。
        game.setCellChangeListener((cells, run) -> {
            cells.forEach((cell) -> {
                updateCellBtnUI(cell);
            });
            leftPanel.updateUI();
        });
        gameThread = new Thread(game);
        initUI();
    }
    
    /**
     * 根据cell更新cellButton的ui
     * @param cell
     */
    public void updateCellBtnUI(final Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
        Component c = leftPanel.getComponentAtXY(x, y);
        if (cell.getStatus() == (byte)1) {
            c.setBackground(CellButton.SURVIVAL_COLOR);
        } else {
            c.setBackground(CellButton.DEATH_COLOR);
        }
    }
    
    public void startGame() {
        run = true;
        if (!gameThread.isAlive())
            gameThread.start();   //线程的开始
        else {
            game.setRun(run);
        }
    }
    
    public void pauseGame() {
        run = false;              
        game.setRun(run);       //结束线程
    }
    
    public void initUI() {
        leftPanel = new CellsPanel(width, height);
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(0, 1));
        getContentPane().add(leftPanel, BorderLayout.WEST);
        getContentPane().add(rightPanel, BorderLayout.EAST);
        leftPanel.setPreferredSize(new Dimension(800, 800));
        startBtn = new JButton("\u5F00\u59CB");
        startBtn.addActionListener((e) -> {
            startGame();
            startBtn.setEnabled(false);
            pauseBtn.setEnabled(true);
        });
        rightPanel.add(startBtn);
        pauseBtn = new JButton("\u6682\u505C");
        pauseBtn.addActionListener((e) -> {
            pauseGame();
            pauseBtn.setEnabled(false);
            startBtn.setEnabled(true);
        });
        pauseBtn.setEnabled(false);
        rightPanel.add(pauseBtn);
        randomBtn = new JButton("\u968F\u673A\u751F\u6210");
        randomBtn.addActionListener((e) -> {
            if (!run) {
                game.randomInit(randomProbability);
                for (int i = 0; i < game.getWidth(); ++i) {
                    for (int j = 0; j < game.getHeight(); ++j) {
                        updateCellBtnUI(game.getCellByXY(i, j));
                    }
                }
            }
        });
        rightPanel.add(randomBtn);
        clearBtn = new JButton("\u6E05\u7A7A\u9875\u9762");
        clearBtn.addActionListener((e) -> {
            if (!run) {
                game.init();
                for (int i = 0; i < game.getWidth(); ++i) {
                    for (int j = 0; j < game.getHeight(); ++j) {
                        updateCellBtnUI(game.getCellByXY(i, j));
                    }
                }
            }
        });
        rightPanel.add(clearBtn);
        
        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                CellButton btn = new CellButton(i, j);
                btn.addActionListener((e) -> {
                    if (!run) {
                        if (btn.getBackground() == CellButton.DEATH_COLOR) {
                            btn.setBackground(CellButton.SURVIVAL_COLOR);
                            game.getCellByXY(btn.getxInCellPanel(), btn.getyInCellPanel()).setStatus((byte) 1);
                        } else {
                            btn.setBackground(CellButton.DEATH_COLOR);
                            game.getCellByXY(btn.getxInCellPanel(), btn.getyInCellPanel()).setStatus((byte) 0);
                        }
                    }
                });
                leftPanel.add(btn);
            }
        }
        pack();
    }

}
