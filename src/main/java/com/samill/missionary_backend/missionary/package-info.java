@org.springframework.modulith.ApplicationModule(
    allowedDependencies = {
        "member",
        "church",
        "church::dto",
        "common::entity",
        "common::enums",
        "common::exception"
    }
)
package com.samill.missionary_backend.missionary;