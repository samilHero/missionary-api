@org.springframework.modulith.ApplicationModule(
    allowedDependencies = {
        "common::entity",
        "common::enums",
        "common::util",
        "common::exception",
        "token::provider"
    }
)
package com.samill.missionary_backend.member;