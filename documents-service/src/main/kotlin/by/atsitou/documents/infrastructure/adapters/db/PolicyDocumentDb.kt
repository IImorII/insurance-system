package by.atsitou.documents.infrastructure.adapters.db

import by.atsitou.documents.domain.PolicyDocument
import by.atsitou.documents.domain.PolicyDocumentRepository
import by.atsitou.documents.infrastructure.annotations.RequiresJdbc
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional
import kotlin.streams.toList

@Singleton
@RequiresJdbc
open class PolicyDocumentDb(
        private val entityManager: EntityManager
) : PolicyDocumentRepository {


    @Transactional
    override fun add(document: PolicyDocument) {
        entityManager.persist(document)
    }

    @Transactional
    override fun findByPolicyNumber(policyNumber: String): List<PolicyDocument> {
        return entityManager
                .createQuery<PolicyDocument>("from PolicyDocument t where t.policyNumber= :policyNumber", PolicyDocument::class.java)
                .setParameter("policyNumber", policyNumber)
                .resultStream
                .toList()

    }

}