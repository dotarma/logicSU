package armqi.logically.su.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import armqi.logically.su.R
import armqi.logically.su.data.script.ScriptRunRecord
import java.text.DateFormat
import java.util.Date

@Composable
fun scriptLastRunText(record: ScriptRunRecord?): String {
    if (record == null) return stringResource(R.string.script_never_run)
    val time = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(Date(record.time))
    return if (record.versionCode != null) {
        stringResource(R.string.script_last_run_version, time, record.versionCode, record.exitCode)
    } else {
        stringResource(R.string.script_last_run, time, record.exitCode)
    }
}
