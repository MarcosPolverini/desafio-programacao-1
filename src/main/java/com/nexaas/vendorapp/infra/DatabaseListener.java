package com.nexaas.vendorapp.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private JdbcTemplate template;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS vendor_detail (id INTEGER IDENTITY PRIMARY KEY, purchaser_name VARCHAR(50) NOT NULL, "
            + "item_description VARCHAR(50) NOT NULL, item_price DECIMAL(19,2) NOT NULL, purchase_count INTEGER NOT NULL, merchant_address VARCHAR(60) NOT NULL, "
            + "merchant_name VARCHAR(50) NOT NULL)";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        template.execute(CREATE_TABLE);
    }

}