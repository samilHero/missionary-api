@org.springframework.modulith.ApplicationModule(
    allowedDependencies = {
        "authentication",
        "authentication::security",
        "configs",
        "common::exception",
        "common::enums",
        "common::dto",
        "common::entity",
        "common::util",
        "member::dto",
        "member",
        "church",
        "church::dto",
        "missionary",
        "missionary::dto",
        "participation",
        "participation::dto"
    }
)
package com.samill.missionary_backend.gateway;
