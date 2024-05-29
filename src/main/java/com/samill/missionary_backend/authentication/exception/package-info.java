@org.springframework.modulith.NamedInterface("exception")
@org.springframework.modulith.ApplicationModule(
    allowedDependencies = {
        "common",
        "common::enums",
        "common::exception",
        "member",
        "member::dto",
        "member::enums",
        "member::exception",
        "token::provider",
        "common::util"
    }
)
package com.samill.missionary_backend.authentication.exception;