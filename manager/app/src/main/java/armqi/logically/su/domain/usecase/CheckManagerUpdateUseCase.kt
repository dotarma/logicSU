package armqi.logically.su.domain.usecase

import armqi.logically.su.data.update.ManagerUpdateRepository
import armqi.logically.su.domain.model.ManagerUpdateChannel
import armqi.logically.su.domain.model.ManagerUpdateInfo

class CheckManagerUpdateUseCase(
    private val repository: ManagerUpdateRepository,
) {
    suspend operator fun invoke(channel: ManagerUpdateChannel): ManagerUpdateInfo? = when (channel) {
        ManagerUpdateChannel.STABLE -> repository.checkStableUpdate()
        ManagerUpdateChannel.BETA -> repository.checkBetaUpdate()
    }
}
