package armqi.logically.su.domain.usecase

import armqi.logically.su.data.network.NetworkStatusRepository
import armqi.logically.su.data.system.HomeRuntimeRepository

class GetHomeBasicInfoUseCase(private val repository: HomeRuntimeRepository) {
    suspend operator fun invoke(
        managerUapiVersion: Int,
        includeSelinuxStatus: Boolean = true,
    ) = repository.getBasicInfo(managerUapiVersion, includeSelinuxStatus)
}

class IsNetworkAvailableUseCase(private val repository: NetworkStatusRepository) {
    operator fun invoke() = repository.isAvailable()
}
