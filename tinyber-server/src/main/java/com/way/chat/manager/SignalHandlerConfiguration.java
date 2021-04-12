package com.tiny.chat.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SignalHandlerConfiguration {

    private int cmd;
    private String cmdHandler;
    private List<String> proCmdhandlers = new ArrayList<String>();

    public SignalHandlerConfiguration() {
    }

    public SignalHandlerConfiguration(String cmd, Properties prop) {
        this.cmd = Integer.parseInt(cmd);
        String[] values = prop.getProperty(cmd).split(",");
        if (values.length > 0) {
            cmdHandler = values[0];
            if (values.length > 1) {
                for (int i = 0; i < values.length; i++) {
                    if (i > 0) {
                        proCmdhandlers.add(values[i]);
                    }
                }
            }
        }
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public String getCmdHandler() {
        return cmdHandler;
    }

    public void setCmdHandler(String cmdHandler) {
        this.cmdHandler = cmdHandler;
    }

    public List<String> getProCmdhandlers() {
        return proCmdhandlers;
    }

    public void setProCmdhandlers(List<String> proCmdhandlers) {
        this.proCmdhandlers = proCmdhandlers;
    }


}
