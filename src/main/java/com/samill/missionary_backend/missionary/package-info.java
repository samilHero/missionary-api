@org.springframework.modulith.ApplicationModule(
        allowedDependencies = {
                "member",
                "church",
                "common::entity",
                "common::enums",
                "common::exception"
        }
)
package com.samill.missionary_backend.missionary;