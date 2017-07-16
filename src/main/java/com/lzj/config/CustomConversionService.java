package com.lzj.config;

import org.thymeleaf.Configuration;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.standard.expression.IStandardConversionService;

import java.util.Calendar;

/**
 * Created by li on 17-7-16.
 */
//  <span th:text="${{user.firstCalendar}}"></span> 将firstCalendar转换成对应的string日期
public class CustomConversionService implements IStandardConversionService {
    @Override
    public <T> T convert(Configuration configuration, IProcessingContext processingContext, Object object, Class<T> targetClass) {
        System.out.println(object+":"+targetClass.getClasses());
        if (object instanceof Calendar){
            Calendar calendar=(Calendar)object;
            String dateTime=calendar.getTime().toString();
            return (T) dateTime;
        }
        return (T) object;
    }
}
