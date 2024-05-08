@org.springframework.modulith.ApplicationModule(
    allowedDependencies = {
        "authentication",
        "authentication::security",
        "common::entity",
        "common::enums",
        "common::util",
        "common::exception"
    }
)
package com.samill.missionary_backend.member;