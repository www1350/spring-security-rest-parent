package com.absurd;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2016/10/11.
 */
public class ClientTest {
    final String urlOverHttps = "http://localhost:8080/spring-security-rest-custom/1";


    @Test
    public void testBasicAuth() throws IOException, AuthenticationException {
        final CloseableHttpClient httpClient =   HttpClients.custom().build();
        final HttpGet getMethod = new HttpGet(urlOverHttps);
        final UsernamePasswordCredentials creds = new UsernamePasswordCredentials("admin", "system");

        HttpResponse response = httpClient.execute(getMethod);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(401));

        getMethod.addHeader(new BasicScheme().authenticate(creds, getMethod, null));
        response = httpClient.execute(getMethod);
        System.out.println(EntityUtils.toString(response.getEntity()));
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
    }
}
