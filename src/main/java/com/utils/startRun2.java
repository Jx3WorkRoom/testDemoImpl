package com.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class startRun1  implements CommandLineRunner {
    @Autowired
    JDBCTestDemo2 JDBCTestDemo2;

    @Override
    public void run(String... var1) throws Exception{
        JDBCTestDemo2.run1();
    }
}
