package com.arr.apklislib.update;


import com.arr.apklislib.update.model.PackageResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(JUnit4.class)
public class APKLisServiceTest {
    private MockWebServer mockWebServer;
    private APKLisService service;

    @Before
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        service = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().create(APKLisService.class);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void getPackageResponseOk() throws Exception {
        enqueueResponse("package_response_ok.json");

        PackageResponse data = service.getPackageResponse("com.arr.example").blockingGet();
        RecordedRequest request = mockWebServer.takeRequest();

        Assert.assertEquals(request.getMethod(), "GET");
        Assert.assertEquals(request.getPath(), "/application/?package_name=com.arr.example");
        Assert.assertEquals(request.getHeader("User-Agent"), "ApplifyCU/1.0.0");
        Assert.assertEquals(request.getHeader("Content-Type"), "application/json");

        Assert.assertEquals(data.getCount(), 1);
        Assert.assertEquals(data.getResults().get(0).getLastRelease().getVersionName(), "v5.0-beta");
        Assert.assertEquals(data.getResults().get(0).getLastRelease().getAppName(), "SIMple");
        Assert.assertEquals(data.getResults().get(0).getLastRelease().getVersionCode(), 50);
        Assert.assertEquals(data.getResults().get(0).getLastRelease().getSize(), "9.99 MB");
        Assert.assertEquals(data.getResults().get(0).getLastRelease().getID(), 62604);
    }

    @Test
    public void getPackageResponseNotFound() throws Exception {
        enqueueResponse("package_response_empty.json");

        PackageResponse data = service.getPackageResponse("com.arr.example").blockingGet();
        RecordedRequest request = mockWebServer.takeRequest();

        Assert.assertEquals(request.getMethod(), "GET");
        Assert.assertEquals(request.getPath(), "/application/?package_name=com.arr.example");
        Assert.assertEquals(request.getHeader("User-Agent"), "ApplifyCU/1.0.0");
        Assert.assertEquals(request.getHeader("Content-Type"), "application/json");

        Assert.assertEquals(data.getCount(), 0);
        Assert.assertEquals(data.getResults().size(), 0);
    }

    private void enqueueResponse(String fileName) throws IOException {

        InputStream inputStream = Objects.requireNonNull(getClass().getClassLoader()).getResourceAsStream("api-response/" + fileName);

        assert inputStream != null;
        BufferedSource source = Okio.buffer(Okio.source(inputStream));

        String body = source.readString(StandardCharsets.UTF_8);

        MockResponse mockResponse = new MockResponse()
                .setBody(body);

        mockWebServer.enqueue(mockResponse);
    }
}