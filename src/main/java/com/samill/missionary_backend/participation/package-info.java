
@org.springframework.modulith.ApplicationModule(
        allowedDependencies = {
                "member",
                "missionary",
                "missionary::dto",
                "notification",
                "common::entity",
                "common::enums",
                "common::exception",
                "common::dto",
                "common::util",
        }
)
package com.samill.missionary_backend.participation;