# DefaultApi

All URIs are relative to *https://todolist*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAll**](DefaultApi.md#getAll) | **GET** /v1/tasks | GET v1/tasks |


<a name="getAll"></a>
# **getAll**
> List&lt;Task&gt; getAll()

GET v1/tasks

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://todolist");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    try {
      List<Task> result = apiInstance.getAll();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getAll");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Task&gt;**](Task.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

