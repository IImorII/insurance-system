package by.atsitou.documents

import io.micronaut.runtime.Micronaut

object DocumentsApplication {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("documents.service")
                .mainClass(DocumentsApplication.javaClass)
                .start()
    }
}