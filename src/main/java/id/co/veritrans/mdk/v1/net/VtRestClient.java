package id.co.veritrans.mdk.v1.net;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.VtRequest;

import java.net.URI;

/**
 * Created by gde on 5/8/15.
 */
public interface VtRestClient {

    <T> T get(Class<T> response, URI uri) throws RestClientException;

    <T> T get(Class<T> response, String url) throws RestClientException;

    <T> T post(Class<T> response, String url) throws RestClientException;

    <T> T post(Class<T> response, String url, VtRequest vtRequest) throws RestClientException;
}
