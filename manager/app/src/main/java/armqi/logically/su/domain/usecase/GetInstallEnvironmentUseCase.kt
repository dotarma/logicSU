package armqi.logically.su.domain.usecase

import armqi.logically.su.data.flash.FlashRepository
import armqi.logically.su.domain.model.InstallEnvironment

class GetInstallEnvironmentUseCase(
    private val repository: FlashRepository,
) {
    fun cached(): InstallEnvironment? = repository.installEnvironment.value

    suspend operator fun invoke(forceRefresh: Boolean = false) = repository.getInstallEnvironment(forceRefresh)
}
