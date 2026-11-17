package armqi.logically.su.ui.component

import androidx.compose.runtime.Composable
import armqi.logically.su.domain.model.KernelStatus

@Composable
inline fun KsuIsValid(
    status: KernelStatus,
    content: @Composable () -> Unit,
) {
    if (status.isFullFeatured) {
        content()
    }
}
