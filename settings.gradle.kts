rootProject.name = "EyeInTheSky"

include(
    "apps:data-ingest",
    "data-schemas:ingestion"
)

project(":apps:data-ingest").projectDir = file("apps/data-ingest")
project(":data-schemas:ingestion").projectDir = file("data-schemas/ingestion")
