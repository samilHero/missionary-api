@org.springframework.modulith.ApplicationModule(
        allowedDependencies = {
                "common::entity",
                "common::enums",
                "common::exception",
                "member",
                "member::dto"
        }
)
package com.samill.missionary_backend.church;