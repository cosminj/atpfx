/*
 * Copyright (c) 2011. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.google.code.atpfx.base;

import com.fxcm.fix.UTCDate;
import com.fxcm.fix.UTCTimeOnly;
import com.fxcm.fix.pretrade.MarketDataSnapshot;
import com.fxcm.messaging.IMessage;
import com.fxcm.messaging.IMessageFactory;

public class MockMarketDataSnapshotAdapter extends MarketDataSnapshot {

    @Override
    protected double _getAskClose() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAskLow(double v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAskHigh(double v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAskOpen(double v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAskClose(double v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setLow(double v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setHigh(double v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBidLow(double v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBidHigh(double v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBidOpen(double v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBidClose(double v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTickVolume(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IMessage toMessage(String s, String s1, String s2, String s3, int i, IMessageFactory iMessageFactory) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean fill(IMessage iMessage) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getBidId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBidId(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getAskId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAskId(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getBidQuoteCondition() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBidQuoteCondition(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getBidQuoteType() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBidQuoteType(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UTCDate getBidExpireDate() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBidExpireDate(UTCDate utcDate) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UTCTimeOnly getBidExpireTime() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBidExpireTime(UTCTimeOnly utcTimeOnly) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getBidOriginator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBidOriginator(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getBidCurrency() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBidCurrency(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double getBidEntrySize() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setBidEntrySize(double v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getAskQuoteCondition() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAskQuoteCondition(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getAskQuoteType() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAskQuoteType(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UTCDate getAskExpireDate() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAskExpireDate(UTCDate utcDate) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UTCTimeOnly getAskExpireTime() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAskExpireTime(UTCTimeOnly utcTimeOnly) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getAskOriginator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAskOriginator(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getAskCurrency() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAskCurrency(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double getAskEntrySize() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAskEntrySize(double v) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected double _getLow() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected double _getHigh() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected double _getAskLow() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected double _getAskHigh() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected double _getAskOpen() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected double _getBidLow() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected double _getBidHigh() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected double _getBidOpen() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected double _getBidClose() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected int _getTickVolume() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}