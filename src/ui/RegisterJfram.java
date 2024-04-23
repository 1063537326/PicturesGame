package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: mx321
 * @description: 游戏注册界面
 * @date: 2024/4/18
 */

//3.创建游戏注册界面
//获取用户输入的用户名
//获取用户输入的密码（两次验证）
//比较两次输入的密码是否一致
//判断当前用户是否已经注册
//完成注册存入数据并跳转登录

public class RegisterJfram extends JFrame implements ActionListener {
    String username = "111";
    String password = "111";

    //用户名输入框
    JTextField usernameField = new JTextField();
    //密码输入框
    JPasswordField passwordField = new JPasswordField();
    //确认密码输入框
    JPasswordField confirmPasswordField = new JPasswordField();
    //注册按钮
    ImageIcon registerIcon = new ImageIcon("pics/login_register/register.png");
    JButton registerButton = new JButton(registerIcon);






    public RegisterJfram(){

        //初始化注册界面
        initRegisterJfram();
        //设置关闭模式
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口显示
        setVisible(true);
    }

    private void initRegisterJfram() {
        //设置窗口大小
        setSize(400,500);
        //设置窗口标题
        setTitle("注册界面");
        //设置打开时在屏幕中心
        setLocationRelativeTo(null);
        //设置窗口大小不可修改
        setResizable(false);

        //用户名输入框
        JLabel usernameLabel = new JLabel("用户名");
        usernameLabel.setBounds(50,100,40,50);
        usernameField.setBounds(110,100,200,50);
        getContentPane().add(usernameLabel);
        getContentPane().add(usernameField);

        //密码输入框
        JLabel passwordLabel = new JLabel("密码");
        passwordLabel.setBounds(50,170,40,50);
        passwordField.setBounds(110,170,200,50);
        getContentPane().add(passwordLabel);
        getContentPane().add(passwordField);

        //确认密码输入框
        JLabel confirmPasswordLabel = new JLabel("确认密码");
        confirmPasswordLabel.setBounds(50,240,50,50);
        confirmPasswordField.setBounds(110,240,200,50);
        getContentPane().add(confirmPasswordLabel);
        getContentPane().add(confirmPasswordField);

        //注册按钮
        registerButton.setBounds(107,350,173,46);
        getContentPane().add(registerButton);

        //注册按钮点击事件
        registerButton.addActionListener(this);

        //设置背景图片
        ImageIcon imageIcon = new ImageIcon("pics\\background\\backgroundImage.png");
        JLabel background = new JLabel(imageIcon);
        background.setBounds(40, 80, 500, 500);
        getContentPane().add(background);

    }

    //判断两次输入的密码是否一致
    public boolean passwordComparison(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }


    //判断用户名是否已经注册
    public boolean registrationComparison(String username){
        return username.equals(this.username);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(registerButton)){
            //获取用户输入的用户名
            String usernameInput = usernameField.getText();
            //获取用户输入的密码
            String passwordInput = new String(passwordField.getPassword());
            //获取用户输入的确认密码
            String confirmPasswordInput = new String(confirmPasswordField.getPassword());

            //判断两次输入的密码是否一致
            if (passwordComparison(passwordInput, confirmPasswordInput)){
                //判断用户名是否已经注册
                if (registrationComparison(usernameInput)){
                    JOptionPane.showMessageDialog(null, "用户名已经注册");
                }else {
                    //完成注册存入数据并跳转登录
                    this.username = usernameInput;
                    this.password = passwordInput;
                    JOptionPane.showMessageDialog(null, "注册成功");
                    new LoginJfram();
                }
            }else {
                JOptionPane.showMessageDialog(null, "两次输入的密码不一致");
            }
        }
    }
}
