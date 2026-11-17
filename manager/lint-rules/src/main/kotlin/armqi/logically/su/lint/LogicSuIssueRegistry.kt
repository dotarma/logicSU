package armqi.logically.su.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API

class LogicSuIssueRegistry : IssueRegistry() {
    override val issues = listOf(
        SegmentedColumnScopeConditionDetector.ISSUE,
        DirectHorizontalPagerDetector.ISSUE,
    )

    override val api = CURRENT_API
}
