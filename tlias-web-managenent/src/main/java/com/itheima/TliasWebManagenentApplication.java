package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan//启动时自动扫描Servlet、Filter、Listener
@SpringBootApplication
public class TliasWebManagenentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagenentApplication.class, args);
    }

}
