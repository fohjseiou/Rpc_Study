package com.yupi.yurpc.registry.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

public class lambdaTest {
    @Test
    public void test(){
        //可以自动包含对象的创建以及方法的调用
        //1.具体的实现类中的实现方法中只有一条数据语句时
//        sendMessage(()-> System.out.println("This is an Email"));

        //当数据语句不止自有一条时候，就不能省掉大括号
//        sendMessage(() ->{
//            System.out.println("This is an Email");
//            System.out.println("Here is here");
//        });

        //2.当接口中的方法包含参数时
//        sendMessage((name) ->{
//            System.out.println("This is an Email to"+name);
//        });

        //因为这里只有一个参数，因此可以将进行简化，同理因为自有一个实现方法，所以大括号也可以进行去掉
//        sendMessage(name ->
//            System.out.println("This is an Email to"+name)
//        );

        //3.处理对象存在返回值的情况
//        sendMessage((message,name) ->{
//            System.out.println("This is email"+" "+message+" "+name);
//            return "Success!";
//        });
        //4.进行改写
        Message lambda = (message,name) ->{
            System.out.println("This is email"+" "+message+" "+name);
            return "Success!";
        };
        sendMessage(lambda);

        //lambda表达式的使用场景  lambda的使用场景是有限制的，它仅且只能用在一个接口中只有一个抽象方法上
    }

     void sendMessage(Message message){
         String result = message.send("Hello!", "Xjr");
         System.out.println(result);
     }

    public interface Message{
       String send(String message ,String name);
    }


    @Data
    @NoArgsConstructor
    public static class Email implements Message{
        private String email;

        public Email(String email) {
            this.email = email;
        }

        @Override
        public String send(String message, String name) {
            System.out.println("This is email"+""+message+" "+name);
            return "Success!";
        }


    }

    @Data
    @NoArgsConstructor
    public static class Msi implements Message{
        private String msi;

        public Msi(String msi) {
            this.msi = msi;
        }


        @Override
        public String send(String message ,String name) {
            System.out.println("This is msi"+""+message+" "+name);
            return "Success!";
        }
    }
}
