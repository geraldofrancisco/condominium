package com.p2p.condominium.util;

import com.p2p.condominium.document.BaseDocument;
import com.p2p.condominium.document.BasicInformationDocument;

import java.time.LocalDateTime;

public class BaseDocumentUtil {
    public static BaseDocument insertInformation(BaseDocument document) {
        document.setBasicInformation(BasicInformationDocument.builder().insertionDate(LocalDateTime.now()).build());
        return document;
    }

    public static BaseDocument updateInformation(BaseDocument document) {
        document.setBasicInformation(BasicInformationDocument.builder().lastUpdateDate(LocalDateTime.now()).build());
        return document;
    }
}
