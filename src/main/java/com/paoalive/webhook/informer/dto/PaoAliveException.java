package com.paoalive.webhook.informer.dto;

/**
 * @author leonidas_patouhas
 * on 8/8/2019.
 */
public class PaoAliveException extends RuntimeException {
    public PaoAliveException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaoAliveException(Throwable cause) {
        super(cause);
    }
}
