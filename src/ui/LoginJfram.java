package ui;

import achieve.Captcha;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * @author: mx321
 * @description: 游戏登录界面
 * @date: 2024/4/18
 */

//2.创建游戏登录界面
//获取用户输入的用户名
//获取用户输入的密码
//生成一个验证码
//获取用户输入的验证码
//比较用户名、密码、验证码是否正确
//完成登录跳转游戏主界面

public class LoginJfram extends JFrame implements ActionListener {

    String username = "111";
    String password = "111";
    String code;

    //用户名输入框
    JTextField usernameField = new JTextField();
    //密码输入框
    JPasswordField passwordField = new JPasswordField();
    //验证码输入框
    JTextField codeField = new JTextField();


    //登录按钮
    ImageIcon loginIcon = new ImageIcon("pics/login_register/login.png");
    JButton loginButton = new JButton(loginIcon);

    //注册按钮
    ImageIcon registerIcon = new ImageIcon("pics/login_register/register.png");
    JButton registerButton = new JButton(registerIcon);


    public LoginJfram(){

        //初始化登录界面
        initJfram();

        //设置关闭模式
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置窗口显示
        setVisible(true);
    }


    private void initJfram() {

        //设置窗口大小
        setSize(400,500);
        //设置窗口标题
        setTitle("登录界面");
        //设置打开时在屏幕中心
        setLocationRelativeTo(null);
        //设置窗口不可调整大小
        setResizable(false);

        //登录界面的用户名输入框
        JLabel usernameLabel = new JLabel(new ImageIcon("pics/login_register/icon_user.png"));
        usernameLabel.setBounds(60, 70, 48, 48);
        getContentPane().add(usernameLabel);

        usernameField.setBounds(120, 70, 200, 48);
        getContentPane().add(usernameField);

        //登录界面的密码输入框
        JLabel passwordLabel = new JLabel(new ImageIcon("pics/login_register/icon_lock.png"));
        passwordLabel.setBounds(60, 150, 48, 48);
        getContentPane().add(passwordLabel);

        passwordField.setBounds(120, 150, 200, 48);
        getContentPane().add(passwordField);

        //验证码输入框
        JLabel codeLabel = new JLabel("验证码");
        codeLabel.setBounds(60, 230, 48, 48);
        getContentPane().add(codeLabel);

        codeField.setBounds(120, 230, 100, 48);
        getContentPane().add(codeField);

        //验证码数字
        initCode();
        JLabel codeNum = new JLabel(code);
        codeNum.setBounds(230, 230, 48, 48);
        getContentPane().add(codeNum);


        //登录按钮
        loginButton.setBounds(120, 300, 173, 46);
        getContentPane().add(loginButton);
        loginButton.addActionListener(this);

        //注册按钮
        registerButton.setBounds(120, 360, 173, 46);
        getContentPane().add(registerButton);
        registerButton.addActionListener(this);

        //背景图片
        ImageIcon imageIcon = new ImageIcon("pics\\background\\backgroundImage.png");
        JLabel background = new JLabel(imageIcon);
        background.setBounds(40, 80, 500, 500);
        getContentPane().add(background);

    }

    private void initCode() {
        Captcha captcha = new Captcha();
        code = captcha.generateCode();
    }

    public void login() {
        //获取用户输入的用户名
        String usernameInput = usernameField.getText();
        //获取用户输入的密码
        String passwordInput = new String(passwordField.getPassword());
        //获取用户输入的验证码
        String codeInput = codeField.getText();




        //比较用户名、密码、验证码是否正确
        if(usernameInput.equals(username) && passwordInput.equals(password) && codeInput.equalsIgnoreCase(code) ){
            //完成登录跳转游戏主界面
            new GameJfram();
        }else {
            setVisible(true);
            JOptionPane.showMessageDialog(null, "用户名、密码或验证码错误");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(loginButton)){
            setVisible(false);
            login();
        } else if (source.equals(registerButton)) {
            setVisible(false);
            new RegisterJfram();
        }
    }
}
