package com.lzj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.spring4.messageresolver.SpringMessageResolver;
import org.thymeleaf.standard.StandardDialect;

import javax.swing.*;
import java.util.Map;
import java.util.Set;

/**
 * Created by li on 17-7-16.
 */
/*@Configuration
@EnableAutoConfiguration
@ComponentScan*/
public class Config{
    //templateEngine对象会自动注入

    @Bean
    @Autowired
    //用于将CustomConversionService放入TemplateEngine
    public CustomConversionService get(TemplateEngine templateEngine){
        //dialectConfiguration.getPrefix() th-->SpringStandarDialect
        //在SpringTemlateEngine中已经创建了一个SpringStandardDialect对象
        //    private static final SpringStandardDialect SPRINGSTANDARD_DIALECT = new SpringStandardDialect();
        //IDialect定义了dom处理，表达式解析如果没有则使用默认的标准方言
        //TemplateEngine是in order to execute Thymeleaf templates,注意只有一个
        //SpringTemplateResovler是用来指定.html存放的位置
        CustomConversionService customConversionService=new CustomConversionService();
       Map<String,IDialect>map= templateEngine.getDialectsByPrefix();
       SpringStandardDialect dialect= (SpringStandardDialect) map.get("th");
       dialect.setConversionService(customConversionService);
        return customConversionService;

    }
    public IMessageResolver getMessageResolver(){
        return null;
    }

}
