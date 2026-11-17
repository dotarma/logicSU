package armqi.logically.su.domain.model

data class SuSFSStatus(
    val enabled: Boolean,
    val version: String,
    val enabledFeatures: String,
)
