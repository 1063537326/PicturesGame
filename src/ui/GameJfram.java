package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author: mx321
 * @description: 游戏主界面
 * @date: 2024/4/18
 */

//1.创建游戏主界面
//JavaBean类描述界面的属性：宽高  行为
//上下左右移动的代码逻辑
//统计步数的代码逻辑
//一键通关
//查看最终效果
//等等...

public class GameJfram extends JFrame implements KeyListener, ActionListener {

    int[][] data = new int[3][3];
    //记录空白方块在二维数组的位置
    int x = 0;
    int y = 0;

    //记录步数
    int step = 0;

    //记录胜利的数组
    int[][] win = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };

    //设置菜单栏的菜单项
    JMenuItem replayItem = new JMenuItem("重新开始");
    JMenuItem changeItem = new JMenuItem("更换图片");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem exitItem = new JMenuItem("退出");
    JMenuItem authorItem = new JMenuItem("MX");

    //图片列表
    int picsIndex = 0;
    ArrayList<String> list = new ArrayList<>();
    //初始化图片列表
    public void initList() {
        list.add("ceremonial");
        list.add("jewel");
        list.add("lion");
        list.add("littlecat");
        list.add("makeup");
        list.add("onthebed");
    }


    //小图片路径
    String smallFigurePath = "pics\\ceremonial\\105x105\\0";
    //大图片路径
    String bigFigurePath = "pics\\ceremonial\\105x105\\";
    //背景图片路径
    String backgroundPath = "pics\\background\\backgroundImage.png";
    //胜利图片路径
    String winPath = "pics\\win.png";

    //更换图片
    public void changeImage() {
        initList();
        Random random = new Random();
        int length = list.size();
        picsIndex = random.nextInt(length);
        if (picsIndex == list.size()) {
            picsIndex = 0;
        }
        smallFigurePath = "pics\\" + list.get(picsIndex) + "\\105x105\\0";
        bigFigurePath = "pics\\" + list.get(picsIndex) + "\\105x105\\";
    }


    public GameJfram() {

        //初始化窗口
        initJFrame();
        //初始化菜单栏
        initJMenuBar();
        //初始化数据（打乱图片）
        initData();
        //初始化图片
        initImage();


        //设置窗口显示
        setVisible(true);
    }

    private void initData() {
        //设置一维数组
        int[] arr = new int[9];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        //打乱一维数组
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int index = random.nextInt(arr.length);
            arr[i] = arr[index];
            arr[index] = temp;
        }

        //获取空白方块的位置
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 9) {
                x = i / 3;
                y = i % 3;
            }

        }

        //将一维数组转换为二维数组
        int index = 0;

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = arr[index];
                index++;
            }
        }

    }

    private void initImage() {

        //清空之前的图片
        getContentPane().removeAll();

        if (checkWin()) {
            JLabel win = new JLabel(new ImageIcon(winPath));
            win.setBounds(190, 220, 200, 100);
            getContentPane().add(win);
        }

        //设置步数
        JLabel jLabelStep = new JLabel("步数：" + step);
        jLabelStep.setBounds(40, 40, 50, 50);
        getContentPane().add(jLabelStep);
        //设置图片
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                int num = data[i][j];
                JLabel jLabel = new JLabel(new ImageIcon(smallFigurePath + num + ".jpg"));
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
                jLabel.setBounds(j * 105 + 130, i * 105 + 155, 105, 105);
                getContentPane().add(jLabel);
            }
        }

        //设置背景图片
        ImageIcon imageIcon = new ImageIcon(backgroundPath);
        JLabel background = new JLabel(imageIcon);
        background.setBounds(40, 80, 500, 500);
        getContentPane().add(background);


        //刷新界面
        getContentPane().repaint();


    }

    private void initJMenuBar() {
        //设置菜单栏
        JMenuBar jMenuBar = new JMenuBar();
        //设置菜单栏的菜单
        JMenu function = new JMenu("功能");
        JMenu about = new JMenu("关于");


        //添加菜单栏
        function.add(changeItem);
        function.add(replayItem);
        function.add(reLoginItem);
        function.add(exitItem);

        about.add(authorItem);

        jMenuBar.add(function);
        jMenuBar.add(about);

        setJMenuBar(jMenuBar);

        //设置菜单栏的菜单项的点击事件
        replayItem.addActionListener(this);
        changeItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        exitItem.addActionListener(this);
        authorItem.addActionListener(this);

    }

    private void initJFrame() {
        //设置窗口大小
        setSize(600, 600);
        //设置窗口标题
        setTitle("拼图游戏单机版");
        //设置打开时在屏幕中心
        setLocationRelativeTo(null);
        //设置窗口大小不可修改
        setResizable(false);
        //取消默认布局
        setLayout(null);
        //设置关闭模式
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置整个界面的键盘监听事件
        addKeyListener(this);


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //按a键显示原图
        if (code == 65) {
            //清空之前的图片
            getContentPane().removeAll();
            //设置图片
            JLabel jLabel = new JLabel(new ImageIcon(bigFigurePath + "all.jpg"));
            jLabel.setBounds(130, 155, 315, 315);
            getContentPane().add(jLabel);
            //设置背景图片
            ImageIcon imageIcon = new ImageIcon(backgroundPath);
            JLabel background = new JLabel(imageIcon);
            background.setBounds(40, 80, 500, 500);
            getContentPane().add(background);
            //刷新界面
            getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (checkWin()) {
            return;
        }
        int code = e.getKeyCode();
        //键盘监听
        switch (code) {
            //松开w键直接胜利
            case 87 -> {
                //将数组还原
                data[0][0] = 1;
                data[0][1] = 2;
                data[0][2] = 3;
                data[1][0] = 4;
                data[1][1] = 5;
                data[1][2] = 6;
                data[2][0] = 7;
                data[2][1] = 8;
                data[2][2] = 9;
                //重新初始化图片
                initImage();
            }
            //松开a键隐藏原图，显示之前图片
            case 65 -> initImage();

            //向上箭头，空白图片向下移动
            case KeyEvent.VK_UP -> {
                if (x == 2) {
                    return;
                } else {
                    data[x][y] = data[x + 1][y];
                    data[x + 1][y] = 9;
                    x++;
                    //计数器加一
                    step++;
                    initImage();
                }
                System.out.println("上");
            }
            //向下箭头，空白图片向上移动
            case KeyEvent.VK_DOWN -> {
                if (x == 0) {
                    return;
                } else {
                    data[x][y] = data[x - 1][y];
                    data[x - 1][y] = 9;
                    x--;
                    //计数器加一
                    step++;
                    initImage();
                }
                System.out.println("下");
            }
            //向左箭头，空白图片向右移动
            case KeyEvent.VK_LEFT -> {
                if (y == 2) {
                    return;
                } else {
                    data[x][y] = data[x][y + 1];
                    data[x][y + 1] = 9;
                    y++;
                    //计数器加一
                    step++;
                    initImage();
                }
                System.out.println("左");
            }
            //向右箭头，空白图片向左移动
            case KeyEvent.VK_RIGHT -> {
                if (y == 0) {
                    return;
                } else {
                    data[x][y] = data[x][y - 1];
                    data[x][y - 1] = 9;
                    y--;
                    //计数器加一
                    step++;
                    initImage();
                }
                System.out.println("右");
            }
        }
    }

    //判断win和data数组里的数值是否相同
    public boolean checkWin() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    //只要有一个不相同就返回false
                    return false;
                }
            }
        }
        //全部相同返回true
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(replayItem)) {
            //从新开始游戏
            initData();
            step = 0;
            initImage();
        } else if (source.equals(changeItem)) {
            //更换图片
            changeImage();
            initData();
            step = 0;
            initImage();

        } else if (source.equals(reLoginItem)) {
            //重新登陆
            //关闭当前界面
            this.setVisible(false);
            //打开登录界面
            new LoginJfram();
        } else if (source.equals(exitItem)) {
            //退出游戏
            System.exit(0);
        } else if (source.equals(authorItem)) {

            //关于
            JOptionPane.showMessageDialog(this, "作者：mx321", "关于", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
