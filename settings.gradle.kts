rootProject.name = "EyeInTheSky"

include(
    "apps:data-ingest",
)

project(":apps:data-ingest").projectDir = file("apps/data-ingest")
