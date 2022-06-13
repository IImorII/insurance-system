package by.atsitou.documents.infrastructure.adapters.web

import io.micronaut.http.annotation.Controller
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.validation.Validated
import by.atsitou.documents.api.DocumentsOperations
import by.atsitou.documents.api.queries.finddocuments.FindDocumentsResult
import by.atsitou.documents.api.queries.finddocuments.GeneratedDocument
import by.atsitou.documents.domain.PolicyDocumentRepository

@ExecuteOn(TaskExecutors.IO)
@Validated
@Controller("/documents")
class DocumentsController(private val policyDocumentRepository: PolicyDocumentRepository) : DocumentsOperations {

    override fun find(policyNumber: String): FindDocumentsResult {
        val findByPolicyNumber = policyDocumentRepository.findByPolicyNumber(policyNumber)
        val list = findByPolicyNumber.map { policyDocument -> GeneratedDocument(policyNumber, policyDocument.bytes) }
        return FindDocumentsResult(list)
    }
}
