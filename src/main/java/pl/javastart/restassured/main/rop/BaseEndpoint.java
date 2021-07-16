package pl.javastart.restassured.main.rop;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.lang.reflect.Type;

public abstract class BaseEndpoint<Endpoint, Model> {

    protected Response response;

    protected abstract Type getModelType();

    public abstract Endpoint sendRequest();

    protected abstract int getSuccessStatusCode();

    public Model getResponseModel() {
        return response.as(getModelType());
    }

    public Endpoint assertRequestSuccess() {
        return assertStatusCode(getSuccessStatusCode());
    }

    public Endpoint assertStatusCode(int statusCode) {
        Assertions.assertThat(response.getStatusCode()).as("Status code").isEqualTo(statusCode);
        return (Endpoint) this;
    }
}
