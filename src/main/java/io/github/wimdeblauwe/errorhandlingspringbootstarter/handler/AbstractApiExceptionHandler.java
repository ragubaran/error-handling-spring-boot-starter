package io.github.wimdeblauwe.errorhandlingspringbootstarter.handler;

import io.github.wimdeblauwe.errorhandlingspringbootstarter.ApiExceptionHandler;
import io.github.wimdeblauwe.errorhandlingspringbootstarter.ErrorHandlingProperties;

public abstract class AbstractApiExceptionHandler implements ApiExceptionHandler {
    protected final ErrorHandlingProperties properties;

    public AbstractApiExceptionHandler(ErrorHandlingProperties properties) {
        this.properties = properties;
    }

    protected String getErrorCode(Throwable exception) {
        return replaceCodeWithConfiguredOverrideIfPresent(exception.getClass().getName());
    }

    protected String replaceCodeWithConfiguredOverrideIfPresent(String code) {
        return properties.getCodes().getOrDefault(code, code);
    }

    protected boolean hasConfiguredOverrideForCode(String code) {
        return properties.getCodes().containsKey(code);
    }

    protected boolean hasConfiguredOverrideForMessage(String key) {
        return properties.getMessages().containsKey(key);
    }

    protected String getOverrideMessage(String key) {
        return properties.getMessages().get(key);
    }
}
