@org.springframework.modulith.ApplicationModule(
    allowedDependencies = {
        "member",
        "member::dto",
        "member::enums",
        "church",
        "church::dto",
        "common::entity",
        "common::enums",
        "common::exception",
        "common::util",
        "common::event"
    }
)
package com.samill.missionary_backend.missionary;