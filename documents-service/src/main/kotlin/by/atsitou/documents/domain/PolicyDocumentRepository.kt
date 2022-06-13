package by.atsitou.documents.domain

interface PolicyDocumentRepository {
    fun add(document: PolicyDocument)
    fun findByPolicyNumber(policyNumber: String): List<PolicyDocument>
}