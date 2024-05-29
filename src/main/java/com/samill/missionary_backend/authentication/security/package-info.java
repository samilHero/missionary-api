@org.springframework.modulith.NamedInterface("security")
@org.springframework.modulith.ApplicationModule(
    allowedDependencies = {
        "member",
        "member::dto",
        "member::exception",
        "token::provider",
        "common::util"
    }
)
package com.samill.missionary_backend.authentication.security;