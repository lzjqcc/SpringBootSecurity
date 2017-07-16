package com.lzj.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.standard.StandardDialect;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by li on 17-7-14.
 */
/*@Configuration
@EnableAutoConfiguration*/
public class MyWebAppConfig  {
    //配置自己的ThymeleafViewResolver，ｓｐｒｉｎｇ mvc 可以寻找到templates子目录下的html文件
// 详情请见   https://stackoverflow.com/questions/24260520/error-resolving-template-pages-template-might-not-exist-or-might-not-be-acces
    @Bean
    public ThymeleafViewResolver getViewResolver(){
        //SpringTemplateEngine engine=new SpringTemplateEngine();
      //  Set<IDialect> iDialects=new HashSet<IDialect>();
      //  iDialects.add(new StandardDialect());
       // engine.setAdditionalDialects(iDialects);
        ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
        String[] viewNames={"classpath:/templates/*"};
        viewResolver.setViewNames(viewNames);
        //viewResolver.setTemplateEngine(engine);
        return viewResolver;
    }

}
