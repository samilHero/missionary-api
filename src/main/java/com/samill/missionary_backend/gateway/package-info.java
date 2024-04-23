@org.springframework.modulith.ApplicationModule(
    allowedDependencies = {
        "authentication::security",
        "configs",
        "common::exception",
        "common::enums",
        "member::dto",
        "member",
    }
)
package com.samill.missionary_backend.gateway;
