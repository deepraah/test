package com.deepu.automation.eventapis;

import com.deepu.automation.eventapis.common.ApiExecution;
import com.deepu.automation.eventapis.common.ApiList;
import com.deepu.automation.eventapis.common.ContentTypeList;

import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: rahulk
 * Date: 2/9/13
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProvisioningApi extends ApiExecution {

    private HttpResponse serverHttpResponse;

    @Test(groups = {"execute"})
    public void GetListAccounts() {
        serverHttpResponse = sendPostrequest(ApiList.PROVISIONING_ACCOUNT_LISTACCOUNTS, ContentTypeList.APPLICATION_XML);
        Assert.assertEquals(serverHttpResponse.getStatusLine().getStatusCode(), 404);
    }

    @Test(groups = {"execute"})
    public void GetLiveEvent() {
        serverHttpResponse = sendPostrequest(ApiList.PROVISIONING_EVENT_GETEVENTUPDATE, ContentTypeList.APPLICATION_JSON);
        Assert.assertEquals(serverHttpResponse.getStatusLine().getStatusCode(), 404);
    }
}