@org.springframework.modulith.ApplicationModule(
    allowedDependencies = {
        "authentication",
        "authentication::security",
        "configs",
        "common::exception",
        "common::enums",
        "common::dto",
        "common::entity",
        "member::dto",
        "member",
        "church",
        "church::dto",
        "missionary",
        "missionary::dto"
    }
)
package com.samill.missionary_backend.gateway;
