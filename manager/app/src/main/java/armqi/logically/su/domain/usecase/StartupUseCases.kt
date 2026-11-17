package armqi.logically.su.domain.usecase

import armqi.logically.su.data.startup.StartupRepository

class ObserveStartupStateUseCase(
    private val repository: StartupRepository,
) {
    operator fun invoke() = repository.state
}
