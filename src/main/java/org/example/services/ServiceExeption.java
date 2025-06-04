package org.example.services;

public class ServiceExeption extends RuntimeException {
    public ServiceExeption(String message) {
        super(message);
    }

  public ServiceExeption(String message, Throwable cause) {
    super(message, cause);
  }
}
