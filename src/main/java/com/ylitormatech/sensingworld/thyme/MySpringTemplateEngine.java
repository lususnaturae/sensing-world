package com.ylitormatech.sensingworld.thyme;

import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;

/**
 * Created by Hannu Lohtander on 23.4.2016.
 */
public class MySpringTemplateEngine extends SpringTemplateEngine {

    public MySpringTemplateEngine() {
        super();
    }

    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        addMessageResolver(new StandardMessageResolver());
    }



}
