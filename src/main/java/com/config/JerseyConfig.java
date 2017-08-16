package com.config;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

import com.action.UserAction;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig{
    public JerseyConfig(){
        register(UserAction.class);
    }
}
