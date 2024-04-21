@org.springframework.modulith.ApplicationModule(
        allowedDependencies = {
                "authentication::security",
                "common::enums",
                "member::dto",
                "member",
        }
)
package com.samill.missionary_backend.gateway;
