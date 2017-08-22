package com.gst.okhttp.custom;

import android.text.style.ClickableSpan;

/**
 * Created by gst-pc on 2017/6/2.
 */

public abstract class ClickableTableSpan extends ClickableSpan {
    protected String tableHtml;

    // This sucks, but we need this so that each table can get its own ClickableTableSpan.
    // Otherwise, we end up removing the clicking from earlier tables.
    public abstract ClickableTableSpan newInstance();

    public void setTableHtml(String tableHtml) {
        this.tableHtml = tableHtml;
    }

    public String getTableHtml() {
        return tableHtml;
    }
}
