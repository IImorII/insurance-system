package by.atsitou.documents.infrastructure.adapters.jsreport

import by.atsitou.policy.service.api.v1.events.dto.PolicyDto

data class ReportRequest(
    val template: Template,
    val options: TemplateOptions,
    val data: PolicyDto
)