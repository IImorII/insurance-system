package by.atsitou.documents.domain

import io.micronaut.http.HttpResponse
import by.atsitou.policy.service.api.v1.events.PolicyRegisteredEvent

interface ReportGenerator {
    fun generate(event: PolicyRegisteredEvent): HttpResponse<ByteArray>?
}