package com.lql.designmode.simplefactory.service;

import com.lql.designmode.simplefactory.beans.ApplePizzer;
import com.lql.designmode.simplefactory.beans.BananaPizzer;
import com.lql.designmode.simplefactory.beans.Pizzer;
import org.springframework.stereotype.Service;

/**
 * @Author lql
 * @Date 2018/4/9 16:00
 */
@Service
public class PizzerFactory {
    public Pizzer getPizzer(String type) {
        Pizzer pizzer = null;
        if (type.equalsIgnoreCase("Apple"))
            pizzer = new ApplePizzer();
        if (type.equalsIgnoreCase("Banana"))
            pizzer = new BananaPizzer();
        return pizzer;
    }
}
