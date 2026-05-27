rootProject.name = "EyeInTheSky"

include(
    "apps:data-ingest",
    "data-schemas"
)

project(":apps:data-ingest").projectDir = file("apps/data-ingest")
