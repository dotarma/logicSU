package armqi.logically.su.domain.usecase

import armqi.logically.su.data.packageinfo.SuperUserRepository

class GetSuperUserAppGroupUseCase(private val repository: SuperUserRepository) {
    suspend operator fun invoke(uid: Int, primaryPackageName: String) = repository.getAppGroup(uid, primaryPackageName)
}
