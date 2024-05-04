
@org.springframework.modulith.ApplicationModule(
        allowedDependencies = {
                "member",
                "missionary",
                "notification",
                "common::entity",
                "common::enums",
                "common::exception",
                "common::util"
        }
)
package com.samill.missionary_backend.participation;