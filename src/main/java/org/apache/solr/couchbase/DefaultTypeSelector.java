package org.apache.solr.couchbase;


public class DefaultTypeSelector implements TypeSelector {

    public static final String DEFAULT_DOCUMENT_TYPE_DOCUMENT = "couchbaseDocument";
    public static final String DEFAULT_DOCUMENT_TYPE_CHECKPOINT = "couchbaseCheckpoint";

    private String defaultDocumentType;
    private String checkpointDocumentType;

    @Override
    public void configure(Settings settings) {
        this.defaultDocumentType = settings.get("couchbase.defaultDocumentType", DEFAULT_DOCUMENT_TYPE_DOCUMENT);
        this.checkpointDocumentType = settings.get("couchbase.checkpointDocumentType", DEFAULT_DOCUMENT_TYPE_CHECKPOINT);
    }

    @Override
    public String getType(String index, String docId) {
        if(docId.startsWith("_local/")) {
            return this.checkpointDocumentType;
        }
        return this.defaultDocumentType;
    }

}
