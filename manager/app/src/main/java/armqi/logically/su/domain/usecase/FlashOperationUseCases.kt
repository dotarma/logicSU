package armqi.logically.su.domain.usecase

import armqi.logically.su.data.flash.FlashRepository
import armqi.logically.su.domain.model.FlashOperation

class ExecuteFlashOperationUseCase(private val repository: FlashRepository) {
    operator fun invoke(operation: FlashOperation) = repository.execute(operation)
}
