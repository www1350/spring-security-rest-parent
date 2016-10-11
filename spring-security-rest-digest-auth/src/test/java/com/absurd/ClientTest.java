package com.absurd;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2016/10/11.
 */
public class ClientTest {
    final String urlOverHttps = "http://localhost:8080/spring-security-rest-digest-auth/1";


    @Test
    public void testBasicAuth() throws IOException, AuthenticationException, MalformedChallengeException {
        final CloseableHttpClient httpClient =   HttpClients.custom().build();
        final HttpGet getMethod = new HttpGet(urlOverHttps);
        final UsernamePasswordCredentials creds = new UsernamePasswordCredentials("user1", "user1Pass");
        final DigestScheme digest = new DigestScheme();
        final HttpClientContext context = HttpClientContext.create();

        HttpResponse response = httpClient.execute(getMethod);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(401));
        // Pull out the auth header that came back from the server
        final Header challenge = response.getHeaders("WWW-Authenticate")[0];
        digest.processChallenge(challenge);
//        digest.overrideParamter("realm","Custom Realm Name");
//        digest.overrideParamter("key","uniqueAndSecret");

        final Header solution = digest.authenticate(creds, getMethod, context);
        getMethod.addHeader(solution);
        response = httpClient.execute(getMethod);
        System.out.println(EntityUtils.toString(response.getEntity()));
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
    }
}
