@org.springframework.modulith.ApplicationModule(
    allowedDependencies = {
        "authentication",
        "common::entity",
        "common::enums"
    }
)
package com.samill.missionary_backend.member;