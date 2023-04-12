package com.example.software_doc_design.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishSecurityEvent(SecurityEvent securityEvent) {
        applicationEventPublisher.publishEvent(securityEvent);
    }
}
