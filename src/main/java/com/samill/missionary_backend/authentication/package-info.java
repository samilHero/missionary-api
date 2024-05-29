@org.springframework.modulith.ApplicationModule(
    allowedDependencies = {
        "member",
        "member::dto",
        "member::exception",
        "common",
        "member::enums",
        "common::exception",
        "token"
    }
)
package com.samill.missionary_backend.authentication;