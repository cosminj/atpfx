package com.google.code.atpfx;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

/**
 * @author juravco
 *         Date: Sep 6, 2009
 *         Time: 1:45:12 PM
 */
@Component()
@Scope("singleton")
public class GlobalProperties {

    public static final String FXCM_ACCOUNTS_PROPERTIES_FILE = "FXCMAccounts";
    public static final String FXCM_GLOBAL_PROPERTIES        = "global";

    private static final ResourceBundle rbAccounts;
    private static final ResourceBundle rbGlobal; 
    private final static GlobalProperties _this;

    static {
        rbAccounts = ResourceBundle.getBundle(FXCM_ACCOUNTS_PROPERTIES_FILE);
        rbGlobal   = ResourceBundle.getBundle(FXCM_GLOBAL_PROPERTIES);
        _this = new GlobalProperties();
    }

    private GlobalProperties() {

    }

    public static boolean isPersistenceEnabled() {
        String x =  _this.getGlobalProperty("enable_persistence");
        return Boolean.valueOf(x);
    }

    public String getAccountsProperty(String key) {
        return rbAccounts.getString(key);
    }

    public String getGlobalProperty(String key) {
        return rbGlobal.getString(key);
    }

    public static GlobalProperties getInstance() {
        return _this;
    }
}